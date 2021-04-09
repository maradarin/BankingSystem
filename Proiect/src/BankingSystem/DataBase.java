package BankingSystem;

import jdk.jshell.execution.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

abstract public class DataBase {
    private static List<Bank> banks = new ArrayList<Bank>();
    protected static HashMap<String, String> countryCodes = new HashMap<String, String>() {{
        put("Afghanistan", "AF");
        put("Albania", "AL");
        put("Algeria", "DZ");
        put("Argentina", "Ar");
        put("Armenia", "AM");
        put("Australia", "AU");
        put("Austria", "AT");
        put("Belgium", "BE");
        put("Brazil", "BR");
        put("Bulgaria", "BG");
        put("Canada", "CA");
        put("China", "CN");
        put("France", "FR");
        put("Germany", "DE");
        put("Romania", "RO");
        put("Spain", "ES");
        put("USA", "US");
    }};

    public static List<Bank> getBanks() {
        return banks;
    }

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

    public static void addBank(Bank bank) {
        DataBase.banks.add(bank);
    }

    public static String getCountryCode(String country) {
        return countryCodes.get(country);
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
        boolean found = true;
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
