package com.mcdonalds.sdk.modules.storelocator;

import com.autonavi.amap.mapcore.MapCore;
import com.mcdonalds.sdk.services.log.MCDLog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class StoreDayPartRange {
    private static final ThreadLocal<SimpleDateFormat> formatter = new C40961();
    private Calendar baseDate;
    public Calendar from;
    /* renamed from: to */
    public Calendar f6684to;

    /* renamed from: com.mcdonalds.sdk.modules.storelocator.StoreDayPartRange$1 */
    static class C40961 extends ThreadLocal<SimpleDateFormat> {
        C40961() {
        }

        /* Access modifiers changed, original: protected */
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm");
        }
    }

    public StoreDayPartRange(Calendar date) {
        this.baseDate = (Calendar) date.clone();
        this.baseDate.set(11, 0);
        this.baseDate.set(12, 0);
        this.baseDate.set(13, 0);
        this.from = (Calendar) date.clone();
        this.from.set(11, 0);
        this.from.set(12, 0);
        this.from.set(13, 0);
        this.from.add(1, 1);
        this.f6684to = (Calendar) date.clone();
        this.f6684to.set(11, 0);
        this.f6684to.set(12, 0);
        this.f6684to.set(13, 0);
        this.f6684to.add(1, -1);
    }

    public void expandRange(String fromString, String toString) {
        try {
            Date fromTime = parseFromTimeString(fromString);
            Date toTime = parseToTimeString(toString);
            Calendar fromNew = createCalendarInstance(fromTime);
            Calendar toNew = createCalendarInstance(toTime);
            if (toNew.before(fromNew)) {
                toNew.add(5, 1);
            }
            if (this.from.after(fromNew)) {
                this.from.setTime(fromNew.getTime());
            }
            if (this.f6684to.before(toNew)) {
                this.f6684to.setTime(toNew.getTime());
                this.f6684to.set(13, 59);
                this.f6684to.set(14, MapCore.MAPRENDER_CAN_STOP_AND_FULLSCREEN_RENDEROVER);
            }
        } catch (ParseException e) {
            MCDLog.error("DAYPART", "Error while trying to parse the from and/or to hours of the store day part (expandRange)");
        }
    }

    private Calendar createCalendarInstance(Date time) {
        Calendar timeTemp = Calendar.getInstance();
        timeTemp.setTime(time);
        Calendar newInstance = Calendar.getInstance();
        newInstance.setTime(this.baseDate.getTime());
        newInstance.set(11, timeTemp.get(11));
        newInstance.set(12, timeTemp.get(12));
        newInstance.set(13, timeTemp.get(13));
        return newInstance;
    }

    public static Date getCurrentTime(Calendar date) throws ParseException {
        return parse(date.get(11) + ":" + date.get(12));
    }

    private static Date parse(String timeString) throws ParseException {
        if (timeString == null) {
            return new Date();
        }
        return ((SimpleDateFormat) formatter.get()).parse(timeString);
    }

    public static Date parseToTimeString(String timeString) throws ParseException {
        Date date = parse(timeString);
        date.setTime((date.getTime() + TimeUnit.SECONDS.toMillis(59)) + 999);
        return date;
    }

    public static Date parseFromTimeString(String timeString) throws ParseException {
        return parse(timeString);
    }

    public static String formatTimeString(Date time) {
        return ((SimpleDateFormat) formatter.get()).format(time);
    }
}
