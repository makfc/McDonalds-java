package com.alipay.android.phone.mrpc.core;

import android.text.format.Time;
import com.newrelic.agent.android.tracing.ActivityTrace;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.alipay.android.phone.mrpc.core.k */
public final class C0535k {
    /* renamed from: a */
    private static final Pattern f351a = Pattern.compile("([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])");
    /* renamed from: b */
    private static final Pattern f352b = Pattern.compile("[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})");

    /* renamed from: com.alipay.android.phone.mrpc.core.k$a */
    private static class C0534a {
        /* renamed from: a */
        int f348a;
        /* renamed from: b */
        int f349b;
        /* renamed from: c */
        int f350c;

        C0534a(int i, int i2, int i3) {
            this.f348a = i;
            this.f349b = i2;
            this.f350c = i3;
        }
    }

    /* renamed from: a */
    public static long m579a(String str) {
        int c;
        int d;
        C0534a e;
        int i;
        int i2 = 1;
        Matcher matcher = f351a.matcher(str);
        int b;
        if (matcher.find()) {
            b = C0535k.m580b(matcher.group(1));
            c = C0535k.m581c(matcher.group(2));
            d = C0535k.m582d(matcher.group(3));
            e = C0535k.m583e(matcher.group(4));
            i = b;
        } else {
            Matcher matcher2 = f352b.matcher(str);
            if (matcher2.find()) {
                c = C0535k.m581c(matcher2.group(1));
                b = C0535k.m580b(matcher2.group(2));
                C0534a e2 = C0535k.m583e(matcher2.group(3));
                d = C0535k.m582d(matcher2.group(4));
                e = e2;
                i = b;
            } else {
                throw new IllegalArgumentException();
            }
        }
        if (d >= 2038) {
            d = 2038;
            c = 0;
        } else {
            i2 = i;
        }
        Time time = new Time("UTC");
        time.set(e.f350c, e.f349b, e.f348a, i2, c, d);
        return time.toMillis(false);
    }

    /* renamed from: b */
    private static int m580b(String str) {
        return str.length() == 2 ? ((str.charAt(0) - 48) * 10) + (str.charAt(1) - 48) : str.charAt(0) - 48;
    }

    /* renamed from: c */
    private static int m581c(String str) {
        switch (((Character.toLowerCase(str.charAt(0)) + Character.toLowerCase(str.charAt(1))) + Character.toLowerCase(str.charAt(2))) - 291) {
            case 9:
                return 11;
            case 10:
                return 1;
            case 22:
                return 0;
            case 26:
                return 7;
            case 29:
                return 2;
            case 32:
                return 3;
            case 35:
                return 9;
            case 36:
                return 4;
            case 37:
                return 8;
            case 40:
                return 6;
            case 42:
                return 5;
            case 48:
                return 10;
            default:
                throw new IllegalArgumentException();
        }
    }

    /* renamed from: d */
    private static int m582d(String str) {
        if (str.length() != 2) {
            return str.length() == 3 ? ((((str.charAt(0) - 48) * 100) + ((str.charAt(1) - 48) * 10)) + (str.charAt(2) - 48)) + 1900 : str.length() == 4 ? ((((str.charAt(0) - 48) * 1000) + ((str.charAt(1) - 48) * 100)) + ((str.charAt(2) - 48) * 10)) + (str.charAt(3) - 48) : 1970;
        } else {
            int charAt = ((str.charAt(0) - 48) * 10) + (str.charAt(1) - 48);
            return charAt >= 70 ? charAt + 1900 : charAt + ActivityTrace.MAX_TRACES;
        }
    }

    /* renamed from: e */
    private static C0534a m583e(String str) {
        int i;
        int charAt = str.charAt(0) - 48;
        if (str.charAt(1) != ':') {
            i = 2;
            charAt = (charAt * 10) + (str.charAt(1) - 48);
        } else {
            i = 1;
        }
        i++;
        int i2 = i + 1;
        i = ((str.charAt(i) - 48) * 10) + (str.charAt(i2) - 48);
        i2 = (i2 + 1) + 1;
        return new C0534a(charAt, i, ((str.charAt(i2) - 48) * 10) + (str.charAt(i2 + 1) - 48));
    }
}
