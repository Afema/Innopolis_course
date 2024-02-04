package ru.inno.course.homework5.task1;

public class MyProgram {
    public static void main(String[] args) {
        Player player1 = new Player(10001, "Jony", true);
        Player player2 = new Player(10001, "Jony", true);
        System.out.println(player1 == player2);
        System.out.println(player1.equals(player2));
    }
}
