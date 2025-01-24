package gov.iti.jets.client.controller;

import gov.iti.jets.client.HelloApplication;
import gov.iti.jets.client.model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignUpController implements Initializable {
    @FXML
    private VBox background;

    @FXML
    private VBox SignUpVbox;

    @FXML
    private Label createLabel;

    @FXML
    private ImageView logo;

    @FXML
    private Image orca;

    @FXML
    private TextField phoneNum;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private TextField cpassword;

    @FXML
    private Button next;

    private Stage primaryStage;

    private Scene nextScene;

    private Parent nextRoot;

    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void handleNextButton(ActionEvent event) throws IOException {
        // check if all data is entered
        if(phoneNum.getText().isBlank() || fname.getText().isBlank() || lname.getText().isBlank() || email.getText().isBlank() || password.getText().isBlank() || password.getText().isBlank()){
            // make a dialogue
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter all fields");
            alert.show();
        }

        // check password is same as cpassword
        else if(!password.getText().equals(cpassword.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Passwords do not match. Please make sure both passwords are the same");
            alert.show();
        }else{
            // make validation on users data
                // phone number is valid
                //password is min 6 characters
                // hash the password
                // valid email
            // add it to a user object
                user = new User();
                user.setPhoneNumber(phoneNum.getText());
                user.setFname(fname.getText());
                user.setLname(lname.getText());
                user.setEmail(email.getText());
                user.setPasswordHashed(password.getText());
            // change scene
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/SignUp2.fxml"));
            nextRoot = fxmlLoader.load();
            // move the user object to signup2
            SignUp2Controller controller = fxmlLoader.getController();
            controller.setUser(user);
            nextScene = new Scene(nextRoot);
            nextScene.getStylesheets().add(Objects.requireNonNull(HelloApplication.class.getResource("styles/SignUp.css")).toExternalForm());
            primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(nextScene);
        }


    }


    public void setUser(User user) {
        this.user = user;
        Platform.runLater(this::updateUI);
    }

    private void updateUI() {
        System.out.println("hello");
        if (user != null) {
            System.out.println("hello2");
            phoneNum.setText(user.getPhoneNumber() != null ? user.getPhoneNumber() : "");
            fname.setText(user.getFname() != null ? user.getFname() : "");
            lname.setText(user.getLname() != null ? user.getLname() : "");
            email.setText(user.getEmail() != null ? user.getEmail() : "");
            password.setText(user.getPasswordHashed() != null ? user.getPasswordHashed() : "");
            cpassword.setText(user.getPasswordHashed() != null ? user.getPasswordHashed() : "");
        }
    }
}
