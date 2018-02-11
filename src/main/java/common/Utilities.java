package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utilities {

    private static final Logger logger = LogManager.getLogger(Utilities.class);

    public static String changeDateFormat(String dateToChange, String formatFrom, String formatTo) throws ParseException {

        Date tempSittingDate = new SimpleDateFormat(formatFrom).parse(dateToChange);
        dateToChange = new SimpleDateFormat(formatTo).format(tempSittingDate);

        return dateToChange;

    }

    public static String addDays(String date, int days) throws ParseException {
        GregorianCalendar calendar = new GregorianCalendar();
        Date tempDate = new SimpleDateFormat("d-MMMM-yyyy").parse(date);
        calendar.setTime(tempDate);
        calendar.add(Calendar.DATE, days);
        return new SimpleDateFormat("d-MMMM-yyyy").format(calendar.getTime());
    }

    public static String getDayOfMonth(String date) throws ParseException {

        Date tempDate = new SimpleDateFormat("d-MMMM-yyyy").parse(date);
        return new SimpleDateFormat("d").format(tempDate);

    }

    public static String getMonthOfYear(String date) throws ParseException {

        Date tempDate = new SimpleDateFormat("d-MMMM-yyyy").parse(date);
        return new SimpleDateFormat("MMMM").format(tempDate);

    }

    public static String getCurrentDate(){
        return new SimpleDateFormat("d-MMMM-yyyy").format(new Date());

    }

    public static String getNextDayOfWeekPastGivenDate (String fromDate, String format, String dayOfWeek,
                                                        String requiredFormat) throws ParseException {

        fromDate = changeDateFormat(fromDate,format,"yyyy-MM-dd");

        int year = Integer.parseInt(fromDate.split("-")[0]);
        int month = Integer.parseInt(fromDate.split("-")[1])-1;
        int day = Integer.parseInt(fromDate.split("-")[2]);

        GregorianCalendar tempDate = new GregorianCalendar( year, month, day );

        int dayOfWeekRequired =Calendar.MONDAY;
        if (dayOfWeek.toUpperCase().equals("MONDAY")){
            dayOfWeekRequired = Calendar.MONDAY;
        } else if (dayOfWeek.toUpperCase().equals("TUESDAY")){
            dayOfWeekRequired = Calendar.TUESDAY;
        }else if (dayOfWeek.toUpperCase().equals("THURSDAY")){
            dayOfWeekRequired = Calendar.THURSDAY;
        }

        while (tempDate.get(Calendar.DAY_OF_WEEK) != dayOfWeekRequired) {
            tempDate.add(Calendar.DATE, 1);
        }
        return new SimpleDateFormat(requiredFormat).format(tempDate.getTime());

    }
}
