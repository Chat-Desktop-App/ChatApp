package gov.iti.jets.database.dao;

import gov.iti.jets.model.Messages;

import java.util.List;

public interface MessagesDao {
    int addMessage(Messages messages);
    List<Messages> getMessagesBySenderId(String senderId);
    List<Messages> getMessagesByGroupId(int groupId);
    Messages getMessageById(int messageId);
    List<Messages> getAllMessages();
    void updateMessage(Messages messages);
    void deleteMessage(int messageId);

}
