package com.mcdonalds.sdk.services.location;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import com.google.android.gms.common.GoogleApiAvailability;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.location.providers.AndroidLocationProvider;
import com.mcdonalds.sdk.services.location.providers.AutonaviLocationProvider;
import com.mcdonalds.sdk.services.location.providers.GoogleLocationProvider;
import com.mcdonalds.sdk.services.location.providers.LocationProvider;
import com.mcdonalds.sdk.services.location.providers.LocationProvider.LocationUpdateListener;
import java.util.List;

public class LocationServicesManager {
    private static final String AUTONAVI_KEY = "connectors.AutoNavi";
    public static final int REQUEST_MODE_ACCURACY = 1;
    public static final int REQUEST_MODE_BATTERY = 0;
    private static LocationServicesManager sInstance;
    private Context mContext;
    private LocationProvider mLocationProvider;

    private LocationServicesManager(Context context) {
        this.mContext = context;
        if (Configuration.getSharedInstance().hasKey(AUTONAVI_KEY)) {
            this.mLocationProvider = new AutonaviLocationProvider(context);
        } else if (areGooglePlayServicesAvailable()) {
            this.mLocationProvider = new GoogleLocationProvider(context);
        } else {
            this.mLocationProvider = new AndroidLocationProvider(context);
        }
    }

    public Location getCurrentUserLocation() throws IllegalStateException {
        Location mockLocation = ModuleManager.getMockLocation();
        return mockLocation != null ? mockLocation : this.mLocationProvider.getCurrentLocation();
    }

    public boolean areGooglePlayServicesAvailable() {
        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.mContext) == 0;
    }

    public void requestSingleUpdate(LocationUpdateListener listener) {
        this.mLocationProvider.requestSingleUpdate(listener);
    }

    public void requestUpdates(LocationUpdateListener listener) {
        this.mLocationProvider.enableLocationUpdates(listener);
    }

    public void requestUpdates(LocationUpdateListener listener, int requestMode) {
        this.mLocationProvider.enableLocationUpdates(listener, requestMode);
    }

    public void disableUpdates() {
        this.mLocationProvider.disableLocationUpdates();
    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        if (VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            return false;
        }
        if (VERSION.SDK_INT >= 19) {
            try {
                locationMode = Secure.getInt(context.getContentResolver(), "location_mode");
            } catch (SettingNotFoundException e) {
                e.printStackTrace();
            }
            if (locationMode == 0) {
                return false;
            }
            return true;
        } else if (TextUtils.isEmpty(Secure.getString(context.getContentResolver(), "location_providers_allowed"))) {
            return false;
        } else {
            return true;
        }
    }

    public static LocationServicesManager getInstance() {
        if (sInstance == null) {
            sInstance = new LocationServicesManager(McDonalds.getContext());
        }
        return sInstance;
    }

    public static void destroy() {
        sInstance = null;
    }

    public List<Address> searchAddress(String query) {
        return this.mLocationProvider.searchAddress(query);
    }

    public List<Address> searchAddress(String query, double southeastLatitude, double southeastLongitude, double northwestLatitude, double northwestLongitude) {
        return this.mLocationProvider.searchAddress(query, southeastLatitude, southeastLongitude, northwestLatitude, northwestLongitude);
    }
}
