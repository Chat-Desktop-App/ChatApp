package gov.iti.jets.view;

import gov.iti.jets.database.dao.UserDao;
import gov.iti.jets.database.dao.UserDaoImpl;
import gov.iti.jets.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private ChoiceBox genderChoiceBox;
    @FXML
    private VBox SignUpVbox;
    @FXML
    private Label createLabel;
    @FXML
    private VBox background;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button nextButton;
    private final UserDao userDao = new UserDaoImpl();


    @FXML
    public void goToSignIn(ActionEvent event) {
        if (!validateInput()) return;
        User user = new User();
        user.setPhoneNumber(phoneNumberField.getText().trim());
        user.setFname(firstNameField.getText().trim());
        user.setLname(lastNameField.getText().trim());
        user.setEmail(emailField.getText().trim());
        user.setPasswordHashed(passwordField.getText().trim()); // Hash Password
        user.setAdmin(true);
        try {
            userDao.hashPass(passwordField.getText().trim());
            userDao.addUser(user);
            navigateToSignIn(event);

        } catch (SQLException sqlException) {
            showAlert(Alert.AlertType.ERROR, "ERROR", "failed to register admin" + sqlException.getMessage());
        }

    }

    private void navigateToSignIn(ActionEvent event) {
        FXMLLoader loaderSignIn = new FXMLLoader(getClass().getResource("/fxml/server-signIn.fxml"));
        try {
            Parent root = loaderSignIn.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.show();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean validateInput() {
        String phone = phoneNumberField.getText().trim();
        String fname = firstNameField.getText().trim();
        String lname = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();
        if (phone.isEmpty() || fname.isEmpty() || lname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields must be filled");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Passwords don't match");
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
