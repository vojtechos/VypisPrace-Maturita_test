module com.example.maturitnipojebanyhovno {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.maturitnipojebanyhovno to javafx.fxml;
    exports com.example.maturitnipojebanyhovno;
}