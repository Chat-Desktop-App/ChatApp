package gov.iti.jets.client.model;

public class Files {
    private int fileId;
    private String fileName;
    private String fileType;

    public Files() {

    }

    public Files(int fileId, String fileName, String fileType) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileType = fileType;
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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    @Override
    public String toString() {
        return "FileEntity{" +
                "fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
