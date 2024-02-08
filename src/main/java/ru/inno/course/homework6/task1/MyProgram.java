package ru.inno.course.homework6.task1;

import java.io.IOException;

public class MyProgram {
    public static void main(String[] args) {
        try {
            Copier.copyTextFile("C:\\Users\\oemelina\\IdeaProjects\\innopolis\\course\\src\\main\\resources\\sourceFile.txt", "C:\\Users\\oemelina\\IdeaProjects\\innopolis\\course\\src\\main\\resources\\receiverFile.txt");
        } catch(IOException e) {
            System.out.println("Что-то пошло не так");
        }
    }
}
