package com.amap.api.mapcore2d;

import android.text.TextUtils;
import java.net.Proxy;
import java.util.Map;

/* compiled from: Request */
/* renamed from: com.amap.api.mapcore2d.eg */
public abstract class C0931eg {
    /* renamed from: c */
    int f2561c = 20000;
    /* renamed from: d */
    int f2562d = 20000;
    /* renamed from: e */
    Proxy f2563e = null;

    /* renamed from: e */
    public abstract Map<String, String> mo10071e();

    /* renamed from: f */
    public abstract Map<String, String> mo10072f();

    /* renamed from: g */
    public abstract String mo10073g();

    /* Access modifiers changed, original: 0000 */
    /* renamed from: h */
    public String mo10074h() {
        byte[] a_ = mo10069a_();
        if (a_ == null || a_.length == 0) {
            return mo10073g();
        }
        Map f = mo10072f();
        if (f == null) {
            return mo10073g();
        }
        String a = C1024ed.m4267a(f);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(mo10073g()).append("?").append(a);
        return stringBuffer.toString();
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: i */
    public byte[] mo10075i() {
        byte[] a_ = mo10069a_();
        if (a_ != null && a_.length != 0) {
            return a_;
        }
        String a = C1024ed.m4267a(mo10072f());
        if (TextUtils.isEmpty(a)) {
            return a_;
        }
        return C0978cw.m4050a(a);
    }

    /* renamed from: a */
    public final void mo10067a(int i) {
        this.f2561c = i;
    }

    /* renamed from: b */
    public final void mo10070b(int i) {
        this.f2562d = i;
    }

    /* renamed from: a_ */
    public byte[] mo10069a_() {
        return null;
    }

    /* renamed from: a */
    public final void mo10068a(Proxy proxy) {
        this.f2563e = proxy;
    }
}
