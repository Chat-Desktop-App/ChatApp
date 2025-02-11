package gov.iti.jets.database.dao;

import gov.iti.jets.database.DataBaseConnection;
import gov.iti.jets.model.ContactStatus;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.Status;
import gov.iti.jets.utility.PictureUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDao {
    static DataBaseConnection dataBaseConnection;

    static {
        try {
            dataBaseConnection = DataBaseConnection.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ContactDaoImpl contactDao = new ContactDaoImpl();
        String phoneNumber = "+1234567890";  // Janes's phone number
        String contactPhoneNumber = "+1122334455"; // alice's phone number
        try {
            //contactDao.addContact(phoneNumber, contactPhoneNumber);
            // Test for friends contacts
            List<ContactUser> friends = contactDao.getFriendsContacts(contactPhoneNumber);
            System.out.println("Friends Contacts: " + friends.size());
            for (ContactUser contact : friends) {
                System.out.println(contact);
            }

            // Test for pending contacts
            List<ContactUser> pending = contactDao.getPendingContacts(contactPhoneNumber);
            System.out.println("Pending Contacts: " + pending.size());
            for (ContactUser contact : pending) {
                System.out.println(contact);
            }

            // Test for blocked contacts
            List<ContactUser> blocked = contactDao.getBlockedContacts(contactPhoneNumber);
            System.out.println("Blocked Contacts: " + blocked.size());
            for (ContactUser contact : blocked) {
                System.out.println(contact);
            }

            // Test for all contacts
            List<ContactUser> allContacts = contactDao.getAllContacts(contactPhoneNumber);
            System.out.println("All Contacts: " + allContacts.size());
            for (ContactUser contact : allContacts) {
                System.out.println(contact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<ContactUser> getFriendsContacts(String phoneNumber) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                    SELECT u.phone_number as contact_id, u.fname, u.lname, u.status AS user_status, u.picture, c.status, c.user_id
                    FROM contacts c
                    JOIN users u ON (CASE
                        WHEN c.user_id = ? THEN c.contact_id = u.phone_number
                        WHEN c.contact_id = ? THEN c.user_id = u.phone_number
                    END)
                    WHERE (c.user_id = ? OR c.contact_id = ?)
                    AND c.status = 'ACCEPTED'
                """;
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, phoneNumber);
        ps.setString(2, phoneNumber);
        ps.setString(3, phoneNumber);
        ps.setString(4, phoneNumber);

        ResultSet rs = ps.executeQuery();
        List<ContactUser> contactUsers = new ArrayList<>();

        while (rs.next()) {
            ContactUser contactUser = new ContactUser(
                    rs.getString(1),
                    rs.getString(2), rs.getString(3), Status.valueOf(rs.getString(4)),
                    rs.getString(5));
            byte[] profilePicture = PictureUtil.getPicture(contactUser.getPicturePath());
            contactUser.setPicture(profilePicture);
            contactUsers.add(contactUser);
        }
        return contactUsers;
    }

    @Override
    public List<ContactUser> getOnlineContacts(String phoneNumber) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                SELECT
                CASE
                    WHEN c.user_id = ? THEN u2.phone_number ELSE u1.phone_number END AS contacted_user,
                CASE
                    WHEN c.user_id = ? THEN u2.fname ELSE u1.fname END AS fname,
                CASE
                    WHEN c.user_id = ? THEN u2.lname ELSE u1.lname END AS lname,
                CASE
                    WHEN c.user_id = ? THEN u2.status ELSE u1.status END AS user_status,
                CASE WHEN c.user_id = ? THEN u2.picture ELSE u1.picture END AS picture,
                c.status AS contact_status,
                c.last_chat_at
                FROM contacts c
                JOIN users u1 ON c.user_id = u1.phone_number
                JOIN users u2 ON c.contact_id = u2.phone_number
                WHERE (c.user_id = ? OR c.contact_id = ?)
                      AND c.status = 'ACCEPTED' AND c.last_chat_at IS NOT NULL 
                      AND u2.status != 'OFFLINE' And u1.status != 'OFFLINE'
                      ORDER BY c.last_chat_at DESC;
                ;
                """;
        List<ContactUser> contactUsers = new ArrayList<>();

        PreparedStatement ps = con.prepareStatement(query);
        for (int i = 1; i <= 7; i++) {
            ps.setString(i, phoneNumber);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ContactUser contactUser = new ContactUser(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    Status.valueOf(rs.getString(4)),
                    rs.getString(5));
            byte[] profilePicture = PictureUtil.getPicture(contactUser.getPicturePath());
            contactUser.setPicture(profilePicture);
            contactUsers.add(contactUser);
        }
        return contactUsers;
    }

    @Override
    public List<ContactUser> getRejectedContacts(String phoneNumber) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                SELECT c.contact_id, u.fname, u.lname,  u.status AS user_status, u.picture,  c.status, c.user_id
                FROM contacts c
                JOIN users u ON c.contact_id = u.phone_number
                WHERE c.contact_id = ? AND c.status = 'REJECTED'
                """;
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, phoneNumber);
        ResultSet rs = ps.executeQuery();
        List<ContactUser> contactUsers = new ArrayList<>();

        while (rs.next()) {
            ContactUser contactUser = new ContactUser(rs.getString(1),
                    rs.getString(2), rs.getString(3), Status.valueOf(rs.getString(4)), rs.getString(5));
            byte[] profilePicture = PictureUtil.getPicture(contactUser.getPicturePath());
            contactUser.setPicture(profilePicture);
            contactUsers.add(contactUser);
        }
        return contactUsers;

    }

    @Override
    public List<ContactUser> getPendingContacts(String phoneNumber) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                SELECT c.contact_id, u.fname, u.lname,  u.status AS user_status, u.picture,  c.status, c.user_id
                FROM contacts c
                JOIN users u ON c.contact_id = u.phone_number
                WHERE c.contact_id = ? AND c.status = 'PENDING'
                """;
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, phoneNumber);
        ResultSet rs = ps.executeQuery();
        List<ContactUser> contactUsers = new ArrayList<>();

        while (rs.next()) {
            ContactUser contactUser = new ContactUser(rs.getString(1),
                    rs.getString(2), rs.getString(3), Status.valueOf(rs.getString(4)), rs.getString(5));
            byte[] profilePicture = PictureUtil.getPicture(contactUser.getPicturePath());
            contactUser.setPicture(profilePicture);
            contactUsers.add(contactUser);
        }
        return contactUsers;
    }

    @Override
    public List<ContactUser> getBlockedContacts(String phoneNumber) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                SELECT c.contact_id, u.fname, u.lname,  u.status AS user_status, u.picture,  c.status, c.user_id
                FROM contacts c
                JOIN users u ON c.contact_id = u.phone_number
                WHERE c.user_id = ? AND c.status = 'BLOCKED'
                """;
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, phoneNumber);
        ResultSet rs = ps.executeQuery();
        List<ContactUser> contactUsers = new ArrayList<>();

        while (rs.next()) {
            ContactUser contactUser = new ContactUser(rs.getString(1),
                    rs.getString(2), rs.getString(3), Status.valueOf(rs.getString(4)), rs.getString(5));
            byte[] profilePicture = PictureUtil.getPicture(contactUser.getPicturePath());
            contactUser.setPicture(profilePicture);
            contactUsers.add(contactUser);
        }
        return contactUsers;
    }

    @Override
    public List<ContactUser> getAllContacts(String phoneNumber) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                SELECT
                CASE
                    WHEN c.user_id = ? THEN u2.phone_number ELSE u1.phone_number END AS contacted_user,
                CASE
                    WHEN c.user_id = ? THEN u2.fname ELSE u1.fname END AS fname,
                CASE
                    WHEN c.user_id = ? THEN u2.lname ELSE u1.lname END AS lname,
                CASE
                    WHEN c.user_id = ? THEN u2.status ELSE u1.status END AS user_status,
                CASE WHEN c.user_id = ? THEN u2.picture ELSE u1.picture END AS picture,
                c.status AS contact_status,c.last_chat_at
                FROM contacts c
                JOIN users u1 ON c.user_id = u1.phone_number
                JOIN users u2 ON c.contact_id = u2.phone_number
                WHERE (c.user_id = ? OR c.contact_id = ?)
                      AND c.status = 'ACCEPTED';
                ;
                """;
        PreparedStatement ps = con.prepareStatement(query);
        for (int i = 1; i <= 7; i++) {
            ps.setString(i, phoneNumber);
        }
        ResultSet rs = ps.executeQuery();
        List<ContactUser> contactUsers = new ArrayList<>();

        while (rs.next()) {
            ContactUser contactUser = new ContactUser(rs.getString(1),
                    rs.getString(2), rs.getString(3), Status.valueOf(rs.getString(4)), rs.getString(5));
            byte[] profilePicture = PictureUtil.getPicture(contactUser.getPicturePath());
            contactUser.setPicture(profilePicture);
            contactUsers.add(contactUser);
        }
        return contactUsers;
    }

    @Override
    public int addContact(String phoneNumber, String contactPhoneNumber) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String checkQuery = """
                    SELECT user_id, contact_id, status FROM contacts 
                    WHERE (user_id = ? AND contact_id = ?) 
                       OR (user_id = ? AND contact_id = ?)
                """;
        PreparedStatement checkStmt = con.prepareStatement(checkQuery);
        checkStmt.setString(1, phoneNumber);
        checkStmt.setString(2, contactPhoneNumber);
        checkStmt.setString(3, contactPhoneNumber);
        checkStmt.setString(4, phoneNumber);
        ResultSet rs = checkStmt.executeQuery();
        boolean senderPending = false;
        boolean receiverPending = false;
        boolean rejectedExists = false;
        while (rs.next()) {
            String userId = rs.getString("user_id");
            String contactId = rs.getString("contact_id");
            String status = rs.getString("status");

            if (userId.equals(phoneNumber) && contactId.equals(contactPhoneNumber) && "PENDING".equals(status)) {
                senderPending = true;
            }
            if (userId.equals(contactPhoneNumber) && contactId.equals(phoneNumber) && "PENDING".equals(status)) {
                receiverPending = true;
            }
            if ("REJECTED".equals(status)) {
                rejectedExists = true;
            }
        }

        if (senderPending && receiverPending) {
            String updateToAcceptedQuery = """
                        UPDATE contacts SET status = 'ACCEPTED'
                        WHERE (user_id = ? AND contact_id = ? AND status = 'PENDING')
                           OR (user_id = ? AND contact_id = ? AND status = 'PENDING')
                    """;
            PreparedStatement updateStmt = con.prepareStatement(updateToAcceptedQuery);
            updateStmt.setString(1, phoneNumber);
            updateStmt.setString(2, contactPhoneNumber);
            updateStmt.setString(3, contactPhoneNumber);
            updateStmt.setString(4, phoneNumber);

            return updateStmt.executeUpdate(); // Should return 2 if both are updated
        }

        if (rejectedExists) {
            String updateRejectedQuery = """
                        UPDATE contacts SET status = 'ACCEPTED'
                        WHERE (user_id = ? AND contact_id = ? AND status = 'REJECTED')
                           OR (user_id = ? AND contact_id = ? AND status = 'REJECTED')
                    """;
            PreparedStatement updateStmt = con.prepareStatement(updateRejectedQuery);
            updateStmt.setString(1, phoneNumber);
            updateStmt.setString(2, contactPhoneNumber);
            updateStmt.setString(3, contactPhoneNumber);
            updateStmt.setString(4, phoneNumber);

            return updateStmt.executeUpdate(); // Should return 1 if updated
        }

        String insertQuery = "INSERT INTO contacts (user_id, contact_id, status) VALUES (?, ?, ?)";
        PreparedStatement insertStmt = con.prepareStatement(insertQuery);
        insertStmt.setString(1, phoneNumber);
        insertStmt.setString(2, contactPhoneNumber);
        insertStmt.setString(3, ContactStatus.PENDING.toString());

        return insertStmt.executeUpdate();
        /*Connection con = dataBaseConnection.getConnection();

        // Check if the contact already exists
        String checkQuery = """
        SELECT status FROM contacts
        WHERE (user_id = ? AND contact_id = ?)
           OR (user_id = ? AND contact_id = ?)
    """;
        PreparedStatement checkStmt = con.prepareStatement(checkQuery);
        checkStmt.setString(1, phoneNumber);
        checkStmt.setString(2, contactPhoneNumber);
        checkStmt.setString(3, contactPhoneNumber);
        checkStmt.setString(4, phoneNumber);

        ResultSet rs = checkStmt.executeQuery();
        if (rs.next()) {
            String existingStatus = rs.getString("status");

            // If the existing status is REJECTED, update it to ACCEPTED
            if ("REJECTED".equals(existingStatus)) {
                String updateQuery = """
                UPDATE contacts SET status = 'ACCEPTED'
                WHERE (user_id = ? AND contact_id = ? AND status = 'REJECTED')
                   OR (user_id = ? AND contact_id = ? AND status = 'REJECTED')
            """;
                PreparedStatement updateStmt = con.prepareStatement(updateQuery);
                updateStmt.setString(1, phoneNumber);
                updateStmt.setString(2, contactPhoneNumber);
                updateStmt.setString(3, contactPhoneNumber);
                updateStmt.setString(4, phoneNumber);

                return updateStmt.executeUpdate(); // Return 1 if updated
            }

            return 0; // Contact already exists and is not rejected, so no insertion needed
        }

        // Insert new contact if it doesn't exist
        String insertQuery = "INSERT INTO contacts (user_id, contact_id, status) VALUES (?, ?, ?)";
        PreparedStatement insertStmt = con.prepareStatement(insertQuery);
        insertStmt.setString(1, phoneNumber);
        insertStmt.setString(2, contactPhoneNumber);
        insertStmt.setString(3, ContactStatus.PENDING.toString());

        return insertStmt.executeUpdate();*/
        // avoid duplicate entries
        /*Connection con = dataBaseConnection.getConnection();

        // Check if contact already exists
        String checkQuery = """
        SELECT COUNT(*) FROM contacts
        WHERE (user_id = ? AND contact_id = ?) OR (user_id = ? AND contact_id = ?)
    """;
        PreparedStatement checkStmt = con.prepareStatement(checkQuery);
        checkStmt.setString(1, phoneNumber);
        checkStmt.setString(2, contactPhoneNumber);
        checkStmt.setString(3, contactPhoneNumber);
        checkStmt.setString(4, phoneNumber);

        ResultSet rs = checkStmt.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            return 0; // Contact already exists, no insertion needed
        }

        // Insert new contact if it doesn't exist
        String insertQuery = "INSERT INTO contacts (user_id, contact_id, status) VALUES (?, ?, ?)";
        PreparedStatement insertStmt = con.prepareStatement(insertQuery);
        insertStmt.setString(1, phoneNumber);
        insertStmt.setString(2, contactPhoneNumber);
        insertStmt.setString(3, ContactStatus.PENDING.toString());

        return insertStmt.executeUpdate();*/

        /*// add phone number as a contact to co
        // add contact and set status to ?
        Connection con = dataBaseConnection.getConnection();
        String query = "INSERT INTO contacts (contact_id, user_id, status) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, phoneNumber);
        ps.setString(2, contactPhoneNumber);
        ps.setString(3,ContactStatus.PENDING.toString());

        return ps.executeUpdate();*/

    }

    @Override
    public Boolean updateContact(String u1, String u2, ContactStatus status) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                UPDATE contacts SET status = ?
                WHERE (contact_id = ? AND user_id = ?) OR ((contact_id = ? AND user_id = ?))
                """;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, status.toString());
        ps.setString(2, u1);
        ps.setString(3, u2);
        ps.setString(4, u2);
        ps.setString(5, u1);
        return ps.executeUpdate() > 0;
    }

    @Override
    public Boolean updateLastContact(String u1, String u2, Timestamp lastChat) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                UPDATE contacts SET last_chat_at = ?
                WHERE (contact_id = ? AND user_id = ?) OR ((contact_id = ? AND user_id = ?))
                """;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setTimestamp(1, lastChat);
        ps.setString(2, u1);
        ps.setString(3, u2);
        ps.setString(4, u2);
        ps.setString(5, u1);
        return ps.executeUpdate() > 0;
    }

    @Override
    public List<ContactUser> getLastContact(String phoneNumber) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                SELECT
                CASE
                    WHEN c.user_id = ? THEN u2.phone_number ELSE u1.phone_number END AS contacted_user,
                CASE
                    WHEN c.user_id = ? THEN u2.fname ELSE u1.fname END AS fname,
                CASE
                    WHEN c.user_id = ? THEN u2.lname ELSE u1.lname END AS lname,
                CASE
                    WHEN c.user_id = ? THEN u2.status ELSE u1.status END AS user_status,
                CASE WHEN c.user_id = ? THEN u2.picture ELSE u1.picture END AS picture,
                c.status AS contact_status,c.last_chat_at
                FROM contacts c
                JOIN users u1 ON c.user_id = u1.phone_number
                JOIN users u2 ON c.contact_id = u2.phone_number
                WHERE (c.user_id = ? OR c.contact_id = ?)
                      AND c.status = 'ACCEPTED' AND c.last_chat_at IS NOT NULL
                      ORDER BY c.last_chat_at DESC;
                ;
                """;
        PreparedStatement ps = con.prepareStatement(query);
        for (int i = 1; i <= 7; i++) {
            ps.setString(i, phoneNumber);
        }

        ResultSet rs = ps.executeQuery();
        List<ContactUser> contactUsers = new ArrayList<>();

        while (rs.next()) {
            ContactUser contactUser = new ContactUser(
                    rs.getString(1),
                    rs.getString(2), rs.getString(3),
                    Status.valueOf(rs.getString(4)),
                    rs.getString(5));
            byte[] profilePicture = PictureUtil.getPicture(contactUser.getPicturePath());
            contactUser.setPicture(profilePicture);
            contactUser.setLastChatAt(rs.getTimestamp("last_chat_at").toLocalDateTime());
            contactUsers.add(contactUser);
        }
        return contactUsers;
    }

    public ContactUser getFriendContact(String phoneNumber) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                SELECT c.contact_id, u.fname, u.lname, u.status AS user_status, u.picture, c.status, c.user_id
                FROM contacts c
                JOIN users u ON c.contact_id = u.phone_number
                WHERE (c.user_id = ? OR c.contact_id = ?) AND c.status = 'ACCEPTED'
                LIMIT 1
                """;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, phoneNumber);
        ps.setString(2, phoneNumber);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            ContactUser contactUser = new ContactUser(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3), Status.valueOf(rs.getString(4)),
                    rs.getString(5));
            byte[] profilePicture = PictureUtil.getPicture(contactUser.getPicturePath());
            contactUser.setPicture(profilePicture);
            return contactUser;
        }

        return null;
    }


}
