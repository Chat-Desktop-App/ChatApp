module gov.iti.jets.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires jakarta.xml.bind;
    requires javafx.web;
    requires java.management.rmi;


    opens gov.iti.jets to javafx.fxml;
    opens gov.iti.jets.view to javafx.graphics, javafx.fxml;
    exports gov.iti.jets;
    exports gov.iti.jets.model;

    exports gov.iti.jets.services.interfaces;
    opens gov.iti.jets.model to jakarta.xml.bind;

}