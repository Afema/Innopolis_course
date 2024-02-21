package ru.inno.course.exam1.task2;

import java.time.Month;


public enum Season {
    SPRING, SUMMER, FALL, WINTER;

    static public Season of(final Month month) {
        switch (month) {

            case MARCH, APRIL, MAY:
                return Season.SPRING;

            case JUNE, JULY, AUGUST:
                return Season.SUMMER;


            case SEPTEMBER, OCTOBER, NOVEMBER:
                return Season.FALL;

            case DECEMBER, JANUARY, FEBRUARY:
                return Season.WINTER;

            default:
                System.out.println("ERROR.");
                return null;
        }
    }
}
