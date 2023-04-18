package com.example.maturitnipojebanyhovno;

/**
 * třída zajišťuje mezi jednotlivými jevištěmi a uchovává stav aplikace
 *
 * uchovává vybraný zápis a předá ho editovacímu kontroleru
 * uchovává mód, který určuje, jestli užovatel přidává, nebo edituje záznam, jelikož na tyto funkce použivá pouze jeden view
 */
public class ReportSingleton {
    private static ReportEntity report;
    private static String mode;

    /**
     * nastavuje výkaz na singleton
     * @param report instance třídy ReportEntity
     */
    public static void setReport(ReportEntity report){
        ReportSingleton.report= report;
    }

    /**
     * získává instanci výkazu ze singletonu
     * @return report(výkaz)
     */
    public static ReportEntity getReport(){
        return report;
    }

    /**
     * nastavuje řežim upravování, nebo přidávání výkazů
     * @param mode mód(režim)
     */
    public static void setMode(String mode){
        ReportSingleton.mode = mode;
    }

    /**
     * získává režim
     * @return mód(režim)
     */
    public static String getMode(){
        return mode;
    }
}
