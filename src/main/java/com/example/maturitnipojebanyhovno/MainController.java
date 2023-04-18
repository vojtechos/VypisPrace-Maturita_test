package com.example.maturitnipojebanyhovno;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * kontroler pro main view, kde se zobrazují záznamy a obsahují tlačítka
 */
public class MainController implements Initializable {

    @FXML
    public TableView<ReportEntity> tableView;
    @FXML
    public TableColumn<ReportEntity, String> dateColumn;
    @FXML
    public TableColumn<ReportEntity, String> timeFromColumn;
    @FXML
    public TableColumn<ReportEntity, String> timeToColumn;
    @FXML
    public Button refreshTableViewButton;
    public Label daysCounter;
    public Label hoursCounter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeFromColumn.setCellValueFactory(new PropertyValueFactory<>("timeFrom"));
        timeToColumn.setCellValueFactory(new PropertyValueFactory<>("timeTo"));
        try {
            refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * metoda která nastaví mód a otevře okno na přidání zápisu
     * @param mouseEvent java fx event po zmářknutí tlačítka
     * @throws IOException pokud nenajde fxml soubor, tak vyhodí chybu
     */
    public void onAdd(MouseEvent mouseEvent) throws IOException{
        ReportSingleton.setMode("add");
        ReportsApplication.editWindow();
    }

    /**
     * metoda, která nastaví mód a otevře okno pro úpravu výpisu
     * @param mouseEvent java fx event po zmářknutí tlačítka
     * @throws IOException pokud nenajde fxml soubor, tak vyhodí chybu
     */
    public void onEdit(MouseEvent mouseEvent) throws IOException{
        ReportEntity selectedReport = tableView.getSelectionModel().getSelectedItem();
        if(selectedReport == null){
            return;
        }
        ReportSingleton.setMode("edit");
        ReportSingleton.setReport(selectedReport);
        ReportsApplication.editWindow();
    }

    /**
     * metoda, která volá metodu refresh
     * @param mouseEvent java fx event po zmářknutí tlačítka
     * @throws SQLException pokud se stane cyhba na databázi
     */
    public void onRefresh(MouseEvent mouseEvent) throws SQLException {
        refresh();
    }

    /**
     * metoda, která odstraní z tabulky výpisů vybranný výpis
     * @param mouseEvent java fx event po zmářknutí tlačítka
     * @throws SQLException pokud se stane cyhba na databázi
     */
    public void onDelete(MouseEvent mouseEvent)throws SQLException {
        ReportEntity selectedReport = tableView.getSelectionModel().getSelectedItem();
        if(selectedReport == null){
            return;
        }
        DBDriver.deleteItem(selectedReport.getId());
        refresh();
    }

    /**
     * metoda refresh, která obnoví data v tabulce, aby se v ní zobrazili
     * @throws SQLException pokud se stane cyhba na databázi
     */
    private void refresh() throws SQLException {
        ArrayList<ReportEntity> reports = DBDriver.selectAll();
        tableView.getItems().clear();
        for (ReportEntity report: reports) {
            tableView.getItems().add(report);
        }
    }

    /**
     * metoda, která na stisknutí tlačítka vypíše, kolik času pesnt strávil nad nějakou praácí
     * @param mouseEvent java fx event po zmářknutí tlačítka
     * @throws SQLException pokud se stane cyhba na databázi
     */
    public void showTimeWorked(MouseEvent mouseEvent) throws SQLException {
        Integer[] values = DBDriver.count();
        int days = values[0];
        int hours = values[1];
        daysCounter.setText(String.valueOf(days));
        hoursCounter.setText(String.valueOf(hours));
    }
}