package gov.iti.jets.view;

import gov.iti.jets.ClientApp;
import gov.iti.jets.controller.RegisterServiceController;

import gov.iti.jets.model.Gender;
import gov.iti.jets.model.User;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


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
    private HBox hiddenPassHbox;

    @FXML
    private PasswordField password;

    @FXML
    private HBox showPassHbox;

    @FXML
    private TextField passwordText;

    @FXML
    private HBox hiddenCpass;

    @FXML
    private PasswordField cpassword;

    @FXML
    private HBox shownCpass;

    @FXML
    private TextField cpasswordText;


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
        StringProperty passwordTextProperty = passwordText.textProperty();
        password.textProperty().bindBidirectional(passwordTextProperty);

        StringProperty cpasswordTextProperty = cpasswordText.textProperty();
        cpassword.textProperty().bindBidirectional(cpasswordTextProperty);

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

        passwordText.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // When focus is lost
                validatePassword();
            }
        });
        password.setTooltip(new Tooltip("Password must contain at least 8 characters, one letter, and one number"));
        passwordText.setTooltip(new Tooltip("Password must contain at least 8 characters, one letter, and one number"));



    }

    private void validatePassword() {
        if(hiddenPassHbox.isVisible()) {
            String storedPassword = password.getText().trim();
            if (!storedPassword.matches(PASS_REGEX)) {

                password.setStyle("-fx-border-color: red; -fx-border-width: 2px;");

                password.requestFocus();
            } else {
                password.setStyle("");

            }
        }else{
            String storedPassword = passwordText.getText().trim();
            if (!storedPassword.matches(PASS_REGEX)) {

                passwordText.setStyle("-fx-border-color: red; -fx-border-width: 2px;");

                passwordText.requestFocus();
            } else {
                passwordText.setStyle("");

            }
        }
    }

    private void validateEmail() {
        String storedEmail = email.getText().trim();
        if (!storedEmail.matches(EMAIL_REGEX)) {
            email.setTooltip(new Tooltip("Invalid email format"));
            email.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            email.requestFocus();
        } else {
            email.setTooltip(null);
            email.setStyle("");
        }
    }

    private void validatePhoneNumber() {
        String phoneNumber = phoneNum.getText().trim();
        if (!phoneNumber.matches(PHONE_REGEX)) {
            phoneNum.setTooltip(new Tooltip("Phone number must contain 10 digits"));
            phoneNum.setStyle("-fx-border-color: red; -fx-border-width: 2px;"); // Highlight the field in red
            phoneNum.requestFocus();

        } else {
            phoneNum.setTooltip(null);
            phoneNum.setStyle("");
        }
    }


    @FXML
    void handleNextButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) submit.getScene().getWindow();
        // check if all data is entered
        if (phoneNum.getText().isBlank() || fname.getText().isBlank() || lname.getText().isBlank() || email.getText().isBlank() || password.getText().isBlank() || password.getText().isBlank()) {

            showAlert("Please enter all fields", stage);
        }
        else if ( !passwordText.getStyle().equals("") || !password.getStyle().equals("") || !email.getStyle().equals("") || !phoneNum.getStyle().equals("")) {
            // All inputs are valid, proceed with the action (e.g., submitting the form)
            showAlert("Invalid Data", stage);
        }
        // check password is same as cpassword
        else if (!password.getText().equals(cpassword.getText())) {

            showAlert("Passwords do not match. Please make sure both passwords are the same",stage);
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


        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image MyFile", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp");
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
        Stage stage = (Stage) submit.getScene().getWindow();
        if (!(male.isSelected() || female.isSelected())) {
            showAlert("Gender is required.", stage);
            return;  // Stop the process if validation fails
        }

        // Validate Date of Birth
        if (dob.getValue() == null) {
            showAlert("Date of Birth is required.", stage);
            return;
        }

        // Validate Country
        if (country.getValue() == null || country.getValue().isEmpty()) {
            showAlert("Country is required.", stage);
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
            showAlert("User already exists", stage);
        }else {


            showSucess("Registration Successful! Your account has been created. You can now log in and start chatting!!", stage);
            FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("fxml/Login.fxml"));
            try {
                nextRoot = fxmlLoader.load();
                nextScene = new Scene(nextRoot);
                primaryStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
                primaryStage.setScene(nextScene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
        errorLabel.setWrapText(true);
        errorLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
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

        // Layout for alert (Icon + Message + Button)
        VBox alertLayout = new VBox(15, icon, errorLabel, okButton);
        alertLayout.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-alignment: center;");
        Scene alertScene = new Scene(alertLayout, 600, 400);

        alertStage.setScene(alertScene);
        alertStage.showAndWait(); // Show alert and wait until it is closed
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
        Scene scene = new Scene(layout,600,400);
        alertStage.setScene(scene);
        alertStage.showAndWait();
    }


    @FXML
    void handleShowPass(MouseEvent event) {

        hiddenPassHbox.setVisible(false);
        showPassHbox.setVisible(true);

        hiddenCpass.setVisible(false);
        shownCpass.setVisible(true);
    }

    @FXML
    void handleHidePass(MouseEvent event) {

        hiddenPassHbox.setVisible(true);
        showPassHbox.setVisible(false);

        hiddenCpass.setVisible(true);
        shownCpass.setVisible(false);

    }


    @FXML
    void handleLoginLink(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(ClientApp.class.getResource("fxml/Login.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }


}
