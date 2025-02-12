package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.*;
import gov.iti.jets.database.dao.ContactDaoImpl;
import gov.iti.jets.database.dao.GroupDaoImpl;
import gov.iti.jets.database.dao.MessageDaoImpl;
import gov.iti.jets.model.*;
import gov.iti.jets.services.interfaces.ChatClient;
import gov.iti.jets.services.interfaces.MessagingService;
import gov.iti.jets.utility.FileUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessagingServiceImpl extends UnicastRemoteObject implements MessagingService {
    private MessageDaoImpl messageDaoImpl;
    private GroupDaoImpl groupDao;
    private ContactDaoImpl contactDao;
    private AnnouncementDao announcementDao;

    public MessagingServiceImpl() throws RemoteException {
        super();
        this.messageDaoImpl = new MessageDaoImpl();
        this.groupDao = new GroupDaoImpl();
        this.contactDao = new ContactDaoImpl();
        this.announcementDao = new AnnouncementDaoImpl();
    }

    @Override
    public boolean sendMessage(Message message) throws RemoteException {
        int rowsAffected = 0;
        try {
            rowsAffected = messageDaoImpl.addMessage(message);
            if(message.getRecipient() == Recipient.GROUP){
                groupDao.updateLastMessage(message.getGroupId(),message.getTimestamp());
            }else {
                contactDao.updateLastContact(message.getSenderId(), message.getReceiverId(), message.getTimestamp());
            }

        } catch (SQLException e) {
            System.out.println("can't update last contact");
            e.printStackTrace();
        }
        resendMessage(message);
        return rowsAffected > 0;
    }

    @Override
    public List<Message> getDirectMessages(String userPhone , String contactPhone) throws RemoteException {
        return messageDaoImpl.getDirectMessages(userPhone , contactPhone);
    }

    @Override
    public List<Message> getMessagesByGroupId(int groupId) throws RemoteException {
        return messageDaoImpl.getMessagesByGroupId(groupId);
    }

    @Override
    public Message getMessageById(int messageId) throws RemoteException {
        return messageDaoImpl.getMessageById(messageId);
    }

    @Override
    public List<Message> getAllMessages() throws RemoteException {
        return messageDaoImpl.getAllMessages();
    }

    @Override
    public void updateMessage(Message message) throws RemoteException {
        messageDaoImpl.updateMessage(message);
    }

    @Override
    public void deleteMessage(int messageId) throws RemoteException {
        messageDaoImpl.deleteMessage(messageId);
    }

    @Override
    public int uploadFile(byte[] fileData, String fileName , FileType fileType) throws RemoteException {
        return FileUtil.addFile(fileData,fileName , fileType);
    }

    @Override
    public byte[] downloadFile(int fileId) throws RemoteException {
        MyFile myFile = FileUtil.getFile(fileId);
        if (myFile != null){
            return myFile.getFileData();
        }
        return null;
    }

    public void resendMessage(Message message) throws RemoteException {
        if(message.getRecipient() == Recipient.GROUP){
            try {
                List<User> userList = groupDao.getAllGroupMembers(message.getGroupId());
                for (User user: userList){
                    if (user.getPhoneNumber().equals(message.getSenderId())){continue;}
                    ChatClient client=LoginImpl.getOnlineClients().get(user.getPhoneNumber());
                    if(client!=null){
                        client.receive(message);
                    }
                }
            } catch (SQLException e) {
                System.out.println("error loading message");
                e.printStackTrace();
            }
        }
        else{
            ChatClient client=LoginImpl.getOnlineClients().get(message.getReceiverId());
            if(client!=null){
                client.receive(message);
            }
        }
    }
    @Override
    public void broadcastAnnouncement(String message) throws RemoteException {
        System.out.println("Broadcasting announcement: " + message);
        String timestamp = java.time.LocalDateTime.now().toString();

        // Store announcement in the database
        try {
            announcementDao.saveAnnouncement(message);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error saving announcement to database.");
        }
        // Send announcement to online clients
        for (ChatClient client : LoginImpl.getOnlineClients().values()) {
            try {
                client.receiveAnnouncement(message, timestamp);
            } catch (RemoteException e) {
                System.out.println("Failed to send announcement to a client: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Announcements> getAnnouncements() throws RemoteException {
        List<Announcements> announcements = new ArrayList<>();
        try{
            announcements = announcementDao.getAllAnnouncements();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return announcements;
    }
}
