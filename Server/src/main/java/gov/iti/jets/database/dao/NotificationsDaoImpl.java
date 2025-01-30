package gov.iti.jets.database.dao;

import gov.iti.jets.database.DataBaseConnection;
import gov.iti.jets.client.model.Notifications;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationsDaoImpl implements NotificationsDao {
    static DataBaseConnection dataBaseConnection;

    static {
        try {
            dataBaseConnection = DataBaseConnection.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void addNotification(Notifications notifications) {
        Connection connection = dataBaseConnection.getConnection();
        String sql = "INSERT INTO notifications (user_id, message, sent_at, is_read) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, notifications.getUserId());
            statement.setString(2, notifications.getMessage());
            statement.setTimestamp(3, Timestamp.valueOf(notifications.getSentAt()));
            statement.setBoolean(4, notifications.isRead());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating notification failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    notifications.setNotificationId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating notification failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Notifications getNotificationById(int notificationId) {
        Connection connection = dataBaseConnection.getConnection();
        String sql = "SELECT * FROM notifications WHERE notification_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, notificationId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToNotification(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Notifications> getAllNotificationById(String userId) {
        Connection connection = dataBaseConnection.getConnection();
        List<Notifications> notifications = new ArrayList<>();
        String sql = "SELECT * FROM notifications WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId );
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    notifications.add(mapResultSetToNotification(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    @Override
    public void updateNotification(Notifications notifications) {
        Connection connection = dataBaseConnection.getConnection();
        String sql = "UPDATE notifications SET user_id = ?, message = ?, sent_at = ?, is_read = ? WHERE notification_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, notifications.getUserId());
            statement.setString(2, notifications.getMessage());
            statement.setTimestamp(3, Timestamp.valueOf(notifications.getSentAt()));
            statement.setBoolean(4, notifications.isRead());
            statement.setInt(5, notifications.getNotificationId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteNotification(int notificationId) {
        Connection connection = dataBaseConnection.getConnection();
        String sql = "DELETE FROM notifications WHERE notification_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, notificationId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private Notifications mapResultSetToNotification(ResultSet resultSet) throws SQLException {
        Notifications notification = new Notifications();
        notification.setNotificationId(resultSet.getInt("notification_id"));
        notification.setUserId(resultSet.getString("user_id"));
        notification.setMessage(resultSet.getString("message"));
        notification.setSentAt(resultSet.getTimestamp("sent_at").toLocalDateTime());
        notification.setRead(resultSet.getBoolean("is_read"));
        return notification;
    }

    public static void main(String[] args) {
        Notifications notifications = new Notifications(1, "0122", "hey message", LocalDateTime.now(),false);
        NotificationsDao notificationsDao = new NotificationsDaoImpl();
        notificationsDao.addNotification(notifications);
    }
}
