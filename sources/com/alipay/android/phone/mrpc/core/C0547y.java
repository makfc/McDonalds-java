package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* renamed from: com.alipay.android.phone.mrpc.core.y */
public final class C0547y implements InvocationHandler {
    /* renamed from: a */
    protected C0529g f401a;
    /* renamed from: b */
    protected Class<?> f402b;
    /* renamed from: c */
    protected C0548z f403c;

    public C0547y(C0529g c0529g, Class<?> cls, C0548z c0548z) {
        this.f401a = c0529g;
        this.f402b = cls;
        this.f403c = c0548z;
    }

    public final Object invoke(Object obj, Method method, Object[] objArr) {
        C0548z c0548z = this.f403c;
        Class cls = this.f402b;
        return c0548z.mo7931a(method, objArr);
    }
}
