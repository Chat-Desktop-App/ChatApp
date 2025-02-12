package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;
import gov.iti.jets.model.*;
import gov.iti.jets.services.interfaces.MessagingService;
import gov.iti.jets.view.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

import static gov.iti.jets.controller.Session.chatsControllerMap;
import static gov.iti.jets.controller.Session.user;


public class MessageServiceController {
    private static MessagingService messagingService = RMIConnector.getRmiConnector().getMessagingService();
    private static ChatAreaController activeChat;



    public static void receiveMessage(Message message) {
        if (activeChat != null) {
            if (message.getRecipient() == Recipient.PRIVATE && activeChat.isContact()) {
                if (message.getSenderId().equals(activeChat.getContactUser().getPhoneNumber())) {
                    String receivedMessage = "/gov/iti/jets/fxml/received-message.fxml";
                    String receivedFile = "/gov/iti/jets/fxml/receivedFile.fxml";
                    String fxml = "";
                    fxml = message instanceof FileMessage ? receivedFile : receivedMessage;
                    FXMLLoader loader = new FXMLLoader(MessageServiceController.class.getResource(fxml));
                    try {
                        HBox hBox = loader.load();
                        if (message instanceof FileMessage fileMessage) {
                            ((ReceiveFileController) loader.getController()).setFileMessage(fileMessage);
                        } else {
                            ((ReceiveMessageController) loader.getController()).setMessage(message);
                        }
                        activeChat.receivedMessage(hBox);
                    } catch (IOException e) {
                        System.out.println("error load received-message");
                    }
                    return;
                }
            } else if (message.getRecipient() == Recipient.GROUP && !activeChat.isContact()) {
                if (message.getGroupId() == activeChat.getGroup().getGroupId()) {
                    String receivedMessage = "/gov/iti/jets/fxml/receiveGroupMessage.fxml";
                    String receivedFile = "/gov/iti/jets/fxml/receiveGroupFile.fxml";
                    String fxml = message instanceof FileMessage ? receivedFile : receivedMessage;
                    FXMLLoader loader = new FXMLLoader(MessageServiceController.class.getResource(fxml));
                    try {
                        HBox hBox = loader.load();
                        if (message instanceof FileMessage fileMessage) {
                            ((ReceiveGroupFileController) loader.getController()).setFileMessage(fileMessage);
                        } else {
                            ((ReceiveGroupMessageController) loader.getController()).setMessage((GroupMessage) message);
                        }
                        activeChat.receivedMessage(hBox);
                    } catch (IOException e) {
                        System.out.println("error load receiveGroupMessage");
                    }
                    return;
                }
            }
        }
        ChatsController controller;
        if (message.getRecipient() == Recipient.PRIVATE) {
            controller = chatsControllerMap.get(message.getSenderId());
        } else {
            controller = chatsControllerMap.get(message.getGroupId() + "");
        }
        if (controller != null) {
            controller.addMessageToCounter();
            controller.getChatable().setLastChatAt(message.getTimestamp().toLocalDateTime());
        }
    }


    public static void handleAIResponse(Message message) {
        if (ChatBotController.isAiActivated() &&
                message.getRecipient() == Recipient.PRIVATE &&
                message.getReceiverId().equals(Session.user.getPhoneNumber()) &&
                !(message instanceof FileMessage)) {
            //System.out.println(message.getContent());

            new Thread(() -> {
                try {
                    Thread.sleep(1000); // 3-second delay before responding (adjust as needed)

                    String AI_response = ChatBotController.chatWithAI(message.getContent());

                    Message message1 = new Message();
                    message1.setTimestamp(new Timestamp(System.currentTimeMillis()));
                    message1.setContent(AI_response);
                    message1.setReceiverId(message.getSenderId());
                    message1.setRecipient(Recipient.PRIVATE);

                    sendMessage(message1);

                    System.out.println("Sent AI response after delay: " + AI_response);

                } catch (IOException e) {
                    System.out.println("Failed to get AI response: " + e.getMessage());
                } catch (InterruptedException e) {
                    System.out.println("Response delay interrupted: " + e.getMessage());
                }
            }).start();
        }

    }



