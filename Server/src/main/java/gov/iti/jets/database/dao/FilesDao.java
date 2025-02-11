package gov.iti.jets.database.dao;

import gov.iti.jets.model.MyFile;

import java.util.List;

public interface FilesDao {
    int addFile(MyFile file);
    MyFile getFileById(int fileId);
    List<MyFile> getAllFiles();
    void updateFile(MyFile file);
    void deleteFile(int fileId);
}
