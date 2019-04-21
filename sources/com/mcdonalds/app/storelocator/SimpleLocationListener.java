package com.mcdonalds.app.storelocator;

import android.location.Location;
import android.os.Bundle;
import com.ensighten.Ensighten;

class SimpleLocationListener implements android.location.LocationListener {
    private final LocationListener mLocationListener;
    private final int mTag;

    public interface LocationListener {
        void onLocationUpdated(Location location, int i);
    }

    SimpleLocationListener(LocationListener listener, int tag) {
        this.mLocationListener = listener;
        this.mTag = tag;
    }

    public void onLocationChanged(Location location) {
        Ensighten.evaluateEvent(this, "onLocationChanged", new Object[]{location});
        this.mLocationListener.onLocationUpdated(location, this.mTag);
    }

    public void onStatusChanged(String provider, int status, Bundle bundle) {
        Ensighten.evaluateEvent(this, "onStatusChanged", new Object[]{provider, new Integer(status), bundle});
        if (status == 0 || status == 1) {
            this.mLocationListener.onLocationUpdated(null, this.mTag);
        }
    }

    public void onProviderEnabled(String s) {
        Ensighten.evaluateEvent(this, "onProviderEnabled", new Object[]{s});
    }

    public void onProviderDisabled(String s) {
        Ensighten.evaluateEvent(this, "onProviderDisabled", new Object[]{s});
        this.mLocationListener.onLocationUpdated(null, this.mTag);
    }
}
