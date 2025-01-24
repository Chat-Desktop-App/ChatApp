package gov.iti.jets.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        scene.getStylesheets().add(Objects.requireNonNull(HelloApplication.class.getResource("styles/login.css")).toExternalForm());
        stage.setTitle("Log in");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}