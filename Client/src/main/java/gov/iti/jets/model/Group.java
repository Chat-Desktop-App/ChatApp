package gov.iti.jets.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Group implements  Chatable {
    @Serial
    private static final long serialVersionUID = 5677958496409793424L;
    private int groupId;
    private String groupName;
    private String adminId;
    private transient String picturePath;
    private byte[] picture;
    private LocalDateTime lastChatAt;

    public Group() {
    }

    public Group(int groupId, String groupName, String adminId, String picture) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.adminId = adminId;
        this.picturePath = picture;
    }

    public Group(int groupId, String groupName, String adminId, String picture, LocalDateTime lastChatAt) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.adminId = adminId;
        this.picturePath = picture;
        this.lastChatAt = lastChatAt;

    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return groupName;
    }

    public void setName(String groupName) {
        this.groupName = groupName;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public LocalDateTime getLastChatAt() {
        return lastChatAt;
    }

    public void setLastChatAt(LocalDateTime lastChatAt) {
        this.lastChatAt = lastChatAt;
    }
}
