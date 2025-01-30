package gov.iti.jets.client.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;

public class AddGroupCellController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView friendIcon;

    @FXML
    private CheckBox friendName;

    @FXML
    void handleCheckbox(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert friendIcon != null : "fx:id=\"friendIcon\" was not injected: check your FXML file 'addGroupCell.fxml'.";
        assert friendName != null : "fx:id=\"friendName\" was not injected: check your FXML file 'addGroupCell.fxml'.";

    }

}
