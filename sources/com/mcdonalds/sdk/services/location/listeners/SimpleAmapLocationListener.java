package com.mcdonalds.sdk.services.location.listeners;

import android.location.Location;
import android.os.Bundle;
import com.amap.api.location.AMapLocationListener;

public abstract class SimpleAmapLocationListener implements AMapLocationListener {
    public void onLocationChanged(Location location) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onProviderDisabled(String provider) {
    }
}
