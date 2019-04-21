package com.mcdonalds.sdk.services.location.providers;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.mcdonalds.sdk.services.location.listeners.SimpleAmapLocationListener;
import com.mcdonalds.sdk.services.location.providers.LocationProvider.LocationUpdateListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class AutonaviLocationProvider implements LocationProvider {
    private static final long REQUEST_MODE_ACCURACY_MIN_DISTANCE = 10;
    private static final long REQUEST_MODE_ACCURACY_MIN_TIME = TimeUnit.SECONDS.toMillis(15);
    private static final long REQUEST_MODE_BATTERY_MIN_DISTANCE = 100;
    private static final long REQUEST_MODE_BATTERY_MIN_TIME = TimeUnit.MINUTES.toMillis(1);
    private SimpleAmapLocationListener mAmapLocationListener = new C41301();
    private Context mContext;
    private LocationUpdateListener mListener;
    private Location mLocation;
    private LocationManagerProxy mLocationManager;

    /* renamed from: com.mcdonalds.sdk.services.location.providers.AutonaviLocationProvider$1 */
    class C41301 extends SimpleAmapLocationListener {
        C41301() {
        }

        public void onLocationChanged(AMapLocation location) {
            AutonaviLocationProvider.this.setLocation(location);
        }
    }

    /* renamed from: com.mcdonalds.sdk.services.location.providers.AutonaviLocationProvider$2 */
    class C41312 extends SimpleAmapLocationListener {
        C41312() {
        }

        public void onLocationChanged(AMapLocation location) {
            AutonaviLocationProvider.this.mLocationManager.removeUpdates((AMapLocationListener) this);
            AutonaviLocationProvider.this.setLocation(location);
        }
    }

    private void setLocation(AMapLocation location) {
        this.mLocation = new Location(location.getProvider());
        this.mLocation.setLatitude(location.getLatitude());
        this.mLocation.setLongitude(location.getLongitude());
        if (this.mListener != null) {
            this.mListener.onLocationChanged(this.mLocation);
        }
    }

    public AutonaviLocationProvider(Context context) {
        this.mContext = context;
        this.mLocationManager = LocationManagerProxy.getInstance(context);
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
        if (requestMode == 0) {
            minTime = REQUEST_MODE_BATTERY_MIN_TIME;
            minDistance = REQUEST_MODE_BATTERY_MIN_DISTANCE;
        } else if (requestMode == 1) {
            minTime = REQUEST_MODE_ACCURACY_MIN_TIME;
            minDistance = REQUEST_MODE_ACCURACY_MIN_DISTANCE;
        } else {
            minTime = REQUEST_MODE_BATTERY_MIN_TIME;
            minDistance = REQUEST_MODE_BATTERY_MIN_DISTANCE;
        }
        this.mLocationManager.requestLocationData(LocationProviderProxy.AMapNetwork, minTime, (float) minDistance, this.mAmapLocationListener);
    }

    public void disableLocationUpdates() {
        this.mLocationManager.removeUpdates(this.mAmapLocationListener);
    }

    public void requestSingleUpdate(LocationUpdateListener listener) {
        this.mLocationManager.requestLocationData(LocationProviderProxy.AMapNetwork, 0, 0.0f, new C41312());
    }

    public List<Address> searchAddress(String query, double southeastLatitude, double southeastLongitude, double northwestLatitude, double northwestLongitude) {
        return searchAddress(query);
    }

    public List<Address> searchAddress(String query) {
        GeocodeQuery geoQuery = new GeocodeQuery(query, "");
        GeocodeSearch search = new GeocodeSearch(this.mContext);
        List<Address> addresses = new ArrayList();
        try {
            for (GeocodeAddress geoAddress : search.getFromLocationName(geoQuery)) {
                Address address = new Address(Locale.getDefault());
                address.setFeatureName(geoAddress.getFormatAddress());
                address.setAdminArea(geoAddress.getDistrict());
                address.setCountryName(geoAddress.getCity());
                address.setLatitude(geoAddress.getLatLonPoint().getLatitude());
                address.setLongitude(geoAddress.getLatLonPoint().getLongitude());
                addresses.add(address);
            }
        } catch (AMapException e) {
        }
        return addresses;
    }
}
