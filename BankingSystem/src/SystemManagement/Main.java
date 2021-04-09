package SystemManagement;

import javax.xml.crypto.Data;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Service service = Service.launchService();
        service.loadAvailableBanks();
        service.loadAvailableClients();

        System.out.println(DataBase.getBankByNames("ING", "Romania"));
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

                    break;
                }
                case 2: {
                    sc.nextLine();
                    System.out.println("Codul IBAN: ");
                    String IBAN = sc.nextLine();
                    service.deposit(IBAN);

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

                    break;
                }
                case 4: {
                    sc.nextLine();
                    System.out.println("Introduceti primul cod IBAN: ");
                    String IBAN1 = sc.nextLine();
                    System.out.println("Introduceti al doilea cod IBAN: ");
                    String IBAN2 = sc.nextLine();
                    service.transfer(IBAN1, IBAN2);

                    break;
                }
                case 5: {
                    sc.nextLine();
                    System.out.println("Introduceti codul IBAN: ");
                    String IBAN = sc.nextLine();
                    service.pay(IBAN);
                    break;
                }
                case 6: {
                    service.dropDownBanks(false);

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
                        for(User client : bank.getClients()) {
                            String additional = "";
                            System.out.println(client.getName() + " - " + client.getSurname() + " - " + client.getCNP());
                            for(Account account : client.getAccounts()) {
                                if(account instanceof CurrentAccount) {
                                    additional = ((CurrentAccount) account).getCreditCard().getPIN();
                                }
                                System.out.println("    - " + account.getIBAN() + " - " + account.getCurrentBalance() + " - " + additional);
                            }
                            System.out.println("____________________________________");
                        }
                    }
                    else {
                        System.out.println("Banca nu a fost gasita!");
                    }
                    break;
                }
                case 8: {
                    sc.nextLine();
                    System.out.println("Codul IBAN: ");
                    String IBAN = sc.nextLine();
                    service.updatePIN(IBAN);
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

                    service.accountStatement(bankName, countryName, CNP);
                    break;
                }
                case 10: {
                    sc.nextLine();
                    System.out.println("Codul IBAN: ");
                    String IBAN = sc.nextLine();
                    service.printTransactionLogs(IBAN);
                    break;
                }
                case 11: {
                    terminateService = true;
                    break;
                }
            }
        } while (!terminateService);
    }
}
