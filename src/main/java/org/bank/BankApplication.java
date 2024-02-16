package org.bank;

import org.bank.model.BankAccount;
import org.bank.util.InputValidator;

public class BankApplication {
    private static final BankAccount bankAccount = new BankAccount();
    private static final InputValidator validator = new InputValidator();

    public static void main(String[] args) {
        boolean exit = false;

        System.out.println("Добро пожаловать в приложение \"Банковский счет\"!");

        while (!exit){
            System.out.print("==================================\n" +
                    "1. Пополнить счет\n" +
                    "2. Снять деньги\n" +
                    "3. Проверить баланс\n" +
                    "4. История транзакций\n" +
                    "5. Выход\n" +
                    "Выберите действие (введите номер): ");

            int choice = validator.integerInput();

            switch (choice) {
                case 1:
                    System.out.print("Введите сумму для пополнения: ");
                    bankAccount.deposit(validator.doubleInput());
                    break;

                case 2:
                    System.out.print("Введите сумму для снятия: ");
                    bankAccount.withdraw(validator.doubleInput());
                    break;

                case 3:
                    System.out.println("Баланс на счете: " + bankAccount.getBalance() + " руб.");
                    break;

                case 4:
                    System.out.println("История транзакций:");
                    bankAccount.printTransactions(bankAccount.getTransactions());
                    break;

                case 5:
                    exit = true;
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("Некорректный ввод. Пожалуйста, выберите действие снова.");
            }
        }
    }
}