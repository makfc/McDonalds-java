package com.alipay.p009a.p010a;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.json.alipay.C4634a;

/* renamed from: com.alipay.a.a.a */
public final class C0497a implements C0495i, C0496j {
    /* renamed from: a */
    public final Object mo7837a(Object obj) {
        Object[] objArr = (Object[]) obj;
        ArrayList arrayList = new ArrayList();
        for (Object b : objArr) {
            arrayList.add(C0502f.m526b(b));
        }
        return arrayList;
    }

    /* renamed from: a */
    public final Object mo7835a(Object obj, Type type) {
        if (!obj.getClass().equals(C4634a.class)) {
            return null;
        }
        C4634a c4634a = (C4634a) obj;
        if (type instanceof GenericArrayType) {
            throw new IllegalArgumentException("Does not support generic array!");
        }
        Type componentType = ((Class) type).getComponentType();
        int a = c4634a.mo34951a();
        Object newInstance = Array.newInstance(componentType, a);
        for (int i = 0; i < a; i++) {
            Array.set(newInstance, i, C0501e.m523a(c4634a.mo34952a(i), componentType));
        }
        return newInstance;
    }

    /* renamed from: a */
    public final boolean mo7836a(Class<?> cls) {
        return cls.isArray();
    }
}
