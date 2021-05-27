package Interface;

import SystemManagement.ServiceDB;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Customer extends javax.swing.JInternalFrame {
    private static ServiceDB serviceDB;
    private static FileWriter csvWriter;

    public Customer(FileWriter csvWriterMain, ServiceDB serviceDBMain) throws SQLException, IOException {
        serviceDB = serviceDBMain;
        csvWriter = csvWriterMain;
        initComponents();
        autoID();
    }

    public void autoID() throws SQLException {
        customerIdLabel.setText(serviceDB.getID("USERS").toString());
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        customerLabel = new javax.swing.JLabel();
        customerIdLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cnpCustomerLabel = new javax.swing.JLabel();
        firstNameCustomerLabel = new javax.swing.JLabel();
        surnameCustomerLabel = new javax.swing.JLabel();
        birthdateCustomerLabel = new javax.swing.JLabel();
        phoneNumberCustomerLabel = new javax.swing.JLabel();
        cnpCustomerTextField = new javax.swing.JTextField();
        firstNameCustomerTextField = new javax.swing.JTextField();
        surnameCustomerTextField = new javax.swing.JTextField();
        birthdateCustomerTextField = new javax.swing.JTextField();
        phoneNumberCustomerTextField = new javax.swing.JTextField();
        backCustomerButton = new javax.swing.JButton();
        addCustomerButton = new javax.swing.JButton();

        jLabel3.setText("jLabel3");

        customerLabel.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        customerLabel.setText("CUSTOMER");

        customerIdLabel.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        customerIdLabel.setForeground(new java.awt.Color(102, 0, 204));
        customerIdLabel.setText("jLabel1");

        cnpCustomerLabel.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        cnpCustomerLabel.setText("CNP");

        firstNameCustomerLabel.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        firstNameCustomerLabel.setText("First Name");

        surnameCustomerLabel.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        surnameCustomerLabel.setText("Surname");

        birthdateCustomerLabel.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        birthdateCustomerLabel.setText("Birthdate");

        phoneNumberCustomerLabel.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        phoneNumberCustomerLabel.setText("Phone number");
        phoneNumberCustomerLabel.setPreferredSize(new java.awt.Dimension(200, 30));

        cnpCustomerTextField.setPreferredSize(new java.awt.Dimension(20, 30));
        cnpCustomerTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnpCustomerTextFieldActionPerformed(evt);
            }
        });

        firstNameCustomerTextField.setPreferredSize(new java.awt.Dimension(20, 30));
        firstNameCustomerTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameCustomerTextFieldActionPerformed(evt);
            }
        });

        surnameCustomerTextField.setPreferredSize(new java.awt.Dimension(20, 30));

        birthdateCustomerTextField.setPreferredSize(new java.awt.Dimension(20, 30));

        phoneNumberCustomerTextField.setPreferredSize(new java.awt.Dimension(20, 30));

        backCustomerButton.setText("Back");
        backCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backCustomerButtonActionPerformed(evt);
            }
        });

        addCustomerButton.setText("Add");
        addCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    addCustomerButtonActionPerformed(evt);
                } catch (Exception e) {
                    e.printStackTrace();
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
                                        .addComponent(cnpCustomerLabel)
                                        .addComponent(firstNameCustomerLabel)
                                        .addComponent(surnameCustomerLabel)
                                        .addComponent(phoneNumberCustomerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(birthdateCustomerLabel))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cnpCustomerTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(firstNameCustomerTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(surnameCustomerTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(phoneNumberCustomerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(birthdateCustomerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(addCustomerButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backCustomerButton, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cnpCustomerTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cnpCustomerLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(firstNameCustomerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(firstNameCustomerLabel)
                                        .addComponent(addCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(surnameCustomerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(surnameCustomerLabel))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(birthdateCustomerLabel)
                                        .addComponent(birthdateCustomerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(phoneNumberCustomerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(phoneNumberCustomerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(customerLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(customerIdLabel)
                                                .addGap(0, 431, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(customerLabel)
                                        .addComponent(customerIdLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void firstNameCustomerTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void cnpCustomerTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void addCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        String id_user = customerIdLabel.getText();
        String cnp = cnpCustomerTextField.getText();
        String firstName = firstNameCustomerTextField.getText();
        String surname = surnameCustomerTextField.getText();
        String phoneNumber = phoneNumberCustomerTextField.getText();
        String birthdate = birthdateCustomerTextField.getText();

        String row = id_user + "," + cnp + "," + firstName + "," + surname + "," + phoneNumber + "," + birthdate;
        //System.out.println(row);
        boolean status = serviceDB.createUser(row);
        if (status) {
            JOptionPane.showMessageDialog(this, "Record added!");
            serviceDB.writeInAuditLog(csvWriter,"Creare client", String.valueOf(Thread.currentThread().getId()), Thread.currentThread().getName());
            autoID();
            cnpCustomerTextField.setText("");
            firstNameCustomerTextField.setText("");
            surnameCustomerTextField.setText("");
            phoneNumberCustomerTextField.setText("");
            birthdateCustomerTextField.setText("");
            cnpCustomerTextField.requestFocus();
        } else {
            System.out.println("Nu s-a putut insera in baza de date");
        }
    }

    private void backCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        backCustomerButton.setVisible(false);
        this.dispose();
    }

    private javax.swing.JButton addCustomerButton;
    private javax.swing.JLabel birthdateCustomerLabel;
    private javax.swing.JTextField birthdateCustomerTextField;
    private javax.swing.JButton backCustomerButton;
    private javax.swing.JLabel cnpCustomerLabel;
    private javax.swing.JTextField cnpCustomerTextField;
    private javax.swing.JLabel customerIdLabel;
    private javax.swing.JLabel customerLabel;
    private javax.swing.JLabel firstNameCustomerLabel;
    private javax.swing.JTextField firstNameCustomerTextField;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel phoneNumberCustomerLabel;
    private javax.swing.JTextField phoneNumberCustomerTextField;
    private javax.swing.JLabel surnameCustomerLabel;
    private javax.swing.JTextField surnameCustomerTextField;
}
