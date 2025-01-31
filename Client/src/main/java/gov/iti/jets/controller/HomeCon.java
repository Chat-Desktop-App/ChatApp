package gov.iti.jets.controller;

import gov.iti.jets.model.ContactUser;
import gov.iti.jets.services.interfaces.LoadHome;
import gov.iti.jets.view.AllChatsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.List;

public class HomeCon {
    LoadHome loadHome;

    public HomeCon(LoadHome loadHome) {
        this.loadHome = loadHome;
    }

    public ObservableList<Node> loadFXMLIntoList(List<ContactUser> myContact) {
        String fxmlPath = "/gov/iti/jets/fxml/allChats.fxml" ;
        ObservableList<Node> list = FXCollections.observableArrayList();
        for (ContactUser contactUser : myContact) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                Node node = loader.load();
                AllChatsController controller = loader.getController();
                controller.setUser(contactUser);
                list.add(node);
            } catch (IOException e) {
                System.out.println("Error when loading " + fxmlPath + ": " + e.getMessage());
            }
        }
        return list;
    }
}
