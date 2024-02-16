package org.bank.model;

public class Transaction {
    private final TransactionType transactionType;
    private final double cashAmount;
    private final String date;

    public Transaction(TransactionType transactionType, double cashAmount, String date) {
        this.transactionType = transactionType;
        this.cashAmount = cashAmount;
        this.date = date;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public String getDate() {
        return date;
    }
}
