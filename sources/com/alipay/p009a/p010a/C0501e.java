package com.alipay.p009a.p010a;

import com.alipay.p009a.p011b.C0507a;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.alipay.C4634a;
import org.json.alipay.C4636b;

/* renamed from: com.alipay.a.a.e */
public final class C0501e {
    /* renamed from: a */
    static List<C0495i> f311a;

    static {
        ArrayList arrayList = new ArrayList();
        f311a = arrayList;
        arrayList.add(new C0506l());
        f311a.add(new C0500d());
        f311a.add(new C0499c());
        f311a.add(new C0504h());
        f311a.add(new C0505k());
        f311a.add(new C0498b());
        f311a.add(new C0497a());
        f311a.add(new C0503g());
    }

    /* renamed from: a */
    public static final <T> T m523a(Object obj, Type type) {
        for (C0495i c0495i : f311a) {
            if (c0495i.mo7836a(C0507a.m539a(type))) {
                Object a = c0495i.mo7835a(obj, type);
                if (a != null) {
                    return a;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static final Object m524a(String str, Type type) {
        if (str == null || str.length() == 0) {
            return null;
        }
        Object trim = str.trim();
        return (trim.startsWith("[") && trim.endsWith("]")) ? C0501e.m523a(new C4634a((String) trim), type) : (trim.startsWith("{") && trim.endsWith("}")) ? C0501e.m523a(new C4636b((String) trim), type) : C0501e.m523a(trim, type);
    }
}
