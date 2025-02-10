package gov.iti.jets.view;

import gov.iti.jets.ClientApp;
import gov.iti.jets.controller.HomeServiceController;
import gov.iti.jets.controller.LogInServiceController;
import gov.iti.jets.model.User;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private VBox loginVbox;

    @FXML
    private Label welcomeLabel;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField password;


    @FXML
    private PasswordField passwordField;

    @FXML
    private HBox HiddenpassHbox;


    @FXML
    private ImageView hidden;

    @FXML
    private HBox TextpassHbox;

    @FXML
    private TextField passwordText;

    @FXML
    private ImageView shown;


    @FXML
    private VBox passwordV;

    @FXML
    private VBox phoneNumberV;

    @FXML
    private Button login;

    @FXML
    private Button signUp;

    @FXML
    private Hyperlink DifferentAcc;

    private Stage primaryStage;
    private Scene nextScene;
    private Parent nextRoot;

    LogInServiceController controller;
    User user;



    @FXML
    void handleLoginButton(ActionEvent event) {

        if(login.getText().equals("NEXT")) {
            if (phoneNumber.getText().isBlank()) {
                Platform.exit();


            } else {
                if(LogInServiceController.checkPhoneNumber(phoneNumber.getText().trim())){

                    phoneNumberV.setVisible(false);
                    passwordV.setVisible(true);
                    login.setText("LOG IN");

                }else{
                    Stage stage;
                    stage = (Stage) login.getScene().getWindow();
                    showAlert("Phone Number doesn't exists", stage);
                }


            }
        }else{
           user = LogInServiceController.logIn(phoneNumber.getText().trim(), passwordField.getText().trim());
            if(user ==  null){
                Stage stage;

                    stage = (Stage) login.getScene().getWindow();

                showAlert("Invalid Password", stage);
            }else{
                System.out.println(user.getFname()+" logged in successfully");
                System.out.println(user.getPhoneNumber());
                FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("fxml/home.fxml"));
                HomeServiceController.setUser(user);
                try {
                    nextRoot = fxmlLoader.load();
                    nextScene = new Scene(nextRoot);
                    primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    primaryStage.setScene(nextScene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        }
    }

    @FXML
    void handleSignupButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("fxml/SignUp.fxml"));
        nextRoot = fxmlLoader.load();
        nextScene = new Scene(nextRoot);
        nextScene.getStylesheets().add(Objects.requireNonNull(ClientApp.class.getResource("styles/signUp.css")).toExternalForm());
        primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(nextScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StringProperty passwordFieldTextProperty = passwordField.textProperty();
        passwordText.textProperty().bindBidirectional(passwordFieldTextProperty);
        //controller = new LogInServiceController(this);
        LogInServiceController.setView(this);
    }



    private void showAlert(String message, Stage owner) {
        // Create a blur effect
        GaussianBlur blur = new GaussianBlur(10);

        // Apply blur effect to the main scene
        owner.getScene().getRoot().setEffect(blur);

        // Create a new alert stage
        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL); // Block interactions with main window
        alertStage.initOwner(owner);
        alertStage.setTitle("Alert");

        // Load icon image
        ImageView icon = new ImageView(new Image(ClientApp.class.getResourceAsStream("images/error.png")));
        icon.setFitWidth(50);
        icon.setFitHeight(50);

        // Alert message
        Label errorLabel = new Label(message);
        errorLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

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

        // Layout for alert (Icon + Message + Button)
        VBox alertLayout = new VBox(15, icon, errorLabel, okButton);
        alertLayout.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-alignment: center;");
        Scene alertScene = new Scene(alertLayout, 300, 200);

        alertStage.setScene(alertScene);
        alertStage.showAndWait(); // Show alert and wait until it is closed
    }

    @FXML
    void handleShownPass(MouseEvent event) {
        // show pass

        TextpassHbox.setVisible(true);
        HiddenpassHbox.setVisible(false);


    }


    @FXML
    void handleHidePass(MouseEvent event) {
        // hide pass
        TextpassHbox.setVisible(false);
        HiddenpassHbox.setVisible(true);

    }


    @FXML
    void handleDiffAccLink(ActionEvent event) {
        phoneNumber.setText("");
        passwordField.setText("");
        phoneNumberV.setVisible(true);
        passwordV.setVisible(false);
        login.setText("NEXT");

    }

    public void setRemmberMe(boolean remmberMe) {
        if(remmberMe){
            phoneNumberV.setVisible(false);
            passwordV.setVisible(true);
            login.setText("LOG IN");
        }
    }

    public void setPhoneNumber(String phoneNumberText){
        phoneNumber.setText(phoneNumberText);
    }
}




