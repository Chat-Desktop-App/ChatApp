package gov.iti.jets.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class homeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane homeBorderPane;

    @FXML
    private AnchorPane left_pane;

    @FXML
    private AnchorPane stackpane;

    @FXML
    void initialize() {
        assert homeBorderPane != null : "fx:id=\"homeBorderPane\" was not injected: check your FXML file 'home.fxml'.";
        assert left_pane != null : "fx:id=\"left_pane\" was not injected: check your FXML file 'home.fxml'.";
        assert stackpane != null : "fx:id=\"stackpane\" was not injected: check your FXML file 'home.fxml'.";

    }

}
