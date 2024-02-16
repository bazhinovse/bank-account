package org.bank.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {
    Scanner scanner = new Scanner(System.in);

    public int integerInput(){
        int value = 0;
        boolean validInput = false;

        while (!validInput){
            try {
                value = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка. Пожалуйста, введите число.");
                scanner.nextLine();
            }
        }

        return value;
    }

    public double doubleInput(){
        double value = 0.0;
        boolean validInput = false;

        while (!validInput){
            try {
                value = scanner.nextDouble();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка. Пожалуйста, введите число.");
                scanner.nextLine();
            }
        }

        return value;
    }
}
