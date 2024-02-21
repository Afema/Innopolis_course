package ru.inno.course.exam1.task2;

import java.util.Objects;

public class MoistureSensor {
    private int moisture;

    public MoistureSensor() {
        this.moisture = (int) (Math.random() * 100);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoistureSensor that = (MoistureSensor) o;
        return moisture == that.moisture;
    }

    @Override
    public int hashCode() {
        return Objects.hash(moisture);
    }

    public void setMoisture(int moisture) {
        this.moisture = moisture;
    }

    public int getMoisture() {
        return moisture;
    }

    @Override
    public String toString() {
        return "MoistureSensor{" +
                "moisture=" + moisture +
                '}';
    }
}
