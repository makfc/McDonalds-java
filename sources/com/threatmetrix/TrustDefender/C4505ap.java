package com.threatmetrix.TrustDefender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.amap.api.location.LocationManagerProxy;
import com.threatmetrix.TrustDefender.C4532g.C4522f;
import com.threatmetrix.TrustDefender.C4532g.C4523g;
import com.threatmetrix.TrustDefender.C4532g.C4525i;

/* renamed from: com.threatmetrix.TrustDefender.ap */
class C4505ap {
    /* renamed from: a */
    private static final String f7491a = C4549w.m8585a(C4505ap.class);
    /* renamed from: b */
    private int f7492b;
    /* renamed from: c */
    private long f7493c;
    /* renamed from: d */
    private long f7494d;
    /* renamed from: e */
    private boolean f7495e = false;
    /* renamed from: f */
    private boolean f7496f = false;
    /* renamed from: g */
    private boolean f7497g = false;
    /* renamed from: h */
    private boolean f7498h = false;
    /* renamed from: i */
    private Context f7499i;
    /* renamed from: j */
    private Location f7500j;
    /* renamed from: k */
    private LocationManager f7501k;
    /* renamed from: l */
    private C4501ao f7502l;
    /* renamed from: m */
    private C4499al f7503m;
    /* renamed from: n */
    private C4502a f7504n = C4502a.NONE;

    /* renamed from: com.threatmetrix.TrustDefender.ap$a */
    enum C4502a {
        NONE,
        FINE,
        COARSE
    }

    /* renamed from: com.threatmetrix.TrustDefender.ap$b */
    class C4503b extends BroadcastReceiver {
        C4503b() {
        }

