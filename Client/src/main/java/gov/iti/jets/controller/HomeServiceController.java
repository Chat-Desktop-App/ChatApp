package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.*;
import gov.iti.jets.services.interfaces.LoadHome;
import gov.iti.jets.view.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

import static gov.iti.jets.controller.Session.*;

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
        String fxmlPath = "/gov/iti/jets/fxml/Chats.fxml";
        try {
            myLastChatList = FXCollections.observableArrayList();
            List<Chatable> list = loadHome.getLastChats(user.getPhoneNumber());

            if (list != null) {
                for (Chatable chatable : list) {
                    FXMLLoader loader = new FXMLLoader(HomeServiceController.class.getResource(fxmlPath));
                    AnchorPane anchorPane = loader.load();
                    ChatsController controller = loader.getController();
                    anchorPane.setUserData(controller);
                    if (chatable instanceof ContactUser con) {
                        chatsControllerMap.put(con.getPhoneNumber(), controller);
                    } else {
                        chatsControllerMap.put(String.valueOf(((Group) chatable).getGroupId()), controller);
                    }

                    controller.setChat(chatable);
                    addListener(chatable);
                    myLastChatList.add(anchorPane);
                }
            }
        } catch (RemoteException e) {
            System.out.println("Error when loading HomeService: " + e.getMessage());
            loadHome = RMIConnector.rmiReconnect().getLoadHome();
            return getLast();
        } catch (IOException e) {
            System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
        }
        return myLastChatList;
    }

    public static ObservableList<AnchorPane> getPendingContacts() {
//        if (myPendingList == null) {
        String fxmlPath = "/gov/iti/jets/fxml/pendingCard.fxml";
        try {
            myPendingList = FXCollections.observableArrayList();
            List<ContactUser> list = loadHome.getPendingContacts(user.getPhoneNumber());
            if (list != null) myPendingList.addAll(getContacts(fxmlPath, list));
        } catch (IOException e) {
            loadHome = RMIConnector.rmiReconnect().getLoadHome();
            return getPendingContacts();
        }
//        }
        return myPendingList;
    }

    public static ObservableList<AnchorPane> getBlockedContacts() {
//        if(myBlockedList == null) {
        String fxmlPath = "/gov/iti/jets/fxml/blockedCard.fxml";
        try {
            myBlockedList = FXCollections.observableArrayList();
            List<ContactUser> list = loadHome.getBlockedContacts(user.getPhoneNumber());
            if (list != null) myBlockedList.addAll(getContacts(fxmlPath, list));
        } catch (IOException e) {
            loadHome = RMIConnector.rmiReconnect().getLoadHome();
            return getBlockedContacts();
        }
//        }
        return myBlockedList;
    }

    public static ObservableList<AnchorPane> getAllContacts() {
//        if (myAllList == null) {
        String fxmlPath = "/gov/iti/jets/fxml/allCard.fxml";
        try {
            myAllList = FXCollections.observableArrayList();
            List<ContactUser> list = loadHome.getAllContacts(user.getPhoneNumber());
            if (list != null) myAllList.addAll(getContacts(fxmlPath, list));
        } catch (IOException e) {
            loadHome = RMIConnector.rmiReconnect().getLoadHome();
            return getAllContacts();
        }
//        }
        return myAllList;
    }

    public static ObservableList<AnchorPane> getOnlineContacts() {
//        if(myOnlineList == null) {
        String fxmlPath = "/gov/iti/jets/fxml/onlineCard.fxml";
        try {
            myOnlineList = FXCollections.observableArrayList();
            List<ContactUser> list = loadHome.getOnlineContacts(user.getPhoneNumber());
            if (list != null) myOnlineList.addAll(getContacts(fxmlPath, list));
        } catch (IOException e) {
            loadHome = RMIConnector.rmiReconnect().getLoadHome();
            return getOnlineContacts();
        }
//        }
        return myOnlineList;
    }

    public static boolean updateLastContactList(ContactUser contact, ContactStatus status, ContactStatus prevStatus) {
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
                anchorPane.setUserData(controller);
                controller.setChat(contact);
                addListener(contact);
                myLastChatList.add(anchorPane);
            }
            return flag;
        } catch (RemoteException e) {
            System.out.println("Error when updating contact");
            loadHome = RMIConnector.rmiReconnect().getLoadHome();
            return updateLastContactList(contact, status, prevStatus);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static boolean addToLastContactList(Chatable chatable) {
        String fxmlPath = "/gov/iti/jets/fxml/Chats.fxml";
        try {
            if(chatable != null){
                    FXMLLoader loader = new FXMLLoader(HomeServiceController.class.getResource(fxmlPath));
                    AnchorPane anchorPane = loader.load();
                    ChatsController controller = loader.getController();
                    anchorPane.setUserData(controller);
                    if (chatable instanceof ContactUser con) {
                        chatsControllerMap.put(con.getPhoneNumber(), controller);
                    } else {
                        chatsControllerMap.put(String.valueOf(((Group)chatable).getGroupId()), controller);
                    }
                    controller.setChat(chatable);
                    addListener(chatable);
                   Platform.runLater( () -> {myLastChatList.add(0, anchorPane);});
            }
        } catch (IOException e) {
            System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
            return false;
        }
        return true;
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

    private static void addListener(Chatable chatable) {
        if (chatable.getLastChatAt() != null) {
            chatable.setLastChatAtListener(new ChangeListener<LocalDateTime>() {
                @Override
                public void changed(ObservableValue<? extends LocalDateTime> observable, LocalDateTime oldValue, LocalDateTime newValue) {
                    // Sort the list whenever lastChatAt changes
                    Platform.runLater(() -> {
                        FXCollections.sort(myLastChatList, (o1, o2) -> {
                            ChatsController controller1 = (ChatsController) o1.getUserData();
                            ChatsController controller2 = (ChatsController) o2.getUserData();

                            Chatable chatable1 = controller1 != null ? controller1.getChatable() : null;
                            Chatable chatable2 = controller2 != null ? controller2.getChatable() : null;

                            LocalDateTime lastChatAt1 = chatable1 != null ? chatable1.getLastChatAt() : LocalDateTime.MIN;
                            LocalDateTime lastChatAt2 = chatable2 != null ? chatable2.getLastChatAt() : LocalDateTime.MIN;

                            return lastChatAt2.compareTo(lastChatAt1); // Sorting descending (latest first)
                        });
                    });
                }
            });
        }
    }
}

