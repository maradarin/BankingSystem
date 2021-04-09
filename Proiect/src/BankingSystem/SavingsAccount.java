package BankingSystem;

import UtilityFunctions.ProcessingDates;

public class SavingsAccount extends Account {
    String period;          // 3 months, 2 years etc.
    float interestRate;
    String openDate;
    Boolean isJoint;

    User jointUser;

    public SavingsAccount(User client, Bank bank) {
        super(client, bank);
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public void setJoint(Boolean joint) {
        isJoint = joint;
    }

    public void setJointUser(User jointUser) {
        if (this.isJoint == true) {
            this.jointUser = jointUser;
        } else {
            System.out.println("This is not a join savings account!");
        }
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public void setAccountOptions(String period, int spanOfPeriod, float interestRate, String openDate, Boolean isJoint) {
        this.setPeriod(period);
        this.setInterestRate(interestRate);
        this.setOpenDate(openDate);
        this.setJoint(isJoint);

        System.out.println("The account's preferences have been saved!");
    }

    // In cazul unui cont de economii, extragerea se poate
    // face la data scadenta a contului, pentru a nu se
    // pierde dobanda. Altfel, se aplica o penalizare
    public void withdraw(String PIN, float amount, String currentDate) {
        String finalDate = ProcessingDates.computeFinalDay(currentDate, this.period);
        if(finalDate.compareTo(currentDate) < 0) {
            System.out.println("Daca retrageti bani inainte de data scadenta a contului, va veti pierde dobanda");
        }
        else if(finalDate == currentDate) {
            if (amount <= this.getCurrentBalance()) {
                this.setCurrentBalance(this.getCurrentBalance() - amount);

                AccountStatement accountStatement = new AccountStatement(this, currentDate, "Retragere numerar", "Debit", amount);
                this.addAccountStatement(accountStatement);
                System.out.println("Retragere realizata cu succes!");
            } else {
                System.out.println("Fonduri insuficiente!");
            }
        }
    }

    protected void extendContract(String period, float interestRate, String currentDate) {
        this.openDate = currentDate;
        this.period = period;
        this.interestRate = interestRate;
    }

    protected void deposit(String PIN, float amount) {
        this.setCurrentBalance(this.getCurrentBalance() + amount);
        System.out.println("Contul a fost alimentat cu suma alocata si are: "  + this.getCurrentBalance());
    }
}