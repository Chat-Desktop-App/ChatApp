package gov.iti.jets.database.dao;

import gov.iti.jets.database.DataBaseConnection;
import gov.iti.jets.model.Messages;
import gov.iti.jets.model.Recipient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesDaoImpl implements MessagesDao {
    static DataBaseConnection dataBaseConnection;

    static {
        try {
            dataBaseConnection = DataBaseConnection.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int addMessage(Messages messages) {
        Connection connection = dataBaseConnection.getConnection();
        String query = "INSERT INTO Messages (sender_id, recipient_type, receiver_id, group_id, content, file_id, font_size, font_style, font_color, is_bold, is_italic, text_background_color, time_stand, emoji) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, messages.getSenderId());
            statement.setString(2, messages.getRecipient().toString());
            statement.setString(3, messages.getReceiverId());
            statement.setObject(4, messages.getGroupId(), Types.INTEGER);
            statement.setString(5, messages.getContent());
            statement.setObject(6, messages.getFileId(), Types.INTEGER);
            statement.setInt(7, messages.getFontSize());
            statement.setString(8, messages.getFontStyle());
            statement.setString(9, messages.getFontColour());
            statement.setBoolean(10, messages.isBold());
            statement.setBoolean(11, messages.isItalic());
            statement.setString(12, messages.getTextBackGroundColour());
            statement.setTimestamp(13, messages.getTimestamp());
            statement.setString(14, messages.getEmoji());

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
    public List<Messages> getMessagesBySenderId(String senderId) {
        Connection connection = dataBaseConnection.getConnection();
        List<Messages> messages = new ArrayList<>();
        String query = "SELECT * FROM Messages WHERE sender_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, senderId);
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
    public List<Messages> getMessagesByGroupId(int groupId) {
        Connection connection = dataBaseConnection.getConnection();
        List<Messages> messages = new ArrayList<>();
        String query = "SELECT * FROM Messages WHERE group_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, groupId);
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
    public Messages getMessageById(int messageId) {
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
    private Messages mapResultSetToMessage(ResultSet resultSet) throws SQLException {
        return new Messages(
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
    public List<Messages> getAllMessages() {
        Connection connection = dataBaseConnection.getConnection();
        List<Messages> messages = new ArrayList<>();
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
    public void updateMessage(Messages messages) {
        Connection connection = dataBaseConnection.getConnection();
        String query = "UPDATE Messages SET sender_id = ?, recipient_type = ?, receiver_id = ?, group_id = ?, content = ?, file_id = ?, font_size = ?, font_style = ?, font_color = ?, is_bold = ?, is_italic = ?, text_background_color = ?, time_stand = ?, emoji = ? " +
                "WHERE message_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, messages.getSenderId());
            statement.setString(2, messages.getRecipient().toString());
            statement.setString(3, messages.getReceiverId());
            statement.setObject(4, messages.getGroupId(), Types.INTEGER);
            statement.setString(5, messages.getContent());
            statement.setObject(6, messages.getFileId(), Types.INTEGER);
            statement.setInt(7, messages.getFontSize());
            statement.setString(8, messages.getFontStyle());
            statement.setString(9, messages.getFontColour());
            statement.setBoolean(10, messages.isBold());
            statement.setBoolean(11, messages.isItalic());
            statement.setString(12, messages.getTextBackGroundColour());
            statement.setTimestamp(13, messages.getTimestamp());
            statement.setString(14, messages.getEmoji());
            statement.setInt(15, messages.getMessageId());

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
        Messages messages = new Messages();

    }

}
