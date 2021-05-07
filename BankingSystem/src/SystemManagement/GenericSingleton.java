package SystemManagement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

interface GenericInterface<T>{
    // Metoda este publica in mod implicit
    void readFromCSV(Class<T> type);

    void writeToCSV(AccountStatement accountStatement, FileWriter csvWriter) throws IOException;
    void writeToCSV(User user, FileWriter csvWriter) throws IOException;
}

@SuppressWarnings("unchecked")
final class GenericSingleton<T> {

    // constructor privat - nu permite instantierea multipla
    private GenericSingleton() {

    }

    // instanta unica - asigura conditia de singleton
    // va implementa metodele definite in interfata
    public static<T> GenericInterface instance() {
        return SingletonListenerInstance.INSTANCE;
    }

    private enum SingletonListenerInstance implements GenericInterface {
        // unic atribut al clasei enum
        INSTANCE;

        // Implementarea metodelor din interfata
        @Override
        public void readFromCSV(Class type) {
            String pathToFile = "";

            // salvam atributele folosite pt constructorii cu care vom
            // construi noile instante
            Class[] constructor = new Class[0];

            if (User.class.equals(type)) {
                pathToFile = "data/clients.csv";
                // constructorul clasei User are 5 parametri
                constructor = new Class[5];
                constructor[0] = String.class;
                constructor[1] = String.class;
                constructor[2] = String.class;
                constructor[3] = String.class;
                constructor[4] = String.class;
            }
            else if(Bank.class.equals(type)) {
                pathToFile = "data/banks.csv";
                // constructorul clasei Bank are 3 parametri
                constructor = new Class[3];
                constructor[0] = String.class;
                constructor[1] = String.class;
                constructor[2] = String.class;
            }
            else if(AccountStatement.class.equals(type)) {
                pathToFile = "data/accountStatements.csv";
                // constructorul clasei AccountStatement are 4 parametri
                constructor = new Class[4];
                constructor[0] = String.class;
                constructor[1] = String.class;
                constructor[2] = String.class;
                constructor[3] = String.class;
            }
            else if(Account.class.equals(type)) {
                pathToFile = "data/accounts.csv";
                // nu atribuim parametrii constructorului pt acest tip
                // deoarece constructorul e bazat pe alte 2 tipuri: User si Bank
            }

            // atribute finale definite pt procesarea in lambda-expresie
            final Class actualClass = type;
            final Class actualConstructor[] = constructor;

            try {
                // caz special pt datele despre conturi
                if(!Account.class.equals(type)) {
                    List<Object> dataList = Files.readAllLines(Paths.get(pathToFile)).stream()
                            .map(line -> {
                                try {
                                    //accesam constructorul parametrizat al clasei curente si generam o noua instanta
                                    return actualClass.getDeclaredConstructor(actualConstructor).newInstance(line.split(","));
                                } catch (InstantiationException e) {
                                    e.printStackTrace();
                                    return null;
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                    return null;
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                    return null;
                                } catch (NoSuchMethodException e) {
                                    e.printStackTrace();
                                    return null;
                                }
                            }).collect(Collectors.toList());

                    // daca citim date specifice extrasurilor de conturi (AccountStatement)
                    // trebuie sa le atribuim pe acestea unui cont
                    // le vom asocia pe toate primului cont existent, deschis la o banca
                    // este inregistrat minim un client
                    if (AccountStatement.class.equals(type)) {
                        Bank bank = DataBase.getBanks().stream()
                                .filter(bankAux -> bankAux.getClients().size() > 0)
                                .findAny()
                                .orElse(null);

                        Account account = bank.getClients().get(0).getAccounts().stream()
                                .filter(accountAux -> accountAux instanceof CurrentAccount).findAny().get();

                        for (Object obj : dataList) {
                            account.addAccountStatement((AccountStatement) obj);
                        }
                    }
                }
                else {
                    // Din fisierul csv citim: CNP-ul clientului, tipul contului, numele bancii si tara
                    List<String[]> lines = Files.readAllLines(Paths.get(pathToFile)).stream()
                            .map(line -> line.split(","))
                            .collect(Collectors.toList());

                    // contul putea fi instantiat pe baza constructorului
                    // dar acesta trebuie sa fie asociat unui client, nu poate exista independent
                    // de aceea apelarea constructorului parametrizat pt clasa Account
                    // va fi apelat in cadrul metodei openAccount atribuit clientului
                    // identificat pe baza CNP-ului citit din fisier
                    for(String[] line : lines) {
                        User client = DataBase.findClientByCNP(line[0]);
                        client.openAccount(line[1], line[2], line[3]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void writeToCSV(AccountStatement accountStatement, FileWriter csvWriter) throws IOException {
            csvWriter.append(accountStatement.getDate() + "               " + accountStatement.getOperation() + "                   " + accountStatement.getAmount() + "\n");
        }

        @Override
        public void writeToCSV(User user, FileWriter csvWriter) throws IOException {
            csvWriter.append(user.getName() + " " + user.getSurname() + " are conturile urmatoare:\n");

            for (Account account : user.getAccounts()) {
                csvWriter.append(account.getIBAN() + " - deschis la banca " + account.getBank().getName() + " din " +
                                 account.getBank().getCountry() + " si cu soldul curent: " + account.getCurrentBalance() + "\n");
            }
        }
    }
}