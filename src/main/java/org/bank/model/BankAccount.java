package org.bank.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private double balance;
    private final List<Transaction> transactions;

    public BankAccount() {
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount){
        if (amount < 10 || amount > 500000){
            System.out.println("Введите корректную сумму (от 10 до 500000 руб)");
        }
        else {
            balance +=amount;
            transactions.add(new Transaction(TransactionType.DEPOSIT, amount, LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
            System.out.println("Счет успешно пополнен на " + amount + " руб.");
        }
    }

    public void withdraw(double amount){
        if (amount > balance){
            System.out.println("На вашем счёте недостаточно средств!");
        }
        else if (amount < 10 || amount > 500000 ){
            System.out.println("Введите корректную сумму (от 10 до 500000 руб)");
        }
        else {
          balance -= amount;
          transactions.add(new Transaction(TransactionType.WITHDRAWAL, amount, LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
          System.out.println("Со счёта снято " + amount + " руб. Баланс: " + balance + " руб.");
        }
    }

    public void printTransactions(List<Transaction> transactions){
        if (transactions.isEmpty()){
            System.out.println("История транзакций пуста");
        }
        else {
            transactions.forEach(tr -> System.out.println(transactions.indexOf(tr) + 1 + ". " +
                    (tr.getTransactionType().equals(TransactionType.DEPOSIT) ? "Пополнение: " : "Снятие: ") +
                    (tr.getTransactionType().equals(TransactionType.DEPOSIT) ? "+" : "-") +
                    tr.getCashAmount()+ " руб" +
                    " " + "(" + tr.getDate() + ")"));
        }
    }

    public double getBalance(){
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
