package com.autonavi.amap.mapcore.interfaces;

import android.graphics.Point;
import android.graphics.Rect;
import android.location.Location;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import com.amap.api.mapcore.indoor.IndoorBuilding;
import com.amap.api.maps.AMap.CancelableCallback;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnCacheRemoveListener;
import com.amap.api.maps.AMap.OnCameraChangeListener;
import com.amap.api.maps.AMap.OnIndoorBuildingActiveListener;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapClickListener;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.AMap.OnMapLongClickListener;
import com.amap.api.maps.AMap.OnMapScreenShotListener;
import com.amap.api.maps.AMap.OnMapTouchListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.AMap.OnMarkerDragListener;
import com.amap.api.maps.AMap.OnMyLocationChangeListener;
import com.amap.api.maps.AMap.OnPOIClickListener;
import com.amap.api.maps.AMap.OnPolylineClickListener;
import com.amap.api.maps.AMap.onMapPrintScreenListener;
import com.amap.api.maps.CustomRenderer;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.ArcOptions;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.MyTrafficStyle;
import com.amap.api.maps.model.NavigateArrowOptions;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapCore;
import com.autonavi.amap.mapcore.MapProjection;
import java.util.ArrayList;
import java.util.List;

public interface IAMapDelegate {
    IArcDelegate addArc(ArcOptions arcOptions) throws RemoteException;

    ICircleDelegate addCircle(CircleOptions circleOptions) throws RemoteException;

    IGroundOverlayDelegate addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException;

    Marker addMarker(MarkerOptions markerOptions) throws RemoteException;

    IMarkerDelegate addMarker4Imp(MarkerOptions markerOptions) throws RemoteException;

    ArrayList<Marker> addMarkers(ArrayList<MarkerOptions> arrayList, boolean z) throws RemoteException;

    INavigateArrowDelegate addNavigateArrow(NavigateArrowOptions navigateArrowOptions) throws RemoteException;

    void addOverlay(GLOverlay gLOverlay);

    IPolygonDelegate addPolygon(PolygonOptions polygonOptions) throws RemoteException;

    IPolylineDelegate addPolyline(PolylineOptions polylineOptions) throws RemoteException;

    Text addText(TextOptions textOptions) throws RemoteException;

    TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException;

    void animateCamera(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) throws RemoteException;

    void animateCameraWithCallback(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate, CancelableCallback cancelableCallback) throws RemoteException;

    void animateCameraWithDurationAndCallback(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate, long j, CancelableCallback cancelableCallback) throws RemoteException;

    void callRunDestroy();

    void changeGLOverlayIndex();

    float checkZoomLevel(float f) throws RemoteException;

    void clear() throws RemoteException;

    void clear(boolean z) throws RemoteException;

    void clearGLOverlay();

    void deleteTexsureId(int i);

    void destroy();

    void geo2Latlng(int i, int i2, DPoint dPoint);

    void geo2Map(int i, int i2, FPoint fPoint);

    int getAnchorX();

    int getAnchorY();

    CameraAnimator getCameraAnimator();

    CameraPosition getCameraPosition() throws RemoteException;

    CameraPosition getCameraPositionPrj(boolean z);

    int getImaginaryLineTextureID();

    void getLatLng2Map(double d, double d2, FPoint fPoint);

    void getLatLng2Pixel(double d, double d2, IPoint iPoint);

    int getLineTextureID();

    int getLogoPosition();

    LatLngBounds getMapBounds();

    LatLngBounds getMapBounds(LatLng latLng, float f);

    MapCore getMapCore();

    int getMapHeight();

    void getMapPrintScreen(onMapPrintScreenListener onmapprintscreenlistener);

    MapProjection getMapProjection();

    List<Marker> getMapScreenMarkers() throws RemoteException;

    void getMapScreenShot(OnMapScreenShotListener onMapScreenShotListener);

    int getMapTextZIndex() throws RemoteException;

    int getMapType() throws RemoteException;

    int getMapWidth();

    float getMapZoomScale();

    float getMaxZoomLevel();

    float getMinZoomLevel();

    Location getMyLocation() throws RemoteException;

    OnCameraChangeListener getOnCameraChangeListener() throws RemoteException;

    void getPixel2Geo(int i, int i2, IPoint iPoint);

    void getPixel2LatLng(int i, int i2, DPoint dPoint);

    IProjectionDelegate getProjection() throws RemoteException;

    Rect getRect();

    float getScalePerPixel() throws RemoteException;

    int getTexsureId();

    IUiSettingsDelegate getUiSettings() throws RemoteException;

    View getView() throws RemoteException;

