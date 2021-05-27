package Interface;

import SystemManagement.Service;
import SystemManagement.ServiceDB;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Withdraw extends javax.swing.JInternalFrame {
    private static ServiceDB serviceDB;
    private static FileWriter csvWriter;

    public Withdraw(FileWriter csvWriterMain, ServiceDB serviceDBMain) throws SQLException, IOException {
        csvWriter = csvWriterMain;
        serviceDB = serviceDBMain;
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        accountWithdrawLabel = new javax.swing.JLabel();
        accountIdWithdrawLabel = new javax.swing.JLabel();
        enterIBANLabel = new javax.swing.JLabel();
        enterIBANTextField = new javax.swing.JTextField();
        findAccWithdrawButton = new javax.swing.JButton();
        cnpWithdrawLabel = new javax.swing.JLabel();
        firstNameWithdrawLabel = new javax.swing.JLabel();
        surnameWithdrawLabel = new javax.swing.JLabel();
        cnpValueWithdrawLabel = new javax.swing.JLabel();
        firstNameValueWithdrawLabel = new javax.swing.JLabel();
        surnameValueWithdrawLabel = new javax.swing.JLabel();
        balanceWithdrawLabel = new javax.swing.JLabel();
        balanceValueWithdrawLabel = new javax.swing.JLabel();
        amountWithdrawLabel = new javax.swing.JLabel();
        amountWithdrawTextField = new javax.swing.JTextField();
        okWithdrawButton = new javax.swing.JButton();
        cancelWithdrawButton = new javax.swing.JButton();

        accountWithdrawLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        accountWithdrawLabel.setText("ACCOUNT");

        accountIdWithdrawLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        accountIdWithdrawLabel.setForeground(new java.awt.Color(102, 0, 153));
        accountIdWithdrawLabel.setPreferredSize(new java.awt.Dimension(65, 23));

        enterIBANLabel.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        enterIBANLabel.setText("Enter your IBAN");

        enterIBANTextField.setPreferredSize(new java.awt.Dimension(15, 25));
        enterIBANTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterIBANTextFieldActionPerformed(evt);
            }
        });

        findAccWithdrawButton.setText("Find");
        findAccWithdrawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    findAccWithdrawButtonActionPerformed(evt);
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
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(enterIBANLabel)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(accountWithdrawLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(accountIdWithdrawLabel))
                                                        .addComponent(enterIBANTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(findAccWithdrawButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(accountWithdrawLabel)
                                        .addComponent(accountIdWithdrawLabel))
                                .addGap(33, 33, 33)
                                .addComponent(enterIBANLabel)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(enterIBANTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(findAccWithdrawButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        cnpWithdrawLabel.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cnpWithdrawLabel.setText("CNP");
        cnpWithdrawLabel.setPreferredSize(new java.awt.Dimension(20, 20));

        firstNameWithdrawLabel.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        firstNameWithdrawLabel.setText("First Name");
        firstNameWithdrawLabel.setPreferredSize(new java.awt.Dimension(60, 20));

        surnameWithdrawLabel.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        surnameWithdrawLabel.setText("Surname");
        surnameWithdrawLabel.setPreferredSize(new java.awt.Dimension(51, 20));

        cnpValueWithdrawLabel.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cnpValueWithdrawLabel.setPreferredSize(new java.awt.Dimension(110, 20));

        firstNameValueWithdrawLabel.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        firstNameValueWithdrawLabel.setPreferredSize(new java.awt.Dimension(110, 20));

        surnameValueWithdrawLabel.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        surnameValueWithdrawLabel.setPreferredSize(new java.awt.Dimension(110, 20));

        balanceWithdrawLabel.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        balanceWithdrawLabel.setText("Balance");
        balanceWithdrawLabel.setPreferredSize(new java.awt.Dimension(63, 23));

        balanceValueWithdrawLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        balanceValueWithdrawLabel.setPreferredSize(new java.awt.Dimension(49, 25));

        amountWithdrawLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        amountWithdrawLabel.setText("WITHDRAW");

        amountWithdrawTextField.setBackground(new java.awt.Color(102, 0, 0));
        amountWithdrawTextField.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        amountWithdrawTextField.setForeground(new java.awt.Color(255, 255, 255));
        amountWithdrawTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountWithdrawTextFieldActionPerformed(evt);
            }
        });

        okWithdrawButton.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        okWithdrawButton.setText("OK");
        okWithdrawButton.setPreferredSize(new java.awt.Dimension(80, 25));
        okWithdrawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    okWithdrawButtonActionPerformed(evt);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cancelWithdrawButton.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cancelWithdrawButton.setText("BACK");
        cancelWithdrawButton.setPreferredSize(new java.awt.Dimension(80, 25));
        cancelWithdrawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelWithdrawButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(firstNameWithdrawLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                        .addComponent(surnameWithdrawLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cnpWithdrawLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(firstNameValueWithdrawLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(surnameValueWithdrawLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(okWithdrawButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(cancelWithdrawButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(amountWithdrawTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(54, 54, 54))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(amountWithdrawLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(balanceWithdrawLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(balanceValueWithdrawLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(131, 131, 131))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cnpValueWithdrawLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(balanceWithdrawLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(30, 30, 30)
                                                .addComponent(balanceValueWithdrawLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(amountWithdrawLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cnpWithdrawLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cnpValueWithdrawLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)))
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(firstNameWithdrawLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(amountWithdrawTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(firstNameValueWithdrawLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(surnameWithdrawLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(surnameValueWithdrawLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(cancelWithdrawButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(okWithdrawButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }

    private void enterIBANTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void amountWithdrawTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void cancelWithdrawButtonActionPerformed(java.awt.event.ActionEvent evt) {
        cancelWithdrawButton.setVisible(false);
        this.dispose();
    }

    private void findAccWithdrawButtonActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {

        String iban = enterIBANTextField.getText();
        ResultSet id_account = serviceDB.findAccountByIBAN(iban);
        if(id_account.next()) {
            ResultSet result = serviceDB.accountJoinUser(id_account.getString(1));
            if(result.next()) {
                cnpValueWithdrawLabel.setText(result.getString(1));
                firstNameValueWithdrawLabel.setText(result.getString(2));
                surnameValueWithdrawLabel.setText(result.getString(3));
                balanceValueWithdrawLabel.setText(result.getString(4));
                accountIdWithdrawLabel.setText(id_account.getString(1));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nu exista niciun cont cu acest IBAN");
        }
    }

    private void okWithdrawButtonActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        Float amount = -Float.parseFloat(amountWithdrawTextField.getText());
        String id_account = accountIdWithdrawLabel.getText();
        boolean status = serviceDB.updateBalance(id_account, amount);
        if (status) {
            JOptionPane.showMessageDialog(this, "Withdraw made!");
            serviceDB.writeInAuditLog(csvWriter,"Retragere bani", String.valueOf(Thread.currentThread().getId()), Thread.currentThread().getName());
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
            String operation_date = dateFormat.format(date);
            String row = operation_date + ",Debit,Retragere cont," + amount.toString() + "," + id_account;
            serviceDB.createAccStatement(row);

            String oldAmount = balanceValueWithdrawLabel.getText();
            Float total = Float.parseFloat(oldAmount) + amount;
            balanceValueWithdrawLabel.setText(total.toString());
        } else {
            System.out.println("Nu s-a putut actualiza baza de date");
        }
    }

    private javax.swing.JLabel accountWithdrawLabel;
    private javax.swing.JLabel accountIdWithdrawLabel;
    private javax.swing.JLabel amountWithdrawLabel;
    private javax.swing.JTextField amountWithdrawTextField;
    private javax.swing.JLabel balanceWithdrawLabel;
    private javax.swing.JLabel balanceValueWithdrawLabel;
    private javax.swing.JButton cancelWithdrawButton;
    private javax.swing.JLabel cnpWithdrawLabel;
    private javax.swing.JLabel cnpValueWithdrawLabel;
    private javax.swing.JLabel enterIBANLabel;
    private javax.swing.JTextField enterIBANTextField;
    private javax.swing.JButton findAccWithdrawButton;
    private javax.swing.JLabel firstNameWithdrawLabel;
    private javax.swing.JLabel firstNameValueWithdrawLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton okWithdrawButton;
    private javax.swing.JLabel surnameWithdrawLabel;
    private javax.swing.JLabel surnameValueWithdrawLabel;
}