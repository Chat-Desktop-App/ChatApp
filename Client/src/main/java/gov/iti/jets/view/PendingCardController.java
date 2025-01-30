package gov.iti.jets.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PendingCardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button acceptButton;

    @FXML
    private ImageView friendIcon;

    @FXML
    private Label friendName;

    @FXML
    private Button rejectButton;

    @FXML
    void handleAccept_Invit(ActionEvent event) {

    }

    @FXML
    void handleReject_Invit(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert acceptButton != null : "fx:id=\"acceptButton\" was not injected: check your FXML file 'pendingCard.fxml'.";
        assert friendIcon != null : "fx:id=\"friendIcon\" was not injected: check your FXML file 'pendingCard.fxml'.";
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'pendingCard.fxml'.";
        assert rejectButton != null : "fx:id=\"rejectButton\" was not injected: check your FXML file 'pendingCard.fxml'.";

    }

}
