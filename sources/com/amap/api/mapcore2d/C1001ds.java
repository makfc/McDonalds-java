package com.amap.api.mapcore2d;

import java.util.HashMap;
import java.util.Map;

/* compiled from: LogInfo */
/* renamed from: com.amap.api.mapcore2d.ds */
public abstract class C1001ds {
    @C1000dl(a = "b2", b = 2)
    /* renamed from: a */
    protected int f2839a = -1;
    @C1000dl(a = "b1", b = 6)
    /* renamed from: b */
    protected String f2840b;
    @C1000dl(a = "b3", b = 2)
    /* renamed from: c */
    protected int f2841c = 1;
    @C1000dl(a = "a1", b = 6)
    /* renamed from: d */
    private String f2842d;

    /* renamed from: a */
    public int mo10218a() {
        return this.f2839a;
    }

    /* renamed from: a */
    public void mo10219a(int i) {
        this.f2839a = i;
    }

    /* renamed from: b */
    public String mo10221b() {
        return this.f2840b;
    }

    /* renamed from: a */
    public void mo10220a(String str) {
        this.f2840b = str;
    }

    /* renamed from: b */
    public void mo10223b(String str) {
        this.f2842d = C0978cw.m4051b(str);
    }

    /* renamed from: c */
    public int mo10224c() {
        return this.f2841c;
    }

    /* renamed from: b */
    public void mo10222b(int i) {
        this.f2841c = i;
    }

    /* renamed from: c */
    public static String m4174c(String str) {
        Map hashMap = new HashMap();
        hashMap.put("b1", str);
        return C0998dj.m4155a(hashMap);
    }

    /* renamed from: c */
    public static String m4173c(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("b2").append("=").append(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
