package ru.inno.course.exam3.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropsHelper {

    private static final String CONFIG_FILE = "src/test/java/ru/inno/course/exam3/resources/config.properties";

    private static final Properties props = new Properties();

    static {
        try {
            props.load(new FileInputStream(CONFIG_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getLoginUrl() {
        return props.getProperty("login_url");
    }

    public static String getProfileUrl() {
        return props.getProperty("profile_url");
    }

    public static String getBooksUrl() {
        return props.getProperty("books_url");
    }

    public static String getBooksByUserIdUrl() {
        return props.getProperty("booksByUserId_url");
    }

    public static String getUserId() {
        return props.getProperty("userId");
    }

    public static String getLogin() {
        return props.getProperty("login");
    }

    public static String getPass() {
        return props.getProperty("pass");
    }

    public static String getBookStoreUrl() {
        return props.getProperty("bookStoreAdd_url");
    }
}
