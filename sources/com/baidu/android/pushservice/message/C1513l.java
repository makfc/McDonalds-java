package com.baidu.android.pushservice.message;

import android.content.Context;
import com.baidu.android.pushservice.p036h.C1425a;

/* renamed from: com.baidu.android.pushservice.message.l */
public class C1513l extends C1503d {
    /* renamed from: b */
    private static final String f5296b = C1513l.class.getSimpleName();

    public C1513l(Context context) {
        super(context);
    }

    /* renamed from: a */
    public C1508h mo13975a(C1506f c1506f) {
        c1506f.f5262e = true;
        C1508h c1508h = new C1508h();
        c1508h.mo13991a(0);
        C1425a.m6441b(f5296b, "handleMessage: server heart beat type - " + c1506f.f5258a);
        return c1508h;
    }
}