        public final void onReceive(Context context, Intent intent) {
            if ("android.intent.action.BATTERY_LOW".equals(intent.getAction())) {
                C4505ap.this.mo34174a();
            } else {
                C4505ap.this.mo34178b();
            }
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.ap$c */
    class C4504c extends BroadcastReceiver {
        C4504c() {
        }

        public final void onReceive(Context context, Intent intent) {
            if (C4523g.m8498a()) {
                try {
                    Object connectivityService = context.getSystemService("connectivity");
                    if (connectivityService != null && (connectivityService instanceof ConnectivityManager)) {
                        NetworkInfo activeNetwork = ((ConnectivityManager) connectivityService).getActiveNetworkInfo();
                        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                        if (isConnected) {
                            C4505ap.this.mo34178b();
                        } else {
                            C4505ap.this.mo34174a();
                        }
                    }
                } catch (SecurityException e) {
                    C4505ap.f7491a;
                } catch (Exception e2) {
                    C4549w.m8594c(C4505ap.f7491a, e2.getMessage());
                }
            }
        }
    }

    C4505ap() {
    }

    /* renamed from: a */
    public final void mo34175a(Context context, long lowPowerUpdateTime, long highPowerUpdateTime, int accuracy) {
        if (!C4522f.m8497b()) {
            this.f7498h = false;
        }
        if (this.f7498h) {
            this.f7499i = context;
            this.f7493c = lowPowerUpdateTime;
            this.f7494d = highPowerUpdateTime;
            this.f7492b = accuracy;
            this.f7502l = new C4501ao();
            C4525i packageManager = new C4525i(context);
            if (packageManager.mo34228a("android.permission.ACCESS_COARSE_LOCATION", context.getPackageName())) {
                this.f7504n = C4502a.COARSE;
            }
            if (packageManager.mo34228a("android.permission.ACCESS_FINE_LOCATION", context.getPackageName())) {
                this.f7504n = C4502a.FINE;
            }
            try {
                this.f7503m = new C4499al();
                this.f7503m.mo34163a(this.f7499i, this.f7494d, this.f7493c, this.f7492b, this.f7502l, this);
                m8408j();
                if (m8406h() || m8405g()) {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                    this.f7499i.registerReceiver(new C4504c(), intentFilter);
                    if (this.f7497g) {
                        intentFilter = new IntentFilter();
                        intentFilter.addAction("android.intent.action.BATTERY_LOW");
                        intentFilter.addAction("android.intent.action.BATTERY_OKAY");
                        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
                        this.f7499i.registerReceiver(new C4503b(), intentFilter);
                    }
                }
            } catch (SecurityException e) {
                String str = f7491a;
                this.f7498h = false;
            } catch (Exception e2) {
                C4549w.m8594c(f7491a, e2.getMessage());
                this.f7498h = false;
            }
        }
    }

    /* renamed from: a */
    public final void mo34174a() {
        if (this.f7498h && !this.f7495e) {
            if (this.f7503m != null) {
                this.f7503m.mo34164a(true);
            }
            if (m8406h()) {
                this.f7503m.mo34166j();
                C4549w.m8594c(f7491a, "paused FUSED location services");
            } else {
                mo34182e();
                C4549w.m8594c(f7491a, "paused NON-FUSED location services");
            }
            this.f7495e = true;
        }
    }

    /* renamed from: b */
    public final void mo34178b() {
        if (this.f7498h && this.f7495e) {
            if (this.f7503m != null) {
                this.f7503m.mo34164a(false);
            }
            if (m8406h()) {
                C4549w.m8594c(f7491a, "resuming FUSED location services");
                this.f7503m.mo34165i();
            } else {
                C4549w.m8594c(f7491a, "resuming NON-FUSED location services");
                m8408j();
            }
            this.f7495e = false;
        }
    }

    /* renamed from: c */
    public final Location mo34180c() {
        Location loc = this.f7500j;
        if (loc == null && this.f7502l != null && this.f7498h) {
            return this.f7502l.mo34167a();
        }
        return loc;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo34176a(Location location) {
        m8403a(location, true);
    }

    /* renamed from: a */
    private void m8403a(Location location, boolean isManualLocation) {
        if (location != null) {
            this.f7500j = new Location(location);
            this.f7496f = isManualLocation;
            return;
        }
        this.f7500j = null;
        this.f7496f = false;
    }

    /* renamed from: d */
    public final boolean mo34181d() {
        return this.f7496f;
    }

    /* renamed from: a */
    public final void mo34177a(boolean isRegisterForLocationServices) {
        if (!(C4522f.m8496a() && C4522f.m8497b())) {
            this.f7498h = false;
        }
        this.f7498h = isRegisterForLocationServices;
    }

    /* renamed from: g */
    private boolean m8405g() {
        return (this.f7501k == null || this.f7502l == null) ? false : true;
    }

    /* renamed from: h */
    private boolean m8406h() {
        return this.f7503m != null && this.f7503m.mo34155b().mo34162a();
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final void mo34179b(boolean m_disableLocSerOnBatteryLow) {
        this.f7497g = m_disableLocSerOnBatteryLow;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: e */
    public final void mo34182e() {
        if (m8405g()) {
            try {
                this.f7501k.removeUpdates(this.f7502l);
            } catch (SecurityException e) {
                String str = f7491a;
            } catch (Exception e2) {
                C4549w.m8594c(f7491a, e2.getMessage());
            }
        }
    }

    /* renamed from: i */
    private void m8407i() {
        float bestAccuracy = Float.MAX_VALUE;
        long bestTime = 0;
        Location bestResult = null;
        C4549w.m8594c(f7491a, "Attempting to find an existing location to prime things");
        String str;
        try {
            for (String provider : this.f7501k.getAllProviders()) {
                if (provider == null) {
                    str = f7491a;
                } else {
                    C4549w.m8594c(f7491a, "getLastLocation() : " + provider);
                    if (this.f7504n != C4502a.COARSE || provider.equals(LocationManagerProxy.NETWORK_PROVIDER)) {
                        Location location = this.f7501k.getLastKnownLocation(provider);
                        if (location != null) {
                            C4549w.m8594c(f7491a, "getLastLocation() : " + location.getProvider() + ":" + location.getLatitude() + ":" + location.getLongitude() + ":" + location.getAccuracy());
                            float accuracy = location.getAccuracy();
                            long time = location.getTime();
                            if (time > this.f7493c && accuracy < bestAccuracy) {
                                bestResult = location;
                                bestAccuracy = accuracy;
                                bestTime = time;
                            } else if (time < this.f7493c && bestAccuracy == Float.MAX_VALUE && time > bestTime) {
                                bestResult = location;
                                bestTime = time;
                            }
                        }
                    }
                }
            }
        } catch (SecurityException e) {
            str = f7491a;
        } catch (Exception e2) {
            C4549w.m8594c(f7491a, e2.getMessage());
        }
        if (bestResult != null) {
            m8403a(bestResult, false);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0100 A:{ExcHandler: Exception (r16_4 'e' java.lang.Exception), Splitter:B:7:0x0047} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0100 A:{ExcHandler: Exception (r16_4 'e' java.lang.Exception), Splitter:B:7:0x0047} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:25:0x0100, code skipped:
            r16 = move-exception;
     */
    /* JADX WARNING: Missing block: B:26:0x0101, code skipped:
            com.threatmetrix.TrustDefender.C4549w.m8594c(f7491a, r16.getMessage());
            r21.f7501k = null;
     */
    /* JADX WARNING: Missing block: B:49:0x01b1, code skipped:
            r16 = move-exception;
     */
    /* JADX WARNING: Missing block: B:50:0x01b2, code skipped:
            com.threatmetrix.TrustDefender.C4549w.m8592b(f7491a, "Failed to register locationServices: " + r16.getMessage());
     */
    /* JADX WARNING: Missing block: B:51:0x01cb, code skipped:
            r16 = move-exception;
     */
    /* JADX WARNING: Missing block: B:52:0x01cc, code skipped:
            com.threatmetrix.TrustDefender.C4549w.m8592b(f7491a, "Failed to register locationServices: " + r16.getMessage());
     */
    /* JADX WARNING: Missing block: B:53:0x01e5, code skipped:
            r16 = move-exception;
     */
    /* JADX WARNING: Missing block: B:54:0x01e6, code skipped:
            com.threatmetrix.TrustDefender.C4549w.m8592b(f7491a, "Failed to register locationServices: " + r16.getMessage());
     */
    /* JADX WARNING: Missing block: B:55:0x01ff, code skipped:
            r16 = move-exception;
     */
    /* JADX WARNING: Missing block: B:56:0x0200, code skipped:
            com.threatmetrix.TrustDefender.C4549w.m8592b(f7491a, "Failed to register locationServices: " + r16.getMessage());
     */
    /* JADX WARNING: Missing block: B:61:?, code skipped:
            return;
     */
    /* renamed from: j */
    private void m8408j() {
        /*
        r21 = this;
        r0 = r21;
        r3 = r0.f7498h;
        if (r3 == 0) goto L_0x000c;
    L_0x0006:
        r0 = r21;
        r3 = r0.f7502l;
        if (r3 != 0) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        r3 = r21.m8406h();
        if (r3 != 0) goto L_0x000c;
    L_0x0013:
        r3 = f7491a;
        r4 = new java.lang.StringBuilder;
        r5 = "registerLocationServices: low power ";
        r4.<init>(r5);
        r0 = r21;
        r8 = r0.f7493c;
        r4 = r4.append(r8);
        r5 = " high power ";
        r4 = r4.append(r5);
        r0 = r21;
        r8 = r0.f7494d;
        r4 = r4.append(r8);
        r5 = " with accuracy ";
        r4 = r4.append(r5);
        r0 = r21;
        r5 = r0.f7492b;
        r4 = r4.append(r5);
        r4 = r4.toString();
        com.threatmetrix.TrustDefender.C4549w.m8594c(r3, r4);
        r0 = r21;
        r3 = r0.f7499i;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = "location";
        r18 = r3.getSystemService(r4);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        if (r18 == 0) goto L_0x0059;
    L_0x0053:
        r0 = r18;
        r3 = r0 instanceof android.location.LocationManager;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        if (r3 != 0) goto L_0x006a;
    L_0x0059:
        r3 = f7491a;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = "Location Manager is not available";
        com.threatmetrix.TrustDefender.C4549w.m8587a(r3, r4);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        goto L_0x000c;
    L_0x0061:
        r3 = move-exception;
        r3 = f7491a;
        r3 = 0;
        r0 = r21;
        r0.f7501k = r3;
        goto L_0x000c;
    L_0x006a:
        r18 = (android.location.LocationManager) r18;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r0 = r18;
        r1 = r21;
        r1.f7501k = r0;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r0 = r21;
        r3 = r0.f7501k;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r0 = r21;
        r4 = r0.f7502l;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r3.removeUpdates(r4);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r13 = new android.location.Criteria;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r13.<init>();	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r0 = r21;
        r3 = r0.f7492b;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r13.setAccuracy(r3);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r3 = 0;
        r13.setAltitudeRequired(r3);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r3 = 0;
        r13.setBearingAccuracy(r3);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r3 = 0;
        r13.setCostAllowed(r3);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r3 = 0;
        r13.setSpeedAccuracy(r3);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r3 = 0;
        r13.setSpeedRequired(r3);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r3 = 0;
        r13.setVerticalAccuracy(r3);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r7 = new android.location.Criteria;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r7.<init>();	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r3 = 1;
        r7.setPowerRequirement(r3);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r3 = 2;
        r7.setAccuracy(r3);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r0 = r21;
        r3 = r0.f7501k;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = 1;
        r17 = r3.getBestProvider(r13, r4);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        if (r17 == 0) goto L_0x00cf;
    L_0x00b9:
        r3 = f7491a;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r5 = "fine provider: ";
        r4.<init>(r5);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r0 = r17;
        r4 = r4.append(r0);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = r4.toString();	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        com.threatmetrix.TrustDefender.C4549w.m8594c(r3, r4);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
    L_0x00cf:
        r0 = r21;
        r3 = r0.f7501k;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = 1;
        r2 = r3.getBestProvider(r7, r4);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        if (r2 == 0) goto L_0x00ee;
    L_0x00da:
        r3 = f7491a;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r5 = "course provider: ";
        r4.<init>(r5);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = r4.append(r2);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = r4.toString();	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        com.threatmetrix.TrustDefender.C4549w.m8594c(r3, r4);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
    L_0x00ee:
        if (r17 != 0) goto L_0x0111;
    L_0x00f0:
        if (r2 != 0) goto L_0x0111;
    L_0x00f2:
        r3 = 0;
        r0 = r21;
        r0.f7501k = r3;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r3 = f7491a;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = "Unable to find location provider, possibly incorrect permissions. Check that android.permission.ACCESS_COARSE_LOCATION or android.permission.ACCESS_FINE_LOCATION is set";
        com.threatmetrix.TrustDefender.C4549w.m8587a(r3, r4);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        goto L_0x000c;
    L_0x0100:
        r16 = move-exception;
        r3 = f7491a;
        r4 = r16.getMessage();
        com.threatmetrix.TrustDefender.C4549w.m8594c(r3, r4);
        r3 = 0;
        r0 = r21;
        r0.f7501k = r3;
        goto L_0x000c;
    L_0x0111:
        r21.m8407i();	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r20 = 0;
        if (r17 == 0) goto L_0x0120;
    L_0x0118:
        if (r2 == 0) goto L_0x0120;
    L_0x011a:
        r0 = r17;
        r20 = r0.equals(r2);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
    L_0x0120:
        r19 = 0;
        r0 = r21;
        r3 = r0.f7500j;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        if (r3 == 0) goto L_0x0133;
    L_0x0128:
        r0 = r21;
        r3 = r0.f7502l;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r0 = r21;
        r4 = r0.f7500j;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r3.onLocationChanged(r4);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
    L_0x0133:
        r0 = r21;
        r3 = r0.f7504n;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = com.threatmetrix.TrustDefender.C4505ap.C4502a.NONE;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        if (r3 == r4) goto L_0x016e;
    L_0x013b:
        r0 = r21;
        r3 = r0.f7501k;	 Catch:{ IllegalArgumentException -> 0x01b1, SecurityException -> 0x01cb, Exception -> 0x0100 }
        r0 = r21;
        r4 = r0.f7493c;	 Catch:{ IllegalArgumentException -> 0x01b1, SecurityException -> 0x01cb, Exception -> 0x0100 }
        r6 = 0;
        r0 = r21;
        r8 = r0.f7502l;	 Catch:{ IllegalArgumentException -> 0x01b1, SecurityException -> 0x01cb, Exception -> 0x0100 }
        r9 = android.os.Looper.getMainLooper();	 Catch:{ IllegalArgumentException -> 0x01b1, SecurityException -> 0x01cb, Exception -> 0x0100 }
        r3.requestLocationUpdates(r4, r6, r7, r8, r9);	 Catch:{ IllegalArgumentException -> 0x01b1, SecurityException -> 0x01cb, Exception -> 0x0100 }
        r19 = 1;
        r3 = f7491a;	 Catch:{ IllegalArgumentException -> 0x01b1, SecurityException -> 0x01cb, Exception -> 0x0100 }
        r4 = new java.lang.StringBuilder;	 Catch:{ IllegalArgumentException -> 0x01b1, SecurityException -> 0x01cb, Exception -> 0x0100 }
        r5 = "LocationManager created: ";
        r4.<init>(r5);	 Catch:{ IllegalArgumentException -> 0x01b1, SecurityException -> 0x01cb, Exception -> 0x0100 }
        r0 = r21;
        r5 = r0.f7501k;	 Catch:{ IllegalArgumentException -> 0x01b1, SecurityException -> 0x01cb, Exception -> 0x0100 }
        r6 = 1;
        r5 = r5.getBestProvider(r7, r6);	 Catch:{ IllegalArgumentException -> 0x01b1, SecurityException -> 0x01cb, Exception -> 0x0100 }
        r4 = r4.append(r5);	 Catch:{ IllegalArgumentException -> 0x01b1, SecurityException -> 0x01cb, Exception -> 0x0100 }
        r4 = r4.toString();	 Catch:{ IllegalArgumentException -> 0x01b1, SecurityException -> 0x01cb, Exception -> 0x0100 }
        com.threatmetrix.TrustDefender.C4549w.m8594c(r3, r4);	 Catch:{ IllegalArgumentException -> 0x01b1, SecurityException -> 0x01cb, Exception -> 0x0100 }
    L_0x016e:
        if (r20 != 0) goto L_0x01a8;
    L_0x0170:
        r0 = r21;
        r3 = r0.f7504n;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = com.threatmetrix.TrustDefender.C4505ap.C4502a.NONE;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        if (r3 == r4) goto L_0x01a8;
    L_0x0178:
        r0 = r21;
        r9 = r0.f7501k;	 Catch:{ IllegalArgumentException -> 0x01e5, SecurityException -> 0x01ff, Exception -> 0x0100 }
        r0 = r21;
        r10 = r0.f7494d;	 Catch:{ IllegalArgumentException -> 0x01e5, SecurityException -> 0x01ff, Exception -> 0x0100 }
        r12 = 0;
        r0 = r21;
        r14 = r0.f7502l;	 Catch:{ IllegalArgumentException -> 0x01e5, SecurityException -> 0x01ff, Exception -> 0x0100 }
        r15 = 0;
        r9.requestLocationUpdates(r10, r12, r13, r14, r15);	 Catch:{ IllegalArgumentException -> 0x01e5, SecurityException -> 0x01ff, Exception -> 0x0100 }
        r19 = 1;
        r3 = f7491a;	 Catch:{ IllegalArgumentException -> 0x01e5, SecurityException -> 0x01ff, Exception -> 0x0100 }
        r4 = new java.lang.StringBuilder;	 Catch:{ IllegalArgumentException -> 0x01e5, SecurityException -> 0x01ff, Exception -> 0x0100 }
        r5 = "LocationManager created: ";
        r4.<init>(r5);	 Catch:{ IllegalArgumentException -> 0x01e5, SecurityException -> 0x01ff, Exception -> 0x0100 }
        r0 = r21;
        r5 = r0.f7501k;	 Catch:{ IllegalArgumentException -> 0x01e5, SecurityException -> 0x01ff, Exception -> 0x0100 }
        r6 = 1;
        r5 = r5.getBestProvider(r13, r6);	 Catch:{ IllegalArgumentException -> 0x01e5, SecurityException -> 0x01ff, Exception -> 0x0100 }
        r4 = r4.append(r5);	 Catch:{ IllegalArgumentException -> 0x01e5, SecurityException -> 0x01ff, Exception -> 0x0100 }
        r4 = r4.toString();	 Catch:{ IllegalArgumentException -> 0x01e5, SecurityException -> 0x01ff, Exception -> 0x0100 }
        com.threatmetrix.TrustDefender.C4549w.m8594c(r3, r4);	 Catch:{ IllegalArgumentException -> 0x01e5, SecurityException -> 0x01ff, Exception -> 0x0100 }
    L_0x01a8:
        if (r19 != 0) goto L_0x000c;
    L_0x01aa:
        r3 = 0;
        r0 = r21;
        r0.f7501k = r3;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        goto L_0x000c;
    L_0x01b1:
        r16 = move-exception;
        r3 = f7491a;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r5 = "Failed to register locationServices: ";
        r4.<init>(r5);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r5 = r16.getMessage();	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = r4.append(r5);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = r4.toString();	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        com.threatmetrix.TrustDefender.C4549w.m8592b(r3, r4);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        goto L_0x016e;
    L_0x01cb:
        r16 = move-exception;
        r3 = f7491a;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r5 = "Failed to register locationServices: ";
        r4.<init>(r5);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r5 = r16.getMessage();	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = r4.append(r5);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = r4.toString();	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        com.threatmetrix.TrustDefender.C4549w.m8592b(r3, r4);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        goto L_0x016e;
    L_0x01e5:
        r16 = move-exception;
        r3 = f7491a;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r5 = "Failed to register locationServices: ";
        r4.<init>(r5);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r5 = r16.getMessage();	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = r4.append(r5);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = r4.toString();	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        com.threatmetrix.TrustDefender.C4549w.m8592b(r3, r4);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        goto L_0x01a8;
    L_0x01ff:
        r16 = move-exception;
        r3 = f7491a;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = new java.lang.StringBuilder;	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r5 = "Failed to register locationServices: ";
        r4.<init>(r5);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r5 = r16.getMessage();	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = r4.append(r5);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        r4 = r4.toString();	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        com.threatmetrix.TrustDefender.C4549w.m8592b(r3, r4);	 Catch:{ SecurityException -> 0x0061, Exception -> 0x0100 }
        goto L_0x01a8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4505ap.m8408j():void");
    }
}
