package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa el manejador de procesos. Se encarga de crear, agregar y
 * atender cada uno de los procesos de acuerdo a sus características. Su modo de
 * atención es FIFO (First Input, First Output), teniendo prelación sobre
 * aquellos procesos que no están bloqueados.
 *
 * @author Cesar Cardozo & Gabriel Amaya
 */
public class ProcessManager {

    //------------------------ Atributos -----------------------------
    public static final int DEFAULT_QUANTUM = 3;
    public static final int DEFAULT_MEMORY = 32;

    private ArrayList<Process> input_ProcessList;
    private ArrayList<Process> output_ProcessList;
    private ArrayList<Partition> output_PartitionList;
    private ArrayList<Partition> partitionsList;
    private ArrayList<Partition> currentPartitionList;
    private ArrayList<Compactation> compactationList;
    private ArrayList<Partition> freeHolesList;

    private int currentUsedMemory;
    private int lastAssigned;
    private int lastDireccion;
    private int quantum;
    private int memory;
    private int currentIndex;

    //------------------------ Constructores -----------------------------
    public ProcessManager() {
        this.quantum = DEFAULT_QUANTUM;
        this.memory = DEFAULT_MEMORY;
        this.currentUsedMemory = 0;
        this.lastAssigned = 0;
        this.lastDireccion = 0;
        this.currentIndex = 0;
        this.input_ProcessList = new ArrayList<>();
        this.output_ProcessList = new ArrayList<>();
        this.partitionsList = new ArrayList<>();
        this.currentPartitionList = new ArrayList<>();
        this.output_PartitionList = new ArrayList<>();
        this.compactationList = new ArrayList<>();
        this.freeHolesList = new ArrayList<>();
    }

    //------------------------ Métodos -----------------------------
    /**
     * Agregar un nuevo proceso al manejador de procesos. En un principio, lo
     * agrega a la lista de procesos de entrada, luego a la lista de procesos
     * listos y por último lo despacha (lo que indica que también lo agrega a la
     * lista de procesos en ejecución). Adicionalmente revisa si el proceso está
     * bloqueado y si lo está, lo agrega a la lista de procesos bloqueados
     *
     * @param p El proceso a ser agregado
     * @return true si el proceso fue agregado, de lo contrario, false
     */
    public boolean addProcess(Process p) {
        input_ProcessList.add(p);
        return true;

    }

    /**
     * Agregar un nuevo proceso al manejador de procesos. En un principio, lo
     * agrega a la lista de procesos de entrada, luego a la lista de procesos
     * listos y por último lo despacha (lo que indica que también lo agrega a la
     * lista de procesos en ejecución). Adicionalmente revisa si el proceso está
     * bloqueado y si lo está, lo agrega a la lista de procesos bloqueados
     *
     * @param par El proceso a ser agregado
     * @return true si el proceso fue agregado, de lo contrario, false
     */
    public boolean addPartition(Partition par) {
        //Busca en la lista de procesos de entrada si existe un proceso con el 
        //mismo nombre, si no, lo agrega a la lista de procesos de entrada, lista
        //procesos listos y hace la transisiónde despachado
        try {
            searchPartition(par.getPartitionName());
            return false;
        } catch (Exception e) {
            partitionsList.add(par);
            return true;
        }
    }

    /**
     *
     * @param name El nombre el proceso
     * @param executionTime El tiempo de ejecución del proceso
     * @param processSize Tamaño ocupado por el proceso
     * @return Una nueva instancia de la clase Proceso con los datos ingresados
     */
    public static Process createProcess(String name, int processSize, int executionTime) {
        return new Process(name, processSize, executionTime);
    }
    /**
     * Busca el proceso con el nombre especificado dentro de la lista
     * especifica- da
     *
     * @param name Nombre del proceso
     * @param list Lista en la cual debe buscar el proceso
     * @return El proceso con el nombre especificado, null si no lo encontró
     */
    public Process searchProcess(String name, ArrayList<Process> list) throws Exception {
        for (Process process : list) {
            if (process.getName().equals(name)) {
                return process;
            }
        }
        throw new Exception("No se pudo encontrar el proceso: " + name + ", en la lista: " + list.toString());
    }
    
