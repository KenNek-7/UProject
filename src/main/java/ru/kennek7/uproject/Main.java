package ru.kennek7.uproject;

import org.jfree.data.category.DefaultCategoryDataset;

public class Main {

    private static SQLLoader SQLLoader;

    public static void main(String[] args) {
        var teams = CSVParser.parseTeams("Показатели спортивных команд.csv");
        SQLLoader = new SQLLoader();

        for (var team : teams.values()) {
            SQLLoader.addTeam(team);
        }

        Task1();
        Task2();
        Task3();
    }

    public static void Task1() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (var set : SQLLoader.getTeamsByAvgAge().entrySet()) {
            dataset.addValue(set.getValue(), "age", set.getKey());
        }

        ChartManager.saveLineChart(
            "Cредний возраст команд", "Команды", "Возраст Игроков", dataset
        );
        System.out.println("График успешно создан!");
    }

    public static void Task2() {
        System.out.println(
            "Топ 5 самых высоких игроков из команды с самым высоким средним ростом: "
            + String.join(", ", SQLLoader.getTopFivePlayerFromHigherTeam())
        );
    }

    public static void Task3() {
        System.out.println(
            """
            Команда, с средним ростом равным от 74 до 78 inches и средним весом от 190 до 210 lbs,
            с самым высоким средним возрастом - """ + SQLLoader.getHigherTeamWithH74_78W190_210()
        );
    }
}