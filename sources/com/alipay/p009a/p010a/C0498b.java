package com.alipay.p009a.p010a;

import com.alipay.p009a.p011b.C0507a;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import org.json.alipay.C4634a;

/* renamed from: com.alipay.a.a.b */
public final class C0498b implements C0495i, C0496j {
    /* renamed from: a */
    private static Collection<Object> m513a(Class<?> cls, Type type) {
        if (cls == AbstractCollection.class) {
            return new ArrayList();
        }
        if (cls.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (cls.isAssignableFrom(LinkedHashSet.class)) {
            return new LinkedHashSet();
        }
        if (cls.isAssignableFrom(TreeSet.class)) {
            return new TreeSet();
        }
        if (cls.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        if (cls.isAssignableFrom(EnumSet.class)) {
            return EnumSet.noneOf(type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : Object.class);
        }
        try {
            return (Collection) cls.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("create instane error, class " + cls.getName());
        }
    }

    /* renamed from: a */
    public final Object mo7837a(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Object b : (Iterable) obj) {
            arrayList.add(C0502f.m526b(b));
        }
        return arrayList;
    }

    /* renamed from: a */
    public final Object mo7835a(Object obj, Type type) {
        int i = 0;
        if (!obj.getClass().equals(C4634a.class)) {
            return null;
        }
        C4634a c4634a = (C4634a) obj;
        Collection a = C0498b.m513a(C0507a.m539a(type), type);
        if (type instanceof ParameterizedType) {
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            while (i < c4634a.mo34951a()) {
                a.add(C0501e.m523a(c4634a.mo34952a(i), type2));
                i++;
            }
            return a;
        }
        throw new IllegalArgumentException("Does not support the implement for generics.");
    }

    /* renamed from: a */
    public final boolean mo7836a(Class<?> cls) {
        return Collection.class.isAssignableFrom(cls);
    }
}
