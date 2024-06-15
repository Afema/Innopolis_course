package ru.inno.course.exam3.model;

public class Book {

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    private String isbn;

    public Book(String isbn) {
        this.isbn = isbn;
    }
}
