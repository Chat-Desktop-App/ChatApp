package gov.iti.jets.view;

import java.net.URL;
import java.util.ResourceBundle;

import gov.iti.jets.model.ContactUser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class OnlineCardController {
    ContactUser contactUser;
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
    void handleInboxIcon(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert friendIcon != null : "fx:id=\"friendIcon\" was not injected: check your FXML file 'onlineCard.fxml'.";
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'onlineCard.fxml'.";
        assert inboxIcon != null : "fx:id=\"inboxIcon\" was not injected: check your FXML file 'onlineCard.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'onlineCard.fxml'.";

    }

    public void setContact(ContactUser contactUser) {
        this.contactUser = contactUser;
        friendName.setText(contactUser.getFname()+" " + contactUser.getLname());
    }
}
