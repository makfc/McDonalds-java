package org.acra.collector;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.acra.ACRA;

final class SettingsCollector {
    SettingsCollector() {
    }

    public static String collectSystemSettings(Context ctx) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : System.class.getFields()) {
            if (!field.isAnnotationPresent(Deprecated.class) && field.getType() == String.class) {
                try {
                    String string = System.getString(ctx.getContentResolver(), (String) field.get(null));
                    if (string != null) {
                        stringBuilder.append(field.getName()).append("=").append(string).append("\n");
                    }
                } catch (IllegalArgumentException e) {
                    ACRA.log.mo23354w(ACRA.LOG_TAG, "Error : ", e);
                } catch (IllegalAccessException e2) {
                    ACRA.log.mo23354w(ACRA.LOG_TAG, "Error : ", e2);
                }
            }
        }
        return stringBuilder.toString();
    }

    public static String collectSecureSettings(Context ctx) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : Secure.class.getFields()) {
            if (!field.isAnnotationPresent(Deprecated.class) && field.getType() == String.class && isAuthorized(field)) {
                try {
                    String string = Secure.getString(ctx.getContentResolver(), (String) field.get(null));
                    if (string != null) {
                        stringBuilder.append(field.getName()).append("=").append(string).append("\n");
                    }
                } catch (IllegalArgumentException e) {
                    ACRA.log.mo23354w(ACRA.LOG_TAG, "Error : ", e);
                } catch (IllegalAccessException e2) {
                    ACRA.log.mo23354w(ACRA.LOG_TAG, "Error : ", e2);
                }
            }
        }
        return stringBuilder.toString();
    }

    public static String collectGlobalSettings(Context ctx) {
        if (Compatibility.getAPILevel() < 17) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Class cls = Class.forName("android.provider.Settings$Global");
            Field[] fields = cls.getFields();
            Method method = cls.getMethod("getString", new Class[]{ContentResolver.class, String.class});
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Deprecated.class) && field.getType() == String.class && isAuthorized(field)) {
                    Object invoke = method.invoke(null, new Object[]{ctx.getContentResolver(), (String) field.get(null)});
                    if (invoke != null) {
                        stringBuilder.append(field.getName()).append("=").append(invoke).append("\n");
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            ACRA.log.mo23354w(ACRA.LOG_TAG, "Error : ", e);
        } catch (IllegalAccessException e2) {
            ACRA.log.mo23354w(ACRA.LOG_TAG, "Error : ", e2);
        } catch (ClassNotFoundException e3) {
            ACRA.log.mo23354w(ACRA.LOG_TAG, "Error : ", e3);
        } catch (SecurityException e4) {
            ACRA.log.mo23354w(ACRA.LOG_TAG, "Error : ", e4);
        } catch (NoSuchMethodException e5) {
            ACRA.log.mo23354w(ACRA.LOG_TAG, "Error : ", e5);
        } catch (InvocationTargetException e6) {
            ACRA.log.mo23354w(ACRA.LOG_TAG, "Error : ", e6);
        }
        return stringBuilder.toString();
    }

    private static boolean isAuthorized(Field key) {
        if (key == null || key.getName().startsWith("WIFI_AP")) {
            return false;
        }
        for (String matches : ACRA.getConfig().excludeMatchingSettingsKeys()) {
            if (key.getName().matches(matches)) {
                return false;
            }
        }
        return true;
    }
}
