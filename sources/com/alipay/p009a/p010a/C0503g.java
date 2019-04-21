package com.alipay.p009a.p010a;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.TreeMap;
import org.json.alipay.C4636b;

/* renamed from: com.alipay.a.a.g */
public final class C0503g implements C0495i, C0496j {
    /* renamed from: a */
    public final Object mo7837a(Object obj) {
        TreeMap treeMap = new TreeMap();
        Object obj2 = obj.getClass();
        Field[] declaredFields = obj2.getDeclaredFields();
        while (!obj2.equals(Object.class)) {
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    Object obj3;
                    if (field == null || obj == null) {
                        obj3 = null;
                    } else {
                        if ("this$0".equals(field.getName())) {
                            obj3 = null;
                        } else {
                            boolean isAccessible = field.isAccessible();
                            field.setAccessible(true);
                            Object obj4 = field.get(obj);
                            if (obj4 == null) {
                                obj3 = null;
                            } else {
                                field.setAccessible(isAccessible);
                                obj3 = C0502f.m526b(obj4);
                            }
                        }
                    }
                    if (obj3 != null) {
                        treeMap.put(field.getName(), obj3);
                    }
                }
            }
            obj2 = obj2.getSuperclass();
            declaredFields = obj2.getDeclaredFields();
        }
        return treeMap;
    }

    /* renamed from: a */
    public final Object mo7835a(Object obj, Type type) {
        if (!obj.getClass().equals(C4636b.class)) {
            return null;
        }
        C4636b c4636b = (C4636b) obj;
        Object obj2 = (Class) type;
        Object newInstance = obj2.newInstance();
        while (!obj2.equals(Object.class)) {
            Field[] declaredFields = obj2.getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    String name = field.getName();
                    Type genericType = field.getGenericType();
                    if (c4636b.mo34959b(name)) {
                        field.setAccessible(true);
                        field.set(newInstance, C0501e.m523a(c4636b.mo34957a(name), genericType));
                    }
                }
            }
            obj2 = obj2.getSuperclass();
        }
        return newInstance;
    }

    /* renamed from: a */
    public final boolean mo7836a(Class<?> cls) {
        return true;
    }
}
