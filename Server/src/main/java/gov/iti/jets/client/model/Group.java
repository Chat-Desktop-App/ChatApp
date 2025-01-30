package gov.iti.jets.client.model;

import java.time.LocalDateTime;

public class Group {
    private int groupId;
    private String groupName;
    private String adminId;
    private String picture;
    private LocalDateTime lastChatAt;

    public Group() {
    }

    public Group(int groupId, String groupName, String adminId, String picture) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.adminId = adminId;
        this.picture = picture;
    }

    public Group(int groupId, String groupName, String adminId, String picture, LocalDateTime lastChatAt) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.adminId = adminId;
        this.picture = picture;
        this.lastChatAt = lastChatAt;

    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public LocalDateTime getLastChatAt() {
        return lastChatAt;
    }

    public void setLastChatAt(LocalDateTime lastChatAt) {
        this.lastChatAt = lastChatAt;
    }
}
