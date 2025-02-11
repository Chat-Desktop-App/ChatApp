package gov.iti.jets.view;

import gov.iti.jets.controller.UserSettingsServiceController;
import gov.iti.jets.model.Status;
import javafx.application.Platform;
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
import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;


public class ProfileController {
    private static ProfileController instance;

    public static ProfileController getInstance() {
        return instance;
    }

    public ProfileController() {
        instance = this;
    }

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
    private ImageView picId;

    @FXML
    private ComboBox<Status> status;

    @FXML
    void handleEditButton(ActionEvent event) {
        handleStatus(event);
        handleBio(event);
        handleEmail(event);
        handleFullName(event);
    }
    @FXML
    void initialize() {
        status.setItems(FXCollections.observableArrayList(Status.values()));

        status.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Status item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(formatStatusText(item));
                    setTextFill(getColorForStatus(item));
                    setStyle("-fx-font-weight: bold;");
                }
            }
        });

        status.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Status item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(formatStatusText(item));
                    setTextFill(getColorForStatus(item));
                    setStyle("-fx-font-weight: bold;");
                }
            }
        });

        status.setValue(Status.AVAILABLE);
        //status.getSelectionModel().selectFirst();
    }

    // Helper method to return a color based on Status
    private Color getColorForStatus(Status status) {
        return switch (status) {
            case AVAILABLE -> Color.GREEN;
            case AWAY -> Color.ORANGE;
            case BUSY -> Color.RED;
            case OFFLINE -> Color.GRAY;
        };
    }

    // Helper method to format status text nicely
    private String formatStatusText(Status status) {
        return status.name().charAt(0) + status.name().substring(1);
    }

    @FXML
    void handleStatus(ActionEvent event) {

        UserSettingsServiceController.updateProfileStatus(status.getValue());
    }
    @FXML
    void handleBio(ActionEvent event) {
        if(bio!=null) {
            String BioText = bio.getText();
            UserSettingsServiceController.updateProfileBio(BioText);
        }else{
            bio=bio;
        }
    }
    @FXML
    void handleEmail(ActionEvent event) {
        if(email!=null) {
            String emailText = email.getText();
            UserSettingsServiceController.updateProfileEmail(emailText);
        }else{
            email=email;
        }
    }
    @FXML
    void handleFullName(ActionEvent event) {
        if(fullName!=null) {
            String NameText = fullName.getText();
            UserSettingsServiceController.updateProfileName(NameText);
        }else {
            fullName=fullName;
        }
    }
    @FXML
    void handleProfilePic(ActionEvent event) throws IOException {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open File");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image MyFile", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"));
            File file = chooser.showOpenDialog(picId.getScene().getWindow()); // Use the current window
            if(file != null) {

                String imagePath = file.getAbsolutePath(); // Get the absolute path

                // Display the image
                Image image = new Image(file.toURI().toString());
                picId.setImage(image);

                // Update the profile picture path
                UserSettingsServiceController.updateProfilePicture(imagePath);
            }
            else
            {
               picId = picId;
            }
    }
    public void updateProfilePicture(String phoneNumber, String picturePath) {
        Platform.runLater(() -> {
            try {
                Image image = new Image("file:" + picturePath);
                picId.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void updateProfileStatus(String phoneNumber, Status statusValue) {
        Platform.runLater(() -> {
            status.setValue(statusValue);
        });
    }

}
