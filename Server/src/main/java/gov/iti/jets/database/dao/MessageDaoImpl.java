package gov.iti.jets.database.dao;

import gov.iti.jets.database.DataBaseConnection;
import gov.iti.jets.model.GroupMessage;
import gov.iti.jets.model.Message;
import gov.iti.jets.model.Recipient;
import gov.iti.jets.utility.PictureUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl implements MessageDao {
    static DataBaseConnection dataBaseConnection;

    static {
        try {
            dataBaseConnection = DataBaseConnection.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int addMessage(Message message) {
        Connection connection = dataBaseConnection.getConnection();
        String query = "INSERT INTO Messages (sender_id, recipient_type, receiver_id, group_id, content, file_id, font_size, font_style, font_color, is_bold, is_italic, text_background_color, time_stand, emoji) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, message.getSenderId());
            statement.setString(2, message.getRecipient().toString());
            statement.setString(3, message.getReceiverId());
            statement.setObject(4, message.getGroupId(), Types.INTEGER);
            statement.setString(5, message.getContent());
            statement.setObject(6, message.getFileId(), Types.INTEGER);
            statement.setInt(7, message.getFontSize());
            statement.setString(8, message.getFontStyle());
            statement.setString(9, message.getFontColour());
            statement.setBoolean(10, message.isBold());
            statement.setBoolean(11, message.isItalic());
            statement.setString(12, message.getTextBackGroundColour());
            statement.setTimestamp(13, message.getTimestamp());
            statement.setString(14, message.getEmoji());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Message added successfully!");
                return rowsAffected;
            } else {
                System.out.println("Message insertion failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Message> getDirectMessages(String userPhone , String contactPhone) {
        Connection connection = dataBaseConnection.getConnection();
        List<Message> messages = new ArrayList<>();
        String query = """
                SELECT * FROM Messages
                WHERE (sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?)
                ORDER BY time_stand
        """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userPhone);
            statement.setString(2, contactPhone);
            statement.setString(3, contactPhone);
            statement.setString(4, userPhone);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                messages.add(mapResultSetToMessage(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return messages;
    }

    @Override
    public List<GroupMessage> getMessagesByGroupId(int groupId) {
        Connection connection = dataBaseConnection.getConnection();
        List<GroupMessage> groupMessages = new ArrayList<>();
        // Updated query with JOIN
        String query = """
            SELECT m.*,u.fname AS sender_name, u.picture AS sender_profile_picture
            FROM Messages m JOIN Users u ON m.sender_id = u.phone_number
            WHERE m.group_id = ?
            ORDER BY m.time_stand
        """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                groupMessages.add(new GroupMessage(
                        resultSet.getInt("message_id"),
                        resultSet.getString("sender_id"),
                        Recipient.valueOf(resultSet.getString("recipient_type")),
                        resultSet.getString("receiver_id"),
                        resultSet.getInt("group_id"),
                        resultSet.getString("content"),
                        resultSet.getInt("file_id"),
                        resultSet.getInt("font_size"),
                        resultSet.getString("font_style"),
                        resultSet.getString("font_color"),
                        resultSet.getBoolean("is_bold"),
                        resultSet.getBoolean("is_italic"),
                        resultSet.getString("text_background_color"),
                        resultSet.getTimestamp("time_stand"),
                        resultSet.getString("emoji"),
                        resultSet.getString("sender_name"),
                        PictureUtil.getPicture(resultSet.getString("sender_profile_picture"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupMessages;
    }


    @Override
    public Message getMessageById(int messageId) {
        Connection connection = dataBaseConnection.getConnection();
        String query = "SELECT * FROM Messages WHERE message_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, messageId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToMessage(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Message mapResultSetToMessage(ResultSet resultSet) throws SQLException {
        return new Message(
                resultSet.getInt("message_id"),
                resultSet.getString("sender_id"),
                Recipient.valueOf(resultSet.getString("recipient_type")),
                resultSet.getString("receiver_id"),
                resultSet.getInt("group_id"),
                resultSet.getString("content"),
                resultSet.getInt("file_id"),
                resultSet.getInt("font_size"),
                resultSet.getString("font_style"),
                resultSet.getString("font_color"),
                resultSet.getBoolean("is_bold"),
                resultSet.getBoolean("is_italic"),
                resultSet.getString("text_background_color"),
                resultSet.getTimestamp("time_stand"),
                resultSet.getString("emoji")
        );
    }

    @Override
    public List<Message> getAllMessages() {
        Connection connection = dataBaseConnection.getConnection();
        List<Message> messages = new ArrayList<>();
        String query = "SELECT * FROM Messages";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                messages.add(mapResultSetToMessage(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public void updateMessage(Message message) {
        Connection connection = dataBaseConnection.getConnection();
        String query = "UPDATE Messages SET sender_id = ?, recipient_type = ?, receiver_id = ?, group_id = ?, content = ?, file_id = ?, font_size = ?, font_style = ?, font_color = ?, is_bold = ?, is_italic = ?, text_background_color = ?, time_stand = ?, emoji = ? " +
                "WHERE message_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, message.getSenderId());
            statement.setString(2, message.getRecipient().toString());
            statement.setString(3, message.getReceiverId());
            statement.setObject(4, message.getGroupId(), Types.INTEGER);
            statement.setString(5, message.getContent());
            statement.setObject(6, message.getFileId(), Types.INTEGER);
            statement.setInt(7, message.getFontSize());
            statement.setString(8, message.getFontStyle());
            statement.setString(9, message.getFontColour());
            statement.setBoolean(10, message.isBold());
            statement.setBoolean(11, message.isItalic());
            statement.setString(12, message.getTextBackGroundColour());
            statement.setTimestamp(13, message.getTimestamp());
            statement.setString(14, message.getEmoji());
            statement.setInt(15, message.getMessageId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Message updated successfully!");
            } else {
                System.out.println("Message update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteMessage(int messageId) {
        Connection connection = dataBaseConnection.getConnection();
        String query = "DELETE FROM Messages WHERE message_Id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, messageId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Message deleted successfully!");
            } else {
                System.out.println("Message deletion failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Message message = new Message();

    }

}
