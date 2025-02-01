package gov.iti.jets;

import gov.iti.jets.controller.RegisterServiceController;
import gov.iti.jets.view.SignUpController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SignUpController view = fxmlLoader.getController();
        RegisterServiceController controller = new RegisterServiceController(view);
        view.setRegisterController(controller);
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