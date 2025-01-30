package gov.iti.jets.database.dao;

import gov.iti.jets.client.model.Group;
import gov.iti.jets.client.model.User;

import java.sql.SQLException;
import java.util.List;

public interface GroupDao {
    public int addGroup(Group group) throws SQLException;
    public List<Group> getGroups(String phoneNumber) throws SQLException;
    public int deleteGroup(String adminPhoneNumber, int GroupId) throws SQLException;
    public Group getGroup(int groupId) throws  SQLException;
    public int addGroupMember(int groupId, String phoneNumber) throws SQLException;
    public List<Group> getLastGroups(String phoneNumber) throws SQLException;

    List<User> getAllGroupMembers(String groupId) throws SQLException;
}
