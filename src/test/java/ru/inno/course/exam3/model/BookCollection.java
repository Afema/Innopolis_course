package ru.inno.course.exam3.model;

import ru.inno.course.exam3.helpers.PropsHelper;

import java.util.Arrays;
import java.util.Objects;

public class BookCollection {
    private String userId = PropsHelper.getUserId();
    private Book[] collectionOfIsbns;

    public BookCollection(String userId, Book[] collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }

    public BookCollection(Book[] collectionOfIsbns) {
        this.collectionOfIsbns = collectionOfIsbns;
    }


    public String getUserId() {
        return userId;
    }

    public Object[] getCollectionOfIsbns() {
        return collectionOfIsbns;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookCollection book)) return false;
        return Objects.equals(userId, book.userId) && Arrays.equals(collectionOfIsbns, book.collectionOfIsbns);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(userId);
        result = 31 * result + Arrays.hashCode(collectionOfIsbns);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "userId='" + userId + '\'' +
                ", collectionOfIsbns=" + Arrays.toString(collectionOfIsbns) +
                '}';
    }
}