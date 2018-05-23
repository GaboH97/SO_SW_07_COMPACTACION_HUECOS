package logic;

/**
 *
 * @author Gabriel Amaya, Cesar Cardozo
 */
public class Partition implements Cloneable {

    //------------------ Attributes --------------------------
    private String partitionName;
    private String holeName;
    private int partitionID;
    private int holeID;
    private int partitionSize;
    private int initialAddress;
    private int finalAddress;
    //MIRAR SI ES UNA LISTA O UN SOLO PROCESO
    //IGUAL SOLO SE EJECUTA UNO Y LUEGO SE LIBERA LA PARTICIÓN
    private Process assignedProcess;
    private static int PAR_BASE_ID = 1;
    public static int HOLE_BASE_ID = 1;

    //--------------------- Constructors ------------------------
    /**
     * CUANDO HAY UNA CONDENSACIÓN, SE LE PASA EL TAMAÑO DE LA NUEVA PARTICIÓN
     *
     * @param partitionSize
     */
    public Partition(int initialAddress, int partitionSize) {
        partitionID = PAR_BASE_ID++;
        holeID = HOLE_BASE_ID++;
        setHoleName("H" + String.valueOf(holeID));
        setPartitionName("PA" + String.valueOf(partitionID));
        this.partitionSize = partitionSize;
        this.initialAddress = initialAddress;
        this.finalAddress = this.initialAddress + this.partitionSize;
        this.assignedProcess = null;
    }

    /**
     * CUANDO ES HUECO SE LE PONE NULL
     *
     * @param assignedProcess
     */
    public Partition(int initialAddress, Process assignedProcess) {
        partitionID = PAR_BASE_ID++;
        holeID = 0;
        holeName = "";
        setPartitionName("PA" + String.valueOf(partitionID));
        this.assignedProcess = assignedProcess;
        this.partitionSize = this.assignedProcess.getProcessSize();
        this.initialAddress = initialAddress;
        this.finalAddress = this.initialAddress + this.partitionSize;
    }

    public Partition(String partitionName, String holeName, int partitionID, int partitionSize, Process assignedProcess, int initialAddress, int finalAddress) {
        this.partitionID = partitionID;
        holeID = 0;
        this.holeName = holeName;
        setPartitionName("PA" + String.valueOf(partitionID));
        this.assignedProcess = assignedProcess;
        this.partitionSize = finalAddress - initialAddress;
        this.initialAddress = initialAddress;
        this.finalAddress = finalAddress;
    }

    public int getTotalExecutionTime() {
        return assignedProcess.getExecutionTime();
    }

    //------------------ Getters & Setters --------------------------
    public Process getAssignedProcess() {
        return assignedProcess;
    }

    public void setAssignedProcess(Process assignedProcess) {
        this.assignedProcess = assignedProcess;
        if (assignedProcess == null) {
            holeID = HOLE_BASE_ID++;
            setHoleName("H" + String.valueOf(holeID));
        }
    }

    public String getPartitionName() {
        return partitionName;
    }

    public int getPartitionID() {
        return partitionID;
    }

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName;
    }

    public int getPartitionSize() {
        return partitionSize;
    }

    public void setPartitionSize(int partitionSize) {
        this.partitionSize = partitionSize;
    }

    public int getInitialAddress() {
        return initialAddress;
    }

    public void setInitialAddress(int initialAddress) {
        this.initialAddress = initialAddress;
    }

    public int getFinalAddress() {
        return finalAddress;
    }

    public void setFinalAddress(int finalAddress) {
        this.finalAddress = finalAddress;
    }

    public String getHoleName() {
        return holeName;
    }

    public void setHoleName(String holeName) {
        this.holeName = holeName;
    }

    public int getHoleID() {
        return holeID;
    }

    public void setHoleID(int holeID) {
        this.holeID = holeID;
    }

    @Override
    public String toString() {
        return this.partitionName + "Partition " + this.assignedProcess + "size" + this.partitionSize + "\n";
    }

    public String toStringHole() {
        return this.holeName + "Hole " + this.assignedProcess + "size" + this.partitionSize + "\n";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
//        if (this.assignedProcess != null) {
        return new Partition(this.partitionName, this.holeName, this.partitionID, this.partitionSize, this.assignedProcess, this.initialAddress, this.finalAddress);
//        } else {
//            return new Partition(this.partitionName, this.partitionID, this.partitionSize, null, this.initialAddress, this.finalAddress);
//        }
    }

    public Object[] getDataVectorForTableOfProcessesThatPassed() {
        return new Object[]{getPartitionName(), (getAssignedProcess() != null) ? getAssignedProcess().getName() : "NA", getPartitionSize()};
    }

    public Object[] getDataVectorForFreeHoles() {
        System.out.println("cosos");
        return new Object[]{getHoleName(), getInitialAddress(), getFinalAddress(), getPartitionSize()};
    }
}
