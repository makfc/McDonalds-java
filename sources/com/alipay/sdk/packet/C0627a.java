package com.alipay.sdk.packet;

import android.text.TextUtils;

/* renamed from: com.alipay.sdk.packet.a */
public class C0627a {
    /* renamed from: a */
    public static String m915a(String str) {
        CharSequence charSequence = null;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split("&");
        if (split.length == 0) {
            return "";
        }
        CharSequence charSequence2 = null;
        CharSequence charSequence3 = null;
        CharSequence charSequence4 = null;
        for (String str2 : split) {
            if (TextUtils.isEmpty(charSequence4)) {
                charSequence4 = C0627a.m916b(str2);
            }
            if (TextUtils.isEmpty(charSequence3)) {
                charSequence3 = C0627a.m917c(str2);
            }
            if (TextUtils.isEmpty(charSequence2)) {
                charSequence2 = C0627a.m918d(str2);
            }
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = C0627a.m920f(str2);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(charSequence4)) {
            stringBuilder.append("biz_type=" + charSequence4 + ";");
        }
        if (!TextUtils.isEmpty(charSequence3)) {
            stringBuilder.append("biz_no=" + charSequence3 + ";");
        }
        if (!TextUtils.isEmpty(charSequence2)) {
            stringBuilder.append("trade_no=" + charSequence2 + ";");
        }
        if (!TextUtils.isEmpty(charSequence)) {
            stringBuilder.append("app_userid=" + charSequence + ";");
        }
        String stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2.endsWith(";")) {
            return stringBuilder2.substring(0, stringBuilder2.length() - 1);
        }
        return stringBuilder2;
    }

    /* renamed from: b */
    private static String m916b(String str) {
        if (str.contains("biz_type")) {
            return C0627a.m919e(str);
        }
        return null;
    }

    /* renamed from: c */
    private static String m917c(String str) {
        if (str.contains("biz_no")) {
            return C0627a.m919e(str);
        }
        return null;
    }

    /* renamed from: d */
    private static String m918d(String str) {
        if (!str.contains("trade_no") || str.startsWith("out_trade_no")) {
            return null;
        }
        return C0627a.m919e(str);
    }

    /* renamed from: e */
    private static String m919e(String str) {
        String[] split = str.split("=");
        if (split.length <= 1) {
            return null;
        }
        String str2 = split[1];
        if (str2.contains("\"")) {
            return str2.replaceAll("\"", "");
        }
        return str2;
    }

    /* renamed from: f */
    private static String m920f(String str) {
        if (str.contains("app_userid")) {
            return C0627a.m919e(str);
        }
        return null;
    }
}
