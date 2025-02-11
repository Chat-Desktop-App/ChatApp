package gov.iti.jets.model;

import java.io.Serial;
import java.sql.Timestamp;
import java.util.Arrays;

public class FileMessage extends Message{
    @Serial
    private static final long serialVersionUID = 5677958496409756424L;
    private byte[] fileData;
    private String fileName;
    private Message message;

    public FileMessage(Message message, byte[] fileData, String fileName) {
        super(message);
        this.message = message;
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

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FileMessage{" +
                "fileData=" + Arrays.toString(fileData) +
                ", fileName='" + fileName + '\'' +
                "} " + super.toString();
    }
}
