package gov.iti.jets.services.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileTransferService extends Remote {
    int uploadFile (byte[] fileData, String fileName) throws RemoteException;
    byte[] downloadFile (int fileId) throws RemoteException;
}
