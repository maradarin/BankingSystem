package Interface;

import SystemManagement.ServiceDB;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;


public class Account extends javax.swing.JInternalFrame {
    private static ServiceDB serviceDB;
    private static FileWriter csvWriter;

    public Account(FileWriter csvWriterMain, ServiceDB serviceDBMain) throws SQLException, IOException {
        serviceDB = serviceDBMain;
        csvWriter = csvWriterMain;
        initComponents();
        autoID();
        if(accTypeAccountComboBox.getSelectedItem().toString().toUpperCase().equals("SAVINGS")) {
            toggleVis(true);
        } else {
            toggleVis(false);
        }
        findJointCnpAccountButton.setVisible(false);
        findCnpAccountButton.setVisible(false);
        bankDropdown();
    }

    public void autoID() throws SQLException {
        accountIdLabel.setText(serviceDB.getID("USERS").toString());
    }

    private void toggleVis(boolean status) {
        periodAccountLabel.setVisible(status);
        periodAccountTextField.setVisible(status);

        interestRateAccountLabel.setVisible(status);
        interestRateAccountTextField.setVisible(status);

        jointCnpAccountLabel.setVisible(status);
        jointCnpAccountTextField.setVisible(status);

        isJointAccountLabel.setVisible(status);
        isJointAccountCheckBox.setVisible(status);

    }

