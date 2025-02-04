package gov.iti.jets.view;

import gov.iti.jets.database.dao.UserDao;
import gov.iti.jets.database.dao.UserDaoImpl;
import gov.iti.jets.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class UserTableController {
    @FXML
    private TableView userTable;
    @FXML
    private TableColumn phoneNumberCol;
    @FXML
    private TableColumn fnameCol;
    @FXML
    private TableColumn lnameCol;
    @FXML
    private TableColumn emailCol;
    @FXML
    private TableColumn genderCol;
    @FXML
    private TableColumn dobCol;
    @FXML
    private TableColumn statusCol;
    @FXML
    private TableColumn lastSeenCol;

    private final UserDao userDao = new UserDaoImpl();
    private ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        lastSeenCol.setCellValueFactory(new PropertyValueFactory<>("lastSeen"));

        loadUsers();
    }
    private void loadUsers() {
        try {
            List<User> users = userDao.getUsers();
            userList.setAll(users);
            userTable.setItems(userList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
