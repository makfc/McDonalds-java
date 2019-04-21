package com.mcdonalds.app.storelocator.maps;

import com.mcdonalds.app.storelocator.maps.model.CameraPosition;
import com.mcdonalds.app.storelocator.maps.model.LatLng;
import com.mcdonalds.app.storelocator.maps.model.Marker;
import com.mcdonalds.app.storelocator.maps.model.MarkerOptions;
import java.util.List;

public interface McdMap {

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker marker);
    }

    public interface OnMapClickListener {
        void onMapClick(LatLng latLng);
    }

    public interface OnMapLoadedListener {
        void onMapLoaded();
    }

    public interface InfoWindowAdapter {
        com.amap.api.maps.AMap.InfoWindowAdapter toAMap();

        com.amap.api.maps2d.AMap.InfoWindowAdapter toAMap2D();

        com.google.android.gms.maps.GoogleMap.InfoWindowAdapter toGMap();
    }

    public interface OnCameraChangeListener {
        void onCameraChange(CameraPosition cameraPosition);
    }

    Marker addMarker(MarkerOptions markerOptions);

    void animateCamera(LatLng latLng, float f);

    void animateCamera(List<LatLng> list, int i);

    void clear();

    void configure();

    CameraPosition getCameraPosition();

    float getCurrentZoom();

    LatLng getUserLocation();

    void moveCamera(CameraPosition cameraPosition);

    void moveCamera(LatLng latLng, float f);

    void onPause();

    void setCurrentZoom(float f);

    void setInfoWindowAdapter(InfoWindowAdapter infoWindowAdapter);

    void setMyLocationEnabled(boolean z);

    void setOnCameraChangeListener(OnCameraChangeListener onCameraChangeListener);

    void setOnMapClickListener(OnMapClickListener onMapClickListener);

    void setOnMapLoadedListener(OnMapLoadedListener onMapLoadedListener);

    void setOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener);
}
