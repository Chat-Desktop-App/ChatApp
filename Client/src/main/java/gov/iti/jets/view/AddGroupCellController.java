package gov.iti.jets.view;

import gov.iti.jets.model.GroupMemberDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class AddGroupCellController implements Initializable {

    @FXML
    private ImageView friendIcon;

    @FXML
    private Label name;

    @FXML
    private Label phoneNum;

    @FXML
    private CheckBox add;

    private GroupMemberDTO user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void handleAdd(ActionEvent event) {

    }

    public boolean isSelected() {
        return add.isSelected();
    }

    public GroupMemberDTO getUser() {
        return user;
    }

    public void setUser(GroupMemberDTO user) {
        this.user = user;
        name.setText(user.firstName() + " " + user.secondName());
        phoneNum.setText(user.phoneNumber());
        if (user.profile() != null && user.profile().length > 0) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(user.profile());
            Image image = new Image(inputStream);
            friendIcon.setImage(image);
        }
    }
}
