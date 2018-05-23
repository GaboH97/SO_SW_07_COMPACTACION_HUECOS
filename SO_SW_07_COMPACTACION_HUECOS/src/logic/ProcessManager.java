package logic;

import java.util.ArrayList;

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
    public static final int DEFAULT_QUANTUM = 5;
    public static final int DEFAULT_MEMORY = 50;

    private ArrayList<Process> input_ProcessList;
    private ArrayList<Process> output_ProcessList;
    private ArrayList<Partition> output_PartitionList;
    private ArrayList<Partition> partitionsList;
    private ArrayList<Partition> currentPartitionList;
    private ArrayList<Compactation> compactationList;
    private ArrayList<Partition> freeHolesList;
    //ESTO TOCA QUITARLO!!!!!!
    private ArrayList<Condensation> condensations;

    private int quantum;
    private int memory;
    private int currentUsedMemory;
    private int lastAssigned;
    private int currentIndex;

    //------------------------ Constructores -----------------------------
    public ProcessManager() {
        this.quantum = DEFAULT_QUANTUM;
        this.memory = DEFAULT_MEMORY;
        this.currentUsedMemory = 0;
        this.lastAssigned = 0;
        this.currentIndex = 0;
        this.input_ProcessList = new ArrayList<>();
        this.output_ProcessList = new ArrayList<>();
        this.partitionsList = new ArrayList<>();
        this.currentPartitionList = new ArrayList<>();
        this.condensations = new ArrayList<>();
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
        //Busca en la lista de procesos de entrada si existe un proceso con el 
        //mismo nombre, si no, lo agrega a la lista de procesos de entrada, lista
        //procesos listos y hace la transisiónde despachado
        try {
            searchProcess(p.getName(), input_ProcessList);
            return false;
        } catch (Exception e) {
            try {
                input_ProcessList.add(p);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
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
     *
     * @param partitionName
     * @param partitionSize
     * @return Una nueva instancia de la clase Proceso con los datos ingresados
     */
    public static Partition createPartition(Process pro) {
        //OJO TOCA CAMBIAR
        return new Partition(0, pro);
    }

    public boolean assignProcessesInitially(Process pro) {
        if (currentUsedMemory + pro.getProcessSize() <= memory) {
            Partition par = createPartition(pro);
            try {
                partitionsList.add((Partition) par.clone());
            } catch (Exception e) {
                e.printStackTrace();
            }
            currentPartitionList.add(par);
            currentUsedMemory += par.getPartitionSize();
            lastAssigned++;
            return true;
        }
        return false;
    }

    public int fits(Process pro) {
        for (int i = 0; i < currentPartitionList.size(); i++) {
            if (currentPartitionList.get(i).getAssignedProcess() == null && currentPartitionList.get(i).getPartitionSize() >= pro.getProcessSize()) {
                return i;
            }
        }
        return -1;
    }

    public void assignProcess() {
        for (int i = lastAssigned; i < input_ProcessList.size(); i++) {
            Process pro = input_ProcessList.get(i);
            int index = fits(pro);
            if (index != -1) {
                if (currentPartitionList.get(index).getPartitionSize() == pro.getProcessSize()) {
                    currentPartitionList.get(index).setAssignedProcess(pro);
                } else {
                    int remeaning = currentPartitionList.get(index).getPartitionSize() - pro.getProcessSize();
                    //OJO AQUI TAMBIEN!!!!!!!!!!!
                    Partition par1 = new Partition(0, pro);
                    Partition par2 = new Partition(0, remeaning);
                    currentPartitionList.set(index, par1);
                    currentPartitionList.add(index + 1, par2);
                    try {
                        partitionsList.add((Partition) par1.clone());
                        partitionsList.add((Partition) par2.clone());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    lastAssigned++;
                }
            }
        }

    }

    public void processProcesses1() {
        for (Process pro : input_ProcessList) {
            if (assignProcessesInitially(pro)) {
            } else {
                break;
            }
        }
        excecuteProcess();
        condensate();
        do {
            assignProcess();
            excecuteProcess();
            condensate();
        } while (input_ProcessList.size() != output_ProcessList.size());
    }

    public void condensate() {
        boolean aux = false;
        for (int i = 0; i < currentPartitionList.size() - 1; i++) {
            if (currentPartitionList.get(i).getAssignedProcess() == null && currentPartitionList.get(i + 1).getAssignedProcess() == null) {
                Partition par1 = currentPartitionList.get(i);
                Partition par2 = currentPartitionList.get(i + 1);
                Condensation con = new Condensation(currentPartitionList.get(i), currentPartitionList.get(i + 1));
                //OJO AQUI TAMBIEN!!!!!!!!!!!
                Partition conPar = new Partition(0, con.getCondensationSize());
                con.setCondensatedPartition(conPar);
                condensations.add(con);
                currentPartitionList.set(i, conPar);
                currentPartitionList.remove(i + 1);
                try {
                    partitionsList.add((Partition) conPar.clone());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                aux = true;
            }
        }
        if (aux) {
            condensate();
        }
    }

    public void excecuteProcess() {
        for (int i = currentIndex; i < currentPartitionList.size(); i++) {
            if (currentPartitionList.get(i).getAssignedProcess() != null) {
                currentPartitionList.get(i).getAssignedProcess().setExecutionTime(currentPartitionList.get(i).getAssignedProcess().getExecutionTime() - quantum);
                if (currentPartitionList.get(i).getAssignedProcess().getExecutionTime() <= 0) {
                    currentUsedMemory -= currentPartitionList.get(i).getAssignedProcess().getProcessSize();
                    output_ProcessList.add(currentPartitionList.get(i).getAssignedProcess());
                    output_PartitionList.add(currentPartitionList.get(i));
                    currentPartitionList.get(i).setAssignedProcess(null);
                }
            }
        }
        for (int i = 0; i < currentIndex; i++) {
            if (currentPartitionList.get(i).getAssignedProcess() != null) {
                currentPartitionList.get(i).getAssignedProcess().setExecutionTime(currentPartitionList.get(i).getAssignedProcess().getExecutionTime() - quantum);
                if (currentPartitionList.get(i).getAssignedProcess().getExecutionTime() <= 0) {
                    currentUsedMemory -= currentPartitionList.get(i).getAssignedProcess().getProcessSize();
                    output_ProcessList.add(currentPartitionList.get(i).getAssignedProcess());
                    output_PartitionList.add(currentPartitionList.get(i));
                    currentPartitionList.get(i).setAssignedProcess(null);
                }
            }
        }
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

    public ArrayList<Condensation> getCondensations() {
        return condensations;
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
            aux += partition.toString();
        }
        aux += "\ncondenzazaos\n";
        for (Condensation con : condensations) {
            aux += con.toString();
        }
        aux += "\nlista salida procesos\n";
        for (Process process : output_ProcessList) {
            aux += process.toString();
        }
        aux += "\nlista salida particiones\n";
        for (Partition par : output_PartitionList) {
            aux += par.toString();
        }
        return aux;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

}
