package ru.inno.course.homework5.task4;

import ru.inno.course.homework3.Movie;

import java.util.ArrayList;
import java.util.List;

public class MyProgram {
    public static void main(String[] args) {
        List<Company> companies = new ArrayList<Company>();
        List<Movie> films1 = new ArrayList<Movie>();
        films1.add(new Movie("The Lord of the Rings: The Return of the King", 10.0, "fantasy", "New Zealand, the United States", true));
        films1.add(new Movie("Die Hard", 9.6, "action movie", "USA", false));
        films1.add(new Movie("The Gentlemen", 8.7, "crime"));

        List<Movie> films2 = new ArrayList<Movie>();
        films2.add(new Movie("Home Alone", 8.5, "fantasy", "New Zealand, the United States", true));
        films2.add(new Movie("Die Hard - 2", 9.6, "action movie", "USA", false));
        films2.add(new Movie("RocknRolla", 8.7, "crime"));

        List<Movie> films3 = new ArrayList<Movie>();
        films3.add(new Movie("Home Alone - 2 ", 7.7, "fantasy", "New Zealand, the United States", true));
        films3.add(new Movie("Die Hard - 3", 8.6, "action movie", "USA", false));
        films3.add(new Movie("Lock, Stock and Two Smoking Barrels", 6.8, "crime"));

        companies.add(new Company("comany1", "2000-01-01", films1));
        companies.add(new Company("comany2", "2014-01-01", films2));
        companies.add(new Company("comany3", "2020-01-01", films3));

        for (Company c : companies) {
            System.out.println(c.getTitle() + " " + c.getFoundationYear() + ":");
            for (Movie m : c.getFilms()) {
                System.out.println(m.getMovieInfo());
            }
        }
    }
}
