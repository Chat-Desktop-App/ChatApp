package gov.iti.jets.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AddGroupController {


    @FXML
    private TextField Selecttextfield;

    @FXML
    private CheckBox friendSelection;

    @FXML
    private ImageView driendIcon;

    @FXML
    private Button createGroupButton;

    @FXML
    void handleCheckbox(ActionEvent event) {

    }

    @FXML
    void handleCreategroup(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert Selecttextfield != null : "fx:id=\"Selecttextfield\" was not injected: check your FXML file 'addGroup.fxml'.";
        assert friendSelection != null : "fx:id=\"friendSelection\" was not injected: check your FXML file 'addGroup.fxml'.";
        assert driendIcon != null : "fx:id=\"driendIcon\" was not injected: check your FXML file 'addGroup.fxml'.";
        assert createGroupButton != null : "fx:id=\"createGroupButton\" was not injected: check your FXML file 'addGroup.fxml'.";

    }
}
