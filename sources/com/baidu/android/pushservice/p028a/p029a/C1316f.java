package com.baidu.android.pushservice.p028a.p029a;

import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.baidu.android.pushservice.a.a.f */
class C1316f implements C1308c {
    /* renamed from: a */
    private int f4676a;
    /* renamed from: b */
    private C1314b f4677b;
    /* renamed from: c */
    private Map<String, C1313a> f4678c;

    /* renamed from: com.baidu.android.pushservice.a.a.f$a */
    static class C1313a {
        /* renamed from: a */
        public Bitmap f4673a;
        /* renamed from: b */
        public int f4674b;
        /* renamed from: c */
        public long f4675c;

        C1313a() {
        }
    }

    /* renamed from: com.baidu.android.pushservice.a.a.f$b */
    public interface C1314b {
        /* renamed from: a */
        String mo13562a(Map<String, C1313a> map);

        /* renamed from: a */
        void mo13563a(C1313a c1313a);
    }

    /* renamed from: com.baidu.android.pushservice.a.a.f$c */
    public static class C1315c implements C1314b {
        /* renamed from: a */
        public String mo13562a(Map<String, C1313a> map) {
            String str = null;
            C1313a c1313a = null;
            for (String str2 : map.keySet()) {
                String str22;
                C1313a c1313a2 = (C1313a) map.get(str22);
                if (c1313a != null && c1313a2.f4675c >= c1313a.f4675c) {
                    str22 = str;
                    c1313a2 = c1313a;
                }
                str = str22;
                c1313a = c1313a2;
            }
            return str;
        }

        /* renamed from: a */
        public void mo13563a(C1313a c1313a) {
            c1313a.f4674b++;
            c1313a.f4675c = System.currentTimeMillis();
        }
    }

    public C1316f(int i) {
        this(i, null);
    }

    public C1316f(int i, C1314b c1314b) {
        this.f4676a = i;
        this.f4677b = c1314b;
        this.f4678c = new HashMap();
        if (this.f4677b == null) {
            this.f4677b = new C1315c();
        }
    }

    /* renamed from: a */
    public synchronized Bitmap mo13564a(String str) {
        Bitmap bitmap;
        C1313a c1313a = (C1313a) this.f4678c.get(str);
        if (c1313a != null) {
            this.f4677b.mo13563a(c1313a);
            bitmap = c1313a.f4673a;
        } else {
            bitmap = null;
        }
        return bitmap;
    }

    /* renamed from: a */
    public synchronized void mo13557a(String str, Bitmap bitmap) {
        if (!mo13566c(str)) {
            if (this.f4678c.size() >= this.f4676a) {
                mo13565b(this.f4677b.mo13562a(this.f4678c));
            }
            C1313a c1313a = new C1313a();
            c1313a.f4674b = 1;
            c1313a.f4675c = System.currentTimeMillis();
            c1313a.f4673a = bitmap;
            this.f4678c.put(str, c1313a);
        }
    }

    /* renamed from: b */
    public synchronized void mo13565b(String str) {
        C1313a c1313a = (C1313a) this.f4678c.remove(str);
        if (!(c1313a == null || c1313a.f4673a == null || c1313a.f4673a.isRecycled())) {
            c1313a.f4673a.recycle();
        }
    }

    /* renamed from: c */
    public synchronized boolean mo13566c(String str) {
        return this.f4678c.get(str) != null;
    }
}
