package ru.inno.course.exam1.task2;

import java.time.LocalDate;

public class WaterPlants {

    public static void main(String[] args) {
        WaterPlants waterPlants = new WaterPlants();
        waterPlants.getWaterDate(LocalDate.of(2024, 2, 1));
    }

    public void getWaterDate(LocalDate lastWaterDate) {
        MoistureSensor m = new MoistureSensor();
        int moisture = m.getMoisture();
        LocalDate currentDate = LocalDate.now();
        LocalDate nextWaterDate = null;
        Season season = Season.of(currentDate.getMonth());

        if (moisture > 30) {
            if (season == Season.WINTER) {
                nextWaterDate = lastWaterDate.plusMonths(1);
                System.out.println("полив раз в месяц: " + nextWaterDate);
            } else if (season == Season.FALL || season == Season.SPRING) {
                nextWaterDate = lastWaterDate.plusDays(7);
                System.out.println("полив раз в неделю: " + nextWaterDate);
            } else {
                System.out.println("Пока не нужно поливать");
            }
        } else {
            if (season == Season.SUMMER) {
                nextWaterDate = lastWaterDate.plusDays(2);
                System.out.println("полив не чаще 1 раза в 2 дня: " + nextWaterDate);
            } else {
                nextWaterDate = currentDate;
                System.out.println("Нужно полить кактус сегодня: " + nextWaterDate);
            }
        }
    }
}
