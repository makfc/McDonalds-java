package com.aps;

import android.location.GpsStatus.NmeaListener;

/* renamed from: com.aps.ap */
final class C1228ap implements NmeaListener {
    /* renamed from: a */
    private /* synthetic */ C1225am f4278a;

    private C1228ap(C1225am c1225am) {
        this.f4278a = c1225am;
    }

    /* synthetic */ C1228ap(C1225am c1225am, byte b) {
        this(c1225am);
    }

    public final void onNmeaReceived(long j, String str) {
        try {
            this.f4278a.f4262l = j;
            this.f4278a.f4263m = str;
        } catch (Exception e) {
        }
    }
}
