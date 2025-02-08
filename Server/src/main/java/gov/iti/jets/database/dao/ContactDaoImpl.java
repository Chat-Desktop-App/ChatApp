package gov.iti.jets.database.dao;

import gov.iti.jets.database.DataBaseConnection;
import gov.iti.jets.model.ContactStatus;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.Status;
import gov.iti.jets.utility.PictureUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements  ContactDao{
    static DataBaseConnection dataBaseConnection;
    static {
        try {
            dataBaseConnection = DataBaseConnection.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<ContactUser> getFriendsContacts(String phoneNumber) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                SELECT c.contact_id, u.fname, u.lname,  u.status AS user_status, u.picture,  c.status, c.user_id
                    FROM contacts c
                    JOIN users u ON c.contact_id = u.phone_number
                    WHERE (c.user_id = ? or c.contact_id = ?) AND c.status = 'ACCEPTED'
                """;
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, phoneNumber);
        ps.setString(2, phoneNumber);
        ResultSet rs = ps.executeQuery();
        List<ContactUser> contactUsers = new ArrayList<>();

        while(rs.next()){
            ContactUser contactUser = new ContactUser(
                    rs.getString(1),
                    rs.getString(2), rs.getString(3), Status.valueOf(rs.getString(4)),
                    rs.getString(5));
            byte[] profilePicture = PictureUtil.getPicture(contactUser.getPicturePath());
            contactUser.setPicture(profilePicture);
            contactUsers.add(contactUser);
        }
        return  contactUsers;
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
        while(rs.next()){
            ContactUser contactUser = new ContactUser(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    Status.valueOf(rs.getString(4)),
                    rs.getString(5) );
            byte[] profilePicture = PictureUtil.getPicture(contactUser.getPicturePath());
            contactUser.setPicture(profilePicture);
            contactUsers.add(contactUser);
        }
        return  contactUsers;
    }


    @Override
    public List<ContactUser> getPendingContacts(String phoneNumber) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                SELECT c.contact_id, u.fname, u.lname,  u.status AS user_status, u.picture,  c.status, c.user_id
                FROM contacts c
                JOIN users u ON c.contact_id = u.phone_number
                WHERE c.user_id = ? AND c.status = 'PENDING'
                """;
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, phoneNumber);
        ResultSet rs = ps.executeQuery();
        List<ContactUser> contactUsers = new ArrayList<>();

        while(rs.next()){
            ContactUser contactUser = new ContactUser(rs.getString(1),
                    rs.getString(2), rs.getString(3), Status.valueOf(rs.getString(4)), rs.getString(5));
            byte[] profilePicture = PictureUtil.getPicture(contactUser.getPicturePath());
            contactUser.setPicture(profilePicture);
            contactUsers.add(contactUser);
        }
        return  contactUsers;
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

        while(rs.next()){
            ContactUser contactUser = new ContactUser(rs.getString(1),
                    rs.getString(2), rs.getString(3), Status.valueOf(rs.getString(4)),rs.getString(5));
            byte[] profilePicture = PictureUtil.getPicture(contactUser.getPicturePath());
            contactUser.setPicture(profilePicture);
            contactUsers.add(contactUser);
        }
        return  contactUsers;
    }

    @Override
    public List<ContactUser> getAllContacts(String phoneNumber) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                SELECT c.contact_id, u.fname, u.lname,  u.status AS user_status, u.picture,  c.status, c.user_id
                FROM contacts c
                JOIN users u ON c.contact_id = u.phone_number
                WHERE c.user_id = ?
                """;
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, phoneNumber);
        ResultSet rs = ps.executeQuery();
        List<ContactUser> contactUsers = new ArrayList<>();

        while(rs.next()){
            ContactUser contactUser = new ContactUser(rs.getString(1),
                    rs.getString(2), rs.getString(3), Status.valueOf(rs.getString(4)), rs.getString(5));
            byte[] profilePicture = PictureUtil.getPicture(contactUser.getPicturePath());
            contactUser.setPicture(profilePicture);
            contactUsers.add(contactUser);
        }
        return  contactUsers;
    }

    @Override
    public int addContact(String phoneNumber, String contactPhoneNumber) throws SQLException {
        // add phone number as a contact to co
        // add contact and set status to ?
        Connection con = dataBaseConnection.getConnection();
        String query = "INSERT INTO contacts (contact_id, user_id, status) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, phoneNumber);
        ps.setString(2, contactPhoneNumber);
        ps.setString(3,ContactStatus.PENDING.toString());

        return ps.executeUpdate();

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

        while(rs.next()){
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
        return  contactUsers;
    }

    public static void main(String[] args){
        ContactDaoImpl contactDao = new ContactDaoImpl();
        String phoneNumber = "+1234567890";  // Janes's phone number
        String contactPhoneNumber = "+1122334455"; // alice's phone number
        try{
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




}
