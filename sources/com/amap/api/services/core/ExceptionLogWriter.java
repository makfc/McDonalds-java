package com.amap.api.services.core;

import java.util.List;

/* renamed from: com.amap.api.services.core.bd */
class ExceptionLogWriter extends LogWriter {
    /* renamed from: a */
    private C1104a f3717a;

    /* compiled from: ExceptionLogWriter */
    /* renamed from: com.amap.api.services.core.bd$a */
    class C1104a implements C1101bm {
        /* renamed from: b */
        private C1085aj f3716b;

        C1104a(C1085aj c1085aj) {
            this.f3716b = c1085aj;
        }

        /* renamed from: a */
        public void mo12046a(String str) {
            try {
                this.f3716b.mo12010b(str, ExceptionLogWriter.this.mo12047a());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    ExceptionLogWriter() {
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public int mo12047a() {
        return 1;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo12049a(String str) {
        return C1077aa.m4684b(str);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public String mo12056b() {
        return C1107be.f3725b;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public C1101bm mo12048a(C1085aj c1085aj) {
        try {
            if (this.f3717a == null) {
                this.f3717a = new C1104a(c1085aj);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.f3717a;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo12051a(List<C1081ac> list) {
        return null;
    }
}
