package com.alipay.p009a.p011b;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* renamed from: com.alipay.a.b.a */
public final class C0507a {
    /* renamed from: a */
    public static Class<?> m539a(Type type) {
        Type type2 = type;
        while (!(type2 instanceof Class)) {
            if (type2 instanceof ParameterizedType) {
                type2 = ((ParameterizedType) type2).getRawType();
            } else {
                throw new IllegalArgumentException("TODO");
            }
        }
        return (Class) type2;
    }

    /* renamed from: a */
    public static boolean m540a(Class<?> cls) {
        return cls.isPrimitive() || cls.equals(String.class) || cls.equals(Integer.class) || cls.equals(Long.class) || cls.equals(Double.class) || cls.equals(Float.class) || cls.equals(Boolean.class) || cls.equals(Short.class) || cls.equals(Character.class) || cls.equals(Byte.class) || cls.equals(Void.class);
    }
}
