package ru.inno.course.homework4.methods.task2;

public class MyProgram {

    public static void main(String[] args) {
        Car car1 = new Car("Volvo");
        System.out.println(car1.getCurrentSpeed());
        car1.speedUp(25);
        System.out.println(car1.getCurrentSpeed());
        car1.brake();
        System.out.println(car1.getCurrentSpeed());
        car1.brake();
        System.out.println(car1.getCurrentSpeed());
        car1.brake();
        System.out.println(car1.getCurrentSpeed());
    }
}
