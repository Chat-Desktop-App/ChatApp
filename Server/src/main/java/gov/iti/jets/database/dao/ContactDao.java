package gov.iti.jets.database.dao;


import gov.iti.jets.model.ContactStatus;
import gov.iti.jets.model.ContactUser;
import gov.iti.jets.model.Status;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface ContactDao {
    public List<ContactUser> getFriendsContacts(String phoneNumber) throws SQLException;
    public List<ContactUser> getOnlineContacts(String phoneNumber) throws SQLException;
    public List<ContactUser> getRejectedContacts(String phoneNumber) throws SQLException;
    public List<ContactUser> getPendingContacts(String phoneNumber) throws  SQLException;
    public List<ContactUser> getBlockedContacts(String phoneNumber) throws  SQLException;
    public List<ContactUser> getAllContacts(String phoneNumber) throws  SQLException;
    public int addContact(String phoneNumber, String contactPhoneNumber) throws SQLException;
    Boolean updateContact(String u1, String u2, ContactStatus status) throws SQLException;

    Boolean updateLastContact(String u1, String u2, Timestamp lastChat) throws SQLException;

    public List<ContactUser> getLastContact(String phoneNumber) throws SQLException;
    public ContactUser getFriendContact(String phoneNumber) throws SQLException;
}
