package com.alipay.p009a.p010a;

import java.lang.reflect.Type;
import java.util.Date;

/* renamed from: com.alipay.a.a.c */
public final class C0499c implements C0495i, C0496j {
    /* renamed from: a */
    public final Object mo7837a(Object obj) {
        return Long.valueOf(((Date) obj).getTime());
    }

    /* renamed from: a */
    public final Object mo7835a(Object obj, Type type) {
        return new Date(((Long) obj).longValue());
    }

    /* renamed from: a */
    public final boolean mo7836a(Class<?> cls) {
        return Date.class.isAssignableFrom(cls);
    }
}
