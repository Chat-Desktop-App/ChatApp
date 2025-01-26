package gov.iti.jets.database.dao;

import gov.iti.jets.model.GroupMember;

import java.util.List;

public class GroupMembersDaoImpl implements GroupMembersDao{
    @Override
    public void addGroupMember(GroupMember groupMember) {

    }

    @Override
    public GroupMember getGroupMember(int groupId, String memberId) {
        return null;
    }

    @Override
    public List<GroupMember> getAllGroupMembers() {
        return List.of();
    }

    @Override
    public void deleteGroupMember(int groupId, String memberId) {

    }
}
