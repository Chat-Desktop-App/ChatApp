package gov.iti.jets.database.dao;

import gov.iti.jets.database.DataBaseConnection;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.Status;

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
                WHERE c.user_id = ? AND c.status = 'ACCEPTED'
                """;
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, phoneNumber);
        ResultSet rs = ps.executeQuery();
        List<ContactUser> contactUsers = new ArrayList<>();

        while(rs.next()){
            ContactUser contactUser = new ContactUser(rs.getString(1),
                    rs.getString(2), rs.getString(3), Status.valueOf(rs.getString(4)));
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
                    rs.getString(2), rs.getString(3), Status.valueOf(rs.getString(4)));
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
                    rs.getString(2), rs.getString(3), Status.valueOf(rs.getString(4)));
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
                    rs.getString(2), rs.getString(3), Status.valueOf(rs.getString(4)));
            contactUsers.add(contactUser);
        }
        return  contactUsers;
    }

    @Override
    public int addContact(String phoneNumber, String contactPhoneNumber) throws SQLException {
        //check contact is a user
        // add phone number as
        // add contact and set status to ?
        return 0;

    }

    public static void main(String[] args){
        ContactDaoImpl contactDao = new ContactDaoImpl();
        String phoneNumber = "+1234567890";  // Jane's phone number
        try{
            // Test for friends contacts
            List<ContactUser> friends = contactDao.getFriendsContacts(phoneNumber);
            System.out.println("Friends Contacts: " + friends.size());
            for (ContactUser contact : friends) {
                System.out.println(contact);
            }

            // Test for pending contacts
            List<ContactUser> pending = contactDao.getPendingContacts(phoneNumber);
            System.out.println("Pending Contacts: " + pending.size());
            for (ContactUser contact : pending) {
                System.out.println(contact);
            }

            // Test for blocked contacts
            List<ContactUser> blocked = contactDao.getBlockedContacts(phoneNumber);
            System.out.println("Blocked Contacts: " + blocked.size());
            for (ContactUser contact : blocked) {
                System.out.println(contact);
            }

            // Test for all contacts
            List<ContactUser> allContacts = contactDao.getAllContacts(phoneNumber);
            System.out.println("All Contacts: " + allContacts.size());
            for (ContactUser contact : allContacts) {
                System.out.println(contact);
            }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    }




}
