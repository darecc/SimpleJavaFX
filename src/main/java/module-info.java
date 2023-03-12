module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    requires com.google.gson;

    opens dc to javafx.fxml;
    exports dc;
}