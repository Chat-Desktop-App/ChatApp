package gov.iti.jets.database.dao;

import gov.iti.jets.database.DataBaseConnection;
import gov.iti.jets.model.Announcements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementDaoImpl implements AnnouncementDao{
    static DataBaseConnection dataBaseConnection;

    static {
        try {
            dataBaseConnection = DataBaseConnection.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void saveAnnouncement(String message) throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        String insertQuery = "INSERT INTO announcements (message, timestamp) VALUES (?, NOW())";
        try(PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, message);
            statement.executeUpdate();
        }

    }

    @Override
    public List<Announcements> getAllAnnouncements() throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        List<Announcements> announcements = new ArrayList<>();
        String selectQuery = "SELECT message, timestamp FROM announcements";
        try(PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    announcements.add(new Announcements(
                            rs.getString("message"),
                            rs.getTimestamp("timestamp")
                    ));
                }
            }
        return announcements;
    }
}
