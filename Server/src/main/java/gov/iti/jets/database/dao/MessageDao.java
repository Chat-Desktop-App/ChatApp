package gov.iti.jets.database.dao;

import gov.iti.jets.model.GroupMessage;
import gov.iti.jets.model.Message;

import java.util.List;

public interface MessageDao {
    int addMessage(Message message);
    List<Message> getDirectMessages(String userPhone , String contactPhone);
    List<Message> getMessagesByGroupId(int groupId);
    Message getMessageById(int messageId);
    List<Message> getAllMessages();
    void updateMessage(Message message);
    void deleteMessage(int messageId);

}
