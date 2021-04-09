package BankingSystem;

import java.util.Date;

public class AccountStatement {     // Extras de cont
    private Account account;
    protected String date;
    protected String operation;
    protected String type;
    private float amount;

    public AccountStatement(Account account, String date, String operation, String type, float amount) {
        this.account = account;
        this.date = date;
        this.operation = operation;
        this.type = type;
        this.amount = amount;
    }

    protected void print() {
        if(this.type == "Debit") {
            System.out.println(this.date + "               " + this.operation + "                   " + this.amount);
        }
        else {
            System.out.println(this.date + "               " + this.operation + "                   " + this.amount);
        }
    }
}
