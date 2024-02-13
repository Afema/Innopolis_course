package ru.inno.course.homework7.task2;


import ru.inno.course.homework7.task1.Player;
import ru.inno.course.homework7.task1.PlayerService;

import java.io.IOException;
import java.util.Collection;

public class TestClassXML {
    public static void main(String[] args) throws IOException {
        PlayerService service = new PlayerServiceXML();

        int playerId1 = service.createPlayer("WinMaster_771");
        int playerId2 = service.createPlayer("WinMaster_772");
        int playerId3 = service.createPlayer("WinMaster_773");
        int playerId4 = service.createPlayer("WinMaster_784");
        int playerId5 = service.createPlayer("WinMaster_785");
        int playerId9 = service.createPlayer("LowlyUser");

        service.deletePlayer(playerId5);

        service.addPoints(playerId1, 100);

        Collection<Player> players = service.getPlayers();
        for (Player player : players) {
            System.out.println(player);
        }
    }
}
