package gov.iti.jets.model;

public class Files {

    private String fileID;
    private String fileName;
    private String fileType;

    public Files( String fileType){
        this.fileType = fileType;
    }

    public void set_fileID(String fileID){
        this.fileID = fileID;
    }
    public void set_fileName(String fileName){
        this.fileName = fileName;
    }
    public void set_fileType(String fileType){
        this.fileType = fileType;
    }
    public String get_fileType(){
        return fileType;
    }
    public String get_fileID(){
        return fileID;
    }
    public String get_fileName(){
        return fileName;
    }

    
}
