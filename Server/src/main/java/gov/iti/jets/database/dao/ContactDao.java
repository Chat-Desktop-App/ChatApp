package gov.iti.jets.database.dao;


import gov.iti.jets.model.ContactUser;
import java.sql.SQLException;
import java.util.List;

public interface ContactDao {
    public List<ContactUser> getFriendsContacts(String phoneNumber) throws SQLException;
    public List<ContactUser> getOnlineContacts(String phoneNumber) throws SQLException;
    public List<ContactUser> getPendingContacts(String phoneNumber) throws  SQLException;
    public List<ContactUser> getBlockedContacts(String phoneNumber) throws  SQLException;
    public List<ContactUser> getAllContacts(String phoneNumber) throws  SQLException;
    public int addContact(String phoneNumber, String contactPhoneNumber) throws SQLException;
    public int updateContact(ContactUser contactUser) throws SQLException;
    public List<ContactUser> getLastContact(String phoneNumber) throws SQLException;
}
