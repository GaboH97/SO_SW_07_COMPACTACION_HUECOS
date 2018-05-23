package logic;

/**
 *
 * @author Cesar Cardozo & Gabriel Amaya
 */
public class Process {

    //-------------------- Atributos -------------------------
    private String name;
    private int oldExecutionTime;
    private int executionTime;
    private int processSize;

    //------------------- Constructores ----------------------
    public Process(String name,  int processSize, int executionTime) {
        this.name = name;
        this.executionTime = executionTime;
        this.oldExecutionTime = this.executionTime;
        this.processSize = processSize;
    }

    //------------------- Getters & Setters --------------------------
    public int getOldExecutionTime() {
        return oldExecutionTime;
    }

    public void setOldExecutionTime(int oldExecutionTime) {
        this.oldExecutionTime = oldExecutionTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public int getProcessSize() {
        return processSize;
    }

    public void setProcessSize(int processSize) {
        this.processSize = processSize;
    }

    // ------------------------ To String --------------------------------
    @Override
    public String toString() {
        return "\n ***Process{" + "name=" + name + ", size=" + processSize +", oldExecutionTime=" + oldExecutionTime + '}'+"\n";
    }
}
