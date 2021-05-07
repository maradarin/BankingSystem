package SystemManagement;

import jdk.jshell.execution.Util;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class DataBase {
    private static List<Bank> banks = new ArrayList<Bank>();
    private static List<User> users = new ArrayList<User>();
    protected static HashMap<String, String> countryCodes = new HashMap<String, String>();

    public static List<Bank> getBanks() {
        return banks;
    }
    public static List<User> getUsers() { return users; }
    public static HashMap<String, String> getCountryCodes() { return countryCodes; }

    public static Bank getBankByNames(String name, String country) {
        Bank bank = banks.stream()
                .filter(bankAux -> name.equals(bankAux.name))
                .filter(bankAux -> country.equals(bankAux.country))
                .findAny()
                .orElse(null);

        return bank;
    }

    public static User findClientByCNP(String bankName, String countryName, String CNP) {
        Bank bank = getBankByNames(bankName, countryName);
        List<User> clients = bank.getClients();
        User user = clients.stream()
                .filter(client -> CNP.equals(client.getCNP()))
                .findAny()
                .orElse(null);

        return user;
    }

    public static User findClientByCNP(String CNP) {
        User user = users.stream()
                .filter(client -> CNP.equals(client.getCNP()))
                .findAny()
                .orElse(null);

        return user;
    }

    public static void addBank(Bank bank) {
        //System.out.println("Primit: " + bank.getName() + "\n");
        DataBase.banks.add(bank);
    }

    public static void addUser(User user) {
        DataBase.users.add(user);
    }

    // Metoda put va adauga noul cod de tara, daca acesta nu exista
    // sau il va modifica pe cel deja existent
    public static void updateCountryCode(String country, String code) {
        DataBase.countryCodes.put(country, code);
    }

    public static String getCountryCode(String country) {
        return countryCodes.get(country);
    }

    public static void printCountryCodes() {
        Map<String, String> map = new HashMap<String, String>(DataBase.getCountryCodes());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " - " + value);
        }
    }

    protected static Bank getBankFromIBAN(String IBAN) {
        String BIC = IBAN.substring(5, 13);
        Bank bank = DataBase.getBanks().stream()
                .filter(bankAux -> BIC.equals(bankAux.getBIC()))
                .findAny()
                .orElse(null);

        return bank;
    }

    protected static Account getAccountFromIBAN(String IBAN) {
        Bank bank = DataBase.getBankFromIBAN(IBAN);
        for(User user : bank.getClients()) {
            for(Account account : user.getAccounts()) {
                if(IBAN.equals(account.getIBAN())) {
                    return account;
                }
            }
        }

        return null;
    }
}
