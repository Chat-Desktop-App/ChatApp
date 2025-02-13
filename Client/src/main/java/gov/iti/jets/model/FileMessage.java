package gov.iti.jets.model;

import java.io.Serial;
import java.sql.Timestamp;

public class FileMessage extends Message {
    @Serial
    private static final long serialVersionUID = 5677958496409756424L;
    private int fileSize;
    private String fileName;
    private FileType fileType;
    private Message message;

    public FileMessage(int messageId, String senderId, Recipient recipient, String receiverId, int groupId,
                       String content, int fileId, int fontSize, String fontStyle, String fontColour, boolean isBold,
                       boolean isItalic, boolean isUnderLine, String textBackGroundColour, Timestamp timestamp,
                       String emoji, int fileSize, String fileName) {
        super(messageId, senderId, recipient, receiverId, groupId, content, fileId, fontSize, fontStyle, fontColour,
                isBold, isItalic, isUnderLine, textBackGroundColour, timestamp, emoji);
        this.fileSize = fileSize;
        this.fileName = fileName;
    }

    public FileMessage(String senderId, Recipient recipient, String receiverId, int groupId, int fileSize, String fileName) {
        super(senderId, recipient, receiverId, groupId);
        this.fileSize = fileSize;
        this.fileName = fileName;
    }

    public FileMessage(Message message, int fileSize, String fileName, FileType fileType) {
        super(message);
        this.message = message;
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.fileType = fileType;

    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "FileMessage{" +
                "fileSize=" + fileSize +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", message=" + message +
                "} " + super.toString();
    }
}
