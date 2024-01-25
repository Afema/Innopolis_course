package ru.inno.course.homework4.methods.task2;

public class Car {
    private String carModel;
    private int currentSpeed;

    public Car(String carModel) {
        this.carModel = carModel;
    }

    public int getCurrentSpeed() {
        return this.currentSpeed;
    }

    public void speedUp(int num) {
        this.currentSpeed = this.currentSpeed + num;
    }

    public void brake() {
        if (this.currentSpeed <= 10) {
            this.currentSpeed = 0;
        } else {
            this.currentSpeed = this.currentSpeed - 10;
        }
    }
}
