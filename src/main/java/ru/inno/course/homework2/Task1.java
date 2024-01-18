package ru.inno.course.homework2;

public class Task1 {
    public static void main(String[] args) {
        String cardNumber = "1234 5678 9012 3456";
        String visibleCode = cardNumber.substring((cardNumber.length() - 4));
        System.out.println("**** **** **** " + visibleCode);
    }
}
