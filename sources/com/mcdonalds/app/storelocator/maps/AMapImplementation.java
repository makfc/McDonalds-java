package com.mcdonalds.app.storelocator.maps;

import android.location.Location;
import android.os.Bundle;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.CancelableCallback;
import com.amap.api.maps.AMap.OnCameraChangeListener;
import com.amap.api.maps.AMap.OnMapClickListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.LocationSource.OnLocationChangedListener;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.Marker;
import com.ensighten.Ensighten;
import com.mcdonalds.app.storelocator.maps.McdMap.InfoWindowAdapter;
import com.mcdonalds.app.storelocator.maps.McdMap.OnMapLoadedListener;
import com.mcdonalds.app.storelocator.maps.model.LatLng;
import com.mcdonalds.app.storelocator.maps.model.MarkerOptions;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.services.log.SafeLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AMapImplementation implements AMapLocationListener, LocationSource, McdMap {
    private static final String LOG_TAG = AMapImplementation.class.getSimpleName();
    private final OnCameraChangeListener mAMapCameraChangeListener = new C37853();
    private List<Marker> mAMapMarkers;
    private final OnMapClickListener mAMapOnMapClickListener = new C37864();
    private final OnMarkerClickListener mAMapOnMarkerClickListener = new C37875();
    private final CancelableCallback mCancelableCallback = new C37842();
    private float mCurrentZoom;
    private OnLocationChangedListener mListener;
    private LocationManagerProxy mLocationManager;
    private AMap mMap;
    private HashMap<String, com.mcdonalds.app.storelocator.maps.model.Marker> mMarkers;
    private McdMap.OnCameraChangeListener mOnCameraChangeListener;
    private McdMap.OnMapClickListener mOnMapClickListener;
    private McdMap.OnMarkerClickListener mOnMarkerClickListener;
    private LatLng mUserLocation;

    /* renamed from: com.mcdonalds.app.storelocator.maps.AMapImplementation$2 */
    class C37842 implements CancelableCallback {
        C37842() {
        }

        public void onFinish() {
            Ensighten.evaluateEvent(this, "onFinish", null);
            AMapImplementation.access$002(AMapImplementation.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.AMapImplementation", "access$100", new Object[]{AMapImplementation.this}).getCameraPosition().zoom);
        }

        public void onCancel() {
            Ensighten.evaluateEvent(this, "onCancel", null);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.maps.AMapImplementation$3 */
    class C37853 implements OnCameraChangeListener {
        C37853() {
        }

        public void onCameraChange(CameraPosition cameraPosition) {
            Ensighten.evaluateEvent(this, "onCameraChange", new Object[]{cameraPosition});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.AMapImplementation", "access$200", new Object[]{AMapImplementation.this}).onCameraChange(com.mcdonalds.app.storelocator.maps.model.CameraPosition.fromAmap(cameraPosition));
        }

        public void onCameraChangeFinish(CameraPosition cameraPosition) {
            Ensighten.evaluateEvent(this, "onCameraChangeFinish", new Object[]{cameraPosition});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.AMapImplementation", "access$200", new Object[]{AMapImplementation.this}).onCameraChange(com.mcdonalds.app.storelocator.maps.model.CameraPosition.fromAmap(cameraPosition));
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.maps.AMapImplementation$4 */
    class C37864 implements OnMapClickListener {
        C37864() {
        }

        public void onMapClick(com.amap.api.maps.model.LatLng latLng) {
            Ensighten.evaluateEvent(this, "onMapClick", new Object[]{latLng});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.AMapImplementation", "access$300", new Object[]{AMapImplementation.this}).onMapClick(LatLng.fromAMaps(latLng));
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.maps.AMapImplementation$5 */
    class C37875 implements OnMarkerClickListener {
        C37875() {
        }

        public boolean onMarkerClick(Marker marker) {
            Ensighten.evaluateEvent(this, "onMarkerClick", new Object[]{marker});
            return Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.AMapImplementation", "access$500", new Object[]{AMapImplementation.this}).onMarkerClick(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.AMapImplementation", "access$400", new Object[]{AMapImplementation.this, marker.getId()}));
        }
    }

    static /* synthetic */ float access$002(AMapImplementation x0, float x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.AMapImplementation", "access$002", new Object[]{x0, new Float(x1)});
        x0.mCurrentZoom = x1;
        return x1;
    }

    public AMapImplementation(AMap map) {
        this.mMap = map;
        this.mMarkers = new HashMap();
        this.mAMapMarkers = new ArrayList();
    }

    public void moveCamera(LatLng location, float zoom) {
        Ensighten.evaluateEvent(this, "moveCamera", new Object[]{location, new Float(zoom)});
        this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location.toAMap(), zoom));
    }

    public void moveCamera(com.mcdonalds.app.storelocator.maps.model.CameraPosition position) {
        Ensighten.evaluateEvent(this, "moveCamera", new Object[]{position});
        this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position.target.toAMap(), position.zoom));
    }

    public void animateCamera(LatLng location, float zoom) {
        Ensighten.evaluateEvent(this, "animateCamera", new Object[]{location, new Float(zoom)});
        this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location.toAMap(), zoom));
    }

    public void animateCamera(List<LatLng> bounds, int margin) {
        Ensighten.evaluateEvent(this, "animateCamera", new Object[]{bounds, new Integer(margin)});
        Builder builder = new Builder();
        for (LatLng latLng : bounds) {
            builder.include(latLng.toAMap());
        }
        animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), margin));
    }

    public void setOnCameraChangeListener(McdMap.OnCameraChangeListener listener) {
        Ensighten.evaluateEvent(this, "setOnCameraChangeListener", new Object[]{listener});
        this.mOnCameraChangeListener = listener;
        this.mMap.setOnCameraChangeListener(this.mAMapCameraChangeListener);
    }

    public void clear() {
        Ensighten.evaluateEvent(this, "clear", null);
        for (Marker marker : this.mAMapMarkers) {
            marker.remove();
            marker.destroy();
        }
        this.mAMapMarkers.clear();
        this.mMarkers.clear();
    }

    public void setCurrentZoom(float zoom) {
        Ensighten.evaluateEvent(this, "setCurrentZoom", new Object[]{new Float(zoom)});
        this.mCurrentZoom = zoom;
    }

    public float getCurrentZoom() {
        Ensighten.evaluateEvent(this, "getCurrentZoom", null);
        return this.mCurrentZoom;
    }

    public LatLng getUserLocation() {
        Ensighten.evaluateEvent(this, "getUserLocation", null);
        return this.mUserLocation;
    }

    public com.mcdonalds.app.storelocator.maps.model.CameraPosition getCameraPosition() {
        Ensighten.evaluateEvent(this, "getCameraPosition", null);
        return com.mcdonalds.app.storelocator.maps.model.CameraPosition.fromAmap(this.mMap.getCameraPosition());
    }

    public com.mcdonalds.app.storelocator.maps.model.Marker addMarker(MarkerOptions options) {
        Ensighten.evaluateEvent(this, "addMarker", new Object[]{options});
        Marker aMarker = this.mMap.addMarker(options.toAMap());
        com.mcdonalds.app.storelocator.maps.model.Marker marker = com.mcdonalds.app.storelocator.maps.model.Marker.fromAMap(aMarker);
        this.mMarkers.put(marker.getId(), marker);
        this.mAMapMarkers.add(aMarker);
        return marker;
    }

    public void onPause() {
        Ensighten.evaluateEvent(this, "onPause", null);
        deactivate();
    }

    public void setOnMapLoadedListener(final OnMapLoadedListener onMapLoadedListener) {
        Ensighten.evaluateEvent(this, "setOnMapLoadedListener", new Object[]{onMapLoadedListener});
        this.mMap.setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
            public void onMapLoaded() {
                Ensighten.evaluateEvent(this, "onMapLoaded", null);
                onMapLoadedListener.onMapLoaded();
            }
        });
    }

    public void configure() {
        Ensighten.evaluateEvent(this, "configure", null);
        this.mMap.getUiSettings().setZoomControlsEnabled(false);
        this.mMap.getUiSettings().setTiltGesturesEnabled(false);
        this.mMap.getUiSettings().setCompassEnabled(false);
        this.mMap.getUiSettings().setRotateGesturesEnabled(false);
        this.mMap.getUiSettings().setMyLocationButtonEnabled(false);
        this.mMap.setMyLocationRotateAngle(180.0f);
        this.mMap.setLocationSource(this);
        this.mMap.setMyLocationEnabled(true);
        this.mMap.setMyLocationType(1);
    }

    public void setMyLocationEnabled(boolean enabled) {
        Ensighten.evaluateEvent(this, "setMyLocationEnabled", new Object[]{new Boolean(enabled)});
        if (enabled != this.mMap.isMyLocationEnabled()) {
            this.mMap.setMyLocationEnabled(enabled);
        }
    }

    public void setInfoWindowAdapter(InfoWindowAdapter adapter) {
        Ensighten.evaluateEvent(this, "setInfoWindowAdapter", new Object[]{adapter});
        this.mMap.setInfoWindowAdapter(adapter.toAMap());
    }

    public void setOnMapClickListener(McdMap.OnMapClickListener listener) {
        Ensighten.evaluateEvent(this, "setOnMapClickListener", new Object[]{listener});
        this.mOnMapClickListener = listener;
        this.mMap.setOnMapClickListener(this.mAMapOnMapClickListener);
    }

    public void setOnMarkerClickListener(McdMap.OnMarkerClickListener listener) {
        Ensighten.evaluateEvent(this, "setOnMarkerClickListener", new Object[]{listener});
        this.mOnMarkerClickListener = listener;
        this.mMap.setOnMarkerClickListener(this.mAMapOnMarkerClickListener);
    }

    private void animateCamera(CameraUpdate cameraUpdate) {
        Ensighten.evaluateEvent(this, "animateCamera", new Object[]{cameraUpdate});
        try {
            this.mMap.animateCamera(cameraUpdate, this.mCancelableCallback);
        } catch (IllegalStateException e) {
            SafeLog.m7746e(LOG_TAG, "error", e);
        }
    }

    private com.mcdonalds.app.storelocator.maps.model.Marker findMarkerById(String id) {
        Ensighten.evaluateEvent(this, "findMarkerById", new Object[]{id});
        return (com.mcdonalds.app.storelocator.maps.model.Marker) this.mMarkers.get(id);
    }

    public void activate(OnLocationChangedListener listener) {
        Ensighten.evaluateEvent(this, "activate", new Object[]{listener});
        this.mListener = listener;
        if (this.mLocationManager == null) {
            this.mLocationManager = LocationManagerProxy.getInstance(McDonalds.getContext());
            this.mLocationManager.requestLocationData(LocationProviderProxy.AMapNetwork, 2000, 10.0f, this);
        }
    }

    public void deactivate() {
        Ensighten.evaluateEvent(this, "deactivate", null);
        this.mListener = null;
        if (this.mLocationManager != null) {
            this.mLocationManager.removeUpdates((AMapLocationListener) this);
            this.mLocationManager.destroy();
            this.mLocationManager = null;
        }
    }

    public void onLocationChanged(AMapLocation location) {
        Ensighten.evaluateEvent(this, "onLocationChanged", new Object[]{location});
        if (this.mListener != null && location != null) {
            this.mListener.onLocationChanged(location);
            this.mMap.setMyLocationRotateAngle(this.mMap.getCameraPosition().bearing);
        }
    }

    public void onLocationChanged(Location location) {
        Ensighten.evaluateEvent(this, "onLocationChanged", new Object[]{location});
        this.mUserLocation = new LatLng(location.getLatitude(), location.getLongitude());
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        Ensighten.evaluateEvent(this, "onStatusChanged", new Object[]{provider, new Integer(status), extras});
    }

    public void onProviderEnabled(String provider) {
        Ensighten.evaluateEvent(this, "onProviderEnabled", new Object[]{provider});
    }

    public void onProviderDisabled(String provider) {
        Ensighten.evaluateEvent(this, "onProviderDisabled", new Object[]{provider});
    }
}
