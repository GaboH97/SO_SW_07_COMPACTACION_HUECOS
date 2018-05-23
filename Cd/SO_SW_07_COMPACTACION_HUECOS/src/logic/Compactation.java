/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author user
 */
public class Compactation {

    private Partition partition;
    private String compactationName;
    private int compactationID;
    private int initialAddress;
    private int finalAddress;

    private static int BASE_ID = 1;

    public Compactation(Partition partition) {
        this.compactationID = BASE_ID++;
        this.partition = partition;
        this.initialAddress = partition.getInitialAddress();
        this.finalAddress = partition.getFinalAddress();
        setCompactationName("COM" + String.valueOf(compactationID));
    }

    public Partition getPartition() {
        return partition;
    }

    public void setPartition(Partition partition) {
        this.partition = partition;
    }

    public int getCompactationID() {
        return compactationID;
    }

    public String getCompactationName() {
        return compactationName;
    }

    public void setCompactationID(int compactationID) {
        this.compactationID = compactationID;
    }

    public void setCompactationName(String compactationName) {
        this.compactationName = compactationName;
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

    @Override
    public String toString() {
        return "Compactation{" + ", compactationName=" + compactationName + "partition=" + partition + ", initialAddress=" + initialAddress + ", finalAddress=" + finalAddress + '}';
    }

    public Object[] getDataVector() {
        return new Object[]{getCompactationName(),
            getInitialAddress(),
            getFinalAddress()};
    }

}
