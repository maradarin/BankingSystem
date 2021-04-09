package SystemManagement;

import UtilityFunctions.CodeGenerators;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

// Aceasta clasa nu va fi instantiata niciodata
// Desi alte 2 clase o mostenesc, mereu vom
// sti cand sa instantiem una din cele 2 tipuri
// mostenite
abstract public class Account {
    // Atribute final, odata ce acestea au fost generate,
    // nu vor mai putea fi modificate
    private final Long accountNumber;
    private final String IBAN;
    private String currency;
    private float currentBalance;

    private User user;
    private Bank bank;

    private List<AccountStatement> accountStatements = new ArrayList<AccountStatement>();
    private Set<TransactionLog> transactionLogs = new TreeSet<>(new TransactionComparator());

    public Account(User user, Bank bank) {
        this.bank = bank;
        this.user = user;
        this.currentBalance = 0;
        this.currency = "RON";
        this.accountNumber = CodeGenerators.generateAccountNumber(this.bank.getAccountNumbers());

        // Se salveaza conturile deschise la fiecare banca in functie de numarul
        // de cont (cheie primara)
        this.bank.addAccountNumber(this.accountNumber);

        Integer randomCheck = ThreadLocalRandom.current().nextInt(100, 999 + 1);
        this.IBAN = DataBase.getCountryCode(this.bank.country) + randomCheck.toString() + this.bank.getBIC() + this.accountNumber;
    }

    // Getter pentru bilant
    protected float getCurrentBalance() {
        return currentBalance;
    }

    // Setter pentru bilant
    public void setCurrentBalance(float currentBalance) {
        this.currentBalance = currentBalance;
    }

    // Getter pentru IBAN
    protected String getIBAN() {
        return IBAN;
    }

    // Getter pentru valuta
    public String getCurrency() {
        return currency;
    }

    // Getter pentru banca la care e deschis contul
    protected Bank getBank() {
        return bank;
    }

    // Getter pentru user-ul care detine contul
    protected User getUser() {
        return user;
    }

    // Getter pentru extrasul de cont
    public List<AccountStatement> getAccountStatements() {
        return accountStatements;
    }

    protected void addAccountStatement(AccountStatement accountStatement) {
        this.accountStatements.add(accountStatement);
    }

    protected void printAccountStatements() {
        System.out.println("Data                   Detalii Tranzactie                   Debit                   Credit");
        for(AccountStatement accountStatement : this.accountStatements) {
            accountStatement.print();
        }
    }

    protected void addTransactionLog(TransactionLog transactionLog) {
        this.transactionLogs.add(transactionLog);
    }

    protected void printTransactionLogs() {
        for (TransactionLog transactionLog : this.transactionLogs) {
            transactionLog.print();
            System.out.println("_________________________________");
        }
    }

    // Metoda de depozitare bani in cont
    // Difera in functie de tipul de cont
    abstract protected void deposit(String PIN, float amount);

    // Metoda de retragere bani din cont
    // Difera in functie de tipul de cont
    abstract protected void withdraw(String PIN, float amount, String currentDate);
}
