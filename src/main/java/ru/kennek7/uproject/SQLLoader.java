package ru.kennek7.uproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.sqlite.SQLiteConfig;

public class SQLLoader {

    private Connection conn;
    private static final String dbPath = "jdbc:sqlite:Teams.db";

    public SQLLoader() {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        try {
            conn = DriverManager.getConnection(dbPath, config.toProperties());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        addTeamTable();
        addPlayerTable();
    }

    private void addTeamTable() {
        var query = """
            CREATE TABLE IF NOT EXISTS team(
                id integer primary key,
                name text UNIQUE
            );
            """;

        try {
            conn.createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addPlayerTable() {
        var query = """
            CREATE TABLE IF NOT EXISTS player (
                id integer PRIMARY KEY AUTOINCREMENT,
                name text,
                position text,
                height integer,
                weight integer,
                age real,
                team_id integer,
                FOREIGN KEY (team_id) REFERENCES team(id)
            );""";

        try {
            conn.createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTeam(Team team) {
        String sqlInsertTeam = "INSERT or IGNORE INTO team(id, name) VALUES(?,?)";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sqlInsertTeam);
            preparedStatement.setInt(1, team.getId());
            preparedStatement.setString(2, team.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (var player : team.getPlayers()) {
            addPlayer(player, team.getId());
        }
    }

    private void addPlayer(Player player, int teamId) {
        var query = "INSERT INTO player(name, position, height, weight, age, team_id) VALUES (?,?,?,?,?,?)";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, player.name());
            preparedStatement.setString(2, player.position());
            preparedStatement.setInt(3, player.height());
            preparedStatement.setInt(4, player.weight());
            preparedStatement.setDouble(5, player.age());
            preparedStatement.setInt(6, teamId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Постройте график по среднему возрасту во всех командах.
    public Map<String, Double> getTeamsByAvgAge() {
        var map = new HashMap<String, Double>();
        var query = """
            SELECT DISTINCT t.name, AVG(age) OVER (PARTITION BY team_id) FROM player p
            INNER JOIN team t ON t.id = p.team_id
            """;

        try {
            var rs = conn.createStatement().executeQuery(query);
            var rsC = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                map.put(rs.getString(1), rs.getDouble(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return map;
    }

    public List<String> getTopFivePlayerFromHigherTeam() {
        List<String> players = new ArrayList<>();

        var query = """
            SELECT DISTINCT t.id, t.name, avg(p.height) OVER (PARTITION BY team_id) FROM player p
            INNER JOIN team t ON t.id = p.team_id
            ORDER BY avg(p.height) OVER (PARTITION BY team_id) DESC
            """;

        try {
            var rs = conn.createStatement().executeQuery(query);
            var teamId = rs.getInt(1);

            rs = conn.createStatement().executeQuery("""
                                                         SELECT DISTINCT name FROM player
                                                         WHERE team_id = '""" + teamId + "'" + """
                                                         ORDER BY height DESC LIMIT 5
                                                         """
            );

            while (rs.next()) {
                players.add(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    // Найдите команду, с средним ростом равным от 74 до 78 inches
    // и средним весом от 190 до 210 lbs, с самым высоким средним возрастом.
    public String getHigherTeamWithH74_78W190_210() {
        var query = """
                SELECT DISTINCT t.name FROM player p
                INNER JOIN team t ON t.id = p.team_id
                WHERE p.id in (
                    SELECT DISTINCT id FROM player
                    WHERE 74 <= height AND height <= 78
                    ORDER BY avg(height) OVER (PARTITION BY team_id)
                ) AND p.id in (
                    SELECT DISTINCT id FROM player
                    WHERE 190 <= weight AND weight <= 210
                    ORDER BY avg(weight) OVER (PARTITION BY team_id)
                ) AND team_id in (
                    SELECT DISTINCT team_id FROM player
                    ORDER BY avg(age) OVER (PARTITION BY team_id) DESC LIMIT 1
                )
            """;
        try {
            return conn.createStatement().executeQuery(query).getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Не найдено";
    }
}
