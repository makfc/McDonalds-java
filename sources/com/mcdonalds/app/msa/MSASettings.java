package com.mcdonalds.app.msa;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.ensighten.Ensighten;
import com.mcdonalds.app.McDonaldsApplication;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import java.util.Calendar;
import java.util.Random;

public class MSASettings {
    public static final MSATuneItem[] BUNDLED_TUNES = new MSATuneItem[0];
    public static final MSATuneItem FIXED_TUNE = new MSATuneItem("", McDonaldsApplication.getInstance().getString(C2658R.string.msa_ringtone_fixed_sound), null, C2658R.raw.mcd_jingle, MSATuneItem.MSA_TUNE_FROM_APP);
    public static final MSATuneItem PHONE_TUNE = new MSATuneItem(McDonaldsApplication.getInstance().getString(C2658R.string.msa_ringtone_select_from_phone), "", null, 0, MSATuneItem.MSA_TUNE_FROM_PHONE);
    public static final MSATuneItem RANDOM_TUNE = new MSATuneItem(McDonaldsApplication.getInstance().getString(C2658R.string.msa_ringtone_surprise_me), McDonaldsApplication.getInstance().getString(C2658R.string.msa_ringtone_surprise_me), null, 0, MSATuneItem.MSA_TUNE_RANDOM);

    public static void scheduleNextAlarm(Context ctx) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSASettings", "scheduleNextAlarm", new Object[]{ctx});
        ctx.startService(new Intent(ctx, PersistService.class));
    }

    public static void startNextAlarm(Context ctx) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSASettings", "startNextAlarm", new Object[]{ctx});
        Calendar alarm = findNextAlarm(ctx);
        if (alarm != null) {
            scheduleNotification(ctx, alarm.getTimeInMillis());
        } else {
            cancelAlarm(ctx);
        }
    }

    public static Calendar findNextAlarm(Context ctx) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSASettings", "findNextAlarm", new Object[]{ctx});
        if (LocalDataManager.getSharedInstance().getPrefSavedLogin() == null) {
            return null;
        }
        SharedPreferences pref = ctx.getSharedPreferences(getPrefName(), 0);
        int hour = pref.getInt("HOUR", -1);
        if (hour < 0) {
            return null;
        }
        int minute = pref.getInt("MINUTE", -1);
        boolean[] alarmDays = new boolean[]{pref.getBoolean("IS_REPEAT_ON_SUNDAY", false), pref.getBoolean("IS_REPEAT_ON_MONDAY", false), pref.getBoolean("IS_REPEAT_ON_TUESDAY", false), pref.getBoolean("IS_REPEAT_ON_WEDNESDAY", false), pref.getBoolean("IS_REPEAT_ON_THURSDAY", false), pref.getBoolean("IS_REPEAT_ON_FRIDAY", false), pref.getBoolean("IS_REPEAT_ON_SATURDAY", false)};
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, hour);
        calendar.set(12, minute);
        calendar.set(13, 0);
        calendar.set(14, 0);
        int dayOfWeek = calendar.get(7) - 1;
        for (int i = 0; i < 7; i++) {
            dayOfWeek %= 7;
            if (alarmDays[dayOfWeek]) {
                Calendar c = checkForNextAlarmTime(calendar);
                if (c != null) {
                    return c;
                }
            }
            calendar.add(10, 24);
            dayOfWeek++;
        }
        return calendar;
    }

    private static Calendar checkForNextAlarmTime(Calendar calendar) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSASettings", "checkForNextAlarmTime", new Object[]{calendar});
        return calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis() > 0 ? calendar : null;
    }

    public static void scheduleNotification(Context ctx, long time) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSASettings", "scheduleNotification", new Object[]{ctx, new Long(time)});
        PendingIntent pIntent = PendingIntent.getBroadcast(ctx, 0, new Intent(ctx, MSANotificationPublisher.class), 134217728);
        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService("alarm");
        if (VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(0, time, pIntent);
        } else if (VERSION.SDK_INT >= 19) {
            alarmManager.setExact(0, time, pIntent);
        } else {
            alarmManager.set(0, time, pIntent);
        }
    }

    public static void saveAlarmId(Context ctx, int alarmType, String alarmId, String desc) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSASettings", "saveAlarmId", new Object[]{ctx, new Integer(alarmType), alarmId, desc});
        Editor e = ctx.getSharedPreferences(getPrefName(), 0).edit();
        e.putInt("ALARM_TONE_TYPE", alarmType);
        e.putString("ALARM_TONE_ID", alarmId);
        e.putString("ALARM_TONE_DESC", desc);
        e.commit();
    }

    public static String getAlarmDesc(Context ctx) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSASettings", "getAlarmDesc", new Object[]{ctx});
        return ctx.getSharedPreferences(getPrefName(), 0).getString("ALARM_TONE_DESC", "");
    }

    public static int getAlarmType(Context ctx) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSASettings", "getAlarmType", new Object[]{ctx});
        return ctx.getSharedPreferences(getPrefName(), 0).getInt("ALARM_TONE_TYPE", -1);
    }

    public static String getAlarmId(Context ctx) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSASettings", "getAlarmId", new Object[]{ctx});
        return ctx.getSharedPreferences(getPrefName(), 0).getString("ALARM_TONE_ID", "");
    }

    private static void cancelAlarm(Context ctx) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSASettings", "cancelAlarm", new Object[]{ctx});
        ((AlarmManager) ctx.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(ctx, 0, new Intent(ctx, MSANotificationPublisher.class), 134217728));
    }

    public static void saveSettings(Context ctx, int hour, int min, boolean[] alarmDays) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSASettings", "saveSettings", new Object[]{ctx, new Integer(hour), new Integer(min), alarmDays});
        if (LocalDataManager.getSharedInstance().getPrefSavedLogin() != null) {
            Editor e = ctx.getSharedPreferences(getPrefName(), 0).edit();
            e.putInt("HOUR", hour);
            e.putInt("MINUTE", min);
            e.putBoolean("IS_REPEAT_ON_SUNDAY", alarmDays[0]);
            e.putBoolean("IS_REPEAT_ON_MONDAY", alarmDays[1]);
            e.putBoolean("IS_REPEAT_ON_TUESDAY", alarmDays[2]);
            e.putBoolean("IS_REPEAT_ON_WEDNESDAY", alarmDays[3]);
            e.putBoolean("IS_REPEAT_ON_THURSDAY", alarmDays[4]);
            e.putBoolean("IS_REPEAT_ON_FRIDAY", alarmDays[5]);
            e.putBoolean("IS_REPEAT_ON_SATURDAY", alarmDays[6]);
            e.commit();
        }
    }

    public static String getPrefName() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSASettings", "getPrefName", null);
        String username = LocalDataManager.getSharedInstance().getPrefSavedLogin();
        if (username != null) {
            return "MSASettings" + username;
        }
        return null;
    }

    public static MSATuneItem getRandomTune() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSASettings", "getRandomTune", null);
        return BUNDLED_TUNES[new Random().nextInt(BUNDLED_TUNES.length)];
    }

    public static void clearAlarm(Context ctx) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSASettings", "clearAlarm", new Object[]{ctx});
        cancelAlarm(ctx);
        ctx.getSharedPreferences(getPrefName(), 0).edit().putBoolean("IS_MSA_TURNED_ON", false).commit();
    }

    public static boolean alarmEnabled(Context ctx) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.msa.MSASettings", "alarmEnabled", new Object[]{ctx});
        return ctx.getSharedPreferences(getPrefName(), 0).getBoolean("IS_MSA_TURNED_ON", false);
    }
}
