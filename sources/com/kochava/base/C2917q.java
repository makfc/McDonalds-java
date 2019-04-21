package com.kochava.base;

import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.mcdonalds.sdk.services.analytics.tagmanager.Parameters;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.kochava.base.q */
final class C2917q extends C2907i {
    @AnyThread
    C2917q(@NonNull C2906h c2906h) {
        super(c2906h, false);
    }

    /* Access modifiers changed, original: final */
    @NonNull
    /* renamed from: a */
    public final JSONArray mo26631a(@NonNull JSONArray jSONArray, @Nullable JSONArray jSONArray2, @Nullable JSONArray jSONArray3, boolean z) {
        JSONArray jSONArray4 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject b = C2901d.m7649b(jSONArray.opt(i), true);
            if (b.has(Parameters.ACTION) && b.has("sdk_version")) {
                JSONObject b2 = C2901d.m7649b(b.opt(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH), true);
                int a = C2907i.m7694a(b);
                String a2 = C2901d.m7628a(b2.opt("event_name"));
                if ((jSONArray3 == null || a2 == null || a != 6 || !C2901d.m7645a(jSONArray3, a2)) && (z || !(a == 3 || a == 2))) {
                    C2907i.m7701a(b, this.f6629a.f6611d);
                    boolean a3 = C2901d.m7644a(b.opt("backfilled"), false);
                    b.remove("backfilled");
                    if (a3) {
                        C2901d.m7636a(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH, mo26610b(a, b2), b);
                    }
                    mo26633b(b, jSONArray2);
                    jSONArray4.put(b);
                }
            }
        }
        return jSONArray4;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final boolean mo26632a(@NonNull JSONObject jSONObject, @Nullable JSONArray jSONArray) {
        if (!jSONObject.has(Parameters.ACTION) || !jSONObject.has("sdk_version")) {
            return false;
        }
        int a = C2907i.m7694a(jSONObject);
        C2907i.m7701a(jSONObject, this.f6629a.f6611d);
        boolean a2 = C2901d.m7644a(this.f6629a.f6611d.mo26572c("push"), false);
        if ((a == 9 || a == 10) && !a2) {
            return false;
        }
        if (a == 4) {
            mo26633b(jSONObject, jSONArray);
        }
        return true;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final void mo26633b(@NonNull JSONObject jSONObject, @Nullable JSONArray jSONArray) {
        if (jSONArray != null) {
            JSONObject b = C2901d.m7649b(jSONObject.opt(MapTilsCacheAndResManager.AUTONAVI_DATA_PATH), true);
            JSONArray c = C2901d.m7657c(b.names(), true);
            for (int i = 0; i < c.length(); i++) {
                String a = C2901d.m7628a(c.opt(i));
                if (a != null && C2901d.m7645a(jSONArray, a)) {
                    b.remove(a);
                }
            }
        }
    }

    public final void run() {
        String str = "run";
        Tracker.m7517a(4, "TQU", "run", new Object[0]);
        Tracker.m7517a(5, "TQU", "run", "update");
        JSONArray g = C2901d.m7662g(this.f6629a.f6611d.mo26572c("blacklist"));
        while (this.f6629a.f6611d.mo26580h() > 0) {
            JSONObject g2 = this.f6629a.f6611d.mo26579g();
            if (!mo26632a(g2, g) || !mo26609a(mo26605a(C2907i.m7694a(g2), (Object) g2), true)) {
                this.f6629a.f6611d.mo26578f();
            } else {
                return;
            }
        }
        Tracker.m7517a(5, "TQU", "run", "event");
        JSONArray g3 = C2901d.m7662g(this.f6629a.f6611d.mo26572c("eventname_blacklist"));
        boolean a = C2901d.m7644a(this.f6629a.f6611d.mo26572c("session_tracking"), true);
        while (this.f6629a.f6611d.mo26577e() > 0) {
            JSONArray d = this.f6629a.f6611d.mo26576d();
            JSONArray a2 = mo26631a(d, g, g3, a);
            if (a2.length() <= 0 || !mo26609a(mo26605a(6, (Object) a2), true)) {
                this.f6629a.f6611d.mo26564a(d.length());
            } else {
                return;
            }
        }
        this.f6629a.f6611d.mo26568b();
        mo26620j();
        Tracker.m7517a(4, "TQU", "run", "Complete");
    }
}
