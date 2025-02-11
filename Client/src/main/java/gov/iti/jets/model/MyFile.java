package gov.iti.jets.model;

import java.util.Arrays;

public class MyFile {
    private int fileId;
    private String fileName;
    private String filePath;
    private byte[] fileData;
    private FileType fileType;
    public MyFile() {

    }

    public MyFile(int fileId, String fileName, String filePath , FileType fileType) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
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

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
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
                ", fileType=" + fileType +
                '}';
    }
}
