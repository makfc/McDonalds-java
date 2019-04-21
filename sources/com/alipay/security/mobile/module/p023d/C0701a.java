package com.alipay.security.mobile.module.p023d;

import com.alipay.security.mobile.module.p019a.C0689a;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* renamed from: com.alipay.security.mobile.module.d.a */
public final class C0701a {
    /* renamed from: a */
    private String f728a;
    /* renamed from: b */
    private String f729b;
    /* renamed from: c */
    private String f730c;
    /* renamed from: d */
    private String f731d;
    /* renamed from: e */
    private String f732e;
    /* renamed from: f */
    private String f733f;
    /* renamed from: g */
    private String f734g;

    public C0701a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.f728a = str;
        this.f729b = str2;
        this.f730c = str3;
        this.f731d = str4;
        this.f732e = str5;
        this.f733f = str6;
        this.f734g = str7;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()));
        stringBuffer.append("," + this.f728a);
        stringBuffer.append("," + this.f729b);
        stringBuffer.append("," + this.f730c);
        stringBuffer.append("," + this.f731d);
        if (C0689a.m1169a(this.f732e) || this.f732e.length() < 20) {
            stringBuffer.append("," + this.f732e);
        } else {
            stringBuffer.append("," + this.f732e.substring(0, 20));
        }
        if (C0689a.m1169a(this.f733f) || this.f733f.length() < 20) {
            stringBuffer.append("," + this.f733f);
        } else {
            stringBuffer.append("," + this.f733f.substring(0, 20));
        }
        if (C0689a.m1169a(this.f734g) || this.f734g.length() < 20) {
            stringBuffer.append("," + this.f734g);
        } else {
            stringBuffer.append("," + this.f734g.substring(0, 20));
        }
        return stringBuffer.toString();
    }
}
