package gov.iti.jets.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class ProfileController {

    @FXML
    private Button ChangeprofilePic;

    @FXML
    private TextField bio;

    @FXML
    private Button edit;

    @FXML
    private TextField email;

    @FXML
    private TextField fullName;

    @FXML
    private ComboBox<?> status;

    @FXML
    private ImageView picId;

    @FXML
    void handleEditButton(ActionEvent event) {

    }

    @FXML
    void handleProfilePic(ActionEvent event) throws IOException {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open File");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"));
            File file = chooser.showOpenDialog(picId.getScene().getWindow()); // Use the current window
            if(file != null) {
                String imagepath = file.toURI().toString();

                Image image = new Image(imagepath);
                picId.setImage(image);
            }
            else
            {
               picId = picId;
            }
    }

}
