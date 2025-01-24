package gov.iti.jets.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class allFriendController {

    @FXML
    private ImageView friendIcon;

    @FXML
    private Circle status;

    @FXML
    private Label friendName;

    @FXML
    private ImageView inbox;

    @FXML
    void initialize() {
        assert friendIcon != null : "fx:id=\"friendIcon\" was not injected: check your FXML file 'allFriends.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'allFriends.fxml'.";
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'allFriends.fxml'.";
        assert inbox != null : "fx:id=\"inbox\" was not injected: check your FXML file 'allFriends.fxml'.";

    }
}
