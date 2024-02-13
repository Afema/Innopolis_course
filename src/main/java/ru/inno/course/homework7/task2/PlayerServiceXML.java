package ru.inno.course.homework7.task2;

import jakarta.xml.bind.*;
import ru.inno.course.homework7.task1.Player;
import ru.inno.course.homework7.task1.PlayerService;

import java.io.File;
import java.util.*;

public class PlayerServiceXML implements PlayerService {

    private Map<Integer, Player> players = new HashMap<>();

    public PlayerServiceXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(ListPlayers.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File xml = new File("src/main/resources/players.xml");
            ListPlayers currentPlayers = (ListPlayers) unmarshaller.unmarshal(xml);
            for (Player p : currentPlayers.getPlayerList()) {
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
        writeToXML();
        return newId;
    }

    @Override
    public Player deletePlayer(int id) {
        Player player = players.remove(id);
        writeToXML();
        return player;
    }

    @Override
    public int addPoints(int playerId, int points) {
        Player player = players.get(playerId);
        player.setPoints(player.getPoints() + points);
        writeToXML();
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

    private void writeToXML() {
        List<Player> playersList = new ArrayList<>();
        for (Map.Entry<Integer, Player> playerEntry : players.entrySet()) {
            playersList.add(playerEntry.getValue());
        }
        ListPlayers lp = new ListPlayers(playersList);
        try {
            File filePath = new File("src/main/resources/players.xml");
            JAXBContext context = JAXBContext.newInstance(ListPlayers.class, Player.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(lp, filePath);

        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }
    }
}