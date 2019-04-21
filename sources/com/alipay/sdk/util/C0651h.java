package com.alipay.sdk.util;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.C0590a;

/* renamed from: com.alipay.sdk.util.h */
public class C0651h {
    /* renamed from: a */
    public static void m1033a(Context context, String str) {
        try {
            String a = C0651h.m1032a(str);
            C0646c.m1017b("", "PayResultUtil::saveTradeToken > tradeToken:" + a);
            if (!TextUtils.isEmpty(a)) {
                C0652i.m1035a(context, "pref_trade_token", a);
            }
        } catch (Throwable th) {
            C0590a.m802a("biz", "SaveTradeTokenError", th);
            C0646c.m1016a(th);
        }
    }

    /* renamed from: a */
    public static String m1032a(String str) {
        String str2 = null;
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(";");
            int i = 0;
            while (i < split.length) {
                if (split[i].startsWith("result={") && split[i].endsWith("}")) {
                    String[] split2 = split[i].substring("result={".length(), split[i].length() - "}".length()).split("&");
                    int i2 = 0;
                    while (i2 < split2.length) {
                        if (split2[i2].startsWith("trade_token=\"") && split2[i2].endsWith("\"")) {
                            str2 = split2[i2].substring("trade_token=\"".length(), split2[i2].length() - "\"".length());
                            break;
                        } else if (split2[i2].startsWith("trade_token=")) {
                            str2 = split2[i2].substring("trade_token=".length());
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
                i++;
            }
        }
        return str2;
    }

    /* renamed from: a */
    public static String m1031a(Context context) {
        String b = C0652i.m1036b(context, "pref_trade_token", "");
        C0646c.m1017b("", "PayResultUtil::fetchTradeToken > tradeToken:" + b);
        return b;
    }
}
