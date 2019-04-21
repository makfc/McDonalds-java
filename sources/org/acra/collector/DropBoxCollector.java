package org.acra.collector;

import android.content.Context;
import android.text.format.Time;
import com.autonavi.amap.mapcore.VTMCDataCache;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import org.acra.ACRA;

final class DropBoxCollector {
    private static final String[] SYSTEM_TAGS = new String[]{"system_app_anr", "system_app_wtf", "system_app_crash", "system_server_anr", "system_server_wtf", "system_server_crash", "BATTERY_DISCHARGE_INFO", "SYSTEM_RECOVERY_LOG", "SYSTEM_BOOT", "SYSTEM_LAST_KMSG", "APANIC_CONSOLE", "APANIC_THREADS", "SYSTEM_RESTART", "SYSTEM_TOMBSTONE", "data_app_strictmode"};

    DropBoxCollector() {
    }

    public static String read(Context context, String[] additionalTags) {
        try {
            String dropBoxServiceName = Compatibility.getDropBoxServiceName();
            if (dropBoxServiceName == null) {
                return "N/A";
            }
            Object systemService = context.getSystemService(dropBoxServiceName);
            Method method = systemService.getClass().getMethod("getNextEntry", new Class[]{String.class, Long.TYPE});
            if (method == null) {
                return "";
            }
            Time time = new Time();
            time.setToNow();
            time.minute -= ACRA.getConfig().dropboxCollectionMinutes();
            time.normalize(false);
            long toMillis = time.toMillis(false);
            ArrayList<String> arrayList = new ArrayList();
            if (ACRA.getConfig().includeDropBoxSystemTags()) {
                arrayList.addAll(Arrays.asList(SYSTEM_TAGS));
            }
            if (additionalTags != null && additionalTags.length > 0) {
                arrayList.addAll(Arrays.asList(additionalTags));
            }
            if (arrayList.isEmpty()) {
                return "No tag configured for collection.";
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (String dropBoxServiceName2 : arrayList) {
                stringBuilder.append("Tag: ").append(dropBoxServiceName2).append(10);
                Object invoke = method.invoke(systemService, new Object[]{dropBoxServiceName2, Long.valueOf(toMillis)});
                if (invoke == null) {
                    stringBuilder.append("Nothing.\n");
                } else {
                    Method method2 = invoke.getClass().getMethod("getText", new Class[]{Integer.TYPE});
                    Method method3 = invoke.getClass().getMethod("getTimeMillis", null);
                    Method method4 = invoke.getClass().getMethod("close", null);
                    Object obj = invoke;
                    while (obj != null) {
                        time.set(((Long) method3.invoke(obj, null)).longValue());
                        stringBuilder.append("@").append(time.format2445()).append(10);
                        String str = (String) method2.invoke(obj, new Object[]{Integer.valueOf(VTMCDataCache.MAXSIZE)});
                        if (str != null) {
                            stringBuilder.append("Text: ").append(str).append(10);
                        } else {
                            stringBuilder.append("Not Text!\n");
                        }
                        method4.invoke(obj, null);
                        obj = method.invoke(systemService, new Object[]{dropBoxServiceName2, Long.valueOf(r16)});
                    }
                }
            }
            return stringBuilder.toString();
        } catch (SecurityException e) {
            ACRA.log.mo23351i(ACRA.LOG_TAG, "DropBoxManager not available.");
        } catch (NoSuchMethodException e2) {
            ACRA.log.mo23351i(ACRA.LOG_TAG, "DropBoxManager not available.");
        } catch (IllegalArgumentException e3) {
            ACRA.log.mo23351i(ACRA.LOG_TAG, "DropBoxManager not available.");
        } catch (IllegalAccessException e4) {
            ACRA.log.mo23351i(ACRA.LOG_TAG, "DropBoxManager not available.");
        } catch (InvocationTargetException e5) {
            ACRA.log.mo23351i(ACRA.LOG_TAG, "DropBoxManager not available.");
        } catch (NoSuchFieldException e6) {
            ACRA.log.mo23351i(ACRA.LOG_TAG, "DropBoxManager not available.");
        }
        return "N/A";
    }
}
