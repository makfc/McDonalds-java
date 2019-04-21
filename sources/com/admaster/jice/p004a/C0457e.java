package com.admaster.jice.p004a;

import android.support.p000v4.util.Pair;
import android.text.TextUtils;
import android.util.Log;
import com.admaster.jice.p005b.HttpConfig;

/* compiled from: JiceCore */
/* renamed from: com.admaster.jice.a.e */
class C0457e implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C0456d f43a;

    C0457e(C0456d c0456d) {
        this.f43a = c0456d;
    }

    public void run() {
        boolean z;
        C0461i c0461i = C0461i.JICEAPPSTART;
        if (this.f43a.f31a.f27e.mo7634a() == C0472y.ACTIVED) {
            z = true;
            c0461i = C0461i.JICEAPPSTART;
        } else {
            z = false;
            c0461i = C0461i.JICEACTIVE;
        }
        String a = this.f43a.m39a(System.currentTimeMillis());
        String c0461i2 = c0461i.toString();
        String str = null;
        if (TextUtils.isEmpty(c0461i2) || TextUtils.isEmpty(a)) {
            str = "initSysEvent eventname or expire is empty";
        }
        c0461i2 = this.f43a.m42a(c0461i2, "", str);
        Pair pair = new Pair(c0461i2, a);
        boolean a2 = this.f43a.f38h.mo7714a(HttpConfig.m166e(), (String) pair.first, HttpConfig.m167f());
        if (!a2 || TextUtils.isEmpty(c0461i2) || TextUtils.isEmpty(a)) {
            Log.e("JiceError", "initSysEvent:" + a2 + "   expire:" + a + "   event:" + c0461i2 + "  hasActived:" + z);
        }
        if (!a2) {
            this.f43a.f31a.f28f.mo7598a(C0455b.FAILED, (String) pair.first, (String) pair.second);
        }
        if (!z) {
            this.f43a.f31a.f27e.mo7635a(C0472y.ACTIVED);
        }
    }
}
