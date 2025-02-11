package gov.iti.jets.database.dao;



import gov.iti.jets.model.Status;
import gov.iti.jets.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserDao {
    public int addUser(User user) throws SQLException;
    //public void delete(String phoneNumber) throws SQLException;
    public User getUser(String phoneNumber) throws SQLException;
    public List<User> getUsers() throws SQLException;
    public int update(User user) throws SQLException;
    public int updatePicture(String phoneNumber,String picturePath) throws SQLException;
    public Map<String, Integer> getUserStatus() throws SQLException;
    public Map<String, Integer> getUserGender() throws SQLException;
    public Map<String, Integer> getUserCountry() throws SQLException;
    String hashPass(String pass);
    public int updateEmail(String phoneNumber,String email) throws SQLException;
    public int updateBio(String phoneNumber,String Bio) throws SQLException ;
    public int updateStatus(String phoneNumber, Status status) throws SQLException ;
    public int updateFName(String phoneNumber,String FName) throws SQLException;
    public int updateLName(String phoneNumber,String LName) throws SQLException;
}
