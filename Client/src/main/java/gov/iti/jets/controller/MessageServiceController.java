package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.GroupMessage;
import gov.iti.jets.model.Message;
import gov.iti.jets.services.interfaces.MessagingService;
import gov.iti.jets.view.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;




public class MessageServiceController {
    private static MessagingService messagingService = RMIConnector.getRmiConnector().getMessagingService();
    private static String phoneNumber = "1234567890";
    private static ObservableList<HBox> messages;

    public boolean sendMessage(Message message) {
        try {
            return messagingService.sendMessage(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<HBox> getDirectMessages(String contactPhoneNumber) {
        String sendPath = "/gov/iti/jets/fxml/send-message.fxml";
        String receivePath = "/gov/iti/jets/fxml/received-message.fxml";
        ObservableList<HBox> observableList = FXCollections.observableArrayList();

        try {
            List<Message> list = messagingService.getDirectMessages(phoneNumber,contactPhoneNumber);
            for (Message m : list) {
                FXMLLoader loader;
                if (m.getSenderId().equals(phoneNumber)) {
                    loader = new FXMLLoader(MessageServiceController.class.getResource(sendPath));
                } else {
                    loader = new FXMLLoader(MessageServiceController.class.getResource(receivePath));
                }
                HBox hBox = loader.load();
                Object controller = loader.getController();

                if (controller instanceof SendMessageController sendMessageController) {
                    sendMessageController.setMessage(m);
                } else if (controller instanceof receiveMessageController receiveMessageController) {
                    receiveMessageController.setMessage(m);
                }
                observableList.add(hBox);
            }
            messages = observableList;
        } catch (RemoteException e) {
            messagingService = RMIConnector.rmiReconnect().getMessagingService();
        } catch (IOException e) {
            System.out.println("Error when loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
        return messages;
    }

    public static ObservableList<HBox> getGroupMessages(int groupId) {
        String sendPath = "/gov/iti/jets/fxml/send-message.fxml";
        String receivePath = "/gov/iti/jets/fxml/receiveGroupMessage.fxml";
        ObservableList<HBox> observableList = FXCollections.observableArrayList();

        try {
            List<GroupMessage> list = messagingService.getMessagesByGroupId(groupId);
            for (GroupMessage m : list) {
                FXMLLoader loader;
                if (m.getSenderId().equals(phoneNumber)) {
                    loader = new FXMLLoader(MessageServiceController.class.getResource(sendPath));
                } else {
                    loader = new FXMLLoader(MessageServiceController.class.getResource(receivePath));
                }
                HBox hBox = loader.load();
                Object controller = loader.getController();

                if (controller instanceof SendMessageController sendMessageController) {
                    sendMessageController.setMessage(m);
                } else if (controller instanceof receiveMessageController receiveMessageController) {
                    receiveMessageController.setMessage(m);
                } else if (controller instanceof receiveGroupMessageController receiveMessageController) {
                    receiveMessageController.setMessage(m);
                }
                observableList.add(hBox);
            }
            messages = observableList;
        } catch (RemoteException e) {
            messagingService = RMIConnector.rmiReconnect().getMessagingService();
        } catch (IOException e) {
            System.out.println("Error when loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
        return messages;
    }
}
