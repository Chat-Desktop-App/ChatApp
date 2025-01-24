package gov.iti.jets.client.database.dao;

import gov.iti.jets.client.database.DataBaseConnection;
import gov.iti.jets.client.model.User;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.security.MessageDigest;

public class UserDaoImpl implements UserDao{
    static DataBaseConnection dataBaseConnection;

    static {
        try {
            dataBaseConnection = DataBaseConnection.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int addUser(User user) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """ 
                        INSERT INTO users (phone_number, name, email, password, gender, country, dob, bio, status, num_entries, Last_seen, is_admin) 
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """;

        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, user.getPhoneNumber());
        ps.setString(2, user.getFname()+" "+user.getLname());
        ps.setString(3, user.getEmail());
        ps.setString(4, hashPass(user.getPasswordHashed()));
        ps.setString(5, user.getGender().toString()); // Assuming gender is an enum
        ps.setString(6, user.getCountry());
        ps.setDate(7, java.sql.Date.valueOf(user.getDob())); // Convert LocalDate to java.sql.Date
        ps.setString(8, user.getBio());
        ps.setString(9, user.getStatus().toString()); // Assuming status is an enum
        ps.setLong(10, user.getNumberEnteries());
        ps.setTimestamp(11, java.sql.Timestamp.valueOf(user.getLastSeen())); // Convert LocalDateTime to java.sql.Timestamp
        ps.setBoolean(12, user.getAdmin());

        int n = ps.executeUpdate();
        return n;
    }

    @Override
    public User getUser(String phoneNumber) throws SQLException {
        return null;
    }

    @Override
    public List<User> getUsers() throws SQLException {
        return List.of();
    }

    @Override
    public int update(User user) throws SQLException {
        return 1;
    }

    public String hashPass(String pass){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        // Perform the hashing
        byte[] hashBytes = digest.digest(pass.getBytes(StandardCharsets.UTF_8));

        // Convert the byte array to a hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();

    }
}
