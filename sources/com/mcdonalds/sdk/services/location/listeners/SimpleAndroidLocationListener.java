package com.mcdonalds.sdk.services.location.listeners;

import android.location.LocationListener;
import android.os.Bundle;

public abstract class SimpleAndroidLocationListener implements LocationListener {
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onProviderDisabled(String provider) {
    }
}
