package gov.iti.jets.utility;

import gov.iti.jets.database.dao.FilesDao;
import gov.iti.jets.database.dao.FilesDaoImpl;
import gov.iti.jets.model.MyFile;

import java.io.*;
import java.nio.file.Files;

public class FileUtil {
    private static FilesDao filesDao = new FilesDaoImpl();
    private static final String FILE_STORAGE_PATH = "server_files/";

     static {
        File directory = new File(FILE_STORAGE_PATH);
        if (!directory.exists()) {
            directory.mkdirs(); // Ensure the storage directory exists
        }
    }

    public static int addFile(byte[] fileData, String fileName) {
        try {
            String filePath = FILE_STORAGE_PATH + fileName; // Save file in server_files/

            // Write the file to the server directory
            File file = new File(filePath);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(fileData);
            }

            // Save file metadata to database
            MyFile newFile = new MyFile(0, fileName, filePath);
            return filesDao.addFile(newFile) ;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public static MyFile getFile(int fileId) {
        MyFile fileRecord = filesDao.getFileById(fileId);
        if (fileRecord == null || fileRecord.getFilePath() == null) {
            return null;
        }

        File file = new File(fileRecord.getFilePath());
        if (!file.exists()) {
            return null;
        }

        try {
            byte[] fileData = Files.readAllBytes(file.toPath());
            fileRecord.setFileData(fileData);
            return fileRecord;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
