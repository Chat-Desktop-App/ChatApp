module iti.jets.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens iti.jets.client to javafx.fxml;
    exports iti.jets.client;
}