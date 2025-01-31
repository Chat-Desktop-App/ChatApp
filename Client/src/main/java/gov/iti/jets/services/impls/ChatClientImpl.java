package gov.iti.jets.services.impls;

import gov.iti.jets.services.interfaces.ChatClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient  {
    public ChatClientImpl() throws RemoteException {
    }

    @Override
    public void receive(String msg) throws RemoteException {

    }
}
