module com.singleton.peliculascrud {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.singleton.peliculascrud to javafx.fxml;
    exports com.singleton.peliculascrud;
}