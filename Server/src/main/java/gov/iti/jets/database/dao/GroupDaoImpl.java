package gov.iti.jets.database.dao;

import gov.iti.jets.database.DataBaseConnection;
import gov.iti.jets.model.Group;
import gov.iti.jets.model.User;
import gov.iti.jets.utility.PictureUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl implements GroupDao{
    static DataBaseConnection dataBaseConnection;
    static {
        try {
            dataBaseConnection = DataBaseConnection.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int addGroup(Group group) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
               INSERT INTO `groups` (group_name, admin_id)
               VALUES (?, ?)
               """;
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, group.getName());
        ps.setString(2, group.getAdminId());
        int n = ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1); // Return the generated ID
        }
        return -1; // Return -1 if no ID was generated
    }

    public int updateGroupPicture(int group_id, byte[] image) throws SQLException {

        String path = PictureUtil.saveGroupProfilePicture(image, String.valueOf(group_id));
        String query = """ 
                            UPDATE `groups`
                            SET picture = ?
                            WHERE group_id = ?""";
        Connection con = dataBaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, path);
        ps.setInt(2, group_id);
        int n = ps.executeUpdate();
        return n;
    }

    @Override
    public List<Group> getGroups(String phoneNumber) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query= """
        SELECT g.group_id, g.group_name, g.admin_id, g.picture
        FROM `groups` g
        JOIN group_members gm ON g.group_id = gm.group_id
        WHERE gm.member_id = ?
        """;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,phoneNumber);
        ResultSet rs = ps.executeQuery();
        List<Group> groups = new ArrayList<>();
        while (rs.next()){
            Group group = new Group();
            group.setGroupId(rs.getInt("group_id"));
            group.setName(rs.getString("group_name"));
            group.setAdminId(rs.getString("admin_id"));
            group.setPicturePath(rs.getString("picture"));
            groups.add(group);
        }

    return groups;
    }

    @Override
    public int deleteGroup(String adminPhoneNumber, int groupId ) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                DELETE FROM `group_members` WHERE group_id = ?
                """;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, groupId);
        ps.executeUpdate();
        query= """
        DELETE FROM `groups` WHERE admin_id = ?
        """;
        ps = con.prepareStatement(query);
        ps.setString(1,adminPhoneNumber);
        int n = ps.executeUpdate();
        return n;
    }

    @Override
    public Group getGroupByID(int groupId) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = " SELECT * FROM `groups` WHERE group_id = ? ";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, groupId);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Group group = new Group();
            group.setGroupId(rs.getInt("group_id"));
            group.setName(rs.getString("group_name"));
            group.setAdminId(rs.getString("admin_id"));
            String pic = rs.getString("picture");
            group.setPicturePath(pic);
            group.setPicture(PictureUtil.getPicture(pic));
            group.setLastChatAt(rs.getTimestamp("last_chat_at").toLocalDateTime());
            return group;
        }else{
            return null;
        }
    }

    @Override
    public int addGroupMember(int groupId, String phoneNumber) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                INSERT INTO group_members (group_id, member_id)
                VALUES (?, ?)
                """;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, groupId);
        ps.setString(2,phoneNumber);
        int n  = ps.executeUpdate();
        return n;
    }

    @Override
    public List<Group> getLastGroups(String phoneNumber) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query= """
        SELECT g.group_id, g.group_name, g.admin_id, g.picture ,g.last_chat_at
        FROM `groups` g
        JOIN group_members gm ON g.group_id = gm.group_id
        WHERE gm.member_id = ?
        ORDER BY g.last_chat_at DESC
        """;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,phoneNumber);
        ResultSet rs = ps.executeQuery();
        List<Group> groups = new ArrayList<>();
        while (rs.next()){
            Group group = new Group();
            group.setGroupId(rs.getInt("group_id"));
            group.setName(rs.getString("group_name"));
            group.setAdminId(rs.getString("admin_id"));
            String pic = rs.getString("picture");
            group.setPicturePath(pic);
            group.setPicture(PictureUtil.getPicture(pic));
            group.setLastChatAt(rs.getTimestamp("last_chat_at").toLocalDateTime());
            groups.add(group);
        }
        return groups;
    }

    @Override
    public List<User> getAllGroupMembers(int groupId) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                    SELECT u.phone_number, u.fname, u.lname
                    FROM group_members gm
                    JOIN users u ON gm.member_id = u.phone_number
                    WHERE gm.group_id = ?
                    """;

        List<User> groupMembers = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1,groupId);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {

            User user = new User(
                    resultSet.getString("phone_number"),
                    resultSet.getString("fname"),
                    resultSet.getString("lname")
            );
            groupMembers.add(user);
        }
        return groupMembers;
    }

    @Override
    public boolean updateLastMessage(int groupId, Timestamp timestamp) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                UPDATE `groups` SET last_chat_at = ?
                WHERE group_id = ?
                """;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setTimestamp(1, timestamp);
        ps.setInt(2, groupId);
        return ps.executeUpdate() > 0;
    }
}
