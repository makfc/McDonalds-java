package com.mcdonalds.sdk.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.text.format.DateFormat;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    public static final long DAY_IN_MILLIS = TimeUnit.DAYS.toMillis(1);
    public static final int DAY_SECONDS_MINUS_ONE_MINUTE = 86340;
    private static final String FORMAT_HHMM = "HH:mm";
    private static final String FORMAT_ISO8631 = "yyyy-MM-dd'T'HH:mm:ss";
    public static final long HOUR_IN_MILLIS = TimeUnit.HOURS.toMillis(1);
    public static final long MINUTE_IN_MILLIS = TimeUnit.MINUTES.toMillis(1);
    public static final long SECOND_IN_MILLIS = 1000;
    private static final String TIME_ZONE_CONFIG_KEY = "modules.customer.useTimeZoneNameForNotification";

    public static String formatToISO8631(Date date, boolean useUTC) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_ISO8631, Locale.getDefault());
        if (useUTC) {
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
        return formatter.format(date);
    }

    public static Date parseFromISO8631(String dateString, boolean useUTC) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat(FORMAT_ISO8631, Locale.getDefault());
        if (useUTC) {
            parser.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
        return parser.parse(dateString);
    }

    public static Date parseFromFormat(String dateString, String format, boolean useUTC) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        if (useUTC) {
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
        return sdf.parse(dateString);
    }

    public static boolean areTimesEqualOrWithinAMinute(String fromTime, String toTime) {
        if (fromTime == null || toTime == null) {
            return false;
        }
        if (fromTime.equalsIgnoreCase(toTime)) {
            return true;
        }
        try {
            Date fromTimeDate = parseFromFormat(fromTime, "hh:mm a", true);
            Date toTimeDate = parseFromFormat(toTime, "hh:mm a", true);
            if (fromTimeDate.compareTo(toTimeDate) == 0) {
                return true;
            }
            Calendar fromCalendar = Calendar.getInstance();
            fromCalendar.setTimeInMillis(fromTimeDate.getTime());
            Calendar toCalendar = Calendar.getInstance();
            toCalendar.setTimeInMillis(toTimeDate.getTime());
            toCalendar.add(12, 1);
            if (toCalendar.getTime().compareTo(fromCalendar.getTime()) == 0) {
                return true;
            }
            fromCalendar.add(10, 24);
            if (toCalendar.getTime().compareTo(fromCalendar.getTime()) == 0) {
                return true;
            }
            return false;
        } catch (ParseException e) {
            return false;
        }
    }

    public static String formatRange(Context context, long rangeMillis) {
        Resources res = context.getResources();
        StringBuilder stringBuilder = new StringBuilder();
        if (rangeMillis >= DAY_IN_MILLIS) {
            int days = (int) (rangeMillis / DAY_IN_MILLIS);
            stringBuilder.append(res.getQuantityString(C3883R.plurals.numberOfDays, days, new Object[]{Integer.valueOf(days), Integer.valueOf(days)}));
            rangeMillis -= ((long) days) * DAY_IN_MILLIS;
        }
        if (rangeMillis >= HOUR_IN_MILLIS) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            int hours = (int) (rangeMillis / HOUR_IN_MILLIS);
            stringBuilder.append(res.getQuantityString(C3883R.plurals.numberOfHours, hours, new Object[]{Integer.valueOf(hours), Integer.valueOf(hours)}));
            rangeMillis -= ((long) hours) * HOUR_IN_MILLIS;
        }
        if (rangeMillis >= MINUTE_IN_MILLIS) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            int minutes = (int) (rangeMillis / MINUTE_IN_MILLIS);
            stringBuilder.append(res.getQuantityString(C3883R.plurals.numberOfMinutes, minutes, new Object[]{Integer.valueOf(minutes)}));
        }
        return stringBuilder.toString();
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat("MM/dd/yy, hh:mm aaa").format(date);
    }

    public static String formatDateInSummary(Context context, Date date) {
        if (date == null) {
            return context.getResources().getString(C3883R.string.unknown);
        }
        SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy, h:mm a");
        if (LocalDataManager.getSharedInstance().getDeviceLanguage().toLowerCase().contains("zh")) {
            df.applyPattern("yyyy/M/d ah:mm");
        }
        return df.format(date);
    }

    public static int dayFromStringToInt(String day) {
        if (day.equalsIgnoreCase("sunday")) {
            return 1;
        }
        if (day.equalsIgnoreCase("monday")) {
            return 2;
        }
        if (day.equalsIgnoreCase("tuesday")) {
            return 3;
        }
        if (day.equalsIgnoreCase("wednesday")) {
            return 4;
        }
        if (day.equalsIgnoreCase("thursday")) {
            return 5;
        }
        if (day.equalsIgnoreCase("friday")) {
            return 6;
        }
        if (day.equalsIgnoreCase("saturday")) {
            return 7;
        }
        return 2;
    }

    public static int getTimeField(String time, int field) {
        Date d = new SimpleDateFormat(FORMAT_HHMM).parse(time, new ParsePosition(0));
        if (d == null) {
            return 0;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(field);
    }

    public static boolean timeCheck(int hourOfDay, int minute, int hourOfDayFrom, int minuteFrom, int hourOfDayTo, int minuteTo) {
        return (hourOfDay * 60) + minute >= (hourOfDayFrom * 60) + minuteFrom && (hourOfDay * 60) + minute <= (hourOfDayTo * 60) + minuteTo;
    }

    public static String format24HourTimeToSystemFormat(String time24HourFormat, Context context) {
        return DateFormat.is24HourFormat(context) ? time24HourFormat : formatTo12Hour(time24HourFormat);
    }

    public static String formatTimeToSystemFormat(String timeFormat, Context context) {
        return format24HourTimeToSystemFormat(formatTo24Hour(timeFormat), context);
    }

    public static String formatTo12Hour(String time) {
        SimpleDateFormat sdf24hr = new SimpleDateFormat(FORMAT_HHMM);
        SimpleDateFormat sdf12hr = new SimpleDateFormat("hh:mm a");
        Date date = null;
        try {
            date = sdf24hr.parse(time);
        } catch (ParseException e) {
        }
        if (date != null) {
            return sdf12hr.format(date);
        }
        return time;
    }

    public static String formatTo24Hour(String time) {
        SimpleDateFormat sdf24hr = new SimpleDateFormat(FORMAT_HHMM);
        Date date = null;
        try {
            date = new SimpleDateFormat("hh:mm a").parse(time);
        } catch (ParseException e) {
        }
        if (date != null) {
            return sdf24hr.format(date);
        }
        return time;
    }

    public static String getTimeInHours(String time) {
        long timeInLong = Long.parseLong(time);
        java.text.DateFormat formatter = new SimpleDateFormat("hh:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInLong);
        return formatter.format(calendar.getTime());
    }

    public static String getHoursTimeInTimeZone(String time, String timeZone) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        TimeZone timeZone2;
        long timeInLong = Long.parseLong(time);
        java.text.DateFormat formatter = new SimpleDateFormat("hh:mm a");
        if (timeZone != null) {
            timeZone2 = TimeZone.getTimeZone(timeZone);
        } else {
            timeZone2 = TimeZone.getDefault();
        }
        formatter.setTimeZone(timeZone2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInLong);
        return formatter.format(calendar.getTime());
    }

    public static Calendar getBaseDate(Calendar c) {
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        return c;
    }

    public static boolean is24HourOpen(String startTime, String endTime) {
        if (TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime)) {
            return false;
        }
        Calendar baseDate = getBaseDate(Calendar.getInstance());
        Calendar from = (Calendar) baseDate.clone();
        Calendar to = (Calendar) baseDate.clone();
        from.add(13, (int) (Long.parseLong(startTime) / 1000));
        to.add(13, (int) (Long.parseLong(endTime) / 1000));
        if ((to.getTimeInMillis() - from.getTimeInMillis()) / 1000 <= 0) {
            to.add(5, 1);
        }
        if ((to.getTimeInMillis() - from.getTimeInMillis()) / 1000 >= 86340) {
            return true;
        }
        return false;
    }

    public static String timeZoneForNotificationCall() {
        if (Configuration.getSharedInstance().getBooleanForKey(TIME_ZONE_CONFIG_KEY)) {
            return TimeZone.getDefault().getID();
        }
        return TimeZone.getDefault().getDisplayName(false, 0);
    }
}
