package ru.inno.course.homework5.task6;

import ru.inno.course.homework5.task1.Player;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PointsMap {
    public static void main(String[] args) {
        Map<Player, Integer> points = new HashMap<>();
        Player player1 = new Player(1, "Jony", true);
        Player player2 = new Player(2, "Dragon", false);
        Player player3 = new Player(3, "Leo134", false);
        Player player4 = new Player(4, "Nick", false);
        Player player5 = new Player(5, "Ronald_0909", true);
        Player player6 = new Player(6, "BigMama", false);
        Player player7 = new Player(7, "Bananas", true);
        Player player8 = new Player(8, "Bilbo", false);
        Player player9 = new Player(9, "mAlinkA", true);
        Player player10 = new Player(10, "laVbit11", true);
        points.put(player1, 0);
        points.put(player2, 0);
        points.put(player3, 0);
        points.put(player4, 0);
        points.put(player5, 0);
        points.put(player6, 0);
        points.put(player7, 0);
        points.put(player8, 0);
        points.put(player9, 0);
        points.put(player10, 0);

        points.replace(player4, 10);
        points.replace(player7, 12);
        points.replace(player8, 11);
        points.replace(player9, 13);
        points.replace(player10, 5);

        Stream<Map.Entry<Player, Integer>> sorted = points.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(3);
        sorted.forEach(System.out::println);
    }
}