    public static HBox sendMessage(Message message) {
        boolean flag;
        HBox hBox = null;
        String sendMessage = "/gov/iti/jets/fxml/send-message.fxml";
        String sendFile = "/gov/iti/jets/fxml/sendFile.fxml";
        String fxml = "";
        try {
            message.setSenderId(user.getPhoneNumber());
            fxml = message instanceof FileMessage ? sendFile : sendMessage;

            FXMLLoader loader = new FXMLLoader(MessageServiceController.class.getResource(fxml));
            flag = messagingService.sendMessage(message);
            if (flag) {
                hBox = loader.load();
                if (message instanceof FileMessage) {
                    ((SendFileController) loader.getController()).setFileMessage((FileMessage) message);
                } else {
                    ((SendMessageController) loader.getController()).setMessage(message);
                }
                if (message.getRecipient() == Recipient.PRIVATE) {
                    ChatsController chatsController = chatsControllerMap.get(message.getReceiverId());
                    chatsController.getChatable().setLastChatAt(message.getTimestamp().toLocalDateTime());
                } else {
                    chatsControllerMap.get(String.valueOf(message.getGroupId())).getChatable().setLastChatAt(message.getTimestamp().toLocalDateTime());
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            messagingService = RMIConnector.rmiReconnect().getMessagingService();
            System.out.println("sssss");
            return sendMessage(message);
        } catch (IOException e) {
            System.out.println("failed load message");
        }
        return hBox;
    }

    public static HBox sendFile(Message message, File file, FileType fileType) {
        try {
            byte[] fileData = Files.readAllBytes(file.toPath());
            int fileId = messagingService.uploadFile(fileData,file.getName(),fileType);
            if (fileId == 0){ return null;}
            FileMessage fileMessage;
            if (message instanceof GroupMessage groupMessage) {
                fileMessage = new FileMessage(groupMessage, fileData.length, file.getName(), fileType );
            } else {
                fileMessage = new FileMessage(message, fileData.length, file.getName(), fileType );
            }
            fileMessage.setFileId(fileId);
            return sendMessage(fileMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ObservableList<HBox> getDirectMessages(String contactPhoneNumber) {
        ObservableList<HBox> messages = FXCollections.observableArrayList();

        String sendMessage = "/gov/iti/jets/fxml/send-message.fxml";
        String receiveMessage = "/gov/iti/jets/fxml/received-message.fxml";
        String sendFile = "/gov/iti/jets/fxml/sendFile.fxml";
        String receivedFile = "/gov/iti/jets/fxml/receivedFile.fxml";


        try {
            List<Message> list = messagingService.getDirectMessages(user.getPhoneNumber(), contactPhoneNumber);
            for (Message m : list) {
                FXMLLoader loader;
                if (m.getSenderId().equals(user.getPhoneNumber())) {
                    String path = m instanceof FileMessage ? sendFile : sendMessage;
                    loader = new FXMLLoader(MessageServiceController.class.getResource(path));
                } else {
                    String path = m instanceof FileMessage ? receivedFile : receiveMessage;
                    loader = new FXMLLoader(MessageServiceController.class.getResource(path));
                }
                HBox hBox = loader.load();
                Object controller = loader.getController();

                if (controller instanceof SendMessageController sendMessageController) {
                    sendMessageController.setMessage(m);
                } else if (controller instanceof ReceiveMessageController receiveMessageController) {
                    receiveMessageController.setMessage(m);
                } else if (controller instanceof SendFileController sendFileController) {
                    sendFileController.setFileMessage((FileMessage) m);
                } else if (controller instanceof ReceiveFileController receiveFileController) {
                    receiveFileController.setFileMessage((FileMessage) m);
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

        ObservableList<HBox> messages = FXCollections.observableArrayList();

        String sendMessage = "/gov/iti/jets/fxml/send-message.fxml";
        String receiveMessage = "/gov/iti/jets/fxml/receiveGroupMessage.fxml";
        String sendFile = "/gov/iti/jets/fxml/sendFile.fxml";
        String receivedFile = "/gov/iti/jets/fxml/receiveGroupFile.fxml";


        try {
            List<Message> list = messagingService.getMessagesByGroupId(groupId);
            for (Message m : list) {
                FXMLLoader loader;
                if (m.getSenderId().equals(user.getPhoneNumber())) {
                    String path = m instanceof FileMessage ? sendFile : sendMessage;
                    loader = new FXMLLoader(MessageServiceController.class.getResource(path));
                } else {
                    String path = m instanceof FileMessage ? receivedFile : receiveMessage;
                    loader = new FXMLLoader(MessageServiceController.class.getResource(path));
                }
                HBox hBox = loader.load();
                Object controller = loader.getController();

                if (controller instanceof SendMessageController sendMessageController) {
                    sendMessageController.setMessage(m);
                } else if (controller instanceof ReceiveGroupMessageController receiveGroupMessageController) {
                    receiveGroupMessageController.setMessage((GroupMessage) m);
                } else if (controller instanceof SendFileController sendFileController) {
                    sendFileController.setFileMessage((FileMessage) m);
                } else if (controller instanceof ReceiveGroupFileController receiveGroupFileController) {
                    receiveGroupFileController.setFileMessage((FileMessage) m);
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

    public static byte [] getFileData(int fileId) {
        try {
            return messagingService.downloadFile(fileId);
        } catch (RemoteException e) {
            System.out.println("Error when download data: " + e.getMessage());
            messagingService = RMIConnector.rmiReconnect().getMessagingService();
            return getFileData(fileId);
        }
    }


}
