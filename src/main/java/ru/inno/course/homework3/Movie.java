package ru.inno.course.homework3;

public class Movie {
    private String movieName;
    private double movieRating;
    private String movieGenre;
    private String movieCountry;
    private boolean hasOscar;

    public Movie(String movieName, double movieRating, String movieGenre, String movieCountry, boolean hasOscar) {
        this.movieName = movieName;
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
