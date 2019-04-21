package com.tencent.p059a.p060a.p061a.p062a;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

/* renamed from: com.tencent.a.a.a.a.d */
final class C4341d extends C4338f {
    public C4341d(Context context) {
        super(context);
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final void mo33757a(String str) {
        synchronized (this) {
            Log.i("MID", "write mid to sharedPreferences");
            Editor edit = PreferenceManager.getDefaultSharedPreferences(this.f6769a).edit();
            edit.putString(C4344h.m7876f("4kU71lN96TJUomD1vOU9lgj9Tw=="), str);
            edit.commit();
        }
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final boolean mo33758a() {
        return true;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: b */
    public final String mo33759b() {
        String string;
        synchronized (this) {
            Log.i("MID", "read mid from sharedPreferences");
            string = PreferenceManager.getDefaultSharedPreferences(this.f6769a).getString(C4344h.m7876f("4kU71lN96TJUomD1vOU9lgj9Tw=="), null);
        }
        return string;
    }
}
