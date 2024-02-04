package ru.inno.course.homework5.task5;

import ru.inno.course.homework5.task1.Player;

import java.util.HashSet;

public class PlayerStorage {
    public static void main(String[] args) {
        HashSet<Player> players = new HashSet<>();
        Player player1 = new Player(10001, "Jony", true);
        Player player2 = new Player(10002, "Dragon", false);
        Player player3 = new Player(10003, "Leo134", false);
        Player player4 = new Player(10004, "Nick", false);
        Player player5 = new Player(10005, "Ronald_0909", true);
        Player player6 = new Player(10006, "BigMama", false);
        Player player7 = new Player(10007, "Bananas", true);
        Player player8 = new Player(10008, "Bilbo", false);
        Player player9 = new Player(10009, "mAlinkA", true);
        Player player10 = new Player(10010, "laVbit11", true);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        players.add(player6);
        players.add(player7);
        players.add(player8);
        players.add(player9);
        players.add(player10);
        players.add(player10);
    }
}