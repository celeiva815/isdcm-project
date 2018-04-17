/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Tito
 */
public class DateHelper {
    
        public static final String YEAR_MONTH_DAY = "yyyy-MM-dd";
    
        public static String formatDate(Date date) {
        
        // Create an instance of SimpleDateFormat used for formatting 
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Using DateFormat format method we can create a string 
        // representation of a date with the defined format.
        return df.format(date);
    }
        
        public static Date parseDate(String date, String format) throws ParseException {
        
        // Create an instance of SimpleDateFormat used for formatting 
        // the string representation of date (month/day/year)
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return sdf.parse(date);
    }
}
