package ru.inno.course.homework5.task2;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    public static void main(String[] args) {
        List<String> toDoList = new ArrayList<String>();
        toDoList.add("do homework");
        toDoList.add("wash dishes");
        toDoList.add("go shopping");
        toDoList.add("buy the book");
        toDoList.add("make dinner");
        for (int i = 1; i <= toDoList.size(); i++) {
            System.out.println("Задача " + i + " : " + toDoList.get(i - 1));
        }
    }
}
