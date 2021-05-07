package SystemManagement;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Bank {
    // Atribute final, se pot schimba doar prin inchiderea
    // sediului central, adica eliminarea definitiva a instantei
    // din baza de date
    protected final String country;
    protected final String name;
    protected final String BIC;

    // Salvam toate numerele conturilor deschise la o banca
    // pentru a ne asigura ca nu se vor genera 2 coduri
    // identice
    private Set<Long> accountNumbers = new LinkedHashSet<Long>();

    // Salvam clientii bancii respective
    private List<User> clients = new ArrayList<User>();

    // O banca este definita de numele ei, tara in care se afla si
    // codul BIC (Bank Identification Code)
    // Luam in considerare sediile centrale ale bancilor, de aceea
    // tara este un atribut relevant
    public Bank(String country, String name, String BIC) {
        this.country = country;
        this.name = name;
        this.BIC = BIC;

        // Adaugam in baza de date banca
        DataBase.addBank(this);
    }

    public String getBIC() {
        return BIC;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    // Getter pentru clienti
    protected List<User> getClients() {
        return clients;
    }

    public void printClients(GenericInterface<User> userReader, FileWriter csvWriterClient) throws IOException {
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
        csvWriterClient.append("_________________________________\n");
        csvWriterClient.append("Clientii bancii " + this.name + " la momentul: " + timeStamp + "\n");
        for (User client : this.getClients()) {
            String additional = "";
            System.out.println(client.getName() + " - " + client.getSurname() + " - " + client.getCNP());
            for(Account account : client.getAccounts()) {
                if(account instanceof CurrentAccount) {
                    additional = ((CurrentAccount) account).getCreditCard().getPIN();
                }
                userReader.writeToCSV(client, csvWriterClient);
                System.out.println("    - " + account.getIBAN() + " - " + account.getCurrentBalance() + " - " + additional);
            }
            System.out.println("____________________________________");
        }
    }

    // Getter pentru numerele de cont
    protected Set<Long> getAccountNumbers() {
        return accountNumbers;
    }

    // Metoda de adaugare a unui numar de cont
    protected void addAccountNumber(Long accountNumber) {
        this.accountNumbers.add(accountNumber);
    }

    // Metoda de adaugare a unui client
    protected void addClient(User client) {

        // Se face o verificare aditionala pt a vedea daca user-ul
        // de adaugat este deja client curent, sau nu
        try {
            User user = this.clients.stream().filter(clientAux -> clientAux.getCNP() == client.getCNP()).findAny().get();
        }
        catch (Exception e) {
            // Client nou, va fi adaugat
            this.clients.add(client);
        }
    }

    protected User findUser(String CNP) {
        User user = clients.stream()
                .filter(accountAux -> CNP.equals(accountAux.getCNP()))
                .findAny()
                .orElse(null);

        return user;
    }

    @Override
    public String toString() {
        return "Bank {" +
                "country= " + country +
                ", name=" + name +
                ", BIC=" + BIC +
                '}' + "\n";
    }
}
