module com.example.javafxproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.javafxproject to javafx.fxml;
    exports com.example.javafxproject;
}