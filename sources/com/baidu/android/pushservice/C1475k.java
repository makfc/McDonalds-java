package com.baidu.android.pushservice;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.baidu.android.pushservice.p033e.C1361a.C1360a;
import com.baidu.android.pushservice.p033e.C1365ab;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1550n;

/* renamed from: com.baidu.android.pushservice.k */
public final class C1475k {
    /* renamed from: a */
    private static C1475k f5177a;
    /* renamed from: b */
    private String f5178b = null;
    /* renamed from: c */
    private String f5179c = null;
    /* renamed from: d */
    private Thread f5180d = null;
    /* renamed from: e */
    private boolean f5181e;
    /* renamed from: f */
    private Context f5182f;

    private C1475k(Context context) {
        this.f5179c = C1550n.m6955a(context, "com.baidu.pushservice.channel_token");
        this.f5178b = PushSettings.m5874a(context);
        this.f5181e = false;
        this.f5182f = context;
    }

    /* renamed from: a */
    public static synchronized C1475k m6721a(Context context) {
        C1475k c1475k;
        synchronized (C1475k.class) {
            if (f5177a == null) {
                f5177a = new C1475k(context);
            }
            c1475k = f5177a;
        }
        return c1475k;
    }

    /* renamed from: a */
    public String mo13946a() {
        return this.f5178b;
    }

    /* renamed from: a */
    public void mo13947a(Context context, boolean z, C1360a c1360a) {
        if (this.f5180d == null || !this.f5180d.isAlive()) {
            C1365ab c1365ab = new C1365ab(context, c1360a);
            if (!z) {
                c1365ab.mo13728a(0);
            }
            this.f5180d = new Thread(c1365ab);
            this.f5180d.start();
        }
    }

    /* renamed from: a */
    public synchronized void mo13948a(String str, String str2) {
        this.f5178b = str;
        this.f5179c = str2;
        PushSettings.m5877a(this.f5182f, str);
        C1550n.m6958a(this.f5182f, "com.baidu.pushservice.channel_token", str2);
    }

    /* renamed from: b */
    public String mo13949b() {
        return this.f5179c;
    }

    /* renamed from: c */
    public boolean mo13950c() {
        if (TextUtils.isEmpty(this.f5178b) || TextUtils.isEmpty(this.f5179c)) {
            C1425a.m6442c("TokenManager", "isChannelTokenAvailable false mChannelId = " + this.f5178b + " mChannelToken =  " + this.f5179c);
            return false;
        }
        C1425a.m6442c("TokenManager", "isChannelTokenAvailable true mChannelId = " + this.f5178b + " mChannelToken =  " + this.f5179c);
        return true;
    }

    /* renamed from: d */
    public boolean mo13951d() {
        try {
            SharedPreferences sharedPreferences = this.f5182f.getSharedPreferences("pushclient", 0);
            if (sharedPreferences.getInt("isFirstReqChannelIDVcode", 0) == C1328a.m6003a()) {
                C1425a.m6442c("TokenManager", "not first REQChannelID");
                return false;
            }
            Editor edit = sharedPreferences.edit();
            edit.putInt("isFirstReqChannelIDVcode", C1328a.m6003a());
            edit.commit();
            C1425a.m6442c("TokenManager", " first REQChannelID");
            return true;
        } catch (Exception e) {
            C1425a.m6440a("TokenManager", e);
        }
    }
}
