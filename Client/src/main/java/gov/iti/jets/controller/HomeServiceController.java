package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.ContactStatus;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.Chatable;
import gov.iti.jets.services.interfaces.LoadHome;
import gov.iti.jets.view.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class HomeServiceController {
    private static LoadHome loadHome = RMIConnector.getRmiConnector().getLoadHome();
    private static final String phoneNumber = "1234567890";
    private static HomeController homeController;
    private static ObservableList<AnchorPane> myContactList ;
    private static ObservableList<AnchorPane> myPendingList ;
    private static ObservableList<AnchorPane> myBlockedList ;;
    private static ObservableList<AnchorPane> myOnlineList ;;
    private static ObservableList<AnchorPane> myAllList ;;
    private static ObservableList<AnchorPane> myLastChatList ;;

    // Generic method to load contacts
    private static ObservableList<AnchorPane> getContacts(String fxmlPath, List<ContactUser> contactList) {
        ObservableList<AnchorPane> observableList = FXCollections.observableArrayList();
        try {
            for (ContactUser contactUser : contactList) {
                FXMLLoader loader = new FXMLLoader(HomeServiceController.class.getResource(fxmlPath));
                AnchorPane anchorPane = loader.load();
                Object controller = loader.getController();
                if (controller instanceof PendingCardController) {
                    ((PendingCardController) controller).setContact(contactUser);
                } else if (controller instanceof BlockedCardController) {
                    ((BlockedCardController) controller).setContact(contactUser);
                } else if (controller instanceof AllCardController) {
                    ((AllCardController) controller).setContact(contactUser);
                } else if (controller instanceof OnlineCardController) {
                    ((OnlineCardController) controller).setContact(contactUser);
                }

                observableList.add(anchorPane);
            }
        } catch (IOException e) {
            System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
        }
        return observableList;
    }

    public static ObservableList<AnchorPane> getLast() {
        String fxmlPath = "/gov/iti/jets/fxml/Chats.fxml";
        try {
            List<Chatable> list = loadHome.getLastChats(phoneNumber);
            ObservableList<AnchorPane> observableList = FXCollections.observableArrayList();
            for (Chatable chatable : list) {
                FXMLLoader loader = new FXMLLoader(HomeServiceController.class.getResource(fxmlPath));
                AnchorPane anchorPane = loader.load();
                ChatsController controller = loader.getController();
                controller.setLastChat(chatable);
                observableList.add(anchorPane);
            }
            myLastChatList = observableList;
        } catch (RemoteException e) {
            loadHome =RMIConnector.rmiReconnector().getLoadHome();
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
        }
        return myLastChatList;
    }

    public static ObservableList<AnchorPane> getPendingContacts() {
        String fxmlPath = "/gov/iti/jets/fxml/pendingCard.fxml";
        try {
            List<ContactUser> list = loadHome.getPendingContacts(phoneNumber);
            if (myPendingList == null){
                myPendingList = getContacts(fxmlPath, list);
            }else {
                myPendingList.clear();
                myPendingList.addAll(getContacts(fxmlPath, list));
            }
        } catch (IOException e) {
            loadHome =RMIConnector.rmiReconnector().getLoadHome();
            return getPendingContacts();
        }
        return myPendingList;
    }

    public static ObservableList<AnchorPane> getBlockedContacts() {
        String fxmlPath = "/gov/iti/jets/fxml/blockedCard.fxml";
        try {
            List<ContactUser> list = loadHome.getBlockedContacts(phoneNumber);
            if (myBlockedList == null){
                myBlockedList = getContacts(fxmlPath, list);
            }else {
                myBlockedList.clear();
                myBlockedList.addAll(getContacts(fxmlPath, list));
            }
        } catch (IOException e) {
            loadHome =RMIConnector.rmiReconnector().getLoadHome();
            return getBlockedContacts();
        }
        return myBlockedList;
    }

    public static ObservableList<AnchorPane> getAllContacts() {
        String fxmlPath = "/gov/iti/jets/fxml/allCard.fxml";
        try {
            List<ContactUser> list = loadHome.getAllContacts(phoneNumber);
            if (myAllList == null){
                myAllList = getContacts(fxmlPath, list);
            }else {
                myAllList.clear();
                myAllList.addAll(getContacts(fxmlPath, list));
            }
        } catch (IOException e) {
            loadHome =RMIConnector.rmiReconnector().getLoadHome();
            return getAllContacts();
        }
        return myAllList;
    }

    public static ObservableList<AnchorPane> getOnlineContacts() {
        String fxmlPath = "/gov/iti/jets/fxml/onlineCard.fxml";
        try {
            List<ContactUser> list = loadHome.getOnlineContacts(phoneNumber);
            if (myOnlineList == null){
                myOnlineList = getContacts(fxmlPath, list);
            }else {
                myOnlineList.clear();
                myOnlineList.addAll(getContacts(fxmlPath, list));
            }
        } catch (IOException e) {
            loadHome =RMIConnector.rmiReconnector().getLoadHome();
            return getOnlineContacts();
        }
        return myOnlineList;
    }

    public static boolean updateContact(String contactsPhoneNumber, ContactStatus status , ContactStatus prevStatus) {
        try {
            boolean flag = loadHome.updateContact(phoneNumber, contactsPhoneNumber, status);
            if (flag) {
                if(prevStatus == ContactStatus.BLOCKED){
                    getBlockedContacts();
                } else if (prevStatus == ContactStatus.PENDING) {
                    getPendingContacts();
                }
                getLast();
            }
            return flag;
        } catch (RemoteException e) {
            System.out.println("Error when updating contact");
        }
        return false;
    }

    public static void setHomeController(HomeController homeController) {
        HomeServiceController.homeController = homeController;
    }

    public static HomeController getHomeController() {
        return homeController;
    }
}
