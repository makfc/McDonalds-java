package com.aps;

import java.util.TimerTask;

/* renamed from: com.aps.ar */
final class C1230ar extends TimerTask {
    /* renamed from: a */
    private /* synthetic */ C1229aq f4280a;

    C1230ar(C1229aq c1229aq) {
        this.f4280a = c1229aq;
    }

    public final void run() {
        try {
            if (C1249bf.f4368a && this.f4280a.f4279a.f4254d != null) {
                this.f4280a.f4279a.f4254d.startScan();
            }
        } catch (Exception e) {
        }
    }
}
