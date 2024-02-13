package ru.inno.course.homework7.task1;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class PlayerServiceJSON implements PlayerService {
    private Map<Integer, Player> players = new HashMap<>();

    public PlayerServiceJSON() {
        File file = new File("src/main/resources/players.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            Player[] playersArr = mapper.readValue(file, Player[].class);
            for (Player p : playersArr) {
                players.put(p.getId(), p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Player getPlayerById(int id) {
        return players.get(id);
    }

    @Override
    public Collection<Player> getPlayers() {
        return players.values();
    }

    @Override
    public int createPlayer(String nickname) {
        int newId = getNextId(players);
        Player newPlayer = new Player(newId, nickname, 10, false);
        players.put(newId, newPlayer);
        writeToJSON();
        return newId;
    }

    @Override
    public Player deletePlayer(int id) {
        Player player = players.remove(id);
        writeToJSON();
        return player;
    }

    @Override
    public int addPoints(int playerId, int points) {
        Player player = players.get(playerId);
        player.setPoints(player.getPoints() + points);
        writeToJSON();
        return player.getPoints();
    }

    private int getNextId(Map<Integer, Player> players) {
        Map.Entry<Integer, Player> entryWithMaxKey = null;
        for (Map.Entry<Integer, Player> currentEntry : players.entrySet()) {
            if (entryWithMaxKey == null || currentEntry.getKey().compareTo(entryWithMaxKey.getKey()) > 0) {
                entryWithMaxKey = currentEntry;
            }
        }
        return entryWithMaxKey == null ? 1 : entryWithMaxKey.getKey() + 1;
    }

    private void writeToJSON() {
        try {
            Path filePath = Path.of("src/main/resources/players.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(filePath.toFile(), players.values());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
