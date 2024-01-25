package ru.inno.course.homework4.methods.task1;

public class MyProgram {

    public static void main(String[] args) {
        Card card1 = new Card("1234 5678 9101 1121", "9999-12-31", "777", "1012");
        card1.getMaskedCardNumber();
        card1.getCardNumber("1012");
    }
}
