package Interface;

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


public class Deposit extends javax.swing.JInternalFrame {
    private static ServiceDB serviceDB;
    private static FileWriter csvWriter;

    public Deposit(FileWriter csvWriterMain, ServiceDB serviceDBMain) throws SQLException, IOException {
        serviceDB = serviceDBMain;
        csvWriter = csvWriterMain;
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        accountDepositLabel = new javax.swing.JLabel();
        accountIdDepositLabel = new javax.swing.JLabel();
        enterIBANLabel = new javax.swing.JLabel();
        enterIBANTextField = new javax.swing.JTextField();
        findAccDepositButton = new javax.swing.JButton();
        cnpDepositLabel = new javax.swing.JLabel();
        firstNameDepositLabel = new javax.swing.JLabel();
        surnameDepositLabel = new javax.swing.JLabel();
        cnpValueDepositLabel = new javax.swing.JLabel();
        firstNameValueDepositLabel = new javax.swing.JLabel();
        surnameValueDepositLabel = new javax.swing.JLabel();
        balanceDepositLabel = new javax.swing.JLabel();
        balanceValueDepositLabel = new javax.swing.JLabel();
        amountDepositLabel = new javax.swing.JLabel();
        amountDepositTextField = new javax.swing.JTextField();
        okDepositButton = new javax.swing.JButton();
        cancelDepositButton = new javax.swing.JButton();

        accountDepositLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        accountDepositLabel.setText("ACCOUNT");

        accountIdDepositLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        accountIdDepositLabel.setForeground(new java.awt.Color(102, 0, 153));
        accountIdDepositLabel.setPreferredSize(new java.awt.Dimension(65, 23));

        enterIBANLabel.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        enterIBANLabel.setText("Enter your IBAN");

        enterIBANTextField.setPreferredSize(new java.awt.Dimension(15, 25));
        enterIBANTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterIBANTextFieldActionPerformed(evt);
            }
        });

        findAccDepositButton.setText("Find");
        findAccDepositButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    findAccDepositButtonActionPerformed(evt);
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
                                                                .addComponent(accountDepositLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(accountIdDepositLabel))
                                                        .addComponent(enterIBANTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(findAccDepositButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(accountDepositLabel)
                                        .addComponent(accountIdDepositLabel))
                                .addGap(33, 33, 33)
                                .addComponent(enterIBANLabel)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(enterIBANTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(findAccDepositButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        cnpDepositLabel.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cnpDepositLabel.setText("CNP");
        cnpDepositLabel.setPreferredSize(new java.awt.Dimension(20, 20));

        firstNameDepositLabel.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        firstNameDepositLabel.setText("First Name");
        firstNameDepositLabel.setPreferredSize(new java.awt.Dimension(60, 20));

        surnameDepositLabel.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        surnameDepositLabel.setText("Surname");
        surnameDepositLabel.setPreferredSize(new java.awt.Dimension(51, 20));

        cnpValueDepositLabel.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cnpValueDepositLabel.setPreferredSize(new java.awt.Dimension(110, 20));

        firstNameValueDepositLabel.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        firstNameValueDepositLabel.setPreferredSize(new java.awt.Dimension(110, 20));

        surnameValueDepositLabel.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        surnameValueDepositLabel.setPreferredSize(new java.awt.Dimension(110, 20));

        balanceDepositLabel.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        balanceDepositLabel.setText("Balance");
        balanceDepositLabel.setPreferredSize(new java.awt.Dimension(63, 23));

        balanceValueDepositLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        balanceValueDepositLabel.setPreferredSize(new java.awt.Dimension(49, 25));

        amountDepositLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        amountDepositLabel.setText("DEPOSIT");

        amountDepositTextField.setBackground(new java.awt.Color(102, 0, 0));
        amountDepositTextField.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        amountDepositTextField.setForeground(new java.awt.Color(255, 255, 255));
        amountDepositTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountDepositTextFieldActionPerformed(evt);
            }
        });

        okDepositButton.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        okDepositButton.setText("OK");
        okDepositButton.setPreferredSize(new java.awt.Dimension(80, 25));
        okDepositButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    okDepositButtonActionPerformed(evt);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cancelDepositButton.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        cancelDepositButton.setText("BACK");
        cancelDepositButton.setPreferredSize(new java.awt.Dimension(80, 25));
        cancelDepositButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelDepositButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(firstNameDepositLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                        .addComponent(surnameDepositLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cnpDepositLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(firstNameValueDepositLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(surnameValueDepositLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(okDepositButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(cancelDepositButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(amountDepositTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(54, 54, 54))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(amountDepositLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(balanceDepositLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(balanceValueDepositLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(131, 131, 131))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cnpValueDepositLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                                .addComponent(balanceDepositLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(30, 30, 30)
                                                .addComponent(balanceValueDepositLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(amountDepositLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cnpDepositLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cnpValueDepositLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)))
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(firstNameDepositLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(amountDepositTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(firstNameValueDepositLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(surnameDepositLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(surnameValueDepositLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(cancelDepositButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(okDepositButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }

    private void enterIBANTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void amountDepositTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void cancelDepositButtonActionPerformed(java.awt.event.ActionEvent evt) {
        cancelDepositButton.setVisible(false);
        this.dispose();
    }

    private void findAccDepositButtonActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {

        String iban = enterIBANTextField.getText();
        ResultSet id_account = serviceDB.findAccountByIBAN(iban);
        if(id_account.next()) {
            ResultSet result = serviceDB.accountJoinUser(id_account.getString(1));
            if(result.next()) {
                cnpValueDepositLabel.setText(result.getString(1));
                firstNameValueDepositLabel.setText(result.getString(2));
                surnameValueDepositLabel.setText(result.getString(3));
                balanceValueDepositLabel.setText(result.getString(4));
                accountIdDepositLabel.setText(id_account.getString(1));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nu exista niciun cont cu acest IBAN");
        }
    }

    private void okDepositButtonActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        Float amount = Float.parseFloat(amountDepositTextField.getText());
        String id_account = accountIdDepositLabel.getText();
        boolean status = serviceDB.updateBalance(id_account, amount);
        if (status) {
            JOptionPane.showMessageDialog(this, "Deposit made!");
            serviceDB.writeInAuditLog(csvWriter,"Depunere bani", String.valueOf(Thread.currentThread().getId()), Thread.currentThread().getName());
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
            String operation_date = dateFormat.format(date);
            String row = operation_date + ",Debit,Alimentare cont," + amount.toString() + "," + id_account;
            serviceDB.createAccStatement(row);

            String oldAmount = balanceValueDepositLabel.getText();
            Float total = Float.parseFloat(oldAmount) + amount;
            balanceValueDepositLabel.setText(total.toString());
        } else {
            System.out.println("Nu s-a putut actualiza baza de date");
        }
    }

    private javax.swing.JLabel accountDepositLabel;
    private javax.swing.JLabel accountIdDepositLabel;
    private javax.swing.JLabel amountDepositLabel;
    private javax.swing.JTextField amountDepositTextField;
    private javax.swing.JLabel balanceDepositLabel;
    private javax.swing.JLabel balanceValueDepositLabel;
    private javax.swing.JButton cancelDepositButton;
    private javax.swing.JLabel cnpDepositLabel;
    private javax.swing.JLabel cnpValueDepositLabel;
    private javax.swing.JLabel enterIBANLabel;
    private javax.swing.JTextField enterIBANTextField;
    private javax.swing.JButton findAccDepositButton;
    private javax.swing.JLabel firstNameDepositLabel;
    private javax.swing.JLabel firstNameValueDepositLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton okDepositButton;
    private javax.swing.JLabel surnameDepositLabel;
    private javax.swing.JLabel surnameValueDepositLabel;
}