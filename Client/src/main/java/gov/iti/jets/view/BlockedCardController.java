package gov.iti.jets.view;

import java.net.URL;
import java.util.ResourceBundle;

import gov.iti.jets.model.ContactUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class BlockedCardController {
    private ContactUser contactUser;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView blockedIcon;

    @FXML
    private ImageView friendIcon;

    @FXML
    private Label friendName;

    @FXML
    private Button unblockButton;


    @FXML
    void initialize() {
        assert blockedIcon != null : "fx:id=\"blockedIcon\" was not injected: check your FXML file 'blockedCard.fxml'.";
        assert friendIcon != null : "fx:id=\"friendIcon\" was not injected: check your FXML file 'blockedCard.fxml'.";
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'blockedCard.fxml'.";
        assert unblockButton != null : "fx:id=\"unblockButton\" was not injected: check your FXML file 'blockedCard.fxml'.";

    }

    public void setContactUser(ContactUser contactUser) {
        this.contactUser = contactUser;
        friendName.setText(contactUser.getFname()+" " + contactUser.getLname());
    }
}
