package gov.iti.jets.services.interfaces;

import gov.iti.jets.model.Announcements;
import gov.iti.jets.model.FileType;
import gov.iti.jets.model.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MessagingService extends Remote {
    boolean sendMessage(Message message) throws RemoteException;

    List<Message> getDirectMessages(String userPhone, String contactPhone) throws RemoteException;

    List<Message> getMessagesByGroupId(int groupId) throws RemoteException;

    Message getMessageById(int messageId) throws RemoteException;

    List<Message> getAllMessages() throws RemoteException;

    void updateMessage(Message message) throws RemoteException;

    void deleteMessage(int messageId) throws RemoteException;

    void broadcastAnnouncement(String message) throws RemoteException;

    List<Announcements> getAnnouncements() throws RemoteException;

    int uploadFile(byte[] fileData, String fileName, FileType fileType) throws RemoteException;

    byte[] downloadFile(int fileId) throws RemoteException;
}

