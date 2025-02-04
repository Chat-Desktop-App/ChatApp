package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.services.interfaces.LoadHome;
import gov.iti.jets.view.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class HomeServiceController {
    LoadHome homeController = RMIConnector.getRmiConnector().getLoadHome();

    String phoneNumber = "0987654321";

    public ObservableList<AnchorPane> getMyContact(){
        String fxmlPath = "/gov/iti/jets/fxml/allChats.fxml" ;
        ObservableList<AnchorPane> observableList = FXCollections.observableArrayList();
        try {
            List<ContactUser> list = homeController.getMyContact(phoneNumber);
            for (ContactUser contactUser : list) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                AnchorPane anchorPane = loader.load();
                AllChatsController controller = loader.getController();
                controller.setContact(contactUser);
                observableList.add(anchorPane);
            }
        } catch (IOException e) {
            System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
        }
        return observableList;
    }

    public ObservableList<AnchorPane> getPendingContacts()  {
        String fxmlPath = "/gov/iti/jets/fxml/pendingCard.fxml";
        ObservableList<AnchorPane> observableList = FXCollections.observableArrayList();
        try {
            List<ContactUser> list = homeController.getPendingContacts(phoneNumber);
            for (ContactUser contactUser : list) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                AnchorPane anchorPane = loader.load();
                PendingCardController controller = loader.getController();
                controller.setContact(contactUser);
                observableList.add(anchorPane);
            }
        } catch (IOException e) {
            System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
        }
        return observableList;
    }

    public ObservableList<AnchorPane> getBlockedContacts()  {
        String fxmlPath = "/gov/iti/jets/fxml/blockedCard.fxml";
        ObservableList<AnchorPane> observableList = FXCollections.observableArrayList();
        try {
            List<ContactUser> list = homeController.getBlockedContacts(phoneNumber);
            for (ContactUser contactUser : list) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                AnchorPane anchorPane = loader.load();
                BlockedCardController controller = loader.getController();
                controller.setContact(contactUser);
                observableList.add(anchorPane);
            }
        } catch (IOException e) {
            System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
        }
        return observableList;
    }

    public ObservableList<AnchorPane> getAllContacts() {
        String fxmlPath = "/gov/iti/jets/fxml/allCard.fxml";
        ObservableList<AnchorPane> observableList = FXCollections.observableArrayList();
        try {
            List<ContactUser> list = homeController.getMyContact(phoneNumber);
            for (ContactUser contactUser : list) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                AnchorPane anchorPane = loader.load();
                AllCardController controller = loader.getController();
                controller.setContact(contactUser);
                observableList.add(anchorPane);
            }
        } catch (IOException e) {
            System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
        }
        return observableList;
    }

    public ObservableList<AnchorPane> getOnlineContacts()  {
        String fxmlPath = "/gov/iti/jets/fxml/onlineCard.fxml";
        ObservableList<AnchorPane> observableList = FXCollections.observableArrayList();
        try {
            List<ContactUser> list = homeController.getOnlineContacts(phoneNumber);
            for (ContactUser contactUser : list) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                AnchorPane anchorPane = loader.load();
                OnlineCardController controller = loader.getController();
                controller.setContact(contactUser);
                observableList.add(anchorPane);
            }
        } catch (IOException e) {
            System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
        }
        return observableList;
    }

}
