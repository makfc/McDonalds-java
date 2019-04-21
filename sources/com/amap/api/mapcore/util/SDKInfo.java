package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

@EntityClass(a = "a")
/* renamed from: com.amap.api.mapcore.util.dv */
public class SDKInfo {
    @EntityField(a = "a1", b = 6)
    /* renamed from: a */
    private String f1826a;
    @EntityField(a = "a2", b = 6)
    /* renamed from: b */
    private String f1827b;
    @EntityField(a = "a6", b = 2)
    /* renamed from: c */
    private int f1828c;
    @EntityField(a = "a3", b = 6)
    /* renamed from: d */
    private String f1829d;
    @EntityField(a = "a4", b = 6)
    /* renamed from: e */
    private String f1830e;
    @EntityField(a = "a5", b = 6)
    /* renamed from: f */
    private String f1831f;
    /* renamed from: g */
    private String f1832g;
    /* renamed from: h */
    private String f1833h;
    /* renamed from: i */
    private String f1834i;
    /* renamed from: j */
    private String f1835j;
    /* renamed from: k */
    private String[] f1836k;

    /* compiled from: SDKInfo */
    /* renamed from: com.amap.api.mapcore.util.dv$a */
    public static class C0824a {
        /* renamed from: a */
        private String f1820a;
        /* renamed from: b */
        private String f1821b;
        /* renamed from: c */
        private String f1822c;
        /* renamed from: d */
        private boolean f1823d = true;
        /* renamed from: e */
        private String f1824e = "standard";
        /* renamed from: f */
        private String[] f1825f = null;

        public C0824a(String str, String str2, String str3) {
            this.f1820a = str2;
            this.f1822c = str3;
            this.f1821b = str;
        }

        /* renamed from: a */
        public C0824a mo9290a(String[] strArr) {
            this.f1825f = (String[]) strArr.clone();
            return this;
        }

        /* renamed from: a */
        public SDKInfo mo9291a() throws AMapCoreException {
            if (this.f1825f != null) {
                return new SDKInfo(this, null);
            }
            throw new AMapCoreException("sdk packages is null");
        }
    }

    /* synthetic */ SDKInfo(C0824a c0824a, C0825dw c0825dw) {
        this(c0824a);
    }

    private SDKInfo() {
        this.f1828c = 1;
        this.f1836k = null;
    }

    private SDKInfo(C0824a c0824a) {
        int i = 1;
        this.f1828c = 1;
        this.f1836k = null;
        this.f1832g = c0824a.f1820a;
        this.f1834i = c0824a.f1821b;
        this.f1833h = c0824a.f1822c;
        if (!c0824a.f1823d) {
            i = 0;
        }
        this.f1828c = i;
        this.f1835j = c0824a.f1824e;
        this.f1836k = c0824a.f1825f;
        this.f1827b = Utils.m2516b(this.f1832g);
        this.f1826a = Utils.m2516b(this.f1834i);
        this.f1829d = Utils.m2516b(this.f1833h);
        this.f1830e = Utils.m2516b(m2497a(this.f1836k));
        this.f1831f = Utils.m2516b(this.f1835j);
    }

    /* renamed from: a */
    public void mo9293a(boolean z) {
        this.f1828c = z ? 1 : 0;
    }

    /* renamed from: a */
    public String mo9292a() {
        if (TextUtils.isEmpty(this.f1834i) && !TextUtils.isEmpty(this.f1826a)) {
            this.f1834i = Utils.m2519c(this.f1826a);
        }
        return this.f1834i;
    }

    /* renamed from: b */
    public String mo9294b() {
        if (TextUtils.isEmpty(this.f1832g) && !TextUtils.isEmpty(this.f1827b)) {
            this.f1832g = Utils.m2519c(this.f1827b);
        }
        return this.f1832g;
    }

    /* renamed from: c */
    public String mo9295c() {
        if (TextUtils.isEmpty(this.f1833h) && !TextUtils.isEmpty(this.f1829d)) {
            this.f1833h = Utils.m2519c(this.f1829d);
        }
        return this.f1833h;
    }

    /* renamed from: d */
    public String mo9296d() {
        if (TextUtils.isEmpty(this.f1835j) && !TextUtils.isEmpty(this.f1831f)) {
            this.f1835j = Utils.m2519c(this.f1831f);
        }
        if (TextUtils.isEmpty(this.f1835j)) {
            this.f1835j = "standard";
        }
        return this.f1835j;
    }

    /* renamed from: e */
    public String[] mo9297e() {
        if ((this.f1836k == null || this.f1836k.length == 0) && !TextUtils.isEmpty(this.f1830e)) {
            this.f1836k = m2498b(Utils.m2519c(this.f1830e));
        }
        return (String[]) this.f1836k.clone();
    }

    /* renamed from: b */
    private String[] m2498b(String str) {
        try {
            return str.split(";");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private String m2497a(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (String append : strArr) {
                stringBuilder.append(append).append(";");
            }
            return stringBuilder.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m2496a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("a1", Utils.m2516b(str));
        return DBOperation.m2617a(hashMap);
    }

    /* renamed from: f */
    public static String m2499f() {
        return "a6=1";
    }
}
