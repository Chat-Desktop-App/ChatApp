package gov.iti.jets.controller;

import gov.iti.jets.model.User;
import gov.iti.jets.view.ChatsController;
import gov.iti.jets.view.HomeController;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;

public class Session {
    static HomeController homeController;
    static ObservableList<AnchorPane> myLastChatList ;
    static  ObservableList<AnchorPane> myAllList ;
    static  ObservableList<AnchorPane> myOnlineList ;
    static  ObservableList<AnchorPane> myPendingList ;
    static  ObservableList<AnchorPane> myBlockedList ;
    public static User user ;
    static HashMap<String, ChatsController> chatsControllerMap = new HashMap<>() ;
    public static void clearSession(){
        homeController= null;
        myLastChatList= null;
        myOnlineList = null;
        myBlockedList = null;
        myAllList = null;
        myPendingList = null;
        chatsControllerMap.clear();
        user = null;
    }
}
