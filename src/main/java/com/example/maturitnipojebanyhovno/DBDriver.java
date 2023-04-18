package com.example.maturitnipojebanyhovno;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * spravuje komunikaci mezi databází a aplikací
 */
public class DBDriver {
    private static Connection connection;
    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/vypis_prace_database", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * získává všechny výkazy
     * @return report(výkaz)
     * @throws SQLException pokud se stane cyhba na databázi
     */
    public static ArrayList<ReportEntity> selectAll() throws SQLException {
        ArrayList<ReportEntity> reports = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM vypis_prace");
        while(resultSet.next()){
            reports.add(new ReportEntity(
                    resultSet.getInt(1),
                    resultSet.getDate(2).toLocalDate(),
                    resultSet.getTime(3).toLocalTime(),
                    resultSet.getTime(4).toLocalTime()));
        }
        return reports;
    }

    /**
     * metoda na přidání výpisu do databáze
     * @param date datum výpisu
     * @param timeFrom počáteční časový údaj práce
     * @param timeTo konečný časový údaj práce
     * @throws SQLException pokud se stane cyhba na databázi
     */
    public static void addItem(LocalDate date, LocalTime timeFrom, LocalTime timeTo) throws SQLException {
        ArrayList<ReportEntity> reports = selectAll();
        for (ReportEntity dbReport : reports) {
            LocalDate dbDate = dbReport.getDate();

            if (!dbDate.equals(date))continue;

            LocalTime dbTimeFrom = dbReport.getTimeFrom();

            if (timeFrom.isBefore(dbTimeFrom) || timeTo.isAfter(dbTimeFrom)) {
                System.out.println("cyhba");
                return;
            }
        }
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO vypis_prace (date, timeFrom, timeTo) VALUES (? , ? , ?);");
        preparedStatement.setDate(1, Date.valueOf(date));
        preparedStatement.setTime(2, Time.valueOf(timeFrom));
        preparedStatement.setTime(3, Time.valueOf(timeTo));
        preparedStatement.execute();
    }

    /**
     * meotda na upravování záznamu výpisu
     * @param id identifikační číslo
     * @param date datum výpisu
     * @param timeFrom počáteční časový údaj práce
     * @param timeTo konečný časový údaj práce
     * @throws SQLException pokud se stane cyhba na databázi
     */
    public static void editItem(int id, LocalDate date, LocalTime timeFrom, LocalTime timeTo) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE vypis_prace SET date= ?,  timeFrom=?, timeTo=? WHERE id=?");
        preparedStatement.setDate(1, Date.valueOf(date));
        preparedStatement.setTime(2, Time.valueOf(timeFrom));
        preparedStatement.setTime(3, Time.valueOf(timeTo));
        preparedStatement.setInt(4, id);
        preparedStatement.execute();

    }

    /**
     * metoda na odebrání záznamu výpisu
     * @param id identifikační číslo
     * @throws SQLException pokud se stane cyhba na databázi
     */
    public static void deleteItem(int id) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM vypis_prace WHERE id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    /**
     * meotda na výpočet stráveného času prací
     * @return pole dvou čísel (počet dnů, počet hodin)
     * @throws SQLException pokud se stane cyhba na databázi
     */
    public static Integer[] count() throws SQLException {
        ArrayList<ReportEntity> reports = selectAll();
        ArrayList<LocalDate> dates = new ArrayList<>();

        int sumMinutes = 0;
        for (ReportEntity report : reports) {
            if (!dates.contains(report.getDate())) {
                dates.add(report.getDate());
            }

            int hours = report.getTimeTo().getHour() - report.getTimeFrom().getHour();
            int minutes = report.getTimeTo().getMinute() - report.getTimeFrom().getMinute();
            sumMinutes += hours * 60 + minutes;
        }
        return new Integer[]{dates.size(), sumMinutes/60};
    }
}
