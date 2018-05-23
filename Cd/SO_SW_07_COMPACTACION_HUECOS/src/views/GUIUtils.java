package views;

/**
 * Esta clase representa una serie de constantes utilizadas en las ventanas del
 * programa
 *
 * @author Gabriel Huertas y Cesar Cardozo
 */
public class GUIUtils {

    public static final String APP_TITLE = "Simulador de transicion de estados de procesos";

    //---------------- GUI TABLES HEADERS ------------------
    public static final String[] ADD_PROCESSES_TABLE_HEADERS = new String[]{"Nombre", "Tiempo", "Tamaño"};
    public static final String[] REPORT_PROCESSES_TABLE_HEADERS = new String[]{"Nombre", "Tiempo", "Tamaño", "Partición Asignada"};
    public static final String[] ADD_PARTITIONS_TABLE_HEADERS = new String[]{"Nombre", "Tamaño"};
    public static final String[] ORDER_FINISHING_PARTITIONS_TABLE_HEADERS = new String[]{"Nombre Partición"};
    public static final String ADD_PARTITIONS_LABEL_HEADER = "Particiones";
    public static final String ADD_PROCESSES_LABEL_HEADER = "Procesos";
    public static final String IO_PROCESSES_LABEL_HEADER = "Procesos E/S";
    //SW_07
    public static final String[] IO_PROCESSES_TABLE_HEADERS = new String[]{"Procesos de Entrada", "Procesos de Salida"};
    public static final String[] ALLOCATION_TABLE_HEADERS = new String[]{"Partición", "Proceso", "Tamaño"};
    public static final String[] FREEHOLES_TABLE_HEADER = new String[]{"Nombre", "Dirección Inicial", "Dirección Final", "Tamaño"};
    public static final String[] COMPACTATIONS_TABLE_HEADER = new String[]{"Nombre", "Dirección Inicial", "Dirección Final"};

    //---------------------- MESSAGES ----------------------
    public static final String MSG_EMPTY_FIELDS = "Hay campos vacíos";
    public static final String MSG_PROCESS_ALREADY_EXISTS = "Este proceso ya existe";
    public static final String MSG_NO_PROCESS = "No hay procesos para ejecutar";
    public static final String MSG_NO_PARTITIONS = "No hay al menos una partición ";
    public static final String MSG_INVALID_TIME = "Tiempo no válido o demasiado grande";
    public static final String MSG_CANNOT_DELETE_PARTITION = "No se puede borrar la partición";
    public static final String MSG_CANNOT_DELETE_PROCESS = "No se puede borrar la el proceso";

    public static String MSG_PARTITION_ALREADY_EXISTS = "Esta partición ya existe";
    public static String MSG_INVALID_NUMBER = "Tamaño invalido";
    public static String MSG_CANNOT_EDIT_PARTITION = "No se puede editar la partición porque tiene procesos asociados";
    public static String MSG_CONFIRM_DELETE_PARTITION = "¿Está seguro de borrar esta partición?";
    public static String MSG_CONFIRM_DELETE_PROCESS = "¿Está seguro de borrar este proceso?";

}
