package ru.inno.course.homework7.task1;


import java.io.IOException;
import java.util.Collection;

public class TestClassJSON {
    public static void main(String[] args) throws IOException {
        PlayerService service = new PlayerServiceJSON();

        int playerId1 = service.createPlayer("WinMaster_900");
        int playerId2 = service.createPlayer("WinMaster_901");
        int playerId3 = service.createPlayer("WinMaster_773");
        int playerId4 = service.createPlayer("WinMaster_784");
        int playerId5 = service.createPlayer("WinMaster_785");
        int playerId17 = service.createPlayer("Lovely");

        service.deletePlayer(playerId5);

        service.addPoints(playerId1, 100);

        Collection<Player> players = service.getPlayers();
        for (Player player : players) {
            System.out.println(player);
        }
    }
}
