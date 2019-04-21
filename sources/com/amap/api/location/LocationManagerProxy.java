package com.amap.api.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import com.amap.api.location.core.CoreUtil;
import com.amap.api.location.core.SocketService;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class LocationManagerProxy {
    public static final String GPS_PROVIDER = "gps";
    public static final String KEY_LOCATION_CHANGED = "location";
    public static final String KEY_PROVIDER_ENABLED = "providerEnabled";
    public static final String KEY_PROXIMITY_ENTERING = "entering";
    public static final String KEY_STATUS_CHANGED = "status";
    public static final String NETWORK_PROVIDER = "network";
    public static final int WEATHER_TYPE_FORECAST = 2;
    public static final int WEATHER_TYPE_LIVE = 1;
    /* renamed from: b */
    static Object f815b = new Object();
    /* renamed from: d */
    private static LocationManagerProxy f816d = null;
    /* renamed from: a */
    Context f817a;
    /* renamed from: c */
    private LocationManager f818c = null;
    /* renamed from: e */
    private AMapLocationManager f819e = null;
    /* renamed from: f */
    private Context f820f;
    /* renamed from: g */
    private LocationListenerProxy f821g;
    /* renamed from: h */
    private C0719b f822h;
    /* renamed from: i */
    private ArrayList<PendingIntent> f823i = new ArrayList();
    /* renamed from: j */
    private Hashtable<String, LocationProviderProxy> f824j = new Hashtable();
    /* renamed from: k */
    private Vector<RequestLocationEntity> f825k = new Vector();
    /* renamed from: l */
    private Vector<RequestLocationEntity> f826l = new Vector();
    /* renamed from: m */
    private C0718a f827m = new C0718a();

    /* renamed from: com.amap.api.location.LocationManagerProxy$a */
    class C0718a implements AMapLocationListener {
        C0718a() {
        }

        public void onLocationChanged(Location location) {
            int i = 0;
            int i2;
            RequestLocationEntity requestLocationEntity;
            if (location != null) {
                try {
                    AMapLocation aMapLocation = new AMapLocation(location);
                    i2 = 0;
                    while (LocationManagerProxy.this.f825k != null && i2 < LocationManagerProxy.this.f825k.size()) {
                        requestLocationEntity = (RequestLocationEntity) LocationManagerProxy.this.f825k.get(i2);
                        if (requestLocationEntity != null) {
                            try {
                                if (requestLocationEntity.f953b != null) {
                                    requestLocationEntity.f953b.onLocationChanged(aMapLocation);
                                }
                            } catch (Throwable th) {
                            }
                        }
                        if (requestLocationEntity != null) {
                            if (requestLocationEntity.f952a == -1 && LocationManagerProxy.this.f826l != null) {
                                LocationManagerProxy.this.f826l.add(requestLocationEntity);
                            }
                        }
                        i2++;
                    }
                    if (LocationManagerProxy.this.f826l != null && LocationManagerProxy.this.f826l.size() > 0 && LocationManagerProxy.this.f825k != null) {
                        while (i < LocationManagerProxy.this.f826l.size()) {
                            LocationManagerProxy.this.f825k.remove(LocationManagerProxy.this.f826l.get(i));
                            i++;
                        }
                        LocationManagerProxy.this.f826l.clear();
                        if (LocationManagerProxy.this.f825k.size() == 0 && LocationManagerProxy.this.f818c != null && LocationManagerProxy.this.f827m != null) {
                            LocationManagerProxy.this.f818c.removeUpdates(LocationManagerProxy.this.f827m);
                            return;
                        }
                        return;
                    }
                    return;
                } catch (Throwable th2) {
                    th2.printStackTrace();
                    return;
                }
            }
            i2 = 0;
            while (LocationManagerProxy.this.f825k != null && i2 < LocationManagerProxy.this.f825k.size()) {
                requestLocationEntity = (RequestLocationEntity) LocationManagerProxy.this.f825k.get(i2);
                if (!(requestLocationEntity == null || requestLocationEntity.f952a != -1 || LocationManagerProxy.this.f826l == null)) {
                    LocationManagerProxy.this.f826l.add(requestLocationEntity);
                }
                i2++;
            }
            if (LocationManagerProxy.this.f826l != null && LocationManagerProxy.this.f826l.size() > 0 && LocationManagerProxy.this.f825k != null) {
                for (int i3 = 0; i3 < LocationManagerProxy.this.f826l.size(); i3++) {
                    LocationManagerProxy.this.f825k.remove(LocationManagerProxy.this.f826l.get(i3));
                }
                LocationManagerProxy.this.f826l.clear();
                if (LocationManagerProxy.this.f825k.size() == 0 && LocationManagerProxy.this.f818c != null && LocationManagerProxy.this.f827m != null) {
                    LocationManagerProxy.this.f818c.removeUpdates(LocationManagerProxy.this.f827m);
                }
            }
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onProviderDisabled(String str) {
        }

        public void onLocationChanged(AMapLocation aMapLocation) {
        }
    }

    /* renamed from: com.amap.api.location.LocationManagerProxy$b */
    class C0719b implements AMapLocationListener {
        C0719b() {
        }

        public void onLocationChanged(Location location) {
            try {
                if (LocationManagerProxy.this.f823i != null && LocationManagerProxy.this.f823i.size() > 0) {
                    Iterator it = LocationManagerProxy.this.f823i.iterator();
                    while (it.hasNext()) {
                        PendingIntent pendingIntent = (PendingIntent) it.next();
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(LocationManagerProxy.KEY_LOCATION_CHANGED, location);
                        intent.putExtras(bundle);
                        pendingIntent.send(LocationManagerProxy.this.f820f, 0, intent);
                    }
                }
            } catch (CanceledException e) {
                e.printStackTrace();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onProviderDisabled(String str) {
        }

        public void onLocationChanged(AMapLocation aMapLocation) {
            try {
                if (LocationManagerProxy.this.f823i != null && LocationManagerProxy.this.f823i.size() > 0) {
                    Iterator it = LocationManagerProxy.this.f823i.iterator();
                    while (it.hasNext()) {
                        PendingIntent pendingIntent = (PendingIntent) it.next();
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(LocationManagerProxy.KEY_LOCATION_CHANGED, aMapLocation);
                        intent.putExtras(bundle);
                        pendingIntent.send(LocationManagerProxy.this.f820f, 0, intent);
                    }
                }
            } catch (CanceledException e) {
                e.printStackTrace();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private static void m1335a() {
        f816d = null;
    }

    private LocationManagerProxy(Context context) {
        this.f817a = context;
        m1336a(context, context);
    }

    private LocationManagerProxy(Activity activity) {
        this.f817a = activity;
        m1336a(activity, activity.getApplicationContext());
    }

    public static LocationManagerProxy getInstance(Activity activity) {
        try {
            synchronized (f815b) {
                if (f816d == null) {
                    f816d = new LocationManagerProxy(activity);
                }
            }
            return f816d;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static synchronized LocationManagerProxy getInstance(Context context) {
        LocationManagerProxy locationManagerProxy;
        synchronized (LocationManagerProxy.class) {
            try {
                if (f816d == null) {
                    f816d = new LocationManagerProxy(context);
                }
                locationManagerProxy = f816d;
            } catch (Throwable th) {
                th.printStackTrace();
                locationManagerProxy = null;
            }
        }
        return locationManagerProxy;
    }

    /* renamed from: a */
    private void m1336a(Context context, Context context2) {
        try {
            this.f820f = context2;
            this.f818c = (LocationManager) context2.getSystemService(KEY_LOCATION_CHANGED);
            this.f819e = AMapLocationManager.m1347a(context, context2.getApplicationContext(), this.f818c);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void addProximityAlert(double d, double d2, float f, long j, PendingIntent pendingIntent) {
        try {
            if (this.f819e.f844g) {
                this.f818c.addProximityAlert(d, d2, f, j, pendingIntent);
            }
            this.f819e.mo8352a(d, d2, f, j, pendingIntent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void addGeoFenceAlert(double d, double d2, float f, long j, PendingIntent pendingIntent) {
        try {
            if (this.f819e != null) {
                this.f819e.mo8359b(d, d2, f, j, pendingIntent);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void removeGeoFenceAlert(PendingIntent pendingIntent) {
        try {
            if (this.f819e != null) {
                this.f819e.mo8360b(pendingIntent);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void removeProximityAlert(PendingIntent pendingIntent) {
        try {
            if (!(this.f819e == null || !this.f819e.f844g || this.f818c == null)) {
                this.f818c.removeProximityAlert(pendingIntent);
            }
            if (this.f819e != null) {
                this.f819e.mo8355a(pendingIntent);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public AMapLocation getLastKnownLocation(String str) {
        try {
            if (this.f819e == null) {
                return null;
            }
            if (LocationProviderProxy.AMapNetwork.equals(str)) {
                return this.f819e.mo8351a();
            }
            if (this.f818c == null) {
                return null;
            }
            Location lastKnownLocation = this.f818c.getLastKnownLocation(str);
            if (lastKnownLocation != null) {
                return new AMapLocation(lastKnownLocation);
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setGpsEnable(boolean z) {
        try {
            if (this.f819e != null) {
                this.f819e.mo8357a(z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private synchronized void m1337a(String str, long j, float f, AMapLocationListener aMapLocationListener, boolean z) {
        try {
            Object obj;
            if (this.f819e == null) {
                this.f819e = AMapLocationManager.m1347a(this.f817a, this.f820f.getApplicationContext(), this.f818c);
            }
            if (str == null) {
                obj = LocationProviderProxy.AMapNetwork;
            } else {
                String obj2 = str;
            }
            if (LocationProviderProxy.AMapNetwork.equals(obj2)) {
                if (this.f819e != null) {
                    this.f819e.mo8354a(j, f, aMapLocationListener, LocationProviderProxy.AMapNetwork, z);
                }
            } else if (!"gps".equals(obj2)) {
                Looper mainLooper = this.f820f.getMainLooper();
                if (Looper.myLooper() == null) {
                    Looper.prepare();
                }
                this.f825k.add(new RequestLocationEntity(j, f, aMapLocationListener, obj2, false));
                this.f818c.requestLocationUpdates(obj2, j, f, this.f827m, mainLooper);
            } else if (this.f819e != null) {
                this.f819e.mo8354a(j, f, aMapLocationListener, "gps", z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return;
    }

    @Deprecated
    public synchronized void requestLocationUpdates(String str, long j, float f, AMapLocationListener aMapLocationListener) {
        m1337a(str, j, f, aMapLocationListener, false);
    }

    public synchronized void requestLocationData(String str, long j, float f, AMapLocationListener aMapLocationListener) {
        m1337a(str, j, f, aMapLocationListener, true);
    }

    public synchronized void removeUpdates(AMapLocationListener aMapLocationListener) {
        if (aMapLocationListener != null) {
            try {
                if (this.f819e != null) {
                    this.f819e.mo8356a(aMapLocationListener);
                }
                this.f818c.removeUpdates(aMapLocationListener);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (this.f825k != null && this.f825k.size() > 0) {
            int size = this.f825k.size();
            int i = 0;
            while (i < size) {
                int i2;
                RequestLocationEntity requestLocationEntity = (RequestLocationEntity) this.f825k.get(i);
                if (aMapLocationListener.equals(requestLocationEntity.f953b)) {
                    this.f825k.remove(requestLocationEntity);
                    size--;
                    i2 = i - 1;
                } else {
                    i2 = i;
                }
                size = size;
                i = i2 + 1;
            }
            if (this.f825k.size() == 0 && this.f827m != null) {
                this.f818c.removeUpdates(this.f827m);
            }
        }
        return;
    }

    public void requestLocationUpdates(String str, long j, float f, PendingIntent pendingIntent) {
        try {
            if (LocationProviderProxy.AMapNetwork.equals(str)) {
                if (this.f821g == null) {
                    this.f821g = new LocationListenerProxy(this);
                }
                if (this.f822h == null) {
                    this.f822h = new C0719b();
                }
                this.f821g.mo8425a(this.f822h, j, f, str);
                this.f823i.add(pendingIntent);
                return;
            }
            this.f823i.add(pendingIntent);
            this.f818c.requestLocationUpdates(str, j, f, pendingIntent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void removeUpdates(PendingIntent pendingIntent) {
        try {
            if (this.f821g != null) {
                this.f823i.remove(pendingIntent);
                if (this.f823i.size() == 0) {
                    this.f821g.mo8424a();
                }
            }
            this.f821g = null;
            this.f818c.removeUpdates(pendingIntent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public List<String> getAllProviders() {
        try {
            List<String> allProviders = this.f818c.getAllProviders();
            if (allProviders == null) {
                allProviders = new ArrayList();
                allProviders.add(LocationProviderProxy.AMapNetwork);
                allProviders.addAll(this.f818c.getAllProviders());
                return allProviders;
            } else if (allProviders.contains(LocationProviderProxy.AMapNetwork)) {
                return allProviders;
            } else {
                allProviders.add(LocationProviderProxy.AMapNetwork);
                return allProviders;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public List<String> getProviders(boolean z) {
        try {
            List providers = this.f818c.getProviders(z);
            if (!isProviderEnabled(LocationProviderProxy.AMapNetwork)) {
                return providers;
            }
            if (providers == null || providers.size() == 0) {
                providers = new ArrayList();
            }
            providers.add(LocationProviderProxy.AMapNetwork);
            return providers;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public List<String> getProviders(Criteria criteria, boolean z) {
        try {
            List<String> providers = this.f818c.getProviders(criteria, z);
            if (providers == null || providers.size() == 0) {
                providers = new ArrayList();
            }
            if (!LocationProviderProxy.AMapNetwork.equals(getBestProvider(criteria, z))) {
                return providers;
            }
            providers.add(LocationProviderProxy.AMapNetwork);
            return providers;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public String getBestProvider(Criteria criteria, boolean z) {
        try {
            String str = LocationProviderProxy.AMapNetwork;
            if (criteria == null) {
                return str;
            }
            if (!getProvider(LocationProviderProxy.AMapNetwork).meetsCriteria(criteria)) {
                str = this.f818c.getBestProvider(criteria, z);
            }
            if (!z || CoreUtil.m1460a(this.f820f)) {
                return str;
            }
            return this.f818c.getBestProvider(criteria, z);
        } catch (Throwable th) {
            th.printStackTrace();
            return "gps";
        }
    }

    public boolean isProviderEnabled(String str) {
        try {
            if (LocationProviderProxy.AMapNetwork.equals(str)) {
                return CoreUtil.m1460a(this.f820f);
            }
            return this.f818c.isProviderEnabled(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public LocationProviderProxy getProvider(String str) {
        if (str == null) {
            try {
                throw new IllegalArgumentException("name不能为空！");
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        } else if (this.f824j.containsKey(str)) {
            return (LocationProviderProxy) this.f824j.get(str);
        } else {
            LocationProviderProxy a = LocationProviderProxy.m1344a(this.f818c, str);
            this.f824j.put(str, a);
            return a;
        }
    }

    public GpsStatus getGpsStatus(GpsStatus gpsStatus) {
        try {
            if (this.f818c != null) {
                return this.f818c.getGpsStatus(gpsStatus);
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void removeGpsStatusListener(Listener listener) {
        try {
            if (this.f818c != null) {
                this.f818c.removeGpsStatusListener(listener);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean addGpsStatusListener(Listener listener) {
        try {
            if (this.f818c != null) {
                return this.f818c.addGpsStatusListener(listener);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    public void addTestProvider(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i, int i2) {
        try {
            if (this.f818c != null) {
                this.f818c.addTestProvider(str, z, z2, z3, z4, z5, z6, z7, i, i2);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setTestProviderEnabled(String str, boolean z) {
        try {
            if (this.f818c != null) {
                this.f818c.setTestProviderEnabled(str, z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setTestProviderLocation(String str, Location location) {
        try {
            if (this.f818c != null) {
                this.f818c.setTestProviderLocation(str, location);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setTestProviderStatus(String str, int i, Bundle bundle, long j) {
        try {
            if (this.f818c != null) {
                this.f818c.setTestProviderStatus(str, i, bundle, j);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void clearTestProviderEnabled(String str) {
        try {
            if (this.f818c != null) {
                this.f818c.clearTestProviderEnabled(str);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void clearTestProviderLocation(String str) {
        try {
            if (this.f818c != null) {
                this.f818c.clearTestProviderLocation(str);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void clearTestProviderStatus(String str) {
        try {
            if (this.f818c != null) {
                this.f818c.clearTestProviderStatus(str);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void requestWeatherUpdates(int i, AMapLocalWeatherListener aMapLocalWeatherListener) {
        try {
            this.f819e.mo8353a(i, aMapLocalWeatherListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Deprecated
    public void destory() {
        try {
            destroy();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void destroy() {
        try {
            synchronized (f815b) {
                AMapLocationManager.m1351c();
                if (this.f824j != null) {
                    this.f824j.clear();
                }
                if (this.f825k != null) {
                    this.f825k.clear();
                }
                if (this.f818c != null) {
                    if (this.f827m != null) {
                        this.f818c.removeUpdates(this.f827m);
                    }
                    if (this.f823i != null) {
                        for (int i = 0; i < this.f823i.size(); i++) {
                            PendingIntent pendingIntent = (PendingIntent) this.f823i.get(i);
                            if (pendingIntent != null) {
                                this.f818c.removeUpdates(pendingIntent);
                            }
                        }
                    }
                }
                if (this.f823i != null) {
                    this.f823i.clear();
                }
                this.f819e = null;
                m1335a();
                this.f827m = null;
                stopScocket();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static String getVersion() {
        try {
            return "1.4.1";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void startSocket() {
        try {
            this.f820f.startService(new Intent(this.f820f, SocketService.class));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void stopScocket() {
        try {
            this.f820f.stopService(new Intent(this.f820f, SocketService.class));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
