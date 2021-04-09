package SystemManagement;

import UtilityFunctions.CodeGenerators;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CurrentAccount extends Account {
    private CreditCard creditCard;

    public CurrentAccount(User client, Bank bank) {
        super(client, bank);

        // Deschiderea unui cont curent presupune atasarea automata a unui
        // card de credit pentru care vom genera un cod de 15 cifre
        Long cardNumber = CodeGenerators.generateCardNumber(this.getBank().getAccountNumbers());
        this.creditCard = new CreditCard(cardNumber.toString(), "", 0f, this);
    }

    protected CreditCard getCreditCard() {
        return creditCard;
    }

    // In cazul unui cont curent, extragerea se poate
    // face prin intermediul unui ATM cu ajutorul
    // cardului de credit
    protected void withdraw(String PIN, float amount, String currentDate) {
        if(this.creditCard.getPIN().equals(PIN)) {
            if (amount <= this.getCurrentBalance()) {
                this.setCurrentBalance(this.getCurrentBalance() - amount);

                // Tranzactia va fi salvata in extrasul de cont
                AccountStatement accountStatement = new AccountStatement(this, currentDate, "Retragere numerar", "Debit", amount);
                this.addAccountStatement(accountStatement);
                System.out.println("Retragere realizata cu succes! Mai aveti in cont: " + this.getCurrentBalance() + " " + this.getCurrency());
            } else {
                System.out.println("Fonduri insuficiente!");
            }
        }
        else {
            System.out.println("Ati introdus PIN-ul gresit!");
        }
    }

    // Contul curent poate fi alimentat la un punct ATM
    // care ofera servicii de Self - Banking
    protected void deposit(String PIN, float amount) {
        if(this.creditCard.getPIN().equals(PIN)) {
            this.setCurrentBalance(this.getCurrentBalance() + amount);
            System.out.println("Contul a fost alimentat cu suma alocata si are: "  + this.getCurrentBalance());
        }
        else {
            System.out.println("Ati introdus PIN-ul gresit");
            System.out.println("Contul are suma: "  + this.getCurrentBalance());
        }
    }
}
