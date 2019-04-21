package com.aps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import com.amap.api.location.LocationManagerProxy;
import com.facebook.internal.ServerProtocol;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

/* renamed from: com.aps.aa */
public class C1213aa {
    /* renamed from: P */
    private static float f4134P = 1.1f;
    /* renamed from: Q */
    private static float f4135Q = 2.2f;
    /* renamed from: R */
    private static float f4136R = 2.3f;
    /* renamed from: S */
    private static float f4137S = 3.8f;
    /* renamed from: T */
    private static int f4138T = 3;
    /* renamed from: U */
    private static int f4139U = 10;
    /* renamed from: V */
    private static int f4140V = 2;
    /* renamed from: W */
    private static int f4141W = 7;
    /* renamed from: X */
    private static int f4142X = 20;
    /* renamed from: Y */
    private static int f4143Y = 70;
    /* renamed from: Z */
    private static int f4144Z = 120;
    /* renamed from: a */
    protected static boolean f4145a = false;
    /* renamed from: b */
    protected static boolean f4146b = true;
    /* renamed from: c */
    private static int f4147c = 10;
    /* renamed from: d */
    private static int f4148d = 2;
    /* renamed from: e */
    private static int f4149e = 10;
    /* renamed from: f */
    private static int f4150f = 10;
    /* renamed from: g */
    private static int f4151g = 50;
    /* renamed from: h */
    private static int f4152h = 200;
    /* renamed from: i */
    private static Object f4153i = new Object();
    /* renamed from: j */
    private static C1213aa f4154j;
    /* renamed from: A */
    private Thread f4155A = null;
    /* renamed from: B */
    private Looper f4156B = null;
    /* renamed from: C */
    private C1236ax f4157C = null;
    /* renamed from: D */
    private Location f4158D = null;
    /* renamed from: E */
    private C1235aw f4159E = null;
    /* renamed from: F */
    private Handler f4160F = null;
    /* renamed from: G */
    private C1237ay f4161G = new C1237ay(this);
    /* renamed from: H */
    private LocationListener f4162H = new C1231as(this);
    /* renamed from: I */
    private BroadcastReceiver f4163I = new C1232at(this);
    /* renamed from: J */
    private GpsStatus f4164J = null;
    /* renamed from: K */
    private int f4165K = 0;
    /* renamed from: L */
    private int f4166L = 0;
    /* renamed from: M */
    private HashMap f4167M = null;
    /* renamed from: N */
    private int f4168N = 0;
    /* renamed from: O */
    private int f4169O = 0;
    /* renamed from: k */
    private boolean f4170k = false;
    /* renamed from: l */
    private boolean f4171l = false;
    /* renamed from: m */
    private int f4172m = -1;
    /* renamed from: n */
    private int f4173n = 0;
    /* renamed from: o */
    private int f4174o = 0;
    /* renamed from: p */
    private int f4175p = 10000;
    /* renamed from: q */
    private long f4176q = 0;
    /* renamed from: r */
    private Context f4177r;
    /* renamed from: s */
    private LocationManager f4178s;
    /* renamed from: t */
    private C1225am f4179t;
    /* renamed from: u */
    private C1244ba f4180u;
    /* renamed from: v */
    private C1251bh f4181v;
    /* renamed from: w */
    private C1222aj f4182w;
    /* renamed from: x */
    private C1250bg f4183x;
    /* renamed from: y */
    private C1238az f4184y;
    /* renamed from: z */
    private C1216ad f4185z;

    private C1213aa(Context context) {
        this.f4177r = context;
        this.f4179t = C1225am.m5360a(context);
        this.f4185z = new C1216ad();
        this.f4180u = new C1244ba(this.f4179t);
        this.f4182w = new C1222aj(context);
        this.f4181v = new C1251bh(this.f4182w);
        this.f4183x = new C1250bg(this.f4182w);
        this.f4178s = (LocationManager) this.f4177r.getSystemService(LocationManagerProxy.KEY_LOCATION_CHANGED);
        this.f4184y = C1238az.m5431a(this.f4177r);
        this.f4184y.mo13154a(this.f4161G);
        m5311n();
        List allProviders = this.f4178s.getAllProviders();
        boolean z = allProviders != null && allProviders.contains("gps") && allProviders.contains("passive");
        this.f4171l = z;
        C1252bi.m5554a(context);
    }

    /* renamed from: a */
    static /* synthetic */ int m5270a(C1213aa c1213aa, C1272y c1272y, int i) {
        if (c1213aa.f4168N >= f4139U) {
            return 1;
        }
        if (c1213aa.f4168N <= f4138T) {
            return 4;
        }
        double c = c1272y.mo13302c();
        if (c <= ((double) f4134P)) {
            return 1;
        }
        if (c >= ((double) f4135Q)) {
            return 4;
        }
        c = c1272y.mo13301b();
        return c > ((double) f4136R) ? c >= ((double) f4137S) ? 4 : i < f4141W ? i <= f4140V ? 4 : c1213aa.f4167M != null ? c1213aa.m5271a(c1213aa.f4167M) : 3 : 1 : 1;
    }

