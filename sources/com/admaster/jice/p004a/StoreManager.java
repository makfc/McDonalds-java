package com.admaster.jice.p004a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/* renamed from: com.admaster.jice.a.x */
public class StoreManager {
    /* renamed from: a */
    private static volatile StoreManager f96a = null;
    /* renamed from: b */
    private final Context f97b;
    /* renamed from: c */
    private SharedPreferences f98c = null;

    private StoreManager(Context context) {
        if (context == null) {
            throw new NullPointerException("StoreManager context can`t be null!");
        }
        this.f97b = context;
        this.f98c = this.f97b.getSharedPreferences("com.admaster.jice.store", 0);
    }

    /* renamed from: a */
    public static StoreManager m130a(Context context) {
        if (f96a == null) {
            synchronized (StoreManager.class) {
                if (f96a == null) {
                    f96a = new StoreManager(context);
                }
            }
        }
        return f96a;
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* renamed from: a */
    public synchronized void mo7636a(String str) {
        try {
            Editor edit = this.f98c.edit();
            edit.putString("02", str);
            edit.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* renamed from: a */
    public synchronized C0472y mo7634a() {
        return C0472y.valueOf(this.f98c.getInt("03", -1));
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* renamed from: a */
    public synchronized void mo7635a(C0472y c0472y) {
        Editor edit = this.f98c.edit();
        edit.putInt("03", c0472y.value());
        edit.commit();
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* renamed from: b */
    public synchronized String mo7637b() {
        return this.f98c.getString("04", "");
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* renamed from: c */
    public synchronized void mo7639c() {
        Editor edit = this.f98c.edit();
        edit.remove("04");
        edit.commit();
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* renamed from: b */
    public synchronized void mo7638b(String str) {
        Editor edit = this.f98c.edit();
        edit.putString("04", str);
        edit.commit();
    }
}
