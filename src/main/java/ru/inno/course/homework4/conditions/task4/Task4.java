package ru.inno.course.homework4.conditions.task4;

public class Task4 {

    public static void main(String[] args) {
        String url = "https://vk.com";
        if (url.startsWith("https")) {
            System.out.println("Соединение безопасное");
        } else {
            System.out.println("Небезопасно. Не указывайте логины, пароли и данные банковских карт");
        }
    }
}
