package ru.inno.course.homework3;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class MainArray {
    public static void main(String[] args) {

        //task1
        String[] todoList = {"do homework", "buy something", "get smile", "read the book", "present gift"};

        //task2
        BigDecimal result = new BigDecimal(Math.PI).setScale(5, RoundingMode.DOWN);
        double pi = result.doubleValue(); //3.14159

        BigDecimal result1 = new BigDecimal(Math.E).setScale(5, RoundingMode.DOWN);
        double e = result1.doubleValue(); //2.71828

        int one = 1;
        double[] array = {pi, e, one};

        for (double num : array) {
            System.out.println(num);
        }

        //task3

        Movie[] films = {
                new Movie("The Lord of the Rings: The Return of the King", 10.0, "fantasy", "New Zealand, the United States", true),
                new Movie("Die Hard", 9.1, "action movie", "USA", false),
                new Movie("The Gentlemen", 8.7, "crime")
        };

        //task4

        for(int i = 1; i <= 1000000000; i++) {
            System.out.println(i);
        }

        //task5

        for (int i = 0; i < films.length; i++) {
            System.out.println(films[i].getMovieInfo());
        }

        //task6

        String[] names = new String[10];
        for (int i = 0; i < 10; i++) {
            names[i] = "User" + i;
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(names[i]);
        }
    }


}
