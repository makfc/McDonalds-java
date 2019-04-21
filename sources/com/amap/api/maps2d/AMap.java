package com.amap.api.maps2d;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import com.amap.api.mapcore2d.C0953ch;
import com.amap.api.mapcore2d.C0955ck;
import com.amap.api.mapcore2d.IAMapDelegate;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.Circle;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.GroundOverlay;
import com.amap.api.maps2d.model.GroundOverlayOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.Polygon;
import com.amap.api.maps2d.model.PolygonOptions;
import com.amap.api.maps2d.model.Polyline;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.maps2d.model.RuntimeRemoteException;
import com.amap.api.maps2d.model.Text;
import com.amap.api.maps2d.model.TextOptions;
import com.amap.api.maps2d.model.TileOverlay;
import com.amap.api.maps2d.model.TileOverlayOptions;
import java.util.List;

public final class AMap {
    public static final String CHINESE = "zh_cn";
    public static final String ENGLISH = "en";
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    /* renamed from: a */
    private final IAMapDelegate f3347a;
    /* renamed from: b */
    private UiSettings f3348b;
    /* renamed from: c */
    private Projection f3349c;

    public interface CancelableCallback {
        void onCancel();

        void onFinish();
    }

    public interface InfoWindowAdapter {
        View getInfoContents(Marker marker);

        View getInfoWindow(Marker marker);
    }

    public interface OnCacheRemoveListener {
        void onRemoveCacheFinish(boolean z);
    }

    public interface OnCameraChangeListener {
        void onCameraChange(CameraPosition cameraPosition);

        void onCameraChangeFinish(CameraPosition cameraPosition);
    }

    public interface OnInfoWindowClickListener {
        void onInfoWindowClick(Marker marker);
    }

    public interface OnMapClickListener {
        void onMapClick(LatLng latLng);
    }

