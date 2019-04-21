package com.aps;

import android.os.Looper;
import java.util.List;

/* renamed from: com.aps.au */
final class C1233au extends Thread {
    /* renamed from: a */
    final /* synthetic */ C1213aa f4282a;

    C1233au(C1213aa c1213aa, String str) {
        this.f4282a = c1213aa;
        super(str);
    }

    public final void run() {
        try {
            Looper.prepare();
            this.f4282a.f4156B = Looper.myLooper();
            this.f4282a.f4159E = new C1235aw(this.f4282a);
            try {
                this.f4282a.f4178s.addGpsStatusListener(this.f4282a.f4159E);
                this.f4282a.f4178s.addNmeaListener(this.f4282a.f4159E);
            } catch (Exception e) {
            }
            this.f4282a.f4160F = new C1234av(this);
            List allProviders = this.f4282a.f4178s.getAllProviders();
            if (allProviders != null && allProviders.contains("gps")) {
                allProviders.contains("passive");
            }
            try {
                this.f4282a.f4178s.requestLocationUpdates("passive", 1000, (float) C1213aa.f4147c, this.f4282a.f4162H);
            } catch (Exception e2) {
            }
            Looper.loop();
        } catch (Exception e3) {
        }
    }
}
