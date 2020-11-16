/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasku;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author indmind
 */
public class FormTugas extends javax.swing.JFrame {

    private final DB db = new DB();

    /**
     * Creates new form FormTugas
     */
    public FormTugas() {
        initComponents();

        // Menyembunyikan kolom id
        hideTableColumn(todoTable, "id");
        hideTableColumn(doingTable, "id");
        hideTableColumn(doneTable, "id");

        // Setup tables
        setupTables();

        UpdateTables();
    }

    final void UpdateTables() {
        try {
            Connection conn = db.getConnection();
            Statement stm = conn.createStatement();

            ResultSet result = stm.executeQuery("select * from tugas");

            DefaultTableModel todoModel = (DefaultTableModel) todoTable.getModel();
            DefaultTableModel doingModel = (DefaultTableModel) doingTable.getModel();
            DefaultTableModel doneModel = (DefaultTableModel) doneTable.getModel();

            // clear semua data
            todoModel.setRowCount(0);
            doingModel.setRowCount(0);
            doneModel.setRowCount(0);

            while (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String subject = result.getString("subject");
                String status = result.getString("status");
                String deadline = result.getString("deadline");

                Object[] data = new Object[]{id, name, subject, status, deadline};

                switch (status) {
                    case "To Do":
                        todoModel.addRow(data);
                        break;
                    case "Doing":
                        doingModel.addRow(data);
                        break;
                    default:
                        doneModel.addRow(data);
                        break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Terjadi kesalahan sql" + ex);
        }
    }

    final void setupTables() {
        TableColumn todoStatus = todoTable.getColumnModel().getColumn(3);
        TableColumn doingStatus = doingTable.getColumnModel().getColumn(3);
        TableColumn doneStatus = doneTable.getColumnModel().getColumn(3);

        JComboBox comboBox = new JComboBox();

        comboBox.addItem("To Do");
        comboBox.addItem("Doing");
        comboBox.addItem("Done");

        todoStatus.setCellEditor(new DefaultCellEditor(comboBox));
        doingStatus.setCellEditor(new DefaultCellEditor(comboBox));
        doneStatus.setCellEditor(new DefaultCellEditor(comboBox));

        setupTableChangeListener(todoTable);
        setupTableChangeListener(doingTable);
        setupTableChangeListener(doneTable);

    }

    final void setupTableChangeListener(JTable table) {
        table.getModel().addTableModelListener((e) -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                try {
                    int row = table.getSelectedRow();
                    String id = table.getValueAt(row, 0).toString();
                    String name = table.getValueAt(row, 1).toString();
                    String subject = table.getValueAt(row, 2).toString();
                    String status = table.getValueAt(row, 3).toString();
                    String deadline = table.getValueAt(row, 4).toString();

                    String sql = "update tugas set name='%s', subject='%s', status='%s', deadline='%s' where id='%s'";

                    sql = String.format(
                            sql,
                            name,
                            subject,
                            status,
                            deadline,
                            id
                    );

                    Connection conn = db.getConnection();
                    PreparedStatement pst = conn.prepareStatement(sql);

                    pst.execute();

                } catch (SQLException ex) {
                    Logger.getLogger(FormTugas.class.getName()).log(Level.SEVERE, null, ex);
                }

                UpdateTables();
            }
        });
    }

    final void hideTableColumn(JTable table, String column) {
        table.getColumn(column).setMinWidth(0); // Must be set before maxWidth!!
        table.getColumn(column).setMaxWidth(0);
        table.getColumn(column).setWidth(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        previewButon = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        todoTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        doingTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        doneTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        newButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel4.setText("Tugasku");

        previewButon.setText("Preview");
        previewButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewButonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(586, 586, 586)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(previewButon)
                .addGap(26, 26, 26))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(previewButon))
                .addGap(18, 18, 18))
        );

        todoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nama", "Pelajaran", "Status", "Deadline"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(todoTable);
        if (todoTable.getColumnModel().getColumnCount() > 0) {
            todoTable.getColumnModel().getColumn(0).setResizable(false);
            todoTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        }

        doingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nama", "Pelajaran", "Status", "Deadline"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(doingTable);
        if (doingTable.getColumnModel().getColumnCount() > 0) {
            doingTable.getColumnModel().getColumn(0).setResizable(false);
            doingTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        }

        doneTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nama", "Pelajaran", "Status", "Deadline"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(doneTable);
        if (doneTable.getColumnModel().getColumnCount() > 0) {
            doneTable.getColumnModel().getColumn(0).setResizable(false);
            doneTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("To Do");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Doing");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Done");

        newButton.setText("New");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(newButton)))
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(clearButton))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(newButton)
                    .addComponent(clearButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contentPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        try {
            String sql = "insert into tugas (name, subject, deadline) values ('', '', CURRENT_TIMESTAMP)";

            Connection conn = db.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.execute();

            UpdateTables();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal memasukan data" + ex);
        }
    }//GEN-LAST:event_newButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        try {
            String sql = "delete from tugas where status='Done'";

            Connection conn = db.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.execute();

            UpdateTables();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal memasukan data" + ex);
        }
    }//GEN-LAST:event_clearButtonActionPerformed

    private void previewButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewButonActionPerformed
        FormPreview preview = new FormPreview();
        
        preview.setTitle("Preview");
        preview.setVisible(true);
    }//GEN-LAST:event_previewButonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JTable doingTable;
    private javax.swing.JTable doneTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton newButton;
    private javax.swing.JButton previewButon;
    private javax.swing.JTable todoTable;
    // End of variables declaration//GEN-END:variables
}
