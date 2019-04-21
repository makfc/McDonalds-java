package com.admaster.square.utils;

import android.content.Context;

public class Reflection {
    public static String getPlayAdId(Context context) {
        try {
            return (String) invokeInstanceMethod(getAdvertisingInfoObject(context), "getId", null, new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }

    public static boolean isGooglePlayServicesAvailable(Context context) {
        try {
            return Boolean.valueOf(isConnectionResultSuccess((Integer) invokeStaticMethod("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", new Class[]{Context.class}, context))).booleanValue();
        } catch (Throwable th) {
            return false;
        }
    }

    public static String getMacAddress(Context context) {
        try {
            return (String) invokeStaticMethod("com.admaster.square.utils.MacAddressUtil", "getMacAddress", new Class[]{Context.class}, context);
        } catch (Throwable th) {
            return null;
        }
    }

    private static Object getAdvertisingInfoObject(Context context) throws Exception {
        return invokeStaticMethod("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", new Class[]{Context.class}, context);
    }

    private static boolean isConnectionResultSuccess(Integer num) {
        if (num == null) {
            return false;
        }
        try {
            if (Class.forName("com.google.android.gms.common.ConnectionResult").getField("SUCCESS").getInt(null) == num.intValue()) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    public static Class forName(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable th) {
            return null;
        }
    }

    public static Object createDefaultInstance(String str) {
        return createDefaultInstance(forName(str));
    }

    public static Object createDefaultInstance(Class cls) {
        try {
            return cls.newInstance();
        } catch (Throwable th) {
            return null;
        }
    }

    public static Object createInstance(String str, Class[] clsArr, Object... objArr) {
        try {
            return Class.forName(str).getConstructor(clsArr).newInstance(objArr);
        } catch (Throwable th) {
            return null;
        }
    }

    public static Object invokeStaticMethod(String str, String str2, Class[] clsArr, Object... objArr) throws Exception {
        return invokeMethod(Class.forName(str), str2, null, clsArr, objArr);
    }

    public static Object invokeInstanceMethod(Object obj, String str, Class[] clsArr, Object... objArr) throws Exception {
        return invokeMethod(obj.getClass(), str, obj, clsArr, objArr);
    }

    public static Object invokeMethod(Class cls, String str, Object obj, Class[] clsArr, Object... objArr) throws Exception {
        return cls.getMethod(str, clsArr).invoke(obj, objArr);
    }
}
