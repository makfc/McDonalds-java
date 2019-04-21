package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.p000v4.view.PointerIconCompat;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amap.api.mapcore.indoor.IndoorBuilding;
import com.amap.api.mapcore.util.GLMapResManager.C0872a;
import com.amap.api.mapcore.util.GLMapResManager.C0873b;
import com.amap.api.mapcore.util.GLMapResManager.C0874c;
import com.amap.api.mapcore.util.IndoorFloorSwitchView.C0793a;
import com.amap.api.mapcore.util.MapOverlayViewGroup.C0736a;
import com.amap.api.mapcore.util.MultiTouchSupport.C0754a;
import com.amap.api.mapcore.util.RotateGestureDetector.C0755a;
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
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.MyTrafficStyle;
import com.amap.api.maps.model.NavigateArrowOptions;
import com.amap.api.maps.model.Poi;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
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
import com.autonavi.amap.mapcore.SelectedMapPoi;
import com.autonavi.amap.mapcore.VMapDataCache;
import com.autonavi.amap.mapcore.VTMCDataCache;
import com.autonavi.amap.mapcore.interfaces.CameraAnimator;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate.Type;
import com.autonavi.amap.mapcore.interfaces.GLOverlay;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IArcDelegate;
import com.autonavi.amap.mapcore.interfaces.ICircleDelegate;
import com.autonavi.amap.mapcore.interfaces.IGLSurfaceView;
import com.autonavi.amap.mapcore.interfaces.IGroundOverlayDelegate;
import com.autonavi.amap.mapcore.interfaces.IMarkerDelegate;
import com.autonavi.amap.mapcore.interfaces.INavigateArrowDelegate;
import com.autonavi.amap.mapcore.interfaces.IOverlayDelegate;
import com.autonavi.amap.mapcore.interfaces.IPolygonDelegate;
import com.autonavi.amap.mapcore.interfaces.IPolylineDelegate;
import com.autonavi.amap.mapcore.interfaces.IProjectionDelegate;
import com.autonavi.amap.mapcore.interfaces.ITileOverlayDelegate;
import com.autonavi.amap.mapcore.interfaces.IUiSettingsDelegate;
import com.facebook.widget.ToolTipPopup;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.util.c */
public class AMapDelegateImp implements Renderer, IAMapDelegate {
    /* renamed from: aG */
    private static final double f1532aG = Math.log(2.0d);
    /* renamed from: A */
    private CopyOnWriteArrayList<Integer> f1533A;
    /* renamed from: B */
    private CopyOnWriteArrayList<Integer> f1534B;
    /* renamed from: C */
    private C0874c f1535C;
    /* renamed from: D */
    private C0872a f1536D;
    /* renamed from: E */
    private C0873b f1537E;
    /* renamed from: F */
    private int f1538F;
    /* renamed from: G */
    private MapCore f1539G;
    /* renamed from: H */
    private Context f1540H;
    /* renamed from: I */
    private AMapCallback f1541I;
    /* renamed from: J */
    private MapProjection f1542J;
    /* renamed from: K */
    private GestureDetector f1543K;
    /* renamed from: L */
    private ScaleGestureDetector f1544L;
    /* renamed from: M */
    private RotateGestureDetector f1545M;
    /* renamed from: N */
    private SurfaceHolder f1546N;
    /* renamed from: O */
    private MapOverlayViewGroup f1547O;
    /* renamed from: P */
    private WaterMarkerView f1548P;
    /* renamed from: Q */
    private LocationView f1549Q;
    /* renamed from: R */
    private CompassView f1550R;
    /* renamed from: S */
    private ScaleView f1551S;
    /* renamed from: T */
    private BlankView f1552T;
    /* renamed from: U */
    private IndoorFloorSwitchView f1553U;
    /* renamed from: V */
    private OnMyLocationChangeListener f1554V;
    /* renamed from: W */
    private OnMarkerClickListener f1555W;
    /* renamed from: X */
    private OnPolylineClickListener f1556X;
    /* renamed from: Y */
    private OnMarkerDragListener f1557Y;
    /* renamed from: Z */
    private OnMapLoadedListener f1558Z;
    /* renamed from: a */
    float f1559a;
    /* renamed from: aA */
    private onMapPrintScreenListener f1560aA;
    /* renamed from: aB */
    private OnMapScreenShotListener f1561aB;
    /* renamed from: aC */
    private Handler f1562aC;
    /* renamed from: aD */
    private IndoorBuilding f1563aD;
    /* renamed from: aE */
    private CameraUpdateFactoryDelegate f1564aE;
    /* renamed from: aF */
    private Timer f1565aF;
    /* renamed from: aH */
    private boolean f1566aH;
    /* renamed from: aI */
    private boolean f1567aI;
    /* renamed from: aJ */
    private boolean f1568aJ;
    /* renamed from: aK */
    private boolean f1569aK;
    /* renamed from: aL */
    private boolean f1570aL;
    /* renamed from: aM */
    private boolean f1571aM;
    /* renamed from: aN */
    private boolean f1572aN;
    /* renamed from: aO */
    private boolean f1573aO;
    /* renamed from: aP */
    private boolean f1574aP;
    /* renamed from: aQ */
    private Boolean f1575aQ;
    /* renamed from: aR */
    private boolean f1576aR;
    /* renamed from: aS */
    private boolean f1577aS;
    /* renamed from: aT */
    private boolean f1578aT;
    /* renamed from: aU */
    private Handler f1579aU;
    /* renamed from: aV */
    private int f1580aV;
    /* renamed from: aW */
    private CustomGLOverlayLayer f1581aW;
    /* renamed from: aX */
    private boolean f1582aX;
    /* renamed from: aY */
    private boolean f1583aY;
    /* renamed from: aZ */
    private volatile boolean f1584aZ;
    /* renamed from: aa */
    private OnCameraChangeListener f1585aa;
    /* renamed from: ab */
    private OnMapClickListener f1586ab;
    /* renamed from: ac */
    private OnMapTouchListener f1587ac;
    /* renamed from: ad */
    private OnPOIClickListener f1588ad;
    /* renamed from: ae */
    private OnMapLongClickListener f1589ae;
    /* renamed from: af */
    private OnInfoWindowClickListener f1590af;
    /* renamed from: ag */
    private OnIndoorBuildingActiveListener f1591ag;
    /* renamed from: ah */
    private InfoWindowAdapter f1592ah;
    /* renamed from: ai */
    private InfoWindowAdapter f1593ai;
    /* renamed from: aj */
    private View f1594aj;
    /* renamed from: ak */
    private IMarkerDelegate f1595ak;
    /* renamed from: al */
    private PopupOverlay f1596al;
    /* renamed from: am */
    private IProjectionDelegate f1597am;
    /* renamed from: an */
    private IUiSettingsDelegate f1598an;
    /* renamed from: ao */
    private LocationSource f1599ao;
    /* renamed from: ap */
    private Rect f1600ap;
    /* renamed from: aq */
    private AMapOnLocationChangedListener f1601aq;
    /* renamed from: ar */
    private MultiTouchSupport f1602ar;
    /* renamed from: as */
    private MyLocationOverlay f1603as;
    /* renamed from: at */
    private CameraAnimator f1604at;
    /* renamed from: au */
    private int f1605au;
    /* renamed from: av */
    private int f1606av;
    /* renamed from: aw */
    private CancelableCallback f1607aw;
    /* renamed from: ax */
    private int f1608ax;
    /* renamed from: ay */
    private Drawable f1609ay;
    /* renamed from: az */
    private Location f1610az;
    /* renamed from: b */
    float f1611b;
    /* renamed from: ba */
    private volatile boolean f1612ba;
    /* renamed from: bb */
    private Handler f1613bb;
    /* renamed from: bc */
    private Runnable f1614bc;
    /* renamed from: bd */
    private volatile boolean f1615bd;
    /* renamed from: be */
    private boolean f1616be;
    /* renamed from: bf */
    private boolean f1617bf;
    /* renamed from: bg */
    private boolean f1618bg;
    /* renamed from: bh */
    private Marker f1619bh;
    /* renamed from: bi */
    private IMarkerDelegate f1620bi;
    /* renamed from: bj */
    private boolean f1621bj;
    /* renamed from: bk */
    private boolean f1622bk;
    /* renamed from: bl */
    private boolean f1623bl;
    /* renamed from: bm */
    private int f1624bm;
    /* renamed from: bn */
    private boolean f1625bn;
    /* renamed from: bo */
    private Thread f1626bo;
    /* renamed from: bp */
    private LatLngBounds f1627bp;
    /* renamed from: bq */
    private boolean f1628bq;
    /* renamed from: br */
    private boolean f1629br;
    /* renamed from: bs */
    private int f1630bs;
    /* renamed from: bt */
    private int f1631bt;
    /* renamed from: bu */
    private Handler f1632bu;
    /* renamed from: bv */
    private Runnable f1633bv;
    /* renamed from: bw */
    private Runnable f1634bw;
    /* renamed from: bx */
    private C0786a f1635bx;
    /* renamed from: c */
    float f1636c;
    /* renamed from: d */
    public MapOverlayImageView f1637d;
    /* renamed from: e */
    MapMessageQueue f1638e;
    /* renamed from: f */
    ZoomControllerView f1639f;
    /* renamed from: g */
    TileOverlayView f1640g;
    /* renamed from: h */
    GLOverlayLayer f1641h;
    /* renamed from: i */
    GLMapResManager f1642i;
    /* renamed from: j */
    IGLSurfaceView f1643j;
    /* renamed from: k */
    Runnable f1644k;
    /* renamed from: l */
    final Handler f1645l;
    /* renamed from: m */
    CustomRenderer f1646m;
    /* renamed from: n */
    private int f1647n;
    /* renamed from: o */
    private int f1648o;
    /* renamed from: p */
    private int f1649p;
    /* renamed from: q */
    private Bitmap f1650q;
    /* renamed from: r */
    private Bitmap f1651r;
    /* renamed from: s */
    private int f1652s;
    /* renamed from: t */
    private int f1653t;
    /* renamed from: u */
    private boolean f1654u;
    /* renamed from: v */
    private boolean f1655v;
    /* renamed from: w */
    private boolean f1656w;
    /* renamed from: x */
    private boolean f1657x;
    /* renamed from: y */
    private MyTrafficStyle f1658y;
    /* renamed from: z */
    private float f1659z;

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$11 */
    class C076711 implements Runnable {
        C076711() {
        }

