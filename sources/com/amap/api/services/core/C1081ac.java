package com.amap.api.services.core;

/* compiled from: SDKInfo */
/* renamed from: com.amap.api.services.core.ac */
public class C1081ac {
    /* renamed from: a */
    String f3623a;
    /* renamed from: b */
    String f3624b;
    /* renamed from: c */
    String f3625c;
    /* renamed from: d */
    private boolean f3626d;
    /* renamed from: e */
    private String f3627e;
    /* renamed from: f */
    private String[] f3628f;

    /* compiled from: SDKInfo */
    /* renamed from: com.amap.api.services.core.ac$a */
    public static class C1080a {
        /* renamed from: a */
        private String f3617a;
        /* renamed from: b */
        private String f3618b;
        /* renamed from: c */
        private String f3619c;
        /* renamed from: d */
        private boolean f3620d = true;
        /* renamed from: e */
        private String f3621e = "standard";
        /* renamed from: f */
        private String[] f3622f = null;

        public C1080a(String str, String str2, String str3) {
            this.f3617a = str2;
            this.f3619c = str3;
            this.f3618b = str;
        }

        /* renamed from: a */
        public C1080a mo11985a(boolean z) {
            this.f3620d = z;
            return this;
        }

        /* renamed from: a */
        public C1080a mo11984a(String str) {
            this.f3621e = str;
            return this;
        }

        /* renamed from: a */
        public C1080a mo11986a(String[] strArr) {
            this.f3622f = (String[]) strArr.clone();
            return this;
        }

        /* renamed from: a */
        public C1081ac mo11987a() throws C1133u {
            if (this.f3622f != null) {
                return new C1081ac(this);
            }
            throw new C1133u("sdk packages is null");
        }
    }

    private C1081ac(C1080a c1080a) {
        this.f3626d = true;
        this.f3627e = "standard";
        this.f3628f = null;
        this.f3623a = c1080a.f3617a;
        this.f3625c = c1080a.f3618b;
        this.f3624b = c1080a.f3619c;
        this.f3626d = c1080a.f3620d;
        this.f3627e = c1080a.f3621e;
        this.f3628f = c1080a.f3622f;
    }

    /* renamed from: a */
    public String mo11988a() {
        return this.f3625c;
    }

    /* renamed from: b */
    public String mo11989b() {
        return this.f3623a;
    }

    /* renamed from: c */
    public String mo11990c() {
        return this.f3624b;
    }

    /* renamed from: d */
    public String mo11991d() {
        return this.f3627e;
    }

    /* renamed from: e */
    public boolean mo11992e() {
        return this.f3626d;
    }

    /* renamed from: f */
    public String[] mo11993f() {
        return (String[]) this.f3628f.clone();
    }
}
