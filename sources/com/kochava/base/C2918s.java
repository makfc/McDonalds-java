package com.kochava.base;

import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import org.json.JSONObject;

/* renamed from: com.kochava.base.s */
final class C2918s extends C2907i {
    @AnyThread
    C2918s(@NonNull C2906h c2906h) {
        super(c2906h, false);
    }

    /* renamed from: a */
    static boolean m7736a(@NonNull C2907i c2907i, boolean z) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (z) {
            c2907i.mo26607a(4, jSONObject, jSONObject2);
        }
        Tracker.m7517a(5, "TUP", "performUpdate", "sendUpdates: " + C2901d.m7644a(c2907i.f6629a.f6611d.mo26572c("send_updates"), true), "gathered: " + (C2901d.m7628a(c2907i.f6629a.f6611d.mo26572c("initial_data")) != null), "needsSent: " + C2901d.m7644a(c2907i.f6629a.f6611d.mo26572c("initial_needs_sent"), true));
        if (C2901d.m7644a(c2907i.f6629a.f6611d.mo26572c("send_updates"), true) && (r0 || !r4)) {
            if (!z) {
                c2907i.mo26607a(4, jSONObject, jSONObject2);
            }
            if (jSONObject2.length() > 2) {
                c2907i.f6629a.f6611d.mo26574c(jSONObject);
                return true;
            }
        }
        return false;
    }

    public final void run() {
        String str = "run";
        Tracker.m7517a(4, "TUP", "run", new Object[0]);
        C2918s.m7736a(this, true);
        mo26614d();
        mo26621k();
        Tracker.m7517a(4, "TUP", "run", "Complete");
    }
}
