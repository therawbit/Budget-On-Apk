package np.com.sudarshandevkota.model;

public class NewTransaction {
    private TransactionType transactionType;
    private String senderOrReceiver;
    private double amount;

    public NewTransaction(TransactionType transactionType, String senderOrReceiver, double amount, String note, boolean pending) {
        this.transactionType = transactionType;
        this.senderOrReceiver = senderOrReceiver;
        this.amount = amount;
        this.note = note;
        this.pending = pending;
    }

    private String note;
    private boolean pending;



    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSenderOrReceiver() {
        return senderOrReceiver;
    }

    public void setSenderOrReceiver(String senderOrReceiver) {
        this.senderOrReceiver = senderOrReceiver;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
