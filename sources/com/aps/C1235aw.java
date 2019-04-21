package com.aps;

import android.location.GpsSatellite;
import android.location.GpsStatus.Listener;
import android.location.GpsStatus.NmeaListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* renamed from: com.aps.aw */
public final class C1235aw implements Listener, NmeaListener {
    /* renamed from: a */
    private long f4284a = 0;
    /* renamed from: b */
    private long f4285b = 0;
    /* renamed from: c */
    private boolean f4286c = false;
    /* renamed from: d */
    private List f4287d = new ArrayList();
    /* renamed from: e */
    private String f4288e = null;
    /* renamed from: f */
    private String f4289f = null;
    /* renamed from: g */
    private String f4290g = null;
    /* renamed from: h */
    private /* synthetic */ C1213aa f4291h;

    protected C1235aw(C1213aa c1213aa) {
        this.f4291h = c1213aa;
    }

    /* renamed from: a */
    public final void mo13148a(String str) {
        if (System.currentTimeMillis() - this.f4285b > 400 && this.f4286c && this.f4287d.size() > 0) {
            try {
                C1272y c1272y = new C1272y(this.f4287d, this.f4288e, null, this.f4290g);
                if (c1272y.mo13300a()) {
                    this.f4291h.f4169O = C1213aa.m5270a(this.f4291h, c1272y, this.f4291h.f4166L);
                    if (this.f4291h.f4169O > 0) {
                        C1213aa.m5288b(this.f4291h, String.format(Locale.CHINA, "&nmea=%.1f|%.1f&g_tp=%d", new Object[]{Double.valueOf(c1272y.mo13302c()), Double.valueOf(c1272y.mo13301b()), Integer.valueOf(this.f4291h.f4169O)}));
                    }
                } else {
                    this.f4291h.f4169O = 0;
                }
            } catch (Exception e) {
                this.f4291h.f4169O = 0;
            }
            this.f4287d.clear();
            this.f4290g = null;
            this.f4289f = null;
            this.f4288e = null;
            this.f4286c = false;
        }
        if (str.startsWith("$GPGGA")) {
            this.f4286c = true;
            this.f4288e = str.trim();
        } else if (str.startsWith("$GPGSV")) {
            this.f4287d.add(str.trim());
        } else if (str.startsWith("$GPGSA")) {
            this.f4290g = str.trim();
        }
        this.f4285b = System.currentTimeMillis();
    }

    public final void onGpsStatusChanged(int i) {
        int i2 = 0;
        try {
            if (this.f4291h.f4178s != null) {
                switch (i) {
                    case 2:
                        this.f4291h.f4168N = 0;
                        return;
                    case 4:
                        if (C1213aa.f4145a || System.currentTimeMillis() - this.f4284a >= 10000) {
                            if (this.f4291h.f4164J == null) {
                                this.f4291h.f4164J = this.f4291h.f4178s.getGpsStatus(null);
                            } else {
                                this.f4291h.f4178s.getGpsStatus(this.f4291h.f4164J);
                            }
                            this.f4291h.f4165K = 0;
                            this.f4291h.f4166L = 0;
                            this.f4291h.f4167M = new HashMap();
                            int i3 = 0;
                            int i4 = 0;
                            for (GpsSatellite gpsSatellite : this.f4291h.f4164J.getSatellites()) {
                                i3++;
                                if (gpsSatellite.usedInFix()) {
                                    i4++;
                                }
                                if (gpsSatellite.getSnr() > 0.0f) {
                                    i2++;
                                }
                                if (gpsSatellite.getSnr() >= ((float) C1213aa.f4142X)) {
                                    this.f4291h.f4166L = this.f4291h.f4166L + 1;
                                }
                            }
                            if (this.f4291h.f4172m == -1 || ((i4 >= 4 && this.f4291h.f4172m < 4) || (i4 < 4 && this.f4291h.f4172m >= 4))) {
                                this.f4291h.f4172m = i4;
                                if (i4 < 4) {
                                    if (this.f4291h.f4179t != null) {
                                        this.f4291h.f4179t.mo13131w();
                                    }
                                } else if (this.f4291h.f4179t != null) {
                                    this.f4291h.f4179t.mo13130v();
                                }
                            }
                            this.f4291h.f4168N = i2;
                            this.f4291h.m5271a(this.f4291h.f4167M);
                            if (!C1213aa.f4145a) {
                                if ((i4 > 3 || i3 > 15) && this.f4291h.f4178s.getLastKnownLocation("gps") != null) {
                                    this.f4284a = System.currentTimeMillis();
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        } catch (Exception e) {
        }
    }

    public final void onNmeaReceived(long j, String str) {
        try {
            if (C1213aa.f4145a && str != null && !str.equals("") && str.length() >= 9 && str.length() <= 150) {
                this.f4291h.f4160F.sendMessage(this.f4291h.f4160F.obtainMessage(1, str));
            }
        } catch (Exception e) {
        }
    }
}
