package com.amap.api.mapcore2d;

import android.location.Location;
import android.os.Handler;
import android.os.RemoteException;
import android.view.View;
import com.amap.api.maps2d.AMap.CancelableCallback;
import com.amap.api.maps2d.AMap.InfoWindowAdapter;
import com.amap.api.maps2d.AMap.OnCacheRemoveListener;
import com.amap.api.maps2d.AMap.OnCameraChangeListener;
import com.amap.api.maps2d.AMap.OnInfoWindowClickListener;
import com.amap.api.maps2d.AMap.OnMapClickListener;
import com.amap.api.maps2d.AMap.OnMapLoadedListener;
import com.amap.api.maps2d.AMap.OnMapLongClickListener;
import com.amap.api.maps2d.AMap.OnMapScreenShotListener;
import com.amap.api.maps2d.AMap.OnMapTouchListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.AMap.OnMarkerDragListener;
import com.amap.api.maps2d.AMap.OnMyLocationChangeListener;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.GroundOverlayOptions;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.PolygonOptions;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.maps2d.model.Text;
import com.amap.api.maps2d.model.TextOptions;
import com.amap.api.maps2d.model.TileOverlay;
import com.amap.api.maps2d.model.TileOverlayOptions;
import java.util.List;

/* renamed from: com.amap.api.mapcore2d.w */
public interface IAMapDelegate {
    /* renamed from: R */
    void mo9936R();

    /* renamed from: S */
    List<Marker> mo9937S() throws RemoteException;

    /* renamed from: T */
    void mo9938T() throws RemoteException;

    /* renamed from: a */
    Handler mo9939a();

    /* renamed from: a */
    IPolygonDelegate mo9940a(PolygonOptions polygonOptions) throws RemoteException;

    /* renamed from: a */
    IPolylineDelegate mo9941a(PolylineOptions polylineOptions) throws RemoteException;

    /* renamed from: a */
    ICircleDelegate mo9942a(CircleOptions circleOptions) throws RemoteException;

    /* renamed from: a */
    IGroundOverlayDelegate mo9943a(GroundOverlayOptions groundOverlayOptions) throws RemoteException;

    /* renamed from: a */
    Marker mo9944a(MarkerOptions markerOptions) throws RemoteException;

    /* renamed from: a */
    Text mo9945a(TextOptions textOptions) throws RemoteException;

    /* renamed from: a */
    TileOverlay mo9946a(TileOverlayOptions tileOverlayOptions) throws RemoteException;

    /* renamed from: a */
    void mo9947a(double d, double d2, C1044r c1044r);

    /* renamed from: a */
    void mo9948a(int i) throws RemoteException;

    /* renamed from: a */
    void mo9949a(int i, int i2, C1044r c1044r);

    /* renamed from: a */
    void mo9950a(Location location);

    /* renamed from: a */
    void mo9951a(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) throws RemoteException;

    /* renamed from: a */
    void mo9952a(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate, long j, CancelableCallback cancelableCallback) throws RemoteException;

    /* renamed from: a */
    void mo9953a(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate, CancelableCallback cancelableCallback) throws RemoteException;

    /* renamed from: a */
    void mo9954a(InfoWindowAdapter infoWindowAdapter) throws RemoteException;

    /* renamed from: a */
    void mo9955a(OnCacheRemoveListener onCacheRemoveListener) throws RemoteException;

    /* renamed from: a */
    void mo9956a(OnCameraChangeListener onCameraChangeListener) throws RemoteException;

    /* renamed from: a */
    void mo9957a(OnInfoWindowClickListener onInfoWindowClickListener) throws RemoteException;

    /* renamed from: a */
    void mo9958a(OnMapClickListener onMapClickListener) throws RemoteException;

    /* renamed from: a */
    void mo9959a(OnMapLoadedListener onMapLoadedListener) throws RemoteException;

    /* renamed from: a */
    void mo9960a(OnMapLongClickListener onMapLongClickListener) throws RemoteException;

    /* renamed from: a */
    void mo9961a(OnMapScreenShotListener onMapScreenShotListener);

    /* renamed from: a */
    void mo9962a(OnMapTouchListener onMapTouchListener) throws RemoteException;

    /* renamed from: a */
    void mo9963a(OnMarkerClickListener onMarkerClickListener) throws RemoteException;

    /* renamed from: a */
    void mo9964a(OnMarkerDragListener onMarkerDragListener) throws RemoteException;

    /* renamed from: a */
    void mo9965a(OnMyLocationChangeListener onMyLocationChangeListener) throws RemoteException;

    /* renamed from: a */
    void mo9966a(LocationSource locationSource) throws RemoteException;

    /* renamed from: a */
    void mo9967a(MyLocationStyle myLocationStyle) throws RemoteException;

    /* renamed from: a */
    void mo9968a(boolean z) throws RemoteException;

    /* renamed from: a */
    boolean mo9969a(String str) throws RemoteException;

    /* renamed from: b */
    C0903ax mo9970b(MarkerOptions markerOptions) throws RemoteException;

    /* renamed from: b */
    void mo9971b(double d, double d2, IPoint iPoint);

    /* renamed from: b */
    void mo9972b(float f) throws RemoteException;

    /* renamed from: b */
    void mo9973b(int i);

    /* renamed from: b */
    void mo9974b(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) throws RemoteException;

    /* renamed from: b */
    void mo9975b(boolean z) throws RemoteException;

    /* renamed from: b */
    boolean mo9976b(String str);

    /* renamed from: c */
    int mo9977c();

    /* renamed from: c */
    void mo9978c(int i);

    /* renamed from: c */
    void mo9979c(String str) throws RemoteException;

    /* renamed from: c */
    void mo9980c(boolean z) throws RemoteException;

    /* renamed from: d */
    int mo9981d();

    /* renamed from: d */
    void mo9982d(boolean z);

    /* renamed from: e */
    View mo9983e() throws RemoteException;

    /* renamed from: e */
    void mo9984e(boolean z);

    /* renamed from: f */
    float mo9985f();

    /* renamed from: f */
    void mo9986f(boolean z);

    /* renamed from: g */
    CameraPosition mo9987g() throws RemoteException;

    /* renamed from: g */
    void mo9988g(boolean z);

    /* renamed from: h */
    float mo9989h();

    /* renamed from: i */
    float mo9990i();

    /* renamed from: j */
    void mo9991j() throws RemoteException;

    /* renamed from: k */
    void mo9992k() throws RemoteException;

    /* renamed from: l */
    int mo9993l() throws RemoteException;

    /* renamed from: m */
    boolean mo9994m() throws RemoteException;

    /* renamed from: n */
    boolean mo9995n() throws RemoteException;

    /* renamed from: p */
    Location mo9996p() throws RemoteException;

    /* renamed from: q */
    IUiSettingsDelegate mo9997q() throws RemoteException;

    /* renamed from: r */
    IProjectionDelegate mo9998r() throws RemoteException;

    /* renamed from: s */
    Projection mo9999s() throws RemoteException;

    /* renamed from: v */
    void mo10000v();

    /* renamed from: w */
    float mo10001w();

    /* renamed from: y */
    void mo10002y();

    /* renamed from: z */
    void mo10003z();
}
