package ru.kennek7.uproject;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private final int id;
    private final String name;
    private final List<Player> players = new ArrayList<>();

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Player[] getPlayers() {
        return players.toArray(Player[]::new);
    }

    @Override
    public String toString() {
        return "Team{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", players=" + players +
            '}';
    }
}
