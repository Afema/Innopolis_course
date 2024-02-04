package ru.inno.course.homework5.task3;

import java.util.ArrayList;
import java.util.List;

public class MyProgram {
    public static void main(String[] args) {
        List<Movie> films = new ArrayList<>();
        try {
            films.add(new Movie("The Lord of the Rings: The Return of the King", 11.0, "fantasy", "New Zealand, the United States", true));
            films.add(new Movie("Die Hard", 9.6, "action movie", "USA", false));
            films.add(new Movie("The Gentlemen", 8.7, "crime"));
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to create MyClass instance: " + e.getMessage());
        }
        for(Movie movie: films) {
            System.out.println(movie.getMovieInfo());
        }

        //вариант по индексу
        for(int i = 0; i < films.size(); i++) {
            System.out.println(films.get(i).getMovieInfo());
        }
    }
}
