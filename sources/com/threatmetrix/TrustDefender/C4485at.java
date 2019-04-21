package com.threatmetrix.TrustDefender;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.threatmetrix.TrustDefender.at */
class C4485at {
    /* renamed from: a */
    private static final String f7366a = C4549w.m8585a(C4485at.class);

    C4485at() {
    }

    /* renamed from: a */
    static Method m8325a(Class clazz, String methodName, Class... args) {
        if (clazz == null) {
            return null;
        }
        Method method = null;
        try {
            return clazz.getMethod(methodName, args);
        } catch (Throwable th) {
            String str = f7366a;
            return method;
        }
    }

    /* renamed from: b */
    static Method m8328b(Class clazz, String methodName, Class... args) {
        if (clazz == null) {
            return null;
        }
        Method method = null;
        try {
            return clazz.getDeclaredMethod(methodName, args);
        } catch (Throwable th) {
            String str = f7366a;
            return method;
        }
    }

    /* renamed from: a */
    static <T> T m8323a(Object receiverInstance, Method method, Object... args) {
        T returnValue = null;
        if (method != null) {
            boolean invokeFailed = false;
            try {
                returnValue = method.invoke(receiverInstance, args);
            } catch (Throwable th) {
                invokeFailed = true;
                String str = f7366a;
                method.getName();
            }
            if (!invokeFailed) {
                return returnValue;
            }
        }
        return null;
    }

    /* renamed from: b */
    static Class m8327b(String className) {
        Class<?> c = null;
        try {
            return Class.forName(className);
        } catch (Throwable th) {
            String str = f7366a;
            return c;
        }
    }

    /* renamed from: a */
    static Field m8324a(Class clazz, String fieldName) {
        if (clazz == null) {
            return null;
        }
        Field field = null;
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (Throwable th) {
            String str = f7366a;
            return field;
        }
    }

    /* renamed from: a */
    static List<String> m8326a(Class clazz) {
        String str;
        if (clazz == null) {
            return null;
        }
        List<String> list = null;
        try {
            Field[] fields = clazz.getDeclaredFields();
            if (fields == null || fields.length == 0) {
                return null;
            }
            List<String> ret = new ArrayList();
            int i = 0;
            while (i < fields.length) {
                try {
                    if (fields[i] != null) {
                        ret.add(fields[i].getName());
                    }
                    i++;
                } catch (Throwable th) {
                    list = ret;
                    str = f7366a;
                    return list;
                }
            }
            return ret;
        } catch (Throwable th2) {
            str = f7366a;
            return list;
        }
    }

    /* renamed from: a */
    static Object m8322a(Object instance, Field field) {
        if (field == null) {
            return null;
        }
        Object obj = null;
        try {
            return field.get(instance);
        } catch (Throwable th) {
            String str = f7366a;
            field.getName();
            return obj;
        }
    }

    /* renamed from: a */
    static Object m8320a(Class clazz, Class[] argsType, Object[] args) {
        Object obj = null;
        if (clazz == null) {
            return obj;
        }
        if (argsType != null && args == null) {
            return obj;
        }
        if (argsType == null && args != null) {
            return obj;
        }
        if (argsType != null && args != null && argsType.length != args.length) {
            return obj;
        }
        try {
            return clazz.getConstructor(argsType).newInstance(args);
        } catch (Throwable th) {
            String str = f7366a;
            return obj;
        }
    }

    /* renamed from: a */
    static Object m8321a(ClassLoader loader, Class<?>[] interfaces, InvocationHandler invocationHandler) {
        try {
            return Proxy.newProxyInstance(loader, interfaces, invocationHandler);
        } catch (Throwable th) {
            String str = f7366a;
            return null;
        }
    }

    /* renamed from: a */
    static Object m8319a(Class clazz, String fieldName, Object instance) {
        Field field = C4485at.m8324a(clazz, fieldName);
        if (field != null) {
            return C4485at.m8322a(null, field);
        }
        return null;
    }
}