    public interface OnMapLoadedListener {
        void onMapLoaded();
    }

    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng latLng);
    }

    public interface OnMapScreenShotListener {
        void onMapScreenShot(Bitmap bitmap);
    }

    public interface OnMapTouchListener {
        void onTouch(MotionEvent motionEvent);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker marker);
    }

    public interface OnMarkerDragListener {
        void onMarkerDrag(Marker marker);

        void onMarkerDragEnd(Marker marker);

        void onMarkerDragStart(Marker marker);
    }

    public interface OnMyLocationChangeListener {
        void onMyLocationChange(Location location);
    }

    protected AMap(IAMapDelegate iAMapDelegate) {
        this.f3347a = iAMapDelegate;
    }

    /* renamed from: a */
    private IAMapDelegate m4558a() {
        return this.f3347a;
    }

    public final CameraPosition getCameraPosition() {
        String str = "getCameraPosition";
        try {
            return m4558a().mo9987g();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final float getMaxZoomLevel() {
        return m4558a().mo9989h();
    }

    public final float getMinZoomLevel() {
        return m4558a().mo9990i();
    }

    public final void moveCamera(CameraUpdate cameraUpdate) {
        String str = "moveCamera";
        try {
            m4558a().mo9951a(cameraUpdate.mo11273a());
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate) {
        String str = "animateCamera";
        try {
            m4558a().mo9974b(cameraUpdate.mo11273a());
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate, CancelableCallback cancelableCallback) {
        String str = "animateCamera";
        try {
            m4558a().mo9953a(cameraUpdate.mo11273a(), cancelableCallback);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate, long j, CancelableCallback cancelableCallback) {
        String str = "animateCamera";
        try {
            C0953ch.m3874b(j > 0, "durationMs must be positive");
            m4558a().mo9952a(cameraUpdate.mo11273a(), j, cancelableCallback);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void stopAnimation() {
        String str = "stopAnimation";
        try {
            m4558a().mo9991j();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final Polyline addPolyline(PolylineOptions polylineOptions) {
        String str = "addPolyline";
        try {
            return new Polyline(m4558a().mo9941a(polylineOptions));
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final Text addText(TextOptions textOptions) {
        try {
            return this.f3347a.mo9945a(textOptions);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", "addText");
            throw new RuntimeRemoteException(e);
        }
    }

    public final Circle addCircle(CircleOptions circleOptions) {
        String str = "addCircle";
        try {
            return new Circle(m4558a().mo9942a(circleOptions));
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final Polygon addPolygon(PolygonOptions polygonOptions) {
        String str = "addPolygon";
        try {
            return new Polygon(m4558a().mo9940a(polygonOptions));
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final Marker addMarker(MarkerOptions markerOptions) {
        String str = "addMarker";
        try {
            return m4558a().mo9944a(markerOptions);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final GroundOverlay addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        String str = "addGroundOverlay";
        try {
            return new GroundOverlay(m4558a().mo9943a(groundOverlayOptions));
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) {
        String str = "addtileOverlay";
        try {
            return m4558a().mo9946a(tileOverlayOptions);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void clear() {
        String str = "clear";
        try {
            if (m4558a() != null) {
                m4558a().mo9992k();
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        } catch (Throwable th) {
            C0955ck.m3888a(th, "AMap", str);
        }
    }

    public final int getMapType() {
        String str = "getMapType";
        try {
            return m4558a().mo9993l();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setMapType(int i) {
        String str = "setMapType";
        try {
            m4558a().mo9948a(i);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setMyLocationRotateAngle(float f) {
        String str = "setMyLocationRoteteAngle";
        try {
            this.f3347a.mo9972b(f);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isTrafficEnabled() {
        String str = "isTrafficEnable";
        try {
            return m4558a().mo9994m();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void setTrafficEnabled(boolean z) {
        String str = "setTradficEnabled";
        try {
            m4558a().mo9975b(z);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isMyLocationEnabled() {
        String str = "isMyLocationEnabled";
        try {
            return m4558a().mo9995n();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public static String getVersion() {
        return "2.9.0";
    }

    public final void setMyLocationEnabled(boolean z) {
        String str = "setMyLocationEnabled";
        try {
            m4558a().mo9980c(z);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final Location getMyLocation() {
        String str = "getMyLocation";
        try {
            return m4558a().mo9996p();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setLocationSource(LocationSource locationSource) {
        String str = "setLocationSource";
        try {
            m4558a().mo9966a(locationSource);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setMyLocationStyle(MyLocationStyle myLocationStyle) {
        String str = "setMyLocationStyle";
        try {
            m4558a().mo9967a(myLocationStyle);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final UiSettings getUiSettings() {
        String str = "getUiSettings";
        try {
            if (this.f3348b == null) {
                this.f3348b = new UiSettings(m4558a().mo9997q());
            }
            return this.f3348b;
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final Projection getProjection() {
        String str = "getProjection";
        try {
            if (this.f3349c == null) {
                this.f3349c = new Projection(m4558a().mo9998r());
            }
            return this.f3349c;
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnCameraChangeListener(OnCameraChangeListener onCameraChangeListener) {
        String str = "setOnCameraChangeListener";
        try {
            m4558a().mo9956a(onCameraChangeListener);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnMapClickListener(OnMapClickListener onMapClickListener) {
        String str = "setOnMapClickListener";
        try {
            m4558a().mo9958a(onMapClickListener);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnMapTouchListener(OnMapTouchListener onMapTouchListener) {
        String str = "setOnMapTouchListener";
        try {
            this.f3347a.mo9962a(onMapTouchListener);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnMyLocationChangeListener(OnMyLocationChangeListener onMyLocationChangeListener) {
        String str = "setOnMyLocaitonChangeListener";
        try {
            m4558a().mo9965a(onMyLocationChangeListener);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnMapLongClickListener(OnMapLongClickListener onMapLongClickListener) {
        String str = "setOnMapLongClickListener";
        try {
            m4558a().mo9960a(onMapLongClickListener);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        String str = "setOnMarkerClickListener";
        try {
            m4558a().mo9963a(onMarkerClickListener);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnMarkerDragListener(OnMarkerDragListener onMarkerDragListener) {
        String str = "setOnMarkerDragListener";
        try {
            m4558a().mo9964a(onMarkerDragListener);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnInfoWindowClickListener(OnInfoWindowClickListener onInfoWindowClickListener) {
        String str = "setOnInfoWindowClickListener";
        try {
            m4558a().mo9957a(onInfoWindowClickListener);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setInfoWindowAdapter(InfoWindowAdapter infoWindowAdapter) {
        String str = "setInfoWindowAdapter";
        try {
            m4558a().mo9954a(infoWindowAdapter);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnMapLoadedListener(OnMapLoadedListener onMapLoadedListener) {
        String str = "setOnMapLoadedListener";
        try {
            m4558a().mo9959a(onMapLoadedListener);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void getMapScreenShot(OnMapScreenShotListener onMapScreenShotListener) {
        m4558a().mo9961a(onMapScreenShotListener);
        invalidate();
    }

    public float getScalePerPixel() {
        return m4558a().mo10001w();
    }

    public final List<Marker> getMapScreenMarkers() {
        String str = "getMapScreenaMarkers";
        try {
            return this.f3347a.mo9937S();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void postInvalidate() {
        m4558a().mo9936R();
    }

    public void invalidate() {
        postInvalidate();
    }

    public void setMapLanguage(String str) {
        String str2 = "setMapLanguage";
        try {
            this.f3347a.mo9979c(str);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str2);
            throw new RuntimeRemoteException(e);
        }
    }

    public void removecache() {
        String str = "removecache";
        try {
            this.f3347a.mo9938T();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }

    public void removecache(OnCacheRemoveListener onCacheRemoveListener) {
        String str = "removecache";
        try {
            this.f3347a.mo9955a(onCacheRemoveListener);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMap", str);
            throw new RuntimeRemoteException(e);
        }
    }
}
