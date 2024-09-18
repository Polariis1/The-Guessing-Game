module org.polar.freader {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.polar.freader to javafx.fxml;
    exports org.polar.freader;
}