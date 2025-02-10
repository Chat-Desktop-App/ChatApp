package gov.iti.jets.view;

import java.io.ByteArrayInputStream;

import gov.iti.jets.controller.HomeServiceController;
import gov.iti.jets.model.ContactStatus;
import gov.iti.jets.model.ContactUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlockedCardController {
    private ContactUser contactUser;

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

    }

    @FXML
    void handleUnblockButton(ActionEvent event) {
        Boolean flag = HomeServiceController.updateContact(contactUser, ContactStatus.ACCEPTED , ContactStatus.BLOCKED);
    }

    public void setContact(ContactUser contactUser) {
        this.contactUser = contactUser;
        friendName.setText(contactUser.getFname()+" " + contactUser.getLname());
        byte [] pic = contactUser.getPicture();
        if(pic != null){
            friendIcon.setImage(new Image(new ByteArrayInputStream(pic)));
        }
    }
}
