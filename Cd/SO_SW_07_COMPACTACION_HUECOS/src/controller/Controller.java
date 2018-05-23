package controller;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import logic.*;
import logic.ProcessManager;
import views.AddProcessDialog;
import views.GUIUtils;
import static views.GUIUtils.APP_TITLE;
import views.MainWindow;

/**
 * Clase que funciona como intermediaria entre la capa Lógica y la capa de Vista
 * Enruta las acciones generadas desde la vista para la ejecución de ciertas
 * tareas en la lógica y muestra un conjunto de datos procesados en la GUI
 *
 * @author Cesar Cardozo y Gabriel Amaya
 */
public class Controller implements ActionListener {

    //----------------- Atributos ------------------------
    /**
     * Clase lógica principal manejadora de enventos
     */
    private ProcessManager processManager;

    /**
     * Ventana de diálogo para crear y agregar un nuevo proceso
     */
    private AddProcessDialog addProcessDialog;

    /**
     * GUI principal
     */
    private MainWindow mainWindow;

    /**
     * Atributo privado para la creación de única instancia de clase
     */
    private static Controller controller;

    //-------------------- Constructores -------------------------
    /**
     * Crea una nueva instancia de la clase Controller
     *
     * @param processManager Manejador de Procesos
     * @return instancia Singleton de la clase
     */
    public static Controller getInstance(ProcessManager processManager) {
        if (controller == null) {
            controller = new Controller(processManager);
        }
        return controller;
    }

    /**
     * Crea una nueva instancia de la clase Controller
     *
     * @return instancia Singleton de la clase
     */
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    private Controller() {
        processManager = new ProcessManager();
        mainWindow = new MainWindow(this);
        addProcessDialog = new AddProcessDialog(mainWindow, true, this);
    }

    private Controller(ProcessManager processManager) {
        this.processManager = processManager;
        mainWindow = new MainWindow(this);
        addProcessDialog = new AddProcessDialog(mainWindow, true, this);
        showProcesses();
    }

