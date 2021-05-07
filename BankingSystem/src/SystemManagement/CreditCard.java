package SystemManagement;

import java.text.ParseException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class CreditCard {
    private final String cardNumber;
    private String CVC;
    private String PIN;
    private String expirationDate;
    private float spendingLimit;

    private static boolean updatedPIN = false;

    private CurrentAccount currentAccount;

    public CreditCard(String cardNumber, String expirationDate, float spendingLimit, CurrentAccount currentAccount) {
        this.cardNumber = cardNumber;
        Integer CVCInt = ThreadLocalRandom.current().nextInt(100, 999 + 1);
        this.CVC = CVCInt.toString();
        Integer PINInt = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
        this.PIN = PINInt.toString();
        this.expirationDate = expirationDate;
        this.spendingLimit = spendingLimit;
        this.currentAccount = currentAccount;
    }

    protected String getPIN() {
        return PIN;
    }

    public void pay(String PIN, float amount) {
        if(PIN.equals(this.getPIN())) {
            float currentBalance = this.currentAccount.getCurrentBalance();
            if (currentBalance < amount) {
                System.out.println("Fonduri insuficiente!");
            } else {
                this.currentAccount.setCurrentBalance(currentBalance - amount);

                AccountStatement accountStatement = new AccountStatement("24-11-2020", "Cumparare POS", "Debit", amount);
                this.currentAccount.getAccountStatements().add(accountStatement);
                System.out.println("Achitarea a fost realizata cu succes!");
            }
        }
        else {
            System.out.println("Ati gresit PIN-ul!");
        }
    }

    public void transfer(CurrentAccount accountTransfer, float amount) {
        boolean canTransfer = false;

        // Daca ambele conturi sunt deschise la aceeasi banca, atunci
        // transferul poate fi efectuat prin intermediul unei aplicatii
        // bancare cu ajutorul numerelor de telefon asociate conturilor
        if(this.currentAccount.getBank().getBIC().equals(accountTransfer.getBank().getBIC())) {
            canTransfer = true;
        }
        else {
            Scanner scAux = new Scanner(System.in);
            System.out.println("Introduceti PIN-ul: ");
            String PIN = scAux.nextLine();

            if(PIN.equals(this.getPIN())) {
                canTransfer = true;
            }
            else {
                System.out.println("Ati gresit PIN-ul!");
            }
        }
        if(canTransfer == true) {
            float currentBalance = this.currentAccount.getCurrentBalance();
            float transferBalance = accountTransfer.getCurrentBalance();
            if (amount <= currentBalance) {
                accountTransfer.setCurrentBalance(transferBalance + amount);

                AccountStatement accountStatement = new AccountStatement("24-Nov-2020", "Transfer bancar", "Debit", amount);
                this.currentAccount.addAccountStatement(accountStatement);

                AccountStatement transferStatement = new AccountStatement("24-Nov-2020", "Incasare", "Debit", amount);
                accountTransfer.addAccountStatement(transferStatement);

                TransactionLog transactionLogSend = new TransactionLog(this.currentAccount, accountTransfer, "24 Nov 2020", "", amount);
                TransactionLog transactionLogReceive = new TransactionLog(transactionLogSend);
                transactionLogSend.setOperation("Transfer");
                transactionLogReceive.setOperation("Incasare");
                this.currentAccount.addTransactionLog(transactionLogSend);
                accountTransfer.addTransactionLog(transactionLogReceive);
            } else {
                System.out.println("Fonduri insuficiente!");
            }
        }
        else {
            System.out.println("Nu s-a putut realiza transferul!");
        }
    }

    protected void updatePIN(String PIN) {
        if(updatedPIN == false) {
            this.PIN = PIN;
            updatedPIN = true;
            System.out.println("PIN-ul cardului a fost modificat cu succes!");
        }
        else {
            System.out.println("PIN-ul cardului a fost modificat deja!");
        }
    }
}
