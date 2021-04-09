package BankingSystem;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Scanner;

// Singleton
// Asigurarea faptului ca aceasta clasa va fi instantiata doar o data
public class Service {
    private static Service single_instance = null;

    private Service() {

    }

    public static Service launchService() {
        if(single_instance == null) {
            single_instance = new Service();
        }

        return single_instance;
    }

    public List<Bank> availableBanks() {
        return DataBase.getBanks();
    }

    public void loadAvailableBanks() {
        Bank ING = new Bank("Romania", "ING", "INGBROBU");

        Bank Postbank = new Bank("Germany", "Postbank", "PBNKDEFF");

        Bank Citibank = new Bank("USA", "Citibank", "CITIUS33");

        Bank Allianz = new Bank("Austria", "Allianz", "AIAGATWWKAG");

        Bank BRD = new Bank("France", "BRD", "SOGEFRPP");

        Bank Caixabank = new Bank("Spain", "Caixabank", "CAIXESBB");

    }

    public void loadAvailableClients() {
        User userING = new User("1234567890000", "Ion", "Popescu", "12-Apr-2000", "0711111111");
        userING.openAccount("Current", "ING", "Romania");

        User userBRD = new User("1234567890001", "Pierre", "Hollande", "13-Apr-2000", "0711111112");
        userBRD.openAccount("Savings", "BRD", "France");

        User userCiti = new User("1234567890002", "John", "Doe", "14-Apr-2000", "0711111113");
    }

    public void dropDownBanks(boolean withHeader) {
        if(withHeader == true) {
            System.out.println(" __________________________________");
            System.out.println("|       DROP-DOWN SELECTION        |");
            System.out.println(" ----------------------------------");
        }
        Integer poz = 1;
        for (Bank b : DataBase.getBanks()) {
            System.out.println("    " + poz.toString() + ". " + b.name + " - " + b.country);
            poz++;
        }
    }

    public void dropDownAccounts(User user, boolean withHeader) {
        if(withHeader == true) {
            System.out.println(" __________________________________");
            System.out.println("|       DROP-DOWN SELECTION        |");
            System.out.println(" ----------------------------------");
        }
        String accountType = "";
        Integer poz = 1;
        for (Account account : user.getAccounts()) {
            if(account instanceof CurrentAccount) {
                accountType = "Current account";
            }
            else {
                accountType = "Savings account";
            }
            System.out.println(poz.toString() + ". " + account.getIBAN() + " - " + accountType);
            poz++;
        }
    }

    public void openAccount(Integer indexSelectedBank, String clientCNP) {
        Scanner scAux = new Scanner(System.in);
        Bank bank = DataBase.getBanks().get(indexSelectedBank - 1);
        User user = DataBase.findClientByCNP(bank.getName(), bank.getCountry(), clientCNP);
        User client;
        if(user == null) {
            // Client nou, datele se introduc manual
            System.out.println("Introduceti datele dumneavoastra personale: ");
            System.out.print("Nume: ");
            String Surname = scAux.nextLine();
            System.out.print("Prenume: ");
            String Name = scAux.nextLine();
            System.out.print("Date of birth. Format should be dd-mon-yyyy. E.g. 05-Jul-2000: ");
            String dateOfBirth = scAux.nextLine();
            System.out.print("Phone number: ");
            String phoneNumber = scAux.nextLine();

            client = new User(clientCNP, Name, Surname, dateOfBirth, phoneNumber);
            bank.addClient(client);
        }
        else {
            client = new User(user.getCNP(), user.getName(), user.getSurname(), user.getDateOfBirth(), user.getPhoneNumber());
        }

        System.out.println();
        System.out.print("Ce tip de cont doriti? Cont curent sau cont de economii?: ");
        String type = scAux.nextLine();

        client.openAccount(type, bank.getName(), bank.getCountry());

    }

    public void deposit(String IBAN) {
        Scanner scAux = new Scanner(System.in);
        Account account = DataBase.getAccountFromIBAN(IBAN);
        String PIN = "";

        if(account instanceof CurrentAccount) {
            System.out.println("Introduceti PIN-ul: ");
            PIN = scAux.nextLine();
        }

        System.out.println("Introduceti suma de bani: ");
        Float amount = scAux.nextFloat();

        account.deposit(PIN, amount);
    }

    protected void updatePIN(String IBAN) {
        Scanner scAux = new Scanner(System.in);
        Account account = DataBase.getAccountFromIBAN(IBAN);

        if(account instanceof CurrentAccount) {
            System.out.println("Introduceti noul PIN: ");
            String newPIN = scAux.nextLine();
            ((CurrentAccount) account).getCreditCard().updatePIN(newPIN);
        }
        else {
            System.out.println("Nu puteti seta un cod PIN pentru un cont de economii!");
        }
    }

    protected void withdraw(Account account, String PIN, float amount, String currentDate) {
        account.withdraw(PIN, amount, currentDate);
    }

    protected void accountStatementIBAN(String IBAN) {
        Account account = DataBase.getAccountFromIBAN(IBAN);
        for(AccountStatement accountStatement : account.getAccountStatements()) {
            accountStatement.print();
        }
    }

    protected void accountStatement(String bankName, String countryName, String CNP) {
        Bank bank = DataBase.getBankByNames(bankName, countryName);
        User user = bank.findUser(CNP);
        Scanner scAux = new Scanner(System.in);
        this.dropDownAccounts(user, true);
        System.out.println("Alegeti contul pentru care doriti sa vizualizati extrasul de cont: ");
        int selectedAccount = scAux.nextInt();
        if(selectedAccount <= user.getAccounts().size()) {
            Account account = user.getAccounts().get(selectedAccount - 1);
            for (AccountStatement accountStatement : account.getAccountStatements()) {
                accountStatement.print();
            }
        }
        else {
            System.out.println("Indice gresit!");
        }
    }

    public void transfer(String IBAN1, String IBAN2) {
        Scanner scAux = new Scanner(System.in);
        System.out.println("Introduceti suma de transferat: ");
        Float amount = scAux.nextFloat();

        Account account1 = DataBase.getAccountFromIBAN(IBAN1);
        Account account2 = DataBase.getAccountFromIBAN(IBAN2);

        if(account1 instanceof CurrentAccount && account2 instanceof CurrentAccount) {
            ((CurrentAccount) account1).getCreditCard().transfer(((CurrentAccount) account2), amount);
        }
    }

    public void pay(String IBAN) {
        Account account = DataBase.getAccountFromIBAN(IBAN);
        if(account instanceof CurrentAccount) {
            Scanner scAux = new Scanner(System.in);
            System.out.println("Introduceti PIN-ul: ");
            String PIN = scAux.nextLine();
            System.out.println("Introduceti suma de achitat: ");
            Float amount = scAux.nextFloat();
            ((CurrentAccount) account).getCreditCard().pay(PIN, amount);
        }
        else {
            System.out.println("Facturile online se pot plati doar cu cardul!");
        }
    }

    public void printTransactionLogs(String IBAN) {
        Account account = DataBase.getAccountFromIBAN(IBAN);
        account.printTransactionLogs();
    }
    
}
