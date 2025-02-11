package gov.iti.jets.view;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import gov.iti.jets.model.FileMessage;
import gov.iti.jets.model.GroupMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ReceiveGroupFileController {
    private FileMessage fileMessage;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox contentVBox;

    @FXML
    private ImageView fileImage;

    @FXML
    private Text name;

    @FXML
    private Label fileName;

    @FXML
    private Label fileSize;

    @FXML
    private HBox parentHBox;

    @FXML
    private ImageView profilePic;

    @FXML
    private Label timeStamp;


    @FXML
    void initialize() {

    }

    public void setFileMessage(FileMessage fileMessage) {
        this.fileMessage = fileMessage;
        fileName.setText(fileMessage.getFileName());
        fileSize.setText((fileMessage.getFileData().length/1000000.0) + " MB");

        LocalDateTime dateTime = fileMessage.getTimestamp().toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E h:mm a", Locale.ENGLISH);
        String formattedTimestamp = dateTime.format(formatter);
        timeStamp.setText(formattedTimestamp);

        GroupMessage message = (GroupMessage) (fileMessage.getMessage());
        name.setText(message.getName());
        byte [] pic = message.getProfilePicture();
        if (pic != null){
            profilePic.setImage(new Image(new ByteArrayInputStream(pic)));
        }

    }

}
