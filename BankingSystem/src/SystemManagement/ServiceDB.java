package SystemManagement;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

//http://db.apache.org/derby/derby_downloads.html

public class ServiceDB implements AutoCloseable {
    private static boolean loadedBanks = false;
    private static boolean loadedUsers = false;
    private static boolean loadedAccounts = false;
    private static boolean loadedAccStatements = false;
    private Connection connection;

    public ServiceDB() throws SQLException, IOException {
        File file = new File("auditLog.csv");
        this.connection = DriverManager.getConnection("jdbc:derby:bankingSystemDB;create=true");
        boolean notFound = true;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }



    /* ----------------------------------------------------------------- */

    public Integer getID(String tableName) throws SQLException {
        Integer id = 0;
        String columnName = tableName.substring(0, tableName.length() - 1).toUpperCase();
        PreparedStatement statementSize = connection.prepareStatement("SELECT COUNT(*) FROM " + tableName.toUpperCase());
        ResultSet size = statementSize.executeQuery();
        if(size.next()){
            if(size.getString(1).equals("0")) {
                id = 1;
            } else {
                PreparedStatement statementSelect = connection.prepareStatement("SELECT MAX(ID_" + columnName + ") + 1 FROM " + tableName.toUpperCase());
                ResultSet maximum = statementSelect.executeQuery();
                while(maximum.next()) {
                    id = Integer.parseInt(maximum.getString(1));
                }
            }
        }

        return id;
    }

    /* ----------------------------------------------------------------- */

