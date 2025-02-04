package gov.iti.jets.view;

import gov.iti.jets.controller.HomeServiceController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class BlockedController {

    @FXML
    private ListView<AnchorPane> listView;

    @FXML
    private Text onlineText;

    @FXML
    void initialize() {
        loadChatsList();
    }

    private void loadChatsList() {
        ObservableList<AnchorPane> items = HomeServiceController.getBlockedContacts();
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