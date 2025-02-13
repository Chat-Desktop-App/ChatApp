package gov.iti.jets.services.interfaces;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatBot extends Remote {
    String chatWithAI(String userMessage, String fname) throws RemoteException;
}
