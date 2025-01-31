package gov.iti.jets.model;

public class Message {
    private String messag_ID;
    private String sender_ID;
    private String receiver_ID;
    private String content;
    private String groupID;
    private String fileID;
    private int fontSize;
    private String fontStyle;
    private boolean isBold;
    private boolean isItalic;
    private String textBackgroundColor;
    private String timestamp;

    public Message(String sender, String text, String timestamp) {
        this.sender_ID = sender;
        this.content = text;
        this.timestamp = timestamp;
    }
    public boolean isSentByCurrentUser(String currentUserId) {
        return sender_ID.equals(currentUserId);
    }

    // Getters and setters
    public String getSender() { return sender_ID; }
    public String getText() { return content; }
    public String getTimestamp() { return timestamp; }

}
