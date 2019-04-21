package com.mcdonalds.sdk.services.location.providers;

import android.location.Address;
import android.location.Location;
import java.util.List;

public interface LocationProvider {

    public interface LocationUpdateListener {
        void onLocationChanged(Location location);
    }

    void disableLocationUpdates();

    void enableLocationUpdates(LocationUpdateListener locationUpdateListener);

    void enableLocationUpdates(LocationUpdateListener locationUpdateListener, int i);

    Location getCurrentLocation();

    void requestSingleUpdate(LocationUpdateListener locationUpdateListener);

    List<Address> searchAddress(String str);

    List<Address> searchAddress(String str, double d, double d2, double d3, double d4);
}
