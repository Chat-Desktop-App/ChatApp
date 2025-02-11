package gov.iti.jets.services.impls;

import gov.iti.jets.controller.HomeServiceController;
import gov.iti.jets.controller.MessageServiceController;
import gov.iti.jets.controller.Session;
import gov.iti.jets.model.Chatable;
import gov.iti.jets.model.Message;
import gov.iti.jets.model.Status;
import gov.iti.jets.services.interfaces.ChatClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient  {
    public ChatClientImpl() throws RemoteException {
    }

    @Override
    public void receive(Message message) throws RemoteException {
        MessageServiceController.receiveMessage(message);
    }

    @Override
    public boolean addToLastContactList(Chatable chatable) throws RemoteException {
        return HomeServiceController.addToLastContactList(chatable);
    }

    @Override
    public void updateStatus(String contactPhone, Status status) throws RemoteException {
        System.out.println( contactPhone + " : " +status);
        Session.updateStatus(contactPhone , status);
    }
}
