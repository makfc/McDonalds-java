package com.ensighten;

import android.os.Build.VERSION;
import java.io.Serializable;
import java.util.Date;

/* renamed from: com.ensighten.I */
public final class C1700I implements Serializable {
    private static final long serialVersionUID = 2724665488728292836L;
    /* renamed from: a */
    public String f5635a;
    /* renamed from: b */
    private String f5636b;
    /* renamed from: c */
    private Date f5637c;
    /* renamed from: d */
    private String f5638d;

    public C1700I(String str, String str2, Date date, String str3) {
        this.f5635a = str;
        this.f5636b = str2;
        this.f5637c = date;
        this.f5638d = str3;
    }

    /* renamed from: a */
    public final String mo15030a() {
        if (VERSION.SDK_INT >= 19) {
            return String.format("%s(\"%s\", '%s');", new Object[]{"gatewayEval2", C1719P.m7252a(this.f5635a.replace("\\\"", "\"").getBytes(), 2), C1719P.m7252a(this.f5638d.getBytes(), 2)});
        }
        return String.format("%s(\"%s\", '%s');", new Object[]{"gatewayEval", this.f5635a, this.f5638d});
    }
}
