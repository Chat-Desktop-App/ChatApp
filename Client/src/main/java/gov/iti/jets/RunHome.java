package gov.iti.jets;

import gov.iti.jets.controller.RegisterServiceController;
import gov.iti.jets.services.interfaces.LoadHome;
import gov.iti.jets.view.SignUpController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RunHome extends Application {
    public static Stage primaryStage;
    public static  Scene loadingScene ;
    @Override
    public void start(Stage stage)  {
        primaryStage= stage;
        System.out.println("client running.......");
        FXMLLoader loader = new FXMLLoader(RunHome.class.getResource("fxml/home.fxml"));
        FXMLLoader loader2 = new FXMLLoader(RunHome.class.getResource("fxml/loading.fxml"));

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

    public static void main(String[] args) {
        launch();
    }
}