package com.alipay.p009a.p010a;

import com.alipay.p009a.p011b.C0507a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.json.alipay.C4634a;
import org.json.alipay.C4636b;

/* renamed from: com.alipay.a.a.f */
public final class C0502f {
    /* renamed from: a */
    private static List<C0496j> f312a;

    static {
        ArrayList arrayList = new ArrayList();
        f312a = arrayList;
        arrayList.add(new C0506l());
        f312a.add(new C0500d());
        f312a.add(new C0499c());
        f312a.add(new C0504h());
        f312a.add(new C0498b());
        f312a.add(new C0497a());
        f312a.add(new C0503g());
    }

    /* renamed from: a */
    public static String m525a(Object obj) {
        if (obj == null) {
            return null;
        }
        Object b = C0502f.m526b(obj);
        if (C0507a.m540a(b.getClass())) {
            return C4636b.m8651c(b.toString());
        }
        if (Collection.class.isAssignableFrom(b.getClass())) {
            return new C4634a((List) b).toString();
        }
        if (Map.class.isAssignableFrom(b.getClass())) {
            return new C4636b((Map) b).toString();
        }
        throw new IllegalArgumentException("Unsupported Class : " + b.getClass());
    }

    /* renamed from: b */
    public static Object m526b(Object obj) {
        if (obj == null) {
            return null;
        }
        for (C0496j c0496j : f312a) {
            if (c0496j.mo7838a(obj.getClass())) {
                Object a = c0496j.mo7837a(obj);
                if (a != null) {
                    return a;
                }
            }
        }
        throw new IllegalArgumentException("Unsupported Class : " + obj.getClass());
    }
}
