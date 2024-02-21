package ru.inno.course.playerService;

import java.io.IOException;

public class TestRunner {

    public static void main(String[] args) throws IOException {


        PlayerServiceTest testClass = new PlayerServiceTest();

        boolean test1 = testClass.addPlayerToCollection();
        if (test1) {
            System.out.println("Пройден тест Добавить нового игрока в существующую коллекцию");
        } else {
            System.err.println("Провален тест Добавить нового игрока в существующую коллекцию");
        }


        if (testClass.deletePlayer()) {
            System.out.println("Пройден тест Удалить игрока из списка");
        } else {
            System.err.println("Провален тест Удалить игрока из списка");
        }


        boolean test3 = testClass.newPlayerCreation();

        boolean test4 = testClass.deletePlayer();

        boolean test5 = testClass.addPlayerToCollection();

        boolean test6 = testClass.deletePlayer();

    }

}