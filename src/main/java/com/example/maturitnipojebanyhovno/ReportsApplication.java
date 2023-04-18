package com.example.maturitnipojebanyhovno;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * třída pro práci s různými scénami
 */
public class ReportsApplication extends Application {
    /**
     * hlavní scéna aplikace, ve které běží celá aplikace
     * @param stage systémové parametry při spuštění
     * @throws IOException pokud nenajde view.fxml tak vyhodí chybu
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ReportsApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Maturita");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * druhá vyskakovací scéna, ve které pracujeme s daty
     * @throws IOException pokud nenajde view.fxml tak vyhodí chybu
     */
    public static void editWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ReportsApplication.class.getResource("edit-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Maturita");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * třída na spuštění aplikace
     * @param args systémové parametry při spuštění
     */
    public static void main(String[] args) {
        launch();
    }
}