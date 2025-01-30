package gov.iti.jets.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class BlockedController {

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
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'blocked.fxml'.";
        assert onlineText != null : "fx:id=\"onlineText\" was not injected: check your FXML file 'blocked.fxml'.";
        String fxmlPath = "/gov/iti/jets/fxml/blockedCard.fxml";
        ObservableList<AnchorPane> observableList = loadFXMLIntoList(fxmlPath, 20);
        createListView(observableList);
    }

    private ObservableList<AnchorPane> loadFXMLIntoList(String fxmlPath, int count) {
        ObservableList<AnchorPane> list = FXCollections.observableArrayList();
        for (int i = 0; i < count; i++) {
            try {
                AnchorPane anchorPane = new FXMLLoader(getClass().getResource(fxmlPath)).load();
                list.add(anchorPane);
            } catch (IOException e) {
                System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
            }
        }
        return list;
    }

    private void createListView(ObservableList<AnchorPane> items) {
        listView.setItems(items);
        listView.setStyle("-fx-background-color: white;");
        listView.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            for (ScrollBar scrollBar : listView.lookupAll(".scroll-bar").stream()
                    .filter(ScrollBar.class::isInstance)
                    .map(ScrollBar.class::cast)
                    .toList()) {
                scrollBar.setOpacity(0);
                scrollBar.setPrefSize(0, 0);
                scrollBar.setDisable(true);
            }
        });
        listView.setCellFactory(lv -> new ListCell<AnchorPane>() {
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