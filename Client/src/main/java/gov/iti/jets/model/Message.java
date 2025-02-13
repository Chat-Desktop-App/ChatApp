package gov.iti.jets.model;

import javafx.scene.text.Font;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class Message implements Serializable {

    @Serial
    private static final long serialVersionUID = 5677958496409756424L;

    private int messageId;
    private String senderId;
    private Recipient recipient = null;
    private String receiverId = null;
    private int groupId;
    private String content;
    private int fileId;
    private int fontSize;
    private String fontStyle;
    private String fontColour;
    private boolean isBold;
    private boolean isItalic;
    private boolean isUnderLine;
    private String textBackGroundColour;
    private Timestamp timestamp;
    private String emoji;

    public Message() {
        fontSize = 14;
        fontStyle = Font.getDefault().getName();
        fontColour = "#000000";
        isBold = false;
        isItalic = false;
        isUnderLine = false;
        textBackGroundColour = "#3d7eb6";
    }

    public Message(String senderId, Recipient recipient, String receiverId, int groupId) {
        this.senderId = senderId;
        this.recipient = recipient;
        this.receiverId = receiverId;
        this.groupId = groupId;
        fontSize = 14;
        fontStyle = Font.getDefault().getName();
        fontColour = "#000000";
        isBold = false;
        isItalic = false;
        isUnderLine = false;
        textBackGroundColour = "#3d7eb6";
    }

    public Message(Message message) {
        messageId = message.getMessageId();
        senderId = message.getSenderId();
        recipient = message.getRecipient();
        receiverId = message.getReceiverId();
        groupId = message.getGroupId();
        content = message.getContent();
        fileId = message.getFileId();
        fontSize = message.getFontSize();
        fontStyle = message.getFontStyle();
        fontColour = message.getFontColour();
        isBold = message.isBold();
        isItalic = message.isItalic();
        isUnderLine = message.isUnderLine();
        textBackGroundColour = message.getTextBackGroundColour();
        timestamp = message.gettimestamp();
        emoji = message.getEmoji();
    }

    public Message(int messageId, String senderId, Recipient recipient, String receiverId, int groupId, String content, int fileId, int fontSize, String fontStyle, String fontColour, boolean isBold, boolean isItalic, boolean isUnderLine, String textBackGroundColour, Timestamp timestamp, String emoji) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.recipient = recipient;
        this.receiverId = receiverId;
        this.groupId = groupId;
        this.content = content;
        this.fileId = fileId;
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
        this.fontColour = fontColour;
        this.isBold = isBold;
        this.isItalic = isItalic;
        this.isUnderLine = isUnderLine;
        this.textBackGroundColour = textBackGroundColour;
        this.timestamp = timestamp;
        this.emoji = emoji;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getFontColour() {
        return fontColour;
    }

    public void setFontColour(String fontColour) {
        this.fontColour = fontColour;
    }

    public boolean isBold() {
        return isBold;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }

    public boolean isItalic() {
        return isItalic;
    }

    public void setItalic(boolean italic) {
        isItalic = italic;
    }

    public String getTextBackGroundColour() {
        return textBackGroundColour;
    }

    public void setTextBackGroundColour(String textBackGroundColour) {
        this.textBackGroundColour = textBackGroundColour;
    }

    public boolean isUnderLine() {
        return isUnderLine;
    }

    public void setUnderLine(boolean underLine) {
        isUnderLine = underLine;
    }

    public Timestamp gettimestamp() {
        return timestamp;
    }

    public void settimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", senderId='" + senderId + '\'' +
                ", recipient=" + recipient +
                ", receiverId='" + receiverId + '\'' +
                ", groupId=" + groupId +
                ", content='" + content + '\'' +
                ", fileId=" + fileId +
                ", fontSize=" + fontSize +
                ", fontStyle='" + fontStyle + '\'' +
                ", fontColour='" + fontColour + '\'' +
                ", isBold=" + isBold +
                ", isItalic=" + isItalic +
                ", isUnderLine=" + isUnderLine +
                ", textBackGroundColour='" + textBackGroundColour + '\'' +
                ", timestamp=" + timestamp +
                ", emoji='" + emoji + '\'' +
                '}';
    }
}