    public void processProcesses() {
        do {
            //asinacion-----------------
            do {
                assign(lastDireccion);
            } while (lastDireccion != memory);
            //ejecucion---------------------
            lastDireccion = returnLastAsignedMemory();
            ArrayList<Process> procesToAcomodate = excecute(lastDireccion);
            System.out.println("tiene que acomodar "+procesToAcomodate.size());
            currentPartitionList = new ArrayList<>();
            int aux = 0;
            for (Process process : procesToAcomodate) {
                Partition par = new Partition(aux, process);
                System.out.println("compacta "+par);
                Compactation con = new Compactation(par);
                compactationList.add(con);
                try {
                    partitionsList.add((Partition) par.clone());
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
                currentPartitionList.add(par);
                lastDireccion = par.getFinalAddress();
                aux += par.getPartitionSize();
            }
        } while (input_ProcessList.size() != output_ProcessList.size());
        createPartitionWihRemainingMemory(memory);
    }

    public void assign(int index) {
        int remainingMemory = memory - index;
        if (lastAssigned < input_ProcessList.size()) {
            Process pro = input_ProcessList.get(lastAssigned);
            if (remainingMemory >= pro.getProcessSize()) {
                Partition par = new Partition(lastDireccion, pro);
                lastDireccion = par.getFinalAddress();
                if (!containsPartition(par)) {
                    try {
                        partitionsList.add((Partition) par.clone());
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                    }
                }
                currentPartitionList.add(par);
                lastAssigned++;
            } else {
                Partition par = new Partition(lastDireccion, remainingMemory);
                if (!containsFreeHoles(par)) {
                    try {
                        freeHolesList.add((Partition) par.clone());
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                    }
                }
                lastDireccion = memory;
                if (!containsPartition(par)) {
                    try {
                        partitionsList.add((Partition) par.clone());
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                    }
                }
                currentPartitionList.add(par);
            }
        } else {
            if (remainingMemory > 0) {
                Partition par = new Partition(lastDireccion, remainingMemory);
                if (!containsFreeHoles(par)) {
                    try {
                        freeHolesList.add((Partition) par.clone());
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                    }
                }
                lastDireccion = memory;
                if (!containsPartition(par)) {
                    try {
                        partitionsList.add((Partition) par.clone());
                    } catch (CloneNotSupportedException ex) {
                        ex.printStackTrace();
                    }
                }
                currentPartitionList.add(par);
            }
        }
    }

    public boolean containsFreeHoles(Partition par) {
        for (Partition para : freeHolesList) {
            if (para.getPartitionSize() == par.getPartitionSize()) {
                return true;
            }
        }
        return false;
    }

    public boolean containsPartition(Partition par) {
        for (Partition para : partitionsList) {
            if (para.getPartitionSize() == par.getPartitionSize()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Process> excecute(int finalDir) {
        ArrayList<Process> aux = new ArrayList<>();
        ArrayList<Process> procesUnterminated = new ArrayList<>();
        for (int i = 0; i < currentPartitionList.size(); i++) {
            Partition par = currentPartitionList.get(i);
            Process pro = par.getAssignedProcess();
            if (par.getInitialAddress() < finalDir) {
                pro.setExecutionTime(pro.getExecutionTime() - quantum);
                if (pro.getExecutionTime() <= 0) {
                    aux.add(pro);
                    par.setAssignedProcess(null);
                    if (!containsFreeHoles(par)) {
                        freeHolesList.add(par);
                    }
                } else {
                    procesUnterminated.add(pro);
                    lastDireccion = par.getFinalAddress();
                }
            } else {
                break;
            }
        }
        Collections.sort(aux);
        for (Process process : aux) {
            output_ProcessList.add(process);
            output_PartitionList.add(searchIntoPartition(process));
        }
        return procesUnterminated;
    }

    public void createPartitionWihRemainingMemory(int remainingMemory) {
        Partition par = new Partition(0, remainingMemory);
        lastAssigned = memory;
        if (!containsPartition(par)) {
            try {
                partitionsList.add((Partition) par.clone());
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
        }
        if (!containsFreeHoles(par)) {
            try {
                freeHolesList.add((Partition) par.clone());
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
        }
        currentPartitionList.add(par);
    }

    public int returnLastAsignedMemory() {
        for (int i = 0; i < currentPartitionList.size(); i++) {
            if (currentPartitionList.get(i).getAssignedProcess() == null) {
                return currentPartitionList.get(i).getInitialAddress();
            }
        }
        return memory;
    }

    public Partition searchIntoPartition(Process pro) {
        for (Partition partition : partitionsList) {
            if (partition.getAssignedProcess() != null) {
                if (partition.getAssignedProcess().getName().equals(pro.getName())) {
                    return partition;
                }
            }
        }
        return null;
    }

    /**
     * Busca el proceso con el nombre especificado dentro de la lista
     * especifica- da
     *
     * @param name Nombre del proceso
     * @return El proceso con el nombre especificado, null si no lo encontró
     */
    public Partition searchPartition(String name) throws Exception {
        for (Partition partition : partitionsList) {
            if (partition.getPartitionName().equals(name)) {
                return partition;
            }
        }
        throw new Exception("No se pudo encontrar la partición: " + name);
    }

    public Object[] getPartitionTableHeaders() {
        ArrayList<String> partitionsHeaders = new ArrayList<>();
        for (Partition partition : partitionsList) {
            partitionsHeaders.add("Part. " + partition.getPartitionName());
        }

        return partitionsHeaders.toArray();
    }

    //---------------- Getters & Setters -----------------------
    public ArrayList<Process> getInput_ProcessList() {
        return input_ProcessList;
    }

    public ArrayList<Process> getOutput_ProcessList() {
        return output_ProcessList;
    }

    public double getQuantum() {
        return quantum;
    }

    public ArrayList<Partition> getPartitionsList() {
        return partitionsList;
    }

    public ArrayList<Partition> getOutput_PartitionsList() {
        return output_PartitionList;
    }

    public ArrayList<Compactation> getCompactationList() {
        return compactationList;
    }

    public void setCompactationList(ArrayList<Compactation> compactationList) {
        this.compactationList = compactationList;
    }

    public ArrayList<Partition> getFreeHolesList() {
        return freeHolesList;
    }

    @Override
    public String toString() {
        String aux = "Partitions general\n";
        for (Partition partition : partitionsList) {
            aux += partition.toString() + "\n";
        }
        aux += "\ncompactaciones\n";
        for (Compactation compactation : compactationList) {
            aux += compactation.toString() + "\n";
        }
        aux += "\nHoles\n";
        for (Partition partition : freeHolesList) {
            aux += partition.toStringHole() + "\n";
        }
        aux += "\nentrada\n";
        for (Process pro : input_ProcessList) {
            aux += pro.toString() + "\n";
        }
        aux += "\nsalida procesos\n";
        for (Process pro : output_ProcessList) {
            aux += pro.toString() + "\n";
        }
        aux += "\nsalida particiones\n";
        for (Partition partition : output_PartitionList) {
            aux += partition.toString() + "\n";
        }
        return aux;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getLastDireccion() {
        return lastDireccion;
    }

    public void setLastDireccion(int lastDireccion) {
        this.lastDireccion = lastDireccion;
    }

}
