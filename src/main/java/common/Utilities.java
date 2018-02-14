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

    //method to change date format
    //parameters(1) - String - date to be changed
    //parameters(2) - String - date format of date to be changed
    //parameters(3) - String - date format required
    //output - String - date in new format
    public static String changeDateFormat(String dateToChange, String formatFrom, String formatTo) throws ParseException {
        Date tempSittingDate = new SimpleDateFormat(formatFrom).parse(dateToChange);
        String updatedDate = new SimpleDateFormat(formatTo).format(tempSittingDate);
        logger.debug("date is changed from [" + dateToChange  + "] to [" + updatedDate + "]");
        return updatedDate;
    }

    //method to add days
    //parameters(1) - String - original date
    //parameters(2) - int - number of days to be added
    //output - String  - new date
    public static String addDays(String date, int days) throws ParseException {
        GregorianCalendar calendar = new GregorianCalendar();
        Date tempDate = new SimpleDateFormat("d-MMMM-yyyy").parse(date);
        calendar.setTime(tempDate);
        calendar.add(Calendar.DATE, days);
        return new SimpleDateFormat("d-MMMM-yyyy").format(calendar.getTime());
    }

    //method to get day of month
    //parameters - String - date
    //output - String - day of month. there will not be any preceeding zeroes
    public static String getDayOfMonth(String date) throws ParseException {
        Date tempDate = new SimpleDateFormat("d-MMMM-yyyy").parse(date);
        return new SimpleDateFormat("d").format(tempDate);
    }

    //method to get month
    //parameters - String - date
    //output - String - month
    public static String getMonthOfYear(String date) throws ParseException {
        Date tempDate = new SimpleDateFormat("d-MMMM-yyyy").parse(date);
        return new SimpleDateFormat("MMMM").format(tempDate);
    }

    //static method to get current date in string format
    //output - String - date in format d-MMMM-yyyy
    public static String getCurrentDate(){
        return new SimpleDateFormat("d-MMMM-yyyy").format(new Date());
    }

}
