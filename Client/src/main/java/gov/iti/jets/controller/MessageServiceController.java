package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.GroupMessage;
import gov.iti.jets.model.Message;
import gov.iti.jets.model.Recipient;
import gov.iti.jets.services.interfaces.MessagingService;
import gov.iti.jets.view.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import static gov.iti.jets.controller.Session.user;
import static gov.iti.jets.controller.Session.chatsControllerMap;


public class MessageServiceController {
    private static MessagingService messagingService = RMIConnector.getRmiConnector().getMessagingService();
    private static ChatAreaController activeChat;



    public static void receiveMessage(Message message) {
        if(activeChat != null){
            if (message.getRecipient() == Recipient.PRIVATE && activeChat.isContact()){
                if(message.getSenderId().equals(activeChat.getContactUser().getPhoneNumber())){
                    FXMLLoader loader = new FXMLLoader(MessageServiceController.class.getResource("/gov/iti/jets/fxml/received-message.fxml"));
                    try {
                        HBox hBox = loader.load();
                        ((ReceiveMessageController) loader.getController()).setMessage(message);
                        activeChat.receivedMessage(hBox);
                    } catch (IOException e) {
                        System.out.println("error load received-message");
                    }
                    return;
                }
            }else if (message.getRecipient() == Recipient.GROUP && !activeChat.isContact()){
                System.out.println("111111111111");
                if(message.getGroupId() == activeChat.getGroup().getGroupId()){
                    FXMLLoader loader = new FXMLLoader(MessageServiceController.class.getResource("/gov/iti/jets/fxml/receiveGroupMessage.fxml"));
                    try {
                        HBox hBox = loader.load();
                        System.out.println("2222222222");
                        ((ReceiveGroupMessageController) loader.getController()).setMessage((GroupMessage) message);
                        activeChat.receivedMessage(hBox);
                    } catch (IOException e) {
                        System.out.println("error load receiveGroupMessage");
                    }
                    return;
                }
            }
        }
        ChatsController controller ;
        if(message.getRecipient() == Recipient.PRIVATE){
            controller = chatsControllerMap.get(message.getSenderId());
        } else {
            controller = chatsControllerMap.get(message.getGroupId()+"");
        }
        if (controller != null) {
            controller.addMessageToCounter();
        }
    }


    public static HBox sendMessage(Message message) {
        boolean flag;
        HBox hBox = null;
        try {
            message.setSenderId(user.getPhoneNumber());
            FXMLLoader loader = new FXMLLoader(MessageServiceController.class.getResource("/gov/iti/jets/fxml/send-message.fxml"));
            flag = messagingService.sendMessage(message);
            if (flag) {
                hBox = loader.load();
                ((SendMessageController) loader.getController()).setMessage(message);
            }
        } catch (RemoteException e) {
            messagingService = RMIConnector.rmiReconnect().getMessagingService();
            return sendMessage(message);
        } catch (IOException e) {
            System.out.println("failed load message");
        }
        return hBox;
    }

    public static ObservableList<HBox> getDirectMessages(String contactPhoneNumber) {
        ObservableList<HBox> messages = FXCollections.observableArrayList();

        String sendPath = "/gov/iti/jets/fxml/send-message.fxml";
        String receivePath = "/gov/iti/jets/fxml/received-message.fxml";
        try {
            List<Message> list = messagingService.getDirectMessages(user.getPhoneNumber(), contactPhoneNumber);
            for (Message m : list) {
                FXMLLoader loader;
                if (m.getSenderId().equals(user.getPhoneNumber())) {
                    loader = new FXMLLoader(MessageServiceController.class.getResource(sendPath));
                } else {
                    loader = new FXMLLoader(MessageServiceController.class.getResource(receivePath));
                }
                HBox hBox = loader.load();
                Object controller = loader.getController();

                if (controller instanceof SendMessageController sendMessageController) {
                    sendMessageController.setMessage(m);
                } else if (controller instanceof ReceiveMessageController receiveMessageController) {
                    receiveMessageController.setMessage(m);
                }
                messages.add(hBox);
            }
        } catch (RemoteException e) {
            System.out.println("Error when getMessagingService: " + e.getMessage());
            messagingService = RMIConnector.rmiReconnect().getMessagingService();
            return getDirectMessages(contactPhoneNumber);
        } catch (IOException e) {
            System.out.println("Error when loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
        return messages;
    }

    public static ObservableList<HBox> getGroupMessages(int groupId) {
        String sendPath = "/gov/iti/jets/fxml/send-message.fxml";
        String receivePath = "/gov/iti/jets/fxml/receiveGroupMessage.fxml";
        ObservableList<HBox> messages = FXCollections.observableArrayList();
        try {
            List<GroupMessage> list = messagingService.getMessagesByGroupId(groupId);
            for (GroupMessage m : list) {
                FXMLLoader loader;
                if (m.getSenderId().equals(user.getPhoneNumber())) {
                    loader = new FXMLLoader(MessageServiceController.class.getResource(sendPath));
                } else {
                    loader = new FXMLLoader(MessageServiceController.class.getResource(receivePath));
                }
                HBox hBox = loader.load();
                Object controller = loader.getController();

                if (controller instanceof SendMessageController sendMessageController) {
                    sendMessageController.setMessage(m);
                } else if (controller instanceof ReceiveMessageController receiveMessageController) {
                    receiveMessageController.setMessage(m);
                } else if (controller instanceof ReceiveGroupMessageController receiveGroupMessageController) {
                    receiveGroupMessageController.setMessage(m);
                }
                messages.add(hBox);
            }
        } catch (RemoteException e) {
            System.out.println("Error when getMessagingService: " + e.getMessage());
            messagingService = RMIConnector.rmiReconnect().getMessagingService();
            return getGroupMessages(groupId);
        } catch (IOException e) {
            System.out.println("Error when loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
        return messages;
    }

    public static ChatAreaController getActiveChat() {
        return activeChat;
    }

    public static void setActiveChat(ChatAreaController activeChat) {
        MessageServiceController.activeChat = activeChat;
    }
}
