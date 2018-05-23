package run;

import logic.ProcessManager;
import controller.Controller;
import logic.Partition;

/**
 * Clase que ejecuta el programa
 *
 * @author Cesar Cardozo y Gabriel Amaya
 */
public class Runner {

    public static void main(String[] args) {

        ProcessManager pm = new ProcessManager();

       pm.addProcess(new logic.Process("p11", 11, 5));
        pm.addProcess(new logic.Process("p15", 15, 7));
        pm.addProcess(new logic.Process("p18", 18, 8));
        pm.addProcess(new logic.Process("p6", 6, 3));
        pm.addProcess(new logic.Process("p9", 9, 4));
        pm.addProcess(new logic.Process("p20", 20, 2));
        pm.addProcess(new logic.Process("p13", 13, 3));
//                System.out.println(pm.toString());*/

        Controller controller = Controller.getInstance(pm);
    }
}
