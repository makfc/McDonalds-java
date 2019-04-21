package com.amap.api.mapcore.util;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.amap.api.mapcore.util.et */
public abstract class LogInfo {
    @EntityField(a = "b2", b = 2)
    /* renamed from: a */
    protected int f1884a = -1;
    @EntityField(a = "b1", b = 6)
    /* renamed from: b */
    protected String f1885b;
    @EntityField(a = "b3", b = 2)
    /* renamed from: c */
    protected int f1886c = 1;
    @EntityField(a = "a1", b = 6)
    /* renamed from: d */
    private String f1887d;

    /* renamed from: a */
    public int mo9337a() {
        return this.f1884a;
    }

    /* renamed from: a */
    public void mo9338a(int i) {
        this.f1884a = i;
    }

    /* renamed from: b */
    public String mo9340b() {
        return this.f1885b;
    }

    /* renamed from: a */
    public void mo9339a(String str) {
        this.f1885b = str;
    }

    /* renamed from: b */
    public void mo9342b(String str) {
        this.f1887d = Utils.m2516b(str);
    }

    /* renamed from: c */
    public int mo9343c() {
        return this.f1886c;
    }

    /* renamed from: b */
    public void mo9341b(int i) {
        this.f1886c = i;
    }

    /* renamed from: c */
    public static String m2638c(String str) {
        Map hashMap = new HashMap();
        hashMap.put("b1", str);
        return DBOperation.m2617a(hashMap);
    }

    /* renamed from: c */
    public static String m2637c(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("b2").append("=").append(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
