package com.threatmetrix.TrustDefender;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/* renamed from: com.threatmetrix.TrustDefender.ao */
class C4501ao implements LocationListener {
    /* renamed from: a */
    private static final String f7483a = C4549w.m8585a(C4501ao.class);
    /* renamed from: b */
    private Location f7484b = null;

    /* JADX WARNING: Missing block: B:29:0x0097, code skipped:
            if (r5 != null) goto L_0x0099;
     */
    /* JADX WARNING: Missing block: B:32:0x009d, code skipped:
            if (r3 == false) goto L_0x009f;
     */
    public void onLocationChanged(android.location.Location r11) {
        /*
        r10 = this;
        r2 = 0;
        r1 = 1;
        r0 = f7483a;
        r3 = new java.lang.StringBuilder;
        r4 = "onLocationChanged() : ";
        r3.<init>(r4);
        r4 = r11.getProvider();
        r3 = r3.append(r4);
        r4 = ":";
        r3 = r3.append(r4);
        r4 = r11.getLatitude();
        r3 = r3.append(r4);
        r4 = ":";
        r3 = r3.append(r4);
        r4 = r11.getLongitude();
        r3 = r3.append(r4);
        r4 = ":";
        r3 = r3.append(r4);
        r4 = r11.getAccuracy();
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.threatmetrix.TrustDefender.C4549w.m8594c(r0, r3);
        r7 = r10.f7484b;
        if (r7 != 0) goto L_0x004d;
    L_0x0048:
        if (r1 == 0) goto L_0x004c;
    L_0x004a:
        r10.f7484b = r11;
    L_0x004c:
        return;
    L_0x004d:
        r4 = r11.getTime();
        r8 = r7.getTime();
        r4 = r4 - r8;
        r8 = 120000; // 0x1d4c0 float:1.68156E-40 double:5.9288E-319;
        r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r0 <= 0) goto L_0x00a1;
    L_0x005d:
        r3 = r1;
    L_0x005e:
        r8 = -120000; // 0xfffffffffffe2b40 float:NaN double:NaN;
        r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r0 >= 0) goto L_0x00a3;
    L_0x0065:
        r0 = r1;
    L_0x0066:
        r8 = 0;
        r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r4 <= 0) goto L_0x00a5;
    L_0x006c:
        r6 = r1;
    L_0x006d:
        if (r3 != 0) goto L_0x0048;
    L_0x006f:
        if (r0 != 0) goto L_0x009f;
    L_0x0071:
        r0 = r11.getAccuracy();
        r3 = r7.getAccuracy();
        r0 = r0 - r3;
        r0 = (int) r0;
        if (r0 <= 0) goto L_0x00a7;
    L_0x007d:
        r5 = r1;
    L_0x007e:
        if (r0 >= 0) goto L_0x00a9;
    L_0x0080:
        r4 = r1;
    L_0x0081:
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 <= r3) goto L_0x00ab;
    L_0x0085:
        r0 = r1;
    L_0x0086:
        r3 = r11.getProvider();
        r7 = r7.getProvider();
        if (r3 != 0) goto L_0x00af;
    L_0x0090:
        if (r7 != 0) goto L_0x00ad;
    L_0x0092:
        r3 = r1;
    L_0x0093:
        if (r4 != 0) goto L_0x0048;
    L_0x0095:
        if (r6 == 0) goto L_0x0099;
    L_0x0097:
        if (r5 == 0) goto L_0x0048;
    L_0x0099:
        if (r6 == 0) goto L_0x009f;
    L_0x009b:
        if (r0 != 0) goto L_0x009f;
    L_0x009d:
        if (r3 != 0) goto L_0x0048;
    L_0x009f:
        r1 = r2;
        goto L_0x0048;
    L_0x00a1:
        r3 = r2;
        goto L_0x005e;
    L_0x00a3:
        r0 = r2;
        goto L_0x0066;
    L_0x00a5:
        r6 = r2;
        goto L_0x006d;
    L_0x00a7:
        r5 = r2;
        goto L_0x007e;
    L_0x00a9:
        r4 = r2;
        goto L_0x0081;
    L_0x00ab:
        r0 = r2;
        goto L_0x0086;
    L_0x00ad:
        r3 = r2;
        goto L_0x0093;
    L_0x00af:
        r3 = r3.equals(r7);
        goto L_0x0093;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4501ao.onLocationChanged(android.location.Location):void");
    }

    public void onProviderDisabled(String provider) {
        C4549w.m8594c(f7483a, "onProviderDisabled: " + provider);
    }

    public void onProviderEnabled(String provider) {
        C4549w.m8594c(f7483a, "onProviderEnabled: " + provider);
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        String str = f7483a;
        StringBuilder append = new StringBuilder("onStatusChanged: ").append(provider).append(" status: ");
        String str2 = status == 2 ? "available " : status == 1 ? "temporarily unavailable" : status == 0 ? "Out of Service" : "unknown";
        C4549w.m8594c(str, append.append(str2).toString());
    }

    /* renamed from: a */
    public final Location mo34167a() {
        if (this.f7484b != null) {
            return new Location(this.f7484b);
        }
        return null;
    }
}
