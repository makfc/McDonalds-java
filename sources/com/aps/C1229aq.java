package com.aps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import java.util.List;
import java.util.Timer;

/* renamed from: com.aps.aq */
final class C1229aq extends BroadcastReceiver {
    /* renamed from: a */
    final /* synthetic */ C1225am f4279a;

    private C1229aq(C1225am c1225am) {
        this.f4279a = c1225am;
    }

    /* synthetic */ C1229aq(C1225am c1225am, byte b) {
        this(c1225am);
    }

    public final void onReceive(Context context, Intent intent) {
        if (context != null && intent != null) {
            try {
                if (this.f4279a.f4254d != null && this.f4279a.f4248A != null && this.f4279a.f4275z != null && intent.getAction() != null && "android.net.wifi.SCAN_RESULTS".equals(intent.getAction())) {
                    List scanResults = this.f4279a.f4254d.getScanResults();
                    synchronized (this) {
                        this.f4279a.f4275z.clear();
                        this.f4279a.f4269s = System.currentTimeMillis();
                        if (scanResults != null && scanResults.size() > 0) {
                            for (int i = 0; i < scanResults.size(); i++) {
                                this.f4279a.f4275z.add((ScanResult) scanResults.get(i));
                            }
                        }
                    }
                    C1230ar c1230ar = new C1230ar(this);
                    synchronized (this) {
                        if (this.f4279a.f4248A != null) {
                            this.f4279a.f4248A.cancel();
                            this.f4279a.f4248A = null;
                        }
                        this.f4279a.f4248A = new Timer();
                        this.f4279a.f4248A.schedule(c1230ar, (long) C1225am.f4246D);
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}
