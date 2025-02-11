package gov.iti.jets.view;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import gov.iti.jets.model.FileMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ReceiveFileController {
    private FileMessage fileMessage ;

    @FXML
    private VBox contentVBox;

    @FXML
    private Button dowloadButton;

    @FXML
    private ImageView fileImage;

    @FXML
    private Label fileName;

    @FXML
    private Label fileSize;

    @FXML
    private HBox parentHBox;

    @FXML
    private Label timeStamp;

    @FXML
    void dowloadButtonHandle(ActionEvent event) {

    }

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
    }
}
