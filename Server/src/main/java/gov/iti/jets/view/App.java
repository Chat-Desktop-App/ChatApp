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



        FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("/fxml/server-homePage.fxml"));
		Pane root = registerLoader.load();

        Scene serverScene = new Scene(root);
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setScene(serverScene);
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);  // Ensure the JVM exits
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}