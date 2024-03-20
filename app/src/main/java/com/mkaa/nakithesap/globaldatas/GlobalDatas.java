package com.mkaa.nakithesap.globaldatas;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class GlobalDatas {

    public String TarihAlma()
    {
        String tarih;
        String gun, ay, yil;
        SimpleDateFormat dfm = new SimpleDateFormat("dd:MM:yyyy");

        //SimpleDateFormat clockFormat = new SimpleDateFormat("hh:mm:ss");
        GregorianCalendar gcalender = new GregorianCalendar();

        gun = dfm.format(gcalender.DAY_OF_WEEK);
        ay = dfm.format(gcalender.MONTH);
        yil = dfm.format(gcalender.YEAR);

        tarih = dfm.format(gcalender.getTime());
        return tarih;
    }
}
