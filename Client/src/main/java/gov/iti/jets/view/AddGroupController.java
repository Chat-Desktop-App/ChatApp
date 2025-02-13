package gov.iti.jets.view;

import gov.iti.jets.ClientApp;
import gov.iti.jets.controller.GroupServiceController;
import gov.iti.jets.controller.HomeServiceController;
import gov.iti.jets.model.CreateGroupDTO;
import gov.iti.jets.model.GroupMemberDTO;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddGroupController implements Initializable {

    private final List<AddGroupCellController> cellControllers = new ArrayList<>();
    @FXML
    private TextField GroupName_Field;
    @FXML
    private ListView<GroupMemberDTO> ListView_OfCheckBox;
    @FXML
    private Button choosePictureButton;
    @FXML
    private Button createGroupButton;
    @FXML
    private ImageView groupImage;
    @FXML
    private TextField selectFriend_Field;
    private byte[] selectedImageBytes;
    private FilteredList<GroupMemberDTO> filteredList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<GroupMemberDTO> friends = GroupServiceController.getUsersList();

        filteredList = new FilteredList<>(friends, user -> true);
        ListView_OfCheckBox.setItems(filteredList);
        ListView_OfCheckBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(GroupMemberDTO user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setGraphic(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(ClientApp.class.getResource("fxml/addGroupCell.fxml"));
                        Parent root = loader.load();
                        AddGroupCellController controller = loader.getController();
                        controller.setUser(user);
                        cellControllers.add(controller);
                        setGraphic(root);
                    } catch (IOException e) {
                        e.printStackTrace();
                        setText("Error loading user card");
                    }
                }
            }
        });

        selectFriend_Field.textProperty().addListener((obs, oldValue, newValue) -> {
            filteredList.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String filterLower = newValue.toLowerCase();
                String fullName = user.firstName().toLowerCase() + " " + user.secondName().toLowerCase();


                return fullName.contains(filterLower) ||
                        user.phoneNumber().contains(newValue);
            });
        });


    }


    @FXML
    void handleChoosePictureButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();


        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp");
        fileChooser.getExtensionFilters().add(imageFilter);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {

            Image image = new Image(selectedFile.toURI().toString());

            groupImage.setImage(image);

            selectedImageBytes = convertFileToBytes(selectedFile);

        }

    }

    @FXML
    void handleCreategroup(ActionEvent event) {

        List<GroupMemberDTO> selectedUsers = new ArrayList<>();

        for (AddGroupCellController controller : cellControllers) {
            if (controller.isSelected()) {
                selectedUsers.add(controller.getUser());
                System.out.println(controller.getUser().firstName());
            }

        }
        // make a dto have group info including its admin id and list of its memebers and including its admin as memeber
        // send to server

        // make groupservice method and give the group and list of its member including its admin
        if (!GroupName_Field.getText().isBlank() && !selectedUsers.isEmpty()) {
            CreateGroupDTO group = new CreateGroupDTO(GroupName_Field.getText().trim(),
                    HomeServiceController.getUser().getPhoneNumber(), selectedImageBytes, selectedUsers);
            GroupServiceController.createGroup(group);
            Stage stage = (Stage) createGroupButton.getScene().getWindow();
            showSucess("Group " + GroupName_Field.getText().trim() + " has been created Successfully", stage);

        }
        // handle that the group name not blank
        if (GroupName_Field.getText().isBlank()) {
            GroupName_Field.setTooltip(new Tooltip("Please enter group Name"));
            GroupName_Field.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        } else {
            GroupName_Field.setTooltip(null);
            GroupName_Field.setStyle("");
        }
        if (selectedUsers.isEmpty()) {
            ListView_OfCheckBox.setTooltip(new Tooltip("Please add memebers to your group"));
            ListView_OfCheckBox.setStyle("-fx-border-color: red; -fx-border-width: 2px;");

        } else {

            ListView_OfCheckBox.setTooltip(null);
            ListView_OfCheckBox.setStyle("");

        }


    }

    private byte[] convertFileToBytes(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private void showSucess(String message, Stage owner) {
        // Create a blur effect
        GaussianBlur blur = new GaussianBlur(10);
        owner.getScene().getRoot().setEffect(blur);

        // Create a new alert stage
        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL); // Block interactions with main window
        alertStage.initOwner(owner);
        alertStage.setTitle("Success");

        ImageView icon = new ImageView(new Image(ClientApp.class.getResourceAsStream("images/success.png")));
        icon.setFitWidth(80);
        icon.setFitHeight(80);

        // Alert message
        Label errorLabel = new Label(message);
        errorLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        errorLabel.setWrapText(true); // Enable text wrapping
        errorLabel.setMaxWidth(300); //
        errorLabel.setAlignment(Pos.CENTER);

        // OK button
        Button okButton = new Button("OK");
        okButton.setStyle("-fx-background-color: #003249; -fx-text-fill: white; -fx-font-weight: bold;");
        okButton.setOnAction(e -> {
            owner.getScene().getRoot().setEffect(null); // Remove blur when closing
            alertStage.close();
        });
        alertStage.setOnCloseRequest(e -> {
            owner.getScene().getRoot().setEffect(null);  // Remove the blur effect
            alertStage.close();  // Close the alert window
        });

        // Layout
        VBox layout = new VBox(10, icon, errorLabel, okButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center; -fx-border-radius: 10px;");
        layout.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-alignment: center;");


        // Scene
        Scene scene = new Scene(layout, 600, 400);
        alertStage.setScene(scene);
        alertStage.showAndWait();
    }


}
