package gov.iti.jets;

import gov.iti.jets.controller.HomeServiceController;
import gov.iti.jets.controller.Session;
import gov.iti.jets.model.LoginStatus;
import gov.iti.jets.model.User;
import gov.iti.jets.services.interfaces.Login;
import gov.iti.jets.view.LoginController;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import javafx.application.Application;
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
import java.rmi.RemoteException;

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
                JAXBContext context = JAXBContext.newInstance(LoginStatus.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                LoginStatus loginStatus = (LoginStatus) unmarshaller.unmarshal(new FileReader(SESSION_FILE));
                // if session is valid go to home page
                // else load login page with user phone number
                if(login.validateSession(loginStatus)){
                    login.skipLogin(loginStatus);
                    loader = new FXMLLoader(ClientApp.class.getResource(HOME_FXML));
                    HomeServiceController.setUser(login.getUser(loginStatus.getPhoneNumber()));
                    return loader.load();
                    // set home page with phone numb
                }else{
                    loader = new FXMLLoader(ClientApp.class.getResource(LOGIN_FXML));
                    Parent root = loader.load();
                    LoginController view =loader.getController();
                    view.setPhoneNumber(loginStatus.getPhoneNumber());
                    view.setRemmberMe(true);
                    return  root;

                }




            } catch (JAXBException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (RemoteException e) {
                login = RMIConnector.rmiReconnect().getLoginService();
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
            context = JAXBContext.newInstance(LoginStatus.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            LoginStatus loginStatus = (LoginStatus) unmarshaller.unmarshal(new FileReader("session.xml"));
            login.exit(loginStatus.getPhoneNumber());
            RMIConnector.getRmiConnector().shutdown();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            login = RMIConnector.rmiReconnect().getLoginService();
            throw new RuntimeException(e);
        }finally {
            System.exit(0);

        }




    }

    public static void main(String[] args) {
        launch();
    }
}