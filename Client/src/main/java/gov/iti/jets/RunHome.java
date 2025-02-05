package gov.iti.jets;

import gov.iti.jets.controller.RegisterServiceController;
import gov.iti.jets.services.interfaces.LoadHome;
import gov.iti.jets.view.SignUpController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RunHome extends Application {
    @Override
    public void start(Stage stage)  {

        LoadHome loadHome = RMIConnector.getRmiConnector().getLoadHome();
        System.out.println("client running.......");
        FXMLLoader loader = new FXMLLoader(RunHome.class.getResource("fxml/home.fxml"));

        Scene scene = null;
        try {
            scene = new Scene(loader.load());
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