    //----------------------- Métodos -------------------------
    /**
     * Método implementado de la clase ActionListener que define una estructura
     * para el manejo de eventos provenientes de la GUI y que permite
     * interactuar con la capa lógica del programa
     *
     * @param e intancia de ActionEvent lanzada por el objeto que genero dicho
     * evento
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Evalúa el comando que generó dicha acción dentro de la lista de comandos
        //del enumerado Actions
        switch (Actions.valueOf(e.getActionCommand())) {
            case OPEN_CREATE_PROCESS:
                openCreateProcess();
                break;
            case CREATE_PROCESS:
                createProcess();
                break;
            case EDIT_PROCESS:
                editProcess();
                break;
            case START:
                start();
                break;
            case OPEN_DEFINE_QUANTUM:
                openDefineQuantum();
                break;
            case OPEN_DEFINE_MEMORY_SIZE:
                openDefineMemorySize();
                break;
            case SHOW_PARTITIONS_AND_PROCESSES:
                showProcesses();
                break;
            case SHOW_PARTITIONS_REPORT_1:
                showPartitionsReport1();
                break;
            case SHOW_PARTITIONS_REPORT_2:
                showPartitionsReport2();
                break;
        }
    }

    private void openCreateProcess() throws HeadlessException {
        addProcessDialog.getCreateProcessbtn().setActionCommand(Actions.CREATE_PROCESS.name());
        addProcessDialog.getProcessNamejtf().setEditable(true);
        addProcessDialog.getProcessNamejtf().revalidate();
        addProcessDialog.setVisible(true);
    }

    /**
     * Agrega un proceso
     */
    private void createProcess() {
        //Crea una nuevo Proceso, si no es nulo, significa que ha sido creado
        //Exitosamente, es decir
        try {
            logic.Process process = addProcessDialog.createProcess(processManager.getInput_ProcessList());
            if (process != null) {
                //Agrega el proceso en la lógica y si ha sido agregado correctamente,
                //Lo agrega a la GUI y cierra el diálogo de agregar proceso
                if (processManager.addProcess(process)) {
                    mainWindow.addProcess(process);
                    addProcessDialog.close();
                } else {
                    //Muestra un mensaje de error indicando que el proceso
                    //Ya existe en la lógica
                    JOptionPane.showMessageDialog(addProcessDialog,
                            GUIUtils.MSG_PROCESS_ALREADY_EXISTS,
                            APP_TITLE,
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                //Muestra un mensaje de error indicando que en el diálogo para agregar
                //Procesos hay campos vacíos
                JOptionPane.showMessageDialog(addProcessDialog,
                        GUIUtils.MSG_EMPTY_FIELDS,
                        APP_TITLE,
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(addProcessDialog,
                    e.getMessage(),
                    APP_TITLE,
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Comienza la ejecución de los procesos
     */
    private void start() {
        if (processManager.getInput_ProcessList().isEmpty()) {
            //Muestra un mensaje de error indicando que no hay procesos para eje-
            //cutarse
            JOptionPane.showMessageDialog(mainWindow,
                    GUIUtils.MSG_NO_PROCESS,
                    APP_TITLE,
                    JOptionPane.ERROR_MESSAGE);
        } else {
            processManager.processProcesses();
            mainWindow.showOptions(false);
            mainWindow.showProcesses(processManager.getInput_ProcessList());

        }
    }

    /**
     * Define un nuevo Quantum para el manejador de procesos
     */
    private void openDefineQuantum() {
        String input = JOptionPane.showInputDialog(mainWindow, "Indique el nuevo valor del quantum");
        //Verifica que el String de entrada sea numérico y que no esté vacío
        //Si es así, cambia el Quantum del manejador de procesos
        if (addProcessDialog.isNumeric(input) && !input.isEmpty() && input.length() < 6) {
            processManager.setQuantum(Integer.parseInt(input));
        } else {
            //Muestra un mensaje de error que indica que el tiempo ingresado es
            //inválido
            JOptionPane.showMessageDialog(mainWindow,
                    GUIUtils.MSG_INVALID_TIME,
                    GUIUtils.APP_TITLE,
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openDefineMemorySize() {
        String input = JOptionPane.showInputDialog(mainWindow, "Indique el nuevo tamaño de la memoria");
        //Verifica que el String de entrada sea numérico y que no esté vacío
        //Si es así, cambia el Quantum del manejador de procesos
        if (addProcessDialog.isNumeric(input) && !input.isEmpty() && input.length() < 6) {
            processManager.setMemory(Integer.parseInt(input));
        } else {
            //Muestra un mensaje de error que indica que el tiempo ingresado es
            //inválido
            JOptionPane.showMessageDialog(mainWindow,
                    GUIUtils.MSG_INVALID_TIME,
                    GUIUtils.APP_TITLE,
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setProcessManager(ProcessManager processManager) {
        this.processManager = processManager;
    }

    /**
     * Muestra las particiones y procesos creados
     */
    private void showProcesses() {
        mainWindow.showProcesses(processManager.getInput_ProcessList());
    }

    /**
     *
     * @param value
     * @return
     */
    public void editProcess(String value) {
        try {
            if (processManager.searchProcess(value, processManager.getInput_ProcessList()) != null) {
                logic.Process pro = processManager.searchProcess(value, processManager.getInput_ProcessList());
                addProcessDialog.getCreateProcessbtn().setActionCommand(Actions.EDIT_PROCESS.name());
                addProcessDialog.setValues(pro, processManager.getPartitionsList());
                addProcessDialog.getProcessNamejtf().setEditable(false);
                addProcessDialog.setVisible(true);
                addProcessDialog.getProcessNamejtf().revalidate();
            } else {
                JOptionPane.showMessageDialog(mainWindow,
                        GUIUtils.MSG_CANNOT_EDIT_PARTITION,
                        APP_TITLE, JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param processName Nombre del proceso a borrar
     */
    public void deleteProcess(String processName) {

        int option = JOptionPane.showConfirmDialog(mainWindow, GUIUtils.MSG_CONFIRM_DELETE_PROCESS, APP_TITLE, JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                logic.Process p = processManager.searchProcess(processName, processManager.getInput_ProcessList());
                processManager.getInput_ProcessList().remove(p);
                mainWindow.showProcesses(processManager.getInput_ProcessList());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainWindow,
                        GUIUtils.MSG_CANNOT_DELETE_PROCESS,
                        APP_TITLE, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showPartitionsReport1() {
        mainWindow.showProcessesAllocationAndIOProcesses(processManager.getPartitionsList(), processManager.getInput_ProcessList(), processManager.getOutput_ProcessList());
    }

    private void showPartitionsReport2() {
        mainWindow.showOrderFinishingPartitionsAndCompactationsAndFreeHoles(processManager.getOutput_PartitionsList(), processManager.getCompactationList(),processManager.getFreeHolesList());
    }

    private void editProcess() {
        try {
            logic.Process pro = addProcessDialog.editProccess(processManager.getPartitionsList());
            int indexOfProcesstoEdit = processManager.getInput_ProcessList().indexOf(processManager.searchProcess(pro.getName(), processManager.getInput_ProcessList()));
            processManager.getInput_ProcessList().set(indexOfProcesstoEdit, pro);
            mainWindow.showProcesses(processManager.getInput_ProcessList());
            addProcessDialog.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(mainWindow,
                    ex.getMessage(),
                    GUIUtils.APP_TITLE,
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
