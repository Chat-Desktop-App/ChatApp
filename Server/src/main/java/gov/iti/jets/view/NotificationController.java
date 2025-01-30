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

public class NotificationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<AnchorPane> listOFNotifications;

    @FXML
    void initialize() {
        assert listOFNotifications != null : "fx:id=\"listOFNotifications\" was not injected: check your FXML file 'notification.fxml'.";
        ObservableList<AnchorPane> observableList = loadFXMLIntoList("/gov/iti/jets/fxml/notificationCell.fxml", 20);
        listOFNotifications.setItems(observableList);

        // Customize the ListView appearance
        listOFNotifications.setStyle("-fx-background-color: white;");
        listOFNotifications.setCellFactory(lv -> new ListCell<>() {
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

        listOFNotifications.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            for (ScrollBar scrollBar : listOFNotifications.lookupAll(".scroll-bar").stream()
                    .filter(ScrollBar.class::isInstance)
                    .map(ScrollBar.class::cast)
                    .toList()) {
                scrollBar.setOpacity(0);
                scrollBar.setPrefSize(0, 0);
                scrollBar.setDisable(true);
            }
        });

    }

    private ObservableList<AnchorPane> loadFXMLIntoList(String fxmlPath, int count) {
        ObservableList<AnchorPane> list = FXCollections.observableArrayList();
        for (int i = 0; i < count; i++) {
            try {
                AnchorPane anchorPane = new FXMLLoader(getClass().getResource(fxmlPath)).load();
                list.add(anchorPane);
            } catch (IOException e) {
                System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
                //e.printStackTrace();
            }
        }
        return list;
    }

    private ListView<AnchorPane> createListView(ObservableList<AnchorPane> items) {
        ListView<AnchorPane> listView = new ListView<>(items);
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
        return listView;
    }

}