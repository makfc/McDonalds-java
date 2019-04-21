package com.aps;

/* renamed from: com.aps.p */
public class Reflect {
    /* renamed from: a */
    public static Object m5696a(Object obj, String str, Object... objArr) throws Exception {
        Class cls = obj.getClass();
        Class[] clsArr = new Class[objArr.length];
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return cls.getMethod(str, clsArr).invoke(obj, objArr);
    }

    /* renamed from: b */
    public static int m5697b(Object obj, String str, Object... objArr) throws Exception {
        Class cls = obj.getClass();
        Class[] clsArr = new Class[objArr.length];
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return ((Integer) cls.getMethod(str, clsArr).invoke(obj, objArr)).intValue();
    }
}
