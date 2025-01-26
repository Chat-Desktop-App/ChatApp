package gov.iti.jets.database.dao;

import gov.iti.jets.model.Files;

import java.util.List;

public interface FilesDao {
    void addFile(Files file);
    Files getFileById(int fileId);
    List<Files> getAllFiles();
    void updateFile(Files file);
    void deleteFile(int fileId);
}
