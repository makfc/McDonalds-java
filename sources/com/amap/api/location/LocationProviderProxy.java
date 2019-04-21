package com.amap.api.location;

import android.location.Criteria;
import android.location.LocationManager;
import android.location.LocationProvider;
import com.newrelic.agent.android.util.SafeJsonPrimitive;

public class LocationProviderProxy {
    public static final String AMapNetwork = "lbs";
    public static final int AVAILABLE = 2;
    public static final int OUT_OF_SERVICE = 0;
    public static final int TEMPORARILY_UNAVAILABLE = 1;
    /* renamed from: a */
    private LocationManager f828a;
    /* renamed from: b */
    private String f829b;

    protected LocationProviderProxy(LocationManager locationManager, String str) {
        this.f828a = locationManager;
        this.f829b = str;
    }

    /* renamed from: a */
    static LocationProviderProxy m1344a(LocationManager locationManager, String str) {
        return new LocationProviderProxy(locationManager, str);
    }

    /* renamed from: a */
    private LocationProvider m1343a() {
        try {
            if (this.f828a != null) {
                return this.f828a.getProvider(this.f829b);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    public int getAccuracy() {
        try {
            if (AMapNetwork != null && AMapNetwork.equals(this.f829b)) {
                return 2;
            }
            if (m1343a() != null) {
                return m1343a().getAccuracy();
            }
            return -1;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String getName() {
        try {
            if (AMapNetwork != null && AMapNetwork.equals(this.f829b)) {
                return AMapNetwork;
            }
            if (m1343a() != null) {
                return m1343a().getName();
            }
            return SafeJsonPrimitive.NULL_STRING;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getPowerRequirement() {
        try {
            if (AMapNetwork != null && AMapNetwork.equals(this.f829b)) {
                return 2;
            }
            if (m1343a() != null) {
                return m1343a().getPowerRequirement();
            }
            return -1;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean hasMonetaryCost() {
        try {
            if ((AMapNetwork == null || !AMapNetwork.equals(this.f829b)) && m1343a() != null) {
                return m1343a().hasMonetaryCost();
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public boolean meetsCriteria(Criteria criteria) {
        try {
            if (AMapNetwork == null || !AMapNetwork.equals(this.f829b)) {
                if (m1343a() != null) {
                    return m1343a().meetsCriteria(criteria);
                }
                return false;
            } else if (criteria == null) {
                return true;
            } else {
                if (criteria.isAltitudeRequired() || criteria.isBearingRequired() || criteria.isSpeedRequired() || criteria.getAccuracy() == 1) {
                    return false;
                }
                return true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean requiresCell() {
        try {
            if ((AMapNetwork == null || !AMapNetwork.equals(this.f829b)) && m1343a() != null) {
                return m1343a().requiresCell();
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    public boolean requiresNetwork() {
        try {
            if ((AMapNetwork == null || !AMapNetwork.equals(this.f829b)) && m1343a() != null) {
                return m1343a().requiresNetwork();
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    public boolean requiresSatellite() {
        try {
            if (AMapNetwork != null && AMapNetwork.equals(this.f829b)) {
                return false;
            }
            if (m1343a() != null) {
                return m1343a().requiresNetwork();
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean supportsAltitude() {
        try {
            if ((AMapNetwork == null || !AMapNetwork.equals(this.f829b)) && m1343a() != null) {
                return m1343a().supportsAltitude();
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public boolean supportsBearing() {
        try {
            if ((AMapNetwork == null || !AMapNetwork.equals(this.f829b)) && m1343a() != null) {
                return m1343a().supportsBearing();
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public boolean supportsSpeed() {
        try {
            if ((AMapNetwork == null || !AMapNetwork.equals(this.f829b)) && m1343a() != null) {
                return m1343a().supportsSpeed();
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }
}
