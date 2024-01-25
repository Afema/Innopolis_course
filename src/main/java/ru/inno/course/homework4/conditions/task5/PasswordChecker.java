package ru.inno.course.homework4.conditions.task5;

import java.util.Scanner;

public class PasswordChecker {

    public static void main(String[] args) {

        while (true) {
            char[] specChars = {'!', '@', '#', '$', '%', '^', '&', '*', '№'};
            System.out.println("Введите пароль:");
            Scanner console = new Scanner(System.in);
            String password = console.nextLine();
            boolean hasDigit = checkDigit(password);
            boolean hasChars = checkSpecChars(password, specChars);

            if (password.length() < 8) {
                System.out.println("Пароль не менее 8 символов");
            } else if (!hasDigit) {
                System.out.println("Пароль должен содержать минимум 1 цифру");
            } else if (!hasChars) {
                System.out.println("Пароль должен содержать минимум 1 спецсимвол");
            } else {
                System.out.println("Пароль принят");
                break;
            }
        }
    }

    static boolean checkDigit(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    static boolean checkSpecChars(String input, char[] specChars) {
        for (int i = 0; i < specChars.length; i++) {
            if (input.indexOf(specChars[i]) >= 0) {
                return true;
            }
        }
        return false;
    }
}
