package gov.iti.jets.client.database;

import gov.iti.jets.client.HelloApplication;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {

    private static DataBaseConnection instance;
    private Connection connection;
    private String url;
    private String username;
    private String password;

    private DataBaseConnection() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        Properties properties = new Properties();

        try (InputStream inputStream = HelloApplication.class.getResourceAsStream("db.properties")){
            properties.load(inputStream);
            this.url = properties.getProperty("URL");
            this.username = properties.getProperty("USER");
            this.password = properties.getProperty("PASSWORD");
            this.connection = DriverManager.getConnection(url, username, password);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public Connection getConnection(){ return  connection;}

    public static DataBaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DataBaseConnection();
        }
        return  instance;
    }


}
