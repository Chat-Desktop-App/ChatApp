package gov.iti.jets.view;

import gov.iti.jets.database.dao.UserDao;
import gov.iti.jets.database.dao.UserDaoImpl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.Map;

public class StatsController {

    @FXML
    private PieChart statusPieChart;
    @FXML
    private PieChart countryPieChart;
    @FXML
    private PieChart genderPieChart;

    private UserDao userDao;

    public StatsController () {
        userDao = new UserDaoImpl();
    }
    @FXML
    void initialize() {
        System.out.println("StatsController initialized!");
        updateStatistics();
        statusPieChart.setLegendVisible(false);
        genderPieChart.setLegendVisible(false);
        countryPieChart.setLegendVisible(false);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> updateStatistics()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
   }

    private void updateStatistics() {
        new Thread(() -> {
            try {
                Map<String, Integer> statusData = userDao.getUserStatus();
                Map<String, Integer> genderData = userDao.getUserGender();
                Map<String, Integer> countryData = userDao.getUserCountry();
                Platform.runLater(() -> {
                    updatePieChart(statusPieChart, statusData);
                    updatePieChart(genderPieChart, genderData);
                    updatePieChart(countryPieChart, countryData);
                });

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }).start();
    }
    private void updatePieChart(PieChart chart, Map<String, Integer> data) {
        chart.getData().clear();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            chart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
    }
}
