package gov.iti.jets.database.dao;

import gov.iti.jets.model.Group;
import gov.iti.jets.model.User;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface GroupDao {
    public int addGroup(Group group) throws SQLException;
    public List<Group> getGroups(String phoneNumber) throws SQLException;
    public int deleteGroup(String adminPhoneNumber, int GroupId) throws SQLException;
    public Group getGroupByID(int groupId) throws  SQLException;
    public int addGroupMember(int groupId, String phoneNumber) throws SQLException;
    public List<Group> getLastGroups(String phoneNumber) throws SQLException;
    public int updateGroupPicture(int group_id, byte[] image) throws SQLException;

    List<User> getAllGroupMembers(int groupId) throws SQLException;

    boolean updateLastMessage(int groupId, Timestamp timestamp) throws SQLException;
}
