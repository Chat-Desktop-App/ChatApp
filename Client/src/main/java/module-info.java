module gov.iti.jets.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.rmi;


    opens gov.iti.jets to javafx.fxml;
    opens gov.iti.jets.view to javafx.fxml;
    exports gov.iti.jets;
    exports gov.iti.jets.view to javafx.fxml;

}