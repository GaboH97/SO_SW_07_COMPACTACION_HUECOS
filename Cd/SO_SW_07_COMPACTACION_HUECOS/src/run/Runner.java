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

//       pm.addProcess(new logic.Process("p11", 11, 5));
//        pm.addProcess(new logic.Process("p15", 15, 3));
//        pm.addProcess(new logic.Process("p13", 13, 5));
//        pm.addProcess(new logic.Process("p4", 4, 2));
//        pm.addProcess(new logic.Process("p12", 12, 4));
//        pm.addProcess(new logic.Process("p18", 18, 6));
//        pm.addProcess(new logic.Process("p5", 5, 7));
//        pm.addProcess(new logic.Process("p8", 8, 3));
//        pm.processProcesses();
//        System.out.println(pm.getLastDireccion());
//                System.out.println(pm.toString());

        Controller cnontroller = Controller.getInstance(pm);
    }
}
