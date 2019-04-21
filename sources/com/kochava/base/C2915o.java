package com.kochava.base;

import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import org.json.JSONObject;

/* renamed from: com.kochava.base.o */
final class C2915o extends C2907i {
    @AnyThread
    C2915o(@NonNull C2906h c2906h) {
        super(c2906h, false);
    }

    public final void run() {
        String str = "run";
        Tracker.m7517a(4, "TIL", "run", new Object[0]);
        if (!C2901d.m7644a(this.f6629a.f6611d.mo26572c("initial_needs_sent"), true)) {
            Tracker.m7517a(4, "TIL", "run", "Skip");
            mo26614d();
            mo26621k();
        } else if (mo26619i()) {
            Object f = C2901d.m7661f(this.f6629a.f6611d.mo26572c("initial_data"));
            if (f == null) {
                Tracker.m7517a(5, "TIL", "run", "Gather");
                f = new JSONObject();
                mo26607a(1, (JSONObject) f, new JSONObject());
                this.f6629a.f6611d.mo26565a("initial_data", f);
            }
            if (!mo26609a(mo26605a(1, f), true)) {
                Tracker.m7517a(5, "TIL", "run", mo26605a(1, f));
                if (!C2901d.m7644a(this.f6629a.f6611d.mo26572c("initial_ever_sent"), false)) {
                    this.f6629a.f6611d.mo26565a("session_resume_time", Integer.valueOf((int) (C2901d.m7626a() / 1000)));
                    this.f6629a.f6611d.mo26565a("session_state_active_count", Integer.valueOf(1));
                }
                this.f6629a.f6611d.mo26569b("initial_data");
                this.f6629a.f6611d.mo26565a("initial_ever_sent", Boolean.valueOf(true));
                this.f6629a.f6611d.mo26565a("initial_needs_sent", Boolean.valueOf(false));
                mo26614d();
                Tracker.m7517a(3, "TIL", "initial", "Complete");
                Tracker.m7517a(4, "TIL", "run", "Complete");
                mo26621k();
            }
        } else {
            mo26618h();
            mo26606a(C2901d.m7647b(this.f6629a.f6611d.mo26572c("initial_wait"), 0));
        }
    }
}
