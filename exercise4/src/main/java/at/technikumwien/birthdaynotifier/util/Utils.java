package at.technikumwien.birthdaynotifier.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import at.technikumwien.birthdaynotifier.data.model.Contact;

/**
 * This class contains our public static util methods that we use
 * e.g. in our {@link Contact} class.
 */
public class Utils {

    // The date format for displaying the birthday in our list
    private static final SimpleDateFormat BIRTHDAY_FORMAT = new SimpleDateFormat("dd. MMM\nyyyy");
    // The date format for parsing birthday dates from a String
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    // Checks whether a date is today by comparing the
    // MONTH and DAY_OF_MONTH fields of Calendars
    public static boolean isToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar now = Calendar.getInstance();

        return now.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                now.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH);
    }

    // Parses a Date from a String via a SimpleDateFormat object
    public static Date parseDate(String dateString) {
        try {
            return DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            Log.e("Utils", "Could not parse String '" + dateString + "' with date format '" + DATE_FORMAT.toPattern() + "'");
            return null;
        }
    }

    // Formats a Date to a String via a SimpleDateFormat object
    public static String formatBirthday(Date birthday) {
        return BIRTHDAY_FORMAT.format(birthday);
    }
}
