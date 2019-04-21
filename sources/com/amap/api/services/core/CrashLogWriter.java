package com.amap.api.services.core;

import java.util.Date;
import java.util.List;

/* renamed from: com.amap.api.services.core.bb */
class CrashLogWriter extends LogWriter {
    /* renamed from: a */
    private C1103a f3713a;

    /* compiled from: CrashLogWriter */
    /* renamed from: com.amap.api.services.core.bb$a */
    class C1103a implements C1101bm {
        /* renamed from: b */
        private C1085aj f3712b;

        C1103a(C1085aj c1085aj) {
            this.f3712b = c1085aj;
        }

        /* renamed from: a */
        public void mo12046a(String str) {
            try {
                this.f3712b.mo12010b(str, CrashLogWriter.this.mo12047a());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    CrashLogWriter() {
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public int mo12047a() {
        return 0;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo12049a(String str) {
        return C1077aa.m4684b(str + C1109bi.m4885a(new Date().getTime()));
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public String mo12056b() {
        return C1107be.f3726c;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public C1101bm mo12048a(C1085aj c1085aj) {
        try {
            if (this.f3713a == null) {
                this.f3713a = new C1103a(c1085aj);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.f3713a;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo12051a(List<C1081ac> list) {
        return null;
    }
}
