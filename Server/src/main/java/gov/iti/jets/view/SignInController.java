package gov.iti.jets.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {
    @FXML
    public VBox loginVbox;

    @FXML
    public Label welcomeLabel;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField password;
    @FXML
    public Button logIn;
    @FXML
    public Button signUp;
    private Parent root;

    @FXML
    private void goToRegister(ActionEvent event) {
        FXMLLoader loaderSignIn = new FXMLLoader(getClass().getResource("/fxml/server-register.fxml"));
        try {
            root = loaderSignIn.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void goToHome (ActionEvent event) {
        FXMLLoader loaderSignIn = new FXMLLoader(getClass().getResource("/fxml/server-homePage.fxml"));
        try {
            root = loaderSignIn.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            double width = stage.getWidth();
            double height = stage.getHeight();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.setWidth(width);
            stage.setHeight(height);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
