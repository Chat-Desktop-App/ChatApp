package gov.iti.jets.database.dao;

import gov.iti.jets.model.Announcements;

import java.sql.SQLException;
import java.util.List;

public interface AnnouncementDao {
    void saveAnnouncement(String message) throws SQLException;
    List<Announcements> getAllAnnouncements() throws SQLException;

}
