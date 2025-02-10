package gov.iti.jets.view;

import gov.iti.jets.controller.MessageServiceController;
import gov.iti.jets.controller.Session;
import gov.iti.jets.model.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;

public class ChatAreaController {

    private ContactUser contactUser;
    private Group group;
    private boolean isContact = false;
    private static Message message = new Message();
    ObservableList<HBox> messagesList;


    private VBox chatFormattingPanel;

    @FXML
    private HBox AttachmentHBOX;

    @FXML
    private AnchorPane chatAnchorPane;

    @FXML
    private ToggleButton textEdit;

    @FXML
    private Button attachment;

    @FXML
    private Button block;

    @FXML
    private Button call;

    @FXML
    private Button camera;

    @FXML
    private ListView<HBox> chatListView;

    @FXML
    private Button document;

    @FXML
    private Button emoji;

    @FXML
    private ImageView friendIcon;

    @FXML
    private Label friendName;

    @FXML
    private Button image;



    @FXML
    private TextArea textArea;

    @FXML
    private Button mic;

    @FXML
    private Button music;

    @FXML
    private Button send;

    @FXML
    private Button video;

    @FXML
    public void initialize() {
        // Hide the HBox when the application starts
        AttachmentHBOX.setVisible(false);

        // Bind the ListView's width and height to the AnchorPane
        chatListView.maxWidthProperty().bind(chatAnchorPane.widthProperty());
        chatListView.maxHeightProperty().bind(chatAnchorPane.heightProperty());

        // Set the cell factory for the ListView to bind cell width dynamically
        chatListView.setCellFactory(lv -> new ListCell<HBox>() {
            @Override
            protected void updateItem(HBox item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(item);
                    ((VBox) item.getChildren().get(item.getChildren().size() - 1)).maxWidthProperty().bind(chatListView.widthProperty().multiply(0.75));
                }
                setStyle("-fx-background-color: transparent;");
            }
        });
        chatFormattingPanel = new MessageFormat().createChatFormattingPanel();

