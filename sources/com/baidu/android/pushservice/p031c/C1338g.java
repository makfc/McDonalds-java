package com.baidu.android.pushservice.p031c;

import android.content.Context;

/* renamed from: com.baidu.android.pushservice.c.g */
public class C1338g extends C1336e {
    /* renamed from: d */
    private static volatile C1338g f4738d;

    private C1338g(Context context) {
        super(context, C1333c.LIGHT_APP_CLIENT_NEW);
    }

    /* renamed from: a */
    public static synchronized C1338g m6051a(Context context) {
        C1338g c1338g;
        synchronized (C1338g.class) {
            if (f4738d == null) {
                f4738d = new C1338g(context);
            } else {
                f4738d.f4736b = context.getApplicationContext();
            }
            c1338g = f4738d;
        }
        return c1338g;
    }
}
