package views;

import controller.Actions;
import controller.Controller;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import logic.Process;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logic.Compactation;
import logic.Condensation;
import logic.Partition;
import static views.GUIUtils.APP_TITLE;

/**
 * Esta clase representa la GUI principal sobre la cual el usuario realizará la
 * mayoría de las operaciones, podrá visualizar los procesos creados, iniciar la
 * ejecución de los procesos y visualizar las tablas de Procesos E/S, Estados y
 * transiciones
 *
 * @author Gabriel Huertas y Cesar Cardozo
 */
public class MainWindow extends javax.swing.JFrame implements ActionListener {

    //---------------------- Attributes -------------------------------
    //SW06
    private DefaultTableModel processesTableModel;
    private DefaultTableModel IOProcessesTableModel;
    private DefaultTableModel processesAllocationTableModel;
    private DefaultTableModel compactationsTableModel;
    private DefaultTableModel orderFinishingPartitionsTableModel;
    private DefaultTableModel freeHolesTableModel;

    private Controller controller;

    //---------------------- Constructores -------------------------
    /**
     * Crea una nueva instancia de MainWindow
     *
     * @param controller referencia al controlador que manejará los eventos
     */
    public MainWindow(Controller controller) {

        this.controller = controller;

        processesTableModel = new DefaultTableModel(GUIUtils.ADD_PROCESSES_TABLE_HEADERS, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //sw 06
        IOProcessesTableModel = new DefaultTableModel(GUIUtils.IO_PROCESSES_TABLE_HEADERS, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        processesAllocationTableModel = new DefaultTableModel(GUIUtils.ALLOCATION_TABLE_HEADERS, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        compactationsTableModel = new DefaultTableModel(GUIUtils.FREEHOLES_TABLE_HEADER, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        orderFinishingPartitionsTableModel = new DefaultTableModel(GUIUtils.ORDER_FINISHING_PARTITIONS_TABLE_HEADERS, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        freeHolesTableModel = new DefaultTableModel(GUIUtils.FREEHOLES_TABLE_HEADER, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.setTitle(APP_TITLE);
        //this.setUndecorated(true);
        initComponents();
        orderFinishingAndCompactationsAndFreeHolesPanel.setVisible(false);
        setActions(controller);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        showOptions(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //-------------------------- Métodos ---------------------------

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        partitionsPopupMenu = new javax.swing.JPopupMenu();
        editPartitionjmi = new javax.swing.JMenuItem();
        deletePartitionjmi = new javax.swing.JMenuItem();
        processesPopupMenu = new javax.swing.JPopupMenu();
        editProcessjmi = new javax.swing.JMenuItem();
        deleteProcessjmi = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        createProcessbtn = new javax.swing.JButton();
        exitbtn = new javax.swing.JButton();
        startbtn = new javax.swing.JButton();
        processesbtn = new javax.swing.JButton();
        partitionsReportbtn1 = new javax.swing.JButton();
        partitionsReportbtn2 = new javax.swing.JButton();
        menulbl = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        processesPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        processesTable = new javax.swing.JTable();
        tableHeaderProcesseslbl = new javax.swing.JLabel();
        orderFinishingAndCompactationsAndFreeHolesPanel = new javax.swing.JPanel();
        orderFinishingTablelbl = new javax.swing.JLabel();
        orderFinishingTablelbl1 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        freeHolesTable = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        compactationsTable = new javax.swing.JTable();
        orderFinishingTablelbl4 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        orderFinishingTable1 = new javax.swing.JTable();
        IOWhoPassesForPanel = new javax.swing.JPanel();
        orderFinishingTablelbl2 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        processesAllocationTable = new javax.swing.JTable();
        orderFinishingTablelbl3 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        IOProcessesTable = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        defineQuantumjmi = new javax.swing.JMenuItem();
        defineMemorySizejmi = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        checkManualjmi = new javax.swing.JMenuItem();

        editPartitionjmi.setText("Editar");
        partitionsPopupMenu.add(editPartitionjmi);

        deletePartitionjmi.setText("Eliminar");
        partitionsPopupMenu.add(deletePartitionjmi);

        editProcessjmi.setText("Editar"
        );
        processesPopupMenu.add(editProcessjmi);

        deleteProcessjmi.setText("Eliminar"
        );
        processesPopupMenu.add(deleteProcessjmi);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(209, 700));

        createProcessbtn.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        createProcessbtn.setText("Crear Proceso");

        exitbtn.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        exitbtn.setText("Salir");
        exitbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitbtnActionPerformed(evt);
            }
        });

        startbtn.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        startbtn.setText("Iniciar");

        processesbtn.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        processesbtn.setText("Procesos");

        partitionsReportbtn1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        partitionsReportbtn1.setText("Reporte Particiones 1");

        partitionsReportbtn2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        partitionsReportbtn2.setText("Reporte Particiones 2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(createProcessbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(processesbtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(partitionsReportbtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(partitionsReportbtn2, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(createProcessbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(processesbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(partitionsReportbtn1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(partitionsReportbtn2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(startbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exitbtn)
                .addContainerGap())
        );

        menulbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        menulbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menulbl.setText("Menú");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(menulbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(menulbl)
                .addGap(5, 5, 5)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1630, Short.MAX_VALUE))
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        processesTable.setModel(processesTableModel);
        processesTable.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(processesTable);

        tableHeaderProcesseslbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tableHeaderProcesseslbl.setText("Procesos");

        javax.swing.GroupLayout processesPanelLayout = new javax.swing.GroupLayout(processesPanel);
        processesPanel.setLayout(processesPanelLayout);
        processesPanelLayout.setHorizontalGroup(
            processesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(processesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(processesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableHeaderProcesseslbl, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                    .addGroup(processesPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        processesPanelLayout.setVerticalGroup(
            processesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, processesPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(tableHeaderProcesseslbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        orderFinishingTablelbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        orderFinishingTablelbl.setText("Compactaciones");

        orderFinishingTablelbl1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        orderFinishingTablelbl1.setText("Huecos Libres");

        freeHolesTable.setModel(freeHolesTableModel);
        jScrollPane11.setViewportView(freeHolesTable);

        compactationsTable.setModel(compactationsTableModel);
        jScrollPane9.setViewportView(compactationsTable);

        orderFinishingTablelbl4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        orderFinishingTablelbl4.setText("Orden Terminación Particiones");

        orderFinishingTable1.setModel(orderFinishingPartitionsTableModel);
        jScrollPane12.setViewportView(orderFinishingTable1);

        javax.swing.GroupLayout orderFinishingAndCompactationsAndFreeHolesPanelLayout = new javax.swing.GroupLayout(orderFinishingAndCompactationsAndFreeHolesPanel);
        orderFinishingAndCompactationsAndFreeHolesPanel.setLayout(orderFinishingAndCompactationsAndFreeHolesPanelLayout);
        orderFinishingAndCompactationsAndFreeHolesPanelLayout.setHorizontalGroup(
            orderFinishingAndCompactationsAndFreeHolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderFinishingAndCompactationsAndFreeHolesPanelLayout.createSequentialGroup()
                .addGroup(orderFinishingAndCompactationsAndFreeHolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderFinishingAndCompactationsAndFreeHolesPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(orderFinishingAndCompactationsAndFreeHolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(orderFinishingAndCompactationsAndFreeHolesPanelLayout.createSequentialGroup()
                                .addComponent(orderFinishingTablelbl1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane11)))
                    .addGroup(orderFinishingAndCompactationsAndFreeHolesPanelLayout.createSequentialGroup()
                        .addGroup(orderFinishingAndCompactationsAndFreeHolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(orderFinishingAndCompactationsAndFreeHolesPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(orderFinishingTablelbl4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orderFinishingAndCompactationsAndFreeHolesPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(orderFinishingAndCompactationsAndFreeHolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(orderFinishingTablelbl, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))))
                .addContainerGap())
        );
        orderFinishingAndCompactationsAndFreeHolesPanelLayout.setVerticalGroup(
            orderFinishingAndCompactationsAndFreeHolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderFinishingAndCompactationsAndFreeHolesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orderFinishingAndCompactationsAndFreeHolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderFinishingTablelbl)
                    .addComponent(orderFinishingTablelbl4))
                .addGap(18, 18, 18)
                .addGroup(orderFinishingAndCompactationsAndFreeHolesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addComponent(orderFinishingTablelbl1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        orderFinishingTablelbl2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        orderFinishingTablelbl2.setText("Asignación Particiones a Procesos");

        processesAllocationTable.setModel(processesAllocationTableModel);
        jScrollPane8.setViewportView(processesAllocationTable);

        orderFinishingTablelbl3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        orderFinishingTablelbl3.setText("Procesos E/S");

        IOProcessesTable.setModel(IOProcessesTableModel);
        jScrollPane10.setViewportView(IOProcessesTable);

        javax.swing.GroupLayout IOWhoPassesForPanelLayout = new javax.swing.GroupLayout(IOWhoPassesForPanel);
        IOWhoPassesForPanel.setLayout(IOWhoPassesForPanelLayout);
        IOWhoPassesForPanelLayout.setHorizontalGroup(
            IOWhoPassesForPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IOWhoPassesForPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(IOWhoPassesForPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                    .addGroup(IOWhoPassesForPanelLayout.createSequentialGroup()
                        .addGroup(IOWhoPassesForPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(orderFinishingTablelbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(orderFinishingTablelbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(IOWhoPassesForPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(IOWhoPassesForPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        IOWhoPassesForPanelLayout.setVerticalGroup(
            IOWhoPassesForPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IOWhoPassesForPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(orderFinishingTablelbl3)
                .addGap(206, 206, 206)
                .addComponent(orderFinishingTablelbl2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(IOWhoPassesForPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(IOWhoPassesForPanelLayout.createSequentialGroup()
                    .addGap(61, 61, 61)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(345, Short.MAX_VALUE)))
        );

        jMenu1.setText("Configuración");

        defineQuantumjmi.setText("Definir Quantum");
        jMenu1.add(defineQuantumjmi);

        defineMemorySizejmi.setText("Definir Tamaño Memoria");
        jMenu1.add(defineMemorySizejmi);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");

        checkManualjmi.setText("Ver Manual");
        checkManualjmi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkManualjmiActionPerformed(evt);
            }
        });
        jMenu2.add(checkManualjmi);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(processesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(orderFinishingAndCompactationsAndFreeHolesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(IOWhoPassesForPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(processesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(orderFinishingAndCompactationsAndFreeHolesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(IOWhoPassesForPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 33, Short.MAX_VALUE)))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Define el comando de acción para cada botón utilizado y agrega como
     * escuchador de eventos a la referencia de la clase Controller
     *
     * @param controller Referencia al manejador de eventos
     */
    private void setActions(Controller controller) {
        createProcessbtn.setActionCommand(Actions.OPEN_CREATE_PROCESS.name());
        createProcessbtn.addActionListener(controller);
        startbtn.setActionCommand(Actions.START.name());
        startbtn.addActionListener(controller);
        defineQuantumjmi.setActionCommand(Actions.OPEN_DEFINE_QUANTUM.name());
        defineQuantumjmi.addActionListener(controller);
        defineMemorySizejmi.setActionCommand(Actions.OPEN_DEFINE_MEMORY_SIZE.name());
        defineMemorySizejmi.addActionListener(controller);
        processesbtn.setActionCommand(Actions.SHOW_PARTITIONS_AND_PROCESSES.name());
        processesbtn.addActionListener(controller);
        //     generalReportbtn.setActionCommand(Actions.SHOW_GENERAL_REPORT.name());
        //   generalReportbtn.addActionListener(controller);
        partitionsReportbtn1.setActionCommand(Actions.SHOW_PARTITIONS_REPORT_1.name());
        partitionsReportbtn1.addActionListener(controller);
        partitionsReportbtn2.setActionCommand(Actions.SHOW_PARTITIONS_REPORT_2.name());
        partitionsReportbtn2.addActionListener(controller);

        //---------------OPCIONES JPOPUPMENUS-------------------------
        editPartitionjmi.addActionListener(this);
        deletePartitionjmi.addActionListener(this);
        editProcessjmi.addActionListener(this);
        deleteProcessjmi.addActionListener(this);

    }

    /**
     * Elimina todos los valores de la tabla sin sus encabezados
     */
    private void clearTable() {
        processesTableModel.getDataVector().removeAllElements();
    }

    /**
     * Agrega un nuevo proceso a la tabla
     *
     * @param p El proceso a agregar a la tabla
     */
    public void addProcess(Process p) {
        //Define el número de filas como el número de filas actual + 1, esto para
        //Dar espacio para agregar el proceso
        processesTableModel.setRowCount(processesTableModel.getRowCount() + 1);
        //Agrega los datos del proceso en las columnas correspondientes
        processesTableModel.setValueAt(p.getName(), processesTableModel.getRowCount() - 1, 0);
        processesTableModel.setValueAt(p.getOldExecutionTime(), processesTableModel.getRowCount() - 1, 1);
        processesTableModel.setValueAt(p.getProcessSize(), processesTableModel.getRowCount() - 1, 2);
        this.repaint();
    }

    private void checkManualjmiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkManualjmiActionPerformed
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("./Manual_de_Usuario.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No se pudo abrir el PDF desde la aplicación. Lo puede encontrar en la" + ""
                        + " \n carpeta SO_SW_07_COMPACTACION_HUECOS ",
                        APP_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_checkManualjmiActionPerformed

    private void exitbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitbtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitbtnActionPerformed

    /**
     * Muestra las opciones en el menú de acuerdo a
     *
     * @param isInitial Bandera que indica si la ventana se muestra con opciones
     * iniciales o con las opciones disponibles después de iniciar la ejecución
     * de los procesos
     */
    public void showOptions(boolean isInitial) {
        createProcessbtn.setVisible(isInitial);
        startbtn.setVisible(isInitial);
        processesbtn.setVisible(!isInitial);
        partitionsReportbtn1.setVisible(!isInitial);
        partitionsReportbtn2.setVisible(!isInitial);
        processesTable.setComponentPopupMenu((isInitial) ? processesPopupMenu : null);
        IOWhoPassesForPanel.setVisible(!isInitial);
        orderFinishingAndCompactationsAndFreeHolesPanel.setVisible(!isInitial);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable IOProcessesTable;
    private javax.swing.JPanel IOWhoPassesForPanel;
    private javax.swing.JMenuItem checkManualjmi;
    private javax.swing.JTable compactationsTable;
    private javax.swing.JButton createProcessbtn;
    private javax.swing.JMenuItem defineMemorySizejmi;
    private javax.swing.JMenuItem defineQuantumjmi;
    private javax.swing.JMenuItem deletePartitionjmi;
    private javax.swing.JMenuItem deleteProcessjmi;
    private javax.swing.JMenuItem editPartitionjmi;
    private javax.swing.JMenuItem editProcessjmi;
    private javax.swing.JButton exitbtn;
    private javax.swing.JTable freeHolesTable;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel menulbl;
    private javax.swing.JPanel orderFinishingAndCompactationsAndFreeHolesPanel;
    private javax.swing.JTable orderFinishingTable1;
    private javax.swing.JLabel orderFinishingTablelbl;
    private javax.swing.JLabel orderFinishingTablelbl1;
    private javax.swing.JLabel orderFinishingTablelbl2;
    private javax.swing.JLabel orderFinishingTablelbl3;
    private javax.swing.JLabel orderFinishingTablelbl4;
    private javax.swing.JPopupMenu partitionsPopupMenu;
    private javax.swing.JButton partitionsReportbtn1;
    private javax.swing.JButton partitionsReportbtn2;
    private javax.swing.JTable processesAllocationTable;
    private javax.swing.JPanel processesPanel;
    private javax.swing.JPopupMenu processesPopupMenu;
    private javax.swing.JTable processesTable;
    private javax.swing.JButton processesbtn;
    private javax.swing.JButton startbtn;
    private javax.swing.JLabel tableHeaderProcesseslbl;
    // End of variables declaration//GEN-END:variables

    /**
     *
     * @param partitionsList
     * @param input_ProcessList
     */
    public void showProcesses(ArrayList<Process> input_ProcessList) {
        clearTable();
        processesPanel.setVisible(true);
        orderFinishingAndCompactationsAndFreeHolesPanel.setVisible(false);
        IOWhoPassesForPanel.setVisible(false);
        for (Process process : input_ProcessList) {
            addProcess(process);
        }
        this.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        if (menuItem == editProcessjmi) {
            int row = processesTable.getSelectedRow();
            String value = processesTable.getModel().getValueAt(row, 0).toString();
            controller.editProcess(value);
        } else if (menuItem == deleteProcessjmi) {
            int row = processesTable.getSelectedRow();
            String value = processesTable.getModel().getValueAt(row, 0).toString();
            controller.deleteProcess(value);
        }
//        System.out.println("salio");  
    }

    public void showProcessesAllocationAndIOProcesses(ArrayList<Partition> partitions, ArrayList<Process> inputProcesses, ArrayList<Process> outputProcesses) {
        IOWhoPassesForPanel.setVisible(true);
        processesPanel.setVisible(false);
        orderFinishingAndCompactationsAndFreeHolesPanel.setVisible(false);
        processesAllocationTableModel.getDataVector().removeAllElements();
        IOProcessesTableModel.getDataVector().removeAllElements();
        for (Partition partition : partitions) {
            processesAllocationTableModel.addRow(partition.getDataVectorForTableOfProcessesThatPassed());
        }
        IOProcessesTableModel.setRowCount(inputProcesses.size());
        for (int i = 0; i < inputProcesses.size(); i++) {
            IOProcessesTableModel.setValueAt(inputProcesses.get(i).getName(), i, 0);
        }

        for (int i = 0; i < outputProcesses.size(); i++) {
            IOProcessesTableModel.setValueAt(outputProcesses.get(i).getName(), i, 1);
        }
    }

    public void showOrderFinishingPartitionsAndCompactationsAndFreeHoles(ArrayList<Partition> output_PartitionsList, ArrayList<Compactation> compactations, ArrayList<Partition> freeHolesList) {
        IOWhoPassesForPanel.setVisible(false);
        processesPanel.setVisible(false);
        orderFinishingAndCompactationsAndFreeHolesPanel.setVisible(true);
        compactationsTableModel.getDataVector().removeAllElements();
        orderFinishingPartitionsTableModel.getDataVector().removeAllElements();
        freeHolesTableModel.getDataVector().removeAllElements();
        for (Compactation compactation : compactations) {
            compactationsTableModel.addRow(compactation.getDataVector());
        }
        for (Partition partition : output_PartitionsList) {
            orderFinishingPartitionsTableModel.addRow(new Object[]{partition.getPartitionName()});
        }
        for (Partition partition : freeHolesList) {
            freeHolesTableModel.addRow(new Object[]{partition.getDataVectorForFreeHoles()});
        }

    }
}
