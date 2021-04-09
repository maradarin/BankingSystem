package SystemManagement;

public class TransactionLog {
    private CurrentAccount senderAccount;
    private CurrentAccount receiverAccount;
    protected String dateOfTransaction;
    protected String operation;
    private float amountIssued;

    public float getAmountIssued() {
        return amountIssued;
    }

    public TransactionLog(CurrentAccount senderAccount, CurrentAccount receiverAccount, String dateOfTransaction, String operation, float amountIssued) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.dateOfTransaction = dateOfTransaction;
        this.operation = operation;
        this.amountIssued = amountIssued;
    }

    public TransactionLog(TransactionLog transactionLog) {
        this.senderAccount = transactionLog.senderAccount;
        this.receiverAccount = transactionLog.receiverAccount;
        this.dateOfTransaction = transactionLog.dateOfTransaction;
        this.operation = transactionLog.operation;
        this.amountIssued = transactionLog.amountIssued;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    protected void print() {
        System.out.println("Sender details: ");
        System.out.println(senderAccount.getIBAN());
        System.out.println(senderAccount.getUser().getName());
        System.out.println("Receiver details: ");
        System.out.println(receiverAccount.getIBAN());
        System.out.println(receiverAccount.getUser().getName());
        System.out.println("Transaction details: ");
        System.out.println("Transaction type: " + this.operation + " - " + this.amountIssued);
    }
}
