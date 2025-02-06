package gov.iti.jets.view;


import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import gov.iti.jets.model.GroupMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class receiveGroupMessageController {

    private GroupMessage message;
    @FXML
    private Text messageContent;

    @FXML
    private Text name;

    @FXML
    private HBox parentHBox;

    @FXML
    private ImageView profilePic;

    @FXML
    private Label timeStamp;

    @FXML
    void initialize() {

    }

    public void setMessage(GroupMessage message) {
        this.message = message;
        messageContent.setText(message.getContent());
        LocalDateTime dateTime = message.getTimestamp().toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E h:mm a", Locale.ENGLISH);
        String formattedTimestamp = dateTime.format(formatter);
        timeStamp.setText(formattedTimestamp);
        name.setText(message.getName());
        byte [] pic = message.getProfilePicture();
        if (pic != null){
            profilePic.setImage(new Image(new ByteArrayInputStream(pic)));
        }

        timeStamp.setText(message.getTimestamp().toLocalDateTime().toString());
    }

}
