package gov.iti.jets.view;

import java.io.IOException;

import gov.iti.jets.view.SignInController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // stage.setTitle("Browser");

        int width = 640,height = 480;

        FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("/fxml/server-homePage.fxml"));
		Pane root = registerLoader.load();

        Scene serverScene = new Scene(root, width, height);
        // stage.setResizable(false);
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setScene(serverScene);
        stage.setOnCloseRequest(event -> {
            // Optionally handle cleanup before exit
            Platform.exit();
            System.exit(0);  // Ensure the JVM exits
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}