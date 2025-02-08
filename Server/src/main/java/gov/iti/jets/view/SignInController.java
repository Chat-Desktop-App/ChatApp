package gov.iti.jets.view;

import gov.iti.jets.database.dao.UserDao;
import gov.iti.jets.database.dao.UserDaoImpl;
import gov.iti.jets.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SignInController {
    @FXML
    public VBox loginVbox;
    @FXML
    public Label welcomeLabel;
    @FXML
    public TextField phoneNumber;
    @FXML
    public PasswordField password;
    @FXML
    public Button logIn;
    @FXML
    public Button signUp;

    private Parent root;
    private final UserDao userDao = new UserDaoImpl();

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
        String phone = phoneNumber.getText().trim();
        String pass = password.getText().trim();

        if (phone.isEmpty() || pass.isEmpty()) {
            showAlert(Alert.AlertType.ERROR,"Missing Credentials","Phone number and password are required");
            return;
        }
        try {
            User user = userDao.getUser(phone);
            if (user != null) {
                String hashedInputPass = userDao.hashPass(pass);
                if (hashedInputPass.equals(user.getPasswordHashed())) {
                    loadScene(event, "/fxml/server-homePage.fxml");
                }
                else {
                    showAlert(Alert.AlertType.ERROR,"LogIn Failed", "Invalid phone number or password");
                }
            }
            else {
                showAlert(Alert.AlertType.ERROR, "LogIn Failed", "User not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database error", "Error while checking credentials");
        }
    }

    private void loadScene(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loaderSignIn = new FXMLLoader(getClass().getResource(fxmlPath));
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
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Scene Error", "Failed to load the Scene");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
