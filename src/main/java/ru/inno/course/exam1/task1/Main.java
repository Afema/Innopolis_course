package ru.inno.course.exam1.task1;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.lengthChecker(345);
    }

    public void lengthChecker(int fenceLength) {
        int confessionLength = 5*62 + 3*12;
        if (confessionLength <= fenceLength) {
            System.out.println("Надпись поместится на заборе");
        } else {
            System.out.println("Надпись не поместится на заборе");
        }
    }
}