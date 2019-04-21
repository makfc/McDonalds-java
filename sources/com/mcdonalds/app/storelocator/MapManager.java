package com.mcdonalds.app.storelocator;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.RemoteException;
import android.support.p000v4.app.Fragment;
import android.widget.Toast;
import com.ensighten.Ensighten;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.mcdonalds.app.storelocator.maps.AMap2DImplementation;
import com.mcdonalds.app.storelocator.maps.AMapImplementation;
import com.mcdonalds.app.storelocator.maps.GoogleImplementation;
import com.mcdonalds.app.storelocator.maps.McdMap;
import com.mcdonalds.app.storelocator.maps.McdMap.OnMapLoadedListener;
import com.mcdonalds.app.storelocator.maps.model.LatLng;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule.NativeMapsSDK;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.log.SafeLog;

public class MapManager {
    private static final String LOG_TAG = MapManager.class.getSimpleName();
    private Context mContext;
    private Callback mListener;
    private McdMap mMap;
    private Fragment mMapFragment;
    private final OnMapReadyCallback mMapReadyCallback = new C37051();
    private final OnMapLoadedListener mOnMapLoadedListener = new C37062();

    public interface Callback {
        void onMapAvailable();

        void onMapError(Dialog dialog);
    }

    /* renamed from: com.mcdonalds.app.storelocator.MapManager$1 */
    class C37051 implements OnMapReadyCallback {
        C37051() {
        }

        public void onMapReady(GoogleMap googleMap) {
            Ensighten.evaluateEvent(this, "onMapReady", new Object[]{googleMap});
            MapManager.access$002(MapManager.this, new GoogleImplementation(googleMap));
            MapManager.access$100(MapManager.this);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.MapManager", "access$200", new Object[]{MapManager.this}).onMapAvailable();
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.MapManager$2 */
    class C37062 implements OnMapLoadedListener {
        C37062() {
        }

        public void onMapLoaded() {
            Ensighten.evaluateEvent(this, "onMapLoaded", null);
            MapManager.access$300(MapManager.this);
        }
    }

    static /* synthetic */ McdMap access$002(MapManager x0, McdMap x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.MapManager", "access$002", new Object[]{x0, x1});
        x0.mMap = x1;
        return x1;
    }

    static /* synthetic */ void access$100(MapManager x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.MapManager", "access$100", new Object[]{x0});
        x0.setDefaults();
    }

    static /* synthetic */ void access$300(MapManager x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.MapManager", "access$300", new Object[]{x0});
        x0.notifyMapAvailable();
    }

    public MapManager(Context context, Callback callback) {
        this.mContext = context;
        this.mListener = callback;
        init();
    }

    /* Access modifiers changed, original: protected */
    public void init() {
        Ensighten.evaluateEvent(this, "init", null);
        createMapFragment();
    }

    private void checkForPlayServices() {
        Ensighten.evaluateEvent(this, "checkForPlayServices", null);
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mContext);
        if (resultCode == 0) {
            this.mMapFragment.getMapAsync(this.mMapReadyCallback);
        } else if (resultCode == 1 || resultCode == 2 || resultCode == 3) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode, (Activity) this.mContext, 1);
            if (this.mListener != null) {
                this.mListener.onMapError(dialog);
            }
        } else if (this.mListener != null) {
            this.mListener.onMapError(null);
        }
    }

    private void createMapFragment() {
        Ensighten.evaluateEvent(this, "createMapFragment", null);
        NativeMapsSDK mapsSDK = ((StoreLocatorModule) ModuleManager.getModule(StoreLocatorModule.NAME)).getPreferredMapsSDK();
        if (mapsSDK == NativeMapsSDK.Google) {
            MapsInitializer.initialize(this.mContext);
            this.mMapFragment = new SupportMapFragment();
            checkForPlayServices();
            return;
        }
        if (mapsSDK == NativeMapsSDK.AutoNavi) {
            try {
                com.amap.api.maps.MapsInitializer.initialize(this.mContext);
                com.amap.api.maps.SupportMapFragment fragment = new com.amap.api.maps.SupportMapFragment();
                this.mMap = new AMapImplementation(fragment.getMap());
                this.mMapFragment = fragment;
            } catch (RemoteException e) {
                if (this.mContext != null) {
                    Toast.makeText(this.mContext, C2658R.string.error_initialize_map, 0).show();
                }
                SafeLog.m7745e(LOG_TAG, "error initializing map");
                return;
            }
        }
        try {
            com.amap.api.maps2d.MapsInitializer.initialize(this.mContext);
            com.amap.api.maps2d.SupportMapFragment fragment2 = new com.amap.api.maps2d.SupportMapFragment();
            this.mMap = new AMap2DImplementation(fragment2.getMap());
            this.mMapFragment = fragment2;
        } catch (RemoteException e2) {
            if (this.mContext != null) {
                Toast.makeText(this.mContext, C2658R.string.error_initialize_map, 0).show();
            }
            SafeLog.m7745e(LOG_TAG, "error initializing map");
            return;
        }
        this.mMap.setOnMapLoadedListener(this.mOnMapLoadedListener);
        setDefaults();
    }

    private void setDefaults() {
        LatLng defaultLocation;
        Ensighten.evaluateEvent(this, "setDefaults", null);
        if (Configuration.getSharedInstance().hasKey("mapDefaults.location")) {
            defaultLocation = AppUtils.getLocationFromString((String) Configuration.getSharedInstance().getValueForKey("mapDefaults.location"));
        } else {
            defaultLocation = AppUtils.getLocationFromString((String) Configuration.getSharedInstance().getValueForKey("mapDefaults.latitude"), (String) Configuration.getSharedInstance().getValueForKey("mapDefaults.longitude"));
        }
        if (this.mMap != null && defaultLocation != null) {
            this.mMap.moveCamera(defaultLocation, (float) Configuration.getSharedInstance().getIntForKey("mapDefaults.zoom"));
        }
    }

    private void notifyMapAvailable() {
        Ensighten.evaluateEvent(this, "notifyMapAvailable", null);
        if (this.mListener != null) {
            this.mListener.onMapAvailable();
        }
    }

    public McdMap getMap() {
        Ensighten.evaluateEvent(this, "getMap", null);
        return this.mMap;
    }

    public Fragment getFragment() {
        Ensighten.evaluateEvent(this, "getFragment", null);
        return this.mMapFragment;
    }

    public void setCallback(Callback callback) {
        Ensighten.evaluateEvent(this, "setCallback", new Object[]{callback});
        this.mListener = callback;
    }
}
