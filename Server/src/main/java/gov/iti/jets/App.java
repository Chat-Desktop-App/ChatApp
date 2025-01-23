package gov.iti.jets;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // stage.setTitle("Browsar");

        int width = 640,height = 480;

        FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("/fxml/orca.fxml"));
		GridPane root = registerLoader.load();
        OrcaController rootController = registerLoader.getController();


        Scene serverScene = new Scene(root, width, height);
        // stage.setResizable(false);
        stage.setMinWidth(400);
        stage.setMinHeight(600);
        stage.setScene(serverScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}