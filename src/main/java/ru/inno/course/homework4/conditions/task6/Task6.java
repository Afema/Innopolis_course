package ru.inno.course.homework4.conditions.task6;

public class Task6 {

    public static void main(String[] args) {
        String password1 = "Qwerty0987654321";
        if (password1.equals("Qwerty0987654321")) {
            System.out.println("Доступ разрешен");
        } else {
            System.out.println("Доступ запрещен");
        }
    }
}