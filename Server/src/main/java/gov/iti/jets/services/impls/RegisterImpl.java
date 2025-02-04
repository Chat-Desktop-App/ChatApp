package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.UserDao;
import gov.iti.jets.database.dao.UserDaoImpl;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.Register;
import gov.iti.jets.utility.PictureUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class RegisterImpl  extends UnicastRemoteObject implements Register {

    public RegisterImpl() throws RemoteException {
    }


    @Override
    public User SignUp(User user) throws RemoteException {



        UserDao dao = new UserDaoImpl();


        try {
            if(dao.getUser(user.getPhoneNumber()) != null){

                return null;
            }

            if(user.getPicture() != null && user.getPicture().length > 0){
                String uploadedPicturePath = PictureUtil.saveUserProfilePicture(user.getPicture(), user);
                user.setPicturePath(uploadedPicturePath);


            }
            dao.addUser(user);

            System.out.println("user added successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return user;
    }


}
