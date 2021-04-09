package BankingSystem;

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
        this.clients.add(client);
    }

    protected User findUser(String CNP) {
        User user = clients.stream()
                .filter(accountAux -> CNP.equals(accountAux.getCNP()))
                .findAny()
                .orElse(null);

        return user;
    }
}
