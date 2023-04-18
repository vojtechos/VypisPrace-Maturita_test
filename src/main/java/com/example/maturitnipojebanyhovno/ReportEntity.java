package com.example.maturitnipojebanyhovno;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * třída představující jeden zápis
 */
public class ReportEntity {

    private int id;
    private LocalDate date;
    private LocalTime timeFrom;
    private LocalTime timeTo;

    /**
     * konstuktor vytváří instanci výkazu a spravuje jej
     * @param id identifikační číslo z databáze
     * @param date datum výpisu
     * @param timeFrom počáteční časový údaj práce
     * @param timeTo konečný časový údaj práce
     */
    public ReportEntity(int id, LocalDate date, LocalTime timeFrom, LocalTime timeTo) {
        this.id = id;
        this.date = date;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    /**
     * získává identifikační číslo
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * získává datum
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * získává počáteční čas
     * @return timeFrom
     */
    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    /**
     * získává konečný čas
     * @return timeTo
     */
    public LocalTime getTimeTo() {
        return timeTo;
    }

    @Override
    public String toString() {
        return "TableViewData{" +
                "id=" + id +
                ", date=" + date +
                ", timeFrom=" + timeFrom +
                ", timeTo=" + timeTo +
                '}';
    }
}
