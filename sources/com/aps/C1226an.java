package com.aps;

import android.os.Looper;
import java.util.Timer;

/* renamed from: com.aps.an */
final class C1226an extends Thread {
    /* renamed from: a */
    private /* synthetic */ C1225am f4276a;

    C1226an(C1225am c1225am, String str) {
        this.f4276a = c1225am;
        super(str);
    }

    public final void run() {
        try {
            Looper.prepare();
            this.f4276a.f4250C = Looper.myLooper();
            this.f4276a.f4248A = new Timer();
            this.f4276a.f4271v = new C1227ao(this.f4276a, (byte) 0);
            C1225am.m5368a(this.f4276a, this.f4276a.f4271v);
            this.f4276a.f4272w = new C1228ap(this.f4276a, (byte) 0);
            try {
                C1225am.m5367a(this.f4276a, this.f4276a.f4272w);
            } catch (Exception e) {
            }
            Looper.loop();
        } catch (Exception e2) {
        }
    }
}
