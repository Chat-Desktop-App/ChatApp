package gov.iti.jets.database.dao;

import gov.iti.jets.model.Message;

import java.util.List;

public interface MessageDao {
    void addMessage(Message message);
    Message getMessageById(int messageId);
    List<Message> getAllMessages();
    void updateMessage(Message message);
    void deleteMessage(int messageId);

}
