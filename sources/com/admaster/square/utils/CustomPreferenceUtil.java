package com.admaster.square.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* renamed from: com.admaster.square.utils.i */
public class CustomPreferenceUtil {
    /* renamed from: a */
    public static void m411a(Context context, String str, String str2, String str3) {
        if (context != null) {
            try {
                SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
                if (sharedPreferences != null) {
                    Editor edit = sharedPreferences.edit();
                    edit.putString(str2, str3);
                    edit.commit();
                }
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: b */
    public static void m413b(Context context, String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2)) {
            CustomPreferenceUtil.m411a(context, str, "m2id", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            CustomPreferenceUtil.m411a(context, str, "ch", str3);
        }
    }

    /* renamed from: a */
    public static String m409a(Context context, String str, String str2) {
        if (context == null) {
            return null;
        }
        try {
            return context.getSharedPreferences(str, 0).getString(str2, "");
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: a */
    public static void m412a(Context context, String str, String str2, boolean z) {
        if (context != null) {
            try {
                SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
                if (sharedPreferences != null) {
                    Editor edit = sharedPreferences.edit();
                    edit.putBoolean(str2, z);
                    edit.commit();
                }
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: b */
    public static boolean m414b(Context context, String str, String str2) {
        boolean z = false;
        if (context == null) {
            return z;
        }
        try {
            return context.getSharedPreferences(str, 0).getBoolean(str2, false);
        } catch (Exception e) {
            return z;
        }
    }

    /* renamed from: a */
    public static SharedPreferences m407a(Context context, String str) {
        SharedPreferences sharedPreferences = null;
        if (context == null) {
            return sharedPreferences;
        }
        try {
            return context.getSharedPreferences(str, 0);
        } catch (Exception e) {
            return sharedPreferences;
        }
    }

    /* renamed from: c */
    public static boolean m415c(Context context, String str, String str2) {
        boolean z = false;
        if (context == null) {
            return z;
        }
        try {
            return context.getSharedPreferences(str, 0).edit().remove(str2).commit();
        } catch (Exception e) {
            return z;
        }
    }

    /* renamed from: a */
    public static void m410a(Context context, String str, String str2, Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            String str3 = new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0));
            objectOutputStream.close();
            if (str3 != null && !str3.equals("")) {
                CustomPreferenceUtil.m411a(context, str, str2, str3);
            }
        } catch (Exception e) {
            CustomPreferenceUtil.m411a(context, str, str2, "");
        }
    }

    /* renamed from: d */
    public static Object m416d(Context context, String str, String str2) {
        try {
            String a = CustomPreferenceUtil.m409a(context, str, str2);
            if (TextUtils.isEmpty(a)) {
                return null;
            }
            return CustomPreferenceUtil.m408a(a);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    private static Object m408a(String str) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(str.getBytes(), 0)));
            Object readObject = objectInputStream.readObject();
            objectInputStream.close();
            return readObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: e */
    public static synchronized String m417e(Context context, String str, String str2) {
        String str3 = null;
        synchronized (CustomPreferenceUtil.class) {
            if (context != null) {
                try {
                    String string = context.getSharedPreferences(str, 0).getString(str2, "");
                    str3 = TextUtils.isEmpty(string) ? "" : AndroidUtil.m395b(string);
                } catch (Exception e) {
                }
            }
        }
        return str3;
    }

    /* renamed from: f */
    public static synchronized String m418f(Context context, String str, String str2) {
        String str3 = null;
        synchronized (CustomPreferenceUtil.class) {
            if (context != null) {
                try {
                    String string = context.getSharedPreferences(str, 0).getString(EncryptionUtil.m420a(str2), "");
                    str3 = TextUtils.isEmpty(string) ? "" : EncryptionUtil.m428b(string);
                } catch (Exception e) {
                }
            }
        }
        return str3;
    }
}
