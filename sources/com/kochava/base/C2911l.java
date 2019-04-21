package com.kochava.base;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import org.json.JSONObject;

/* renamed from: com.kochava.base.l */
final class C2911l extends C2907i {
    @Nullable
    /* renamed from: b */
    private JSONObject f6649b = null;

    @AnyThread
    C2911l(@NonNull C2906h c2906h) {
        super(c2906h, false);
    }

    @NonNull
    /* renamed from: a */
    static String m7728a(@Nullable String str) {
        String str2 = null;
        if (!(str == null || "false".equalsIgnoreCase(str))) {
            JSONObject f = C2901d.m7661f(str);
            if (f != null) {
                str2 = C2901d.m7632a(f);
            }
        }
        return str2 == null ? "{\"attribution\":\"false\"}" : str2;
    }

    @WorkerThread
    public final void run() {
        String str = "run";
        Tracker.m7517a(4, "TGA", "run", "run");
        final Object a = C2901d.m7628a(this.f6629a.f6611d.mo26572c("attribution"));
        int a2 = (int) (C2901d.m7626a() / 1000);
        int b = C2901d.m7647b(this.f6629a.f6611d.mo26572c("attribution_time"), a2);
        int b2 = C2901d.m7647b(this.f6629a.f6611d.mo26572c("attribution_staleness"), -1);
        String str2 = "TGA";
        String str3 = "run";
        Object[] objArr = new Object[1];
        objArr[0] = "Now: " + a2 + " Last: " + b + " Staleness: " + b2 + " HasCache: " + (a != null);
        Tracker.m7517a(4, str2, str3, objArr);
        if (b == a2) {
            this.f6629a.f6611d.mo26565a("attribution_time", Integer.valueOf(a2));
        }
        int i = (b2 == -1 || b + b2 >= a2) ? 1 : 0;
        if ((this.f6629a.f6612e == null && this.f6629a.f6613f == null) || ((a != null && i != 0) || (a != null && this.f6629a.f6613f == null))) {
            Tracker.m7517a(4, "TGA", "run", "Skip");
            mo26614d();
            mo26621k();
        } else if (mo26619i() || a != null) {
            if (this.f6649b == null) {
                Tracker.m7517a(5, "TGA", "run", "Gather");
                this.f6649b = new JSONObject();
                mo26607a(5, this.f6649b, new JSONObject());
            }
            JSONObject a3 = mo26605a(5, (Object) this.f6649b);
            if (!mo26609a(a3, true)) {
                str = null;
                a3 = C2901d.m7661f(a3.opt(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH));
                if (a3 != null) {
                    str = a3.optString("attribution");
                }
                final Object a4 = C2911l.m7728a(str);
                this.f6629a.f6611d.mo26565a("attribution", a4);
                if (C2901d.m7643a(a4, a)) {
                    Tracker.m7517a(4, "TGA", "run", "Attribution Refresh Did Not Change");
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            try {
                                if (C2911l.this.f6629a.f6613f != null) {
                                    C2911l.this.f6629a.f6613f.onAttributionUpdated(a4);
                                }
                                if (a == null && C2911l.this.f6629a.f6612e != null) {
                                    C2911l.this.f6629a.f6612e.onAttributionReceived(a4);
                                }
                            } catch (Throwable th) {
                                Tracker.m7517a(2, "TGA", "run", "Exception in Host App", th);
                            }
                        }
                    });
                }
                this.f6629a.f6611d.mo26565a("attribution_time", Integer.valueOf((int) (C2901d.m7626a() / 1000)));
                mo26614d();
                Tracker.m7517a(3, "TGA", "Attribution", "Complete");
                Tracker.m7517a(4, "TGA", "run", "Complete");
            }
        } else {
            mo26618h();
            mo26606a(C2901d.m7647b(this.f6629a.f6611d.mo26572c("getattribution_wait"), 7));
        }
    }
}
