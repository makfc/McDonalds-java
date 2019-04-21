package com.aps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import java.text.SimpleDateFormat;

/* renamed from: com.aps.as */
final class C1231as implements LocationListener {
    /* renamed from: a */
    private /* synthetic */ C1213aa f4281a;

    C1231as(C1213aa c1213aa) {
        this.f4281a = c1213aa;
    }

    /* renamed from: a */
    private static boolean m5427a(Location location) {
        return location != null && "gps".equalsIgnoreCase(location.getProvider()) && location.getLatitude() > -90.0d && location.getLatitude() < 90.0d && location.getLongitude() > -180.0d && location.getLongitude() < 180.0d;
    }

    public final void onLocationChanged(Location location) {
        try {
            long time = location.getTime();
            long currentTimeMillis = System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.format(Long.valueOf(time));
            simpleDateFormat.format(Long.valueOf(currentTimeMillis));
            if (time > 0) {
                currentTimeMillis = time;
            }
            if (location != null && C1231as.m5427a(location)) {
                if (location.getSpeed() > ((float) C1213aa.f4149e)) {
                    C1244ba.m5539a(C1213aa.f4152h);
                    C1244ba.m5540b(C1213aa.f4152h * 10);
                } else if (location.getSpeed() > ((float) C1213aa.f4148d)) {
                    C1244ba.m5539a(C1213aa.f4151g);
                    C1244ba.m5540b(C1213aa.f4151g * 10);
                } else {
                    C1244ba.m5539a(C1213aa.f4150f);
                    C1244ba.m5540b(C1213aa.f4150f * 10);
                }
                this.f4281a.f4184y.mo13155a();
                C1231as.m5427a(location);
                if (this.f4281a.f4184y.mo13155a() && C1231as.m5427a(location)) {
                    location.setTime(System.currentTimeMillis());
                    this.f4281a.f4176q = System.currentTimeMillis();
                    this.f4281a.f4158D = location;
                    if (!this.f4281a.f4170k) {
                        C1213aa.m5282a(this.f4281a, location, 0, currentTimeMillis);
                    } else {
                        C1252bi.m5553a("collector");
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public final void onProviderDisabled(String str) {
    }

    public final void onProviderEnabled(String str) {
    }

    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