    Point getWaterMarkerPositon();

    float getZoomLevel();

    void hiddenInfoWindowShown();

    boolean isAdreno();

    boolean isDrawOnce();

    boolean isIndoorEnabled() throws RemoteException;

    boolean isInfoWindowShown(IMarkerDelegate iMarkerDelegate) throws RemoteException;

    boolean isMaploaded();

    boolean isMyLocationEnabled() throws RemoteException;

    boolean isNeedRunDestroy();

    boolean isTrafficEnabled() throws RemoteException;

    boolean isUseAnchor();

    void latlon2Geo(double d, double d2, IPoint iPoint);

    void map2Geo(float f, float f2, IPoint iPoint);

    void moveCamera(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) throws RemoteException;

    void onActivityPause();

    void onActivityResume();

    void onPause();

    void onResume();

    boolean onTouchEvent(MotionEvent motionEvent);

    void pixel2Map(int i, int i2, FPoint fPoint);

    void queueEvent(Runnable runnable);

    void redrawInfoWindow();

    void reloadMap();

    boolean removeGLOverlay(String str) throws RemoteException;

    boolean removeMarker(String str);

    void removeOverlay(GLOverlay gLOverlay);

    void removecache() throws RemoteException;

    void removecache(OnCacheRemoveListener onCacheRemoveListener) throws RemoteException;

    void runDestroy();

    void set3DBuildingEnabled(boolean z) throws RemoteException;

    void setCenterToPixel(int i, int i2) throws RemoteException;

    void setCustomRenderer(CustomRenderer customRenderer) throws RemoteException;

    void setIndoorBuildingInfo(IndoorBuilding indoorBuilding) throws RemoteException;

    void setIndoorEnabled(boolean z) throws RemoteException;

    void setInfoWindowAdapter(InfoWindowAdapter infoWindowAdapter) throws RemoteException;

    void setLoadOfflineData(boolean z) throws RemoteException;

    void setLocationSource(LocationSource locationSource) throws RemoteException;

    void setLogoPosition(int i);

    void setMapTextEnable(boolean z) throws RemoteException;

    void setMapTextZIndex(int i) throws RemoteException;

    void setMapType(int i) throws RemoteException;

    void setMyLocationEnabled(boolean z) throws RemoteException;

    void setMyLocationRotateAngle(float f) throws RemoteException;

    void setMyLocationStyle(MyLocationStyle myLocationStyle) throws RemoteException;

    void setMyLocationType(int i) throws RemoteException;

    void setMyTrafficStyle(MyTrafficStyle myTrafficStyle) throws RemoteException;

    void setOnCameraChangeListener(OnCameraChangeListener onCameraChangeListener) throws RemoteException;

    void setOnIndoorBuildingActiveListener(OnIndoorBuildingActiveListener onIndoorBuildingActiveListener) throws RemoteException;

    void setOnInfoWindowClickListener(OnInfoWindowClickListener onInfoWindowClickListener) throws RemoteException;

    void setOnMapClickListener(OnMapClickListener onMapClickListener) throws RemoteException;

    void setOnMapLongClickListener(OnMapLongClickListener onMapLongClickListener) throws RemoteException;

    void setOnMapTouchListener(OnMapTouchListener onMapTouchListener) throws RemoteException;

    void setOnMaploadedListener(OnMapLoadedListener onMapLoadedListener) throws RemoteException;

    void setOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) throws RemoteException;

    void setOnMarkerDragListener(OnMarkerDragListener onMarkerDragListener) throws RemoteException;

    void setOnMyLocationChangeListener(OnMyLocationChangeListener onMyLocationChangeListener) throws RemoteException;

    void setOnPOIClickListener(OnPOIClickListener onPOIClickListener) throws RemoteException;

    void setOnPolylineClickListener(OnPolylineClickListener onPolylineClickListener) throws RemoteException;

    void setRenderFps(int i);

    void setRunLowFrame(boolean z);

    void setTrafficEnabled(boolean z) throws RemoteException;

    void setVisibilityEx(int i);

    void setZOrderOnTop(boolean z) throws RemoteException;

    void setZoomPosition(int i);

    void showCompassEnabled(boolean z);

    void showIndoorSwitchControlsEnabled(boolean z);

    void showInfoWindow(IMarkerDelegate iMarkerDelegate) throws RemoteException;

    void showMyLocationButtonEnabled(boolean z);

    void showMyLocationOverlay(Location location) throws RemoteException;

    void showScaleEnabled(boolean z);

    void showZoomControlsEnabled(boolean z);

    void stopAnimation() throws RemoteException;

    float toMapLenWithWin(int i);
}
