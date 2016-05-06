package com.mycompany.web.validator.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	
    public SimpleDateFormat apiDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    
    private static final DateFormat dateFormat_yyyyMMdd_timeWithoutSec = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    private static final DateFormat dateFormat_ddMMyyyy_timeWithoutSec = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    private static final DateFormat hourFormatWithoutSec = new SimpleDateFormat("HH:mm");    

	
    /**
     * is formatted date
     *
     * @param formattedDate
     * @return Boolean
     */
    public static Boolean isFormattedDate(String formattedDate) {

            try {
                dateFormat_yyyyMMdd_timeWithoutSec.setLenient(false);
                dateFormat_yyyyMMdd_timeWithoutSec.parse(formattedDate);
            } catch (ParseException e) {
                return Boolean.FALSE;
            }

        return Boolean.TRUE;
    }

    /**
     * gets date from string
     *
     * @param formattedDate
     * @return GregorianCalendar
     * @throws ParseException
     */
    public static GregorianCalendar getDateFromString(String formattedDate) throws ParseException {
        GregorianCalendar date = new GregorianCalendar();
        Date parsedDate = new Date();
      
            parsedDate = dateFormat_yyyyMMdd_timeWithoutSec.parse(formattedDate);
   
        date.setTime(parsedDate);
        return date;
    }

    /**
     * gets date from string ddMMyyyy
     *
     * @param formattedDate
     * @return GregorianCalendar
     * @throws ParseException
     */
    public static GregorianCalendar getDateFromStringddMMyyyy(String formattedDate) throws ParseException {
        GregorianCalendar date = new GregorianCalendar();
        Date parsedDate = new Date();
        parsedDate = dateFormat_ddMMyyyy_timeWithoutSec.parse(formattedDate);
        date.setTime(parsedDate);
        return date;
    }

    /**
     * gets hours from string
     *
     * @param formattedHour
     * @return GregorianCalendar
     * @throws ParseException
     */
    public static GregorianCalendar getHourFromString(String formattedHour) throws ParseException {
        GregorianCalendar date = new GregorianCalendar();
        Date parsedHour = new Date();
        parsedHour = hourFormatWithoutSec.parse(formattedHour);
        date.setTime(parsedHour);
        return date;
    }

    /**
     * is null or empty
     *
     * @param data
     * @return boolean
     */
    public static boolean isNullOrEmpty(String data) {
        return data == null || data.isEmpty() || data.equals("");
    }

}
