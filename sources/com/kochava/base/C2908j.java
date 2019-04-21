package com.kochava.base;

import android.support.annotation.NonNull;

/* renamed from: com.kochava.base.j */
final class C2908j extends C2907i {
    /* renamed from: b */
    private final boolean f6639b;

    C2908j(@NonNull C2906h c2906h, boolean z) {
        super(c2906h, true);
        this.f6639b = z;
    }

    public final void run() {
        String str = "run";
        Tracker.m7517a(4, "TAL", "run", new Object[0]);
        Object b = C2901d.m7648b(this.f6629a.f6611d.mo26572c("app_limit_tracking"));
        Tracker.m7517a(5, "TAL", "run", "cachedAppLimitAdTracking: " + b, "isEqual: " + C2901d.m7643a(Boolean.valueOf(this.f6639b), b));
        if (b == null || !r1) {
            this.f6629a.f6611d.mo26565a("app_limit_tracking", Boolean.valueOf(this.f6639b));
            this.f6629a.f6611d.mo26565a("app_limit_trackingupd", Boolean.valueOf(true));
            if (C2918s.m7736a(this, false) && this.f6629a.f6616i.mo26533k()) {
                mo26621k();
            }
            Tracker.m7517a(4, "TAL", "run", "Complete");
            return;
        }
        Tracker.m7517a(4, "TAL", "run", "Skip");
    }
}
