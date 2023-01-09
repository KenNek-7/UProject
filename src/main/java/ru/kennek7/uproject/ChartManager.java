package ru.kennek7.uproject;

import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartManager {

    public static void saveLineChart(String name, String category, String value,
        DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart(name, category, value, dataset);

        try {
            ChartUtils.saveChartAsJPEG(new File("chart.jpg"), chart,
                1920, 1080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
