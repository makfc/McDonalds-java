package com.mcdonalds.sdk.services.location.providers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.services.location.providers.LocationProvider.LocationUpdateListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoogleLocationProvider implements ConnectionCallbacks, OnConnectionFailedListener, LocationProvider {
    private static final long REQUEST_MODE_ACCURACY_FASTEST_INTERVAL = (REQUEST_MODE_ACCURACY_UPDATE_INTERVAL / 2);
    private static final long REQUEST_MODE_ACCURACY_UPDATE_INTERVAL = TimeUnit.SECONDS.toMillis(15);
    private static final long REQUEST_MODE_BATTERY_FASTEST_INTERVAL = (REQUEST_MODE_BATTERY_UPDATE_INTERVAL / 3);
    private static final long REQUEST_MODE_BATTERY_UPDATE_INTERVAL = TimeUnit.MINUTES.toMillis(1);
    private GoogleApiClient mClient;
    private Geocoder mGeocoder;
    private LocationListener mGoogleLocationListener = new C41321();
    private LocationUpdateListener mListener;
    private Location mLocation;
    private LocationRequest mPendingRequest;

    /* renamed from: com.mcdonalds.sdk.services.location.providers.GoogleLocationProvider$1 */
    class C41321 implements LocationListener {
        C41321() {
        }

        public void onLocationChanged(Location location) {
            GoogleLocationProvider.this.mLocation = location;
            if (GoogleLocationProvider.this.mListener != null) {
                GoogleLocationProvider.this.mListener.onLocationChanged(location);
            }
        }
    }

    public GoogleLocationProvider(Context context) {
        this.mClient = new Builder(context, this, this).addApi(LocationServices.API).build();
        this.mClient.connect();
    }

    public Location getCurrentLocation() {
        if (this.mClient.isConnected()) {
            this.mLocation = LocationServices.FusedLocationApi.getLastLocation(this.mClient);
        }
        return this.mLocation;
    }

    public void enableLocationUpdates(LocationUpdateListener listener) {
        enableLocationUpdates(listener, 0);
    }

    public void enableLocationUpdates(LocationUpdateListener listener, int requestMode) {
        LocationRequest request;
        if (requestMode == 0) {
            request = LocationRequest.create().setPriority(102).setInterval(REQUEST_MODE_BATTERY_UPDATE_INTERVAL).setFastestInterval(REQUEST_MODE_BATTERY_FASTEST_INTERVAL);
        } else if (requestMode == 1) {
            request = LocationRequest.create().setPriority(100).setInterval(REQUEST_MODE_ACCURACY_UPDATE_INTERVAL).setFastestInterval(REQUEST_MODE_ACCURACY_FASTEST_INTERVAL);
        } else {
            request = LocationRequest.create().setPriority(102).setInterval(REQUEST_MODE_BATTERY_UPDATE_INTERVAL).setFastestInterval(REQUEST_MODE_BATTERY_FASTEST_INTERVAL);
        }
        requestUpdates(listener, request);
    }

    public void disableLocationUpdates() {
        this.mListener = null;
        try {
            LocationServices.FusedLocationApi.removeLocationUpdates(this.mClient, this.mGoogleLocationListener);
        } catch (IllegalStateException e) {
        }
    }

    public void requestSingleUpdate(LocationUpdateListener listener) {
        requestUpdates(listener, LocationRequest.create().setNumUpdates(1).setPriority(100).setInterval(0));
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

    private void requestUpdates(LocationUpdateListener listener, LocationRequest request) {
        this.mListener = listener;
        if (this.mClient.isConnected()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(this.mClient, request, this.mGoogleLocationListener);
        } else {
            this.mPendingRequest = request;
        }
    }

    public void onConnected(@Nullable Bundle bundle) {
        if (this.mPendingRequest != null) {
            requestUpdates(this.mListener, this.mPendingRequest);
            this.mPendingRequest = null;
        }
        if (this.mListener != null && this.mLocation != null) {
            this.mListener.onLocationChanged(this.mLocation);
        }
    }

    public void onConnectionSuspended(int i) {
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
