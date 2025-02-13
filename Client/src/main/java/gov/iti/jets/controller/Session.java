package gov.iti.jets.controller;

import gov.iti.jets.model.Status;
import gov.iti.jets.model.User;
import gov.iti.jets.view.ChatsController;
import gov.iti.jets.view.HomeController;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.HashMap;

public class Session {
    public static User user;
    static HomeController homeController;
    static ObservableList<AnchorPane> myLastChatList;
    static ObservableList<AnchorPane> myAllList;
    static ObservableList<AnchorPane> myOnlineList;
    static ObservableList<AnchorPane> myPendingList;
    static ObservableList<AnchorPane> myBlockedList;
    static HashMap<String, ChatsController> chatsControllerMap = new HashMap<>();
    private static Session instance;
    private static Stage loadStage;
    private String loggedPhoneNumber;

    private Session() {
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public static void updateStatus(String contactPhone, Status status) throws RemoteException {
        ChatsController chatsController = chatsControllerMap.get(contactPhone);
        user.setStatus(status);
        if (chatsController != null) {
            chatsController.updateStatus(status);
        }
    }

    public static Stage getLoadStage() {
        return loadStage;
    }

    public static void setLoadStage(Stage loadStage) {
        Session.loadStage = loadStage;
    }

    public String getPhoneNumber() {
        return loggedPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.loggedPhoneNumber = phoneNumber;
    }

    public void clearSession() {
        homeController = null;
        myLastChatList = null;
        myOnlineList = null;
        myBlockedList = null;
        myAllList = null;
        myPendingList = null;
        chatsControllerMap.clear();
        user = null;
        loggedPhoneNumber = null;
        instance = null;
    }
}
