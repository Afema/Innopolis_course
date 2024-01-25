package ru.inno.course.homework4.conditions.task3;

public class Task3 {

    public static void main(String[] args) {
        int num1 = 12;
        if(num1%2 == 0) {
            System.out.print("Четное число.");
            if(num1%4 == 0) {
                System.out.println(" Кратно четырем.");
            }
        } else if(num1%3 == 0){
            System.out.println("Нечетное число. Кратно трем.");
        } else {
            System.out.println("Нечетное число.");
        }
    }
}
