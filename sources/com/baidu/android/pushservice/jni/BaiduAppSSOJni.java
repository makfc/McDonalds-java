package com.baidu.android.pushservice.jni;

import android.content.Context;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p039k.C1464a;
import com.baidu.android.pushservice.p039k.C1465b;
import com.baidu.android.pushservice.p039k.C1471e;
import com.baidu.android.pushservice.util.C1578v;

public class BaiduAppSSOJni {
    static {
        try {
            System.loadLibrary("bdpush_V2_7");
        } catch (UnsatisfiedLinkError e) {
            C1425a.m6444e("BaiduAppSSOJni", "Native library not found! Please copy libbdpush_V2_7.so into your project!");
        }
    }

    public static native byte[] decryptAES(byte[] bArr, int i, int i2);

    public static native byte[] encryptAES(String str, int i);

    public static String getDecrypted(Context context, String str, String str2) {
        if (str == null) {
            try {
                str = "";
            } catch (Exception e) {
                C1425a.m6438a("BaiduAppSSOJni", "getDecrypted: " + e);
                return null;
            } catch (UnsatisfiedLinkError e2) {
                C1425a.m6444e("BaiduAppSSOJni", "UnsatisfiedLinkError getDecrypted " + str2);
                C1578v.m7095b("UnsatisfiedLinkError getDecrypted " + str2, context);
                return null;
            }
        }
        byte[] key = getKey(str);
        if (key == null) {
            C1425a.m6438a("BaiduAppSSOJni", "keyInfo null");
            return null;
        }
        String str3;
        byte[] a = C1465b.m6679a(str2.getBytes());
        String str4 = new String(key, "utf-8");
        if (str4 == null || str4.length() <= 0) {
            str3 = null;
        } else {
            str3 = new String(C1464a.m6677b(str4.substring(16), str4.substring(0, 16), a), "utf-8");
        }
        return str3;
    }

    public static String getEncrypted(Context context, String str, String str2) {
        if (str == null) {
            try {
                str = "";
            } catch (Exception e) {
                C1425a.m6438a("BaiduAppSSOJni", "getEncrypted: " + e);
                return null;
            } catch (UnsatisfiedLinkError e2) {
                C1425a.m6444e("BaiduAppSSOJni", "UnsatisfiedLinkError getEncrypted " + str2);
                C1578v.m7095b("UnsatisfiedLinkError getEncrypted " + str2, context);
                return null;
            }
        }
        byte[] key = getKey(str);
        if (key == null) {
            C1425a.m6438a("BaiduAppSSOJni", "keyInfo null");
            return null;
        }
        byte[] bytes = str2.getBytes();
        String str3 = new String(key, "utf-8");
        if (str3 == null || str3.length() <= 0) {
            return null;
        }
        return C1465b.m6678a(C1464a.m6676a(str3.substring(16), str3.substring(0, 16), bytes), "utf-8");
    }

    private static native byte[] getKey(String str);

    public static String getPushHash(Context context, String str, String str2, String str3) {
        if (context == null || str == null || str2 == null) {
            return null;
        }
        String str4 = str3 == null ? "other" : str3;
        String p = C1578v.m7138p(context.getApplicationContext(), str);
        if (p == null) {
            C1425a.m6442c("BaiduAppSSOJni", "can not get singInfo for: " + str);
            return null;
        }
        String str5 = "";
        try {
            return C1465b.m6678a(getSsoHashNative(context, str, str2, p, C1471e.m6687a(context), str4, System.currentTimeMillis()), "utf-8");
        } catch (Exception e) {
            C1425a.m6442c("BaiduAppSSOJni", "getPushHashException: " + e);
            return str5;
        }
    }

    private static native byte[] getSsoHashNative(Context context, String str, String str2, String str3, String str4, String str5, long j);
}
