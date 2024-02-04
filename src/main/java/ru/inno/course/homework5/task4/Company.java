package ru.inno.course.homework5.task4;

import ru.inno.course.homework3.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company {
    public String getTitle() {
        return title;
    }

    public String getFoundationYear() {
        return foundationYear;
    }

    private String title;
    private String foundationYear;
    private List<Movie> films;

    public Company(String title, String foundationYear, List<Movie> films) {
        this.title = title;
        this.foundationYear = foundationYear;
        this.films = films;
    }

    public List<Movie> getFilms() {
        return this.films;
    }
}
