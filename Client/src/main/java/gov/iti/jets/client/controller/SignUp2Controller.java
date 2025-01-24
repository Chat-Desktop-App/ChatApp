package gov.iti.jets.client.controller;

import gov.iti.jets.client.HelloApplication;
import gov.iti.jets.client.database.dao.UserDao;
import gov.iti.jets.client.database.dao.UserDaoImpl;
import gov.iti.jets.client.model.Gender;
import gov.iti.jets.client.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUp2Controller implements Initializable {
    private User user;

    private Stage primaryStage;
    private Scene nextScene;
    private Parent nextRoot;

    @FXML
    private VBox background;

    @FXML
    private Image orca;

    @FXML
    private VBox SignUpVbox;

    @FXML
    private Label createLabel;

    @FXML
    private ImageView profilePicture;

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
    void handleChoosePicButton(ActionEvent event) {

    }

    @FXML
    void handlePrevButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/SignUp.fxml"));
        nextRoot = fxmlLoader.load();
        // move the user object to signup2
        SignUpController controller = fxmlLoader.getController();
        controller.setUser(user);
        nextScene = new Scene(nextRoot);
        nextScene.getStylesheets().add(Objects.requireNonNull(HelloApplication.class.getResource("styles/signUp.css")).toExternalForm());
        primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(nextScene);
    }

    @FXML
    void handleSubmitButton(ActionEvent event) {
        if(bio.getText().isBlank() || country.getValue() == null || gender.getSelectedToggle() == null || dob.getValue() == null ){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter all fields");
            alert.show();
        }else{
            user.setCountry(country.getValue());
            user.setBio(bio.getText());
            user.setDob(dob.getValue());

            RadioButton selectedRadioButton = (RadioButton) gender.getSelectedToggle();
            String selectedGender = selectedRadioButton.getText();
            if(selectedGender.equals("Female") ){
                user.setGender(Gender.FEMALE);
            }else {
                user.setGender(Gender.FEMALE);

            }
            UserDao dao = new UserDaoImpl();
            try {
                dao.addUser(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void setUser(User user) {
        this.user = user;
    }


}
