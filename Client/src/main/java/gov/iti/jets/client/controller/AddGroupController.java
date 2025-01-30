package gov.iti.jets.client.controller;
package gov.iti.jets.client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AddGroupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField GroupName_Field;

    @FXML
    private ListView<?> ListView_OfCheckBox;

    @FXML
    private Button choosePictureButton;

    @FXML
    private Button createGroupButton;

    @FXML
    private ImageView groupImage;

    @FXML
    private TextField selectFriend_Field;

    @FXML
    void handleChoosePictureButton(ActionEvent event) {

    }

    @FXML
    void handleCreategroup(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert GroupName_Field != null : "fx:id=\"GroupName_Field\" was not injected: check your FXML file 'addGroup.fxml'.";
        assert ListView_OfCheckBox != null : "fx:id=\"ListView_OfCheckBox\" was not injected: check your FXML file 'addGroup.fxml'.";
        assert choosePictureButton != null : "fx:id=\"choosePictureButton\" was not injected: check your FXML file 'addGroup.fxml'.";
        assert createGroupButton != null : "fx:id=\"createGroupButton\" was not injected: check your FXML file 'addGroup.fxml'.";
        assert groupImage != null : "fx:id=\"groupImage\" was not injected: check your FXML file 'addGroup.fxml'.";
        assert selectFriend_Field != null : "fx:id=\"selectFriend_Field\" was not injected: check your FXML file 'addGroup.fxml'.";

    }

}
