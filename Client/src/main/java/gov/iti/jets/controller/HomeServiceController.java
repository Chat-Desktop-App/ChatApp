package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.Chatable;
import gov.iti.jets.model.ContactStatus;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.Notifications;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.LoadHome;
import gov.iti.jets.view.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import static gov.iti.jets.controller.Session.user;
import static gov.iti.jets.controller.Session.homeController;
import static gov.iti.jets.controller.Session.myLastChatList;
import static gov.iti.jets.controller.Session.myAllList;
import static gov.iti.jets.controller.Session.myOnlineList;
import static gov.iti.jets.controller.Session.myPendingList;
import static gov.iti.jets.controller.Session.myBlockedList;

public class HomeServiceController {
    private static LoadHome loadHome = RMIConnector.getRmiConnector().getLoadHome();
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
        if(myLastChatList == null) {
            String fxmlPath = "/gov/iti/jets/fxml/Chats.fxml";
            try {
                List<Chatable> list = loadHome.getLastChats(user.getPhoneNumber());
                myLastChatList = FXCollections.observableArrayList();
                for (Chatable chatable : list) {
                    FXMLLoader loader = new FXMLLoader(HomeServiceController.class.getResource(fxmlPath));
                    AnchorPane anchorPane = loader.load();
                    ChatsController controller = loader.getController();
                    controller.setLastChat(chatable);
                    myLastChatList.add(anchorPane);
                }
            } catch (RemoteException e) {
                System.out.println("Error when loadHomeservice: " + e.getMessage());
                loadHome = RMIConnector.rmiReconnect().getLoadHome();
                return getLast();
            } catch (IOException e) {
                System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
            }
        }
        return myLastChatList;
    }

    public static ObservableList<AnchorPane> getPendingContacts() {
        if (myPendingList == null) {
            String fxmlPath = "/gov/iti/jets/fxml/pendingCard.fxml";
            try {
                List<ContactUser> list = loadHome.getPendingContacts(user.getPhoneNumber());
                myPendingList = FXCollections.observableArrayList();
                myPendingList.addAll(getContacts(fxmlPath, list));
            } catch (IOException e) {
                loadHome = RMIConnector.rmiReconnect().getLoadHome();
                return getPendingContacts();
            }
        }
        return myPendingList;
    }

    public static ObservableList<AnchorPane> getBlockedContacts() {
        if(myBlockedList == null) {
            String fxmlPath = "/gov/iti/jets/fxml/blockedCard.fxml";
            try {
                List<ContactUser> list = loadHome.getBlockedContacts(user.getPhoneNumber());
                myBlockedList = FXCollections.observableArrayList();
                myBlockedList.addAll(getContacts(fxmlPath, list));
            } catch (IOException e) {
                loadHome = RMIConnector.rmiReconnect().getLoadHome();
                return getBlockedContacts();
            }
        }
        return myBlockedList;
    }

    public static ObservableList<AnchorPane> getAllContacts() {
        if (myAllList == null) {
            String fxmlPath = "/gov/iti/jets/fxml/allCard.fxml";
            try {
                List<ContactUser> list = loadHome.getAllContacts(user.getPhoneNumber());
                myAllList = FXCollections.observableArrayList();
                myAllList.addAll(getContacts(fxmlPath, list));
            } catch (IOException e) {
                loadHome = RMIConnector.rmiReconnect().getLoadHome();
                return getAllContacts();
            }
        }
        return myAllList;
    }

    public static ObservableList<AnchorPane> getOnlineContacts() {
        if(myOnlineList == null) {
            String fxmlPath = "/gov/iti/jets/fxml/onlineCard.fxml";
            try {
                List<ContactUser> list = loadHome.getOnlineContacts(user.getPhoneNumber());
                myOnlineList = FXCollections.observableArrayList();
                myOnlineList.addAll(getContacts(fxmlPath, list));
            } catch (IOException e) {
                loadHome = RMIConnector.rmiReconnect().getLoadHome();
                return getOnlineContacts();
            }
        }
        return myOnlineList;
    }

    public static boolean updateContact(ContactUser contact, ContactStatus status, ContactStatus prevStatus) {
        try {
            boolean flag = loadHome.updateContact(user.getPhoneNumber(), contact.getPhoneNumber(), status);
            if (flag) {
                if (prevStatus == ContactStatus.BLOCKED) {
                    getBlockedContacts();
                } else if (prevStatus == ContactStatus.PENDING) {
                    getPendingContacts();
                }
                String fxmlPath = "/gov/iti/jets/fxml/Chats.fxml";
                FXMLLoader loader = new FXMLLoader(HomeServiceController.class.getResource(fxmlPath));
                AnchorPane anchorPane = loader.load();
                ChatsController controller = loader.getController();
                controller.setLastChat(contact);
                myLastChatList.add(anchorPane);
            }
            return flag;
        } catch (RemoteException e) {
            System.out.println("Error when updating contact");
            loadHome = RMIConnector.rmiReconnect().getLoadHome();
            return updateContact(contact, status, prevStatus);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

        public static HomeController getHomeController() {
        return homeController;
    }

    public static void setHomeController(HomeController homeController) {
        Session.homeController = homeController;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Session.user = user;
    }


    }

