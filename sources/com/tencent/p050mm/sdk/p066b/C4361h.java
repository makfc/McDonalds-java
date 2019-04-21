package com.tencent.p050mm.sdk.p066b;

import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.util.TimeZone;

/* renamed from: com.tencent.mm.sdk.b.h */
public final class C4361h {
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    /* renamed from: bh */
    public static final long[] f6813bh = new long[]{300, 200, 300, 200};
    /* renamed from: bi */
    private static final long[] f6814bi = new long[]{300, 50, 300, 50};
    /* renamed from: bj */
    private static final char[] f6815bj = new char[]{'<', '>', '\"', '\'', '&', 13, 10, SafeJsonPrimitive.NULL_CHAR, 9};
    /* renamed from: bk */
    private static final String[] f6816bk = new String[]{"&lt;", "&gt;", "&quot;", "&apos;", "&amp;", "&#x0D;", "&#x0A;", "&#x20;", "&#x09;"};

    /* renamed from: h */
    public static boolean m7904h(String str) {
        return str == null || str.length() <= 0;
    }

    /* renamed from: u */
    public static C4359f m7905u() {
        return new C4359f();
    }
}
