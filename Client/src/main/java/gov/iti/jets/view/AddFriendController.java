package gov.iti.jets.view;

import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import gov.iti.jets.controller.Session;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.ContactService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class AddFriendController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addFriend;

    @FXML
    private ListView<AnchorPane> listViewOf_Friend_Request;

    @FXML
    private TextField phoneField;

    @FXML
    private Button sendInvetation;
    private ContactService contactService;
    private ObservableList<AnchorPane> friendList = FXCollections.observableArrayList();
    private Set<String> addedPhoneNumbers = new HashSet<>();

    @FXML
    void handleAddFriend(ActionEvent event) {
        String phoneNumber = phoneField.getText().trim();
        if (phoneNumber.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "missing credentials", "you must enter a phone number");
            return;
        }
        if (addedPhoneNumbers.contains(phoneNumber)) {
            showAlert(Alert.AlertType.WARNING, "Duplicate Entry", "This phone number is already added");
            return;
        }
        try {
            User user = contactService.findUserByPhoneNumber(phoneNumber);
            if (user != null) {
                addUserToList(user);
                phoneField.clear();
            }
            else {
                showAlert(Alert.AlertType.ERROR, "User not found", "No user is found with this phone number");
            }
        } catch (RemoteException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to fetch user data");
            e.printStackTrace();
        }
    }



    @FXML
    void handleSendInvitation(ActionEvent event) {
        if (friendList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Users", "No users to send request to");
            return;
        }
        try {
            for (AnchorPane cell : friendList) {
                AddFriendCellController controller = (AddFriendCellController) cell.getUserData();
                String receiverPhone = controller.getUser().getPhoneNumber();
                String senderPhone = Session.getInstance().getPhoneNumber();
                boolean requestSent = contactService.sendFriendRequest(senderPhone, receiverPhone);
                if(!requestSent) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to send Request to " + receiverPhone);
                }
            }
            showAlert(Alert.AlertType.INFORMATION, "Success", "Friend requests sent successfully.");
            friendList.clear();
            addedPhoneNumbers.clear();
        } catch (RemoteException exception) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to send requests");
            exception.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        try {
            contactService = (ContactService) Naming.lookup("rmi://localhost:1099/ContactService");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Connection Error", "Failed to connect to server.");
        }

        listViewOf_Friend_Request.setItems(friendList);
        /*assert addFriend != null : "fx:id=\"addFriend\" was not injected: check your FXML file 'addFriend.fxml'.";
        assert listViewOf_Friend_Request != null : "fx:id=\"listViewOf_Friend_Request\" was not injected: check your FXML file 'addFriend.fxml'.";
        assert phoneField != null : "fx:id=\"phoneField\" was not injected: check your FXML file 'addFriend.fxml'.";
        assert sendInvetation != null : "fx:id=\"sendInvetation\" was not injected: check your FXML file 'addFriend.fxml'.";

        ObservableList<AnchorPane> observableList = loadFXMLIntoList("/gov/iti/jets/fxml/addFriendCell.fxml", 20);
        listViewOf_Friend_Request.setItems(observableList);

        // Customize the ListView appearance
        listViewOf_Friend_Request.setStyle("-fx-background-color: white;");
        listViewOf_Friend_Request.setCellFactory(lv -> new ListCell<>() {
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

        listViewOf_Friend_Request.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            for (ScrollBar scrollBar : listViewOf_Friend_Request.lookupAll(".scroll-bar").stream()
                    .filter(ScrollBar.class::isInstance)
                    .map(ScrollBar.class::cast)
                    .toList()) {
                scrollBar.setOpacity(0);
                scrollBar.setPrefSize(0, 0);
                scrollBar.setDisable(true);
            }
        });*/

    }
    private void addUserToList(User user) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gov/iti/jets/fxml/addFriendCell.fxml"));
            AnchorPane cell = loader.load();
            AddFriendCellController controller = loader.getController();
            controller.setUser(user);
            friendList.add(cell);
            addedPhoneNumbers.add(user.getPhoneNumber());
        } catch(IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load user cell");
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    /*private ObservableList<AnchorPane> loadFXMLIntoList(String fxmlPath, int count) {
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
    }*/


    /*private ListView<AnchorPane> createListView(ObservableList<AnchorPane> items) {
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
    }*/

}
