package com.baidu.android.pushservice.p031c;

import android.content.Context;
import android.text.TextUtils;

/* renamed from: com.baidu.android.pushservice.c.d */
public class C1334d {
    /* renamed from: a */
    public C1339h f4730a;
    /* renamed from: b */
    public C1340i f4731b;
    /* renamed from: c */
    public C1337f f4732c;
    /* renamed from: d */
    private C1333c f4733d;

    public C1334d(C1333c c1333c) {
        this.f4733d = c1333c;
    }

    /* renamed from: a */
    public static C1334d m6039a(Context context, String str) {
        C1332b.m6020a(context).mo13597b(context);
        C1339h d = C1332b.m6020a(context).mo13600d(str);
        C1334d c1334d;
        if (d == null || TextUtils.isEmpty(d.f4718c)) {
            C1340i f = C1341j.m6054a(context).mo13609b(str);
            if (f != null && f.mo13589c() != null) {
                c1334d = new C1334d(C1333c.SDK_CLIENT);
                c1334d.f4731b = f;
                return c1334d;
            } else if (((C1342k) C1343l.m6060a(context).mo13609b(str)) != null) {
                return new C1334d(C1333c.WEBAPP_CLIENT);
            } else {
                C1337f c1337f = (C1337f) C1338g.m6051a(context).mo13609b(str);
                if (c1337f == null) {
                    return new C1334d(C1333c.UNKNOWN_CLIENT);
                }
                C1334d c1334d2 = new C1334d(C1333c.LIGHT_APP_CLIENT_NEW);
                c1334d2.f4732c = c1337f;
                return c1334d2;
            }
        }
        c1334d = new C1334d(C1333c.PUSH_CLIENT);
        c1334d.f4730a = d;
        return c1334d;
    }

    /* renamed from: a */
    public C1333c mo13603a() {
        return this.f4733d;
    }
}
