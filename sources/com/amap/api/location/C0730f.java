package com.amap.api.location;

import android.location.LocationListener;
import android.os.Bundle;

/* compiled from: IGPSManager */
/* renamed from: com.amap.api.location.f */
class C0730f implements LocationListener {
    /* renamed from: a */
    final /* synthetic */ IGPSManager f948a;

    C0730f(IGPSManager iGPSManager) {
        this.f948a = iGPSManager;
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x0156 A:{Catch:{ Exception -> 0x00ed, all -> 0x0142, Throwable -> 0x00e2 }} */
    public void onLocationChanged(android.location.Location r7) {
        /*
        r6 = this;
        r0 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r1 = 1;
        r0.mo8361b(r1);	 Catch:{ Throwable -> 0x00e2 }
        r0 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r2 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Throwable -> 0x00e2 }
        r0.f842e = r2;	 Catch:{ Throwable -> 0x00e2 }
        r2 = 0;
        if (r7 != 0) goto L_0x0068;
    L_0x0019:
        r0 = new android.os.Message;	 Catch:{ Throwable -> 0x00e2 }
        r0.<init>();	 Catch:{ Throwable -> 0x00e2 }
        r0.obj = r2;	 Catch:{ Throwable -> 0x00e2 }
        r1 = 100;
        r0.what = r1;	 Catch:{ Throwable -> 0x00e2 }
        r1 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r1 = r1.f943d;	 Catch:{ Throwable -> 0x00e2 }
        if (r1 == 0) goto L_0x0035;
    L_0x002c:
        r1 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r1 = r1.f943d;	 Catch:{ Throwable -> 0x00e2 }
        r1.sendMessage(r0);	 Catch:{ Throwable -> 0x00e2 }
    L_0x0035:
        r0 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r1 = 1;
        r0.f841d = r1;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f840c;	 Catch:{ Throwable -> 0x00e2 }
        if (r0 == 0) goto L_0x0067;
    L_0x0048:
        r0 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f840c;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f868a;	 Catch:{ Throwable -> 0x00e2 }
        if (r0 == 0) goto L_0x0067;
    L_0x0054:
        r0 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f840c;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f868a;	 Catch:{ Throwable -> 0x00e2 }
        r1 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r1 = r1.f945f;	 Catch:{ Throwable -> 0x00e2 }
        r0.mo13173a(r1, r2);	 Catch:{ Throwable -> 0x00e2 }
    L_0x0067:
        return;
    L_0x0068:
        r0 = r7.getLatitude();	 Catch:{ Exception -> 0x00ed, all -> 0x0142 }
        r4 = r7.getLongitude();	 Catch:{ Exception -> 0x00ed, all -> 0x0142 }
        r0 = com.amap.api.location.core.ClientInfoUtil.m1423a(r0, r4);	 Catch:{ Exception -> 0x00ed, all -> 0x0142 }
        if (r0 == 0) goto L_0x00e7;
    L_0x0076:
        r0 = r7.getLongitude();	 Catch:{ Exception -> 0x00ed, all -> 0x0142 }
        r4 = r7.getLatitude();	 Catch:{ Exception -> 0x00ed, all -> 0x0142 }
        r0 = com.aps.C1270w.m5742a(r0, r4);	 Catch:{ Exception -> 0x00ed, all -> 0x0142 }
        r1 = new com.amap.api.location.AMapLocation;	 Catch:{ Exception -> 0x00ed, all -> 0x0142 }
        r1.<init>(r7);	 Catch:{ Exception -> 0x00ed, all -> 0x0142 }
        r2 = 1;
        r2 = r0[r2];	 Catch:{ Exception -> 0x0195 }
        r1.setLatitude(r2);	 Catch:{ Exception -> 0x0195 }
        r2 = 0;
        r2 = r0[r2];	 Catch:{ Exception -> 0x0195 }
        r1.setLongitude(r2);	 Catch:{ Exception -> 0x0195 }
    L_0x0093:
        r0 = new android.os.Message;	 Catch:{ Throwable -> 0x00e2 }
        r0.<init>();	 Catch:{ Throwable -> 0x00e2 }
        r0.obj = r1;	 Catch:{ Throwable -> 0x00e2 }
        r2 = 100;
        r0.what = r2;	 Catch:{ Throwable -> 0x00e2 }
        r2 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r2 = r2.f943d;	 Catch:{ Throwable -> 0x00e2 }
        if (r2 == 0) goto L_0x00af;
    L_0x00a6:
        r2 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r2 = r2.f943d;	 Catch:{ Throwable -> 0x00e2 }
        r2.sendMessage(r0);	 Catch:{ Throwable -> 0x00e2 }
    L_0x00af:
        r0 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r2 = 1;
        r0.f841d = r2;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f840c;	 Catch:{ Throwable -> 0x00e2 }
        if (r0 == 0) goto L_0x0067;
    L_0x00c2:
        r0 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f840c;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f868a;	 Catch:{ Throwable -> 0x00e2 }
        if (r0 == 0) goto L_0x0067;
    L_0x00ce:
        r0 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f840c;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f868a;	 Catch:{ Throwable -> 0x00e2 }
        r2 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r2 = r2.f945f;	 Catch:{ Throwable -> 0x00e2 }
        r0.mo13173a(r2, r1);	 Catch:{ Throwable -> 0x00e2 }
        goto L_0x0067;
    L_0x00e2:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0067;
    L_0x00e7:
        r1 = new com.amap.api.location.AMapLocation;	 Catch:{ Exception -> 0x00ed, all -> 0x0142 }
        r1.<init>(r7);	 Catch:{ Exception -> 0x00ed, all -> 0x0142 }
        goto L_0x0093;
    L_0x00ed:
        r0 = move-exception;
        r1 = r2;
    L_0x00ef:
        r0.printStackTrace();	 Catch:{ all -> 0x0192 }
        r0 = new android.os.Message;	 Catch:{ Throwable -> 0x00e2 }
        r0.<init>();	 Catch:{ Throwable -> 0x00e2 }
        r0.obj = r1;	 Catch:{ Throwable -> 0x00e2 }
        r2 = 100;
        r0.what = r2;	 Catch:{ Throwable -> 0x00e2 }
        r2 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r2 = r2.f943d;	 Catch:{ Throwable -> 0x00e2 }
        if (r2 == 0) goto L_0x010e;
    L_0x0105:
        r2 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r2 = r2.f943d;	 Catch:{ Throwable -> 0x00e2 }
        r2.sendMessage(r0);	 Catch:{ Throwable -> 0x00e2 }
    L_0x010e:
        r0 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r2 = 1;
        r0.f841d = r2;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f840c;	 Catch:{ Throwable -> 0x00e2 }
        if (r0 == 0) goto L_0x0067;
    L_0x0121:
        r0 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f840c;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f868a;	 Catch:{ Throwable -> 0x00e2 }
        if (r0 == 0) goto L_0x0067;
    L_0x012d:
        r0 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f840c;	 Catch:{ Throwable -> 0x00e2 }
        r0 = r0.f868a;	 Catch:{ Throwable -> 0x00e2 }
        r2 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r2 = r2.f945f;	 Catch:{ Throwable -> 0x00e2 }
        r0.mo13173a(r2, r1);	 Catch:{ Throwable -> 0x00e2 }
        goto L_0x0067;
    L_0x0142:
        r0 = move-exception;
    L_0x0143:
        r1 = new android.os.Message;	 Catch:{ Throwable -> 0x00e2 }
        r1.<init>();	 Catch:{ Throwable -> 0x00e2 }
        r1.obj = r2;	 Catch:{ Throwable -> 0x00e2 }
        r3 = 100;
        r1.what = r3;	 Catch:{ Throwable -> 0x00e2 }
        r3 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r3 = r3.f943d;	 Catch:{ Throwable -> 0x00e2 }
        if (r3 == 0) goto L_0x015f;
    L_0x0156:
        r3 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r3 = r3.f943d;	 Catch:{ Throwable -> 0x00e2 }
        r3.sendMessage(r1);	 Catch:{ Throwable -> 0x00e2 }
    L_0x015f:
        r1 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r1 = r1.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r3 = 1;
        r1.f841d = r3;	 Catch:{ Throwable -> 0x00e2 }
        r1 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r1 = r1.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r1 = r1.f840c;	 Catch:{ Throwable -> 0x00e2 }
        if (r1 == 0) goto L_0x0191;
    L_0x0172:
        r1 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r1 = r1.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r1 = r1.f840c;	 Catch:{ Throwable -> 0x00e2 }
        r1 = r1.f868a;	 Catch:{ Throwable -> 0x00e2 }
        if (r1 == 0) goto L_0x0191;
    L_0x017e:
        r1 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r1 = r1.f944e;	 Catch:{ Throwable -> 0x00e2 }
        r1 = r1.f840c;	 Catch:{ Throwable -> 0x00e2 }
        r1 = r1.f868a;	 Catch:{ Throwable -> 0x00e2 }
        r3 = r6.f948a;	 Catch:{ Throwable -> 0x00e2 }
        r3 = r3.f945f;	 Catch:{ Throwable -> 0x00e2 }
        r1.mo13173a(r3, r2);	 Catch:{ Throwable -> 0x00e2 }
    L_0x0191:
        throw r0;	 Catch:{ Throwable -> 0x00e2 }
    L_0x0192:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0143;
    L_0x0195:
        r0 = move-exception;
        goto L_0x00ef;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.location.C0730f.onLocationChanged(android.location.Location):void");
    }

    public void onProviderDisabled(String str) {
        if ("gps".equals(str)) {
            this.f948a.f944e.mo8361b(false);
        }
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
        if (i == 0 || i == 1) {
            this.f948a.f944e.mo8361b(false);
        }
    }
}
