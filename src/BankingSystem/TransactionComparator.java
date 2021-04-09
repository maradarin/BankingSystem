package BankingSystem;

import java.util.Comparator;

public class TransactionComparator implements Comparator<TransactionLog> {
    @Override
    public int compare(TransactionLog o1, TransactionLog o2) {
        return o1.getAmountIssued() <= o2.getAmountIssued() ? o1.toString().compareTo(o2.toString()) : 1;
    }
}
