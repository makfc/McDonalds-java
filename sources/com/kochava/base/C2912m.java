package com.kochava.base;

import android.support.annotation.NonNull;
import com.autonavi.amap.mapcore.interfaces.CameraAnimator;
import org.json.JSONObject;

/* renamed from: com.kochava.base.m */
final class C2912m extends C2907i {
    @NonNull
    /* renamed from: b */
    private final JSONObject f6650b;

    C2912m(@NonNull C2906h c2906h, @NonNull JSONObject jSONObject) {
        super(c2906h, true);
        this.f6650b = jSONObject;
    }

    public final void run() {
        String str = "run";
        Tracker.m7517a(4, "TIL", "run", new Object[0]);
        JSONObject b = C2901d.m7649b(this.f6629a.f6611d.mo26572c("identity_link_all"), true);
        Object b2 = C2901d.m7649b(this.f6629a.f6611d.mo26572c("identity_link"), true);
        boolean z = b2.length() != 0;
        C2901d.m7651b((JSONObject) b2, this.f6650b);
        if (z || !C2901d.m7646a(b, (JSONObject) b2)) {
            Object jSONObject;
            JSONObject jSONObject2;
            C2901d.m7651b(b, (JSONObject) b2);
            if (b.length() > CameraAnimator.DEFAULT_DURATION) {
                jSONObject2 = new JSONObject();
                Tracker.m7517a(4, "TIL", "run", "Max Size Exceeded. Resetting Saved List.");
            } else {
                jSONObject2 = b;
            }
            this.f6629a.f6611d.mo26565a("identity_link_all", jSONObject2);
            this.f6629a.f6611d.mo26565a("identity_link", b2);
            z = C2901d.m7628a(this.f6629a.f6611d.mo26572c("initial_data")) != null;
            boolean a = C2901d.m7644a(this.f6629a.f6611d.mo26572c("initial_needs_sent"), true);
            if (z || !a) {
                jSONObject2 = new JSONObject();
                mo26607a(7, jSONObject2, new JSONObject());
                this.f6629a.f6611d.mo26574c(jSONObject2);
                if (this.f6629a.f6616i.mo26533k()) {
                    mo26621k();
                }
            }
            mo26614d();
            Tracker.m7517a(4, "TIL", "run", "Complete");
            return;
        }
        this.f6629a.f6611d.mo26569b("identity_link");
        Tracker.m7517a(4, "TIL", "run", "Skip");
    }
}
