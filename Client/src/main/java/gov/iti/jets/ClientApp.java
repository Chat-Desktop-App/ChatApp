package gov.iti.jets;

import gov.iti.jets.controller.HomeServiceController;
import gov.iti.jets.controller.LogInServiceController;
import gov.iti.jets.controller.Session;
import gov.iti.jets.model.LoginStatus;
import gov.iti.jets.model.User;
import gov.iti.jets.services.impls.ChatClientImpl;
import gov.iti.jets.services.interfaces.Login;
import gov.iti.jets.view.LoginController;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.Properties;

public class ClientApp extends Application {

//    private static final String SESSION_FILE =  "session.xml";
    private static final String LOGIN_FXML = "fxml/Login.fxml";
    private static final String HOME_FXML = "fxml/home.fxml";
    private Login login =  RMIConnector.getRmiConnector().getLoginService();

    @Override
    public void start(Stage stage)  {

        System.out.println("client running.......");
        Parent root = loadView();
        Scene scene = new Scene(root);
        stage.setTitle("Orca Chat");
        stage.setMinHeight(620);
        stage.setMinWidth(800);
        stage.setScene(scene);
        Session.setLoadStage(stage);
        Image icon = new Image(String.valueOf(ClientApp.class.getResource("images/orca-no-text.png")));
        stage.getIcons().add(icon);
        stage.show();

    }

    private Parent loadView() {
        String jarDirectory = System.getProperty("user.dir");        //check session to decide which scene to load
//        File file = new File(jarDirectory,"session.xml");
        File propertiesFile = new File(jarDirectory, "session.properties");
       // Path path = Paths.get("session.xml");
        FXMLLoader loader = null;

        if(Files.exists(propertiesFile.toPath())){
            // unmarshall it to a UserSession
            try {
//                JAXBContext context = JAXBContext.newInstance(LoginStatus.class);
//                Unmarshaller unmarshaller = context.createUnmarshaller();
////                LoginStatus loginStatus = (LoginStatus) unmarshaller.unmarshal(new FileReader(SESSION_FILE));
//                LoginStatus loginStatus = (LoginStatus) unmarshaller.unmarshal(new FileReader(file));
                // if session is valid go to home page
                // else load login page with user phone number

                Properties prop = new Properties();
                InputStream input =new FileInputStream(propertiesFile);
                prop.load(input);
                String phoneNumber = prop.getProperty("phoneNumber");
                String token = prop.getProperty("sessionToken");
                LoginStatus loginStatus = new LoginStatus(token,phoneNumber);

                if(login.validateSession(loginStatus)){
                    LogInServiceController.skipLogIn(loginStatus);
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
            String jarDirectory = System.getProperty("user.dir");        //check session to decide which scene to load
//            File file = new File(jarDirectory,"session.xml");
//            context = JAXBContext.newInstance(LoginStatus.class);
//            Unmarshaller unmarshaller = context.createUnmarshaller();
////            LoginStatus loginStatus = (LoginStatus) unmarshaller.unmarshal(new FileReader("session.xml"));
//            LoginStatus loginStatus = (LoginStatus) unmarshaller.unmarshal(new FileReader(file));

            File propertiesFile = new File(jarDirectory, "session.properties");
            Properties prop = new Properties();
            InputStream input =new FileInputStream(propertiesFile);
            prop.load(input);
            String phoneNumber = prop.getProperty("phoneNumber");
            String token = prop.getProperty("sessionToken");
            LoginStatus loginStatus = new LoginStatus(token,phoneNumber);

            login.exit(loginStatus.getPhoneNumber());
            RMIConnector.getRmiConnector().shutdown();
        } catch (RemoteException e) {
            login = RMIConnector.rmiReconnect().getLoginService();
            throw new RuntimeException(e);
        } catch ( IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.exit(0);

        }

    }

    public static void main(String[] args) {
        launch();
    }
}