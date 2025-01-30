package gov.iti.jets.client.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class AllCardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView friendIcon;

    @FXML
    private Label friendName;

    @FXML
    private ImageView inboxIcon;

    @FXML
    private Circle status;

    @FXML
    void initialize() {
        assert friendIcon != null : "fx:id=\"friendIcon\" was not injected: check your FXML file 'allCard.fxml'.";
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'allCard.fxml'.";
        assert inboxIcon != null : "fx:id=\"inboxIcon\" was not injected: check your FXML file 'allCard.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'allCard.fxml'.";

    }

}