    public void loadBanks() throws Exception {
        if (loadedBanks == false) {
            Integer id_bank = getID("BANKS");

            String row;
            BufferedReader csvReader = new BufferedReader(new FileReader("data/banks.csv"));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                PreparedStatement statement = connection.prepareStatement("INSERT INTO BANKS (ID_BANK, COUNTRY_NAME, BANK_NAME, BIC) VALUES (?,?,?,?)");
                statement.setString(1, id_bank.toString());
                statement.setString(2, data[0].replace("\n", ""));
                statement.setString(3, data[1].replace("\n", ""));
                statement.setString(4, data[2].replace("\n", ""));
                id_bank++;
                if (statement.executeUpdate() != 1) {
                    System.out.println("Nu s-a putut insera in tabela BANKS");
                    return;
                }
            }
            loadedBanks = true;
            System.out.println("banks.csv incarcat cu succes");
        } else {
            System.out.println("Date deja incarcate din CSV");
        }
    }

    public ResultSet findBankByBIC(String bic) throws SQLException {
        PreparedStatement statementSelect = connection.prepareStatement("SELECT ID_BANK FROM BANKS WHERE BIC = ?");
        statementSelect.setString(1, bic);
        ResultSet id_bank = statementSelect.executeQuery();
        return id_bank;
    }

    public ResultSet getBanks() throws SQLException {
        PreparedStatement statementSelect = connection.prepareStatement("SELECT * FROM BANKS");
        ResultSet banks = statementSelect.executeQuery();
        return banks;
    }

    public Bank findBankById(String id_bank) throws SQLException {
        PreparedStatement statementSelect = connection.prepareStatement("SELECT * FROM BANKS WHERE ID_BANK = ?");
        statementSelect.setString(1, id_bank);
        ResultSet bank = statementSelect.executeQuery();
        bank.next();
        Bank newBank = new Bank(bank.getString(1), bank.getString(2), bank.getString(3), bank.getString(4));
        return newBank;
    }

    public Bank findBankByNames(String countryName, String bankName) throws SQLException {
        PreparedStatement statementSelect = connection.prepareStatement("SELECT * FROM BANKS WHERE COUNTRY_NAME = ? AND BANK_NAME = ?");
        statementSelect.setString(1, countryName);
        statementSelect.setString(2, bankName);
        ResultSet bank = statementSelect.executeQuery();
        bank.next();
        Bank newBank = new Bank(bank.getString(1), bank.getString(2), bank.getString(3), bank.getString(4));
        return newBank;
    }

    public boolean checkClientAtBank(String id_bank, String cnp) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select COUNT(*) from users u join accounts a on u.id_user = a.id_user join banks b on a.ID_BANK = b.ID_BANK WHERE b.ID_BANK = ? AND u.CNP = ?");
        statement.setString(1, id_bank);
        statement.setString(2, cnp);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            if(result.getString(1).equals("0")) {
                return false;
            }
        }
        return true;
    }

    /* ----------------------------------------------------------------- */

    public void loadUsers() throws Exception {
        if (loadedUsers == false) {
            Integer id_user = getID("USERS");

            String row;
            BufferedReader csvReader = new BufferedReader(new FileReader("data/clients.csv"));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS (ID_USER, CNP, FIRST_NAME, SURNAME, PHONE_NUMBER, BIRTHDATE) VALUES (?,?,?,?,?,?)");
                statement.setString(1, id_user.toString());
                statement.setString(2, data[0].replace("\n", ""));
                statement.setString(3, data[1].replace("\n", ""));
                statement.setString(4, data[2].replace("\n", ""));
                statement.setString(5, data[3].replace("\n", ""));
                statement.setString(6, data[4].replace("\n", ""));
                id_user++;
                if (statement.executeUpdate() != 1) {
                    System.out.println("Nu s-a putut insera in baza de date");
                    return;
                }
            }
            loadedUsers = true;
            System.out.println("users.csv incarcat cu succes");
        } else {
            System.out.println("Date deja incarcate din CSV");
        }
    }

    public boolean createUser(String row) throws Exception {
        String[] data = row.split(",");
        PreparedStatement statement = connection.prepareStatement("INSERT INTO USERS (ID_USER, CNP, FIRST_NAME, SURNAME, PHONE_NUMBER, BIRTHDATE) VALUES (?,?,?,?,?,?)");
        statement.setString(1, data[0].replace("\n", ""));
        statement.setString(2, data[1].replace("\n", ""));
        statement.setString(3, data[2].replace("\n", ""));
        statement.setString(4, data[3].replace("\n", ""));
        statement.setString(5, data[4].replace("\n", ""));
        statement.setString(6, data[5].replace("\n", ""));
        if (statement.executeUpdate() != 1) {
            System.out.println("Nu s-a putut insera in baza de date");
            return false;
        }

        return true;
    }

    public void showUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS");
        ResultSet results = statement.executeQuery();
        while(results.next()){
            users.add(new User(results.getString(1), results.getString(2),
                    results.getString(3), results.getString(4), results.getString(5)));
        }

        for(User user : users) {
            System.out.println(user);
        }
    }
    /* ----------------------------------------------------------------- */
    public void loadAccounts() throws Exception {
        if (loadedAccounts == false) {

            String row;
            BufferedReader csvReader = new BufferedReader(new FileReader("data/accounts.csv"));
            while ((row = csvReader.readLine()) != null) {
                Account account;
                String[] data = row.split(",");

                ResultSet id_user = this.findClientByCnp(data[0]);
                if(id_user.next()) {
                    Bank bank = this.findBankByNames(data[3], data[2]);
                    ResultSet id_bank = this.findBankByBIC(bank.getBIC());
                    if(id_bank.next()) {

                        if (data[1].toUpperCase().equals("SAVINGS")) {
                            this.createAccount(id_user.getString(1), id_bank.getString(1), "Savings", "3 luni", 0.2f, false, "");

                        } else {
                            this.createAccount(id_user.getString(1), id_bank.getString(1), "Current", "", 0f, false, "");
                        }
                    } else {
                        System.out.println("Banca incarcata nu exista");
                    }
                } else {
                    System.out.println("Clientul incarcat nu exista");
                }
            }
            loadedAccounts = true;
            System.out.println("Accounts.csv incarcat cu succes");
        } else {
            System.out.println("Date deja incarcate din CSV pentru conturi");
        }
    }


    public boolean createAccount(String id_user, String id_bank, String type, String period, float interestRate, boolean isJoint, String id_joint_user) throws SQLException {
        Integer id_account = getID("ACCOUNTS");
        boolean status = false;

        // client deja existent
        if(id_user != "") {
            Bank bank = this.findBankById(id_bank);
            bank.setId(id_bank);
            User user = this.findUserById(id_user);
            user.setId(id_user);

            if(type.toUpperCase().equals("CURRENT")) {
                status = createCurrentAccount(id_account, user, bank);
            } else {
                status = createSavingsAccount(id_account, user, bank, period, interestRate, isJoint, id_joint_user);
            }
        }
        return status;
    }

    public boolean createCurrentAccount(Integer id_account, User user, Bank bank) throws SQLException {
        CurrentAccount currentAccount = new CurrentAccount(user, bank);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO ACCOUNTS (ID_ACCOUNT, ACCOUNT_NUMBER, IBAN, CURRENCY, CURRENT_BALANCE, ID_USER, ID_BANK) VALUES (?,?,?,?,?,?,?)");
        statement.setString(1, id_account.toString());
        statement.setString(2, currentAccount.getAccountNumber().toString());
        statement.setString(3, currentAccount.getIBAN());
        statement.setString(4, currentAccount.getCurrency());
        statement.setString(5, Float.toString(currentAccount.getCurrentBalance()));
        statement.setString(6, user.getId());
        statement.setString(7, bank.getId());
        if (statement.executeUpdate() != 1) {
            System.out.println("Nu s-a putut insera in tabela ACCOUNTS");
            return false;
        } else {
            statement = connection.prepareStatement("INSERT INTO CURRENT_ACCOUNTS (ID_CURRENT_ACCOUNT) VALUES (?)");
            statement.setString(1, id_account.toString());
            if (statement.executeUpdate() != 1) {
                System.out.println("Nu s-a putut insera in tabela CURRENT_ACCOUNTS");
                return false;
            }
        }
        return true;
    }

    public boolean createSavingsAccount(Integer id_account, User user, Bank bank, String period, float interestRate, boolean isJoint, String id_joint_user) throws SQLException {
        SavingsAccount savingsAccount = new SavingsAccount(user, bank);
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        String openDate = dateFormat.format(date);

        savingsAccount.setAccountOptions(period, interestRate, openDate, isJoint);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO ACCOUNTS (ID_ACCOUNT, ACCOUNT_NUMBER, IBAN, CURRENCY, CURRENT_BALANCE, ID_USER, ID_BANK) VALUES (?,?,?,?,?,?,?)");
        statement.setString(1, id_account.toString());
        statement.setString(2, savingsAccount.getAccountNumber().toString());
        statement.setString(3, savingsAccount.getIBAN());
        statement.setString(4, savingsAccount.getCurrency());
        statement.setString(5, Float.toString(savingsAccount.getCurrentBalance()));
        statement.setString(6, user.getId());
        statement.setString(7, bank.getId());
        if (statement.executeUpdate() != 1) {
            System.out.println("Nu s-a putut insera in tabela ACCOUNTS");
            return false;
        } else {
            statement = connection.prepareStatement("INSERT INTO SAVINGS_ACCOUNTS (ID_SAVINGS_ACCOUNT, PERIOD, INTEREST_RATE, OPEN_DATE) VALUES (?,?,?,?)");
            statement.setString(1, id_account.toString());
            statement.setString(2, period);
            statement.setString(3, Float.toString(interestRate));
            statement.setString(4, openDate);
            if (statement.executeUpdate() != 1) {
                System.out.println("Nu s-a putut insera in tabela SAVINGS_ACCOUNTS");
                return false;
            } else {
                if (isJoint == true) {
                    statement = connection.prepareStatement("UPDATE SAVINGS_ACCOUNTS SET ID_JOINT_USER = ? WHERE ID_SAVINGS_ACCOUNT = ?");
                    statement.setString(1, id_joint_user);
                    statement.setString(2, id_account.toString());
                    if (statement.executeUpdate() != 1) {
                        System.out.println("Nu s-a putut actualiza clientul comun din tabela SAVINGS_ACCOUNT");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public ResultSet findClientByCnp(String cnp) throws SQLException {
        PreparedStatement statementSelect = connection.prepareStatement("SELECT * FROM USERS WHERE CNP = ?");
        statementSelect.setString(1, cnp);
        ResultSet id_user = statementSelect.executeQuery();
        return id_user;
    }

    public User findUserById(String id_user) throws SQLException {
        PreparedStatement statementSelect = connection.prepareStatement("SELECT * FROM USERS WHERE ID_USER = ?");
        statementSelect.setString(1, id_user);
        ResultSet user = statementSelect.executeQuery();
        user.next();
        User newUser = new User(user.getString(1), user.getString(2), user.getString(3), user.getString(4), user.getString(5), user.getString(6));
        return newUser;
    }

    public ResultSet findAccountByIBAN(String iban) throws SQLException {
        PreparedStatement statementSelect = connection.prepareStatement("SELECT ID_ACCOUNT FROM ACCOUNTS WHERE IBAN = ?");
        statementSelect.setString(1, iban);
        ResultSet id_account = statementSelect.executeQuery();
        return id_account;
    }

    public ResultSet getCurrentBalance(String id_account) throws SQLException {
        PreparedStatement statementSelect = connection.prepareStatement("SELECT CURRENT_BALANCE FROM ACCOUNTS WHERE ID_ACCOUNT = ?");
        statementSelect.setString(1, id_account);
        ResultSet currentBalance = statementSelect.executeQuery();
        return currentBalance;
    }

    public ResultSet accountJoinUser(String id_account) throws SQLException {
        PreparedStatement statementSelect = connection.prepareStatement("SELECT U.CNP, U.FIRST_NAME, U.SURNAME, A.CURRENT_BALANCE FROM USERS U JOIN ACCOUNTS A ON U.ID_USER = A.ID_USER WHERE A.ID_ACCOUNT = ?");
        statementSelect.setString(1, id_account);
        ResultSet result = statementSelect.executeQuery();
        return result;
    }

    public boolean updateBalance(String id_account, Float balance) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT CURRENT_BALANCE FROM ACCOUNTS WHERE ID_ACCOUNT = ?");
        statement.setString(1, id_account);
        ResultSet curr_balance = statement.executeQuery();
        curr_balance.next();
        balance += Float.parseFloat(curr_balance.getString(1));
        statement = connection.prepareStatement("UPDATE ACCOUNTS SET CURRENT_BALANCE = ? WHERE ID_ACCOUNT = ?");
        statement.setString(1, balance.toString());
        statement.setString(2, id_account);
        return statement.executeUpdate() == 1;
    }

    /* ----------------------------------------------------------------- */
    public void loadAccStatements() throws Exception {
        if (loadedAccStatements == false) {
            Integer id_acc_statement = getID("ACCOUNT_STATEMENTS");

            String row;
            BufferedReader csvReader = new BufferedReader(new FileReader("data/accountStatements.csv"));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                PreparedStatement statement = connection.prepareStatement("INSERT INTO ACCOUNT_STATEMENTS (ID_ACCOUNT_STATEMENT, OPERATION_DATE, OPERATION, ACCOUNT_TYPE, AMOUNT, ID_ACCOUNT) VALUES (?,?,?,?,?,?)");
                statement.setString(1, id_acc_statement.toString());
                statement.setString(2, data[0].replace("\n", ""));
                statement.setString(3, data[1].replace("\n", ""));
                statement.setString(4, data[2].replace("\n", ""));
                statement.setString(5, data[3].replace("\n", ""));

                ResultSet id_account = this.findAccountByIBAN(data[4]);
                id_account.next();
                System.out.println(id_acc_statement.toString() + " " + data[0] + " " + data[1] + " " + data[2] + " " + data[3] + " " );
                statement.setString(6, id_account.getString(1));

                id_acc_statement++;
                if (statement.executeUpdate() != 1) {
                    System.out.println("Nu s-a putut insera in tabela ACCOUNT_STATEMENTS");
                    return;
                } else {
                    if(data[2].toUpperCase().equals("ALIMENTARE CONT")) {
                        this.updateBalance(id_account.getString(1), Float.parseFloat(data[3]));
                    }
                }
            }
            loadedAccStatements = true;
            System.out.println("accountStatements.csv incarcat cu succes");
        } else {
            System.out.println("Date deja incarcate din CSV");
        }
    }

    public boolean createAccStatement(String row) throws Exception {
        Integer id_account_statement = getID("ACCOUNT_STATEMENTS");

        String[] data = row.split(",");
        PreparedStatement statement = connection.prepareStatement("INSERT INTO ACCOUNT_STATEMENTS (ID_ACCOUNT_STATEMENT, OPERATION_DATE, ACCOUNT_TYPE, OPERATION, AMOUNT, ID_ACCOUNT) VALUES (?,?,?,?,?,?)");
        statement.setString(1, id_account_statement.toString());
        statement.setString(2, data[0].replace("\n", ""));
        statement.setString(3, data[1].replace("\n", ""));
        statement.setString(4, data[2].replace("\n", ""));
        statement.setString(5, data[3].replace("\n", ""));
        statement.setString(6, data[4].replace("\n", ""));
        if (statement.executeUpdate() != 1) {
            System.out.println("Nu s-a putut insera in tabela ACCOUNT_STATEMENTS");
            return false;
        }

        return true;
    }

    public ResultSet showAccStatements(String id_account) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT ID_ACCOUNT_STATEMENT, OPERATION_DATE, OPERATION, ACCOUNT_TYPE, AMOUNT FROM ACCOUNT_STATEMENTS WHERE ID_ACCOUNT = ?");
        statement.setString(1, id_account);
        ResultSet results = statement.executeQuery();
        return results;
    }

    public boolean deleteAccStatement(String id_account_statement) throws SQLException {
        PreparedStatement statementSelect = connection.prepareStatement("DELETE FROM ACCOUNT_STATEMENTS WHERE ID_ACCOUNT_STATEMENT = ?");
        statementSelect.setString(1, id_account_statement);
        return statementSelect.executeUpdate() == 1;
    }

    public ResultSet findAccountsByID(String id_user) throws SQLException {
        PreparedStatement statementSelect = connection.prepareStatement("SELECT IBAN FROM ACCOUNTS WHERE ID_USER = ?");
        statementSelect.setString(1, id_user);
        ResultSet accounts = statementSelect.executeQuery();
        return accounts;
    }

    public ResultSet findCurrentAccountsByID(String id_user) throws SQLException {
        PreparedStatement statementSelect = connection.prepareStatement("SELECT A.IBAN FROM ACCOUNTS A JOIN CURRENT_ACCOUNTS C ON A.ID_ACCOUNT = C.ID_CURRENT_ACCOUNT WHERE A.ID_USER = ?");
        statementSelect.setString(1, id_user);
        ResultSet accounts = statementSelect.executeQuery();
        return accounts;
    }

    public static void writeInAuditLog(FileWriter csvWriter, String action, String id_thread, String thread_name) throws IOException {
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
        System.out.println(action + " - " + timeStamp + " de catre threadul cu ID: " + id_thread + " si numele " + thread_name + "\n");
        csvWriter.append(action + " - " + timeStamp + " de catre threadul cu ID: " + id_thread + " si numele " + thread_name + "\n");
    }
}