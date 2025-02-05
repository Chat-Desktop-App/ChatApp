package gov.iti.jets.database.dao;



import gov.iti.jets.database.DataBaseConnection;
import gov.iti.jets.model.Gender;
import gov.iti.jets.model.Status;
import gov.iti.jets.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                        INSERT INTO users (phone_number, fname, lname, email, password, gender, country, dob, bio, status, num_entries, Last_seen, is_admin, picture) 
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?)
                        """;

        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, user.getPhoneNumber());
        ps.setString(2, user.getFname());
        ps.setString(3, user.getLname());
        ps.setString(4, user.getEmail());
        ps.setString(5, hashPass(user.getPasswordHashed()));
        ps.setString(6, user.getGender().toString()); // Assuming gender is an enum
        ps.setString(7, user.getCountry());
        ps.setDate(8, java.sql.Date.valueOf(user.getDob())); // Convert LocalDate to java.sql.Date
        ps.setString(9, user.getBio());
        ps.setString(10, user.getStatus().toString()); // Assuming status is an enum
        ps.setLong(11, user.getNumberEnteries());
        ps.setTimestamp(12, java.sql.Timestamp.valueOf(user.getLastSeen())); // Convert LocalDateTime to java.sql.Timestamp
        ps.setBoolean(13, user.getAdmin());
        ps.setString(14, user.getPicturePath());
        int n = ps.executeUpdate();
        return n;
    }

    @Override
    public User getUser(String phoneNumber) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """ 
                        SELECT * FROM users WHERE phone_number = ?
                        """;
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, phoneNumber);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            User user = new User();
            user.setPhoneNumber(rs.getNString(1));
            user.setFname(rs.getNString(2));
            user.setLname(rs.getNString(3));
            user.setEmail(rs.getString(4));
            user.setPasswordHashed(rs.getString(6));
            user.setGender(Gender.valueOf(rs.getString(7)));
            user.setCountry(rs.getString(8));
            user.setDob(rs.getDate(9).toLocalDate());
            user.setBio(rs.getString(10));
            user.setStatus(Status.valueOf(rs.getString(11)));
            user.setNumberEnteries(rs.getLong(12));
            user.setLastSeen(rs.getTimestamp(13).toLocalDateTime());
            user.setAdmin(rs.getBoolean(14));
            return user;
        }else{
        return null;
        }
    }

    @Override
    public List<User> getUsers() throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = """ 
                        SELECT * FROM users
                        """;
        PreparedStatement ps
                = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<User> users = new ArrayList<>();
        while(rs.next()){
            User user = new User();
            user.setPhoneNumber(rs.getNString(1));
            user.setFname(rs.getNString(2));
            user.setLname(rs.getNString(3));
            user.setEmail(rs.getString(4));
            user.setPasswordHashed(rs.getString(6));
            user.setGender(Gender.valueOf(rs.getString(7)));
            user.setCountry(rs.getString(8));
            user.setDob(rs.getDate(9).toLocalDate());
            user.setBio(rs.getString(10));
            user.setStatus(Status.valueOf(rs.getString(11)));
            user.setNumberEnteries(rs.getLong(12));
            user.setLastSeen(rs.getTimestamp(13).toLocalDateTime());
            user.setAdmin(rs.getBoolean(14));
            users.add(user);
        }
        return users;
    }

    @Override
    public int update(User user) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        StringBuilder query = new StringBuilder("UPDATE users SET ");
        List<Object> params = new ArrayList<>(); // to be passed to the prepared statement

        if(user.getFname() != null) {
            query.append("fname = ?, ");
            params.add(user.getFname());
        }
        if (user.getLname() != null) {
            query.append("lname = ?, ");
            params.add(user.getLname());
        }
        if (user.getEmail() != null) {
            query.append("email = ?, ");
            params.add(user.getEmail());
        }
        if (user.getPasswordHashed() != null) {
            query.append("password = ?, ");
            params.add(hashPass(user.getPasswordHashed()));
        }
        if (user.getGender() != null) {
            query.append("gender = ?, ");
            params.add(user.getGender().toString());
        }
        if (user.getCountry() != null) {
            query.append("country = ?, ");
            params.add(user.getCountry());
        }
        if (user.getDob() != null) {
            query.append("dob = ?, ");
            params.add(java.sql.Date.valueOf(user.getDob()));
        }
        if (user.getBio() != null) {
            query.append("bio = ?, ");
            params.add(user.getBio());
        }
        if (user.getStatus() != null) {
            query.append("status = ?, ");
            params.add(user.getStatus().toString());
        }
        if (user.getNumberEnteries() != null) {
            query.append("num_entries = ?, ");
            params.add(user.getNumberEnteries());
        }
        if (user.getLastSeen() != null) {
            query.append("Last_seen = ?, ");
            params.add(java.sql.Timestamp.valueOf(user.getLastSeen()));
        }
        if (user.getAdmin() != null) {
            query.append("is_admin = ?, ");
            params.add(user.getAdmin());
        }
        if (user.getPicture() != null) {
            query.append("picture = ?, ");
            params.add(user.getPicture());
        }
        if (params.isEmpty()) {
            throw new SQLException("No fields to update.");
        }

        // Remove last comma and space
        query.setLength(query.length() - 2);

        query.append(" WHERE phone_number = ?");
        params.add(user.getPhoneNumber());
        System.out.println("Generated Query: " + query.toString());

        PreparedStatement ps = con.prepareStatement(query.toString());

        for (int i = 0; i < params.size(); i++) {
            if (params.get(i) instanceof String) {
                ps.setString(i + 1, (String) params.get(i));
            } else if (params.get(i) instanceof Integer) {
                ps.setInt(i + 1, (Integer) params.get(i));
            } else if (params.get(i) instanceof Long) {
                ps.setLong(i + 1, (Long) params.get(i));
            } else if (params.get(i) instanceof Boolean) {
                ps.setBoolean(i + 1, (Boolean) params.get(i));
            } else if (params.get(i) instanceof java.sql.Date) {
                ps.setDate(i + 1, (java.sql.Date) params.get(i));
            } else if (params.get(i) instanceof java.sql.Timestamp) {
                ps.setTimestamp(i + 1, (java.sql.Timestamp) params.get(i));
            } else if (params.get(i) instanceof byte[]) {
                ps.setBytes(i + 1, (byte[]) params.get(i));
            }
        }
        return ps.executeUpdate();
        /*String query =  """
               UPDATE users 
               SET fname = ?, 
                   lname = ?, 
                   email = ?, 
                   password = ?, 
                   gender = ?, 
                   country = ?, 
                   dob = ?, 
                   bio = ?, 
                   status = ?, 
                   num_entries = ?, 
                   Last_seen = ?
               WHERE phone_number = ? 
               """;
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, user.getPhoneNumber());
        ps.setString(2, user.getFname());
        ps.setString(3, user.getLname());
        ps.setString(4, user.getEmail());
        ps.setString(5, hashPass(user.getPasswordHashed()));
        ps.setString(6, user.getGender().toString()); // Assuming gender is an enum
        ps.setString(7, user.getCountry());
        ps.setDate(8, java.sql.Date.valueOf(user.getDob())); // Convert LocalDate to java.sql.Date
        ps.setString(9, user.getBio());
        ps.setString(10, user.getStatus().toString()); // Assuming status is an enum
        ps.setLong(11, user.getNumberEnteries());
        ps.setTimestamp(12, java.sql.Timestamp.valueOf(user.getLastSeen())); // Convert LocalDateTime to java.sql.Timestamp
        ps.setString(13, user.getPhoneNumber());
        int n = ps.executeUpdate();
        return n;*/
    }

    public int updatePicture(String phoneNumber,String picturePath) throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = "UPDATE users SET picture = ? WHERE phone_number = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, picturePath);
        ps.setString(2, phoneNumber);
        int n = ps.executeUpdate();
        return n;

    }

    @Override
    public Map<String, Integer> getUserStatus() throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        String query = "SELECT status, COUNT(*) FROM users GROUP BY status";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        Map<String, Integer> statusCounts = new HashMap<>();
        while (resultSet.next()) {
            statusCounts.put(resultSet.getString(1), resultSet.getInt(2));
        }
        return statusCounts;
    }

    @Override
    public Map<String, Integer> getUserGender() throws SQLException {
        Connection connection = dataBaseConnection.getConnection();
        String query = "SELECT gender, COUNT(*) FROM users GROUP BY gender";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        Map<String, Integer> genderCounts = new HashMap<>();
        while(resultSet.next()) {
            genderCounts.put(resultSet.getString(1), resultSet.getInt(2));
        }
        return genderCounts;
    }

    @Override
    public Map<String, Integer> getUserCountry() throws SQLException {
        Connection con = dataBaseConnection.getConnection();
        String query = "SELECT country, COUNT(*) FROM users GROUP BY country";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        Map<String, Integer> countryCounts = new HashMap<>();
        while (rs.next()) {
            countryCounts.put(rs.getString(1), rs.getInt(2));
        }
        return countryCounts;
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
    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();

        try {
         /*   // Create a test user
            User testUser = new User();
            testUser.setPhoneNumber("+1234567890");
            testUser.setFname("John");
            testUser.setLname("Doe");
            testUser.setEmail("john.doe@example.com");
            testUser.setPasswordHashed("testpassword");
            testUser.setGender(Gender.MALE);
            testUser.setCountry("USA");
            testUser.setDob(LocalDate.of(1990, 1, 1));
            testUser.setBio("Test user biography");
            //testUser.setStatus(Status.ONLINE); default available
            //testUser.setNumberEnteries(0L); default 1L
            //testUser.setLastSeen(LocalDateTime.now()); default
            //testUser.setAdmin(false); default false


            // Test addUser method

            int addResult = userDao.addUser(testUser);
            System.out.println("User Added: " + (addResult > 0));

            // Test getUser method
            User retrievedUser = userDao.getUser("+1234567890");
            System.out.println("Retrieved User: " + (retrievedUser != null));
            System.out.println("User Name: " + retrievedUser.getFname() + " " + retrievedUser.getLname());

            // Test getUsers method
            List<User> users = userDao.getUsers();
            System.out.println("Total Users: " + users.size());

            // Test update method
            retrievedUser.setFname("Jane");
            int updateResult = userDao.update(retrievedUser);
            System.out.println("User Updated: " + (updateResult > 0));
        */

            User newUser = new User();
            newUser.setPhoneNumber("+1122");
            newUser.setFname("Alice");
            newUser.setLname("Johnson");
            newUser.setEmail("alice2.joon@example.net");
            newUser.setPasswordHashed("mypassword456");
            newUser.setGender(Gender.FEMALE);
            newUser.setCountry("UK");
            newUser.setDob(LocalDate.of(1995, 3, 25));
            newUser.setBio("Tech enthusiast and avid traveler, exploring the world one line of code at a time.");

            userDao.addUser(newUser);

            User newUser2 = new User();
            newUser2.setPhoneNumber("+1125867");
            newUser2.setFname("Alice");
            newUser2.setLname("Johnson");
            newUser2.setEmail("alice.nn@example.net");
            newUser2.setPasswordHashed("mypassword456");
            newUser2.setGender(Gender.MALE);
            newUser2.setCountry("USA");
            newUser2.setDob(LocalDate.of(1995, 3, 25));
            newUser2.setBio("Tech enthusiast and avid traveler, exploring the world one line of code at a time.");
            newUser2.setStatus(Status.BUSY);

            userDao.addUser(newUser2);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
