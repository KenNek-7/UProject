package ru.kennek7.uproject;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CSVParser {

    private static List<String[]> parse(String url) throws IOException {
        try {
            var fileReader = new FileReader(url);
            var csvReader = new CSVReader(fileReader);
            return csvReader.readAll();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HashMap<String, Team> parseTeams(String url) {
        var teams = new HashMap<String, Team>();
        try {
            var data = parse(url);

            if (data == null) {
                return teams;
            }

            int team_id = 0;
            for (var i = 1; i < data.size(); i++) {
                var teamName = data.get(i)[1];

                if (!teams.containsKey(teamName)) {
                    teams.put(teamName, new Team(team_id, teamName));
                }
                var team = teams.get(teamName);
                team.addPlayer(
                    new Player(
                        data.get(i)[0], team.getId(), data.get(i)[2],
                        Integer.parseInt(data.get(i)[3]),
                        Integer.parseInt(data.get(i)[4]),
                        Double.parseDouble(data.get(i)[5])
                    )
                );
                team_id++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return teams;
    }
}