    private void bankDropdown() throws SQLException {
        hiddenBankId.setVisible(false);
        ResultSet banks = serviceDB.getBanks();
        while(banks.next()) {
            bankAccountComboBox.addItem(banks.getString(3));
            hiddenBankId.addItem(banks.getString(1));
        }
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        accountLabel = new javax.swing.JLabel();
        accountIdLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        accTypeAccountLabel = new javax.swing.JLabel();
        bankAccountLabel = new javax.swing.JLabel();
        cnpAccountLabel = new javax.swing.JLabel();
        periodAccountLabel = new javax.swing.JLabel();
        cnpAccountTextField = new javax.swing.JTextField();
        periodAccountTextField = new javax.swing.JTextField();
        interestRateAccountLabel = new javax.swing.JLabel();
        interestRateAccountTextField = new javax.swing.JTextField();
        isJointAccountLabel = new javax.swing.JLabel();
        jointCnpAccountLabel = new javax.swing.JLabel();
        jointCnpAccountTextField = new javax.swing.JTextField();
        accTypeAccountComboBox = new javax.swing.JComboBox<>();
        bankAccountComboBox = new javax.swing.JComboBox<>();
        isJointAccountCheckBox = new javax.swing.JCheckBox();
        findCnpAccountButton = new javax.swing.JButton();
        findJointCnpAccountButton = new javax.swing.JButton();
        hiddenBankId = new javax.swing.JComboBox<>();
        addAccountButton = new javax.swing.JButton();
        backAccountButton = new javax.swing.JButton();

        jLabel3.setText("jLabel3");

        accountLabel.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        accountLabel.setText("ACCOUNT");

        accountIdLabel.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        accountIdLabel.setForeground(new java.awt.Color(102, 0, 204));
        accountIdLabel.setText("jLabel1");

        accTypeAccountLabel.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        accTypeAccountLabel.setText("Account Type");
        accTypeAccountLabel.setPreferredSize(new java.awt.Dimension(200, 30));

        bankAccountLabel.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        bankAccountLabel.setText("Bank");
        bankAccountLabel.setPreferredSize(new java.awt.Dimension(200, 30));

        cnpAccountLabel.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        cnpAccountLabel.setText("CNP of Client");
        cnpAccountLabel.setPreferredSize(new java.awt.Dimension(200, 30));

        periodAccountLabel.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        periodAccountLabel.setText("Period");
        periodAccountLabel.setPreferredSize(new java.awt.Dimension(200, 30));

        cnpAccountTextField.setPreferredSize(new java.awt.Dimension(57, 30));
        cnpAccountTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnpAccountTextFieldActionPerformed(evt);
            }
        });

        periodAccountTextField.setPreferredSize(new java.awt.Dimension(57, 30));
        periodAccountTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                periodAccountTextFieldActionPerformed(evt);
            }
        });

        interestRateAccountLabel.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        interestRateAccountLabel.setText("Interest rate");
        interestRateAccountLabel.setPreferredSize(new java.awt.Dimension(200, 30));

        interestRateAccountTextField.setPreferredSize(new java.awt.Dimension(57, 30));
        interestRateAccountTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                interestRateAccountTextFieldActionPerformed(evt);
            }
        });

        isJointAccountLabel.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        isJointAccountLabel.setText("Joint Account");
        isJointAccountLabel.setPreferredSize(new java.awt.Dimension(200, 30));

        jointCnpAccountLabel.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        jointCnpAccountLabel.setText("CNP of Joint Client");
        jointCnpAccountLabel.setPreferredSize(new java.awt.Dimension(200, 30));

        jointCnpAccountTextField.setPreferredSize(new java.awt.Dimension(20, 30));
        jointCnpAccountTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jointCnpAccountTextFieldActionPerformed(evt);
            }
        });

        accTypeAccountComboBox.setPreferredSize(new java.awt.Dimension(57, 30));
        accTypeAccountComboBox.addItem("Current");
        accTypeAccountComboBox.addItem("Savings");
        accTypeAccountComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accTypeAccountComboBoxActionPerformed(evt);
            }
        });

        bankAccountComboBox.setPreferredSize(new java.awt.Dimension(57, 30));
        bankAccountComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bankAccountComboBoxActionPerformed(evt);
            }
        });

        isJointAccountCheckBox.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        isJointAccountCheckBox.setText("Yes");
        isJointAccountCheckBox.setPreferredSize(new java.awt.Dimension(57, 30));
        isJointAccountCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isJointAccountCheckBoxActionPerformed(evt);
            }
        });

        findCnpAccountButton.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        findCnpAccountButton.setText("Find");
        findCnpAccountButton.setPreferredSize(new java.awt.Dimension(57, 30));
        findCnpAccountButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                findCnpAccountButtonStateChanged(evt);
            }
        });
        findCnpAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    findCnpAccountButtonActionPerformed(evt);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        findJointCnpAccountButton.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        findJointCnpAccountButton.setText("Find");
        findJointCnpAccountButton.setPreferredSize(new java.awt.Dimension(57, 30));
        findJointCnpAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    findJointCnpAccountButtonActionPerformed(evt);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jointCnpAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(73, 73, 73)
                                                .addComponent(jointCnpAccountTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(cnpAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(periodAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(interestRateAccountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(isJointAccountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(accTypeAccountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(bankAccountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(73, 73, 73)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(isJointAccountCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(interestRateAccountTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(cnpAccountTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(periodAccountTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(accTypeAccountComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(bankAccountComboBox, 0, 205, Short.MAX_VALUE)))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(findJointCnpAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(findCnpAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(hiddenBankId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(accTypeAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(accTypeAccountComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bankAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bankAccountComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(hiddenBankId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(65, 65, 65)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cnpAccountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cnpAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(findCnpAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(periodAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(periodAccountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(interestRateAccountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(interestRateAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(isJointAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(isJointAccountCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jointCnpAccountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jointCnpAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(findJointCnpAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        addAccountButton.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        addAccountButton.setText("Add");
        addAccountButton.setPreferredSize(new java.awt.Dimension(25, 30));
        addAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    addAccountButtonActionPerformed(evt);
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        backAccountButton.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        backAccountButton.setText("Back");
        backAccountButton.setPreferredSize(new java.awt.Dimension(63, 30));
        backAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backAccountButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(accountLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(accountIdLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                                                .addComponent(addAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(backAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(accountLabel)
                                        .addComponent(accountIdLabel)
                                        .addComponent(addAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void addAccountButtonActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, IOException {
        boolean status = false;
        int index = bankAccountComboBox.getSelectedIndex();
        String id_bank = hiddenBankId.getItemAt(index);

        String cnp = cnpAccountTextField.getText();
        ResultSet id_user = serviceDB.findClientByCnp(cnp);

        String type = accTypeAccountComboBox.getSelectedItem().toString();
        String period = "";
        float interestRate = 0f;
        boolean isJoint = false;
        if(type.toUpperCase().equals("SAVINGS")) {
            period = periodAccountTextField.getText();
            interestRate = Float.parseFloat(interestRateAccountTextField.getText() + "f");
        }

        if(id_user.next()) {
            String id_joint_user = "";
            if(isJointAccountCheckBox.isSelected()) {
                isJoint = true;
                String cnp_joint_user = jointCnpAccountTextField.getText();
                ResultSet id_joint = serviceDB.findClientByCnp(cnp_joint_user);
                if(id_joint.next()) {
                    id_joint_user = id_joint.getString(1);
                } else {
                    JOptionPane.showMessageDialog(this, "Client has to be registered first");
                }
            }
            status = serviceDB.createAccount(id_user.getString(1), id_bank, type, period, interestRate, isJoint, id_joint_user);
            if (status == true) {
                JOptionPane.showMessageDialog(this, "Account created!");
                autoID();
                cnpAccountTextField.setText("");
                bankAccountComboBox.setSelectedIndex(0);
                accTypeAccountComboBox.setSelectedIndex(0);
                cnpAccountTextField.requestFocus();
                serviceDB.writeInAuditLog(csvWriter,"Creare cont", String.valueOf(Thread.currentThread().getId()), Thread.currentThread().getName());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Client has to be registered first");

        }
    }

    private void backAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {
        backAccountButton.setVisible(false);
        this.dispose();
    }

    private void interestRateAccountTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jointCnpAccountTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void bankAccountComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        String bankName = bankAccountComboBox.getSelectedItem().toString();
        int index = bankAccountComboBox.getSelectedIndex();
        System.out.println(hiddenBankId.getItemAt(index));
    }

    private void isJointAccountCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void findCnpAccountButtonActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
        int index = bankAccountComboBox.getSelectedIndex();
        String id_bank = hiddenBankId.getItemAt(index);
        String cnp = cnpAccountTextField.getText();
        boolean found = serviceDB.checkClientAtBank(id_bank,cnp);
        if(found) {
            JOptionPane.showMessageDialog(this, "Client successfully found! Proceed...");
        } else {
            JOptionPane.showMessageDialog(this, "Client has to be registered at the selected bank");
        }

    }

    private void findJointCnpAccountButtonActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
        int index = bankAccountComboBox.getSelectedIndex();
        String id_bank = hiddenBankId.getItemAt(index);
        String cnp_joint_user = jointCnpAccountTextField.getText();

        boolean found = serviceDB.checkClientAtBank(id_bank, cnp_joint_user);
        if(found) {
            JOptionPane.showMessageDialog(this, "Client successfully found! Proceed...");
        } else {
            JOptionPane.showMessageDialog(this, "Client has to be registered at the selected bank");
        }
    }

    private void findCnpAccountButtonStateChanged(javax.swing.event.ChangeEvent evt) {
    }

    private void accTypeAccountComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        if(accTypeAccountComboBox.getSelectedItem().toString().toUpperCase().equals("SAVINGS")) {
            toggleVis(true);
        } else {
            toggleVis(false);
        }

    }

    private void cnpAccountTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void periodAccountTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    // Variables declaration - do not modify
    private javax.swing.JComboBox<String> accTypeAccountComboBox;
    private javax.swing.JLabel accTypeAccountLabel;
    private javax.swing.JLabel accountIdLabel;
    private javax.swing.JLabel accountLabel;
    private javax.swing.JButton addAccountButton;
    private javax.swing.JButton backAccountButton;
    private javax.swing.JComboBox<String> bankAccountComboBox;
    private javax.swing.JLabel bankAccountLabel;
    private javax.swing.JLabel cnpAccountLabel;
    private javax.swing.JTextField cnpAccountTextField;
    private javax.swing.JButton findCnpAccountButton;
    private javax.swing.JButton findJointCnpAccountButton;
    private javax.swing.JComboBox<String> hiddenBankId;
    private javax.swing.JLabel interestRateAccountLabel;
    private javax.swing.JTextField interestRateAccountTextField;
    private javax.swing.JCheckBox isJointAccountCheckBox;
    private javax.swing.JLabel isJointAccountLabel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jointCnpAccountLabel;
    private javax.swing.JTextField jointCnpAccountTextField;
    private javax.swing.JLabel periodAccountLabel;
    private javax.swing.JTextField periodAccountTextField;
}