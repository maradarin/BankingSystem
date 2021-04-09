package BankingSystem;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CurrentAccount extends Account {
    private CreditCard creditCard;

    public CurrentAccount(User client, Bank bank) {
        super(client, bank);

        // Deschiderea unui cont curent presupune atasarea automata a unui
        // card de credit pentru care vom genera un cod de 15 cifre
        Long cardNumber = this.generateCardNumber();
        this.creditCard = new CreditCard(cardNumber.toString(), "", 0f, this);
    }

    protected CreditCard getCreditCard() {
        return creditCard;
    }

    protected Long generateCardNumber() {
        boolean isUnique = false;
        long maxKey = 0L;
        while(isUnique != true) {
            boolean next = false;
            maxKey = ThreadLocalRandom.current().nextLong(10000000000000L, 99999999999999L + 1);

            Iterator<Long> it = this.getBank().getAccountNumbers().iterator();
            while(it.hasNext()) {
                long maxKeylong = maxKey;
                if(it.next() == maxKeylong) {
                    next = true;
                    break;
                }
            }

            if(next == false) {
                isUnique = true;
                break;
            }
        }
        long reversedKey = 0L;
        long copyKey = maxKey;
        int sum = 0;
        boolean evenPos = true;
        while(copyKey != 0) {
            reversedKey = reversedKey * 10 + copyKey % 10;
            copyKey /= 10;
        }
        while(reversedKey != 0) {
            long digit = reversedKey % 10;
            if(evenPos == true) {
                digit = digit * 2;
                if(digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            reversedKey /= 10;
            evenPos = !evenPos;
        }

        long luhnKey = 0L;
        luhnKey = 4 * (10 ^ 16) +  maxKey * 10 + sum % 10;
        return luhnKey;
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
