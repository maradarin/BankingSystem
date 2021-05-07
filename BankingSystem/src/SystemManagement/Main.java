package SystemManagement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        // unica instantiere a clasei de serviciu care
        // gestioneaza rezolvarea cerintelor din meniu
        Service service = Service.launchService();
        service.loadAvailableCountryCodes();

        // unica instanta de citire pt banca
        GenericInterface<Bank> bankReader = GenericSingleton.instance();
        bankReader.readFromCSV(Bank.class);
        // varianta din etapa 1
        //service.loadAvailableBanks();

        // unica instanta de citire a clientilor
        GenericInterface<User> userReader = GenericSingleton.instance();
        userReader.readFromCSV(User.class);
        // varianta din etapa 1
        //service.loadAvailableClients();

        // unica instanta de citire pt conturile clientilor
        GenericInterface<Account> accReader = GenericSingleton.instance();
        accReader.readFromCSV(Account.class);

        // unica instanta de citire a extraselor de conturi
        GenericInterface<AccountStatement> accStatementReader = GenericSingleton.instance();
        accStatementReader.readFromCSV(AccountStatement.class);

        File file = new File("auditLog.csv");
        FileWriter csvWriter = new FileWriter(file);

        File fileClient = new File("output/clients.csv");
        FileWriter csvWriterClient = new FileWriter(fileClient);

        File fileAccStatement = new File("output/accountStatements.csv");
        FileWriter csvWriterAccStatement = new FileWriter(fileAccStatement);

        // verificarea incarcarii datelor din banks.csv
        System.out.println(DataBase.getBankByNames("ING", "Romania"));
        // verificarea incarcarii datelor din clients.csv
        System.out.println(DataBase.findClientByCNP("ING", "Romania", "1234567890000"));

        boolean terminateService = false;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Alegeti optiunea din meniu");
            System.out.println("1. Deschideti un cont nou");
            System.out.println("2. Depuneti bani in cont");
            System.out.println("3. Retrageti bani din cont");
            System.out.println("4. Efectuati un transfer");
            System.out.println("5. Platiti o factura");
            System.out.println("6. Afisati bancile existente in baza de date");
            System.out.println("7. Afisati clientii inregistrati la o anumita banca");
            System.out.println("8. Setati un nou PIN");
            System.out.println("9. Afisati extrasul de cont");
            System.out.println("10. Afisati raportul tranzactiilor unui cont");
            System.out.println("11. EXIT");
            System.out.println();
            System.out.println("Optiunea aleasa: ");
            int option = sc.nextInt();
            switch (option) {
                case 1: {
                    sc.nextLine();
                    System.out.println("Alegeti o sucursala din meniul drop-down");
                    service.dropDownBanks(true);
                    System.out.print("Indicele surcursalei alese: ");
                    int selectedBank = sc.nextInt();

                    System.out.println("Introduceti CNP-ul clientului: ");
                    sc.nextLine();
                    String CNP = sc.nextLine();
                    service.openAccount(selectedBank, CNP);

                    service.writeInAuditLog(csvWriter, "Deschidere cont");

                    break;
                }
                case 2: {
                    sc.nextLine();
                    System.out.println("Codul IBAN: ");
                    String IBAN = sc.nextLine();
                    service.deposit(IBAN);

                    service.writeInAuditLog(csvWriter, "Depunere bani in cont");

                    break;
                }
                case 3: {
                    sc.nextLine();
                    System.out.println("Codul IBAN: ");
                    String IBAN = sc.nextLine();
                    Account account = DataBase.getAccountFromIBAN(IBAN);
                    String PIN = "";
                    if(account instanceof CurrentAccount) {
                        sc.nextLine();
                        System.out.println("Introduceti PIN-ul: ");
                        PIN = sc.nextLine();
                    }
                    System.out.println("Introduceti suma pe care doriti s-o retrageti: ");
                    float amount = sc.nextFloat();
                    service.withdraw(account, PIN, amount, "24-Nov-2020");

                    service.writeInAuditLog(csvWriter, "Retragere bani din cont");

                    break;
                }
                case 4: {
                    sc.nextLine();
                    System.out.println("Introduceti primul cod IBAN: ");
                    String IBAN1 = sc.nextLine();
                    System.out.println("Introduceti al doilea cod IBAN: ");
                    String IBAN2 = sc.nextLine();
                    service.transfer(IBAN1, IBAN2);

                    service.writeInAuditLog(csvWriter, "Transfer bancar");

                    break;
                }
                case 5: {
                    sc.nextLine();
                    System.out.println("Introduceti codul IBAN: ");
                    String IBAN = sc.nextLine();
                    service.pay(IBAN);

                    service.writeInAuditLog(csvWriter, "Achitare factura");

                    break;
                }
                case 6: {
                    service.dropDownBanks(false);

                    service.writeInAuditLog(csvWriter, "Afisarea bancilor existente in baza de date");

                    break;
                }
                case 7: {
                    sc.nextLine();
                    System.out.println("Numele bancii: ");
                    String bankName = sc.nextLine();
                    System.out.println("Tara bancii: ");
                    String countryName = sc.nextLine();
                    Bank bank = DataBase.getBankByNames(bankName, countryName);
                    if(bank != null) {
                        bank.printClients(userReader, csvWriterClient);

                        service.writeInAuditLog(csvWriter, "Afisarea clientilor inregistrati la banca " + bankName);
                    }
                    else {
                        System.out.println("Banca nu a fost gasita!");

                        service.writeInAuditLog(csvWriter, "Esuarea afisarii clientilor inregistrati la banca " + bankName);
                    }
                    break;
                }
                case 8: {
                    sc.nextLine();
                    System.out.println("Codul IBAN: ");
                    String IBAN = sc.nextLine();
                    service.updatePIN(IBAN);

                    service.writeInAuditLog(csvWriter, "Setarea unui nou PIN");

                    break;
                }
                case 9: {
                    sc.nextLine();
                    System.out.println("Numele bancii: ");
                    String bankName = sc.nextLine();
                    System.out.println("Numele tarii: ");
                    String countryName = sc.nextLine();

                    System.out.println("CNP-ul clientului: ");
                    String CNP = sc.nextLine();

                    service.printAccountStatement(bankName, countryName, CNP, accStatementReader, csvWriterAccStatement);

                    service.writeInAuditLog(csvWriter, "Afisarea extrasului de cont");

                    break;
                }
                case 10: {
                    sc.nextLine();
                    System.out.println("Codul IBAN: ");
                    String IBAN = sc.nextLine();
                    service.printTransactionLogs(IBAN);

                    service.writeInAuditLog(csvWriter, "Afisarea raportului de tranzactii");

                    break;
                }
                case 11: {
                    terminateService = true;

                    service.writeInAuditLog(csvWriter, "Iesirea din Meniu");
                    csvWriter.flush();
                    csvWriter.close();

                    csvWriterClient.flush();
                    csvWriterClient.close();

                    csvWriterAccStatement.flush();
                    csvWriterAccStatement.close();

                    break;
                }
            }
        } while (!terminateService);

    }
}
