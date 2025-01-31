package gov.iti.jets.database.dao;

import gov.iti.jets.database.DataBaseConnection;
import gov.iti.jets.model.Group;
import gov.iti.jets.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, group.getGroupName());
        ps.setString(2, group.getAdminId());

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
            group.setGroupName(rs.getString("group_name"));
            group.setAdminId(rs.getString("admin_id"));
            group.setPicture(rs.getString("picture"));


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
    public Group getGroup(int groupId) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = " SELECT * FROM `groups` WHERE group_id = ? ";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, groupId);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            Group group = new Group();
            group.setGroupId(rs.getInt("group_id"));
            group.setGroupName(rs.getString("group_name"));
            group.setAdminId(rs.getString("admin_id"));
            group.setPicture(rs.getString("picture"));

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
        SELECT g.group_id, g.group_name, g.admin_id, g.picture
        FROM `groups` g
        JOIN group_members gm ON g.group_id = gm.group_id
        WHERE gm.member_id = ? AND last_chat_at IS NOT NULL
        ORDER BY c.last_chat_at DESC AND c.last_chat_at IS NOT NULL
        """;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,phoneNumber);
        ResultSet rs = ps.executeQuery();
        List<Group> groups = new ArrayList<>();
        while (rs.next()){
            Group group = new Group();
            group.setGroupId(rs.getInt("group_id"));
            group.setGroupName(rs.getString("group_name"));
            group.setAdminId(rs.getString("admin_id"));
            group.setPicture(rs.getString("picture"));


            groups.add(group);
        }

        return groups;
    }



    @Override
    public List<User> getAllGroupMembers(String groupId) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """
                    SELECT u.phone_number, u.fname, u.lname
                    FROM group_members gm
                    JOIN users u ON gm.member_id = u.phone_number
                    WHERE gm.group_id = ?
                    """;

        List<User> groupMembers = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1,groupId);
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

    public static void main(String[] args) {
        GroupDaoImpl groupDao = new GroupDaoImpl();

        try {
            // Add a new group with Alice as the admin

            Group newGroup = new Group();
            newGroup.setGroupName("Tech Enthusiasts");
            newGroup.setAdminId("+1122334455"); // Alice's phone number
            int addedGroupResult = groupDao.addGroup(newGroup);
            if (addedGroupResult > 0) {
                System.out.println("Group successfully added.");
            } else {
                System.out.println("Failed to add group.");
                return;
            }

            // Get the group ID of the newly created group 
            int groupId = 1;
            System.out.println("Group ID of newly created group is assumed to be: " + groupId);

            // Step 2: Add Jane as a member of the group
            System.out.println("Step 2: Adding Jane as a member of the group...");
            String janePhoneNumber = "+1234567890";
            int addMemberResult = groupDao.addGroupMember(groupId, janePhoneNumber);
            if (addMemberResult > 0) {
                System.out.println("Jane successfully added to the group.");
            } else {
                System.out.println("Failed to add Jane to the group.");
                return;
            }

            //Retrieve all groups Jane is a member of

            List<Group> janeGroups = groupDao.getGroups(janePhoneNumber);
            if (!janeGroups.isEmpty()) {
                System.out.println("Groups Jane is a member of:");
                for (Group group : janeGroups) {
                    System.out.println("Group ID: " + group.getGroupId());
                    System.out.println("Group Name: " + group.getGroupName());
                    System.out.println("Admin ID: " + group.getAdminId());
                    System.out.println("Picture: " + group.getPicture());
                    System.out.println("-------------");
                }
            } else {
                System.out.println("No groups found for Jane.");
            }

            //Retrieve details of the newly created group

            Group retrievedGroup = groupDao.getGroup(groupId);
            if (retrievedGroup != null) {
                System.out.println("Retrieved Group Details:");
                System.out.println("Group ID: " + retrievedGroup.getGroupId());
                System.out.println("Group Name: " + retrievedGroup.getGroupName());
                System.out.println("Admin ID: " + retrievedGroup.getAdminId());
                System.out.println("Picture: " + retrievedGroup.getPicture());
            } else {
                System.out.println("Group not found.");
            }

            //Delete the group and associated members

            int deleteGroupMembersResult = groupDao.deleteGroup("+1122334455", groupId); // Alice's phone number
            if (deleteGroupMembersResult > 0) {
                System.out.println("Group and associated members successfully deleted.");
            } else {
                System.out.println("Failed to delete the group and its members.");
            }

        } catch (SQLException e) {
            System.err.println("An error occurred while testing GroupDaoImpl:");
            e.printStackTrace();
        }
    }
}
