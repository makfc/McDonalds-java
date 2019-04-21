package com.tencent.p050mm.sdk.p066b;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.tencent.p050mm.sdk.p066b.C4353b.C4352a;

/* renamed from: com.tencent.mm.sdk.b.c */
final class C4354c implements C4352a {
    private Handler handler = new Handler(Looper.getMainLooper());

    C4354c() {
    }

    /* renamed from: f */
    public final void mo33766f(String str, String str2) {
        if (C4353b.level <= 2) {
            Log.i(str, str2);
        }
    }

    /* renamed from: g */
    public final void mo33767g(String str, String str2) {
        if (C4353b.level <= 1) {
            Log.d(str, str2);
        }
    }

    public final int getLogLevel() {
        return C4353b.level;
    }

    /* renamed from: h */
    public final void mo33769h(String str, String str2) {
        if (C4353b.level <= 3) {
            Log.w(str, str2);
        }
    }

    /* renamed from: i */
    public final void mo33770i(String str, String str2) {
        if (C4353b.level <= 4) {
            Log.e(str, str2);
        }
    }
}
