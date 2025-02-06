package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.MessageDaoImpl;
import gov.iti.jets.model.GroupMessage;
import gov.iti.jets.model.Message;
import gov.iti.jets.services.interfaces.MessagingService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MessagingServiceImpl extends UnicastRemoteObject implements MessagingService {
    private MessageDaoImpl messageDao;

    public MessagingServiceImpl() throws RemoteException {
        super();
        this.messageDao = new MessageDaoImpl();
    }

    @Override
    public boolean sendMessage(Message message) throws RemoteException {
        int rowsAffected = messageDao.addMessage(message);
        return rowsAffected > 0;
    }

    @Override
    public List<Message> getDirectMessages(String userPhone , String contactPhone) throws RemoteException {
        return messageDao.getDirectMessages(userPhone , contactPhone);
    }

    @Override
    public List<GroupMessage> getMessagesByGroupId(int groupId) throws RemoteException {
        return messageDao.getMessagesByGroupId(groupId);
    }

    @Override
    public Message getMessageById(int messageId) throws RemoteException {
        return messageDao.getMessageById(messageId);
    }

    @Override
    public List<Message> getAllMessages() throws RemoteException {
        return messageDao.getAllMessages();
    }

    @Override
    public void updateMessage(Message message) throws RemoteException {
        messageDao.updateMessage(message);
    }

    @Override
    public void deleteMessage(int messageId) throws RemoteException {
        messageDao.deleteMessage(messageId);
    }
}
