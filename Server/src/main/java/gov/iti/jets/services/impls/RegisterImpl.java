package gov.iti.jets.services.impls;

import gov.iti.jets.database.dao.UserDao;
import gov.iti.jets.database.dao.UserDaoImpl;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.Register;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class RegisterImpl  extends UnicastRemoteObject implements Register {
    private static final String FOLDER_PATH = System.getProperty("user.dir") + File.separator + "pictures" + File.separator;
    public RegisterImpl() throws RemoteException {
    }


    @Override
    public User SignUp(User user, byte[] profilePicture) throws RemoteException {

        // TODO make sure its a valid phone number first
        UserDao dao = new UserDaoImpl();
        try {
            dao.addUser(user);
            if(profilePicture != null && profilePicture.length > 0){
                String uploadedPicturePath = saveUserProfilePicture(profilePicture, user);
                dao.updatePicture(user.getPhoneNumber(), uploadedPicturePath);
            }

            System.out.println("user added successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    private String saveUserProfilePicture(byte[] imageBytes, User user) {


        File folder = new File(FOLDER_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }


        String imageFileName = user.getPhoneNumber() + "_profile_pic.jpg";
        File imageFile = new File(folder, imageFileName);

        // Save the byte array as an image file
        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            fos.write(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // Return the saved image file path
        return FOLDER_PATH + imageFileName;
    }

}
