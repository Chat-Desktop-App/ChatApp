package gov.iti.jets.client;

import gov.iti.jets.client.controller.LoginControllerService;
import gov.iti.jets.client.view.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LoginController view = fxmlLoader.getController();
        LoginControllerService controller = new LoginControllerService(view);
        view.setController(controller);
        //scene.getStylesheets().add(Objects.requireNonNull(HelloApplication.class.getResource("styles/login.css")).toExternalForm());
        stage.setTitle("Log in");
        stage.setMinHeight(550);
        stage.setMinWidth(1050);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}