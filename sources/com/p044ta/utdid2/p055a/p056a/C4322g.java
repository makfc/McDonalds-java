package com.p044ta.utdid2.p055a.p056a;

/* renamed from: com.ta.utdid2.a.a.g */
public class C4322g {
    public static String get(String str, String str2) {
        String str3 = "";
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls.newInstance(), new Object[]{str, str2});
        } catch (Exception e) {
            return str3;
        }
    }
}
