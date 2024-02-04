package ru.inno.course.homework5.task3;

public class Movie {
    private String movieName;
    private double movieRating;
    private String movieGenre;
    private String movieCountry;
    private boolean hasOscar;

    public Movie(String movieName, double movieRating, String movieGenre, String movieCountry, boolean hasOscar) {
        this.movieName = movieName;
        if(movieRating < 0 || movieRating > 10) {
            throw new IllegalArgumentException("movieRating to be defined from 0 to 10");
        }
        this.movieRating = movieRating;
        this.movieGenre = movieGenre;
        this.movieCountry = movieCountry;
        this.hasOscar = hasOscar;
    }

    public Movie(String movieName, double movieRating, String movieGenre) {
        this.movieName = movieName;
        this.movieRating = movieRating;
        this.movieGenre = movieGenre;
    }

    public String getMovieInfo() {
        return movieName + " - " + movieGenre + " - " + movieRating;
    }
}
