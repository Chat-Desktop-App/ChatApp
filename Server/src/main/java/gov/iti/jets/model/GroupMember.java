package gov.iti.jets.model;

public class GroupMember {
    private int groupId;
    private String memberId;

    public GroupMember() {

    }
    public GroupMember(int groupId, String memberId){
        this.groupId = groupId;
        this.memberId = memberId;
    }
    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public String getMemberId(){
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public String toString() {
        return "GroupMember{" +
                "groupId='" + groupId + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }

}
