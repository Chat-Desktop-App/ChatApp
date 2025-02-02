package gov.iti.jets.view;

import gov.iti.jets.RMIConnector;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class StatusController {
    @FXML
    private Label statusLabel;
    @FXML
    private ImageView switchOffImage;
    @FXML
    private ImageView switchOnImage;

    @FXML
    public void initialize() {
        updateUI();
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        switchOnImage.setOnMouseClicked(event -> startServer());
        switchOffImage.setOnMouseClicked(event -> stopServer());
    }

    private void startServer() {
        RMIConnector.startServer();
        updateUI();
    }

    private void stopServer() {
        RMIConnector.stopServer();
        updateUI();
    }

    private void updateUI() {
        boolean isRunning = RMIConnector.isServerRunning();
        Platform.runLater(() -> {
            statusLabel.setText(isRunning ? "Server is ON" : "Server is OFF");
            switchOnImage.setDisable(isRunning);
            switchOffImage.setDisable(!isRunning);
        });
    }

}
