package com.example.maturitnipojebanyhovno;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * kontroler pro editovací view, kde se nachází jeden záznam, s kterým můžeme pracovat
 */
public class EditController implements Initializable {
    @FXML
    public DatePicker datePicker;
    @FXML
    public Button submitButton;
    @FXML
    public TextField fromTimeHours;
    @FXML
    public TextField toTimeHours;
    @FXML
    public TextField toTimeMinutes;
    @FXML
    public TextField fromTimeMinutes;
    @FXML
    public Label warning;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ReportEntity report = ReportSingleton.getReport();
        if (report == null) {
            return;
        }
        datePicker.setValue(report.getDate());
        fromTimeHours.setText(report.getTimeFrom().getHour() + "");
        fromTimeMinutes.setText(report.getTimeFrom().getMinute() + "");
        toTimeHours.setText(report.getTimeTo().getHour() + "");
        toTimeMinutes.setText(report.getTimeTo().getMinute() + "");
    }

    /**
     * metoda, která vykoná změnu a vytvoření záznamu
     * <p>
     * ošetřuje zapisování času do výpisu, kdy dodržuje časový formát xx:xx, kdy uživatel může první číslo vynechat
     *
     * @param mouseEvent java fx event po zmářknutí tlačítka
     * @throws SQLException pokud se stane cyhba na databázi
     */
    public void submitButton(MouseEvent mouseEvent) throws SQLException {
        handleSubmit((Node) mouseEvent.getSource());
    }

    private void handleSubmit(Node source) throws SQLException {
        LocalDate localDate = datePicker.getValue();
        String fromHourValue = fromTimeHours.getText();
        String toHourValue = toTimeHours.getText();
        String fromMinuteValue = fromTimeMinutes.getText();
        String toMinuteValue = toTimeMinutes.getText();

        if (localDate == null || fromHourValue.equals("") || toHourValue.equals("") || fromMinuteValue.equals("") || toMinuteValue.equals("")) {
            return;
        }

        String finalFromHour = String.format("%02d", Integer.parseInt(fromHourValue));
        String finalToHour = String.format("%02d", Integer.parseInt(toHourValue));
        String finalFromMinute = String.format("%02d", Integer.parseInt(fromMinuteValue));
        String finalToMinute = String.format("%02d", Integer.parseInt(toMinuteValue));

        LocalTime localTimeFrom = LocalTime.parse(finalFromHour + ":" + finalFromMinute);
        LocalTime localTimeTo = LocalTime.parse(finalToHour + ":" + finalToMinute);

        String mode = ReportSingleton.getMode();
        if (mode.equals("add")) {
            DBDriver.addItem(localDate, localTimeFrom, localTimeTo);
        } else if (mode.equals("edit")) {
            int id = ReportSingleton.getReport().getId();
            DBDriver.editItem(id, localDate, localTimeFrom, localTimeTo);
        }

        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * metoda, která vykoná změnu a vytvoření záznamu
     *
     * @param keyEvent java fx event po zmářknutí klávesnice enter na na tlačítku
     * @throws SQLException pokud se stane cyhba na databázi
     */
    public void onEnter(KeyEvent keyEvent) throws SQLException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            handleSubmit((Node) keyEvent.getSource());
        }
    }
}
