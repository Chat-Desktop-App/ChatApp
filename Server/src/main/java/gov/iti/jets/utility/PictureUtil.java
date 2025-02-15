package gov.iti.jets.utility;

import gov.iti.jets.model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class PictureUtil{
    private static final String FOLDER_PATH = "pictures" + File.separator;

    public static String saveUserProfilePicture(byte[] imageBytes, User user) {
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

    public static String saveGroupProfilePicture(byte[] imageBytes, String groupID) {
        File folder = new File(FOLDER_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String imageFileName = groupID + "_group_pic.jpg";
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


    // get user picture
    public static byte[] getPicture(String picturePath){
        if (picturePath == null) {
            return null;
        }

        File file = new File(picturePath);
        if (!file.exists()) {
            return null;
        }
        try (FileInputStream fis = new FileInputStream(file);
             FileChannel fileChannel = fis.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(buffer);
            return buffer.array();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }



}
