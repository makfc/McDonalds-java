package com.baidu.android.pushservice.p031c;

import android.content.Context;

/* renamed from: com.baidu.android.pushservice.c.l */
public class C1343l extends C1336e {
    /* renamed from: d */
    private static volatile C1343l f4743d;

    private C1343l(Context context) {
        super(context, C1333c.WEBAPP_CLIENT);
    }

    /* renamed from: a */
    public static synchronized C1343l m6060a(Context context) {
        C1343l c1343l;
        synchronized (C1343l.class) {
            if (f4743d == null) {
                f4743d = new C1343l(context);
            } else {
                f4743d.f4736b = context.getApplicationContext();
            }
            c1343l = f4743d;
        }
        return c1343l;
    }
}
