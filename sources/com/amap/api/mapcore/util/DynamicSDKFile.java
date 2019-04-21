package com.amap.api.mapcore.util;

import java.util.HashMap;
import java.util.Map;

@EntityClass(a = "file")
/* renamed from: com.amap.api.mapcore.util.fd */
public class DynamicSDKFile {
    @EntityField(a = "filename", b = 6)
    /* renamed from: a */
    private String f1930a;
    @EntityField(a = "md5", b = 6)
    /* renamed from: b */
    private String f1931b;
    @EntityField(a = "sdkname", b = 6)
    /* renamed from: c */
    private String f1932c;
    @EntityField(a = "version", b = 6)
    /* renamed from: d */
    private String f1933d;
    @EntityField(a = "dynamicversion", b = 6)
    /* renamed from: e */
    private String f1934e;
    @EntityField(a = "status", b = 6)
    /* renamed from: f */
    private String f1935f;

    /* compiled from: DynamicSDKFile */
    /* renamed from: com.amap.api.mapcore.util.fd$a */
    public static class C0835a {
        /* renamed from: a */
        private String f1924a;
        /* renamed from: b */
        private String f1925b;
        /* renamed from: c */
        private String f1926c;
        /* renamed from: d */
        private String f1927d;
        /* renamed from: e */
        private String f1928e;
        /* renamed from: f */
        private String f1929f = "copy";

        public C0835a(String str, String str2, String str3, String str4, String str5) {
            this.f1924a = str;
            this.f1925b = str2;
            this.f1926c = str3;
            this.f1927d = str4;
            this.f1928e = str5;
        }

        /* renamed from: a */
        public C0835a mo9371a(String str) {
            this.f1929f = str;
            return this;
        }

        /* renamed from: a */
        public DynamicSDKFile mo9372a() {
            return new DynamicSDKFile(this, null);
        }
    }

    /* synthetic */ DynamicSDKFile(C0835a c0835a, C0838fj c0838fj) {
        this(c0835a);
    }

    private DynamicSDKFile(C0835a c0835a) {
        this.f1930a = c0835a.f1924a;
        this.f1931b = c0835a.f1925b;
        this.f1932c = c0835a.f1926c;
        this.f1933d = c0835a.f1927d;
        this.f1934e = c0835a.f1928e;
        this.f1935f = c0835a.f1929f;
    }

    private DynamicSDKFile() {
    }

    /* renamed from: a */
    public static String m2720a(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("sdkname", str);
        hashMap.put("dynamicversion", str2);
        return DBOperation.m2617a(hashMap);
    }

    /* renamed from: b */
    public static String m2723b(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("sdkname", str);
        hashMap.put("status", str2);
        return DBOperation.m2617a(hashMap);
    }

    /* renamed from: a */
    public static String m2719a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("sdkname", str);
        return DBOperation.m2617a(hashMap);
    }

    /* renamed from: b */
    public static String m2722b(String str) {
        Map hashMap = new HashMap();
        hashMap.put("filename", str);
        return DBOperation.m2617a(hashMap);
    }

    /* renamed from: a */
    public static String m2721a(String str, String str2, String str3, String str4) {
        Map hashMap = new HashMap();
        hashMap.put("filename", str);
        hashMap.put("sdkname", str2);
        hashMap.put("dynamicversion", str4);
        hashMap.put("version", str3);
        return DBOperation.m2617a(hashMap);
    }

    /* renamed from: a */
    public String mo9373a() {
        return this.f1930a;
    }

    /* renamed from: b */
    public String mo9374b() {
        return this.f1931b;
    }

    /* renamed from: c */
    public String mo9375c() {
        return this.f1933d;
    }

    /* renamed from: d */
    public String mo9377d() {
        return this.f1934e;
    }

    /* renamed from: e */
    public String mo9378e() {
        return this.f1935f;
    }

    /* renamed from: c */
    public void mo9376c(String str) {
        this.f1935f = str;
    }
}
