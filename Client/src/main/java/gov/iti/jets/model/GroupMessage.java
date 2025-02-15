package gov.iti.jets.model;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

public class GroupMessage extends Message implements Serializable {

    @Serial
    private static final long serialVersionUID = 5677958496409756424L;
    private final byte[] profilePicture;
    private String name;


    public GroupMessage(int messageId, String senderId, Recipient recipient, String receiverId, int groupId,
                        String content, int fileId, int fontSize, String fontStyle, String fontColour, boolean isBold,
                        boolean isItalic, boolean isUnderLine, String textBackGroundColour, Timestamp timestamp,
                        String emoji, String name, byte[] profilePicture) {

        super(messageId, senderId, recipient, receiverId, groupId, content, fileId, fontSize, fontStyle, fontColour,
                isBold, isItalic, isUnderLine, textBackGroundColour, timestamp, emoji);

        this.profilePicture = profilePicture;
        this.name = name;
    }

    public GroupMessage(Message message, String name, byte[] profilePicture) {
        super(message);
        this.profilePicture = profilePicture;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    @Override
    public String toString() {
        return "GroupMessage{" +
                "profilePicture=" + Arrays.toString(profilePicture) +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}
