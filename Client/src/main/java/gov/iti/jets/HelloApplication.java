package gov.iti.jets;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
<<<<<<< HEAD

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/home.fxml"));
=======
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/home.fxml"));

>>>>>>> 40a81b4e0bbe520b445ca6241cf9672fbc4a6e3f
        Scene scene = new Scene(fxmlLoader.load());

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