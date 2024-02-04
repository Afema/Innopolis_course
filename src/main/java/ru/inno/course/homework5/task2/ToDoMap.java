package ru.inno.course.homework5.task2;

import java.util.HashMap;
import java.util.Map;

public class ToDoMap {
    public static void main(String[] args) {
        HashMap<Integer, String> toDoMap = new HashMap<Integer, String>();

        toDoMap.put(1, "do homework");
        toDoMap.put(2, "wash dishes");
        toDoMap.put(3, "go shopping");
        toDoMap.put(4, "buy the book");
        toDoMap.put(5, "make dinner");
        for (Map.Entry mp : toDoMap.entrySet()) {
            System.out.println("Задача " + mp.getKey() + ": " + mp.getValue());
        }
    }
}
