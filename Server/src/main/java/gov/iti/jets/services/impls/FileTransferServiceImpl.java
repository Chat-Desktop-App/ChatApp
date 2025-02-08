package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.FilesDao;
import gov.iti.jets.database.dao.FilesDaoImpl;
import gov.iti.jets.model.Files;
import gov.iti.jets.services.interfaces.FileTransferService;

import java.io.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FileTransferServiceImpl extends UnicastRemoteObject implements FileTransferService {
    private FilesDao filesDao = new FilesDaoImpl();
    private static final String FILE_STORAGE_PATH = "server_files/";

    public FileTransferServiceImpl() throws RemoteException {
        super();
        this.filesDao = new FilesDaoImpl();
        File directory = new File(FILE_STORAGE_PATH);
        if (!directory.exists()) {
            directory.mkdirs(); // Ensure the storage directory exists
        }
    }

    @Override
    public boolean uploadFile(byte[] fileData, String fileName, String fileType) throws RemoteException {
        try {
            String filePath = FILE_STORAGE_PATH + fileName; // Save file in server_files/

            // Write the file to the server directory
            File file = new File(filePath);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(fileData);
            }

            // Save file metadata to database
            Files newFile = new Files(0, fileName, fileType, filePath);
            return filesDao.addFile(newFile) > 0;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public byte[] downloadFile(int fileId) throws RemoteException {
        Files fileRecord = filesDao.getFileById(fileId);
        if (fileRecord == null || fileRecord.getFilePath() == null) {
            return null;
        }

        File file = new File(fileRecord.getFilePath());
        if (!file.exists()) {
            return null;
        }

        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
