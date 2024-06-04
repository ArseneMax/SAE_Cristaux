module com.example.cristaux {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires junit;

    opens com.example.cristaux to javafx.fxml;
    exports com.example.cristaux;

    exports vue;
    exports modele;
    exports controleur;
    exports test;
}