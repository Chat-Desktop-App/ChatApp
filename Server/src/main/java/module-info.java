module iti.jets.server {
    requires javafx.controls;
    requires javafx.fxml;


    opens iti.jets.server to javafx.fxml;
    exports iti.jets.server;
}