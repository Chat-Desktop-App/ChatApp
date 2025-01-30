package gov.iti.jets.database.dao;



import gov.iti.jets.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public int addUser(User user) throws SQLException;
    //public void delete(String phoneNumber) throws SQLException;
    public User getUser(String phoneNumber) throws SQLException;
    public List<User> getUsers() throws SQLException;
    public int update(User user) throws SQLException;
}
