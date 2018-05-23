package logic;

/**
 *
 * @author Gabriel Huertas, Cesar Cardozo
 */
public class Condensation {

    private Partition partition1;
    private Partition partition2;
    private Partition condensatedPartition;
    private int condensationSize;
    private int condensationID;
    private String condensationName;
    private static int BASE_ID = 1;

    public Condensation(Partition partition1, Partition partition2) {
        this.partition1 = partition1;
        this.partition2 = partition2;
        this.condensationID = BASE_ID++;
        setCondensationName("C" + this.condensationID);
        this.condensationSize = getPartition1().getPartitionSize() + partition2.getPartitionSize();
    }

    public Partition getPartition1() {
        return partition1;
    }

    public void setPartiton1(Partition partiton1) {
        this.partition1 = partiton1;
    }

    public Partition getPartition2() {
        return partition2;
    }

    public void setPartition2(Partition partition2) {
        this.partition2 = partition2;
    }

    public int getCondensationSize() {
        return condensationSize;
    }

    public void setCondensationSize(int getCondensationSize) {
        this.condensationSize = getCondensationSize;
    }

    public int getCondensationID() {
        return condensationID;
    }

    public void setCondensationID(int condensationID) {
        this.condensationID = condensationID;
    }

    public String getCondensationName() {
        return condensationName;
    }

    public Partition getCondensatedPartition() {
        return condensatedPartition;
    }

    public void setCondensatedPartition(Partition condensatedPartition) {
        this.condensatedPartition = condensatedPartition;
    }

    public void setCondensationName(String condensationName) {
        this.condensationName = condensationName;
    }

    public Object[] getDataVector() {
        return new Object[]{getCondensationName(),
            getPartition1().getPartitionName(),
            getPartition1().getPartitionSize(),
            getPartition2().getPartitionName(),
            getPartition2().getPartitionSize(),
            getCondensatedPartition().getPartitionName(),
            getCondensatedPartition().getPartitionSize()
        };
    }

    @Override
    public String toString() {
        return "Condensation{"+ partition1.getPartitionName() + " - "+ partition2.getPartitionName() + ", condensationSize=" + condensationSize + ", condensationID=" + condensationID + ", condensationName=" + condensationName + "}\n";
    }
    
    

}
