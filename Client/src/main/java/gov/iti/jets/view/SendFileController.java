package gov.iti.jets.view;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import gov.iti.jets.model.FileMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SendFileController {
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
    private Label fileName;

    @FXML
    private Label fileSize;

    @FXML
    private HBox parentHBox;

    @FXML
    private Label timeStamp;

    @FXML
    void initialize() {
        assert contentVBox != null : "fx:id=\"contentVBox\" was not injected: check your FXML file 'sendFile.fxml'.";
        assert fileImage != null : "fx:id=\"fileImage\" was not injected: check your FXML file 'sendFile.fxml'.";
        assert fileName != null : "fx:id=\"fileName\" was not injected: check your FXML file 'sendFile.fxml'.";
        assert fileSize != null : "fx:id=\"fileSize\" was not injected: check your FXML file 'sendFile.fxml'.";
        assert parentHBox != null : "fx:id=\"parentHBox\" was not injected: check your FXML file 'sendFile.fxml'.";
        assert timeStamp != null : "fx:id=\"timeStamp\" was not injected: check your FXML file 'sendFile.fxml'.";

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
