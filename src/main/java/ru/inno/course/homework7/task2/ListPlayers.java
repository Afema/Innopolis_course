package ru.inno.course.homework7.task2;

import jakarta.xml.bind.annotation.*;
import ru.inno.course.homework7.task1.Player;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "players")

public class ListPlayers {
    private List<Player> playerList;

    public ListPlayers() {
        this.playerList = new ArrayList<Player>();
    }

    public ListPlayers(List<Player> playerList) {
        this.playerList = playerList;
    }

    @XmlElement(name = "player")
    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
