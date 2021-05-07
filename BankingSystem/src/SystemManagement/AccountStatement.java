package SystemManagement;

import java.util.Date;

public class AccountStatement {     // Extras de cont
    //private Account account;
    private String date;
    private String operation;
    private String type;
    private float amount;

    public AccountStatement(String date, String operation, String type, String amountS) {
        this.date = date;
        this.operation = operation;
        this.type = type;
        this.amount = Float.parseFloat(amountS);
    }

    public AccountStatement(String date, String operation, String type, float amount) {
        //this.account = account;
        this.date = date;
        this.operation = operation;
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public float getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getOperation() {
        return operation;
    }

    protected void print() {
        System.out.println(this.date + "               " + this.operation + "                   " + this.amount);
    }
}