        public void run() {
            AMapDelegateImp.this.f1553U.setVisibility(8);
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$12 */
    class C076812 implements Runnable {
        C076812() {
        }

        public void run() {
            try {
                AMapDelegateImp.this.f1553U.mo9598a(AMapDelegateImp.this.f1563aD.floor_names);
                AMapDelegateImp.this.f1553U.mo9596a(AMapDelegateImp.this.f1563aD.activeFloorName);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$13 */
    class C076913 implements Runnable {
        C076913() {
        }

        public void run() {
            AMapDelegateImp.this.f1539G.setIndoorBuildingToBeActive(AMapDelegateImp.this.f1563aD.activeFloorName, AMapDelegateImp.this.f1563aD.activeFloorIndex, AMapDelegateImp.this.f1563aD.poiid);
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$14 */
    class C077014 implements Runnable {
        C077014() {
        }

        public void run() {
            AMapDelegateImp.this.m2085l();
            AMapDelegateImp.this.m2096p();
            if (AMapDelegateImp.this.f1541I != null) {
                AMapDelegateImp.this.f1541I.onResume(AMapDelegateImp.this.f1539G);
                AMapDelegateImp.this.setRunLowFrame(false);
            }
            if (AMapDelegateImp.this.f1640g != null) {
                AMapDelegateImp.this.f1640g.mo8751d();
            }
            if (AMapDelegateImp.this.f1603as != null) {
                AMapDelegateImp.this.f1603as.mo8590a();
            }
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$15 */
    class C077115 implements Runnable {
        C077115() {
        }

        public void run() {
            if (AMapDelegateImp.this.f1567aI) {
                AMapDelegateImp.this.f1535C = C0874c.DAY;
                AMapDelegateImp.this.f1536D = C0872a.NORAML;
                AMapDelegateImp.this.f1537E = C0873b.NORMAL;
                try {
                    AMapDelegateImp.this.f1539G.destroy();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                VMapDataCache.getInstance().reset();
                AMapDelegateImp.this.f1567aI = false;
            }
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$16 */
    class C077216 implements Runnable {
        C077216() {
        }

        public void run() {
            try {
                AMapDelegateImp.this.f1539G.destroy();
                AMapDelegateImp.this.f1539G = null;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$17 */
    class C077317 implements Runnable {
        C077317() {
        }

        public void run() {
            AMapDelegateImp.this.f1551S.mo8676b();
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$18 */
    class C077418 implements Runnable {
        C077418() {
        }

        public void run() {
            if (!AMapDelegateImp.this.f1582aX) {
                try {
                    AMapDelegateImp.this.setMapType(AMapDelegateImp.this.f1538F);
                    if (AMapDelegateImp.this.f1563aD != null) {
                        AMapDelegateImp.this.setIndoorBuildingInfo(AMapDelegateImp.this.f1563aD);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                AMapDelegateImp.this.f1552T.mo9523a(false);
            }
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$7 */
    class C07837 implements Runnable {
        C07837() {
        }

        public void run() {
            try {
                AMapDelegateImp.this.showInfoWindow(AMapDelegateImp.this.f1595ak);
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "showInfoWindow postDelayed");
                th.printStackTrace();
            }
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$9 */
    class C07859 implements InfoWindowAdapter {
        C07859() {
        }

        public View getInfoWindow(Marker marker) {
            return null;
        }

        public View getInfoContents(Marker marker) {
            return null;
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$a */
    private static abstract class C0786a implements Runnable {
        /* renamed from: b */
        boolean f1489b;
        /* renamed from: c */
        boolean f1490c;
        /* renamed from: d */
        C0872a f1491d;
        /* renamed from: e */
        C0874c f1492e;
        /* renamed from: f */
        C0873b f1493f;

        private C0786a() {
            this.f1489b = false;
            this.f1490c = false;
        }

        /* synthetic */ C0786a(C07761 c07761) {
            this();
        }

        public void run() {
            this.f1489b = false;
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$b */
    private class C0787b implements C0754a {
        /* renamed from: a */
        Float f1494a;
        /* renamed from: b */
        Float f1495b;
        /* renamed from: c */
        IPoint f1496c;
        /* renamed from: d */
        float f1497d;
        /* renamed from: e */
        CameraUpdateFactoryDelegate f1498e;
        /* renamed from: g */
        private float f1500g;
        /* renamed from: h */
        private float f1501h;
        /* renamed from: i */
        private float f1502i;
        /* renamed from: j */
        private float f1503j;
        /* renamed from: k */
        private float f1504k;

        private C0787b() {
            this.f1494a = null;
            this.f1495b = null;
            this.f1496c = new IPoint();
            this.f1497d = 0.0f;
            this.f1498e = CameraUpdateFactoryDelegate.newInstance();
        }

        /* synthetic */ C0787b(AMapDelegateImp aMapDelegateImp, C07761 c07761) {
            this();
        }

        /* renamed from: a */
        public void mo8797a(float f, float f2, float f3, float f4, float f5) {
            this.f1500g = f2;
            this.f1502i = f3;
            this.f1501h = f4;
            this.f1503j = f5;
            this.f1504k = (this.f1503j - this.f1502i) / (this.f1501h - this.f1500g);
            this.f1494a = null;
            this.f1495b = null;
            if (AMapDelegateImp.this.f1629br) {
                this.f1498e.nowType = Type.changeGeoCenterZoomTiltBearing;
                AMapDelegateImp.this.getPixel2Geo(AMapDelegateImp.this.f1630bs, AMapDelegateImp.this.f1631bt, this.f1496c);
                this.f1498e.geoPoint = this.f1496c;
                this.f1498e.isUseAnchor = AMapDelegateImp.this.f1629br;
            } else {
                this.f1498e.nowType = Type.changeTilt;
            }
            this.f1498e.zoom = AMapDelegateImp.this.f1542J.getMapZoomer();
            this.f1498e.bearing = AMapDelegateImp.this.f1542J.getMapAngle();
        }

        /* renamed from: a */
        public boolean mo8798a(MotionEvent motionEvent, float f, float f2, float f3, float f4) {
            try {
                if (!AMapDelegateImp.this.f1598an.isTiltGesturesEnabled()) {
                    return true;
                }
                if (AMapDelegateImp.this.f1617bf || AMapDelegateImp.this.f1622bk) {
                    return true;
                }
                if (this.f1495b == null) {
                    this.f1495b = Float.valueOf(f4);
                }
                if (this.f1494a == null) {
                    this.f1494a = Float.valueOf(f2);
                }
                float f5 = this.f1502i - f2;
                float f6 = this.f1503j - f4;
                float f7 = this.f1500g - f;
                float f8 = this.f1501h - f3;
                if (((double) Math.abs(this.f1504k - ((f4 - f2) / (f3 - f)))) >= 0.2d || (((f5 <= 0.0f || f6 <= 0.0f) && (f5 >= 0.0f || f6 >= 0.0f)) || ((f7 < 0.0f || f8 < 0.0f) && (f7 > 0.0f || f8 > 0.0f)))) {
                    return false;
                }
                f6 = (this.f1494a.floatValue() - f2) / 4.0f;
                AMapDelegateImp.this.f1616be = true;
                f5 = AMapDelegateImp.this.f1542J.getCameraHeaderAngle();
                if (f5 > 45.0f) {
                    f5 = 45.0f;
                }
                this.f1497d = f5 - f6;
                this.f1498e.tilt = this.f1497d;
                AMapDelegateImp.this.f1638e.mo8487a(this.f1498e);
                this.f1494a = Float.valueOf(f2);
                this.f1495b = Float.valueOf(f4);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return true;
            }
        }

        /* renamed from: a */
        public void mo8796a() {
            if (!AMapDelegateImp.this.f1617bf) {
                try {
                    if (!AMapDelegateImp.this.f1598an.isZoomGesturesEnabled()) {
                        return;
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    AMapDelegateImp.this.animateCamera(CameraUpdateFactoryDelegate.zoomOut());
                } catch (RemoteException e2) {
                    SDKLogHandler.m2563a(e2, "AMapDelegateImpGLSurfaceView", "onMultiTouchSingleTap");
                    e2.printStackTrace();
                }
            }
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$c */
    private class C0791c implements OnDoubleTapListener {
        private C0791c() {
        }

        /* synthetic */ C0791c(AMapDelegateImp aMapDelegateImp, C07761 c07761) {
            this();
        }

        /* JADX WARNING: Missing block: B:3:0x000b, code skipped:
            if (com.amap.api.mapcore.util.AMapDelegateImp.m2108w(r5.f1511a).isZoomGesturesEnabled() == false) goto L_0x000d;
     */
        public boolean onDoubleTap(android.view.MotionEvent r6) {
            /*
            r5 = this;
            r4 = 1;
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;	 Catch:{ RemoteException -> 0x000e }
            r0 = r0.f1598an;	 Catch:{ RemoteException -> 0x000e }
            r0 = r0.isZoomGesturesEnabled();	 Catch:{ RemoteException -> 0x000e }
            if (r0 != 0) goto L_0x0012;
        L_0x000d:
            return r4;
        L_0x000e:
            r0 = move-exception;
            r0.printStackTrace();
        L_0x0012:
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r0 = r0.f1624bm;
            if (r0 > r4) goto L_0x000d;
        L_0x001a:
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r0.f1623bl = r4;
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r0 = r0.f1542J;
            r0 = r0.getMapZoomer();
            r1 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r1 = r1.getMaxZoomLevel();
            r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
            if (r0 == 0) goto L_0x000d;
        L_0x0033:
            r0 = r6.getX();
            r1 = r6.getY();
            r0 = (int) r0;
            r1 = (int) r1;
            r2 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
            r3 = new android.graphics.Point;
            r3.<init>(r0, r1);
            r0 = com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate.zoomBy(r2, r3);
            r1 = com.amap.api.mapcore.util.AMapDelegateImp.this;	 Catch:{ RemoteException -> 0x004e }
            r1.animateCamera(r0);	 Catch:{ RemoteException -> 0x004e }
            goto L_0x000d;
        L_0x004e:
            r0 = move-exception;
            r1 = "AMapDelegateImpGLSurfaceView";
            r2 = "onDoubleTap";
            com.amap.api.mapcore.util.SDKLogHandler.m2563a(r0, r1, r2);
            r0.printStackTrace();
            goto L_0x000d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.AMapDelegateImp$C0791c.onDoubleTap(android.view.MotionEvent):boolean");
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return false;
        }

        public boolean onSingleTapConfirmed(final MotionEvent motionEvent) {
            AMapDelegateImp.this.f1621bj = false;
            if (AMapDelegateImp.this.f1625bn) {
                AMapDelegateImp.this.f1625bn = false;
            } else {
                if (AMapDelegateImp.this.f1594aj != null) {
                    if (AMapDelegateImp.this.f1637d.mo8500a(new Rect(AMapDelegateImp.this.f1594aj.getLeft(), AMapDelegateImp.this.f1594aj.getTop(), AMapDelegateImp.this.f1594aj.getRight(), AMapDelegateImp.this.f1594aj.getBottom()), (int) motionEvent.getX(), (int) motionEvent.getY())) {
                        if (AMapDelegateImp.this.f1590af != null) {
                            IMarkerDelegate e = AMapDelegateImp.this.f1637d.mo8510e();
                            if (e.isVisible()) {
                                AMapDelegateImp.this.f1590af.onInfoWindowClick(new Marker(e));
                            }
                        }
                    }
                }
                try {
                    if (AMapDelegateImp.this.f1637d.mo8505b(motionEvent)) {
                        final IMarkerDelegate e2 = AMapDelegateImp.this.f1637d.mo8510e();
                        if (e2 != null && e2.isVisible()) {
                            Marker marker = new Marker(e2);
                            if (AMapDelegateImp.this.f1555W != null) {
                                if (AMapDelegateImp.this.f1555W.onMarkerClick(marker) || AMapDelegateImp.this.f1637d.mo8502b() <= 0) {
                                    AMapDelegateImp.this.f1637d.mo8508d(e2);
                                } else {
                                    AMapDelegateImp.this.f1562aC.postDelayed(new Runnable() {
                                        public void run() {
                                            try {
                                                AMapDelegateImp.this.showInfoWindow(e2);
                                            } catch (Throwable th) {
                                                SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "onSingleTapUp showInfoWindow");
                                                th.printStackTrace();
                                            }
                                        }
                                    }, 20);
                                    if (!e2.isViewMode()) {
                                        LatLng realPosition = e2.getRealPosition();
                                        if (realPosition != null) {
                                            IPoint iPoint = new IPoint();
                                            AMapDelegateImp.this.latlon2Geo(realPosition.latitude, realPosition.longitude, iPoint);
                                            AMapDelegateImp.this.moveCamera(CameraUpdateFactoryDelegate.changeGeoCenter(iPoint));
                                        }
                                    }
                                }
                            }
                            AMapDelegateImp.this.f1637d.mo8508d(e2);
                        }
                    } else {
                        DPoint dPoint;
                        if (AMapDelegateImp.this.f1586ab != null) {
                            dPoint = new DPoint();
                            AMapDelegateImp.this.getPixel2LatLng((int) motionEvent.getX(), (int) motionEvent.getY(), dPoint);
                            AMapDelegateImp.this.f1586ab.onMapClick(new LatLng(dPoint.f4559y, dPoint.f4558x));
                        }
                        if (AMapDelegateImp.this.f1556X != null) {
                            dPoint = new DPoint();
                            AMapDelegateImp.this.getPixel2LatLng((int) motionEvent.getX(), (int) motionEvent.getY(), dPoint);
                            LatLng latLng = new LatLng(dPoint.f4559y, dPoint.f4558x);
                            if (latLng != null) {
                                IOverlayDelegate a = AMapDelegateImp.this.f1641h.mo9559a(latLng);
                                if (a != null) {
                                    AMapDelegateImp.this.f1556X.onPolylineClick(new Polyline((IPolylineDelegate) a));
                                }
                            }
                        }
                        AMapDelegateImp.this.queueEvent(new Runnable() {
                            public void run() {
                                final Poi a = AMapDelegateImp.this.m2041a((int) motionEvent.getX(), (int) motionEvent.getY(), 25);
                                if (AMapDelegateImp.this.f1588ad != null && a != null) {
                                    AMapDelegateImp.this.f1645l.post(new Runnable() {
                                        public void run() {
                                            AMapDelegateImp.this.f1588ad.onPOIClick(a);
                                        }
                                    });
                                }
                            }
                        });
                    }
                } catch (RemoteException e3) {
                    SDKLogHandler.m2563a(e3, "AMapDelegateImpGLSurfaceView", "onSingleTapUp moveCamera");
                    e3.printStackTrace();
                } catch (Throwable e32) {
                    SDKLogHandler.m2563a(e32, "AMapDelegateImpGLSurfaceView", "onSingleTapUp");
                    e32.printStackTrace();
                }
            }
            return true;
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$d */
    private class C0792d implements OnGestureListener {
        /* renamed from: a */
        FPoint f1512a;
        /* renamed from: b */
        IPoint f1513b;
        /* renamed from: c */
        IPoint f1514c;
        /* renamed from: d */
        CameraUpdateFactoryDelegate f1515d;

        private C0792d() {
            this.f1512a = new FPoint();
            this.f1513b = new IPoint();
            this.f1514c = new IPoint();
            this.f1515d = CameraUpdateFactoryDelegate.changeGeoCenter(this.f1514c);
        }

        /* synthetic */ C0792d(AMapDelegateImp aMapDelegateImp, C07761 c07761) {
            this();
        }

        public boolean onDown(MotionEvent motionEvent) {
            AMapDelegateImp.this.f1621bj = false;
            if (!AMapDelegateImp.this.f1623bl) {
                try {
                    AMapDelegateImp.this.stopAnimation();
                } catch (RemoteException e) {
                    SDKLogHandler.m2563a(e, "AMapDelegateImpGLSurfaceView", "onDown");
                    e.printStackTrace();
                }
            }
            AMapDelegateImp.this.f1623bl = false;
            AMapDelegateImp.this.f1624bm = 0;
            this.f1512a.f4560x = motionEvent.getX();
            this.f1512a.f4561y = motionEvent.getY();
            AMapDelegateImp.this.getPixel2Geo((int) this.f1512a.f4560x, (int) this.f1512a.f4561y, this.f1513b);
            return true;
        }

        /* JADX WARNING: Missing block: B:3:0x0011, code skipped:
            if (com.amap.api.mapcore.util.AMapDelegateImp.m2108w(r10.f1516e).isScrollGesturesEnabled() == false) goto L_0x0013;
     */
        public boolean onFling(android.view.MotionEvent r11, android.view.MotionEvent r12, float r13, float r14) {
            /*
            r10 = this;
            r4 = 0;
            r9 = 1;
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r0.f1621bj = r4;
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;	 Catch:{ RemoteException -> 0x0014 }
            r0 = r0.f1598an;	 Catch:{ RemoteException -> 0x0014 }
            r0 = r0.isScrollGesturesEnabled();	 Catch:{ RemoteException -> 0x0014 }
            if (r0 != 0) goto L_0x001f;
        L_0x0013:
            return r9;
        L_0x0014:
            r0 = move-exception;
            r1 = "AMapDelegateImpGLSurfaceView";
            r2 = "onFling";
            com.amap.api.mapcore.util.SDKLogHandler.m2563a(r0, r1, r2);
            r0.printStackTrace();
        L_0x001f:
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r0 = r0.f1602ar;
            r0 = r0.mo8799a();
            if (r0 != 0) goto L_0x0013;
        L_0x002b:
            r0 = r11.getEventTime();
            r2 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r2 = r2.f1602ar;
            r2 = r2.mo8801b();
            r0 = r0 - r2;
            r2 = 30;
            r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r0 < 0) goto L_0x0013;
        L_0x0040:
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r0 = r0.getMapWidth();
            r1 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r1 = r1.getMapHeight();
            r6 = r0 * 2;
            r8 = r1 * 2;
            r2 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r0 = r0 / 2;
            r2.f1605au = r0;
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r1 = r1 / 2;
            r0.f1606av = r1;
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r1 = 0;
            r0.f1607aw = r1;
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r0 = r0.f1594aj;
            if (r0 == 0) goto L_0x0096;
        L_0x006c:
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r0 = r0.f1595ak;
            if (r0 == 0) goto L_0x0096;
        L_0x0074:
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r0 = r0.f1595ak;
            r0 = r0.isViewMode();
            if (r0 != 0) goto L_0x0096;
        L_0x0080:
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r0.f1577aS = r4;
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r0 = r0.f1596al;
            if (r0 == 0) goto L_0x0096;
        L_0x008d:
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r0 = r0.f1596al;
            r0.setVisible(r9);
        L_0x0096:
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r0 = r0.f1604at;
            r1 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r1 = r1.f1605au;
            r2 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r2 = r2.f1606av;
            r3 = -r13;
            r3 = (int) r3;
            r3 = r3 * 3;
            r3 = r3 / 5;
            r4 = -r14;
            r4 = (int) r4;
            r4 = r4 * 3;
            r4 = r4 / 5;
            r5 = -r6;
            r7 = -r8;
            r0.fling(r1, r2, r3, r4, r5, r6, r7, r8);
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r0 = r0.f1640g;
            if (r0 == 0) goto L_0x0013;
        L_0x00bf:
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;
            r0 = r0.f1640g;
            r0.mo8748b(r9);
            goto L_0x0013;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.AMapDelegateImp$C0792d.onFling(android.view.MotionEvent, android.view.MotionEvent, float, float):boolean");
        }

        public void onLongPress(MotionEvent motionEvent) {
            AMapDelegateImp.this.f1621bj = false;
            AMapDelegateImp.this.f1620bi = AMapDelegateImp.this.f1637d.mo8494a(motionEvent);
            if (AMapDelegateImp.this.f1557Y != null && AMapDelegateImp.this.f1620bi != null && AMapDelegateImp.this.f1620bi.isDraggable()) {
                AMapDelegateImp.this.f1619bh = new Marker(AMapDelegateImp.this.f1620bi);
                LatLng position = AMapDelegateImp.this.f1619bh.getPosition();
                LatLng realPosition = AMapDelegateImp.this.f1620bi.getRealPosition();
                IPoint iPoint = new IPoint();
                AMapDelegateImp.this.getLatLng2Pixel(realPosition.latitude, realPosition.longitude, iPoint);
                iPoint.f4563y -= 60;
                DPoint dPoint = new DPoint();
                AMapDelegateImp.this.getPixel2LatLng(iPoint.f4562x, iPoint.f4563y, dPoint);
                AMapDelegateImp.this.f1619bh.setPosition(new LatLng((position.latitude + dPoint.f4559y) - realPosition.latitude, (dPoint.f4558x + position.longitude) - realPosition.longitude));
                AMapDelegateImp.this.f1637d.mo8508d(AMapDelegateImp.this.f1620bi);
                AMapDelegateImp.this.f1557Y.onMarkerDragStart(AMapDelegateImp.this.f1619bh);
                AMapDelegateImp.this.f1618bg = true;
            } else if (AMapDelegateImp.this.f1589ae != null) {
                DPoint dPoint2 = new DPoint();
                AMapDelegateImp.this.getPixel2LatLng((int) motionEvent.getX(), (int) motionEvent.getY(), dPoint2);
                AMapDelegateImp.this.f1589ae.onMapLongClick(new LatLng(dPoint2.f4559y, dPoint2.f4558x));
                AMapDelegateImp.this.f1625bn = true;
            }
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            AMapDelegateImp.this.f1621bj = true;
            if ((!AMapDelegateImp.this.f1604at.isFinished() && AMapDelegateImp.this.f1604at.getMode() == 1) || AMapDelegateImp.this.f1602ar.mo8799a() || motionEvent2.getEventTime() - AMapDelegateImp.this.f1602ar.mo8801b() < 30) {
                AMapDelegateImp.this.f1621bj = false;
            } else if (motionEvent2.getPointerCount() >= 2) {
                AMapDelegateImp.this.f1621bj = false;
            } else {
                try {
                    if (!AMapDelegateImp.this.f1598an.isScrollGesturesEnabled()) {
                        AMapDelegateImp.this.f1621bj = false;
                    } else if (AMapDelegateImp.this.f1624bm > 1) {
                        AMapDelegateImp.this.f1621bj = false;
                    } else {
                        if (!(AMapDelegateImp.this.f1594aj == null || AMapDelegateImp.this.f1595ak == null || AMapDelegateImp.this.f1595ak.isViewMode() || AMapDelegateImp.this.f1596al == null)) {
                            AMapDelegateImp.this.f1596al.setVisible(true);
                        }
                        IPoint iPoint = new IPoint();
                        AMapDelegateImp.this.getPixel2Geo((int) motionEvent2.getX(), (int) motionEvent2.getY(), iPoint);
                        int i = this.f1513b.f4562x - iPoint.f4562x;
                        int i2 = this.f1513b.f4563y - iPoint.f4563y;
                        IPoint iPoint2 = new IPoint();
                        AMapDelegateImp.this.f1542J.getGeoCenter(iPoint2);
                        this.f1514c.f4562x = i + iPoint2.f4562x;
                        this.f1514c.f4563y = i2 + iPoint2.f4563y;
                        this.f1515d.geoPoint = this.f1514c;
                        AMapDelegateImp.this.f1638e.mo8487a(this.f1515d);
                    }
                } catch (Throwable th) {
                    SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "onScroll");
                    th.printStackTrace();
                }
            }
            return true;
        }

        public void onShowPress(MotionEvent motionEvent) {
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$e */
    private class C0794e implements C0793a {
        private C0794e() {
        }

        /* synthetic */ C0794e(AMapDelegateImp aMapDelegateImp, C07761 c07761) {
            this();
        }

        /* renamed from: a */
        public void mo9022a(int i) {
            if (AMapDelegateImp.this.f1563aD != null) {
                AMapDelegateImp.this.f1563aD.activeFloorIndex = AMapDelegateImp.this.f1563aD.floor_indexs[i];
                AMapDelegateImp.this.f1563aD.activeFloorName = AMapDelegateImp.this.f1563aD.floor_names[i];
                try {
                    AMapDelegateImp.this.setIndoorBuildingInfo(AMapDelegateImp.this.f1563aD);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$f */
    private class C0795f implements C0755a {
        /* renamed from: a */
        float f1518a;
        /* renamed from: b */
        float f1519b;
        /* renamed from: c */
        IPoint f1520c;
        /* renamed from: d */
        CameraUpdateFactoryDelegate f1521d;

        private C0795f() {
            this.f1518a = 0.0f;
            this.f1519b = 0.0f;
            this.f1520c = new IPoint();
            this.f1521d = CameraUpdateFactoryDelegate.newInstance();
        }

        /* synthetic */ C0795f(AMapDelegateImp aMapDelegateImp, C07761 c07761) {
            this();
        }

        /* renamed from: a */
        public boolean mo8802a(RotateGestureDetector rotateGestureDetector) {
            if (AMapDelegateImp.this.f1616be) {
                return false;
            }
            float b = rotateGestureDetector.mo8807b();
            this.f1518a += b;
            if (!AMapDelegateImp.this.f1622bk && Math.abs(this.f1518a) <= 30.0f && Math.abs(this.f1518a) <= 350.0f) {
                return true;
            }
            AMapDelegateImp.this.f1622bk = true;
            this.f1519b = b + AMapDelegateImp.this.f1542J.getMapAngle();
            this.f1521d.bearing = this.f1519b;
            AMapDelegateImp.this.f1638e.mo8487a(this.f1521d);
            this.f1518a = 0.0f;
            return true;
        }

        /* renamed from: b */
        public boolean mo8803b(RotateGestureDetector rotateGestureDetector) {
            try {
                if (!AMapDelegateImp.this.f1598an.isRotateGesturesEnabled()) {
                    return false;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (AMapDelegateImp.this.f1629br) {
                this.f1521d.isUseAnchor = AMapDelegateImp.this.f1629br;
                this.f1521d.nowType = Type.changeBearingGeoCenter;
                AMapDelegateImp.this.getPixel2Geo(AMapDelegateImp.this.f1630bs, AMapDelegateImp.this.f1631bt, this.f1520c);
                this.f1521d.geoPoint = this.f1520c;
            } else {
                this.f1521d.nowType = Type.changeBearing;
            }
            AMapDelegateImp.this.f1622bk = false;
            this.f1518a = 0.0f;
            AMapDelegateImp.this.f1624bm = 2;
            if (AMapDelegateImp.this.f1616be || ((float) AMapDelegateImp.this.mo9173c()) / 8.0f >= rotateGestureDetector.mo8805c()) {
                return false;
            }
            return true;
        }

        /* renamed from: c */
        public void mo8804c(RotateGestureDetector rotateGestureDetector) {
            this.f1518a = 0.0f;
            if (AMapDelegateImp.this.f1622bk) {
                AMapDelegateImp.this.f1622bk = false;
                CameraUpdateFactoryDelegate newInstance = CameraUpdateFactoryDelegate.newInstance();
                newInstance.isChangeFinished = true;
                AMapDelegateImp.this.f1638e.mo8487a(newInstance);
            }
            AMapDelegateImp.this.m2104t();
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$g */
    private class C0796g implements OnScaleGestureListener {
        /* renamed from: a */
        CameraUpdateFactoryDelegate f1523a;
        /* renamed from: c */
        private float f1525c;
        /* renamed from: d */
        private IPoint f1526d;

        private C0796g() {
            this.f1525c = 0.0f;
            this.f1526d = new IPoint();
            this.f1523a = CameraUpdateFactoryDelegate.newInstance();
        }

        /* synthetic */ C0796g(AMapDelegateImp aMapDelegateImp, C07761 c07761) {
            this();
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            if (!AMapDelegateImp.this.f1616be) {
                float scaleFactor = scaleGestureDetector.getScaleFactor();
                if (AMapDelegateImp.this.f1617bf || ((double) scaleFactor) > 1.08d || ((double) scaleFactor) < 0.92d) {
                    AMapDelegateImp.this.f1617bf = true;
                    scaleFactor = (float) (Math.log((double) scaleFactor) / AMapDelegateImp.f1532aG);
                    this.f1523a.zoom = Util.m2337a(scaleFactor + this.f1525c);
                    AMapDelegateImp.this.f1638e.mo8487a(this.f1523a);
                }
            }
            return false;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            try {
                if (!AMapDelegateImp.this.f1598an.isZoomGesturesEnabled() || AMapDelegateImp.this.f1624bm < 2) {
                    return false;
                }
            } catch (RemoteException e) {
                SDKLogHandler.m2563a(e, "AMapDelegateImpGLSurfaceView", "onScaleBegin");
                e.printStackTrace();
            }
            AMapDelegateImp.this.f1624bm = 2;
            if (AMapDelegateImp.this.f1616be) {
                return false;
            }
            if (AMapDelegateImp.this.f1629br) {
                this.f1523a.isUseAnchor = AMapDelegateImp.this.f1629br;
                this.f1523a.nowType = Type.changeGeoCenterZoom;
                AMapDelegateImp.this.getPixel2Geo(AMapDelegateImp.this.f1630bs, AMapDelegateImp.this.f1631bt, this.f1526d);
                this.f1523a.geoPoint = this.f1526d;
            } else {
                this.f1523a.nowType = Type.zoomTo;
            }
            AMapDelegateImp.this.f1617bf = false;
            this.f1525c = AMapDelegateImp.this.f1542J.getMapZoomer();
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            this.f1525c = 0.0f;
            if (AMapDelegateImp.this.f1617bf) {
                AMapDelegateImp.this.f1617bf = false;
                CameraUpdateFactoryDelegate newInstance = CameraUpdateFactoryDelegate.newInstance();
                newInstance.isChangeFinished = true;
                AMapDelegateImp.this.f1638e.mo8487a(newInstance);
            }
            AMapDelegateImp.this.m2104t();
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$h */
    private class C0797h extends TimerTask {
        /* renamed from: a */
        AMapDelegateImp f1527a;

        public C0797h(AMapDelegateImp aMapDelegateImp) {
            this.f1527a = aMapDelegateImp;
        }

        public void run() {
            if (!AMapDelegateImp.this.f1584aZ || AMapDelegateImp.this.f1612ba || !AMapDelegateImp.this.f1641h.mo9568d()) {
                AMapDelegateImp.this.f1643j.requestRender();
            } else if (!AMapDelegateImp.this.f1637d.mo8509d()) {
                AMapDelegateImp.this.f1643j.requestRender();
            }
        }
    }

    /* compiled from: AMapDelegateImp */
    /* renamed from: com.amap.api.mapcore.util.c$i */
    private class C0798i implements Runnable {
        /* renamed from: b */
        private Context f1530b;
        /* renamed from: c */
        private OnCacheRemoveListener f1531c;

        public C0798i(Context context, OnCacheRemoveListener onCacheRemoveListener) {
            this.f1530b = context;
            this.f1531c = onCacheRemoveListener;
        }

        /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
        /* JADX WARNING: Removed duplicated region for block: B:13:0x0041 A:{Catch:{ Throwable -> 0x004b }} */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x0090 A:{Catch:{ Throwable -> 0x0096 }} */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x0090 A:{Catch:{ Throwable -> 0x0096 }} */
        public void run() {
            /*
            r9 = this;
            r0 = 1;
            r1 = 0;
            r2 = r9.f1530b;	 Catch:{ Throwable -> 0x0050, all -> 0x007a }
            r2 = r2.getApplicationContext();	 Catch:{ Throwable -> 0x0050, all -> 0x007a }
            r3 = com.amap.api.mapcore.util.Util.m2369b(r2);	 Catch:{ Throwable -> 0x0050, all -> 0x007a }
            r4 = com.amap.api.mapcore.util.Util.m2350a(r2);	 Catch:{ Throwable -> 0x0050, all -> 0x007a }
            r2 = com.amap.api.mapcore.util.AMapDelegateImp.this;	 Catch:{ Throwable -> 0x0050, all -> 0x007a }
            r5 = new java.io.File;	 Catch:{ Throwable -> 0x0050, all -> 0x007a }
            r5.<init>(r3);	 Catch:{ Throwable -> 0x0050, all -> 0x007a }
            r2 = r2.m2051a(r5);	 Catch:{ Throwable -> 0x0050, all -> 0x007a }
            if (r2 == 0) goto L_0x0047;
        L_0x001d:
            r2 = r0;
        L_0x001e:
            if (r2 == 0) goto L_0x0049;
        L_0x0020:
            r3 = com.amap.api.mapcore.util.AMapDelegateImp.this;	 Catch:{ Throwable -> 0x00a3, all -> 0x009b }
            r5 = new java.io.File;	 Catch:{ Throwable -> 0x00a3, all -> 0x009b }
            r5.<init>(r4);	 Catch:{ Throwable -> 0x00a3, all -> 0x009b }
            r2 = r3.m2051a(r5);	 Catch:{ Throwable -> 0x00a3, all -> 0x009b }
            if (r2 == 0) goto L_0x0049;
        L_0x002d:
            r6 = r0;
        L_0x002e:
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;	 Catch:{ Throwable -> 0x004b }
            r0 = r0.f1539G;	 Catch:{ Throwable -> 0x004b }
            r1 = 2601; // 0xa29 float:3.645E-42 double:1.285E-320;
            r2 = 1;
            r3 = 0;
            r4 = 0;
            r5 = 0;
            r0.setParameter(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x004b }
            r0 = r9.f1531c;	 Catch:{ Throwable -> 0x004b }
            if (r0 == 0) goto L_0x0046;
        L_0x0041:
            r0 = r9.f1531c;	 Catch:{ Throwable -> 0x004b }
            r0.onRemoveCacheFinish(r6);	 Catch:{ Throwable -> 0x004b }
        L_0x0046:
            return;
        L_0x0047:
            r2 = r1;
            goto L_0x001e;
        L_0x0049:
            r6 = r1;
            goto L_0x002e;
        L_0x004b:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x0046;
        L_0x0050:
            r1 = move-exception;
            r8 = r1;
            r1 = r0;
            r0 = r8;
        L_0x0054:
            r2 = "AMapDelegateImpGLSurfaceView";
            r3 = "RemoveCacheRunnable";
            com.amap.api.mapcore.util.SDKLogHandler.m2563a(r0, r2, r3);	 Catch:{ all -> 0x009f }
            r6 = 0;
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;	 Catch:{ Throwable -> 0x0075 }
            r0 = r0.f1539G;	 Catch:{ Throwable -> 0x0075 }
            r1 = 2601; // 0xa29 float:3.645E-42 double:1.285E-320;
            r2 = 1;
            r3 = 0;
            r4 = 0;
            r5 = 0;
            r0.setParameter(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0075 }
            r0 = r9.f1531c;	 Catch:{ Throwable -> 0x0075 }
            if (r0 == 0) goto L_0x0046;
        L_0x006f:
            r0 = r9.f1531c;	 Catch:{ Throwable -> 0x0075 }
            r0.onRemoveCacheFinish(r6);	 Catch:{ Throwable -> 0x0075 }
            goto L_0x0046;
        L_0x0075:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x0046;
        L_0x007a:
            r1 = move-exception;
            r6 = r1;
            r7 = r0;
        L_0x007d:
            r0 = com.amap.api.mapcore.util.AMapDelegateImp.this;	 Catch:{ Throwable -> 0x0096 }
            r0 = r0.f1539G;	 Catch:{ Throwable -> 0x0096 }
            r1 = 2601; // 0xa29 float:3.645E-42 double:1.285E-320;
            r2 = 1;
            r3 = 0;
            r4 = 0;
            r5 = 0;
            r0.setParameter(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0096 }
            r0 = r9.f1531c;	 Catch:{ Throwable -> 0x0096 }
            if (r0 == 0) goto L_0x0095;
        L_0x0090:
            r0 = r9.f1531c;	 Catch:{ Throwable -> 0x0096 }
            r0.onRemoveCacheFinish(r7);	 Catch:{ Throwable -> 0x0096 }
        L_0x0095:
            throw r6;
        L_0x0096:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x0095;
        L_0x009b:
            r0 = move-exception;
            r6 = r0;
            r7 = r2;
            goto L_0x007d;
        L_0x009f:
            r0 = move-exception;
            r6 = r0;
            r7 = r1;
            goto L_0x007d;
        L_0x00a3:
            r0 = move-exception;
            r1 = r2;
            goto L_0x0054;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.AMapDelegateImp$C0798i.run():void");
        }

        public boolean equals(Object obj) {
            return obj instanceof C0798i;
        }
    }

    public MapCore getMapCore() {
        return this.f1539G;
    }

    public int getLineTextureID() {
        return this.f1647n;
    }

    public MapProjection getMapProjection() {
        if (this.f1542J == null) {
            this.f1542J = this.f1539G.getMapstate();
        }
        return this.f1542J;
    }

    /* renamed from: a */
    public void mo9169a(GL10 gl10) {
        int i = 0;
        if (!this.f1578aT) {
            int[] iArr = new int[VTMCDataCache.MAXSIZE];
            this.f1533A.clear();
            gl10.glGenTextures(VTMCDataCache.MAXSIZE, iArr, 0);
            while (i < iArr.length) {
                this.f1533A.add(Integer.valueOf(iArr[i]));
                i++;
            }
            this.f1578aT = true;
        }
    }

    public AMapDelegateImp(IGLSurfaceView iGLSurfaceView, Context context) {
        this(iGLSurfaceView, context, null);
        this.f1540H = context;
    }

    private AMapDelegateImp(IGLSurfaceView iGLSurfaceView, Context context, AttributeSet attributeSet) {
        this.f1647n = -1;
        this.f1648o = -1;
        this.f1649p = 40;
        this.f1650q = null;
        this.f1651r = null;
        this.f1652s = 221010267;
        this.f1653t = 101697799;
        this.f1559a = 10.0f;
        this.f1611b = 0.0f;
        this.f1636c = 0.0f;
        this.f1654u = false;
        this.f1655v = true;
        this.f1656w = true;
        this.f1657x = false;
        this.f1658y = null;
        this.f1659z = 1.0f;
        this.f1533A = new CopyOnWriteArrayList();
        this.f1534B = new CopyOnWriteArrayList();
        this.f1638e = new MapMessageQueue(this);
        this.f1535C = C0874c.DAY;
        this.f1536D = C0872a.NORAML;
        this.f1537E = C0873b.NORMAL;
        this.f1538F = 1;
        this.f1541I = null;
        this.f1546N = null;
        this.f1600ap = new Rect();
        this.f1605au = 0;
        this.f1606av = 0;
        this.f1607aw = null;
        this.f1608ax = 0;
        this.f1609ay = null;
        this.f1560aA = null;
        this.f1561aB = null;
        this.f1562aC = new Handler();
        this.f1563aD = null;
        this.f1564aE = null;
        this.f1566aH = true;
        this.f1567aI = false;
        this.f1568aJ = false;
        this.f1569aK = false;
        this.f1570aL = false;
        this.f1571aM = true;
        this.f1572aN = false;
        this.f1573aO = false;
        this.f1574aP = false;
        this.f1575aQ = Boolean.valueOf(false);
        this.f1576aR = false;
        this.f1577aS = true;
        this.f1578aT = false;
        this.f1579aU = new Handler();
        this.f1641h = null;
        this.f1642i = null;
        this.f1643j = null;
        this.f1580aV = 0;
        this.f1581aW = new CustomGLOverlayLayer();
        this.f1584aZ = false;
        this.f1612ba = false;
        this.f1613bb = new Handler();
        this.f1614bc = new C0855j(this);
        this.f1615bd = false;
        this.f1616be = false;
        this.f1617bf = false;
        this.f1618bg = false;
        this.f1619bh = null;
        this.f1620bi = null;
        this.f1621bj = false;
        this.f1622bk = false;
        this.f1623bl = false;
        this.f1624bm = 0;
        this.f1625bn = false;
        this.f1626bo = new C0806d(this);
        this.f1627bp = null;
        this.f1645l = new C0826e(this);
        this.f1628bq = false;
        this.f1629br = false;
        this.f1632bu = new C0832f(this);
        this.f1633bv = new C0850g(this);
        this.f1634bw = new C0853h(this);
        this.f1635bx = new C0854i(this);
        ConfigableConst.f2123c = AppInfo.m2384c(context);
        this.f1643j = iGLSurfaceView;
        this.f1540H = context;
        this.f1598an = new UiSettingsDelegateImp(this);
        this.f1539G = new MapCore(this.f1540H);
        this.f1541I = new AMapCallback(this);
        this.f1539G.setMapCallback(this.f1541I);
        iGLSurfaceView.setRenderer(this);
        m2085l();
        this.f1642i = new GLMapResManager(this, context);
        this.f1597am = new ProjectionDelegateImp(this);
        this.f1601aq = new AMapOnLocationChangedListener(this);
        this.f1543K = new GestureDetector(context, new C0792d(this, null));
        this.f1543K.setOnDoubleTapListener(new C0791c(this, null));
        this.f1543K.setIsLongpressEnabled(true);
        this.f1544L = new ScaleGestureDetector(context, new C0796g(this, null));
        this.f1545M = new RotateGestureDetector(context, new C0795f(this, null));
        this.f1602ar = new MultiTouchSupport(context, new C0787b(this, null));
        this.f1547O = new MapOverlayViewGroup(context, this) {
            /* Access modifiers changed, original: protected */
            /* renamed from: a */
            public void mo8521a() {
                super.mo8521a();
                AMapDelegateImp.this.f1562aC.removeCallbacks(AMapDelegateImp.this.f1634bw);
                AMapDelegateImp.this.f1562aC.post(AMapDelegateImp.this.f1633bv);
            }
        };
        this.f1641h = new GLOverlayLayer(this);
        this.f1548P = new WaterMarkerView(this.f1540H, this);
        this.f1551S = new ScaleView(this.f1540H, this);
        this.f1552T = new BlankView(this.f1540H);
        this.f1553U = new IndoorFloorSwitchView(this.f1540H);
        this.f1640g = new TileOverlayView(this.f1540H, this);
        this.f1639f = new ZoomControllerView(this.f1540H, this);
        this.f1549Q = new LocationView(this.f1540H, this.f1638e, this);
        this.f1550R = new CompassView(this.f1540H, this.f1638e, this);
        this.f1637d = new MapOverlayImageView(this.f1540H, attributeSet, this);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.f1547O.addView((View) this.f1643j, 0, layoutParams);
        this.f1547O.addView(this.f1552T, 1, layoutParams);
        this.f1547O.addView(this.f1637d, new C0736a(layoutParams));
        this.f1547O.addView(this.f1548P, layoutParams);
        this.f1547O.addView(this.f1551S, layoutParams);
        this.f1547O.addView(this.f1640g, layoutParams);
        this.f1547O.addView(this.f1553U, new LayoutParams(-2, -2));
        this.f1553U.mo9595a(new C0794e(this, null));
        this.f1547O.addView(this.f1639f, new C0736a(-2, -2, new FPoint(0.0f, 0.0f), 0, 0, 83));
        this.f1547O.addView(this.f1549Q, new C0736a(-2, -2, new FPoint(0.0f, 0.0f), 0, 0, 83));
        try {
            if (!this.f1598an.isMyLocationButtonEnabled()) {
                this.f1549Q.setVisibility(8);
            }
        } catch (RemoteException e) {
            SDKLogHandler.m2563a(e, "AMapDelegateImpGLSurfaceView", "locationView gone");
            e.printStackTrace();
        }
        this.f1547O.addView(this.f1550R, new C0736a(-2, -2, new FPoint(0.0f, 0.0f), 0, 0, 51));
        this.f1550R.setVisibility(8);
        this.f1604at = new CameraAnimator(context);
        this.f1603as = new MyLocationOverlay(this, context);
        this.f1593ai = new C07859();
        this.f1592ah = this.f1593ai;
    }

    public void addOverlay(GLOverlay gLOverlay) {
        gLOverlay.setMap(this);
        this.f1581aW.mo9543a(gLOverlay);
    }

    public void removeOverlay(GLOverlay gLOverlay) {
        this.f1581aW.mo9545b(gLOverlay);
    }

    public void clearGLOverlay() {
        this.f1581aW.mo9542a();
    }

    public void setOnMyLocationChangeListener(OnMyLocationChangeListener onMyLocationChangeListener) {
        this.f1554V = onMyLocationChangeListener;
    }

    public void onActivityResume() {
        this.f1583aY = false;
    }

    public void onActivityPause() {
        this.f1583aY = true;
    }

    public void onResume() {
        if (this.f1580aV != 1) {
            this.f1580aV = 1;
            this.f1582aX = false;
            if (!this.f1567aI) {
                queueEvent(new C077014());
            }
            if (this.f1643j instanceof AMapGLSurfaceView) {
                ((AMapGLSurfaceView) this.f1643j).onResume();
            } else {
                ((AMapGLTextureView) this.f1643j).onResume();
            }
        }
    }

    public void onPause() {
        if (this.f1580aV == 1) {
            this.f1580aV = -1;
            this.f1582aX = true;
            this.f1570aL = false;
            if (this.f1552T != null) {
                this.f1552T.mo9523a(true);
            }
            if (this.f1541I != null) {
                this.f1541I.destoryMap(this.f1539G);
            }
            m2098q();
            IPoint iPoint = new IPoint();
            this.f1542J.recalculate();
            this.f1542J.getGeoCenter(iPoint);
            this.f1652s = iPoint.f4562x;
            this.f1653t = iPoint.f4563y;
            this.f1559a = this.f1542J.getMapZoomer();
            this.f1636c = this.f1542J.getMapAngle();
            this.f1611b = this.f1542J.getCameraHeaderAngle();
            if (this.f1643j instanceof AMapGLSurfaceView) {
                ((AMapGLSurfaceView) this.f1643j).onPause();
            } else {
                ((AMapGLTextureView) this.f1643j).onPause();
            }
            m2089m();
        }
    }

    /* renamed from: l */
    private void m2085l() {
        if (!this.f1567aI) {
            this.f1539G.newMap();
            this.f1541I.onResume(this.f1539G);
            this.f1542J = this.f1539G.getMapstate();
            this.f1542J.setGeoCenter(this.f1652s, this.f1653t);
            this.f1542J.setMapAngle(this.f1636c);
            this.f1542J.setMapZoomer(this.f1559a);
            this.f1542J.setCameraHeaderAngle(this.f1611b);
            this.f1539G.setMapstate(this.f1542J);
            this.f1567aI = true;
            m2092n();
            this.f1643j.setRenderMode(0);
        }
    }

    /* renamed from: m */
    private void m2089m() {
        queueEvent(new C077115());
    }

    /* renamed from: n */
    private void m2092n() {
        try {
            setIndoorEnabled(this.f1654u);
            set3DBuildingEnabled(this.f1655v);
            setMapTextEnable(this.f1656w);
            setTrafficEnabled(this.f1657x);
            setMyTrafficStyle(this.f1658y);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setMyLocationStyle(MyLocationStyle myLocationStyle) {
        if (this.f1603as != null) {
            this.f1603as.mo8594a(myLocationStyle);
        }
    }

    public void setMyLocationType(int i) {
        if (this.f1603as != null) {
            this.f1603as.mo8592a(i);
        }
    }

    public void setMyLocationRotateAngle(float f) throws RemoteException {
        if (this.f1603as != null) {
            this.f1603as.mo8591a(f);
        }
    }

    public void showMyLocationOverlay(Location location) throws RemoteException {
        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            try {
                if (!this.f1566aH || this.f1599ao == null) {
                    this.f1603as.mo8595b();
                    this.f1603as = null;
                    return;
                }
                if (this.f1603as == null || this.f1610az == null) {
                    if (this.f1603as == null) {
                        this.f1603as = new MyLocationOverlay(this, this.f1540H);
                    }
                    moveCamera(CameraUpdateFactoryDelegate.newLatLngZoom(latLng, this.f1542J.getMapZoomer()));
                }
                this.f1603as.mo8593a(location);
                if (!(this.f1554V == null || (this.f1610az != null && this.f1610az.getBearing() == location.getBearing() && this.f1610az.getAccuracy() == location.getAccuracy() && this.f1610az.getLatitude() == location.getLatitude() && this.f1610az.getLongitude() == location.getLongitude()))) {
                    this.f1554V.onMyLocationChange(location);
                }
                this.f1610az = new Location(location);
                setRunLowFrame(false);
            } catch (RemoteException e) {
                SDKLogHandler.m2563a(e, "AMapDelegateImpGLSurfaceView", "showMyLocationOverlay");
                e.printStackTrace();
            }
        }
    }

    public void showZoomControlsEnabled(boolean z) {
        if (this.f1639f != null) {
            this.f1639f.mo8789a(z);
        }
    }

    public void showIndoorSwitchControlsEnabled(boolean z) {
        if (this.f1553U != null && z && m2094o()) {
            this.f1553U.mo9597a(true);
        }
    }

    /* renamed from: o */
    private boolean m2094o() {
        if (!(this.f1542J.getMapZoomer() < 17.0f || this.f1563aD == null || this.f1563aD.geoCenter == null)) {
            IPoint iPoint = new IPoint();
            mo9164a(this.f1563aD.geoCenter.f4562x, this.f1563aD.geoCenter.f4563y, iPoint);
            if (this.f1600ap.contains(iPoint.f4562x, iPoint.f4563y)) {
                return true;
            }
        }
        return false;
    }

    public void destroy() {
        this.f1575aQ = Boolean.valueOf(true);
        try {
            m2098q();
            if (this.f1651r != null) {
                this.f1651r.recycle();
                this.f1651r = null;
            }
            if (this.f1650q != null) {
                this.f1650q.recycle();
                this.f1650q = null;
            }
            if (!(this.f1645l == null || this.f1644k == null)) {
                this.f1645l.removeCallbacks(this.f1644k);
                this.f1644k = null;
            }
            if (this.f1613bb != null) {
                this.f1613bb.removeCallbacks(this.f1614bc);
            }
            if (this.f1639f != null) {
                this.f1639f.mo8786a();
            }
            if (this.f1551S != null) {
                this.f1551S.mo8672a();
            }
            if (this.f1548P != null) {
                this.f1548P.mo8778a();
            }
            if (this.f1549Q != null) {
                this.f1549Q.mo8466a();
            }
            if (this.f1550R != null) {
                this.f1550R.mo9538a();
            }
            if (this.f1640g != null) {
                this.f1640g.mo8747b();
                this.f1640g.mo8752e();
            }
            if (this.f1641h != null) {
                this.f1641h.mo9560a();
            }
            if (this.f1637d != null) {
                this.f1637d.mo8512f();
            }
            if (this.f1553U != null) {
                this.f1553U.mo9599b();
            }
            if (this.f1626bo != null) {
                this.f1626bo.interrupt();
                this.f1626bo = null;
            }
            if (this.f1541I != null) {
                this.f1541I.OnMapDestory(this.f1539G);
                this.f1539G.setMapCallback(null);
                this.f1541I = null;
            }
            hiddenInfoWindowShown();
            Util.m2358a(this.f1609ay);
            if (this.f1533A != null) {
                this.f1533A.clear();
            }
            if (this.f1534B != null) {
                this.f1534B.clear();
            }
            if (this.f1539G != null) {
                queueEvent(new C077216());
                Thread.sleep(200);
            }
            if (this.f1547O != null) {
                this.f1547O.removeAllViews();
                this.f1547O = null;
            }
            this.f1599ao = null;
            this.f1586ab = null;
            this.f1658y = null;
            SDKLogHandler.m2564b();
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "destroy");
            th.printStackTrace();
        }
    }

    public void showMyLocationButtonEnabled(boolean z) {
        if (this.f1549Q != null) {
            if (z) {
                this.f1549Q.setVisibility(0);
            } else {
                this.f1549Q.setVisibility(8);
            }
        }
    }

    public void showCompassEnabled(boolean z) {
        if (this.f1550R != null) {
            this.f1550R.mo9539a(z);
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo9163a() {
        this.f1645l.obtainMessage(14).sendToTarget();
    }

    public void showScaleEnabled(boolean z) {
        if (this.f1551S != null) {
            this.f1551S.mo8675a(z);
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo9172b() {
        this.f1645l.post(new C077317());
    }

    public boolean removeGLOverlay(String str) throws RemoteException {
        setRunLowFrame(false);
        return this.f1641h.mo9567c(str);
    }

    public synchronized void setRunLowFrame(boolean z) {
        if (!z) {
            this.f1612ba = false;
            this.f1613bb.removeCallbacks(this.f1614bc);
            this.f1584aZ = false;
        } else if (!(this.f1584aZ || this.f1612ba)) {
            this.f1612ba = true;
            this.f1613bb.postDelayed(this.f1614bc, ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME);
        }
    }

    public void onDrawFrame(GL10 gl10) {
        int i = 1;
        try {
            if (this.f1567aI) {
                gl10.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
                gl10.glClear(16640);
                this.f1539G.setGL(gl10);
                this.f1539G.drawFrame(gl10);
                mo9169a(gl10);
                this.f1640g.mo8745a(gl10);
                this.f1641h.mo9563a(gl10, false, this.f1608ax);
                this.f1637d.mo8499a(gl10);
                this.f1581aW.mo9544a(gl10);
                if (this.f1596al != null) {
                    this.f1596al.mo8663a(gl10);
                }
                if (this.f1576aR) {
                    if (!this.f1539G.canStopRenderMap()) {
                        i = 0;
                    }
                    Message obtainMessage = this.f1645l.obtainMessage(16, AMapDelegateImp.m2032a(0, 0, mo9173c(), mo9174d(), gl10));
                    obtainMessage.arg1 = i;
                    obtainMessage.sendToTarget();
                    this.f1576aR = false;
                }
                if (!this.f1604at.isFinished()) {
                    this.f1645l.sendEmptyMessage(13);
                }
                if (this.f1552T != null) {
                    i = this.f1552T.getVisibility();
                    BlankView blankView = this.f1552T;
                    if (i != 8) {
                        if (!this.f1568aJ) {
                            this.f1645l.sendEmptyMessage(11);
                            this.f1568aJ = true;
                        }
                        this.f1570aL = true;
                        this.f1645l.post(new C077418());
                        return;
                    }
                    return;
                }
                return;
            }
            gl10.glClearColor(0.9453125f, 0.93359f, 0.9101f, 1.0f);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public Rect getRect() {
        return this.f1600ap;
    }

    public int getMapWidth() {
        return this.f1600ap.width();
    }

    public int getMapHeight() {
        return this.f1600ap.height();
    }

    /* renamed from: c */
    public int mo9173c() {
        return this.f1643j.getWidth();
    }

    /* renamed from: d */
    public int mo9174d() {
        return this.f1643j.getHeight();
    }

    public void setMyTrafficStyle(MyTrafficStyle myTrafficStyle) {
        if (this.f1567aI && myTrafficStyle != null) {
            this.f1658y = myTrafficStyle;
            this.f1539G.setParameter(2201, 1, 1, 1, 1);
            this.f1539G.setParameter(2202, myTrafficStyle.getSmoothColor(), myTrafficStyle.getSlowColor(), myTrafficStyle.getCongestedColor(), myTrafficStyle.getSeriousCongestedColor());
        }
    }

    /* renamed from: p */
    private synchronized void m2096p() {
        if (this.f1565aF != null) {
            m2098q();
        }
        if (this.f1565aF == null) {
            this.f1565aF = new Timer();
        }
        this.f1565aF.schedule(new C0797h(this), 0, (long) (1000 / this.f1649p));
    }

    /* renamed from: q */
    private synchronized void m2098q() {
        if (this.f1565aF != null) {
            this.f1565aF.cancel();
            this.f1565aF = null;
        }
    }

    /* renamed from: r */
    private synchronized void m2099r() {
        try {
            if (!this.f1615bd) {
                this.f1642i.mo9550a();
                this.f1642i.mo9551a(true);
                this.f1642i.mo9553b(true);
                this.f1642i.mo9557e(true);
                this.f1642i.mo9556d(true);
                this.f1642i.mo9555c(true);
                this.f1615bd = true;
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "setInternaltexture");
            th.printStackTrace();
        }
        return;
    }

    public int getImaginaryLineTextureID() {
        return this.f1648o;
    }

    public void redrawInfoWindow() {
        try {
            if (this.f1577aS && this.f1594aj != null && this.f1595ak != null) {
                C0736a c0736a = (C0736a) this.f1594aj.getLayoutParams();
                if (c0736a != null) {
                    this.f1595ak.getRect();
                    int realInfoWindowOffsetX = this.f1595ak.getRealInfoWindowOffsetX() + this.f1595ak.getInfoWindowOffsetX();
                    int realInfoWindowOffsetY = (this.f1595ak.getRealInfoWindowOffsetY() + this.f1595ak.getInfoWindowOffsetY()) + 2;
                    c0736a.f999a = this.f1595ak.getMapPosition();
                    c0736a.f1000b = realInfoWindowOffsetX;
                    c0736a.f1001c = realInfoWindowOffsetY;
                    if (this.f1596al != null) {
                        this.f1596al.mo8662a(this.f1595ak.getMapPosition());
                        this.f1596al.setInfoWindowOffset(realInfoWindowOffsetX, realInfoWindowOffsetY);
                    }
                }
                this.f1547O.onLayout(false, 0, 0, 0, 0);
                setRunLowFrame(false);
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "redrawInfoWindow");
            th.printStackTrace();
        }
    }

    public void setZOrderOnTop(boolean z) {
        this.f1643j.setZOrderOnTop(z);
    }

    public CameraPosition getCameraPosition() throws RemoteException {
        return getCameraPositionPrj(this.f1629br);
    }

    public float getMaxZoomLevel() {
        return ConfigableConst.f2126f;
    }

    public float getMinZoomLevel() {
        return 3.0f;
    }

    public void moveCamera(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) throws RemoteException {
        if (cameraUpdateFactoryDelegate.nowType == Type.newLatLngBounds) {
            boolean z = mo9173c() > 0 && mo9174d() > 0;
            AMapThrowException.m2226a(z, (Object) "the map must have a size");
        }
        if ((this.f1582aX || this.f1583aY) && this.f1638e.mo8490d() > 0) {
            CameraUpdateFactoryDelegate newInstance = CameraUpdateFactoryDelegate.newInstance();
            newInstance.nowType = Type.changeGeoCenterZoomTiltBearing;
            newInstance.geoPoint = new IPoint(this.f1652s, this.f1653t);
            newInstance.zoom = this.f1559a;
            newInstance.bearing = this.f1636c;
            newInstance.tilt = this.f1611b;
            this.f1638e.mo8487a(cameraUpdateFactoryDelegate);
            while (this.f1638e.mo8490d() > 0) {
                CameraUpdateFactoryDelegate c = this.f1638e.mo8489c();
                if (c != null) {
                    if (c.cameraPosition != null) {
                        float f;
                        CameraPosition cameraPosition = c.cameraPosition;
                        if (cameraPosition.target != null) {
                            IPoint iPoint = new IPoint();
                            MapProjection.lonlat2Geo(cameraPosition.target.longitude, cameraPosition.target.latitude, iPoint);
                            newInstance.geoPoint = iPoint;
                        }
                        newInstance.zoom = cameraPosition.zoom == 0.0f ? newInstance.zoom : cameraPosition.zoom;
                        newInstance.bearing = cameraPosition.bearing == 0.0f ? newInstance.bearing : cameraPosition.bearing;
                        if (cameraPosition.tilt == 0.0f) {
                            f = newInstance.tilt;
                        } else {
                            f = cameraPosition.tilt;
                        }
                        newInstance.tilt = f;
                    } else if (c.nowType.equals(Type.zoomIn)) {
                        newInstance.zoom += 1.0f;
                    } else if (c.nowType.equals(Type.zoomOut)) {
                        newInstance.zoom -= 1.0f;
                    } else if (c.nowType.equals(Type.zoomBy)) {
                        newInstance.zoom += newInstance.amount;
                    } else {
                        int i;
                        newInstance.geoPoint = c.geoPoint == null ? newInstance.geoPoint : c.geoPoint;
                        newInstance.zoom = c.zoom == 0.0f ? newInstance.zoom : c.zoom;
                        newInstance.bearing = c.bearing == 0.0f ? newInstance.bearing : c.bearing;
                        newInstance.tilt = c.tilt == 0.0f ? newInstance.tilt : c.tilt;
                        newInstance.xPixel = c.xPixel == 0.0f ? newInstance.xPixel : c.xPixel;
                        newInstance.yPixel = c.yPixel == 0.0f ? newInstance.yPixel : c.yPixel;
                        newInstance.width = c.width == 0 ? newInstance.width : c.width;
                        if (c.height == 0) {
                            i = newInstance.height;
                        } else {
                            i = c.height;
                        }
                        newInstance.height = i;
                    }
                    newInstance.zoom = Util.m2337a(newInstance.zoom);
                    newInstance.tilt = Util.m2338a(newInstance.tilt, newInstance.zoom);
                }
            }
            cameraUpdateFactoryDelegate = newInstance;
        }
        stopAnimation();
        cameraUpdateFactoryDelegate.isChangeFinished = true;
        cameraUpdateFactoryDelegate.isUseAnchor = this.f1629br;
        this.f1638e.mo8487a(cameraUpdateFactoryDelegate);
    }

    public void animateCamera(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) throws RemoteException {
        animateCameraWithCallback(cameraUpdateFactoryDelegate, null);
    }

    public void animateCameraWithCallback(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate, CancelableCallback cancelableCallback) throws RemoteException {
        animateCameraWithDurationAndCallback(cameraUpdateFactoryDelegate, 250, cancelableCallback);
    }

    public void animateCameraWithDurationAndCallback(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate, long j, CancelableCallback cancelableCallback) throws RemoteException {
        if (this.f1582aX || this.f1583aY) {
            moveCamera(cameraUpdateFactoryDelegate);
            return;
        }
        if (cameraUpdateFactoryDelegate.nowType == Type.newLatLngBounds) {
            boolean z = mo9173c() > 0 && mo9174d() > 0;
            AMapThrowException.m2226a(z, (Object) "the map must have a size");
        }
        if (!this.f1604at.isFinished()) {
            this.f1604at.forceFinished(true);
            if (this.f1607aw != null) {
                this.f1607aw.onCancel();
            }
        }
        this.f1604at.setUseAnchor(this.f1629br);
        this.f1607aw = cancelableCallback;
        if (this.f1573aO) {
            this.f1574aP = true;
        }
        this.f1572aN = false;
        IPoint iPoint;
        float mapZoomer;
        float a;
        int i;
        int i2;
        int i3;
        IPoint iPoint2;
        float mapAngle;
        float f;
        float cameraHeaderAngle;
        float a2;
        if (cameraUpdateFactoryDelegate.nowType == Type.scrollBy) {
            if (cameraUpdateFactoryDelegate.xPixel == 0.0f && cameraUpdateFactoryDelegate.yPixel == 0.0f) {
                this.f1645l.obtainMessage(17).sendToTarget();
                return;
            }
            this.f1604at.setUseAnchor(false);
            iPoint = new IPoint();
            this.f1542J.getGeoCenter(iPoint);
            IPoint iPoint3 = new IPoint();
            getPixel2Geo((mo9173c() / 2) + ((int) cameraUpdateFactoryDelegate.xPixel), (mo9174d() / 2) + ((int) cameraUpdateFactoryDelegate.yPixel), iPoint3);
            this.f1604at.setInterpolator(new AccelerateDecelerateInterpolator());
            this.f1604at.startChangeCamera(iPoint.f4562x, iPoint.f4563y, this.f1542J.getMapZoomer(), this.f1542J.getMapAngle(), this.f1542J.getCameraHeaderAngle(), iPoint3.f4562x - iPoint.f4562x, iPoint3.f4563y - iPoint.f4563y, 0.0f, 0.0f, 0.0f, j);
        } else if (cameraUpdateFactoryDelegate.nowType == Type.zoomIn) {
            mapZoomer = this.f1542J.getMapZoomer();
            a = Util.m2337a(1.0f + mapZoomer) - mapZoomer;
            if (a == 0.0f) {
                this.f1645l.obtainMessage(17).sendToTarget();
                return;
            }
            iPoint = new IPoint();
            if (this.f1629br) {
                getPixel2Geo(this.f1630bs, this.f1631bt, iPoint);
            } else {
                this.f1542J.getGeoCenter(iPoint);
            }
            this.f1604at.setInterpolator(new AccelerateInterpolator());
            this.f1604at.startChangeCamera(iPoint.f4562x, iPoint.f4563y, mapZoomer, this.f1542J.getMapAngle(), this.f1542J.getCameraHeaderAngle(), 0, 0, a, 0.0f, 0.0f, j);
        } else if (cameraUpdateFactoryDelegate.nowType == Type.zoomOut) {
            mapZoomer = this.f1542J.getMapZoomer();
            a = Util.m2337a(mapZoomer - 1.0f) - mapZoomer;
            if (a == 0.0f) {
                this.f1645l.obtainMessage(17).sendToTarget();
                return;
            }
            iPoint = new IPoint();
            if (this.f1629br) {
                getPixel2Geo(this.f1630bs, this.f1631bt, iPoint);
            } else {
                this.f1542J.getGeoCenter(iPoint);
            }
            this.f1604at.setInterpolator(new AccelerateInterpolator());
            this.f1604at.startChangeCamera(iPoint.f4562x, iPoint.f4563y, mapZoomer, this.f1542J.getMapAngle(), this.f1542J.getCameraHeaderAngle(), 0, 0, a, 0.0f, 0.0f, j);
        } else if (cameraUpdateFactoryDelegate.nowType == Type.zoomTo) {
            mapZoomer = this.f1542J.getMapZoomer();
            a = Util.m2337a(cameraUpdateFactoryDelegate.zoom) - mapZoomer;
            if (a == 0.0f) {
                this.f1645l.obtainMessage(17).sendToTarget();
                return;
            }
            iPoint = new IPoint();
            if (this.f1629br) {
                getPixel2Geo(this.f1630bs, this.f1631bt, iPoint);
            } else {
                this.f1542J.getGeoCenter(iPoint);
            }
            this.f1604at.setInterpolator(new AccelerateInterpolator());
            this.f1604at.startChangeCamera(iPoint.f4562x, iPoint.f4563y, mapZoomer, this.f1542J.getMapAngle(), this.f1542J.getCameraHeaderAngle(), 0, 0, a, 0.0f, 0.0f, j);
        } else if (cameraUpdateFactoryDelegate.nowType == Type.zoomBy) {
            this.f1604at.setUseAnchor(false);
            float f2 = cameraUpdateFactoryDelegate.amount;
            mapZoomer = this.f1542J.getMapZoomer();
            a = Util.m2337a(mapZoomer + f2) - mapZoomer;
            if (a == 0.0f) {
                this.f1645l.obtainMessage(17).sendToTarget();
                return;
            }
            Point point = cameraUpdateFactoryDelegate.focus;
            IPoint iPoint4 = new IPoint();
            this.f1542J.getGeoCenter(iPoint4);
            i = 0;
            i2 = 0;
            IPoint iPoint5 = new IPoint();
            int i4;
            if (point != null) {
                getPixel2Geo(point.x, point.y, iPoint5);
                i3 = iPoint4.f4562x - iPoint5.f4562x;
                i4 = iPoint4.f4563y - iPoint5.f4563y;
                i = (int) ((((double) i3) / Math.pow(2.0d, (double) f2)) - ((double) i3));
                i2 = (int) ((((double) i4) / Math.pow(2.0d, (double) f2)) - ((double) i4));
            } else if (this.f1629br) {
                getPixel2Geo(this.f1630bs, this.f1631bt, iPoint5);
                i3 = iPoint4.f4562x - iPoint5.f4562x;
                i4 = iPoint4.f4563y - iPoint5.f4563y;
                i = (int) ((((double) i3) / Math.pow(2.0d, (double) f2)) - ((double) i3));
                i2 = (int) ((((double) i4) / Math.pow(2.0d, (double) f2)) - ((double) i4));
            }
            this.f1604at.setInterpolator(new AccelerateInterpolator());
            this.f1604at.startChangeCamera(iPoint4.f4562x, iPoint4.f4563y, mapZoomer, this.f1542J.getMapAngle(), this.f1542J.getCameraHeaderAngle(), i, i2, a, 0.0f, 0.0f, j);
        } else if (cameraUpdateFactoryDelegate.nowType == Type.newCameraPosition) {
            iPoint = new IPoint();
            if (this.f1629br) {
                getPixel2Geo(this.f1630bs, this.f1631bt, iPoint);
            } else {
                this.f1542J.getGeoCenter(iPoint);
            }
            iPoint2 = new IPoint();
            CameraPosition cameraPosition = cameraUpdateFactoryDelegate.cameraPosition;
            MapProjection.lonlat2Geo(cameraPosition.target.longitude, cameraPosition.target.latitude, iPoint2);
            mapZoomer = this.f1542J.getMapZoomer();
            i = iPoint2.f4562x - iPoint.f4562x;
            i2 = iPoint2.f4563y - iPoint.f4563y;
            a = Util.m2337a(cameraPosition.zoom) - mapZoomer;
            mapAngle = this.f1542J.getMapAngle();
            f = (cameraPosition.bearing % 360.0f) - (mapAngle % 360.0f);
            if (Math.abs(f) >= 180.0f) {
                f -= Math.signum(f) * 360.0f;
            }
            cameraHeaderAngle = this.f1542J.getCameraHeaderAngle();
            a2 = Util.m2338a(cameraPosition.tilt, cameraPosition.zoom) - cameraHeaderAngle;
            if (i == 0 && i2 == 0 && a == 0.0f && f == 0.0f && a2 == 0.0f) {
                this.f1645l.obtainMessage(17).sendToTarget();
                return;
            } else {
                this.f1604at.setInterpolator(new AccelerateInterpolator());
                this.f1604at.startChangeCamera(iPoint.f4562x, iPoint.f4563y, mapZoomer, mapAngle, cameraHeaderAngle, i, i2, a, f, a2, j);
            }
        } else if (cameraUpdateFactoryDelegate.nowType == Type.changeBearing) {
            mapAngle = this.f1542J.getMapAngle();
            f = (cameraUpdateFactoryDelegate.bearing % 360.0f) - (mapAngle % 360.0f);
            if (Math.abs(f) >= 180.0f) {
                f -= Math.signum(f) * 360.0f;
            }
            if (f == 0.0f) {
                this.f1645l.obtainMessage(17).sendToTarget();
                return;
            }
            iPoint = new IPoint();
            if (this.f1629br) {
                getPixel2Geo(this.f1630bs, this.f1631bt, iPoint);
            } else {
                this.f1542J.getGeoCenter(iPoint);
            }
            this.f1604at.setInterpolator(new AccelerateInterpolator());
            this.f1604at.startChangeCamera(iPoint.f4562x, iPoint.f4563y, this.f1542J.getMapZoomer(), mapAngle, this.f1542J.getCameraHeaderAngle(), 0, 0, 0.0f, f, 0.0f, j);
        } else if (cameraUpdateFactoryDelegate.nowType == Type.changeTilt) {
            cameraHeaderAngle = this.f1542J.getCameraHeaderAngle();
            a2 = cameraUpdateFactoryDelegate.tilt - cameraHeaderAngle;
            if (a2 == 0.0f) {
                this.f1645l.obtainMessage(17).sendToTarget();
                return;
            }
            iPoint = new IPoint();
            if (this.f1629br) {
                getPixel2Geo(this.f1630bs, this.f1631bt, iPoint);
            } else {
                this.f1542J.getGeoCenter(iPoint);
            }
            this.f1604at.setInterpolator(new AccelerateInterpolator());
            this.f1604at.startChangeCamera(iPoint.f4562x, iPoint.f4563y, this.f1542J.getMapZoomer(), this.f1542J.getMapAngle(), cameraHeaderAngle, 0, 0, 0.0f, 0.0f, a2, j);
        } else if (cameraUpdateFactoryDelegate.nowType == Type.changeCenter) {
            iPoint = new IPoint();
            if (this.f1629br) {
                getPixel2Geo(this.f1630bs, this.f1631bt, iPoint);
            } else {
                this.f1542J.getGeoCenter(iPoint);
            }
            i = cameraUpdateFactoryDelegate.geoPoint.f4562x - iPoint.f4562x;
            i2 = cameraUpdateFactoryDelegate.geoPoint.f4563y - iPoint.f4563y;
            if (i == 0 && i2 == 0) {
                this.f1645l.obtainMessage(17).sendToTarget();
                return;
            } else {
                this.f1604at.setInterpolator(new AccelerateDecelerateInterpolator());
                this.f1604at.startChangeCamera(iPoint.f4562x, iPoint.f4563y, this.f1542J.getMapZoomer(), this.f1542J.getMapAngle(), this.f1542J.getCameraHeaderAngle(), i, i2, 0.0f, 0.0f, 0.0f, j);
            }
        } else if (cameraUpdateFactoryDelegate.nowType == Type.newLatLngBounds || cameraUpdateFactoryDelegate.nowType == Type.newLatLngBoundsWithSize) {
            int i5;
            this.f1604at.setUseAnchor(false);
            if (cameraUpdateFactoryDelegate.nowType == Type.newLatLngBounds) {
                i3 = mo9173c();
                i = mo9174d();
                i5 = i3;
            } else {
                i3 = cameraUpdateFactoryDelegate.width;
                i = cameraUpdateFactoryDelegate.height;
                i5 = i3;
            }
            float mapAngle2 = this.f1542J.getMapAngle() % 360.0f;
            float cameraHeaderAngle2 = this.f1542J.getCameraHeaderAngle();
            f = -mapAngle2;
            if (Math.abs(f) >= 180.0f) {
                f -= Math.signum(f) * 360.0f;
            }
            a2 = -cameraHeaderAngle2;
            LatLngBounds latLngBounds = cameraUpdateFactoryDelegate.bounds;
            int i6 = cameraUpdateFactoryDelegate.padding;
            IPoint iPoint6 = new IPoint();
            this.f1542J.getGeoCenter(iPoint6);
            float mapZoomer2 = this.f1542J.getMapZoomer();
            this.f1604at.setInterpolator(new AccelerateInterpolator());
            iPoint = new IPoint();
            iPoint2 = new IPoint();
            MapProjection.lonlat2Geo(latLngBounds.northeast.longitude, latLngBounds.northeast.latitude, iPoint);
            MapProjection.lonlat2Geo(latLngBounds.southwest.longitude, latLngBounds.southwest.latitude, iPoint2);
            i2 = iPoint.f4562x - iPoint2.f4562x;
            int i7 = iPoint2.f4563y - iPoint.f4563y;
            if (i2 > 0 || i7 > 0) {
                int i8 = (iPoint.f4562x + iPoint2.f4562x) / 2;
                int i9 = (iPoint.f4563y + iPoint2.f4563y) / 2;
                IPoint iPoint7 = new IPoint();
                getLatLng2Pixel((latLngBounds.northeast.latitude + latLngBounds.southwest.latitude) / 2.0d, (latLngBounds.northeast.longitude + latLngBounds.southwest.longitude) / 2.0d, iPoint7);
                int i10;
                if ((!this.f1600ap.contains(iPoint7.f4562x, iPoint7.f4563y) ? 1 : null) == null) {
                    i3 = i5 - (i6 * 2);
                    i10 = i - (i6 * 2);
                    if (i3 <= 0) {
                        i3 = 1;
                    }
                    if (i10 <= 0) {
                        i10 = 1;
                    }
                    a = Util.m2337a((float) ((int) (Math.min(Math.log(((double) this.f1542J.getMapLenWithWin(i3)) / ((double) this.f1542J.getMapLenWithGeo(i2))) / Math.log(2.0d), Math.log(((double) this.f1542J.getMapLenWithWin(i10)) / ((double) this.f1542J.getMapLenWithGeo(i7))) / Math.log(2.0d)) + ((double) mapZoomer2)))) - mapZoomer2;
                    i = i8 - iPoint6.f4562x;
                    i2 = i9 - iPoint6.f4563y;
                    if (i == 0 && i2 == 0 && a == 0.0f) {
                        this.f1645l.obtainMessage(17).sendToTarget();
                        return;
                    } else {
                        this.f1604at.setInterpolator(new DecelerateInterpolator());
                        this.f1604at.startChangeCamera(iPoint6.f4562x, iPoint6.f4563y, mapZoomer2, mapAngle2, cameraHeaderAngle2, i, i2, a, f, a2, j);
                    }
                } else {
                    final CancelableCallback cancelableCallback2 = this.f1607aw;
                    final LatLngBounds latLngBounds2 = latLngBounds;
                    final int i11 = i5;
                    final int i12 = i;
                    final int i13 = i6;
                    final long j2 = j;
                    this.f1607aw = new CancelableCallback() {
                        public void onFinish() {
                            try {
                                AMapDelegateImp.this.animateCameraWithDurationAndCallback(CameraUpdateFactoryDelegate.newLatLngBoundsWithSize(latLngBounds2, i11, i12, i13), j2, cancelableCallback2);
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }

                        public void onCancel() {
                            if (cancelableCallback2 != null) {
                                cancelableCallback2.onCancel();
                            }
                        }
                    };
                    i7 = ((iPoint6.f4562x + i8) / 2) - iPoint6.f4562x;
                    i2 = ((iPoint6.f4563y + i9) / 2) - iPoint6.f4563y;
                    i10 = (int) Util.m2336a((double) (((float) getMapWidth()) / 2.0f), (double) (((float) getMapHeight()) / 2.0f), (double) Math.abs(i8 - iPoint6.f4562x), (double) Math.abs(i9 - iPoint6.f4563y));
                    a = i10 == 0 ? 0.0f : ((float) i10) - mapZoomer2;
                    if (a >= 0.0f) {
                        a = 0.0f;
                    }
                    this.f1572aN = true;
                    this.f1604at.startChangeCamera(iPoint6.f4562x, iPoint6.f4563y, mapZoomer2, mapAngle2, cameraHeaderAngle2, i7, i2, a, f / 2.0f, a2 / 2.0f, j / 2);
                }
            } else {
                this.f1645l.obtainMessage(17).sendToTarget();
                return;
            }
        } else {
            cameraUpdateFactoryDelegate.isChangeFinished = true;
            this.f1638e.mo8487a(cameraUpdateFactoryDelegate);
        }
        setRunLowFrame(false);
    }

    public void stopAnimation() throws RemoteException {
        if (!this.f1604at.isFinished()) {
            this.f1604at.forceFinished(true);
            mo9171a(true, null);
            if (this.f1607aw != null) {
                this.f1607aw.onCancel();
            }
            if (!(this.f1594aj == null || this.f1596al == null)) {
                this.f1594aj.setVisibility(0);
            }
            this.f1607aw = null;
        }
        setRunLowFrame(false);
    }

    public IPolylineDelegate addPolyline(PolylineOptions polylineOptions) throws RemoteException {
        if (polylineOptions == null) {
            return null;
        }
        IOverlayDelegate polylineDelegateImp = new PolylineDelegateImp(this.f1641h);
        polylineDelegateImp.setColor(polylineOptions.getColor());
        polylineDelegateImp.setGeodesic(polylineOptions.isGeodesic());
        polylineDelegateImp.setDottedLine(polylineOptions.isDottedLine());
        polylineDelegateImp.setPoints(polylineOptions.getPoints());
        polylineDelegateImp.setVisible(polylineOptions.isVisible());
        polylineDelegateImp.setWidth(polylineOptions.getWidth());
        polylineDelegateImp.setZIndex(polylineOptions.getZIndex());
        polylineDelegateImp.mo8660a(polylineOptions.isUseTexture());
        if (polylineOptions.getColorValues() != null) {
            polylineDelegateImp.setColorValues(polylineOptions.getColorValues());
            polylineDelegateImp.useGradient(polylineOptions.isUseGradient());
        }
        if (polylineOptions.getCustomTexture() != null) {
            polylineDelegateImp.mo8656a(polylineOptions.getCustomTexture());
        }
        if (polylineOptions.getCustomTextureList() != null) {
            polylineDelegateImp.setCustomTextureList(polylineOptions.getCustomTextureList());
            polylineDelegateImp.setCustemTextureIndex(polylineOptions.getCustomTextureIndex());
        }
        this.f1641h.mo9561a(polylineDelegateImp);
        setRunLowFrame(false);
        return polylineDelegateImp;
    }

    public INavigateArrowDelegate addNavigateArrow(NavigateArrowOptions navigateArrowOptions) throws RemoteException {
        if (navigateArrowOptions == null) {
            return null;
        }
        IOverlayDelegate navigateArrowDelegateImp = new NavigateArrowDelegateImp(this);
        navigateArrowDelegateImp.setTopColor(navigateArrowOptions.getTopColor());
        navigateArrowDelegateImp.setPoints(navigateArrowOptions.getPoints());
        navigateArrowDelegateImp.setVisible(navigateArrowOptions.isVisible());
        navigateArrowDelegateImp.setWidth(navigateArrowOptions.getWidth());
        navigateArrowDelegateImp.setZIndex(navigateArrowOptions.getZIndex());
        this.f1641h.mo9561a(navigateArrowDelegateImp);
        setRunLowFrame(false);
        return navigateArrowDelegateImp;
    }

    public IPolygonDelegate addPolygon(PolygonOptions polygonOptions) throws RemoteException {
        if (polygonOptions == null) {
            return null;
        }
        IOverlayDelegate polygonDelegateImp = new PolygonDelegateImp(this);
        polygonDelegateImp.setFillColor(polygonOptions.getFillColor());
        polygonDelegateImp.setPoints(polygonOptions.getPoints());
        polygonDelegateImp.setVisible(polygonOptions.isVisible());
        polygonDelegateImp.setStrokeWidth(polygonOptions.getStrokeWidth());
        polygonDelegateImp.setZIndex(polygonOptions.getZIndex());
        polygonDelegateImp.setStrokeColor(polygonOptions.getStrokeColor());
        this.f1641h.mo9561a(polygonDelegateImp);
        setRunLowFrame(false);
        return polygonDelegateImp;
    }

    public ICircleDelegate addCircle(CircleOptions circleOptions) throws RemoteException {
        if (circleOptions == null) {
            return null;
        }
        IOverlayDelegate circleDelegateImp = new CircleDelegateImp(this);
        circleDelegateImp.setFillColor(circleOptions.getFillColor());
        circleDelegateImp.setCenter(circleOptions.getCenter());
        circleDelegateImp.setVisible(circleOptions.isVisible());
        circleDelegateImp.setStrokeWidth(circleOptions.getStrokeWidth());
        circleDelegateImp.setZIndex(circleOptions.getZIndex());
        circleDelegateImp.setStrokeColor(circleOptions.getStrokeColor());
        circleDelegateImp.setRadius(circleOptions.getRadius());
        this.f1641h.mo9561a(circleDelegateImp);
        setRunLowFrame(false);
        return circleDelegateImp;
    }

    public IArcDelegate addArc(ArcOptions arcOptions) throws RemoteException {
        if (arcOptions == null) {
            return null;
        }
        IOverlayDelegate arcDelegateImp = new ArcDelegateImp(this);
        arcDelegateImp.setStrokeColor(arcOptions.getStrokeColor());
        arcDelegateImp.setStart(arcOptions.getStart());
        arcDelegateImp.setPassed(arcOptions.getPassed());
        arcDelegateImp.setEnd(arcOptions.getEnd());
        arcDelegateImp.setVisible(arcOptions.isVisible());
        arcDelegateImp.setStrokeWidth(arcOptions.getStrokeWidth());
        arcDelegateImp.setZIndex(arcOptions.getZIndex());
        this.f1641h.mo9561a(arcDelegateImp);
        setRunLowFrame(false);
        return arcDelegateImp;
    }

    public IGroundOverlayDelegate addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
        if (groundOverlayOptions == null) {
            return null;
        }
        IOverlayDelegate groundOverlayDelegateImp = new GroundOverlayDelegateImp(this);
        groundOverlayDelegateImp.setAnchor(groundOverlayOptions.getAnchorU(), groundOverlayOptions.getAnchorV());
        groundOverlayDelegateImp.setDimensions(groundOverlayOptions.getWidth(), groundOverlayOptions.getHeight());
        groundOverlayDelegateImp.setImage(groundOverlayOptions.getImage());
        groundOverlayDelegateImp.setPosition(groundOverlayOptions.getLocation());
        groundOverlayDelegateImp.setPositionFromBounds(groundOverlayOptions.getBounds());
        groundOverlayDelegateImp.setBearing(groundOverlayOptions.getBearing());
        groundOverlayDelegateImp.setTransparency(groundOverlayOptions.getTransparency());
        groundOverlayDelegateImp.setVisible(groundOverlayOptions.isVisible());
        groundOverlayDelegateImp.setZIndex(groundOverlayOptions.getZIndex());
        this.f1641h.mo9561a(groundOverlayDelegateImp);
        setRunLowFrame(false);
        return groundOverlayDelegateImp;
    }

    public Marker addMarker(MarkerOptions markerOptions) throws RemoteException {
        if (markerOptions == null) {
            return null;
        }
        IMarkerDelegate markerDelegateImp = new MarkerDelegateImp(markerOptions, this.f1637d);
        this.f1637d.mo8503b(markerDelegateImp);
        setRunLowFrame(false);
        return new Marker(markerDelegateImp);
    }

    public Text addText(TextOptions textOptions) throws RemoteException {
        if (textOptions == null) {
            return null;
        }
        IMarkerDelegate textDelegateImp = new TextDelegateImp(textOptions, this.f1637d);
        this.f1637d.mo8503b(textDelegateImp);
        setRunLowFrame(false);
        return new Text(textDelegateImp);
    }

    public ArrayList<Marker> addMarkers(ArrayList<MarkerOptions> arrayList, boolean z) throws RemoteException {
        int i = 0;
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        ArrayList<Marker> arrayList2 = new ArrayList();
        try {
            MarkerOptions markerOptions;
            if (arrayList.size() == 1) {
                markerOptions = (MarkerOptions) arrayList.get(0);
                if (markerOptions != null) {
                    arrayList2.add(addMarker(markerOptions));
                    if (z && markerOptions.getPosition() != null) {
                        moveCamera(CameraUpdateFactoryDelegate.newLatLngZoom(markerOptions.getPosition(), 18.0f));
                    }
                    return arrayList2;
                }
            }
            final Builder builder = LatLngBounds.builder();
            int i2 = 0;
            while (i2 < arrayList.size()) {
                int i3;
                markerOptions = (MarkerOptions) arrayList.get(i2);
                if (arrayList.get(i2) != null) {
                    arrayList2.add(addMarker(markerOptions));
                    if (markerOptions.getPosition() != null) {
                        builder.include(markerOptions.getPosition());
                        i3 = i + 1;
                        i2++;
                        i = i3;
                    }
                }
                i3 = i;
                i2++;
                i = i3;
            }
            if (z && i > 0) {
                if (this.f1568aJ) {
                    this.f1645l.postDelayed(new Runnable() {
                        public void run() {
                            try {
                                AMapDelegateImp.this.moveCamera(CameraUpdateFactoryDelegate.newLatLngBounds(builder.build(), 50));
                            } catch (Throwable th) {
                            }
                        }
                    }, 50);
                } else {
                    this.f1564aE = CameraUpdateFactoryDelegate.newLatLngBounds(builder.build(), 50);
                }
            }
            return arrayList2;
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "addMarkers");
            th.printStackTrace();
            return arrayList2;
        }
    }

    /* renamed from: a */
    public MarkerDelegateImp addMarker4Imp(MarkerOptions markerOptions) throws RemoteException {
        if (markerOptions == null) {
            return null;
        }
        IMarkerDelegate markerDelegateImp = new MarkerDelegateImp(markerOptions, this.f1637d);
        this.f1637d.mo8503b(markerDelegateImp);
        setRunLowFrame(false);
        return markerDelegateImp;
    }

    public TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException {
        if (tileOverlayOptions == null || tileOverlayOptions.getTileProvider() == null) {
            return null;
        }
        ITileOverlayDelegate tileOverlayDelegateImp = new TileOverlayDelegateImp(tileOverlayOptions, this.f1640g);
        this.f1640g.mo8744a(tileOverlayDelegateImp);
        setRunLowFrame(false);
        return new TileOverlay(tileOverlayDelegateImp);
    }

    public void clear() throws RemoteException {
        try {
            clear(false);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "clear");
            Log.d("amapApi", "AMapDelegateImpGLSurfaceView clear erro" + th.getMessage());
            th.printStackTrace();
        }
    }

    public void clear(boolean z) throws RemoteException {
        String str = null;
        try {
            String c;
            hiddenInfoWindowShown();
            if (this.f1603as != null) {
                if (z) {
                    c = this.f1603as.mo8596c();
                    str = this.f1603as.mo8597d();
                    this.f1641h.mo9565b(str);
                    this.f1640g.mo8747b();
                    this.f1637d.mo8504b(c);
                    setRunLowFrame(false);
                }
                this.f1603as.mo8598e();
            }
            c = null;
            this.f1641h.mo9565b(str);
            this.f1640g.mo8747b();
            this.f1637d.mo8504b(c);
            setRunLowFrame(false);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "clear");
            Log.d("amapApi", "AMapDelegateImpGLSurfaceView clear erro" + th.getMessage());
            th.printStackTrace();
        }
    }

    public boolean removeMarker(String str) {
        IMarkerDelegate a;
        try {
            a = this.f1637d.mo8495a(str);
        } catch (RemoteException e) {
            SDKLogHandler.m2563a(e, "AMapDelegateImpGLSurfaceView", "removeMarker");
            e.printStackTrace();
            a = null;
        }
        if (a == null) {
            return false;
        }
        setRunLowFrame(false);
        return this.f1637d.mo8507c(a);
    }

    public int getMapType() throws RemoteException {
        return this.f1538F;
    }

    public void setMapType(int i) throws RemoteException {
        this.f1538F = i;
        if (this.f1568aJ) {
            if (i == 1) {
                try {
                    mo9166a(C0872a.NORAML, C0874c.DAY);
                } catch (Throwable th) {
                    SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "setMaptype");
                    th.printStackTrace();
                    return;
                }
            } else if (i == 2) {
                mo9166a(C0872a.SATELLITE, C0874c.DAY);
            } else if (i == 3) {
                mo9167a(C0872a.NORAML, C0874c.NIGHT, C0873b.NAVI_CAR);
            } else if (i == 4) {
                mo9167a(C0872a.NORAML, C0874c.DAY, C0873b.NAVI_CAR);
            } else {
                this.f1538F = 1;
            }
            setRunLowFrame(false);
        }
    }

    /* renamed from: a */
    public void mo9166a(C0872a c0872a, C0874c c0874c) {
        mo9167a(c0872a, c0874c, C0873b.NORMAL);
    }

    /* renamed from: a */
    public void mo9167a(C0872a c0872a, C0874c c0874c, C0873b c0873b) {
        if (this.f1535C != c0874c || this.f1536D != c0872a || this.f1537E != c0873b) {
            if (this.f1569aK) {
                final C0874c c0874c2 = this.f1535C;
                final C0872a c0872a2 = this.f1536D;
                C0873b c0873b2 = this.f1537E;
                if (this.f1615bd && this.f1567aI) {
                    final C0874c c0874c3 = c0874c;
                    final C0872a c0872a3 = c0872a;
                    final C0873b c0873b3 = c0873b;
                    queueEvent(new Runnable() {

                        /* compiled from: AMapDelegateImp */
                        /* renamed from: com.amap.api.mapcore.util.c$3$1 */
                        class C07781 implements Runnable {
                            C07781() {
                            }

                            public void run() {
                                AMapDelegateImp.this.m2105u();
                            }
                        }

                        public void run() {
                            String b = AMapDelegateImp.this.f1642i.mo9552b();
                            String c = AMapDelegateImp.this.f1642i.mo9554c();
                            AMapDelegateImp.this.f1535C = c0874c3;
                            AMapDelegateImp.this.f1536D = c0872a3;
                            AMapDelegateImp.this.f1537E = c0873b3;
                            String b2 = AMapDelegateImp.this.f1642i.mo9552b();
                            String c2 = AMapDelegateImp.this.f1642i.mo9554c();
                            if (AMapDelegateImp.this.f1536D == C0872a.SATELLITE || AMapDelegateImp.this.f1535C == C0874c.NIGHT || c0874c2 == C0874c.NIGHT || c0872a2 == C0872a.SATELLITE) {
                                AMapDelegateImp.this.f1645l.post(new C07781());
                            }
                            AMapDelegateImp.this.f1539G.setParameter(2501, 0, 0, 0, 0);
                            if (!b.equals(b2)) {
                                AMapDelegateImp.this.f1642i.mo9550a();
                            }
                            if (AMapDelegateImp.this.f1536D == C0872a.SATELLITE || c0872a2 == C0872a.SATELLITE) {
                                AMapDelegateImp.this.f1539G.setParameter(2011, AMapDelegateImp.this.f1536D == C0872a.SATELLITE ? 1 : 0, 0, 0, 0);
                            }
                            if (AMapDelegateImp.this.f1535C == C0874c.NIGHT || c0874c2 == C0874c.NIGHT) {
                                int i;
                                MapCore g = AMapDelegateImp.this.f1539G;
                                if (AMapDelegateImp.this.f1535C == C0874c.NIGHT) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                g.setParameter(2401, i, 0, 0, 0);
                                AMapDelegateImp.this.f1642i.mo9556d(true);
                                AMapDelegateImp.this.f1642i.mo9555c(true);
                            }
                            if (!c.equals(c2)) {
                                AMapDelegateImp.this.f1642i.mo9551a(true);
                            }
                            AMapDelegateImp.this.f1642i.mo9553b(true);
                            if (AMapDelegateImp.this.f1537E != null) {
                                AMapDelegateImp.this.f1539G.setParameter(2013, AMapDelegateImp.this.f1536D.ordinal(), AMapDelegateImp.this.f1535C.ordinal(), AMapDelegateImp.this.f1537E.ordinal(), 0);
                            }
                            AMapDelegateImp.this.f1539G.setParameter(2501, 1, 1, 0, 0);
                        }
                    });
                    return;
                }
                this.f1635bx.f1491d = c0872a;
                this.f1635bx.f1492e = c0874c;
                this.f1635bx.f1489b = true;
                return;
            }
            this.f1535C = c0874c;
            this.f1536D = c0872a;
            this.f1537E = c0873b;
        }
    }

    public boolean isTrafficEnabled() throws RemoteException {
        return this.f1657x;
    }

    public void setTrafficEnabled(boolean z) throws RemoteException {
        this.f1657x = z;
        setRunLowFrame(false);
        this.f1638e.mo8486a(new MapMessage(2).mo8483a(z));
    }

    public void setMapTextEnable(final boolean z) throws RemoteException {
        this.f1656w = z;
        setRunLowFrame(false);
        queueEvent(new Runnable() {
            public void run() {
                if (AMapDelegateImp.this.f1539G != null) {
                    int i;
                    MapCore g = AMapDelegateImp.this.f1539G;
                    if (z) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    g.setParameter(1024, i, 0, 0, 0);
                }
            }
        });
    }

    public boolean isIndoorEnabled() throws RemoteException {
        return this.f1654u;
    }

    public void setIndoorEnabled(final boolean z) throws RemoteException {
        this.f1654u = z;
        setRunLowFrame(false);
        if (z) {
            this.f1539G.setParameter(1026, 1, 0, 0, 0);
        } else {
            this.f1539G.setParameter(1026, 0, 0, 0, 0);
            ConfigableConst.f2126f = 19.0f;
            if (this.f1598an.isZoomControlsEnabled()) {
                this.f1645l.sendEmptyMessage(21);
            }
        }
        if (this.f1598an.isIndoorSwitchEnabled()) {
            this.f1645l.post(new Runnable() {
                public void run() {
                    if (z) {
                        AMapDelegateImp.this.showIndoorSwitchControlsEnabled(true);
                    } else {
                        AMapDelegateImp.this.f1553U.mo9597a(false);
                    }
                }
            });
        }
    }

    public void set3DBuildingEnabled(final boolean z) throws RemoteException {
        this.f1655v = z;
        setRunLowFrame(false);
        queueEvent(new Runnable() {
            public void run() {
                if (AMapDelegateImp.this.f1539G != null) {
                    int i;
                    MapCore g = AMapDelegateImp.this.f1539G;
                    if (z) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    g.setParameter(PointerIconCompat.TYPE_GRABBING, i, 0, 0, 0);
                }
            }
        });
    }

    public boolean isMyLocationEnabled() throws RemoteException {
        return this.f1566aH;
    }

    public void setMyLocationEnabled(boolean z) throws RemoteException {
        try {
            if (this.f1599ao == null) {
                this.f1549Q.mo8467a(false);
            } else if (z) {
                this.f1599ao.activate(this.f1601aq);
                this.f1549Q.mo8467a(true);
                if (this.f1603as == null) {
                    this.f1603as = new MyLocationOverlay(this, this.f1540H);
                }
            } else {
                if (this.f1603as != null) {
                    this.f1603as.mo8595b();
                    this.f1603as = null;
                }
                this.f1610az = null;
                this.f1599ao.deactivate();
            }
            if (!z) {
                this.f1598an.setMyLocationButtonEnabled(z);
            }
            this.f1566aH = z;
            setRunLowFrame(false);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "setMyLocationEnabled");
            th.printStackTrace();
        }
    }

    public Location getMyLocation() throws RemoteException {
        if (this.f1599ao != null) {
            return this.f1601aq.f2073a;
        }
        return null;
    }

    public void setLocationSource(LocationSource locationSource) throws RemoteException {
        try {
            this.f1599ao = locationSource;
            if (locationSource != null) {
                this.f1549Q.mo8467a(true);
            } else {
                this.f1549Q.mo8467a(false);
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "setLocationSource");
            th.printStackTrace();
        }
    }

    public IUiSettingsDelegate getUiSettings() throws RemoteException {
        return this.f1598an;
    }

    public IProjectionDelegate getProjection() throws RemoteException {
        return this.f1597am;
    }

    public void setOnCameraChangeListener(OnCameraChangeListener onCameraChangeListener) throws RemoteException {
        this.f1585aa = onCameraChangeListener;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo9168a(CameraPosition cameraPosition) {
        Message message = new Message();
        message.what = 10;
        message.obj = cameraPosition;
        this.f1645l.sendMessage(message);
    }

    public OnCameraChangeListener getOnCameraChangeListener() throws RemoteException {
        return this.f1585aa;
    }

    public void setOnMapClickListener(OnMapClickListener onMapClickListener) throws RemoteException {
        this.f1586ab = onMapClickListener;
    }

    public void setOnMapTouchListener(OnMapTouchListener onMapTouchListener) throws RemoteException {
        this.f1587ac = onMapTouchListener;
    }

    public void setOnPOIClickListener(OnPOIClickListener onPOIClickListener) throws RemoteException {
        this.f1588ad = onPOIClickListener;
    }

    public void setOnMapLongClickListener(OnMapLongClickListener onMapLongClickListener) throws RemoteException {
        this.f1589ae = onMapLongClickListener;
    }

    public void setOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) throws RemoteException {
        this.f1555W = onMarkerClickListener;
    }

    public void setOnPolylineClickListener(OnPolylineClickListener onPolylineClickListener) throws RemoteException {
        this.f1556X = onPolylineClickListener;
    }

    public void setOnMarkerDragListener(OnMarkerDragListener onMarkerDragListener) throws RemoteException {
        this.f1557Y = onMarkerDragListener;
    }

    public void setOnMaploadedListener(OnMapLoadedListener onMapLoadedListener) throws RemoteException {
        this.f1558Z = onMapLoadedListener;
    }

    public void setOnInfoWindowClickListener(OnInfoWindowClickListener onInfoWindowClickListener) throws RemoteException {
        this.f1590af = onInfoWindowClickListener;
    }

    public void setOnIndoorBuildingActiveListener(OnIndoorBuildingActiveListener onIndoorBuildingActiveListener) throws RemoteException {
        this.f1591ag = onIndoorBuildingActiveListener;
    }

    public void setInfoWindowAdapter(InfoWindowAdapter infoWindowAdapter) throws RemoteException {
        if (infoWindowAdapter == null) {
            this.f1592ah = this.f1593ai;
        } else {
            this.f1592ah = infoWindowAdapter;
        }
    }

    public View getView() throws RemoteException {
        return this.f1547O;
    }

    public float checkZoomLevel(float f) throws RemoteException {
        return Util.m2337a(f);
    }

    public float toMapLenWithWin(int i) {
        if (this.f1567aI) {
            return this.f1542J.getMapLenWithWin(i);
        }
        return 0.0f;
    }

    public void getPixel2LatLng(int i, int i2, DPoint dPoint) {
        m2048a(this.f1542J, i, i2, dPoint);
    }

    /* renamed from: a */
    private void m2048a(MapProjection mapProjection, int i, int i2, DPoint dPoint) {
        if (this.f1567aI) {
            FPoint fPoint = new FPoint();
            mapProjection.win2Map(i, i2, fPoint);
            IPoint iPoint = new IPoint();
            mapProjection.map2Geo(fPoint.f4560x, fPoint.f4561y, iPoint);
            MapProjection.geo2LonLat(iPoint.f4562x, iPoint.f4563y, dPoint);
        }
    }

    public void getPixel2Geo(int i, int i2, IPoint iPoint) {
        if (this.f1567aI) {
            FPoint fPoint = new FPoint();
            this.f1542J.win2Map(i, i2, fPoint);
            this.f1542J.map2Geo(fPoint.f4560x, fPoint.f4561y, iPoint);
        }
    }

    /* renamed from: a */
    public void mo9164a(int i, int i2, IPoint iPoint) {
        if (this.f1567aI) {
            FPoint fPoint = new FPoint();
            this.f1542J.geo2Map(i, i2, fPoint);
            this.f1542J.map2Win(fPoint.f4560x, fPoint.f4561y, iPoint);
        }
    }

    public void getLatLng2Map(double d, double d2, FPoint fPoint) {
        if (this.f1567aI) {
            IPoint iPoint = new IPoint();
            MapProjection.lonlat2Geo(d2, d, iPoint);
            this.f1542J.geo2Map(iPoint.f4562x, iPoint.f4563y, fPoint);
        }
    }

    public void latlon2Geo(double d, double d2, IPoint iPoint) {
        MapProjection.lonlat2Geo(d2, d, iPoint);
    }

    public void pixel2Map(int i, int i2, FPoint fPoint) {
        if (this.f1567aI) {
            this.f1542J.win2Map(i, i2, fPoint);
        }
    }

    public void geo2Map(int i, int i2, FPoint fPoint) {
        if (this.f1567aI) {
            this.f1542J.geo2Map(i2, i, fPoint);
        }
    }

    public void map2Geo(float f, float f2, IPoint iPoint) {
        if (this.f1567aI) {
            this.f1542J.map2Geo(f, f2, iPoint);
        }
    }

    public void geo2Latlng(int i, int i2, DPoint dPoint) {
        MapProjection.geo2LonLat(i, i2, dPoint);
    }

    public void getLatLng2Pixel(double d, double d2, IPoint iPoint) {
        if (this.f1567aI) {
            MapProjection mapProjection = new MapProjection(this.f1539G);
            mapProjection.recalculate();
            IPoint iPoint2 = new IPoint();
            FPoint fPoint = new FPoint();
            MapProjection.lonlat2Geo(d2, d, iPoint2);
            mapProjection.geo2Map(iPoint2.f4562x, iPoint2.f4563y, fPoint);
            mapProjection.map2Win(fPoint.f4560x, fPoint.f4561y, iPoint);
            mapProjection.recycle();
        }
    }

    /* renamed from: s */
    private LatLng m2102s() {
        if (!this.f1567aI) {
            return null;
        }
        DPoint dPoint = new DPoint();
        IPoint iPoint = new IPoint();
        this.f1542J.getGeoCenter(iPoint);
        MapProjection.geo2LonLat(iPoint.f4562x, iPoint.f4563y, dPoint);
        return new LatLng(dPoint.f4559y, dPoint.f4558x, false);
    }

    public CameraPosition getCameraPositionPrj(boolean z) {
        if (!this.f1567aI) {
            return null;
        }
        LatLng latLng;
        if (z) {
            DPoint dPoint = new DPoint();
            getPixel2LatLng(this.f1630bs, this.f1631bt, dPoint);
            latLng = new LatLng(dPoint.f4559y, dPoint.f4558x, false);
        } else {
            latLng = m2102s();
        }
        return CameraPosition.builder().target(latLng).bearing(this.f1542J.getMapAngle()).tilt(this.f1542J.getCameraHeaderAngle()).zoom(this.f1542J.getMapZoomer()).build();
    }

    /* renamed from: t */
    private void m2104t() {
        CameraUpdateFactoryDelegate newInstance;
        if (this.f1625bn) {
            this.f1625bn = false;
        }
        if (this.f1621bj) {
            this.f1621bj = false;
            newInstance = CameraUpdateFactoryDelegate.newInstance();
            newInstance.isChangeFinished = true;
            this.f1638e.mo8487a(newInstance);
        }
        if (this.f1616be) {
            this.f1616be = false;
            newInstance = CameraUpdateFactoryDelegate.newInstance();
            newInstance.isChangeFinished = true;
            this.f1638e.mo8487a(newInstance);
        }
        this.f1617bf = false;
        this.f1618bg = false;
        if (this.f1557Y != null && this.f1619bh != null) {
            this.f1557Y.onMarkerDragEnd(this.f1619bh);
            this.f1619bh = null;
        }
    }

    /* renamed from: a */
    private void m2046a(MotionEvent motionEvent) throws RemoteException {
        if (this.f1618bg && this.f1619bh != null) {
            int x = (int) motionEvent.getX();
            int y = (int) (motionEvent.getY() - 60.0f);
            LatLng realPosition = this.f1620bi.getRealPosition();
            LatLng position = this.f1620bi.getPosition();
            DPoint dPoint = new DPoint();
            getPixel2LatLng(x, y, dPoint);
            this.f1619bh.setPosition(new LatLng((position.latitude + dPoint.f4559y) - realPosition.latitude, (dPoint.f4558x + position.longitude) - realPosition.longitude));
            this.f1557Y.onMarkerDrag(this.f1619bh);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f1568aJ) {
            return false;
        }
        setRunLowFrame(false);
        if (motionEvent.getAction() == 261) {
            this.f1624bm = motionEvent.getPointerCount();
        }
        this.f1543K.onTouchEvent(motionEvent);
        this.f1602ar.mo8800a(motionEvent);
        this.f1544L.onTouchEvent(motionEvent);
        this.f1545M.mo8792a(motionEvent);
        if (motionEvent.getAction() == 2) {
            try {
                m2046a(motionEvent);
            } catch (RemoteException e) {
                SDKLogHandler.m2563a(e, "AMapDelegateImpGLSurfaceView", "onDragMarker");
                e.printStackTrace();
            }
        }
        if (motionEvent.getAction() == 1) {
            m2104t();
        }
        setRunLowFrame(false);
        if (this.f1587ac != null) {
            this.f1632bu.removeMessages(1);
            Message obtainMessage = this.f1632bu.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = MotionEvent.obtain(motionEvent);
            obtainMessage.sendToTarget();
        }
        return true;
    }

    public void showInfoWindow(IMarkerDelegate iMarkerDelegate) throws RemoteException {
        int i = -2;
        if (iMarkerDelegate != null) {
            hiddenInfoWindowShown();
            if ((iMarkerDelegate.getTitle() != null || iMarkerDelegate.getSnippet() != null) && this.f1592ah != null) {
                this.f1595ak = iMarkerDelegate;
                if (this.f1568aJ) {
                    int i2;
                    Marker marker = new Marker(iMarkerDelegate);
                    this.f1594aj = this.f1592ah.getInfoWindow(marker);
                    try {
                        if (this.f1609ay == null) {
                            this.f1609ay = NinePatchTool.m1585a(this.f1540H, "infowindow_bg.9.png");
                        }
                    } catch (Throwable th) {
                        SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "showInfoWindow decodeDrawableFromAsset");
                        th.printStackTrace();
                    }
                    if (this.f1594aj == null) {
                        this.f1594aj = this.f1592ah.getInfoContents(marker);
                    }
                    LinearLayout linearLayout = new LinearLayout(this.f1540H);
                    if (this.f1594aj != null) {
                        if (this.f1594aj.getBackground() == null) {
                            this.f1594aj.setBackgroundDrawable(this.f1609ay);
                        }
                        linearLayout.addView(this.f1594aj);
                    } else {
                        linearLayout.setBackgroundDrawable(this.f1609ay);
                        TextView textView = new TextView(this.f1540H);
                        textView.setText(iMarkerDelegate.getTitle());
                        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        TextView textView2 = new TextView(this.f1540H);
                        textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        textView2.setText(iMarkerDelegate.getSnippet());
                        linearLayout.setOrientation(1);
                        linearLayout.addView(textView);
                        linearLayout.addView(textView2);
                    }
                    this.f1594aj = linearLayout;
                    LayoutParams layoutParams = this.f1594aj.getLayoutParams();
                    this.f1594aj.setDrawingCacheEnabled(true);
                    this.f1594aj.setDrawingCacheQuality(0);
                    iMarkerDelegate.getRect();
                    int realInfoWindowOffsetX = iMarkerDelegate.getRealInfoWindowOffsetX() + iMarkerDelegate.getInfoWindowOffsetX();
                    int realInfoWindowOffsetY = (iMarkerDelegate.getRealInfoWindowOffsetY() + iMarkerDelegate.getInfoWindowOffsetY()) + 2;
                    if (layoutParams != null) {
                        i2 = layoutParams.width;
                        i = layoutParams.height;
                    } else {
                        i2 = -2;
                    }
                    C0736a c0736a = new C0736a(i2, i, iMarkerDelegate.getMapPosition(), realInfoWindowOffsetX, realInfoWindowOffsetY, 81);
                    Bitmap a;
                    BitmapDescriptor fromBitmap;
                    if (this.f1596al == null) {
                        a = Util.m2347a(this.f1594aj);
                        fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
                        a.recycle();
                        this.f1596al = new PopupOverlay(new MarkerOptions().icon(fromBitmap), this) {
                            /* renamed from: a */
                            public void mo8661a() {
                                AMapDelegateImp.this.f1562aC.removeCallbacks(AMapDelegateImp.this.f1633bv);
                                AMapDelegateImp.this.f1562aC.post(AMapDelegateImp.this.f1634bw);
                            }
                        };
                        this.f1596al.mo8662a(iMarkerDelegate.getMapPosition());
                        this.f1596al.setInfoWindowOffset(realInfoWindowOffsetX, realInfoWindowOffsetY);
                    } else {
                        this.f1596al.mo8662a(iMarkerDelegate.getMapPosition());
                        this.f1596al.setInfoWindowOffset(realInfoWindowOffsetX, realInfoWindowOffsetY);
                        a = Util.m2347a(this.f1594aj);
                        fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
                        a.recycle();
                        this.f1596al.setIcon(fromBitmap);
                    }
                    this.f1547O.addView(this.f1594aj, c0736a);
                    iMarkerDelegate.setInfoWindowShown(true);
                    return;
                }
                this.f1562aC.postDelayed(new C07837(), 100);
            }
        }
    }

    public boolean isInfoWindowShown(IMarkerDelegate iMarkerDelegate) {
        try {
            if (!(this.f1595ak == null || this.f1594aj == null)) {
                return this.f1595ak.getId().equals(iMarkerDelegate.getId());
            }
        } catch (RemoteException e) {
            SDKLogHandler.m2563a(e, "AMapDelegateImpGLSurfaceView", "isInfoWindowShown");
            e.printStackTrace();
        }
        return false;
    }

    public void hiddenInfoWindowShown() {
        if (this.f1594aj != null) {
            this.f1594aj.clearFocus();
            this.f1547O.removeView(this.f1594aj);
            Util.m2358a(this.f1594aj.getBackground());
            Util.m2358a(this.f1609ay);
            if (this.f1596al != null) {
                this.f1596al.setVisible(false);
            }
            this.f1594aj = null;
        }
        this.f1595ak = null;
    }

    public float getZoomLevel() {
        return this.f1542J.getMapZoomer();
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: e */
    public void mo9175e() {
        this.f1645l.obtainMessage(18).sendToTarget();
    }

    public LatLngBounds getMapBounds() {
        return this.f1627bp;
    }

    public LatLngBounds getMapBounds(LatLng latLng, float f) {
        int c = mo9173c();
        int d = mo9174d();
        if (c <= 0 || d <= 0) {
            return null;
        }
        IPoint iPoint = new IPoint();
        MapProjection.lonlat2Geo(latLng.longitude, latLng.latitude, iPoint);
        MapProjection mapProjection = new MapProjection(this.f1539G);
        mapProjection.setCameraHeaderAngle(0.0f);
        mapProjection.setMapAngle(0.0f);
        mapProjection.setGeoCenter(iPoint.f4562x, iPoint.f4563y);
        mapProjection.setMapZoomer(f);
        mapProjection.recalculate();
        DPoint dPoint = new DPoint();
        m2048a(mapProjection, 0, 0, dPoint);
        LatLng latLng2 = new LatLng(dPoint.f4559y, dPoint.f4558x, false);
        m2048a(mapProjection, c, d, dPoint);
        LatLng latLng3 = new LatLng(dPoint.f4559y, dPoint.f4558x, false);
        mapProjection.recycle();
        return LatLngBounds.builder().include(latLng3).include(latLng2).build();
    }

    public Point getWaterMarkerPositon() {
        if (this.f1548P == null) {
            return null;
        }
        return this.f1548P.mo8782c();
    }

    /* renamed from: a */
    public static Bitmap m2032a(int i, int i2, int i3, int i4, GL10 gl10) {
        try {
            int[] iArr = new int[(i3 * i4)];
            int[] iArr2 = new int[(i3 * i4)];
            IntBuffer wrap = IntBuffer.wrap(iArr);
            wrap.position(0);
            gl10.glReadPixels(i, i2, i3, i4, 6408, 5121, wrap);
            for (int i5 = 0; i5 < i4; i5++) {
                for (int i6 = 0; i6 < i3; i6++) {
                    int i7 = iArr[(i5 * i3) + i6];
                    iArr2[(((i4 - i5) - 1) * i3) + i6] = ((i7 & -16711936) | ((i7 << 16) & 16711680)) | ((i7 >> 16) & 255);
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i3, i4, Config.ARGB_8888);
            createBitmap.setPixels(iArr2, 0, i3, 0, 0, i3, i4);
            return createBitmap;
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "SavePixels");
            th.printStackTrace();
            return null;
        }
    }

    public void getMapPrintScreen(onMapPrintScreenListener onmapprintscreenlistener) {
        this.f1560aA = onmapprintscreenlistener;
        this.f1576aR = true;
        setRunLowFrame(false);
    }

    public void getMapScreenShot(OnMapScreenShotListener onMapScreenShotListener) {
        this.f1561aB = onMapScreenShotListener;
        this.f1576aR = true;
        setRunLowFrame(false);
    }

    public int getLogoPosition() {
        try {
            return this.f1598an.getLogoPosition();
        } catch (RemoteException e) {
            SDKLogHandler.m2563a(e, "AMapDelegateImpGLSurfaceView", "getLogoPosition");
            e.printStackTrace();
            return 0;
        }
    }

    public void setLogoPosition(int i) {
        if (this.f1548P != null) {
            this.f1548P.mo8779a(i);
            this.f1548P.invalidate();
            if (this.f1551S.getVisibility() == 0) {
                this.f1551S.invalidate();
            }
        }
    }

    public void setZoomPosition(int i) {
        if (this.f1639f != null) {
            this.f1639f.mo8788a(i);
        }
    }

    public float getScalePerPixel() {
        try {
            LatLng latLng = getCameraPosition().target;
            float f = this.f1559a;
            if (this.f1567aI) {
                f = this.f1542J.getMapZoomer();
            }
            return (float) ((((Math.cos((latLng.latitude * 3.141592653589793d) / 180.0d) * 2.0d) * 3.141592653589793d) * 6378137.0d) / (Math.pow(2.0d, (double) f) * 256.0d));
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "getScalePerPixel");
            th.printStackTrace();
            return 0.0f;
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo9170a(boolean z) {
        int i;
        Handler handler = this.f1645l;
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        handler.obtainMessage(20, i, 0).sendToTarget();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo9171a(boolean z, CameraPosition cameraPosition) {
        if (this.f1585aa != null && this.f1604at.isFinished() && this.f1643j.isEnabled()) {
            if (cameraPosition == null) {
                try {
                    cameraPosition = getCameraPosition();
                } catch (RemoteException e) {
                    SDKLogHandler.m2563a(e, "AMapDelegateImpGLSurfaceView", "cameraChangeFinish");
                    e.printStackTrace();
                }
            }
            this.f1585aa.onCameraChangeFinish(cameraPosition);
        }
    }

    public void deleteTexsureId(int i) {
        if (this.f1534B.contains(Integer.valueOf(i))) {
            this.f1533A.add(Integer.valueOf(i));
            this.f1534B.remove(this.f1534B.indexOf(Integer.valueOf(i)));
        }
    }

    public int getTexsureId() {
        Integer valueOf = Integer.valueOf(0);
        if (this.f1533A.size() > 0) {
            valueOf = (Integer) this.f1533A.get(0);
            this.f1533A.remove(0);
            this.f1534B.add(valueOf);
        }
        return valueOf.intValue();
    }

    public List<Marker> getMapScreenMarkers() {
        boolean z = mo9173c() > 0 && mo9174d() > 0;
        AMapThrowException.m2226a(z, (Object) "地图未初始化完成！");
        return this.f1637d.mo8514g();
    }

    public void changeGLOverlayIndex() {
        this.f1641h.mo9564b();
    }

    public boolean isAdreno() {
        return this.f1571aM;
    }

    public void callRunDestroy() {
        this.f1628bq = true;
    }

    public boolean isNeedRunDestroy() {
        return this.f1628bq;
    }

    public void runDestroy() {
        if (this.f1637d != null) {
            this.f1637d.mo8515h();
        }
        this.f1628bq = false;
    }

    public void setCenterToPixel(int i, int i2) {
        if (this.f1541I != null) {
            this.f1629br = true;
            this.f1541I.mo8462a(i, i2);
            this.f1630bs = i;
            this.f1631bt = i2;
        }
    }

    public boolean isUseAnchor() {
        return this.f1629br;
    }

    public void setMapTextZIndex(int i) {
        this.f1608ax = i;
    }

    public int getMapTextZIndex() {
        return this.f1608ax;
    }

    public boolean isMaploaded() {
        return this.f1568aJ;
    }

    public int getAnchorX() {
        return this.f1630bs;
    }

    public int getAnchorY() {
        return this.f1631bt;
    }

    public CameraAnimator getCameraAnimator() {
        return this.f1604at;
    }

    public void setLoadOfflineData(final boolean z) throws RemoteException {
        queueEvent(new Runnable() {
            public void run() {
                int i;
                MapCore g = AMapDelegateImp.this.f1539G;
                if (z) {
                    i = 1;
                } else {
                    i = 0;
                }
                g.setParameter(2601, i, 0, 0, 0);
            }
        });
    }

    public void removecache() {
        removecache(null);
    }

    public void removecache(OnCacheRemoveListener onCacheRemoveListener) {
        if (this.f1579aU != null) {
            try {
                this.f1539G.setParameter(2601, 0, 0, 0, 0);
                C0798i c0798i = new C0798i(this.f1540H, onCacheRemoveListener);
                this.f1579aU.removeCallbacks(c0798i);
                this.f1579aU.post(c0798i);
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "removecache");
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private boolean m2051a(File file) throws IOException, Exception {
        if (file == null || !file.exists()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isFile()) {
                    if (!listFiles[i].delete()) {
                        return false;
                    }
                } else if (!m2051a(listFiles[i])) {
                    return false;
                } else {
                    listFiles[i].delete();
                }
            }
        }
        return true;
    }

    /* renamed from: f */
    public void mo9176f() {
        if (this.f1641h != null) {
            this.f1641h.mo9566c();
        }
        if (this.f1637d != null) {
            this.f1637d.mo8506c();
        }
        if (this.f1646m != null) {
            this.f1646m.OnMapReferencechanged();
        }
    }

    public void setVisibilityEx(int i) {
        this.f1643j.setVisibility(i);
    }

    /* renamed from: a */
    public void mo9165a(IndoorBuilding indoorBuilding) throws RemoteException {
        if (!this.f1654u) {
            return;
        }
        if (indoorBuilding == null) {
            if (!m2094o()) {
                if (this.f1591ag != null) {
                    this.f1591ag.OnIndoorBuilding(indoorBuilding);
                }
                if (this.f1563aD != null) {
                    this.f1563aD.geoCenter = null;
                }
                if (this.f1553U.mo9601d()) {
                    this.f1645l.post(new C076711());
                }
                ConfigableConst.f2126f = 19.0f;
                if (this.f1598an.isZoomControlsEnabled()) {
                    this.f1645l.sendEmptyMessage(21);
                }
            }
        } else if (this.f1563aD == null || !this.f1563aD.poiid.equals(indoorBuilding.poiid) || !this.f1553U.mo9601d()) {
            if (this.f1563aD == null || !this.f1563aD.poiid.equals(indoorBuilding.poiid) || this.f1563aD.geoCenter == null) {
                this.f1563aD = indoorBuilding;
                this.f1563aD.geoCenter = new IPoint();
                this.f1542J.getGeoCenter(this.f1563aD.geoCenter);
            }
            if (this.f1591ag != null) {
                this.f1591ag.OnIndoorBuilding(indoorBuilding);
            }
            ConfigableConst.f2126f = 20.0f;
            if (this.f1598an.isZoomControlsEnabled()) {
                this.f1645l.sendEmptyMessage(21);
            }
            if (this.f1598an.isIndoorSwitchEnabled() && !this.f1553U.mo9601d()) {
                this.f1598an.setIndoorSwitchEnabled(true);
                this.f1645l.post(new C076812());
            } else if (!this.f1598an.isIndoorSwitchEnabled() && this.f1553U.mo9601d()) {
                this.f1598an.setIndoorSwitchEnabled(false);
            }
        }
    }

    public void setIndoorBuildingInfo(IndoorBuilding indoorBuilding) throws RemoteException {
        if (indoorBuilding != null && indoorBuilding.activeFloorName != null && indoorBuilding.poiid != null) {
            this.f1563aD = indoorBuilding;
            setRunLowFrame(false);
            queueEvent(new C076913());
        }
    }

    /* renamed from: a */
    private Poi m2041a(int i, int i2, int i3) {
        if (!this.f1568aJ) {
            return null;
        }
        try {
            SelectedMapPoi GetSelectedMapPoi = this.f1539G.GetSelectedMapPoi(i, i2, i3);
            if (GetSelectedMapPoi == null) {
                return null;
            }
            DPoint dPoint = new DPoint();
            MapProjection.geo2LonLat(GetSelectedMapPoi.mapx, GetSelectedMapPoi.mapy, dPoint);
            return new Poi(GetSelectedMapPoi.name, new LatLng(dPoint.f4559y, dPoint.f4558x, false), GetSelectedMapPoi.poiid);
        } catch (Throwable th) {
            return null;
        }
    }

    public void setCustomRenderer(CustomRenderer customRenderer) {
        this.f1646m = customRenderer;
    }

    /* renamed from: g */
    public Context mo9177g() {
        return this.f1540H;
    }

    public void queueEvent(Runnable runnable) {
        if (this.f1643j != null) {
            this.f1643j.queueEvent(runnable);
        }
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        try {
            if (!this.f1567aI) {
                m2085l();
            }
            this.f1615bd = false;
            this.f1539G.setGL(gl10);
            m2099r();
            this.f1539G.surfaceCreate(gl10);
            if (this.f1650q == null || this.f1650q.isRecycled()) {
                this.f1650q = Util.m2344a(this.f1540H, "lineTexture.png");
            }
            if (this.f1651r == null || this.f1651r.isRecycled()) {
                this.f1651r = Util.m2344a(this.f1540H, "lineDashTexture.png");
            }
            this.f1578aT = false;
            this.f1647n = Util.m2341a(gl10, this.f1650q);
            this.f1648o = Util.m2342a(gl10, this.f1651r, true);
            this.f1650q = null;
            this.f1637d.mo8517j();
            this.f1641h.mo9569e();
            this.f1640g.mo8753f();
            if (this.f1596al != null) {
                this.f1596al.reLoadTexture();
            }
            m2096p();
            setRunLowFrame(false);
            if (!this.f1569aK) {
                this.f1626bo.setName("AuthThread");
                this.f1626bo.start();
                this.f1569aK = true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (this.f1646m != null) {
            this.f1646m.onSurfaceCreated(gl10, eGLConfig);
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        int i3 = 120;
        int i4 = 50;
        int i5 = 1;
        this.f1600ap = new Rect(0, 0, i, i2);
        try {
            this.f1539G.setGL(gl10);
            this.f1539G.surfaceChange(gl10, i, i2);
            int i6 = this.f1540H.getResources().getDisplayMetrics().densityDpi;
            float f = this.f1540H.getResources().getDisplayMetrics().density;
            int i7 = 100;
            if (i6 > 120) {
                if (i6 <= 160) {
                    if (Math.max(i, i2) <= 480) {
                        i6 = 120;
                    } else {
                        i6 = 100;
                        i3 = 160;
                    }
                    i4 = i3;
                    i7 = i6;
                } else if (i6 <= 240) {
                    if (Math.min(i, i2) >= 1000) {
                        i7 = 60;
                        i4 = 200;
                        i5 = 2;
                    } else {
                        i7 = 70;
                        i4 = 150;
                        i5 = 2;
                    }
                } else if (i6 <= 320) {
                    i5 = 3;
                    i7 = 50;
                    i4 = 180;
                } else if (i6 <= 480) {
                    i5 = 3;
                    i7 = 50;
                    i4 = 300;
                } else {
                    i7 = 40;
                    i4 = 360;
                    i5 = 4;
                }
            }
            this.f1539G.setParameter(2051, i7, i4, (int) (f * 100.0f), i5);
            this.f1659z = ((float) i7) / 100.0f;
            this.f1539G.setParameter(1001, 0, 0, 0, 0);
            this.f1539G.setParameter(1023, 1, 0, 0, 0);
            setRunLowFrame(false);
            if (this.f1646m != null) {
                this.f1646m.onSurfaceChanged(gl10, i, i2);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public float getMapZoomScale() {
        return this.f1659z;
    }

    public void reloadMap() {
        this.f1568aJ = false;
        onPause();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onResume();
    }

    /* renamed from: h */
    public C0874c mo9178h() {
        return this.f1535C;
    }

    /* renamed from: i */
    public C0872a mo9179i() {
        return this.f1536D;
    }

    /* renamed from: j */
    public C0873b mo9180j() {
        return this.f1537E;
    }

    /* renamed from: u */
    private void m2105u() {
        if (this.f1536D == C0872a.SATELLITE || this.f1535C == C0874c.NIGHT) {
            this.f1548P.mo8780a(true);
        } else {
            this.f1548P.mo8780a(false);
        }
    }

    public void setRenderFps(int i) {
        try {
            this.f1649p = Math.max(10, Math.min(i, 40));
            this.f1645l.sendEmptyMessage(22);
        } catch (Throwable th) {
        }
    }

    public boolean isDrawOnce() {
        return this.f1570aL;
    }
}
