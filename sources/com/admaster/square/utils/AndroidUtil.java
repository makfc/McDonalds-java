package com.admaster.square.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.admaster.square.api.Logger;
import com.facebook.stetho.common.Utf8Charset;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;

@SuppressLint({"NewApi"})
/* renamed from: com.admaster.square.utils.a */
public class AndroidUtil {
    /* renamed from: a */
    public static String m391a(String str) {
        try {
            return URLEncoder.encode(str, Utf8Charset.NAME);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    public static String m395b(String str) {
        try {
            return URLDecoder.decode(str, Utf8Charset.NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public static String m392a(Map<?, ?> map) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{");
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new NullPointerException("key == null");
                }
                stringBuilder.append("\"");
                stringBuilder.append(str);
                stringBuilder.append("\":");
                if (str.toString().equals("deviceID") || str.toString().equals("orderMessages") || str.toString().equals("applist") || str.toString().equals("wirelesslist")) {
                    stringBuilder.append(entry.getValue());
                    stringBuilder.append(",");
                } else {
                    stringBuilder.append("\"");
                    stringBuilder.append(entry.getValue());
                    stringBuilder.append("\",");
                }
            }
            stringBuilder.replace(stringBuilder.lastIndexOf(","), stringBuilder.lastIndexOf(",") + 1, "");
            stringBuilder.append("}");
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m394a(long j) {
        try {
            Calendar instance = Calendar.getInstance();
            int i = instance.get(1);
            int i2 = instance.get(2);
            int i3 = instance.get(5);
            instance.set(i, i2, i3, 0, 0, 0);
            long timeInMillis = instance.getTimeInMillis();
            instance.set(i, i2, i3, 23, 59, 59);
            long timeInMillis2 = instance.getTimeInMillis();
            if (j < timeInMillis || j > timeInMillis2) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m396b(long j) {
        try {
            Calendar instance = Calendar.getInstance();
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            instance.add(5, -2);
            int i = instance.get(1);
            int i2 = instance.get(2);
            int i3 = instance.get(5);
            long timeInMillis = instance.getTimeInMillis();
            instance.set(i, i2, i3 + 2, 23, 59, 59);
            long timeInMillis2 = instance.getTimeInMillis();
            if (j < timeInMillis || j > timeInMillis2) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public static String m390a(Context context, String str) {
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                obj = null;
            } else {
                obj = applicationInfo.metaData.get(str);
            }
        } catch (NameNotFoundException e) {
            Logger.m364b("Could not read  meta-data from AndroidManifest.xml.");
            obj = null;
        }
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    /* renamed from: b */
    public static boolean m397b(Context context, String str) {
        try {
            if (context.checkCallingOrSelfPermission(str) == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public static void m393a(Map<String, String> map, String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        if (z) {
            map.put(str, AndroidUtil.m391a(str2));
        } else {
            map.put(str, str2);
        }
    }
}
