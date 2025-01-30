package gov.iti.jets.client.view;

import gov.iti.jets.client.model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
    private TextField password;

    @FXML
    private TextField cpassword;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
           /* // change scene
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/signUp2.fxml"));
            nextRoot = fxmlLoader.load();
            // move the user object to signup2
            SignUp2Controller controller = fxmlLoader.getController();
            controller.setUser(user);
            nextScene = new Scene(nextRoot);
            nextScene.getStylesheets().add(Objects.requireNonNull(HelloApplication.class.getResource("styles/signUp.css")).toExternalForm());
            primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(nextScene);*/
            SignUp1Vbox.setVisible(false);
            SignUp2Vbox.setVisible(true);

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

    public void handleChoosePicButton(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();


        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp");
        fileChooser.getExtensionFilters().add(imageFilter);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {

            Image image = new Image(selectedFile.toURI().toString());
            profilePicture.setImage(image);

        }
    }

    public void handleSubmitButton(ActionEvent actionEvent) {
    }

    public void handlePrevButton(ActionEvent actionEvent) {
        SignUp2Vbox.setVisible(false);
        SignUp1Vbox.setVisible(true);

    }
}