        chatAnchorPane.getChildren().add(chatFormattingPanel);
        AnchorPane.setBottomAnchor(chatFormattingPanel, 0.0);
        AnchorPane.setLeftAnchor(chatFormattingPanel,0.0);
        chatFormattingPanel.setVisible(false);
        textArea.setWrapText(true);
    }

    @FXML
    void BlockFriendAction(ActionEvent event) {

    }

    @FXML
    void callAction(ActionEvent event) {

    }

    @FXML
    void camerAction(ActionEvent event) {

    }

    @FXML
    void handleAttachmentButton(ActionEvent event) {

       // AttachmentHBOX.setVisible(true);
        AttachmentHBOX.setVisible(!AttachmentHBOX.isVisible());

    }

    @FXML
    void handleShareDoc(ActionEvent event) {

    }

    @FXML
    void handleShareImage(ActionEvent event) {

    }

    @FXML
    void handleshareMusic(ActionEvent event) {

    }

    @FXML
    void micAction(ActionEvent event) {

    }

    @FXML
    void recordVoice(MouseEvent event) {

    }

    @FXML
    void sendAction(ActionEvent event) {
        sendMessage();
    }

    @FXML
    void sendEmoji(MouseEvent event) {

    }

    @FXML
    void sendEmojiAction(ActionEvent event) {

    }

    @FXML
    void textAreaKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (event.isShiftDown()) {
                textArea.insertText(textArea.getCaretPosition(),"\n");
            } else {
                if(!textArea.getText().equals("")){
                   sendMessage();
                }
            }
            event.consume();
        }
    }

    @FXML
    void textEditHandle(ActionEvent actionEvent) {
        if(textEdit.isSelected()){
            chatFormattingPanel.setVisible(true);        }
        else {
            chatFormattingPanel.setVisible(false);
        }
    }

    @FXML
    void sendVideo(MouseEvent event) {

    }

    @FXML
    void showUserInfo(MouseEvent event) {

    }

    @FXML
    void videoCall(MouseEvent event) {

    }

    @FXML
    void viodeoAction(ActionEvent event) {

    }

    @FXML
    void voiceCall(MouseEvent event) {

    }

    public void setChat(Chatable chatable) {
        friendName.setText(chatable.getName());
        byte [] pic = chatable.getPicture();
        if(pic != null){
            friendIcon.setImage(new Image(new ByteArrayInputStream(pic)));
        }
        if(chatable instanceof ContactUser m){
            contactUser = m;
            isContact = true;
        }else {
            group = (Group) chatable;
            isContact = false;
        }

        messagesList = loadMessages();
        chatListView.setItems(messagesList);
        if (messagesList != null && !messagesList.isEmpty()) {
            Platform.runLater(() -> {
                chatListView.scrollTo(messagesList.size() - 1);
            });
        }
    }


    private ObservableList<HBox> loadMessages() {
        if (isContact){
            return MessageServiceController.getDirectMessages(contactUser.getPhoneNumber());
        }else {
           return MessageServiceController.getGroupMessages(group.getGroupId());

        }
    }

    private void sendMessage(){
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));
        message.setContent(textArea.getText().trim());
        HBox hBox ;
        if(isContact){
            message.setReceiverId(contactUser.getPhoneNumber());
            message.setRecipient(Recipient.PRIVATE);
            hBox = MessageServiceController.sendMessage(message);
        }else {
            message.setGroupId(group.getGroupId());
            message.setRecipient(Recipient.GROUP);
            GroupMessage groupMessage = new GroupMessage(message,
                    (Session.user.getFname() + " "+ Session.user.getLname()),Session.user.getPicture());
            hBox = MessageServiceController.sendMessage(groupMessage);
        }
        textArea.clear();
        if (hBox != null ){
            Platform.runLater(() -> {
                messagesList.add(hBox);
                chatListView.scrollTo(messagesList.size() - 1);
            });
        }
    }
    public void receivedMessage(HBox hBox){
        if (hBox != null ){
            Platform.runLater(() -> {
                messagesList.add(hBox);
                chatListView.scrollTo(messagesList.size() - 1);
            });
        }

    }



    public Group getGroup() {
        return group;
    }

    public ContactUser getContactUser() {
        return contactUser;
    }

    public boolean isContact() {
        return isContact;
    }

    private TextArea getTextArea() {
        return textArea;
    }

    class MessageFormat{

        public VBox createChatFormattingPanel() {
            ComboBox<String> fontComboBox = new ComboBox<>();
            fontComboBox.setPrefWidth(125);
            fontComboBox.getItems().addAll(Font.getFamilies());
            fontComboBox.setValue(Font.getDefault().getName());// Default font
            fontComboBox.setOnAction(e ->{
                message.setFontStyle(fontComboBox.getValue());
                setTextAreaFormat();
            });

            ComboBox<Integer> fontSizeComboBox = new ComboBox<>();
            for (int i = 12; i <= 30; i += 2) {
                fontSizeComboBox.getItems().add(i);
            }
            fontSizeComboBox.setValue(14); // Default size
            fontSizeComboBox.setOnAction(e ->{
                message.setFontSize(fontSizeComboBox.getValue());
                setTextAreaFormat();        }
            );

            ColorPicker textColorPicker = new ColorPicker(Color.BLACK);
            textColorPicker.setOnAction(e ->{
                message.setFontColour(toRgbString(textColorPicker.getValue()));
                setTextAreaFormat();
            });

            ColorPicker bgColorPicker = new ColorPicker(Color.valueOf("#3d7eb6"));
            bgColorPicker.setOnAction(e ->{
                message.setTextBackGroundColour(toRgbString(bgColorPicker.getValue()));
                setTextAreaFormat();
            });

            ToggleButton boldButton = new ToggleButton("B");
            boldButton.setOnAction(e -> {
                message.setBold(boldButton.isSelected());
                setTextAreaFormat();
            });

            ToggleButton italicButton = new ToggleButton("I");
            italicButton.setOnAction(e -> {
                message.setItalic(italicButton.isSelected());
                setTextAreaFormat();
            });

            ToggleButton underlineButton = new ToggleButton("U");
            underlineButton.setOnAction(e ->{
                message.setUnderLine(underlineButton.isSelected());
                setTextAreaFormat();
            });


            HBox hBox = new HBox(fontSizeComboBox,boldButton, italicButton, underlineButton);
            VBox vBox = new VBox(fontComboBox,  textColorPicker, bgColorPicker ,hBox);
            hBox.setAlignment(Pos.CENTER);

            vBox.setStyle("-fx-padding: 15px;");
            return vBox;
        }

        private String toRgbString(Color color) {
            return String.format("rgb(%d, %d, %d)",
                    (int) (color.getRed() * 255),
                    (int) (color.getGreen() * 255),
                    (int) (color.getBlue() * 255)
            );
        }

        private void setTextAreaFormat(){
            StringBuilder builder = new StringBuilder();
            if (message.isBold()) { builder.append("-fx-font-weight: bold;\n");}
            if (message.isItalic()){builder.append("-fx-font-style: italic;\n");}
            if (message.isUnderLine()){builder.append("-fx-underline: " ).append(message.isUnderLine()).append(";\n");}
            if (message.getFontSize() != 0){builder.append("-fx-font-size: ").append(message.getFontSize()).append("px;\n");}
            if (message.getFontStyle() != null){builder.append("-fx-font-family: '").append(message.getFontStyle()).append("';\n");}
            if (message.getFontColour() != null){builder.append("-fx-text-fill: ").append(message.getFontColour()).append(";\n");}
            if (!message.getTextBackGroundColour().equals("#3d7eb6")){builder.append("-fx-control-inner-background: ")
                    .append(message.getTextBackGroundColour()).append(";\n");}
            textArea.setStyle(builder.toString());
        }

    }
}

