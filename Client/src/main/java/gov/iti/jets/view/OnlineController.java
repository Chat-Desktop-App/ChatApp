package gov.iti.jets.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import gov.iti.jets.controller.HomeServiceController;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.services.interfaces.LoadHome;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class OnlineController {
    HomeServiceController homeServiceController;

    @FXML
    private ResourceBundle resources;


    @FXML
    private URL location;

    @FXML
    private ListView<AnchorPane> listView;

    @FXML
    private Text onlineText;

    @FXML
    void initialize() {
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'online.fxml'.";
        assert onlineText != null : "fx:id=\"onlineText\" was not injected: check your FXML file 'online.fxml'.";
        homeServiceController = new HomeServiceController();
        loadChatsList();
    }

    private void loadChatsList() {
        ObservableList<AnchorPane> items = homeServiceController.getOnlineContacts();
        listView.setItems(items);
        listView.setStyle("-fx-background-color: white;");
        listView.setSelectionModel(null);
        listView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(AnchorPane item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setStyle("-fx-background-color: transparent;");
                } else {
                    setGraphic(item);
                    setStyle("-fx-background-color: transparent;");
                }
            }
        });
    }

}
