package SystemManagement;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String CNP;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String phoneNumber;

    // Un client poate avea conturi multiple
    private List<Account> accounts = new ArrayList<Account>();

    public User() {

    }

    // Constructor: contine datele personale + numele bancii si tara unde isi deschide cont
    public User(String cnp, String name, String surname, String dateOfBirth, String phoneNumber) {
        this.CNP = cnp;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;

        DataBase.addUser(this);
    }

    public User(String[] values) {
        this.CNP = values[0];
        this.name = values[1];
        this.surname = values[2];
        this.dateOfBirth = values[3];
        this.phoneNumber = values[4];

        DataBase.addUser(this);
    }

    protected String getCNP() {
        return CNP;
    }

    protected String getName() {
        return name;
    }

    protected String getDateOfBirth() {
        return dateOfBirth;
    }

    protected String getPhoneNumber() {
        return phoneNumber;
    }

    protected String getSurname() {
        return surname;
    }

    // Getter pentru lista de conturi
    protected List<Account> getAccounts() {
        return accounts;
    }

    // Actiune realizata de client: de a-si deschide cont
    public void openAccount(String type, String bankName, String countryName) {
        Bank bank;

        // Identificam instanta bancii unde isi deschide cont
        bank = DataBase.getBankByNames(bankName, countryName);

        // Verificarea daca acest user este deja client sau nu
        // se va face in metoda apelata
        bank.addClient(this);

        // Contul de deschis
        Account account;

        // Alegerea pachetului de servicii
        //Upcasting
        if(type.toLowerCase().equals("savings")) {
            // Cont de economii
            account = new SavingsAccount(this, bank);
        }
        else {
            // Cont curent
            account = new CurrentAccount(this, bank);
        }

        // Adaugam contul la lista de conturi deschise de utilizatorul curent
        this.accounts.add(account);
    }

    // Identificarea unui cont specific in functie de codul IBAN
    protected Account getAccount(String IBAN) {
        Account account = accounts.stream()
                .filter(accountAux -> IBAN.equals(accountAux.getIBAN()))
                .findAny()
                .orElse(null);

        return account;
    }

    @Override
    public String toString() {
        return "User{" +
                "CNP= " + CNP +
                ", name= " + name +
                ", surname= " + surname +
                ", dateOfBirth= " + dateOfBirth +
                ", phoneNumber= " + phoneNumber +
                '}' + "\n";
    }
}
