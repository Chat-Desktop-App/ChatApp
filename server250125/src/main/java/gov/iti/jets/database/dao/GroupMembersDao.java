package gov.iti.jets.database.dao;

import gov.iti.jets.model.GroupMember;

import java.util.List;

public interface GroupMembersDao {
    void addGroupMember(GroupMember groupMember);
    GroupMember getGroupMember(int groupId, String memberId);
    List<GroupMember> getAllGroupMembers();
    void deleteGroupMember(int groupId, String memberId);

}
