module gov.iti.jets.server {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.rmi;

    opens gov.iti.jets to javafx.fxml;
    exports gov.iti.jets;

    exports gov.iti.jets.client.services.interfaces;
}