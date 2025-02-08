package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.MessageDaoImpl;
import gov.iti.jets.model.GroupMessage;
import gov.iti.jets.model.Message;
import gov.iti.jets.services.interfaces.ChatClient;
import gov.iti.jets.services.interfaces.MessagingService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MessagingServiceImpl extends UnicastRemoteObject implements MessagingService {
    private MessageDaoImpl messageDaoImpl;

    public MessagingServiceImpl() throws RemoteException {
        super();
        this.messageDaoImpl = new MessageDaoImpl();
    }

    @Override
    public boolean sendMessage(Message message) throws RemoteException {
        int rowsAffected = messageDaoImpl.addMessage(message);
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
        ChatClient client=LoginImpl.getOnlineClients().get(message.getReceiverId());
        if(client!=null){
            client.receive(message);
        }
    }
}
