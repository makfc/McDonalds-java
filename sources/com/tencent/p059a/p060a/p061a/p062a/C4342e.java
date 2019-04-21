package com.tencent.p059a.p060a.p061a.p062a;

import android.content.Context;
import android.provider.Settings.System;
import android.util.Log;

/* renamed from: com.tencent.a.a.a.a.e */
public final class C4342e extends C4338f {
    public C4342e(Context context) {
        super(context);
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final void mo33757a(String str) {
        synchronized (this) {
            Log.i("MID", "write mid to Settings.System");
            System.putString(this.f6769a.getContentResolver(), C4344h.m7876f("4kU71lN96TJUomD1vOU9lgj9Tw=="), str);
        }
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final boolean mo33758a() {
        return C4344h.m7872a(this.f6769a, "android.permission.WRITE_SETTINGS");
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: b */
    public final String mo33759b() {
        String string;
        synchronized (this) {
            Log.i("MID", "read mid from Settings.System");
            string = System.getString(this.f6769a.getContentResolver(), C4344h.m7876f("4kU71lN96TJUomD1vOU9lgj9Tw=="));
        }
        return string;
    }
}
