package com.admaster.jice.p004a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;
import java.util.Map;

/* renamed from: com.admaster.jice.a.a */
public class EventManager {
    /* renamed from: b */
    private static EventManager f20b = null;
    /* renamed from: a */
    private final Context f21a;

    private EventManager(Context context) {
        if (context == null) {
            throw new NullPointerException("StoreManager context can`t be null!");
        }
        this.f21a = context;
    }

    /* renamed from: a */
    public static EventManager m19a(Context context) {
        if (f20b == null) {
            synchronized (StoreManager.class) {
                if (f20b == null) {
                    f20b = new EventManager(context);
                }
            }
        }
        return f20b;
    }

    /* renamed from: a */
    public synchronized SharedPreferences mo7595a() {
        return this.f21a.getSharedPreferences("com.admaster.jice.event.normal", 0);
    }

    /* renamed from: b */
    private synchronized SharedPreferences m20b() {
        return this.f21a.getSharedPreferences("com.admaster.jice.event.failed", 0);
    }

    /* renamed from: a */
    public synchronized void mo7598a(C0455b c0455b, String str, String str2) {
        SharedPreferences sharedPreferences = null;
        if (c0455b == C0455b.NORMAL) {
            sharedPreferences = mo7595a();
        } else if (c0455b == C0455b.FAILED) {
            sharedPreferences = m20b();
        }
        Editor edit = sharedPreferences.edit();
        edit.putString(str, str2);
        boolean commit = edit.commit();
        if (!commit || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Log.e("JiceError", "saveEvent2Store success:" + commit + "   event:" + str + "  expire:" + str2);
        }
    }

    /* renamed from: a */
    public synchronized Map<String, String> mo7596a(C0455b c0455b) {
        Map<String, String> map = null;
        synchronized (this) {
            SharedPreferences a;
            if (c0455b == C0455b.NORMAL) {
                a = mo7595a();
            } else if (c0455b == C0455b.FAILED) {
                a = m20b();
            } else {
                a = null;
            }
            if (a != null) {
                map = a.getAll();
            }
        }
        return map;
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* renamed from: a */
    public synchronized void mo7597a(C0455b c0455b, String str) {
        SharedPreferences sharedPreferences = null;
        if (c0455b == C0455b.NORMAL) {
            sharedPreferences = mo7595a();
        } else if (c0455b == C0455b.FAILED) {
            sharedPreferences = m20b();
        }
        Editor edit = sharedPreferences.edit();
        edit.remove(str);
        boolean commit = edit.commit();
        if (!commit || TextUtils.isEmpty(str)) {
            Log.e("JiceError", "deleteEvent success:" + commit + "   event:" + str);
        }
    }
}
