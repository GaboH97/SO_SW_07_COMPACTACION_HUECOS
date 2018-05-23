package views;

import controller.Actions;
import controller.Controller;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import logic.Partition;
import logic.ProcessManager;
import logic.Process;

/**
 * Clase que representa una ventana de diálogo mediante la cual el usuario puede
 * crear procesos
 *
 * @author Gabriel Amaya y Cesar Cardozo
 */
public class AddProcessDialog extends javax.swing.JDialog {


    //--------------------- Constructores ----------------
    public AddProcessDialog(java.awt.Frame parent, boolean modal, Controller controller) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        //Define los comando de la acción y el escucha de acciones
        createProcessbtn.setActionCommand(Actions.CREATE_PROCESS.name());
        createProcessbtn.addActionListener(controller);
        setLocationRelativeTo(null);
        setVisible(false);
    }

    //------------------- Métodos ------------------------
    /**
     * Limpia los campos a sus valores por defecto
     */
    private void clearFields() {
        this.processNamejtf.setText("");
        this.executionTimejtf.setText("");
        this.processSizejtf.setText("");
    }

    /**
     * Crea un nuevo proceso
     *
     * @return Una instancia de la clase Proceso
     */
    public Process createProcess(ArrayList<Process> processes) throws Exception {
        Process process = null;
        for (Process pro : processes) {
            if (pro.getName().equals(processNamejtf.getText())) {
                throw new Exception(GUIUtils.MSG_PROCESS_ALREADY_EXISTS);
            }
        }
        String timeInput = executionTimejtf.getText();
        String sizeInput = processSizejtf.getText();
        //Verifica que los campos no estén vacíos
        if (!processNamejtf.getText().isEmpty() && !timeInput.isEmpty() && !sizeInput.isEmpty()) {
            //Verifica que la entrada del tiempo de ejecución sea válida
            if (isNumeric(timeInput)&& isNumeric(sizeInput)) {
                //Hace una llamada del método estático de la lógica para crear
                //una nueva instancia de la clase Proceso
                process = ProcessManager.createProcess(processNamejtf.getText(),  Integer.parseInt(processSizejtf.getText()), Integer.parseInt(executionTimejtf.getText()));
            } else {
                JOptionPane.showMessageDialog(this,
                        GUIUtils.MSG_INVALID_TIME,
                        GUIUtils.APP_TITLE,
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return process;
    }

    /**
     * Verifica si una cadena de texto corresponde a un número decimal positivo
     *
     * @param str Una cadena de caracteres
     * @return true si la cadena de caracteres encaja con la expresión regular
     * para un número decimal positivo
     */
    public boolean isNumeric(String str) {
        return str != null && (str.matches("[+]?\\d*(\\.\\d+)?") && str.equals("") == false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        processNamejtf = new javax.swing.JTextField();
        executionTimejtf = new javax.swing.JTextField();
        createProcessbtn = new javax.swing.JButton();
        cancelbtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        processSizejtf = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Agregar Proceso");

        jLabel2.setText("Nombre del Proceso");

        jLabel3.setText("Tiempo de Ejecución");

        executionTimejtf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (executionTimejtf.getText().length() >= 6
                    || !Character.isDigit(c)) // limit textfield to 3 characters
                e.consume();
            }
        });

        createProcessbtn.setText("Crear");

        cancelbtn.setText("Cancelar");
        cancelbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbtnActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Tamaño");

        processSizejtf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (processSizejtf.getText().length() >= 6
                    ||!Character.isDigit(c)) // limit textfield to 3 characters
                e.consume();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(processNamejtf, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(executionTimejtf, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(processSizejtf))
                        .addGap(0, 34, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(createProcessbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(cancelbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(processNamejtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(executionTimejtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(processSizejtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createProcessbtn)
                    .addComponent(cancelbtn))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Limpia los campos y desaparece la ventana
     *
     * @param evt Evento generado
     */
    private void cancelbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtnActionPerformed
        clearFields();
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_cancelbtnActionPerformed

    /**
     * Limpia los campos y desaparece la ventana
     */
    public void close() {
        clearFields();
        setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelbtn;
    private javax.swing.JButton createProcessbtn;
    private javax.swing.JTextField executionTimejtf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField processNamejtf;
    private javax.swing.JTextField processSizejtf;
    // End of variables declaration//GEN-END:variables

    
    
    public void setValues(Process process, ArrayList<Partition> partitions){
        processNamejtf.setText(process.getName());
        processSizejtf.setText(process.getProcessSize()+"");
        executionTimejtf.setText(process.getExecutionTime()+"");
    }

    public JButton getCreateProcessbtn() {
        return createProcessbtn;
    }

    public JTextField getExecutionTimejtf() {
        return executionTimejtf;
    }

    public JTextField getProcessNamejtf() {
        return processNamejtf;
    }

    public JTextField getProcessSizejtf() {
        return processSizejtf;
    }

    public Process editProccess(ArrayList<Partition> partitionsList) throws Exception {
        Process process = null;
        String timeInput = executionTimejtf.getText();
        String sizeInput = processSizejtf.getText();
        //Verifica que los campos no estén vacíos
        if (!processNamejtf.getText().isEmpty() && !timeInput.isEmpty() && !sizeInput.isEmpty()) {
            //Verifica que la entrada del tiempo de ejecución sea válida
          
                //Hace una llamada del método estático de la lógica para crear
                //una nueva instancia de la clase Proceso
                process = ProcessManager.createProcess(processNamejtf.getText(), Integer.parseInt(executionTimejtf.getText()),  Integer.parseInt(processSizejtf.getText()));
        
        } else {
            throw new Exception (GUIUtils.MSG_EMPTY_FIELDS);         
        }
        return process;
    }

    
}
