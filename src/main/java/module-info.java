module com.fiseq1.simuladormetro {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.fiseq1.simuladormetro to javafx.fxml;
    exports com.fiseq1.simuladormetro;
}