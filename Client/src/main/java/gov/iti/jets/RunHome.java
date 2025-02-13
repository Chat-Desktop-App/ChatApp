package gov.iti.jets;

import gov.iti.jets.controller.HomeServiceController;
import gov.iti.jets.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RunHome extends Application {
    public static Stage primaryStage;
    public static Scene loadingScene;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        System.out.println("client running.......");
        FXMLLoader loader = new FXMLLoader(RunHome.class.getResource("fxml/home.fxml"));
        FXMLLoader loader2 = new FXMLLoader(RunHome.class.getResource("fxml/loading.fxml"));
        HomeServiceController.setUser(new User("0987654321", "John", "Doe"));

        Scene scene = null;
        try {
            scene = new Scene(loader.load());

            loadingScene = new Scene(loader2.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setTitle("Log in");
        stage.setMinHeight(550);
        stage.setMinWidth(1050);
        stage.setScene(scene);
        stage.show();

    }
}