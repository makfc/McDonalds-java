package com.alipay.p009a.p010a;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import org.json.alipay.C4634a;

/* renamed from: com.alipay.a.a.k */
public final class C0505k implements C0495i {
    /* renamed from: a */
    public final Object mo7835a(Object obj, Type type) {
        int i = 0;
        if (!obj.getClass().equals(C4634a.class)) {
            return null;
        }
        C4634a c4634a = (C4634a) obj;
        HashSet hashSet = new HashSet();
        Type type2 = type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : Object.class;
        while (i < c4634a.mo34951a()) {
            hashSet.add(C0501e.m523a(c4634a.mo34952a(i), type2));
            i++;
        }
        return hashSet;
    }

    /* renamed from: a */
    public final boolean mo7836a(Class<?> cls) {
        return Set.class.isAssignableFrom(cls);
    }
}
