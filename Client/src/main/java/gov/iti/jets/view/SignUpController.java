package gov.iti.jets.view;

import gov.iti.jets.controller.RegisterServiceController;

import gov.iti.jets.model.Gender;
import gov.iti.jets.model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class SignUpController implements Initializable {
    @FXML
    private VBox background;

    @FXML
    private ImageView logo;

    @FXML
    private Image orca;

    @FXML
    private VBox SignUp1Vbox;

    @FXML
    private Label createLabel;

    @FXML
    private TextField phoneNum;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField cpassword;

    @FXML
    private Button next;

    @FXML
    private VBox SignUp2Vbox;

    @FXML
    private Label createLabel2;

    @FXML
    private ImageView profilePicture;

    @FXML
    private Image profile;

    @FXML
    private Button choosePicture;

    @FXML
    private RadioButton female;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton male;

    @FXML
    private DatePicker dob;

    @FXML
    private ComboBox<String> country;

    @FXML
    private TextField bio;

    @FXML
    private Button prev;

    @FXML
    private Button submit;

    private Stage primaryStage;

    private Scene nextScene;

    private Parent nextRoot;

    private User user;


    private byte[] selectedImageBytes;

    /*
        * +1 234 567 8901
           +20 100 123 4567
           +201001234567
           +44 (20) 1234 5678
           0123 456 7890
           +91 98765 43210
           * min digits 8 max 11
    * */
    private static final String PHONE_REGEX = "^(\\+?\\d{1,3} ?\\(?\\d{1,4}\\)? ?\\d{3,4} ?\\d{4})$";

    /*
                *  user@example.com
                    user.name@example.com
                    user_name@example.com
                    user+123@example.co.uk
                    U123@domain.net
     */
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    /*
    *   At least one letter ([A-Za-z])
        At least one digit (\d)
        Minimum length of 8 characters
        Allows only letters and numbers (No special characters required)
    * */
    private static final String PASS_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";


    private RegisterServiceController registerController;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerController  = new RegisterServiceController(this);
        country.getItems().addAll("Afghanistan", "Albania", "Algeria", "Andorra", "Angola",
                "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan",
                "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus",
                "Belgium", "Belize", "Benin", "Bhutan", "Bolivia",
                "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria",
                "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon",
                "Canada", "Central African Republic", "Chad", "Chile", "China",
                "Colombia", "Comoros", "Congo", "Costa Rica", "Croatia",
                "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti",
                "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador",
                "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia",
                "Fiji", "Finland", "France", "Gabon", "Gambia",
                "Georgia", "Germany", "Ghana", "Greece", "Grenada",
                "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti",
                "Honduras", "Hungary", "Iceland", "India", "Indonesia",
                "Iran", "Iraq", "Ireland", "Israel", "Italy",
                "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya",
                "Kiribati", "Korea, North", "Korea, South", "Kosovo", "Kuwait",
                "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho",
                "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
                "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali",
                "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico");

        phoneNum.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // When focus is lost
                validatePhoneNumber();
            }
        });

        email.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // When focus is lost
                validateEmail();
            }
        });

        password.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // When focus is lost
                validatePassword();
            }
        });


    }

    private void validatePassword() {
        String storedPassword = password.getText().trim();
        if (!storedPassword.matches(PASS_REGEX)) {
            showAlert("Invalid Password!\n" +
                    "Your password must contain:\n" +
                    "- At least 8 characters\n" +
                    "- At least one letter (A-Z or a-z)\n" +
                    "- At least one number (0-9)");
            password.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            password.requestFocus();
        } else {
            password.setStyle("");
        }
    }

    private void validateEmail() {
        String storedEmail = email.getText().trim();
        if (!storedEmail.matches(EMAIL_REGEX)) {
            showAlert("Invalid Email!");
            email.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            email.requestFocus();
        } else {
            email.setStyle("");
        }
    }

    private void validatePhoneNumber() {
        String phoneNumber = phoneNum.getText().trim();
        if (!phoneNumber.matches(PHONE_REGEX)) {
            showAlert("Invalid phone Number!");
            phoneNum.setStyle("-fx-border-color: red; -fx-border-width: 2px;"); // Highlight the field in red
            phoneNum.requestFocus();

        } else {
            phoneNum.setStyle("");
        }
    }


    @FXML
    void handleNextButton(ActionEvent event) throws IOException {
        // check if all data is entered
        if (phoneNum.getText().isBlank() || fname.getText().isBlank() || lname.getText().isBlank() || email.getText().isBlank() || password.getText().isBlank() || password.getText().isBlank()) {
            // make a dialogue
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter all fields");
            alert.show();
        }

        // check password is same as cpassword
        else if (!password.getText().equals(cpassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Passwords do not match. Please make sure both passwords are the same");
            alert.show();
        } else {

            // add it to a user object
            user = new User();
            user.setPhoneNumber(phoneNum.getText().trim().replaceAll("\\s", ""));
            user.setFname(fname.getText().trim());
            user.setLname(lname.getText().trim());
            user.setEmail(email.getText().trim());
            user.setPasswordHashed(password.getText().trim());
            SignUp1Vbox.setVisible(false);
            SignUp2Vbox.setVisible(true);

        }


    }


    public void setUser(User user) {
        this.user = user;
        Platform.runLater(this::updateUI);
    }

    private void updateUI() {

        if (user != null) {

            phoneNum.setText(user.getPhoneNumber() != null ? user.getPhoneNumber() : "");
            fname.setText(user.getFname() != null ? user.getFname() : "");
            lname.setText(user.getLname() != null ? user.getLname() : "");
            email.setText(user.getEmail() != null ? user.getEmail() : "");
            password.setText(user.getPasswordHashed() != null ? user.getPasswordHashed() : "");
            cpassword.setText(user.getPasswordHashed() != null ? user.getPasswordHashed() : "");
        }
    }

    public void handleChoosePicButton(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();


        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp");
        fileChooser.getExtensionFilters().add(imageFilter);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {

            Image image = new Image(selectedFile.toURI().toString());

            profilePicture.setImage(image);

            selectedImageBytes = convertFileToBytes(selectedFile);

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

    public void handleSubmitButton(ActionEvent actionEvent) {
        //picture
        //gender
        //DOB
        // country
        // bio

        // Validate Gender
        if (!(male.isSelected() || female.isSelected())) {
            showAlert("Gender is required.");
            return;  // Stop the process if validation fails
        }

        // Validate Date of Birth
        if (dob.getValue() == null) {
            showAlert("Date of Birth is required.");
            return;
        }

        // Validate Country
        if (country.getValue() == null || country.getValue().isEmpty()) {
            showAlert("Country is required.");
            return;
        }


        if (male.isSelected()) {
            user.setGender(Gender.MALE);
        } else if (female.isSelected()) {
            user.setGender(Gender.FEMALE);
        }

        user.setDob(dob.getValue());
        user.setBio(bio.getText());
        user.setCountry(country.getValue());
        user.setPicture(selectedImageBytes);

        if (!registerController.signUp(user)) {
            showAlert("User already exists");
        }

        // set userSession
        // login
        // switch scene


    }

    public void handlePrevButton(ActionEvent actionEvent) {
        SignUp2Vbox.setVisible(false);
        SignUp1Vbox.setVisible(true);

    }

    public void setRegisterController(RegisterServiceController registerController) {
        this.registerController = registerController;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
