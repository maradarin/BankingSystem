package Interface;

import SystemManagement.ServiceDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;


public class CustomerReport extends javax.swing.JInternalFrame {
    private static ServiceDB serviceDB;
    private static FileWriter csvWriter;

    public CustomerReport(FileWriter csvWriterMain, ServiceDB serviceDBMain) throws SQLException, IOException {
        serviceDB = serviceDBMain;
        csvWriter = csvWriterMain;
        initComponents();
    }

    private void updateTable(ResultSet id_account) throws SQLException {
        ResultSet results = serviceDB.showAccStatements(id_account.getString(1));
        ResultSetMetaData RSMD = results.getMetaData();
        int CC = RSMD.getColumnCount();
        System.out.println(CC);
        DefaultTableModel DFT = (DefaultTableModel) accReportTable.getModel();
        DFT.setRowCount(0);

        while(results.next()) {
            Vector line = new Vector();

            //System.out.println(results.getString("ID_ACCOUNT_STATEMENT") + " " + results.getString("OPERATION_DATE") + " " + results.getString("OPERATION") + " " + results.getString("ACCOUNT_TYPE") + " " + results.getString("AMOUNT"));
            line.add(Integer.parseInt(results.getString("ID_ACCOUNT_STATEMENT")));
            line.add(results.getString("OPERATION_DATE"));
            line.add(results.getString("OPERATION"));
            line.add(results.getString("ACCOUNT_TYPE"));
            line.add(results.getString("AMOUNT"));

            DFT.addRow(line);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        accStatementsLabel = new javax.swing.JLabel();
        enterCnpLabel = new javax.swing.JLabel();
        selCurrAccLabel = new javax.swing.JLabel();
        selCurrAccValueComboBox = new javax.swing.JComboBox<>();
        enterCnpValueTextField = new javax.swing.JTextField();
        findByCnpButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        accReportTable = new javax.swing.JTable();
        backAccReportButton = new javax.swing.JButton();

        accStatementsLabel.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        accStatementsLabel.setText("Account Statements");

        enterCnpLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        enterCnpLabel.setText("Enter CNP");

        selCurrAccLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        selCurrAccLabel.setText("Select Current Account");

        selCurrAccValueComboBox.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        selCurrAccValueComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    selCurrAccValueComboBoxActionPerformed(evt);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


        enterCnpValueTextField.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        enterCnpValueTextField.setPreferredSize(new java.awt.Dimension(85, 30));
        enterCnpValueTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterCnpValueTextFieldActionPerformed(evt);
            }
        });

        findByCnpButton.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        findByCnpButton.setText("Find");
        findByCnpButton.setPreferredSize(new java.awt.Dimension(77, 27));
        findByCnpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    findByCnpButtonActionPerformed(evt);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        backAccReportButton.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        backAccReportButton.setText("BACK");
        backAccReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backAccReportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(selCurrAccValueComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(enterCnpValueTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(accStatementsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(0, 16, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(enterCnpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(selCurrAccLabel)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(90, 90, 90)
                                                .addComponent(findByCnpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(91, 91, 91)
                                                .addComponent(backAccReportButton)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(accStatementsLabel)
                                .addGap(38, 38, 38)
                                .addComponent(enterCnpLabel)
                                .addGap(15, 15, 15)
                                .addComponent(enterCnpValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(findByCnpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(selCurrAccLabel)
                                .addGap(15, 15, 15)
                                .addComponent(selCurrAccValueComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(backAccReportButton)
                                .addContainerGap(103, Short.MAX_VALUE))
        );

        accReportTable.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        accReportTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                },
                new String [] {
                        "ID", "Date", "Operation", "Type", "Amount"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        accReportTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    accReportTableMouseClicked(evt);
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        jScrollPane2.setViewportView(accReportTable);
        if (accReportTable.getColumnModel().getColumnCount() > 0) {
            accReportTable.getColumnModel().getColumn(0).setPreferredWidth(15);
            accReportTable.getColumnModel().getColumn(1).setPreferredWidth(35);
            accReportTable.getColumnModel().getColumn(2).setPreferredWidth(15);
            accReportTable.getColumnModel().getColumn(3).setPreferredWidth(15);
            accReportTable.getColumnModel().getColumn(4).setPreferredWidth(35);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26))))
        );

        pack();
    }

    private void backAccReportButtonActionPerformed(java.awt.event.ActionEvent evt) {
        backAccReportButton.setVisible(false);
        this.dispose();
    }

    private void selCurrAccValueComboBoxActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
        if(selCurrAccValueComboBox.getItemCount() > 0) {
            String iban = selCurrAccValueComboBox.getSelectedItem().toString();
            ResultSet id_account = serviceDB.findAccountByIBAN(iban);
            id_account.next();
            updateTable(id_account);
        }
    }

    private void enterCnpValueTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void findByCnpButtonActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
        selCurrAccValueComboBox.removeAllItems();
        String cnp = enterCnpValueTextField.getText();
        ResultSet id_user= serviceDB.findClientByCnp(cnp);
        if(id_user.next()) {

            ResultSet accounts = serviceDB.findCurrentAccountsByID(id_user.getString(1));
            while(accounts.next()) {
                //System.out.println(accounts.getString(1));
                selCurrAccValueComboBox.addItem(accounts.getString(1));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Client doesn't exit");
            selCurrAccValueComboBox.removeAllItems();
        }
    }

    private void accReportTableMouseClicked(java.awt.event.MouseEvent evt) throws SQLException, IOException {
        DefaultTableModel DFT = (DefaultTableModel) accReportTable.getModel();
        int selectedIndex = accReportTable.getSelectedRow();
        Integer id_account_statement = Integer.parseInt(DFT.getValueAt(selectedIndex,0).toString());
        int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to delete the record?", "Warning", JOptionPane.YES_NO_OPTION);

        if(dialogResult == JOptionPane.YES_OPTION) {
            serviceDB.deleteAccStatement(id_account_statement.toString());
            serviceDB.writeInAuditLog(csvWriter, "Stergere din extrasul de cont", String.valueOf(Thread.currentThread().getId()), Thread.currentThread().getName());
        }
        if(selCurrAccValueComboBox.getItemCount() > 0) {
            String iban = selCurrAccValueComboBox.getSelectedItem().toString();
            ResultSet id_account = serviceDB.findAccountByIBAN(iban);
            id_account.next();
            updateTable(id_account);
        }
    }

    private javax.swing.JTable accReportTable;
    private javax.swing.JLabel accStatementsLabel;
    private javax.swing.JLabel enterCnpLabel;
    private javax.swing.JTextField enterCnpValueTextField;
    private javax.swing.JButton findByCnpButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel selCurrAccLabel;
    private javax.swing.JComboBox<String> selCurrAccValueComboBox;
    private javax.swing.JButton backAccReportButton;
}
