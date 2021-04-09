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

    // Constructor: contine datele personale + numele bancii si tara unde isi deschide cont
    public User(String cnp, String name, String surname, String dateOfBirth, String phoneNumber) {
        this.CNP = cnp;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;

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

        // Verificam daca User-ul este deja client al bancii unde vrea sa-si deschida cont
        boolean clientExists = false;
        for(User client : bank.getClients()) {
            if(client.getCNP() == this.getCNP()) {
                clientExists = true;
                break;
            }
        }

        // Daca este client nou, il adaugam in lista de clienti a bancii respective
        if(clientExists == false) {
            bank.addClient(this);
        }

        // Contul de deschis
        Account account;

        // Alegerea pachetului de servicii
        // Cont de economii
        if(type == "Savings") {
            //Upcasting
            account = new SavingsAccount(this, bank);
        }
        // Cont curent
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
}
