package gov.iti.jets;

import gov.iti.jets.controller.LogInServiceController;
import gov.iti.jets.controller.RegisterServiceController;
import gov.iti.jets.model.UserSession;
import gov.iti.jets.services.interfaces.LoadHome;
import gov.iti.jets.services.interfaces.Login;
import gov.iti.jets.view.HomeController;
import gov.iti.jets.view.LoginController;
import gov.iti.jets.view.SignUpController;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientApp extends Application {

    private static final String SESSION_FILE = "session.xml";
    private static final String LOGIN_FXML = "fxml/Login.fxml";
    private static final String HOME_FXML = "fxml/home.fxml";
    private Login login =  RMIConnector.getRmiConnector().getLoginService();


    @Override
    public void start(Stage stage)  {

        System.out.println("client running.......");
        Parent root = loadView();
        Scene scene = new Scene(root);

        stage.setTitle("Orca");
        stage.setMinHeight(602);
        stage.setMinWidth(1062);
        stage.setScene(scene);
        stage.show();


    }

    private Parent loadView() {

        //check session to decide which scene to load
        Path path = Paths.get(SESSION_FILE);
        FXMLLoader loader = null;

        if(Files.exists(path)){
            // unmarshall it to a UserSession
            try {
                JAXBContext context = JAXBContext.newInstance(UserSession.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                UserSession userSession = (UserSession) unmarshaller.unmarshal(new FileReader(SESSION_FILE));
                // if session is valid go to home page
                // else load login page with user phone number
                if(login.validateSession(userSession)){
                    login.skipLogin(userSession);
                    loader = new FXMLLoader(ClientApp.class.getResource(HOME_FXML));
                    return loader.load();
                    // set home page with phone number
                }else{
                    loader = new FXMLLoader(ClientApp.class.getResource(LOGIN_FXML));
                    Parent root = loader.load();
                    LoginController view =loader.getController();
                    view.setPhoneNumber(userSession.getPhoneNumber());
                    view.setRemmberMe(true);
                    return  root;

                }




            } catch (JAXBException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (RemoteException e) {
                login = RMIConnector.rmiReconnector().getLoginService();
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }else{
            // load log in page normally
            loader = new FXMLLoader(ClientApp.class.getResource(LOGIN_FXML));
            try {
                return loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void stop(){
        // exit
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(UserSession.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UserSession userSession = (UserSession) unmarshaller.unmarshal(new FileReader("session.xml"));
            login.exit(userSession.getPhoneNumber());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            login = RMIConnector.rmiReconnector().getLoginService();
            throw new RuntimeException(e);
        }


        Platform.exit();
        System.exit(0);

    }

    public static void main(String[] args) {
        launch();
    }
}