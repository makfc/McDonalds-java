package com.mcdonalds.sdk.services.location.providers;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import com.amap.api.location.LocationManagerProxy;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.services.location.listeners.SimpleAndroidLocationListener;
import com.mcdonalds.sdk.services.location.providers.LocationProvider.LocationUpdateListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AndroidLocationProvider implements LocationProvider {
    private static final long REQUEST_MODE_ACCURACY_MIN_DISTANCE = 10;
    private static final long REQUEST_MODE_ACCURACY_MIN_TIME = TimeUnit.SECONDS.toMillis(15);
    private static final long REQUEST_MODE_BATTERY_MIN_DISTANCE = 100;
    private static final long REQUEST_MODE_BATTERY_MIN_TIME = TimeUnit.MINUTES.toMillis(1);
    private SimpleAndroidLocationListener mAndroidLocationListener = new C41291();
    private Geocoder mGeocoder;
    private LocationUpdateListener mListener;
    private Location mLocation;
    private LocationManager mLocationManager;

    /* renamed from: com.mcdonalds.sdk.services.location.providers.AndroidLocationProvider$1 */
    class C41291 extends SimpleAndroidLocationListener {
        C41291() {
        }

        public void onLocationChanged(Location location) {
            AndroidLocationProvider.this.mLocation = location;
            if (AndroidLocationProvider.this.mListener != null) {
                AndroidLocationProvider.this.mListener.onLocationChanged(location);
            }
        }
    }

    public AndroidLocationProvider(Context context) {
        this.mLocationManager = (LocationManager) context.getSystemService(LocationManagerProxy.KEY_LOCATION_CHANGED);
    }

    public Location getCurrentLocation() {
        long minTime = new Date().getTime() - 300000;
        long bestTime = 0;
        float bestAccuracy = 1500.0f;
        Location bestResult = null;
        for (String provider : this.mLocationManager.getAllProviders()) {
            Location location = this.mLocationManager.getLastKnownLocation(provider);
            if (location != null) {
                float accuracy = location.getAccuracy();
                long time = location.getTime();
                if (time > minTime && accuracy < bestAccuracy) {
                    bestResult = location;
                    bestAccuracy = accuracy;
                    bestTime = time;
                } else if (time < minTime && bestAccuracy == Float.MAX_VALUE && time > bestTime) {
                    bestResult = location;
                    bestTime = time;
                }
            }
        }
        if (bestResult != null) {
            this.mLocation = bestResult;
        }
        return this.mLocation;
    }

    public void enableLocationUpdates(LocationUpdateListener listener) {
        enableLocationUpdates(listener, 0);
    }

    public void enableLocationUpdates(LocationUpdateListener listener, int requestMode) {
        long minTime;
        long minDistance;
        this.mListener = listener;
        Criteria criteria = new Criteria();
        if (requestMode == 0) {
            criteria.setAccuracy(2);
            criteria.setPowerRequirement(2);
            minTime = REQUEST_MODE_BATTERY_MIN_TIME;
            minDistance = REQUEST_MODE_BATTERY_MIN_DISTANCE;
        } else if (requestMode == 1) {
            criteria.setAccuracy(3);
            criteria.setPowerRequirement(3);
            minTime = REQUEST_MODE_ACCURACY_MIN_TIME;
            minDistance = REQUEST_MODE_ACCURACY_MIN_DISTANCE;
        } else {
            criteria.setAccuracy(2);
            criteria.setPowerRequirement(2);
            minTime = REQUEST_MODE_BATTERY_MIN_TIME;
            minDistance = REQUEST_MODE_BATTERY_MIN_DISTANCE;
        }
        String provider = this.mLocationManager.getBestProvider(criteria, true);
        if (provider != null) {
            this.mLocationManager.requestLocationUpdates(provider, minTime, (float) minDistance, this.mAndroidLocationListener);
        }
    }

    public void disableLocationUpdates() {
        this.mLocationManager.removeUpdates(this.mAndroidLocationListener);
    }

    public void requestSingleUpdate(LocationUpdateListener listener) {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setPowerRequirement(3);
        this.mLocationManager.requestSingleUpdate(criteria, this.mAndroidLocationListener, Looper.getMainLooper());
    }

    public List<Address> searchAddress(String query) {
        return searchAddress(query, 0.0d, 0.0d, 0.0d, 0.0d);
    }

    public List<Address> searchAddress(String query, double southeastLatitude, double southeastLongitude, double northwestLatitude, double northwestLongitude) {
        if (!Geocoder.isPresent()) {
            return new ArrayList();
        }
        if (this.mGeocoder == null) {
            this.mGeocoder = new Geocoder(McDonalds.getContext());
        }
        try {
            return this.mGeocoder.getFromLocationName(query, 5, southeastLatitude, southeastLongitude, northwestLatitude, northwestLongitude);
        } catch (IOException e) {
            return new ArrayList();
        }
    }
}
