package gov.iti.jets.view;

import gov.iti.jets.database.dao.UserDao;
import gov.iti.jets.database.dao.UserDaoImpl;
import gov.iti.jets.model.Gender;
import gov.iti.jets.model.Status;
import gov.iti.jets.model.User;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.DefaultStringConverter;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.DatePicker;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserTableController {
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> phoneNumberCol;
    @FXML
    private TableColumn<User, String> fnameCol;
    @FXML
    private TableColumn<User, String> lnameCol;
    @FXML
    private TableColumn<User, String> emailCol;
    @FXML
    private TableColumn<User, Gender> genderCol;
    @FXML
    private TableColumn<User, String> dobCol;
    @FXML
    private TableColumn<User, Status> statusCol;
    @FXML
    private TableColumn<User, String> lastSeenCol;

    private final UserDao userDao = new UserDaoImpl();
    private ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        userTable.setEditable(true);
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        dobCol.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getDob().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        lastSeenCol.setCellValueFactory(cellData ->
                Bindings.createStringBinding(() ->
                        cellData.getValue().getLastSeen()
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        //lastSeenCol.setCellValueFactory(new PropertyValueFactory<>("lastSeen"));

        makeColumnEditable(fnameCol, "fname");
        makeColumnEditable(lnameCol, "lname");
        makeColumnEditable(emailCol, "email");

        // make status editable using comboBox
        statusCol.setCellFactory(ComboBoxTableCell.forTableColumn(Status.values()));
        statusCol.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setStatus(event.getNewValue());
            updateUserInDatabase(user);
        });
        // make gender editable
        genderCol.setCellFactory(ComboBoxTableCell.forTableColumn(Gender.values()));
        genderCol.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setGender(event.getNewValue());
            updateUserInDatabase(user);
        });

        dobCol.setCellFactory(column -> new TableCell<>() {
            private final DatePicker datePicker = new DatePicker();

            {
                datePicker.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    user.setDob(datePicker.getValue()); // Update User object
                    updateUserInDatabase(user); // Save to DB
                    commitEdit(datePicker.getValue().toString()); // Commit edit
                });
            }

            @Override
            protected void updateItem(String dob, boolean empty) {
                super.updateItem(dob, empty);
                if (empty || dob == null) {
                    setGraphic(null);
                } else {
                    datePicker.setValue(LocalDate.parse(dob));
                    setGraphic(datePicker);
                }
            }
        });

        loadUsers();
    }

    private void makeColumnEditable(TableColumn<User, String> column, String fieldName) {
        column.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        column.setOnEditCommit(event -> {
            User user = event.getRowValue();
            String newValue = event.getNewValue();

            if (newValue != null && !newValue.trim().isEmpty()) {
                switch (fieldName) {
                    case "fname" -> user.setFname(newValue);
                    case "lname" -> user.setLname(newValue);
                    case "email" -> user.setEmail(newValue);
                    case "bio" -> user.setBio(newValue);
                }
                updateUserInDatabase(user);
            }
        });
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
    private void updateUserInDatabase(User user) {
        try{
            userDao.update(user);
            userTable.refresh();
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
    }
}
