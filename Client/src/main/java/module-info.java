module gov.iti.jets.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.rmi;


    opens gov.iti.jets.client to javafx.fxml;
    opens gov.iti.jets.client.view to javafx.fxml;
    exports gov.iti.jets.client;
    exports gov.iti.jets.client.view to javafx.fxml;
    exports gov.iti.jets.client.services.interfaces;

}