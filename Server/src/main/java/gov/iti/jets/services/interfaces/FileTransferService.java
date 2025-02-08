package gov.iti.jets.services.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileTransferService extends Remote {
    boolean uploadFile (byte[] fileData, String fileName, String fileTypr) throws RemoteException;
    byte[] downloadFile (int fileId) throws RemoteException;
}
