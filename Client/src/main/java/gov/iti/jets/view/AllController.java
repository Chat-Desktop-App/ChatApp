package gov.iti.jets.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

public class AllController {
    private LoadHome loadHome;
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
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'all.fxml'.";
        assert onlineText != null : "fx:id=\"onlineText\" was not injected: check your FXML file 'all.fxml'.";


    }

    public void setLoadHome(LoadHome loadHome) {
        this.loadHome = loadHome;
        loadAll();
    }

    private void loadAll() {
        ObservableList<AnchorPane> observableList = loadFXMLIntoList();
        createListView(observableList);
    }

    public ObservableList<AnchorPane> loadFXMLIntoList()  {
        String fxmlPath = "/gov/iti/jets/fxml/allCard.fxml";
        ObservableList<AnchorPane> list = FXCollections.observableArrayList();
        try {
            List<ContactUser> myContact = loadHome.getMyContact("1234567890");
            for (ContactUser contactUser : myContact) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                AnchorPane anchorPane = loader.load();
                AllCardController controller = loader.getController();
                controller.setContactUser(contactUser);
                list.add(anchorPane);
            }

        } catch (IOException e) {
            System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
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
