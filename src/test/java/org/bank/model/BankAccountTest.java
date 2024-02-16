package org.bank.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    private BankAccount bankAccount;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp(){
        bankAccount = new BankAccount();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @ParameterizedTest
    @MethodSource("depositArguments")
    void deposit(Double depositAmount, Double expectedBalance, int transactionSize, String output) {
        bankAccount.deposit(depositAmount);
        String actualOutput = outputStream.toString();

        assertEquals(expectedBalance, bankAccount.getBalance());
        assertEquals(transactionSize, bankAccount.getTransactions().size());
        assertTrue(actualOutput.contains(output));
    }

    @ParameterizedTest
    @MethodSource("withdrawArguments")
    void withdraw(Double initialBalance, Double withdrawAmount, Double expectedBalance, int transactionSize, String output) {
        bankAccount.setBalance(initialBalance);
        bankAccount.withdraw(withdrawAmount);
        String actualOutput = outputStream.toString();

        assertEquals(expectedBalance, bankAccount.getBalance());
        assertEquals(transactionSize, bankAccount.getTransactions().size());
        assertTrue(actualOutput.contains(output));
    }

    private static Stream<Arguments> depositArguments(){
        return Stream.of(
                Arguments.of(10.0, 10.0, 1, "Счет успешно пополнен на 10.0 руб."),
                Arguments.of(10.2, 10.2, 1, "Счет успешно пополнен на 10.2 руб."),
                Arguments.of(500000.0, 500000.0, 1, "Счет успешно пополнен на 500000.0 руб."),
                Arguments.of(9.0, 0.0, 0, "Введите корректную сумму (от 10 до 500000 руб)"),
                Arguments.of(500001.0, 0.0, 0, "Введите корректную сумму (от 10 до 500000 руб)"),
                Arguments.of(-1.0, 0.0, 0, "Введите корректную сумму (от 10 до 500000 руб)")
        );
    }

    private static Stream<Arguments> withdrawArguments(){
        return Stream.of(
                Arguments.of(1000.0, 100.0, 900.0, 1, "Со счёта снято 100.0 руб. Баланс: 900.0 руб."),
                Arguments.of(1000.0, 1000.0, 0.0, 1, "Со счёта снято 1000.0 руб. Баланс: 0.0 руб."),
                Arguments.of(5000.0, 10000.0, 5000.0, 0, "На вашем счёте недостаточно средств!"),
                Arguments.of(1000.0, 5.0, 1000.0, 0, "Введите корректную сумму (от 10 до 500000 руб)")
        );
    }
}