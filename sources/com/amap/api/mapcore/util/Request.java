package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.net.Proxy;
import java.util.Map;

/* renamed from: com.amap.api.mapcore.util.fw */
public abstract class Request {
    /* renamed from: g */
    int f1397g = 20000;
    /* renamed from: h */
    int f1398h = 20000;
    /* renamed from: i */
    Proxy f1399i = null;

    /* renamed from: a */
    public abstract String mo8901a();

    /* renamed from: b */
    public abstract Map<String, String> mo8905b();

    /* renamed from: c */
    public abstract Map<String, String> mo8907c();

    /* Access modifiers changed, original: 0000 */
    /* renamed from: f */
    public String mo8908f() {
        byte[] a_ = mo8904a_();
        if (a_ == null || a_.length == 0) {
            return mo8901a();
        }
        Map b = mo8905b();
        if (b == null) {
            return mo8901a();
        }
        String a = HttpUrlUtil.m2808a(b);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(mo8901a()).append("?").append(a);
        return stringBuffer.toString();
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: g */
    public byte[] mo8909g() {
        byte[] a_ = mo8904a_();
        if (a_ != null && a_.length != 0) {
            return a_;
        }
        String a = HttpUrlUtil.m2808a(mo8905b());
        if (TextUtils.isEmpty(a)) {
            return a_;
        }
        return Utils.m2515a(a);
    }

    /* renamed from: a */
    public final void mo8902a(int i) {
        this.f1397g = i;
    }

    /* renamed from: b */
    public final void mo8906b(int i) {
        this.f1398h = i;
    }

    /* renamed from: a_ */
    public byte[] mo8904a_() {
        return null;
    }

    /* renamed from: a */
    public final void mo8903a(Proxy proxy) {
        this.f1399i = proxy;
    }
}
