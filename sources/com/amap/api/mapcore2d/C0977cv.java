package com.amap.api.mapcore2d;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

@C0999dk(a = "a")
/* compiled from: SDKInfo */
/* renamed from: com.amap.api.mapcore2d.cv */
public class C0977cv {
    @C1000dl(a = "a1", b = 6)
    /* renamed from: a */
    private String f2782a;
    @C1000dl(a = "a2", b = 6)
    /* renamed from: b */
    private String f2783b;
    @C1000dl(a = "a6", b = 2)
    /* renamed from: c */
    private int f2784c;
    @C1000dl(a = "a3", b = 6)
    /* renamed from: d */
    private String f2785d;
    @C1000dl(a = "a4", b = 6)
    /* renamed from: e */
    private String f2786e;
    @C1000dl(a = "a5", b = 6)
    /* renamed from: f */
    private String f2787f;
    /* renamed from: g */
    private String f2788g;
    /* renamed from: h */
    private String f2789h;
    /* renamed from: i */
    private String f2790i;
    /* renamed from: j */
    private String f2791j;
    /* renamed from: k */
    private String[] f2792k;

    /* compiled from: SDKInfo */
    /* renamed from: com.amap.api.mapcore2d.cv$a */
    public static class C0976a {
        /* renamed from: a */
        private String f2776a;
        /* renamed from: b */
        private String f2777b;
        /* renamed from: c */
        private String f2778c;
        /* renamed from: d */
        private boolean f2779d = true;
        /* renamed from: e */
        private String f2780e = "standard";
        /* renamed from: f */
        private String[] f2781f = null;

        public C0976a(String str, String str2, String str3) {
            this.f2776a = str2;
            this.f2778c = str3;
            this.f2777b = str;
        }

        /* renamed from: a */
        public C0976a mo10170a(String[] strArr) {
            this.f2781f = (String[]) strArr.clone();
            return this;
        }

        /* renamed from: a */
        public C0977cv mo10171a() throws C0956cl {
            if (this.f2781f != null) {
                return new C0977cv(this);
            }
            throw new C0956cl("sdk packages is null");
        }
    }

    private C0977cv() {
        this.f2784c = 1;
        this.f2792k = null;
    }

    private C0977cv(C0976a c0976a) {
        int i = 1;
        this.f2784c = 1;
        this.f2792k = null;
        this.f2788g = c0976a.f2776a;
        this.f2790i = c0976a.f2777b;
        this.f2789h = c0976a.f2778c;
        if (!c0976a.f2779d) {
            i = 0;
        }
        this.f2784c = i;
        this.f2791j = c0976a.f2780e;
        this.f2792k = c0976a.f2781f;
        this.f2783b = C0978cw.m4051b(this.f2788g);
        this.f2782a = C0978cw.m4051b(this.f2790i);
        this.f2785d = C0978cw.m4051b(this.f2789h);
        this.f2786e = C0978cw.m4051b(m4032a(this.f2792k));
        this.f2787f = C0978cw.m4051b(this.f2791j);
    }

    /* renamed from: a */
    public void mo10173a(boolean z) {
        this.f2784c = z ? 1 : 0;
    }

    /* renamed from: a */
    public String mo10172a() {
        if (TextUtils.isEmpty(this.f2790i) && !TextUtils.isEmpty(this.f2782a)) {
            this.f2790i = C0978cw.m4053c(this.f2782a);
        }
        return this.f2790i;
    }

    /* renamed from: b */
    public String mo10174b() {
        if (TextUtils.isEmpty(this.f2788g) && !TextUtils.isEmpty(this.f2783b)) {
            this.f2788g = C0978cw.m4053c(this.f2783b);
        }
        return this.f2788g;
    }

    /* renamed from: c */
    public String mo10175c() {
        if (TextUtils.isEmpty(this.f2789h) && !TextUtils.isEmpty(this.f2785d)) {
            this.f2789h = C0978cw.m4053c(this.f2785d);
        }
        return this.f2789h;
    }

    /* renamed from: d */
    public String mo10176d() {
        if (TextUtils.isEmpty(this.f2791j) && !TextUtils.isEmpty(this.f2787f)) {
            this.f2791j = C0978cw.m4053c(this.f2787f);
        }
        if (TextUtils.isEmpty(this.f2791j)) {
            this.f2791j = "standard";
        }
        return this.f2791j;
    }

    /* renamed from: e */
    public String[] mo10177e() {
        if ((this.f2792k == null || this.f2792k.length == 0) && !TextUtils.isEmpty(this.f2786e)) {
            this.f2792k = m4033b(C0978cw.m4053c(this.f2786e));
        }
        return (String[]) this.f2792k.clone();
    }

    /* renamed from: b */
    private String[] m4033b(String str) {
        try {
            return str.split(";");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private String m4032a(String[] strArr) {
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
    public static String m4031a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("a1", C0978cw.m4051b(str));
        return C0998dj.m4155a(hashMap);
    }

    /* renamed from: f */
    public static String m4034f() {
        return "a6=1";
    }
}
