package gov.iti.jets.model;

import java.util.Arrays;

public class MyFile {
    private int fileId;
    private String fileName;
    private String filePath;
    private byte[] fileData;
    public MyFile() {

    }

    public MyFile(int fileId, String fileName, String filePath) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileData=" + Arrays.toString(fileData) +
                '}';
    }
}
