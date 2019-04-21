package com.amap.api.location;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.amap.api.location.AMapLocationManager.C0723a;
import com.amap.api.location.core.AMapLocException;
import java.util.Iterator;

/* renamed from: com.amap.api.location.d */
public class IGPSManager {
    /* renamed from: a */
    public LocationManager f940a = null;
    /* renamed from: b */
    LocationListener f941b = new C0729e(this);
    /* renamed from: c */
    LocationListener f942c = new C0730f(this);
    /* renamed from: d */
    private C0723a f943d;
    /* renamed from: e */
    private AMapLocationManager f944e;
    /* renamed from: f */
    private Context f945f;
    /* renamed from: g */
    private final Listener f946g = new C0731g(this);

    IGPSManager(Context context, LocationManager locationManager, C0723a c0723a, AMapLocationManager aMapLocationManager) {
        this.f945f = context;
        this.f940a = locationManager;
        this.f944e = aMapLocationManager;
        this.f943d = c0723a;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8413a(long j, float f) {
        try {
            Looper mainLooper = this.f945f.getMainLooper();
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            this.f940a.addGpsStatusListener(this.f946g);
            this.f940a.requestLocationUpdates("gps", j, f, this.f942c, mainLooper);
            this.f940a.requestLocationUpdates("gps", 5000, 0.0f, this.f941b, mainLooper);
        } catch (SecurityException e) {
            Message obtain = Message.obtain();
            AMapLocation aMapLocation = new AMapLocation("");
            aMapLocation.setAMapException(new AMapLocException(AMapLocException.ERROR_FAILURE_INFO));
            aMapLocation.setProvider("gps");
            obtain.obj = aMapLocation;
            obtain.what = 100;
            if (this.f943d != null) {
                this.f943d.sendMessage(obtain);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8412a() {
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo8414b() {
        if (this.f942c != null) {
            this.f940a.removeUpdates(this.f942c);
        }
        if (this.f946g != null) {
            this.f940a.removeGpsStatusListener(this.f946g);
        }
        if (this.f941b != null) {
            this.f940a.removeUpdates(this.f941b);
        }
    }

    /* renamed from: a */
    private void m1483a(int i, GpsStatus gpsStatus) {
        boolean z = false;
        if (i == 4) {
            boolean z2;
            boolean maxSatellites = gpsStatus.getMaxSatellites();
            Iterator it = gpsStatus.getSatellites().iterator();
            while (true) {
                z2 = z;
                if (it.hasNext() && z2 <= maxSatellites) {
                    if (((GpsSatellite) it.next()).usedInFix()) {
                        z = z2 + 1;
                    } else {
                        z = z2;
                    }
                }
            }
            if (z2 >= true) {
                this.f944e.f846i = SystemClock.elapsedRealtime();
            }
        } else if (i != 1 && i == 2) {
            this.f944e.mo8361b(false);
        }
    }
}
