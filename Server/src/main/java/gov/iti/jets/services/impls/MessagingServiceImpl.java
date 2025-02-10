package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.ContactDaoImpl;
import gov.iti.jets.database.dao.GroupDaoImpl;
import gov.iti.jets.database.dao.MessageDaoImpl;
import gov.iti.jets.model.GroupMessage;
import gov.iti.jets.model.Message;
import gov.iti.jets.model.Recipient;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.ChatClient;
import gov.iti.jets.services.interfaces.MessagingService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class MessagingServiceImpl extends UnicastRemoteObject implements MessagingService {
    private MessageDaoImpl messageDaoImpl;
    private GroupDaoImpl groupDao;
    private ContactDaoImpl contactDao;

    public MessagingServiceImpl() throws RemoteException {
        super();
        this.messageDaoImpl = new MessageDaoImpl();
        this.groupDao = new GroupDaoImpl();
        this.contactDao = new ContactDaoImpl();
    }

    @Override
    public boolean sendMessage(Message message) throws RemoteException {
        int rowsAffected = messageDaoImpl.addMessage(message);
        try {
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
    public List<GroupMessage> getMessagesByGroupId(int groupId) throws RemoteException {
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
}
