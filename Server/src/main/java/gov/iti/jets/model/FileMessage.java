package gov.iti.jets.model;

import java.io.Serial;
import java.sql.Timestamp;
import java.util.Arrays;

public class FileMessage extends Message{
    @Serial
    private static final long serialVersionUID = 5677958496409756424L;
    private byte[] fileData;
    private String fileName;
    public FileMessage(int messageId, String senderId, Recipient recipient, String receiverId, int groupId,
                       String content, int fileId, int fontSize, String fontStyle, String fontColour, boolean isBold,
                       boolean isItalic, boolean isUnderLine, String textBackGroundColour, Timestamp timestamp,
                       String emoji, byte[] fileData, String  fileName) {
        super(messageId, senderId, recipient, receiverId, groupId, content, fileId, fontSize, fontStyle, fontColour,
                isBold, isItalic,isUnderLine, textBackGroundColour, timestamp, emoji );
        this.fileData = fileData;
        this.fileName = fileName;
    }

    public FileMessage(String senderId, Recipient recipient, String receiverId, int groupId, byte[] fileData, String fileName) {
        super(senderId, recipient, receiverId, groupId);
        this.fileData = fileData;
        this.fileName = fileName;
    }

    public FileMessage(Message message, byte[] fileData, String fileName) {
        super(message);
        this.fileData = fileData;
        this.fileName = fileName;

    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }



    @Override
    public String toString() {
        return "FileMessage{" +
                "fileData=" + Arrays.toString(fileData) +
                ", fileName='" + fileName + '\'' +
                "} " + super.toString();
    }
}
