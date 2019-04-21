package com.tencent.p059a.p060a.p061a.p062a;

import android.content.Context;

/* renamed from: com.tencent.a.a.a.a.f */
public abstract class C4338f {
    /* renamed from: a */
    protected Context f6769a = null;

    protected C4338f(Context context) {
        this.f6769a = context;
    }

    /* renamed from: a */
    public final void mo33756a(C4340c c4340c) {
        if (c4340c != null) {
            String c4340c2 = c4340c.toString();
            if (mo33758a()) {
                mo33757a(C4344h.m7877g(c4340c2));
            }
        }
    }

    /* renamed from: a */
    public abstract void mo33757a(String str);

    /* renamed from: a */
    public abstract boolean mo33758a();

    /* renamed from: b */
    public abstract String mo33759b();

    /* renamed from: o */
    public final C4340c mo33760o() {
        String f = mo33758a() ? C4344h.m7876f(mo33759b()) : null;
        return f != null ? C4340c.m7856e(f) : null;
    }
}
