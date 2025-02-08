package gov.iti.jets.controller;

import gov.iti.jets.model.User;
import gov.iti.jets.view.HomeController;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

public class Session {
    static HomeController homeController;
    static ObservableList<AnchorPane> myLastChatList ;
    static  ObservableList<AnchorPane> myAllList ;
    static  ObservableList<AnchorPane> myOnlineList ;
    static  ObservableList<AnchorPane> myPendingList ;
    static  ObservableList<AnchorPane> myBlockedList ;
    static User user ;

}
