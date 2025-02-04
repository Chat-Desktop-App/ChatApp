package gov.iti.jets.services.interfaces;

import gov.iti.jets.model.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MessagingService extends Remote {
    boolean sendMessage(Message message) throws RemoteException;
    List<Message> getMessagesBySenderId(String senderId) throws RemoteException;
    List<Message> getMessagesByGroupId(int groupId) throws RemoteException;
    Message getMessageById(int messageId) throws RemoteException;
    List<Message> getAllMessages() throws RemoteException;
    void updateMessage(Message message) throws RemoteException;
    void deleteMessage(int messageId) throws RemoteException;
}
