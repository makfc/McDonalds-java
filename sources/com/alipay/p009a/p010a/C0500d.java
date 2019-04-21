package com.alipay.p009a.p010a;

import java.lang.reflect.Type;

/* renamed from: com.alipay.a.a.d */
public final class C0500d implements C0495i, C0496j {
    /* renamed from: a */
    public final Object mo7837a(Object obj) {
        return ((Enum) obj).name();
    }

    /* renamed from: a */
    public final Object mo7835a(Object obj, Type type) {
        return Enum.valueOf((Class) type, obj.toString());
    }

    /* renamed from: a */
    public final boolean mo7836a(Class<?> cls) {
        return Enum.class.isAssignableFrom(cls);
    }
}
