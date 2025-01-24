package gov.iti.jets.client.controller;

import gov.iti.jets.client.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
    private Button login;

    @FXML
    private Button signUp;

    private Stage primaryStage;
    private Scene nextScene;
    private Parent nextRoot;

    @FXML
    void handleLoginButton(ActionEvent event) {

    }

    @FXML
    void handleSignupButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/SignUp.fxml"));
        nextRoot = fxmlLoader.load();
        nextScene = new Scene(nextRoot);
        nextScene.getStylesheets().add(Objects.requireNonNull(HelloApplication.class.getResource("styles/SignUp.css")).toExternalForm());
        primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(nextScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
