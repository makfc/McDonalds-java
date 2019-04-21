package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Proxy;

/* renamed from: com.alipay.android.phone.mrpc.core.x */
public final class C0546x {
    /* renamed from: a */
    private C0529g f399a;
    /* renamed from: b */
    private C0548z f400b = new C0548z(this);

    public C0546x(C0529g c0529g) {
        this.f399a = c0529g;
    }

    /* renamed from: a */
    public final C0529g mo7928a() {
        return this.f399a;
    }

    /* renamed from: a */
    public final <T> T mo7929a(Class<T> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new C0547y(this.f399a, cls, this.f400b));
    }
}
