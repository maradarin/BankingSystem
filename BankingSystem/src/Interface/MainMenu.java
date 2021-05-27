package Interface;

import SystemManagement.Service;
import SystemManagement.ServiceDB;
import SystemManagement.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;


public class MainMenu extends javax.swing.JFrame {
    private static FileWriter csvWriter;
    private static ServiceDB serviceDB;
    public MainMenu() throws IOException, SQLException {
        serviceDB = new ServiceDB();
        File file = new File("auditLog.csv");
        csvWriter = new FileWriter(file);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        customerMenuItem = new javax.swing.JMenuItem();
        accountMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        transactionMenu = new javax.swing.JMenu();
        withdrawMenuItem = new javax.swing.JMenuItem();
        depositMenuItem = new javax.swing.JMenuItem();
        transferMenu = new javax.swing.JMenu();
        accToAccMenuItem = new javax.swing.JMenuItem();
        reportMenu = new javax.swing.JMenu();
        customerReportMenuItem = new javax.swing.JMenuItem();
        balanceMenu = new javax.swing.JMenu();
        balanceCheckMenuItem = new javax.swing.JMenuItem();
        userAccountMenuItem = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1366, 768));

        jDesktopPane1.setBackground(new java.awt.Color(0, 0, 0));
        jDesktopPane1.setPreferredSize(new java.awt.Dimension(1366, 768));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
                jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 672, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
                jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 388, Short.MAX_VALUE)
        );

        fileMenu.setText("File");

        customerMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        customerMenuItem.setText("Customer");
        customerMenuItem.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mara\\Desktop\\334-3347042_icon-add-user-icon2.png"));
        customerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    customerMenuItemActionPerformed(evt);
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        fileMenu.add(customerMenuItem);

        accountMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        accountMenuItem.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mara\\Desktop\\piggy_bank2.png"));
        accountMenuItem.setText("Account");
        accountMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    accountMenuItemActionPerformed(evt);
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        fileMenu.add(accountMenuItem);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        exitMenuItem.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mara\\Desktop\\exiticon3.png"));
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    exitMenuItemActionPerformed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        fileMenu.add(exitMenuItem);

        jMenuBar1.add(fileMenu);

        transactionMenu.setText("Transaction");

        withdrawMenuItem.setText("Withdraw");
        withdrawMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    withdrawMenuItemActionPerformed(evt);
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        transactionMenu.add(withdrawMenuItem);

        depositMenuItem.setText("Deposit");
        depositMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    depositMenuItemActionPerformed(evt);
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        transactionMenu.add(depositMenuItem);

        jMenuBar1.add(transactionMenu);

        transferMenu.setText("Transfer");

        accToAccMenuItem.setText("Account to Account");
        accToAccMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accToAccMenuItemActionPerformed(evt);
            }
        });
        transferMenu.add(accToAccMenuItem);

        jMenuBar1.add(transferMenu);

        reportMenu.setText("Report");

        customerReportMenuItem.setText("Customer Report");
        customerReportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    customerReportMenuItemActionPerformed(evt);
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        reportMenu.add(customerReportMenuItem);

        jMenuBar1.add(reportMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
        );

        pack();
    }

    private void customerMenuItemActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, IOException {
        Customer cus = new Customer(csvWriter, serviceDB);
        jDesktopPane1.add(cus);
        cus.setVisible(true);
    }

    private void accountMenuItemActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, IOException {
        Account acc = new Account(csvWriter, serviceDB);
        jDesktopPane1.add(acc);
        acc.setVisible(true);
    }

    private void withdrawMenuItemActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, IOException {
        Withdraw wit = new Withdraw(csvWriter, serviceDB);
        jDesktopPane1.add(wit);
        wit.setVisible(true);
    }

    private void depositMenuItemActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, IOException {
        Deposit dep = new Deposit(csvWriter, serviceDB);
        jDesktopPane1.add(dep);
        dep.setVisible(true);
    }

    private void customerReportMenuItemActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, IOException {
        CustomerReport custRep = new CustomerReport(csvWriter, serviceDB);
        jDesktopPane1.add(custRep);
        custRep.setVisible(true);
    }

    private void accToAccMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        Transfer tns = new Transfer(csvWriter, serviceDB);
        jDesktopPane1.add(tns);
        tns.setVisible(true);
    }

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        csvWriter.flush();
        csvWriter.close();
        exitMenuItem.setVisible(false);
        this.dispose();
    }

    public static void main(String[] args) throws Exception {
        Service service = Service.launchService();
        service.loadAvailableCountryCodes();
        ServiceDB serviceDB = new ServiceDB();
        //serviceDB.loadBanks();
        //serviceDB.loadUsers();
        //serviceDB.loadAccounts();
        //serviceDB.loadAccStatements();

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainMenu().setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }

    private javax.swing.JMenuItem accToAccMenuItem;
    private javax.swing.JMenuItem accountMenuItem;
    private javax.swing.JMenuItem balanceCheckMenuItem;
    private javax.swing.JMenu balanceMenu;
    private javax.swing.JMenuItem customerMenuItem;
    private javax.swing.JMenuItem customerReportMenuItem;
    private javax.swing.JMenuItem depositMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu reportMenu;
    private javax.swing.JMenu transactionMenu;
    private javax.swing.JMenu transferMenu;
    private javax.swing.JMenu userAccountMenuItem;
    private javax.swing.JMenuItem withdrawMenuItem;
}
