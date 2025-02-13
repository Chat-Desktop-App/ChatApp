package gov.iti.jets.view;

import gov.iti.jets.controller.Session;
import gov.iti.jets.controller.UserSettingsServiceController;
import gov.iti.jets.model.Status;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


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
    private ImageView picId;

    @FXML
    private ComboBox<Status> status;

    @FXML
    void handleEditButton(ActionEvent event) {

    }

    @FXML
    void initialize() {

        picId.setImage(new Image(new ByteArrayInputStream(Session.user.getPicture())));
        status.setItems(FXCollections.observableArrayList(Status.values()));
        email.setText(Session.user.getEmail());
        fullName.setText(Session.user.getFname() + " " + Session.user.getLname());
        bio.setText(Session.user.getBio());
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
        status.setValue(Session.user.getStatus());
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
        showSuccessPopup();
    }

    @FXML
    void handleBio(ActionEvent event) {
        String BioText = bio.getText();
        UserSettingsServiceController.updateProfileBio(BioText);
        showSuccessPopup();
    }

    @FXML
    void handleEmail(ActionEvent event) {
        String emailText = email.getText();
        UserSettingsServiceController.updateProfileEmail(emailText);
        showSuccessPopup();

    }

    @FXML
    void handleFullName(ActionEvent event) {
        String NameText = fullName.getText();
        UserSettingsServiceController.updateProfileName(NameText);
        showSuccessPopup();
    }

    @FXML
    void handleProfilePic(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image MyFile", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp");
        fileChooser.getExtensionFilters().add(imageFilter);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {

            try {
                byte[] picByte;
                picByte = Files.readAllBytes(selectedFile.toPath());
                boolean flag = UserSettingsServiceController.updateProfilePicture(picByte);
                if (flag) {
                    Image image = new Image(selectedFile.toURI().toString());
                    picId.setImage(image);
                    showSuccessPopup();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
                    alert.setTitle("update picture");
                    alert.setHeaderText("update picture failed");
                    alert.showAndWait();
                }
            } catch (IOException e) {
                System.out.println("can't read file");
            }
        }
    }

    private void showSuccessPopup() {
        // Create an alert for success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);  // No header
        alert.setContentText("Update successful!");

        // Show the alert
        alert.showAndWait();
    }

}