    /* renamed from: a */
    private int m5271a(HashMap hashMap) {
        if (this.f4165K > 4) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int i = 0;
            Iterator it = hashMap.entrySet().iterator();
            while (true) {
                int i2 = i;
                if (!it.hasNext()) {
                    break;
                }
                List list = (List) ((Entry) it.next()).getValue();
                if (list != null) {
                    double[] a = m5284a(list);
                    if (a != null) {
                        arrayList.add(a);
                        i = i2 + 1;
                        arrayList2.add(Integer.valueOf(i2));
                    }
                }
                i = i2;
            }
            if (!arrayList.isEmpty()) {
                double[] dArr = new double[2];
                int size = arrayList.size();
                for (int i3 = 0; i3 < size; i3++) {
                    double[] dArr2 = (double[]) arrayList.get(i3);
                    i = ((Integer) arrayList2.get(i3)).intValue();
                    dArr2[0] = dArr2[0] * ((double) i);
                    dArr2[1] = dArr2[1] * ((double) i);
                    dArr[0] = dArr[0] + dArr2[0];
                    dArr[1] = dArr[1] + dArr2[1];
                }
                dArr[0] = dArr[0] / ((double) size);
                dArr[1] = dArr[1] / ((double) size);
                double d = dArr[0];
                double d2 = dArr[1];
                double toDegrees = d2 == 0.0d ? d > 0.0d ? 90.0d : d < 0.0d ? 270.0d : 0.0d : Math.toDegrees(Math.atan(d / d2));
                double[] dArr3 = new double[]{Math.sqrt((d * d) + (d2 * d2)), toDegrees};
                String.format(Locale.CHINA, "%d,%d,%d,%d", new Object[]{Long.valueOf(Math.round(dArr[0] * 100.0d)), Long.valueOf(Math.round(dArr[1] * 100.0d)), Long.valueOf(Math.round(dArr3[0] * 100.0d)), Long.valueOf(Math.round(dArr3[1] * 100.0d))});
                if (dArr3[0] <= ((double) f4143Y)) {
                    return 1;
                }
                if (dArr3[0] >= ((double) f4144Z)) {
                    return 4;
                }
            }
        }
        return 3;
    }

    /* renamed from: a */
    public static C1213aa m5277a(Context context) {
        if (f4154j == null) {
            synchronized (f4153i) {
                if (f4154j == null) {
                    f4154j = new C1213aa(context);
                }
            }
        }
        return f4154j;
    }

    /* renamed from: a */
    public static String m5279a(String str) {
        return str.equals("version") ? "COL.14.1126r" : null;
    }

    /* renamed from: a */
    static /* synthetic */ void m5282a(C1213aa c1213aa, Location location, int i, long j) {
        boolean z;
        Location location2;
        C1215ac a;
        Long valueOf;
        System.currentTimeMillis();
        boolean a2 = c1213aa.f4180u.mo13185a(location);
        if (a2) {
            c1213aa.f4180u.f4356b.f4359b = new Location(location);
        }
        boolean b = c1213aa.f4180u.mo13186b(location);
        if (b) {
            c1213aa.f4180u.f4355a.f4367b = new Location(location);
        }
        int i2 = 0;
        if (i == 1) {
            z = true;
            a2 = true;
            location2 = c1213aa.f4158D;
        } else if (i == 2) {
            z = false;
            a2 = true;
            location2 = c1213aa.f4158D;
        } else {
            z = a2;
            a2 = b;
            location2 = location;
        }
        if (z) {
            i2 = 1;
            if (a2) {
                i2 = 3;
            }
        } else if (a2) {
            i2 = 2;
        }
        try {
            C1216ad c1216ad = c1213aa.f4185z;
            a = C1216ad.m5325a(location2, c1213aa.f4179t, i2, (byte) c1213aa.f4169O, j, false);
        } catch (Exception e) {
            a = null;
        }
        if (!(a == null || c1213aa.f4179t == null)) {
            List n = c1213aa.f4179t.mo13122n();
            valueOf = Long.valueOf(0);
            if (n != null && n.size() > 0) {
                valueOf = (Long) n.get(0);
            }
            c1213aa.f4181v.mo13190a(valueOf.longValue(), a.mo13086a());
        }
        if (c1213aa.f4177r != null && c1213aa.f4185z != null) {
            SharedPreferences sharedPreferences = c1213aa.f4177r.getSharedPreferences("app_pref", 0);
            if (!sharedPreferences.getString("get_sensor", "").equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                try {
                    C1216ad c1216ad2 = c1213aa.f4185z;
                    a = C1216ad.m5325a(null, c1213aa.f4179t, i2, (byte) c1213aa.f4169O, j, true);
                } catch (Exception e2) {
                    a = null;
                }
                if (a != null && c1213aa.f4179t != null) {
                    List n2 = c1213aa.f4179t.mo13122n();
                    valueOf = Long.valueOf(0);
                    if (n2 != null && n2.size() > 0) {
                        valueOf = (Long) n2.get(0);
                    }
                    c1213aa.f4181v.mo13190a(valueOf.longValue(), a.mo13086a());
                    sharedPreferences.edit().putString("get_sensor", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE).commit();
                }
            }
        }
    }

    /* renamed from: a */
    private double[] m5284a(List list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        double[] dArr = new double[2];
        for (GpsSatellite gpsSatellite : list) {
            if (gpsSatellite != null) {
                double elevation = (double) (90.0f - gpsSatellite.getElevation());
                double azimuth = (double) gpsSatellite.getAzimuth();
                double[] dArr2 = new double[]{Math.sin(Math.toRadians(azimuth)) * elevation, elevation * Math.cos(Math.toRadians(azimuth))};
                dArr[0] = dArr[0] + dArr2[0];
                dArr[1] = dArr[1] + dArr2[1];
            }
        }
        int size = list.size();
        dArr[0] = dArr[0] / ((double) size);
        dArr[1] = dArr[1] / ((double) size);
        return dArr;
    }

    /* renamed from: n */
    private void m5311n() {
        this.f4173n = this.f4184y.mo13157b() * 1000;
        this.f4174o = this.f4184y.mo13159c();
        C1244ba c1244ba = this.f4180u;
        int i = this.f4173n;
        i = this.f4174o;
        C1244ba.m5538a();
    }

    /* renamed from: a */
    public void mo13077a() {
        C1249bf.f4368a = true;
        if (this.f4171l && this.f4179t != null && !f4145a) {
            IntentFilter intentFilter = new IntentFilter("android.location.GPS_ENABLED_CHANGE");
            intentFilter.addAction("android.location.GPS_FIX_CHANGE");
            f4146b = true;
            this.f4177r.registerReceiver(this.f4163I, intentFilter);
            String str = "";
            this.f4178s.removeUpdates(this.f4162H);
            if (this.f4156B != null) {
                this.f4156B.quit();
                this.f4156B = null;
            }
            if (this.f4155A != null) {
                this.f4155A.interrupt();
                this.f4155A = null;
            }
            this.f4155A = new C1233au(this, str);
            this.f4155A.start();
            this.f4179t.mo13101a();
            f4145a = true;
        }
    }

    /* renamed from: a */
    public void mo13078a(int i) {
        if (i == 256 || i == 8736 || i == 768) {
            this.f4182w.mo13095a(i);
            return;
        }
        throw new RuntimeException("invalid Size! must be COLLECTOR_SMALL_SIZE or COLLECTOR_BIG_SIZE or COLLECTOR_MEDIUM_SIZE");
    }

    /* renamed from: a */
    public void mo13079a(C1221ai c1221ai, String str) {
        boolean a = this.f4184y.mo13156a(str);
        if (c1221ai != null) {
            byte[] a2 = c1221ai.mo13090a();
            if (a && a2 != null) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f4177r.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    if (activeNetworkInfo.getType() == 1) {
                        this.f4184y.mo13153a(a2.length + this.f4184y.mo13161e());
                    } else {
                        this.f4184y.mo13158b(a2.length + this.f4184y.mo13162f());
                    }
                }
            }
            c1221ai.mo13089a(a);
            this.f4183x.mo13189a(c1221ai);
        }
    }

    /* renamed from: b */
    public void mo13080b() {
        C1249bf.f4368a = false;
        if (this.f4171l && this.f4179t != null && f4145a) {
            if (this.f4163I != null) {
                try {
                    this.f4177r.unregisterReceiver(this.f4163I);
                } catch (Exception e) {
                }
            }
            if (this.f4179t != null) {
                this.f4179t.mo13131w();
            }
            this.f4178s.removeGpsStatusListener(this.f4159E);
            this.f4178s.removeNmeaListener(this.f4159E);
            this.f4159E = null;
            this.f4178s.removeUpdates(this.f4162H);
            if (this.f4156B != null) {
                this.f4156B.quit();
                this.f4156B = null;
            }
            if (this.f4155A != null) {
                this.f4155A.interrupt();
                this.f4155A = null;
            }
            if (this.f4157C != null) {
                this.f4170k = false;
                this.f4157C.interrupt();
                this.f4157C = null;
            }
            this.f4179t.mo13104b();
            f4145a = false;
        }
    }

    /* renamed from: c */
    public void mo13081c() {
        if (this.f4171l) {
            mo13080b();
        }
    }

    /* renamed from: d */
    public C1221ai mo13082d() {
        if (this.f4183x == null) {
            return null;
        }
        mo13083e();
        return this.f4184y.mo13155a() ? this.f4183x.mo13188a(this.f4184y.mo13160d()) : null;
    }

    /* renamed from: e */
    public boolean mo13083e() {
        if (this.f4179t == null) {
            return false;
        }
        List n = this.f4179t.mo13122n();
        return (n == null || n.size() <= 0) ? false : this.f4182w.mo13097b(((Long) n.get(0)).longValue());
    }

    /* renamed from: f */
    public int mo13084f() {
        return this.f4183x != null ? this.f4183x.mo13187a() : 0;
    }
}
