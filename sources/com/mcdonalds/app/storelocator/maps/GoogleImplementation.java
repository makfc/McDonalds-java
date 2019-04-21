package com.mcdonalds.app.storelocator.maps;

import android.location.Location;
import android.os.Handler;
import com.ensighten.Ensighten;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.gms.maps.model.Marker;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.storelocator.maps.McdMap.InfoWindowAdapter;
import com.mcdonalds.app.storelocator.maps.McdMap.OnMapLoadedListener;
import com.mcdonalds.app.storelocator.maps.model.MarkerOptions;
import com.mcdonalds.sdk.services.location.LocationServicesManager;
import com.mcdonalds.sdk.services.location.providers.LocationProvider.LocationUpdateListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GoogleImplementation implements McdMap {
    private static final String LOG_TAG = GoogleImplementation.class.getSimpleName();
    private final CancelableCallback mCancelableCallback = new C37892();
    private float mCurrentZoom;
    private List<Marker> mGoogleMarkers;
    private final OnCameraChangeListener mGoogleOnCameraChangeListener = new C37903();
    private final OnMapClickListener mGoogleOnMapClickListener = new C37914();
    private final OnMarkerClickListener mGoogleOnMarkerClickListener = new C37925();
    private OnLocationChangedListener mListener;
    private final LocationSource mLocationSource = new C37947();
    private final LocationUpdateListener mLocationUpdateListener = new C37936();
    private GoogleMap mMap;
    private HashMap<String, com.mcdonalds.app.storelocator.maps.model.Marker> mMarkers;
    private McdMap.OnCameraChangeListener mOnCameraChangeListener;
    private McdMap.OnMapClickListener mOnMapClickListener;
    private McdMap.OnMarkerClickListener mOnMarkerClickListener;

    /* renamed from: com.mcdonalds.app.storelocator.maps.GoogleImplementation$2 */
    class C37892 implements CancelableCallback {
        C37892() {
        }

        public void onFinish() {
            Ensighten.evaluateEvent(this, "onFinish", null);
            GoogleImplementation.access$102(GoogleImplementation.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.GoogleImplementation", "access$200", new Object[]{GoogleImplementation.this}).getCameraPosition().zoom);
        }

        public void onCancel() {
            Ensighten.evaluateEvent(this, "onCancel", null);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.maps.GoogleImplementation$3 */
    class C37903 implements OnCameraChangeListener {
        C37903() {
        }

        public void onCameraChange(CameraPosition cameraPosition) {
            Ensighten.evaluateEvent(this, "onCameraChange", new Object[]{cameraPosition});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.GoogleImplementation", "access$300", new Object[]{GoogleImplementation.this}).onCameraChange(com.mcdonalds.app.storelocator.maps.model.CameraPosition.fromGMap(cameraPosition));
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.maps.GoogleImplementation$4 */
    class C37914 implements OnMapClickListener {
        C37914() {
        }

        public void onMapClick(LatLng latLng) {
            Ensighten.evaluateEvent(this, "onMapClick", new Object[]{latLng});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.GoogleImplementation", "access$400", new Object[]{GoogleImplementation.this}).onMapClick(com.mcdonalds.app.storelocator.maps.model.LatLng.fromGMap(latLng));
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.maps.GoogleImplementation$5 */
    class C37925 implements OnMarkerClickListener {
        C37925() {
        }

        public boolean onMarkerClick(Marker marker) {
            Ensighten.evaluateEvent(this, "onMarkerClick", new Object[]{marker});
            return Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.GoogleImplementation", "access$600", new Object[]{GoogleImplementation.this}).onMarkerClick(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.GoogleImplementation", "access$500", new Object[]{GoogleImplementation.this, marker.getId()}));
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.maps.GoogleImplementation$6 */
    class C37936 implements LocationUpdateListener {
        C37936() {
        }

        public void onLocationChanged(Location location) {
            Ensighten.evaluateEvent(this, "onLocationChanged", new Object[]{location});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.GoogleImplementation", "access$700", new Object[]{GoogleImplementation.this}) != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.GoogleImplementation", "access$700", new Object[]{GoogleImplementation.this}).onLocationChanged(location);
            }
            DataLayerManager.getInstance().setLocation(location);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.maps.GoogleImplementation$7 */
    class C37947 implements LocationSource {
        C37947() {
        }

        public void activate(OnLocationChangedListener onLocationChangedListener) {
            Ensighten.evaluateEvent(this, "activate", new Object[]{onLocationChangedListener});
            GoogleImplementation.access$702(GoogleImplementation.this, onLocationChangedListener);
            LocationServicesManager.getInstance().requestUpdates(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.GoogleImplementation", "access$800", new Object[]{GoogleImplementation.this}));
        }

        public void deactivate() {
            Ensighten.evaluateEvent(this, "deactivate", null);
            LocationServicesManager.getInstance().disableUpdates();
        }
    }

    static /* synthetic */ void access$000(GoogleImplementation x0, CameraUpdate x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.GoogleImplementation", "access$000", new Object[]{x0, x1});
        x0.animateCamera(x1);
    }

    static /* synthetic */ float access$102(GoogleImplementation x0, float x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.GoogleImplementation", "access$102", new Object[]{x0, new Float(x1)});
        x0.mCurrentZoom = x1;
        return x1;
    }

    static /* synthetic */ OnLocationChangedListener access$702(GoogleImplementation x0, OnLocationChangedListener x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.maps.GoogleImplementation", "access$702", new Object[]{x0, x1});
        x0.mListener = x1;
        return x1;
    }

    public GoogleImplementation(GoogleMap map) {
        this.mMap = map;
        this.mMarkers = new HashMap();
        this.mGoogleMarkers = new ArrayList();
    }

    public void moveCamera(com.mcdonalds.app.storelocator.maps.model.LatLng location, float zoom) {
        Ensighten.evaluateEvent(this, "moveCamera", new Object[]{location, new Float(zoom)});
        this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location.toGmaps(), zoom));
    }

    public void moveCamera(com.mcdonalds.app.storelocator.maps.model.CameraPosition position) {
        Ensighten.evaluateEvent(this, "moveCamera", new Object[]{position});
        this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position.target.toGmaps(), position.zoom));
    }

    public void animateCamera(com.mcdonalds.app.storelocator.maps.model.LatLng location, float zoom) {
        Ensighten.evaluateEvent(this, "animateCamera", new Object[]{location, new Float(zoom)});
        this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location.toGmaps(), zoom));
    }

    public void animateCamera(List<com.mcdonalds.app.storelocator.maps.model.LatLng> bounds, int margin) {
        Ensighten.evaluateEvent(this, "animateCamera", new Object[]{bounds, new Integer(margin)});
        Builder builder = new Builder();
        for (com.mcdonalds.app.storelocator.maps.model.LatLng latLng : bounds) {
            builder.include(latLng.toGmaps());
        }
        animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), margin));
    }

    public void setOnCameraChangeListener(McdMap.OnCameraChangeListener listener) {
        Ensighten.evaluateEvent(this, "setOnCameraChangeListener", new Object[]{listener});
        this.mOnCameraChangeListener = listener;
        this.mMap.setOnCameraChangeListener(this.mGoogleOnCameraChangeListener);
    }

    public void clear() {
        Ensighten.evaluateEvent(this, "clear", null);
        for (Marker marker : this.mGoogleMarkers) {
            marker.remove();
        }
        this.mMarkers.clear();
        this.mGoogleMarkers.clear();
    }

    public void setCurrentZoom(float zoom) {
        Ensighten.evaluateEvent(this, "setCurrentZoom", new Object[]{new Float(zoom)});
        this.mCurrentZoom = zoom;
    }

    public float getCurrentZoom() {
        Ensighten.evaluateEvent(this, "getCurrentZoom", null);
        return this.mCurrentZoom;
    }

    public com.mcdonalds.app.storelocator.maps.model.LatLng getUserLocation() {
        Ensighten.evaluateEvent(this, "getUserLocation", null);
        return null;
    }

    public com.mcdonalds.app.storelocator.maps.model.CameraPosition getCameraPosition() {
        Ensighten.evaluateEvent(this, "getCameraPosition", null);
        return com.mcdonalds.app.storelocator.maps.model.CameraPosition.fromGMap(this.mMap.getCameraPosition());
    }

    public com.mcdonalds.app.storelocator.maps.model.Marker addMarker(MarkerOptions options) {
        Ensighten.evaluateEvent(this, "addMarker", new Object[]{options});
        try {
            Marker googleMarker = this.mMap.addMarker(options.toGMap());
            com.mcdonalds.app.storelocator.maps.model.Marker marker = com.mcdonalds.app.storelocator.maps.model.Marker.fromGMap(googleMarker);
            this.mMarkers.put(marker.getId(), marker);
            this.mGoogleMarkers.add(googleMarker);
            return marker;
        } catch (Exception e) {
            return null;
        }
    }

    public void onPause() {
        Ensighten.evaluateEvent(this, "onPause", null);
    }

    public void setOnMapLoadedListener(OnMapLoadedListener onMapLoadedListener) {
        Ensighten.evaluateEvent(this, "setOnMapLoadedListener", new Object[]{onMapLoadedListener});
    }

    public void configure() {
        Ensighten.evaluateEvent(this, "configure", null);
        this.mMap.getUiSettings().setZoomControlsEnabled(false);
        this.mMap.getUiSettings().setTiltGesturesEnabled(false);
        this.mMap.getUiSettings().setCompassEnabled(false);
        this.mMap.getUiSettings().setRotateGesturesEnabled(false);
    }

    public void setMyLocationEnabled(boolean enabled) {
        boolean z = true;
        Ensighten.evaluateEvent(this, "setMyLocationEnabled", new Object[]{new Boolean(enabled)});
        this.mMap.setMyLocationEnabled(enabled);
        UiSettings uiSettings = this.mMap.getUiSettings();
        if (enabled) {
            z = false;
        }
        uiSettings.setMyLocationButtonEnabled(z);
        this.mMap.setLocationSource(this.mLocationSource);
    }

    public void setInfoWindowAdapter(InfoWindowAdapter adapter) {
        Ensighten.evaluateEvent(this, "setInfoWindowAdapter", new Object[]{adapter});
        this.mMap.setInfoWindowAdapter(adapter.toGMap());
    }

    public void setOnMapClickListener(McdMap.OnMapClickListener listener) {
        Ensighten.evaluateEvent(this, "setOnMapClickListener", new Object[]{listener});
        this.mOnMapClickListener = listener;
        this.mMap.setOnMapClickListener(this.mGoogleOnMapClickListener);
    }

    public void setOnMarkerClickListener(McdMap.OnMarkerClickListener listener) {
        Ensighten.evaluateEvent(this, "setOnMarkerClickListener", new Object[]{listener});
        this.mOnMarkerClickListener = listener;
        this.mMap.setOnMarkerClickListener(this.mGoogleOnMarkerClickListener);
    }

    private void animateCamera(final CameraUpdate cameraUpdate) {
        Ensighten.evaluateEvent(this, "animateCamera", new Object[]{cameraUpdate});
        try {
            this.mMap.animateCamera(cameraUpdate, this.mCancelableCallback);
        } catch (IllegalStateException e) {
            new Handler().post(new Runnable() {
                public void run() {
                    Ensighten.evaluateEvent(this, "run", null);
                    GoogleImplementation.access$000(GoogleImplementation.this, cameraUpdate);
                }
            });
        }
    }

    private com.mcdonalds.app.storelocator.maps.model.Marker findMarkerById(String id) {
        Ensighten.evaluateEvent(this, "findMarkerById", new Object[]{id});
        return (com.mcdonalds.app.storelocator.maps.model.Marker) this.mMarkers.get(id);
    }
}
