package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.amap.api.mapcore2d.C0899at.C0898a;
import com.amap.api.mapcore2d.CameraChangeFinishObserver.C0923a;
import com.amap.api.mapcore2d.CameraUpdateFactoryDelegate.C1036a;
import com.amap.api.mapcore2d.CancelAnimObserver.C0924a;
import com.amap.api.mapcore2d.MultiTouchGestureDetector.C0921b;
import com.amap.api.mapcore2d.MultiTouchGestureDetector.C0925a;
import com.amap.api.mapcore2d.StopAnimObserver.C0922a;
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
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.PolygonOptions;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.maps2d.model.Text;
import com.amap.api.maps2d.model.TextOptions;
import com.amap.api.maps2d.model.TileOverlay;
import com.amap.api.maps2d.model.TileOverlayOptions;
import com.autonavi.amap.mapcore.VTMCDataCache;
import com.mcdonalds.sdk.connectors.autonavi.AutoNaviConnector;
import com.newrelic.agent.android.payload.PayloadController;
import com.newrelic.agent.android.tracing.ActivityTrace;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* renamed from: com.amap.api.mapcore2d.b */
class AMapDelegateImpGLSurfaceView extends View implements OnDoubleTapListener, OnGestureListener, C0921b, C0922a, C0923a, C0924a, IAMapDelegate {
    /* renamed from: aD */
    private static int f2421aD = Color.rgb(222, 215, 214);
    /* renamed from: aE */
    private static Paint f2422aE = null;
    /* renamed from: aF */
    private static Bitmap f2423aF = null;
    /* renamed from: A */
    private C0947ca f2424A;
    /* renamed from: B */
    private C0933bi f2425B;
    /* renamed from: C */
    private LocationSource f2426C;
    /* renamed from: D */
    private C1040o f2427D;
    /* renamed from: E */
    private C0883a f2428E = null;
    /* renamed from: F */
    private boolean f2429F = false;
    /* renamed from: G */
    private boolean f2430G = false;
    /* renamed from: H */
    private OnCameraChangeListener f2431H;
    /* renamed from: I */
    private CameraAnimator f2432I;
    /* renamed from: J */
    private CancelableCallback f2433J = null;
    /* renamed from: K */
    private MapProjection f2434K;
    /* renamed from: L */
    private boolean f2435L = false;
    /* renamed from: M */
    private boolean f2436M = false;
    /* renamed from: N */
    private View f2437N;
    /* renamed from: O */
    private OnInfoWindowClickListener f2438O;
    /* renamed from: P */
    private InfoWindowAdapter f2439P;
    /* renamed from: Q */
    private C0903ax f2440Q;
    /* renamed from: R */
    private OnMarkerClickListener f2441R;
    /* renamed from: S */
    private Drawable f2442S = null;
    /* renamed from: T */
    private IProjectionDelegate f2443T;
    /* renamed from: U */
    private boolean f2444U = false;
    /* renamed from: V */
    private boolean f2445V = false;
    /* renamed from: W */
    private boolean f2446W = false;
    /* renamed from: Z */
    private OnMarkerDragListener f2447Z;
    /* renamed from: a */
    Mediator f2448a;
    /* renamed from: aA */
    private long f2449aA = 0;
    /* renamed from: aB */
    private int f2450aB = 0;
    /* renamed from: aC */
    private int f2451aC = 0;
    /* renamed from: aG */
    private int f2452aG = 0;
    /* renamed from: aH */
    private boolean f2453aH = false;
    /* renamed from: aI */
    private C0919a f2454aI = null;
    /* renamed from: aa */
    private OnMapTouchListener f2455aa;
    /* renamed from: ab */
    private OnMapLongClickListener f2456ab;
    /* renamed from: ac */
    private OnMapLoadedListener f2457ac;
    /* renamed from: ad */
    private OnMapClickListener f2458ad;
    /* renamed from: ae */
    private boolean f2459ae = false;
    /* renamed from: af */
    private OnMapScreenShotListener f2460af = null;
    /* renamed from: ag */
    private Timer f2461ag = null;
    /* renamed from: ah */
    private Thread f2462ah = null;
    /* renamed from: ai */
    private TimerTask f2463ai = new C09141();
    /* renamed from: aj */
    private Handler f2464aj = new Handler();
    /* renamed from: ak */
    private Handler f2465ak = new C09152();
    /* renamed from: al */
    private Point f2466al;
    /* renamed from: am */
    private GestureDetector f2467am;
    /* renamed from: an */
    private C0925a f2468an;
    /* renamed from: ao */
    private ArrayList<OnGestureListener> f2469ao = new ArrayList();
    /* renamed from: ap */
    private ArrayList<C0921b> f2470ap = new ArrayList();
    /* renamed from: aq */
    private Scroller f2471aq;
    /* renamed from: ar */
    private int f2472ar = 0;
    /* renamed from: as */
    private int f2473as = 0;
    /* renamed from: at */
    private Matrix f2474at = new Matrix();
    /* renamed from: au */
    private float f2475au = 1.0f;
    /* renamed from: av */
    private boolean f2476av = false;
    /* renamed from: aw */
    private float f2477aw;
    /* renamed from: ax */
    private float f2478ax;
    /* renamed from: ay */
    private int f2479ay;
    /* renamed from: az */
    private int f2480az;
    /* renamed from: b */
    public MapController f2481b;
    /* renamed from: c */
    float[] f2482c = new float[2];
    /* renamed from: d */
    boolean f2483d = false;
    /* renamed from: e */
    C0894ar f2484e = new C0894ar(this);
    /* renamed from: f */
    C0952cb f2485f;
    /* renamed from: g */
    public C0899at f2486g;
    /* renamed from: h */
    protected IUiSettingsDelegate f2487h;
    /* renamed from: i */
    public C0940br f2488i;
    /* renamed from: j */
    public C0897as f2489j;
    /* renamed from: k */
    final Handler f2490k = new C09163();
    /* renamed from: l */
    float f2491l = -1.0f;
    /* renamed from: m */
    private Context f2492m;
    /* renamed from: n */
    private boolean f2493n = false;
    /* renamed from: o */
    private boolean f2494o = true;
    /* renamed from: p */
    private Marker f2495p;
    /* renamed from: q */
    private IMarkerDelegate f2496q;
    /* renamed from: r */
    private final int[] f2497r = new int[]{10000000, 5000000, 2000000, 1000000, 500000, 200000, 100000, AutoNaviConnector.DEFAULT_SEARCH_RADIUS, 30000, 20000, 10000, 5000, ActivityTrace.MAX_TRACES, 1000, VTMCDataCache.MAXSIZE, 200, 100, 50, 25, 10, 5};
    /* renamed from: s */
    private boolean f2498s = true;
    /* renamed from: t */
    private int f2499t = 1;
    /* renamed from: u */
    private C0889ao f2500u;
    /* renamed from: v */
    private Location f2501v;
    /* renamed from: w */
    private C0946c f2502w;
    /* renamed from: x */
    private OnMyLocationChangeListener f2503x;
    /* renamed from: y */
    private boolean f2504y = true;
    /* renamed from: z */
    private C0926bb f2505z;

    /* compiled from: AMapDelegateImpGLSurfaceView */
    /* renamed from: com.amap.api.mapcore2d.b$1 */
    class C09141 extends TimerTask {
        C09141() {
        }

        public void run() {
            try {
                AMapDelegateImpGLSurfaceView.this.f2490k.sendEmptyMessage(19);
            } catch (Throwable th) {
                C0955ck.m3888a(th, "AMapDelegateImpGLSurfaceView", "TimerTask run");
            }
        }
    }

    /* compiled from: AMapDelegateImpGLSurfaceView */
    /* renamed from: com.amap.api.mapcore2d.b$2 */
    class C09152 extends Handler {
        /* renamed from: a */
        String f2412a = "onTouchHandler";

        C09152() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            try {
                if (AMapDelegateImpGLSurfaceView.this.f2455aa != null) {
                    AMapDelegateImpGLSurfaceView.this.f2455aa.onTouch((MotionEvent) message.obj);
                }
            } catch (Throwable th) {
                C0955ck.m3888a(th, "AMapDelegateImpGLSurfaceView", this.f2412a);
            }
        }
    }

    /* compiled from: AMapDelegateImpGLSurfaceView */
    /* renamed from: com.amap.api.mapcore2d.b$3 */
    class C09163 extends Handler {
        /* renamed from: a */
        String f2414a = "handleMessage";

        C09163() {
        }

        public void handleMessage(Message message) {
            if (message != null && AMapDelegateImpGLSurfaceView.this.f2448a != null && AMapDelegateImpGLSurfaceView.this.f2448a.f2384c != null) {
                Bitmap bitmap;
                try {
                    switch (message.what) {
                        case 2:
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Key验证失败：[");
                            if (message.obj != null) {
                                stringBuilder.append(message.obj);
                            } else {
                                stringBuilder.append(AuthConfigManager.f2732b);
                            }
                            stringBuilder.append("]");
                            Log.w("amapsdk", stringBuilder.toString());
                            return;
                        case 10:
                            if (AMapDelegateImpGLSurfaceView.this.f2431H != null) {
                                AMapDelegateImpGLSurfaceView.this.f2431H.onCameraChange(new CameraPosition(AMapDelegateImpGLSurfaceView.this.m3466ab(), AMapDelegateImpGLSurfaceView.this.mo9985f(), 0.0f, 0.0f));
                                return;
                            }
                            return;
                        case 11:
                            if (AMapDelegateImpGLSurfaceView.this.f2457ac != null) {
                                AMapDelegateImpGLSurfaceView.this.f2457ac.onMapLoaded();
                                return;
                            }
                            return;
                        case 13:
                            if (AMapDelegateImpGLSurfaceView.this.f2432I != null && AMapDelegateImpGLSurfaceView.this.f2432I.mo10300g()) {
                                switch (AMapDelegateImpGLSurfaceView.this.f2432I.mo10301h()) {
                                    case 2:
                                        CameraUpdateFactoryDelegate a = CameraUpdateFactoryDelegate.m4320a(new IPoint(AMapDelegateImpGLSurfaceView.this.f2432I.mo10295b(), AMapDelegateImpGLSurfaceView.this.f2432I.mo10296c()), AMapDelegateImpGLSurfaceView.this.f2432I.mo10297d(), AMapDelegateImpGLSurfaceView.this.f2432I.mo10298e(), AMapDelegateImpGLSurfaceView.this.f2432I.mo10299f());
                                        if (AMapDelegateImpGLSurfaceView.this.f2432I.mo10294a()) {
                                            a.f3004l = true;
                                        }
                                        AMapDelegateImpGLSurfaceView.this.f2484e.mo9815a(a);
                                        return;
                                    default:
                                        return;
                                }
                            }
                            return;
                        case 15:
                            AMapDelegateImpGLSurfaceView.this.m3465aa();
                            return;
                        case 16:
                            bitmap = (Bitmap) message.obj;
                            if (!bitmap.isRecycled()) {
                                bitmap = Bitmap.createBitmap(bitmap);
                                if (bitmap != null) {
                                    Canvas canvas = new Canvas(bitmap);
                                    if (AMapDelegateImpGLSurfaceView.this.f2424A != null) {
                                        AMapDelegateImpGLSurfaceView.this.f2424A.draw(canvas);
                                    }
                                    if (!(AMapDelegateImpGLSurfaceView.this.f2437N == null || AMapDelegateImpGLSurfaceView.this.f2440Q == null)) {
                                        Bitmap drawingCache = AMapDelegateImpGLSurfaceView.this.f2437N.getDrawingCache(true);
                                        if (drawingCache != null) {
                                            canvas.drawBitmap(drawingCache, (float) AMapDelegateImpGLSurfaceView.this.f2437N.getLeft(), (float) AMapDelegateImpGLSurfaceView.this.f2437N.getTop(), new Paint());
                                        }
                                    }
                                    if (AMapDelegateImpGLSurfaceView.this.f2460af != null) {
                                        AMapDelegateImpGLSurfaceView.this.f2460af.onMapScreenShot(bitmap);
                                    }
                                } else if (AMapDelegateImpGLSurfaceView.this.f2460af != null) {
                                    AMapDelegateImpGLSurfaceView.this.f2460af.onMapScreenShot(null);
                                }
                                AMapDelegateImpGLSurfaceView.this.destroyDrawingCache();
                                AMapDelegateImpGLSurfaceView.this.f2460af = null;
                                return;
                            }
                            return;
                        case 17:
                            CameraPosition h = AMapDelegateImpGLSurfaceView.this.m3451Z();
                            if (AMapDelegateImpGLSurfaceView.this.f2431H != null) {
                                AMapDelegateImpGLSurfaceView.this.m3463a(true, h);
                            }
                            if (C1042p.f3038h == null || C1042p.f3038h.trim().length() == 0) {
                                if (h.zoom < 10.0f || C0954cj.m3876a(h.target.latitude, h.target.longitude)) {
                                    AMapDelegateImpGLSurfaceView.this.f2424A.setVisibility(0);
                                } else {
                                    AMapDelegateImpGLSurfaceView.this.f2424A.setVisibility(8);
                                }
                            }
                            if (AMapDelegateImpGLSurfaceView.this.f2433J != null) {
                                AMapDelegateImpGLSurfaceView.this.f2429F = true;
                                AMapDelegateImpGLSurfaceView.this.f2433J.onFinish();
                                AMapDelegateImpGLSurfaceView.this.f2429F = false;
                            }
                            if (AMapDelegateImpGLSurfaceView.this.f2430G) {
                                AMapDelegateImpGLSurfaceView.this.f2430G = false;
                                return;
                            } else {
                                AMapDelegateImpGLSurfaceView.this.f2433J = null;
                                return;
                            }
                        case 19:
                            if (AMapDelegateImpGLSurfaceView.this.f2448a != null && AMapDelegateImpGLSurfaceView.this.f2448a.f2385d != null) {
                                AMapDelegateImpGLSurfaceView.this.f2448a.f2385d.mo9887a();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                } catch (Exception e) {
                    C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", this.f2414a);
                    bitmap = null;
                } catch (Throwable th) {
                    C0955ck.m3888a(th, "AMapDelegateImpGLSurfaceView", "handle_handleMessage");
                }
                C0955ck.m3888a(th, "AMapDelegateImpGLSurfaceView", "handle_handleMessage");
            }
        }
    }

    /* compiled from: AMapDelegateImpGLSurfaceView */
    /* renamed from: com.amap.api.mapcore2d.b$4 */
    class C09174 extends UrlFormater {
        C09174() {
        }

        /* renamed from: a */
        public String mo9873a(int i, int i2, int i3) {
            return MapServerUrl.m3230a().mo9863e() + "/appmaptile?z=" + i3 + "&x=" + i + "&y=" + i2 + "&lang=zh_cn&size=1&scale=1&style=6";
        }
    }

    /* compiled from: AMapDelegateImpGLSurfaceView */
    /* renamed from: com.amap.api.mapcore2d.b$5 */
    class C09185 extends UrlFormater {
        C09185() {
        }

        /* renamed from: a */
        public String mo9873a(int i, int i2, int i3) {
            return MapServerUrl.m3230a().mo9861c() + "/trafficengine/mapabc/traffictile?v=w2.61&zoom=" + (17 - i3) + "&x=" + i + "&y=" + i2;
        }
    }

    /* compiled from: AMapDelegateImpGLSurfaceView */
    /* renamed from: com.amap.api.mapcore2d.b$a */
    private static abstract class C0919a {
        /* renamed from: a */
        public abstract void mo9926a(int i, int i2, int i3, int i4);
    }

    /* compiled from: AMapDelegateImpGLSurfaceView */
    /* renamed from: com.amap.api.mapcore2d.b$b */
    private class C0920b implements Runnable {
        /* renamed from: b */
        private Context f2419b;
        /* renamed from: c */
        private OnCacheRemoveListener f2420c;

        public C0920b(Context context, OnCacheRemoveListener onCacheRemoveListener) {
            this.f2419b = context;
            this.f2420c = onCacheRemoveListener;
        }

        /* JADX WARNING: Missing block: B:32:?, code skipped:
            return;
     */
        public void run() {
            /*
            r4 = this;
            r1 = 1;
            r0 = new java.io.File;	 Catch:{ Throwable -> 0x001e }
            r2 = r4.f2419b;	 Catch:{ Throwable -> 0x001e }
            r2 = com.amap.api.mapcore2d.C0955ck.m3896b(r2);	 Catch:{ Throwable -> 0x001e }
            r0.<init>(r2);	 Catch:{ Throwable -> 0x001e }
            com.amap.api.mapcore2d.C0955ck.m3893a(r0);	 Catch:{ Throwable -> 0x001e }
            r0 = r4.f2420c;	 Catch:{ Throwable -> 0x0019 }
            if (r0 == 0) goto L_0x0018;
        L_0x0013:
            r0 = r4.f2420c;	 Catch:{ Throwable -> 0x0019 }
            r0.onRemoveCacheFinish(r1);	 Catch:{ Throwable -> 0x0019 }
        L_0x0018:
            return;
        L_0x0019:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x0018;
        L_0x001e:
            r0 = move-exception;
            r2 = "AMapDelegateImpGLSurfaceView";
            r3 = "RemoveCacheRunnable";
            com.amap.api.mapcore2d.C0990dd.m4098b(r0, r2, r3);	 Catch:{ all -> 0x0036 }
            r0 = 0;
            r1 = r4.f2420c;	 Catch:{ Throwable -> 0x0031 }
            if (r1 == 0) goto L_0x0018;
        L_0x002b:
            r1 = r4.f2420c;	 Catch:{ Throwable -> 0x0031 }
            r1.onRemoveCacheFinish(r0);	 Catch:{ Throwable -> 0x0031 }
            goto L_0x0018;
        L_0x0031:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x0018;
        L_0x0036:
            r0 = move-exception;
            r2 = r4.f2420c;	 Catch:{ Throwable -> 0x0041 }
            if (r2 == 0) goto L_0x0040;
        L_0x003b:
            r2 = r4.f2420c;	 Catch:{ Throwable -> 0x0041 }
            r2.onRemoveCacheFinish(r1);	 Catch:{ Throwable -> 0x0041 }
        L_0x0040:
            throw r0;
        L_0x0041:
            r1 = move-exception;
            r1.printStackTrace();
            goto L_0x0040;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.AMapDelegateImpGLSurfaceView$C0920b.run():void");
        }
    }

    /* renamed from: a */
    public Handler mo9939a() {
        return this.f2490k;
    }

    /* renamed from: b */
    public Mediator mo10022b() {
        return this.f2448a;
    }

    /* renamed from: c */
    public int mo9977c() {
        if (this.f2448a == null || this.f2448a.f2384c == null) {
            return 0;
        }
        return this.f2448a.f2384c.mo9903c();
    }

    /* renamed from: d */
    public int mo9981d() {
        if (this.f2448a == null || this.f2448a.f2384c == null) {
            return 0;
        }
        return this.f2448a.f2384c.mo9904d();
    }

    /* renamed from: e */
    public View mo9983e() throws RemoteException {
        return this.f2486g;
    }

    /* renamed from: a */
    public void mo9968a(boolean z) throws RemoteException {
    }

    /* renamed from: a */
    public void mo9950a(Location location) {
        String str = "showMyLocationOverlay";
        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            try {
                if (!mo9995n() || this.f2426C == null) {
                    this.f2505z.mo10057a();
                    this.f2505z = null;
                    return;
                }
                if (this.f2505z == null || this.f2501v == null) {
                    if (this.f2505z == null) {
                        this.f2505z = new C0926bb(this);
                    }
                    if (latLng != null) {
                        mo9951a(CameraUpdateFactoryDelegate.m4323a(latLng, this.f2448a.f2384c.mo9905e()));
                    }
                }
                this.f2505z.mo10059a(latLng, (double) location.getAccuracy());
                if (!(this.f2503x == null || (this.f2501v != null && this.f2501v.getBearing() == location.getBearing() && this.f2501v.getAccuracy() == location.getAccuracy() && this.f2501v.getLatitude() == location.getLatitude() && this.f2501v.getLongitude() == location.getLongitude()))) {
                    this.f2503x.onMyLocationChange(location);
                }
                this.f2501v = new Location(location);
            } catch (RemoteException e) {
                C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", str);
            }
        }
    }

    /* renamed from: a */
    public boolean mo9969a(String str) throws RemoteException {
        if (this.f2448a == null) {
            return false;
        }
        return this.f2448a.f2388g.mo10331b(str);
    }

    /* renamed from: b */
    public boolean mo9976b(String str) {
        IMarkerDelegate a;
        String str2 = "removeMarker";
        try {
            a = this.f2489j.mo9820a(str);
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", str2);
            a = null;
        }
        if (a != null) {
            return this.f2489j.mo9829b(a);
        }
        return false;
    }

    /* renamed from: a */
    public float mo10016a(float f) {
        if (f < ((float) this.f2448a.f2384c.mo9899b())) {
            f = (float) this.f2448a.f2384c.mo9899b();
        }
        if (f > ((float) this.f2448a.f2384c.mo9892a())) {
            return (float) this.f2448a.f2384c.mo9892a();
        }
        return f;
    }

    /* renamed from: f */
    public float mo9985f() {
        String str = "getZoomLevel";
        float f = 0.0f;
        if (this.f2448a == null || this.f2448a.f2384c == null) {
            return f;
        }
        try {
            return this.f2448a.f2384c.mo9905e();
        } catch (Exception e) {
            C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", str);
            return f;
        }
    }

    /* renamed from: g */
    public CameraPosition mo9987g() throws RemoteException {
        LatLng ab = m3466ab();
        if (ab == null) {
            return null;
        }
        return CameraPosition.builder().target(ab).zoom(mo9985f()).build();
    }

    /* renamed from: h */
    public float mo9989h() {
        if (this.f2448a == null || this.f2448a.f2384c == null) {
            return (float) C1042p.f3033c;
        }
        return (float) this.f2448a.f2384c.mo9892a();
    }

    /* renamed from: i */
    public float mo9990i() {
        if (this.f2448a == null || this.f2448a.f2384c == null) {
            return (float) C1042p.f3034d;
        }
        return (float) this.f2448a.f2384c.mo9899b();
    }

    /* renamed from: a */
    public void mo9951a(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) throws RemoteException {
        this.f2428E.mo9608a(cameraUpdateFactoryDelegate);
        mo9934Q();
    }

    /* renamed from: b */
    public void mo9974b(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) throws RemoteException {
        mo9953a(cameraUpdateFactoryDelegate, null);
    }

    /* renamed from: a */
    public void mo9953a(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate, CancelableCallback cancelableCallback) throws RemoteException {
        mo9952a(cameraUpdateFactoryDelegate, 250, cancelableCallback);
    }

    /* renamed from: a */
    public void mo9952a(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate, long j, CancelableCallback cancelableCallback) throws RemoteException {
        if (cameraUpdateFactoryDelegate.f2993a == C1036a.newLatLngBounds) {
            boolean z = getWidth() > 0 && getHeight() > 0;
            C0953ch.m3872a(z, (Object) "the map must have a size");
        }
        if (this.f2481b != null) {
            this.f2433J = cancelableCallback;
            if (this.f2481b.mo9798f()) {
                this.f2481b.mo9799g();
            }
            this.f2433J = cancelableCallback;
            if (this.f2429F) {
                this.f2430G = true;
            }
            CameraPosition cameraPosition;
            if (cameraUpdateFactoryDelegate.f2993a == C1036a.scrollBy) {
                mo10014M();
                if (this.f2448a != null && this.f2493n) {
                    this.f2481b.mo9791b((int) cameraUpdateFactoryDelegate.f2994b, (int) cameraUpdateFactoryDelegate.f2995c);
                    postInvalidate();
                }
            } else if (cameraUpdateFactoryDelegate.f2993a == C1036a.zoomIn) {
                this.f2481b.mo9794c();
            } else if (cameraUpdateFactoryDelegate.f2993a == C1036a.zoomOut) {
                this.f2481b.mo9796d();
            } else if (cameraUpdateFactoryDelegate.f2993a == C1036a.zoomTo) {
                this.f2481b.mo9793c(cameraUpdateFactoryDelegate.f2996d);
            } else if (cameraUpdateFactoryDelegate.f2993a == C1036a.zoomBy) {
                mo10018a(cameraUpdateFactoryDelegate.f2997e, cameraUpdateFactoryDelegate.f3003k, true);
            } else if (cameraUpdateFactoryDelegate.f2993a == C1036a.newCameraPosition) {
                cameraPosition = cameraUpdateFactoryDelegate.f2998f;
                this.f2481b.mo9793c(cameraPosition.zoom);
                this.f2481b.mo9785a(new GeoPoint((int) (cameraPosition.target.latitude * 1000000.0d), (int) (cameraPosition.target.longitude * 1000000.0d)), (int) j);
            } else if (cameraUpdateFactoryDelegate.f2993a == C1036a.changeCenter) {
                cameraPosition = cameraUpdateFactoryDelegate.f2998f;
                this.f2481b.mo9785a(new GeoPoint((int) (cameraPosition.target.latitude * 1000000.0d), (int) (cameraPosition.target.longitude * 1000000.0d)), (int) j);
            } else if (cameraUpdateFactoryDelegate.f2993a == C1036a.newLatLngBounds || cameraUpdateFactoryDelegate.f2993a == C1036a.newLatLngBoundsWithSize) {
                mo10014M();
                mo10020a(cameraUpdateFactoryDelegate, true, j);
            } else {
                cameraUpdateFactoryDelegate.f3004l = true;
                this.f2484e.mo9815a(cameraUpdateFactoryDelegate);
            }
        }
    }

    /* renamed from: a */
    public void mo10018a(float f, Point point, boolean z) {
        if (this.f2481b != null) {
            float f2 = mo9985f();
            if (C0955ck.m3895b(f2 + f) - f2 != 0.0f) {
                IPoint iPoint = new IPoint();
                iPoint = m3467ac();
                if (point != null) {
                    IPoint iPoint2 = new IPoint();
                    m3458a(point.x, point.y, iPoint2);
                    int i = iPoint.f2229a - iPoint2.f2229a;
                    int i2 = iPoint.f2230b - iPoint2.f2230b;
                    i2 = (int) ((((double) i2) / Math.pow(2.0d, (double) f)) - ((double) i2));
                    iPoint.f2229a = ((int) ((((double) i) / Math.pow(2.0d, (double) f)) - ((double) i))) + iPoint2.f2229a;
                    iPoint.f2230b = iPoint2.f2230b + i2;
                    GeoPoint b = this.f2448a.f2390i.mo9854b(new GeoPoint((double) iPoint.f2230b, (double) iPoint.f2229a, false));
                    if (z) {
                        this.f2481b.mo9785a(b, 1000);
                        return;
                    }
                    this.f2481b.mo9783a(b);
                    CameraChangeFinishObserver.m4312a().mo10303b();
                }
            }
        }
    }

    /* renamed from: j */
    public void mo9991j() throws RemoteException {
        if (this.f2481b != null) {
            if (!this.f2432I.mo10294a()) {
                this.f2432I.mo10293a(true);
                CameraChangeFinishObserver.m4312a().mo10303b();
                if (this.f2433J != null) {
                    this.f2433J.onCancel();
                }
                this.f2433J = null;
            }
            this.f2481b.mo9786a(true);
        }
    }

    /* renamed from: a */
    public IPolylineDelegate mo9941a(PolylineOptions polylineOptions) throws RemoteException {
        IOverlayDelegate c0929be = new C0929be(this);
        c0929be.mo9668a(polylineOptions.getColor());
        c0929be.mo9671b(polylineOptions.isDottedLine());
        c0929be.mo9672c(polylineOptions.isGeodesic());
        c0929be.mo9669a(polylineOptions.getPoints());
        c0929be.mo9650a(polylineOptions.isVisible());
        c0929be.mo9670b(polylineOptions.getWidth());
        c0929be.mo9648a(polylineOptions.getZIndex());
        if (this.f2448a == null) {
            return null;
        }
        mo10022b().f2388g.mo10329a(c0929be);
        invalidate();
        return c0929be;
    }

    /* renamed from: a */
    public ICircleDelegate mo9942a(CircleOptions circleOptions) throws RemoteException {
        IOverlayDelegate c1037n = new C1037n(this);
        c1037n.mo10312b(circleOptions.getFillColor());
        c1037n.mo10310a(circleOptions.getCenter());
        c1037n.mo9650a(circleOptions.isVisible());
        c1037n.mo10311b(circleOptions.getStrokeWidth());
        c1037n.mo9648a(circleOptions.getZIndex());
        c1037n.mo10309a(circleOptions.getStrokeColor());
        c1037n.mo10308a(circleOptions.getRadius());
        if (this.f2448a == null) {
            return null;
        }
        this.f2448a.f2388g.mo10329a(c1037n);
        invalidate();
        return c1037n;
    }

    /* renamed from: a */
    public IGroundOverlayDelegate mo9943a(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
        IOverlayDelegate c1048v = new C1048v(this);
        c1048v.mo10356b(groundOverlayOptions.getAnchorU(), groundOverlayOptions.getAnchorV());
        c1048v.mo10348c(groundOverlayOptions.getBearing());
        c1048v.mo10343a(groundOverlayOptions.getWidth(), groundOverlayOptions.getHeight());
        c1048v.mo10344a(groundOverlayOptions.getImage());
        c1048v.mo10345a(groundOverlayOptions.getLocation());
        c1048v.mo10346a(groundOverlayOptions.getBounds());
        c1048v.mo10349d(groundOverlayOptions.getTransparency());
        c1048v.mo9650a(groundOverlayOptions.isVisible());
        c1048v.mo9648a(groundOverlayOptions.getZIndex());
        if (this.f2448a == null) {
            return null;
        }
        this.f2448a.f2388g.mo10329a(c1048v);
        invalidate();
        return c1048v;
    }

    /* renamed from: a */
    public IPolygonDelegate mo9940a(PolygonOptions polygonOptions) throws RemoteException {
        IOverlayDelegate c0928bd = new C0928bd(this);
        c0928bd.mo9659a(polygonOptions.getFillColor());
        c0928bd.mo9660a(polygonOptions.getPoints());
        c0928bd.mo9650a(polygonOptions.isVisible());
        c0928bd.mo9662b(polygonOptions.getStrokeWidth());
        c0928bd.mo9648a(polygonOptions.getZIndex());
        c0928bd.mo9663b(polygonOptions.getStrokeColor());
        if (this.f2448a == null) {
            return null;
        }
        this.f2448a.f2388g.mo10329a(c0928bd);
        invalidate();
        return c0928bd;
    }

    /* renamed from: a */
    public Marker mo9944a(MarkerOptions markerOptions) throws RemoteException {
        IMarkerDelegate c0903ax = new C0903ax(markerOptions, this.f2489j);
        this.f2489j.mo9823a(c0903ax);
        invalidate();
        return new Marker(c0903ax);
    }

    /* renamed from: b */
    public C0903ax mo9970b(MarkerOptions markerOptions) throws RemoteException {
        IMarkerDelegate c0903ax = new C0903ax(markerOptions, this.f2489j);
        this.f2489j.mo9823a(c0903ax);
        invalidate();
        return c0903ax;
    }

    /* renamed from: a */
    public TileOverlay mo9946a(TileOverlayOptions tileOverlayOptions) throws RemoteException {
        if (this.f2448a == null) {
            return null;
        }
        ITileOverlayDelegate c0937bq = new C0937bq(tileOverlayOptions, this.f2488i, this.f2448a.f2390i, this.f2448a, this.f2492m);
        this.f2488i.mo10101a(c0937bq);
        invalidate();
        return new TileOverlay(c0937bq);
    }

    /* renamed from: k */
    public void mo9992k() throws RemoteException {
        String str = "clear";
        try {
            mo10053t();
            if (this.f2448a != null) {
                this.f2448a.f2388g.mo10327a();
                this.f2489j.mo9830c();
                this.f2488i.mo10104b();
                if (this.f2505z != null) {
                    this.f2505z.mo10057a();
                }
                invalidate();
            }
        } catch (Exception e) {
            C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", str);
            Log.d("amapApi", "AMapDelegateImpGLSurfaceView clear erro" + e.getMessage());
        } catch (Throwable th) {
            C0955ck.m3888a(th, "AMapDelegateImpGLSurfaceView", str);
        }
    }

    /* renamed from: l */
    public int mo9993l() throws RemoteException {
        return this.f2499t;
    }

    /* renamed from: a */
    public void mo9948a(int i) throws RemoteException {
        if (i == 2) {
            this.f2499t = 2;
            mo10030h(true);
            this.f2424A.mo10140a(true);
        } else {
            this.f2499t = 1;
            mo10030h(false);
            this.f2424A.mo10140a(false);
        }
        postInvalidate();
    }

    /* renamed from: m */
    public boolean mo9994m() throws RemoteException {
        return mo10009F();
    }

    /* renamed from: b */
    public void mo9975b(boolean z) throws RemoteException {
        mo10031i(z);
        postInvalidate();
    }

    /* renamed from: n */
    public boolean mo9995n() throws RemoteException {
        return this.f2504y;
    }

    /* renamed from: c */
    public void mo9980c(boolean z) throws RemoteException {
        if (this.f2426C == null) {
            this.f2500u.mo9767a(false);
        } else if (z) {
            this.f2426C.activate(this.f2502w);
            this.f2500u.mo9767a(true);
            if (this.f2505z == null) {
                this.f2505z = new C0926bb(this);
            }
        } else {
            if (this.f2505z != null) {
                this.f2505z.mo10057a();
                this.f2505z = null;
            }
            this.f2501v = null;
            this.f2426C.deactivate();
            this.f2500u.mo9767a(false);
        }
        if (z) {
            this.f2500u.setVisibility(0);
        } else {
            this.f2500u.setVisibility(8);
        }
        this.f2504y = z;
    }

    /* renamed from: b */
    public void mo9972b(float f) throws RemoteException {
        if (this.f2505z != null) {
            this.f2505z.mo10058a(f);
        }
    }

    /* renamed from: a */
    public void mo9967a(MyLocationStyle myLocationStyle) throws RemoteException {
        if (mo10032o() != null) {
            mo10032o().mo10060a(myLocationStyle);
        }
    }

    /* renamed from: o */
    public C0926bb mo10032o() {
        return this.f2505z;
    }

    /* renamed from: p */
    public Location mo9996p() throws RemoteException {
        if (this.f2426C != null) {
            return this.f2502w.f2659a;
        }
        return null;
    }

    /* renamed from: a */
    public void mo9966a(LocationSource locationSource) throws RemoteException {
        this.f2426C = locationSource;
        if (locationSource != null) {
            this.f2500u.mo9767a(true);
        } else {
            this.f2500u.mo9767a(false);
        }
    }

    /* renamed from: q */
    public IUiSettingsDelegate mo9997q() throws RemoteException {
        return this.f2487h;
    }

    /* renamed from: r */
    public IProjectionDelegate mo9998r() {
        return this.f2443T;
    }

    /* renamed from: s */
    public Projection mo9999s() {
        if (this.f2448a == null) {
            return null;
        }
        return this.f2448a.f2383b;
    }

    /* renamed from: a */
    public void mo9958a(OnMapClickListener onMapClickListener) throws RemoteException {
        this.f2458ad = onMapClickListener;
    }

    /* renamed from: a */
    public void mo9962a(OnMapTouchListener onMapTouchListener) throws RemoteException {
        this.f2455aa = onMapTouchListener;
    }

    /* renamed from: a */
    public void mo9960a(OnMapLongClickListener onMapLongClickListener) throws RemoteException {
        this.f2456ab = onMapLongClickListener;
    }

    /* renamed from: a */
    public void mo9963a(OnMarkerClickListener onMarkerClickListener) throws RemoteException {
        this.f2441R = onMarkerClickListener;
    }

    /* renamed from: a */
    public void mo9964a(OnMarkerDragListener onMarkerDragListener) throws RemoteException {
        this.f2447Z = onMarkerDragListener;
    }

    /* renamed from: a */
    public void mo9959a(OnMapLoadedListener onMapLoadedListener) throws RemoteException {
        this.f2457ac = onMapLoadedListener;
    }

    /* renamed from: a */
    public void mo9957a(OnInfoWindowClickListener onInfoWindowClickListener) throws RemoteException {
        this.f2438O = onInfoWindowClickListener;
    }

    /* renamed from: a */
    public void mo9954a(InfoWindowAdapter infoWindowAdapter) throws RemoteException {
        this.f2439P = infoWindowAdapter;
    }

    /* renamed from: a */
    public void mo10019a(IMarkerDelegate iMarkerDelegate) throws RemoteException {
        int i = -2;
        String str = "showInfoWindow";
        if (iMarkerDelegate != null) {
            if (iMarkerDelegate.mo9636f() != null || iMarkerDelegate.mo9637g() != null) {
                int i2;
                mo10053t();
                Marker marker = new Marker(iMarkerDelegate);
                if (this.f2439P != null) {
                    this.f2437N = this.f2439P.getInfoWindow(marker);
                }
                try {
                    if (this.f2442S == null) {
                        this.f2442S = C0927bc.m3607a(this.f2492m, "infowindow_bg2d.9.png");
                    }
                } catch (Exception e) {
                    C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", str);
                }
                if (this.f2437N == null && this.f2439P != null) {
                    this.f2437N = this.f2439P.getInfoContents(marker);
                }
                if (this.f2437N == null) {
                    LinearLayout linearLayout = new LinearLayout(this.f2492m);
                    linearLayout.setBackgroundDrawable(this.f2442S);
                    TextView textView = new TextView(this.f2492m);
                    textView.setText(iMarkerDelegate.mo9636f());
                    textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    TextView textView2 = new TextView(this.f2492m);
                    textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    textView2.setText(iMarkerDelegate.mo9637g());
                    linearLayout.setOrientation(1);
                    linearLayout.addView(textView);
                    linearLayout.addView(textView2);
                    this.f2437N = linearLayout;
                } else if (this.f2437N.getBackground() == null) {
                    this.f2437N.setBackgroundDrawable(this.f2442S);
                }
                LayoutParams layoutParams = this.f2437N.getLayoutParams();
                this.f2437N.setDrawingCacheEnabled(true);
                this.f2437N.setDrawingCacheQuality(0);
                C1044r e2 = iMarkerDelegate.mo9635e();
                if (layoutParams != null) {
                    i2 = layoutParams.width;
                    i = layoutParams.height;
                } else {
                    i2 = -2;
                }
                C0898a c0898a = new C0898a(i2, i, iMarkerDelegate.mo9633c(), (-((int) e2.f3048a)) + (iMarkerDelegate.mo9644n() / 2), (-((int) e2.f3049b)) + 2, 81);
                this.f2440Q = (C0903ax) iMarkerDelegate;
                this.f2486g.addView(this.f2437N, c0898a);
            }
        }
    }

    /* renamed from: b */
    public boolean mo10025b(IMarkerDelegate iMarkerDelegate) {
        if (this.f2440Q == null || this.f2437N == null) {
            return false;
        }
        return this.f2440Q.mo9634d().equals(iMarkerDelegate.mo9634d());
    }

    /* renamed from: t */
    public void mo10053t() {
        if (this.f2437N != null) {
            this.f2437N.clearFocus();
            this.f2437N.destroyDrawingCache();
            this.f2486g.removeView(this.f2437N);
            Drawable background = this.f2437N.getBackground();
            if (background != null) {
                background.setCallback(null);
            }
            this.f2437N = null;
        }
        this.f2440Q = null;
    }

    /* renamed from: u */
    public void mo10054u() {
        if (this.f2437N != null && this.f2440Q != null) {
            C0898a c0898a = (C0898a) this.f2437N.getLayoutParams();
            if (c0898a != null) {
                c0898a.f2296b = this.f2440Q.mo9633c();
            }
            this.f2486g.mo9839a();
        }
    }

    /* renamed from: a */
    public void mo9965a(OnMyLocationChangeListener onMyLocationChangeListener) throws RemoteException {
        this.f2503x = onMyLocationChangeListener;
    }

    /* renamed from: d */
    public void mo9982d(boolean z) {
        if (z) {
            this.f2485f.setVisibility(0);
        } else {
            this.f2485f.setVisibility(8);
        }
    }

    /* renamed from: e */
    public void mo9984e(boolean z) {
        if (z) {
            this.f2500u.setVisibility(0);
        } else {
            this.f2500u.setVisibility(8);
        }
    }

    /* renamed from: f */
    public void mo9986f(boolean z) {
        if (z) {
            this.f2427D.setVisibility(0);
        } else {
            this.f2427D.setVisibility(8);
        }
    }

    /* renamed from: g */
    public void mo9988g(boolean z) {
        if (z) {
            this.f2425B.setVisibility(0);
            mo10015N();
            return;
        }
        this.f2425B.mo10082a("");
        this.f2425B.mo10081a(0);
        this.f2425B.setVisibility(8);
    }

    /* renamed from: v */
    public void mo10000v() {
        String str = "destroy";
        try {
            if (this.f2461ag != null) {
                this.f2461ag.cancel();
                this.f2461ag = null;
            }
            if (this.f2463ai != null) {
                this.f2463ai.cancel();
                this.f2463ai = null;
            }
            if (this.f2465ak != null) {
                this.f2465ak.removeCallbacksAndMessages(null);
            }
            if (this.f2490k != null) {
                this.f2490k.removeCallbacksAndMessages(null);
            }
            CancelAnimObserver.m4331a().mo10307b(this);
            StopAnimObserver.m3684a().mo10084a(this);
            CameraChangeFinishObserver.m4312a().mo10304b(this);
            this.f2485f.mo10148a();
            this.f2425B.mo10080a();
            this.f2424A.mo10138a();
            this.f2500u.mo9766a();
            this.f2427D.mo10321a();
            this.f2448a.f2388g.mo10330b();
            this.f2489j.mo9836f();
            if (this.f2442S != null) {
                this.f2442S.setCallback(null);
            }
            this.f2486g.removeAllViews();
            mo10053t();
            if (this.f2488i != null) {
                this.f2488i.mo10109f();
            }
            if (this.f2448a != null) {
                this.f2448a.f2385d.mo9888b();
                m3448W();
            }
            this.f2426C = null;
            this.f2458ad = null;
            C1042p.f3038h = null;
            C1042p.f3037g = null;
            C0990dd.m4097b();
        } catch (Exception e) {
            C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", str);
        }
    }

    /* renamed from: a */
    public void mo9961a(OnMapScreenShotListener onMapScreenShotListener) {
        this.f2460af = onMapScreenShotListener;
        this.f2444U = true;
    }

    /* renamed from: b */
    public void mo9973b(int i) {
        if (this.f2424A != null) {
            this.f2424A.mo10139a(i);
            this.f2424A.invalidate();
            if (this.f2425B.getVisibility() == 0) {
                this.f2425B.invalidate();
            }
        }
    }

    /* renamed from: c */
    public void mo9978c(int i) {
        if (this.f2485f != null) {
            this.f2485f.mo10150a(i);
            this.f2485f.invalidate();
        }
    }

    /* renamed from: w */
    public float mo10001w() {
        int width = getWidth();
        C1044r c1044r = new C1044r();
        C1044r c1044r2 = new C1044r();
        mo9949a(0, 0, c1044r);
        mo9949a(width, 0, c1044r2);
        return (float) (C0955ck.m3878a(new LatLng(c1044r.f3049b, c1044r.f3048a), new LatLng(c1044r2.f3049b, c1044r2.f3048a)) / ((double) width));
    }

    /* renamed from: x */
    public LatLngBounds mo10055x() {
        return null;
    }

    /* renamed from: y */
    public void mo10002y() {
        if (this.f2448a != null) {
            this.f2448a.f2385d.mo9889c();
        }
        if (this.f2488i != null) {
            this.f2488i.mo10108e();
        }
    }

    /* renamed from: z */
    public void mo10003z() {
        if (this.f2448a != null) {
            this.f2448a.f2385d.mo9890d();
        }
        if (this.f2488i != null) {
            this.f2488i.mo10107d();
        }
    }

    /* renamed from: U */
    private void m3446U() {
        String str = "setLayerType";
        for (Method method : View.class.getMethods()) {
            if (method.getName().equals("setLayerType")) {
                break;
            }
        }
        Method method2 = null;
        if (method2 != null) {
            try {
                Field field = View.class.getField("LAYER_TYPE_SOFTWARE");
                method2.invoke(this, new Object[]{Integer.valueOf(field.getInt(null)), null});
            } catch (Exception e) {
                C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", str);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: A */
    public Point mo10004A() {
        return this.f2424A.mo10142c();
    }

    public AMapDelegateImpGLSurfaceView(Context context) {
        super(context);
        m3446U();
        setClickable(true);
        m3460a(context, null);
    }

    /* renamed from: a */
    private void m3460a(Context context, AttributeSet attributeSet) {
        C1042p.f3032b = C0957cm.m3903c(context);
        String str = "initEnviornment";
        this.f2492m = context;
        this.f2462ah = new AuthTask(this.f2492m, this);
        this.f2443T = new C0930bg(this);
        setBackgroundColor(Color.rgb(222, 215, 214));
        CancelAnimObserver.m4331a().mo10305a(this);
        CameraChangeFinishObserver.m4312a().mo10302a(this);
        this.f2428E = new C0883a(this);
        this.f2502w = new C0946c(this);
        this.f2432I = new CameraAnimator(context);
        this.f2488i = new C0940br(this.f2492m, this);
        this.f2448a = new Mediator(this.f2492m, this, C1042p.f3039i);
        this.f2488i.mo10102a(true);
        this.f2434K = this.f2448a.f2390i;
        this.f2481b = new MapController(this.f2448a);
        this.f2487h = new C0945bx(this);
        this.f2485f = new C0952cb(this.f2492m, this.f2481b, this);
        this.f2486g = new C0899at(this.f2492m, this);
        this.f2500u = new C0889ao(this.f2492m, this.f2484e, this);
        this.f2424A = new C0947ca(this.f2492m, this);
        this.f2425B = new C0933bi(this.f2492m, this);
        this.f2427D = new C1040o(this.f2492m, this.f2484e, this);
        this.f2489j = new C0897as(this.f2492m, attributeSet, this);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        m3447V();
        this.f2486g.addView(this.f2488i, layoutParams);
        this.f2486g.addView(this.f2424A, layoutParams);
        this.f2486g.addView(this.f2425B, layoutParams);
        this.f2486g.addView(this.f2489j, new C0898a(layoutParams));
        this.f2486g.addView(this.f2485f, new C0898a(-2, -2, new LatLng(0.0d, 0.0d), 0, 0, 83));
        this.f2486g.addView(this.f2500u, new C0898a(-2, -2, new LatLng(0.0d, 0.0d), 0, 0, 83));
        try {
            if (!mo9997q().mo9723d()) {
                this.f2500u.setVisibility(8);
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", str);
        }
        this.f2427D.setVisibility(8);
        this.f2486g.addView(this.f2427D, new C0898a(-2, -2, new LatLng(0.0d, 0.0d), 0, 0, 51));
        this.f2505z = new C0926bb(this);
        this.f2485f.setId(AutoTestConfig.f2928a);
        try {
            this.f2462ah.setName("AuthThread");
            this.f2462ah.start();
            if (this.f2461ag == null) {
                this.f2461ag = new Timer();
                this.f2461ag.schedule(this.f2463ai, 10000, 1000);
            }
        } catch (Throwable th) {
            C0955ck.m3888a(th, "AMapDelegateImpGLSurfaceView", str);
        }
    }

    /* renamed from: B */
    public boolean mo10005B() {
        return this.f2494o;
    }

    /* renamed from: C */
    public GeoPoint mo10006C() {
        if (this.f2448a == null || this.f2448a.f2384c == null) {
            return null;
        }
        return this.f2448a.f2384c.mo9906f();
    }

    /* renamed from: D */
    public MapController mo10007D() {
        return this.f2481b;
    }

    /* renamed from: V */
    private void m3447V() {
        m3459a(this.f2492m);
        this.f2486g.addView(this, 0, new LayoutParams(-1, -1));
    }

    /* renamed from: h */
    public void mo10030h(boolean z) {
        if (mo10008E() != z) {
            if (!z) {
                mo10022b().f2386e.mo9881a(mo10022b().f2386e.f2362h, false);
                mo10022b().f2386e.mo9881a(mo10022b().f2386e.f2361g, true);
                mo10022b().f2384c.mo9898a(false, false);
            } else if (this.f2448a == null) {
            } else {
                if (mo10022b().f2386e.mo9882b(mo10022b().f2386e.f2362h) != null) {
                    mo10022b().f2386e.mo9881a(mo10022b().f2386e.f2362h, true);
                    mo10022b().f2384c.mo9898a(false, false);
                    return;
                }
                C0886am c0886am = new C0886am(this.f2434K);
                c0886am.f2236q = new TileServer(this.f2448a, this.f2492m, c0886am);
                c0886am.f2246j = new C09174();
                c0886am.f2238b = mo10022b().f2386e.f2362h;
                c0886am.f2241e = true;
                c0886am.mo9758a(true);
                c0886am.f2242f = true;
                c0886am.f2239c = C1042p.f3033c;
                c0886am.f2240d = C1042p.f3034d;
                mo10022b().f2386e.mo9880a(c0886am, getContext());
                mo10022b().f2386e.mo9881a(mo10022b().f2386e.f2362h, true);
                mo10022b().f2384c.mo9898a(false, false);
            }
        }
    }

    /* renamed from: E */
    public boolean mo10008E() {
        if (this.f2448a == null || this.f2448a.f2386e == null) {
            return false;
        }
        C0886am b = mo10022b().f2386e.mo9882b(mo10022b().f2386e.f2362h);
        if (b != null) {
            return b.mo9759a();
        }
        return false;
    }

    /* renamed from: i */
    public void mo10031i(boolean z) {
        if (z != mo10009F()) {
            String str = "";
            if (this.f2448a != null) {
                str = mo10022b().f2386e.f2363i;
                if (!z) {
                    mo10022b().f2386e.mo9881a(str, false);
                    mo10022b().f2384c.mo9898a(false, false);
                } else if (mo10022b().f2386e.mo9882b(str) != null) {
                    mo10022b().f2386e.mo9881a(str, true);
                    mo10022b().f2384c.mo9898a(false, false);
                } else {
                    C0886am c0886am = new C0886am(this.f2434K);
                    c0886am.f2236q = new TileServer(this.f2448a, this.f2492m, c0886am);
                    c0886am.f2243g = true;
                    c0886am.f2245i = PayloadController.PAYLOAD_REQUEUE_PERIOD_MS;
                    c0886am.f2246j = new C09185();
                    c0886am.f2238b = str;
                    c0886am.f2241e = false;
                    c0886am.mo9758a(true);
                    c0886am.f2242f = false;
                    c0886am.f2239c = 18;
                    c0886am.f2240d = 9;
                    mo10022b().f2386e.mo9880a(c0886am, getContext());
                    mo10022b().f2386e.mo9881a(str, true);
                    mo10022b().f2384c.mo9898a(false, false);
                }
            }
        }
    }

    /* renamed from: F */
    public boolean mo10009F() {
        String str = "";
        if (mo10022b() == null) {
            return false;
        }
        C0886am b = mo10022b().f2386e.mo9882b(mo10022b().f2386e.f2363i);
        if (b != null) {
            return b.mo9759a();
        }
        return false;
    }

    /* renamed from: W */
    private void m3448W() {
        this.f2448a.mo9913a();
        if (this.f2481b != null) {
            this.f2481b.mo9786a(true);
            this.f2481b.mo9797e();
        }
        this.f2481b = null;
        this.f2448a = null;
    }

    /* renamed from: X */
    private void m3449X() {
        CameraUpdateFactoryDelegate a;
        if (this.f2435L) {
            this.f2435L = false;
        }
        if (this.f2446W) {
            this.f2446W = false;
            a = CameraUpdateFactoryDelegate.m4316a();
            a.f3004l = true;
            this.f2484e.mo9815a(a);
        }
        if (this.f2436M) {
            this.f2436M = false;
            a = CameraUpdateFactoryDelegate.m4316a();
            a.f3004l = true;
            this.f2484e.mo9815a(a);
        }
        this.f2445V = false;
        if (this.f2447Z != null && this.f2495p != null) {
            this.f2447Z.onMarkerDragEnd(this.f2495p);
            this.f2495p = null;
            this.f2496q = null;
        }
    }

    /* renamed from: a */
    private void m3461a(MotionEvent motionEvent) {
        if (this.f2445V && this.f2496q != null && this.f2495p != null) {
            int x = (int) motionEvent.getX();
            int y = (int) (motionEvent.getY() - 60.0f);
            C1044r c1044r = new C1044r();
            mo9949a(x, y, c1044r);
            LatLng latLng = new LatLng(c1044r.f3049b, c1044r.f3048a);
            if (this.f2496q != null && this.f2496q.mo9638h()) {
                this.f2496q.mo9624a(latLng);
                if (this.f2447Z != null) {
                    this.f2447Z.onMarkerDrag(this.f2495p);
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
    }

    /* Access modifiers changed, original: protected */
    public Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void computeScroll() {
        if (this.f2471aq.computeScrollOffset()) {
            int currX = this.f2471aq.getCurrX() - this.f2472ar;
            int currY = this.f2471aq.getCurrY() - this.f2473as;
            this.f2472ar = this.f2471aq.getCurrX();
            this.f2473as = this.f2471aq.getCurrY();
            GeoPoint a = this.f2448a.f2383b.mo9909a(currX + this.f2448a.f2390i.f2318n.x, currY + this.f2448a.f2390i.f2318n.y);
            if (this.f2471aq.isFinished()) {
                CameraChangeFinishObserver.m4312a().mo10303b();
                if (this.f2431H != null) {
                    m3463a(true, m3451Z());
                }
                this.f2448a.f2384c.mo9898a(false, false);
                return;
            }
            this.f2448a.f2384c.mo9902b(a);
            return;
        }
        super.computeScroll();
    }

    public void setClickable(boolean z) {
        this.f2493n = z;
        super.setClickable(z);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.f2448a == null) {
            return true;
        }
        if (!this.f2493n) {
            return false;
        }
        if (this.f2448a.f2386e.mo9878a(i, keyEvent) || this.f2481b.onKey(this, i, keyEvent)) {
            return true;
        }
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.f2448a == null) {
            return true;
        }
        if (!this.f2493n) {
            return false;
        }
        if (this.f2448a.f2386e.mo9885b(i, keyEvent) || this.f2481b.onKey(this, i, keyEvent)) {
            return true;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!C1042p.f3046p || this.f2448a == null) {
            return true;
        }
        if (!this.f2493n) {
            return false;
        }
        if (this.f2455aa != null) {
            this.f2465ak.removeMessages(1);
            Message obtainMessage = this.f2465ak.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = MotionEvent.obtain(motionEvent);
            obtainMessage.sendToTarget();
        }
        if (this.f2448a.f2386e.mo9879a(motionEvent)) {
            return true;
        }
        m3469b(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    /* Access modifiers changed, original: protected|final */
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f2448a.f2390i.mo9849a(new Point(i / 2, i2 / 2));
        this.f2448a.f2384c.mo9895a(i, i2);
        if (!(this.f2481b.mo9778a() == 0.0f || this.f2481b.mo9789b() == 0.0f)) {
            this.f2481b.mo9780a(this.f2481b.mo9778a(), this.f2481b.mo9789b());
            this.f2481b.mo9779a(0.0f);
            this.f2481b.mo9790b(0.0f);
        }
        mo10054u();
        if (this.f2454aI != null) {
            this.f2454aI.mo9926a(i, i2, i3, i4);
        }
    }

    /* renamed from: a */
    private void m3459a(Context context) {
        this.f2466al = null;
        this.f2467am = new GestureDetector(context, this);
        this.f2468an = MultiTouchGestureDetector.m3589a(context, this);
        this.f2471aq = new Scroller(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        displayMetrics = context.getApplicationContext().getResources().getDisplayMetrics();
        this.f2479ay = displayMetrics.widthPixels;
        this.f2480az = displayMetrics.heightPixels;
        this.f2472ar = displayMetrics.widthPixels / 2;
        this.f2473as = displayMetrics.heightPixels / 2;
    }

    /* renamed from: G */
    public MultiTouchGestureDetector mo10010G() {
        return this.f2468an;
    }

    /* renamed from: H */
    public static int m3444H() {
        return f2421aD;
    }

    /* renamed from: I */
    public static synchronized Paint m3445I() {
        Paint paint;
        synchronized (AMapDelegateImpGLSurfaceView.class) {
            if (f2422aE == null) {
                f2422aE = new Paint();
                f2422aE.setColor(-7829368);
                f2422aE.setAlpha(90);
                f2422aE.setPathEffect(new DashPathEffect(new float[]{2.0f, 2.5f}, 1.0f));
            }
            paint = f2422aE;
        }
        return paint;
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        int i;
        Paint I = AMapDelegateImpGLSurfaceView.m3445I();
        canvas.drawColor(AMapDelegateImpGLSurfaceView.m3444H());
        int width = getWidth();
        int height = getHeight();
        if (width > height) {
            i = width;
        } else {
            i = height;
        }
        float left = (float) getLeft();
        float top = (float) getTop();
        for (int i2 = 0; i2 < i; i2 += 256) {
            canvas.drawLine(left, (float) i2, left + ((float) getWidth()), (float) i2, I);
            canvas.drawLine((float) i2, top, (float) i2, top + ((float) getHeight()), I);
        }
        if (this.f2444U) {
            setDrawingCacheEnabled(true);
            buildDrawingCache();
            Bitmap drawingCache = getDrawingCache();
            Message obtainMessage = this.f2490k.obtainMessage();
            obtainMessage.what = 16;
            obtainMessage.obj = drawingCache;
            this.f2490k.sendMessage(obtainMessage);
            this.f2444U = false;
        }
        this.f2448a.f2384c.mo9895a(getWidth(), getHeight());
        this.f2448a.f2386e.mo9875a(canvas, this.f2474at, this.f2477aw, this.f2478ax);
        if (!this.f2432I.mo10294a()) {
            this.f2490k.sendEmptyMessage(13);
        }
        if (!this.f2459ae) {
            this.f2490k.sendEmptyMessage(11);
            this.f2459ae = true;
        }
    }

    /* renamed from: c */
    public void mo10027c(float f) {
        this.f2475au = f;
    }

    /* renamed from: J */
    public float mo10011J() {
        return this.f2475au;
    }

    /* renamed from: K */
    public void mo10012K() {
        this.f2477aw = 0.0f;
        this.f2478ax = 0.0f;
    }

    /* renamed from: Y */
    private void m3450Y() {
        if (this.f2466al != null) {
            int i = this.f2466al.x - this.f2450aB;
            int i2 = this.f2466al.y - this.f2451aC;
            this.f2466al.x = this.f2450aB;
            this.f2466al.y = this.f2451aC;
            this.f2481b.mo9791b(i, i2);
        }
    }

    /* renamed from: a */
    private void m3457a(int i, int i2) {
        if (this.f2466al != null) {
            this.f2450aB = i;
            this.f2451aC = i2;
            m3450Y();
        }
    }

    /* renamed from: b */
    private boolean m3469b(MotionEvent motionEvent) {
        boolean a = this.f2468an.mo10056a(motionEvent, getWidth(), getHeight());
        if (!a) {
            a = this.f2467am.onTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 1 && this.f2446W) {
            CameraChangeFinishObserver.m4312a().mo10303b();
        }
        if (motionEvent.getAction() == 2) {
            m3461a(motionEvent);
        }
        if (motionEvent.getAction() == 1) {
            m3449X();
        }
        return a;
    }

    /* renamed from: Z */
    private CameraPosition m3451Z() {
        GeoPoint C = mo10006C();
        if (C == null) {
            return null;
        }
        return CameraPosition.fromLatLngZoom(new LatLng(((double) C.mo10334b()) / 1000000.0d, ((double) C.mo10332a()) / 1000000.0d), mo9985f());
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.f2446W = false;
        if (!(this.f2453aH || this.f2432I.mo10294a())) {
            this.f2432I.mo10293a(true);
            if (this.f2433J != null) {
                this.f2433J.onCancel();
            }
            this.f2433J = null;
        }
        this.f2453aH = false;
        this.f2452aG = 0;
        if (this.f2466al == null) {
            this.f2466al = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
        } else {
            this.f2466al.set((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        return true;
    }

    /* JADX WARNING: Missing block: B:8:0x0025, code skipped:
            if (r10.f2487h.mo9725e() != false) goto L_0x0027;
     */
    public boolean onFling(android.view.MotionEvent r11, android.view.MotionEvent r12, float r13, float r14) {
        /*
        r10 = this;
        r9 = 1;
        r1 = "onFling";
        r0 = r10.f2468an;
        r0 = r0.f2520k;
        if (r0 != 0) goto L_0x0018;
    L_0x0009:
        r2 = r11.getEventTime();
        r0 = r10.f2468an;
        r4 = r0.f2524o;
        r2 = r2 - r4;
        r4 = 30;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 >= 0) goto L_0x0019;
    L_0x0018:
        return r9;
    L_0x0019:
        r10.invalidate();
        r0 = 0;
        r10.f2446W = r0;
        r0 = r10.f2487h;	 Catch:{ RemoteException -> 0x004a }
        r0 = r0.mo9725e();	 Catch:{ RemoteException -> 0x004a }
        if (r0 == 0) goto L_0x0018;
    L_0x0027:
        r0 = 0;
        r10.f2433J = r0;
        r0 = r10.f2471aq;
        r1 = r10.f2472ar;
        r2 = r10.f2473as;
        r3 = -r13;
        r3 = (int) r3;
        r3 = r3 * 3;
        r3 = r3 / 5;
        r4 = -r14;
        r4 = (int) r4;
        r4 = r4 * 3;
        r4 = r4 / 5;
        r5 = r10.f2479ay;
        r5 = -r5;
        r6 = r10.f2479ay;
        r7 = r10.f2480az;
        r7 = -r7;
        r8 = r10.f2480az;
        r0.fling(r1, r2, r3, r4, r5, r6, r7, r8);
        goto L_0x0018;
    L_0x004a:
        r0 = move-exception;
        r2 = "AMapDelegateImpGLSurfaceView";
        com.amap.api.mapcore2d.C0955ck.m3888a(r0, r2, r1);
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.AMapDelegateImpGLSurfaceView.onFling(android.view.MotionEvent, android.view.MotionEvent, float, float):boolean");
    }

    public void onLongPress(MotionEvent motionEvent) {
        this.f2446W = false;
        if (this.f2456ab != null) {
            C1044r c1044r = new C1044r();
            mo9949a((int) motionEvent.getX(), (int) motionEvent.getY(), c1044r);
            this.f2456ab.onMapLongClick(new LatLng(c1044r.f3049b, c1044r.f3048a));
            this.f2435L = true;
        }
        this.f2496q = this.f2489j.mo9819a(motionEvent);
        if (this.f2496q != null) {
            this.f2495p = new Marker(this.f2496q);
            if (this.f2447Z != null && this.f2496q != null && this.f2496q.mo9638h()) {
                this.f2496q.mo9624a(m3455a(this.f2496q.mo9633c()));
                this.f2489j.mo9831c(this.f2496q);
                this.f2447Z.onMarkerDragStart(this.f2495p);
                this.f2445V = true;
            }
        }
    }

    /* renamed from: a */
    private LatLng m3455a(LatLng latLng) {
        IPoint iPoint = new IPoint();
        mo9971b(latLng.latitude, latLng.longitude, iPoint);
        iPoint.f2230b -= 60;
        C1044r c1044r = new C1044r();
        mo9949a(iPoint.f2229a, iPoint.f2230b, c1044r);
        return new LatLng(c1044r.f3049b, c1044r.f3048a);
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        String str = "onScroll";
        if (!this.f2468an.f2520k && motionEvent2.getEventTime() - this.f2468an.f2524o >= 30) {
            try {
                if (!this.f2487h.mo9725e()) {
                    this.f2446W = false;
                }
            } catch (RemoteException e) {
                C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", str);
            }
            if (this.f2452aG > 1) {
                this.f2446W = false;
            } else {
                this.f2446W = true;
                m3457a((int) motionEvent2.getX(), (int) motionEvent2.getY());
                postInvalidate();
                mo10014M();
            }
        }
        return true;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    /* renamed from: a */
    public boolean mo9928a(float f, float f2) {
        this.f2481b.mo9786a(true);
        if (this.f2476av) {
            this.f2477aw += f;
            this.f2478ax += f2;
        }
        invalidate();
        return this.f2476av;
    }

    /* JADX WARNING: Missing block: B:3:0x0009, code skipped:
            if (r4.f2487h.mo9727f() == false) goto L_0x000b;
     */
    /* renamed from: d */
    public boolean mo10029d(float r5) {
        /*
        r4 = this;
        r3 = 0;
        r1 = "onScale";
        r0 = r4.f2487h;	 Catch:{ RemoteException -> 0x000c }
        r0 = r0.mo9727f();	 Catch:{ RemoteException -> 0x000c }
        if (r0 != 0) goto L_0x0012;
    L_0x000b:
        return r3;
    L_0x000c:
        r0 = move-exception;
        r2 = "AMapDelegateImpGLSurfaceView";
        com.amap.api.mapcore2d.C0955ck.m3888a(r0, r2, r1);
    L_0x0012:
        r4.mo10027c(r5);
        goto L_0x000b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.AMapDelegateImpGLSurfaceView.mo10029d(float):boolean");
    }

    /* renamed from: a */
    public boolean mo9930a(Matrix matrix) {
        return false;
    }

    /* renamed from: b */
    public boolean mo10024b(Matrix matrix) {
        String str = "onScale";
        try {
            if (!this.f2487h.mo9727f()) {
                return false;
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", str);
        }
        this.f2474at.set(matrix);
        postInvalidate();
        return true;
    }

    /* renamed from: a */
    public boolean mo9929a(float f, PointF pointF) {
        String str = "onScale";
        try {
            if (!this.f2487h.mo9727f()) {
                return false;
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", str);
        }
        this.f2448a.f2386e.f2357c = false;
        mo10014M();
        m3456a(f, pointF, this.f2477aw, this.f2478ax);
        this.f2476av = false;
        postInvalidateDelayed(8);
        this.f2448a.mo9915a(true);
        return true;
    }

    /* renamed from: b */
    public boolean mo9932b(float f, PointF pointF) {
        String str = "endScale";
        this.f2476av = false;
        try {
            if (!this.f2487h.mo9727f()) {
                return false;
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", str);
        }
        CameraChangeFinishObserver.m4312a().mo10303b();
        return true;
    }

    /* renamed from: a */
    public boolean mo9931a(PointF pointF) {
        String str = "startScale";
        try {
            if (!this.f2487h.mo9727f()) {
                return false;
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", str);
        }
        try {
            if (!mo9997q().mo9727f()) {
                return false;
            }
        } catch (RemoteException e2) {
            C0955ck.m3888a(e2, "AMapDelegateImpGLSurfaceView", str);
        }
        this.f2448a.mo9915a(this.f2494o);
        this.f2448a.f2386e.mo9877a(true);
        this.f2448a.f2386e.f2357c = true;
        this.f2476av = true;
        return true;
    }

    /* JADX WARNING: Missing block: B:3:0x0009, code skipped:
            if (r4.f2487h.mo9727f() == false) goto L_0x000b;
     */
    public boolean onDoubleTap(android.view.MotionEvent r5) {
        /*
        r4 = this;
        r3 = 1;
        r1 = "onDoubleTap";
        r0 = r4.f2487h;	 Catch:{ RemoteException -> 0x000c }
        r0 = r0.mo9727f();	 Catch:{ RemoteException -> 0x000c }
        if (r0 != 0) goto L_0x0012;
    L_0x000b:
        return r3;
    L_0x000c:
        r0 = move-exception;
        r2 = "AMapDelegateImpGLSurfaceView";
        com.amap.api.mapcore2d.C0955ck.m3888a(r0, r2, r1);
    L_0x0012:
        r0 = r4.f2498s;
        if (r0 == 0) goto L_0x0025;
    L_0x0016:
        r0 = r4.f2481b;
        r1 = r5.getX();
        r1 = (int) r1;
        r2 = r5.getY();
        r2 = (int) r2;
        r0.mo9788a(r1, r2);
    L_0x0025:
        r0 = r4.f2452aG;
        if (r0 > r3) goto L_0x000b;
    L_0x0029:
        r4.f2453aH = r3;
        r0 = r4.f2485f;
        r1 = r4.f2448a;
        r1 = r1.f2384c;
        r1 = r1.mo9905e();
        r2 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r1 = r1 + r2;
        r0.mo10149a(r1);
        goto L_0x000b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.AMapDelegateImpGLSurfaceView.onDoubleTap(android.view.MotionEvent):boolean");
    }

    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        String str = "onSingleTapConfirmed";
        if (this.f2481b == null) {
            return false;
        }
        this.f2448a.f2386e.mo9886b(motionEvent);
        Iterator it = this.f2469ao.iterator();
        while (it.hasNext()) {
            ((OnGestureListener) it.next()).onSingleTapUp(motionEvent);
        }
        this.f2446W = false;
        if (this.f2435L) {
            this.f2435L = false;
            return true;
        }
        try {
            if (this.f2437N != null) {
                if (this.f2489j.mo9825a(new Rect(this.f2437N.getLeft(), this.f2437N.getTop(), this.f2437N.getRight(), this.f2437N.getBottom()), (int) motionEvent.getX(), (int) motionEvent.getY()) && this.f2438O != null) {
                    IMarkerDelegate e = this.f2489j.mo9834e();
                    if (!e.mo9614s()) {
                        return true;
                    }
                    this.f2438O.onInfoWindowClick(new Marker(e));
                    return true;
                }
            }
            if (this.f2489j.mo9828b(motionEvent)) {
                IMarkerDelegate e2 = this.f2489j.mo9834e();
                if (e2 == null || !e2.mo9614s()) {
                    return true;
                }
                Marker marker = new Marker(e2);
                if (this.f2441R != null) {
                    if (this.f2441R.onMarkerClick(marker) || this.f2489j.mo9826b() <= 0) {
                        this.f2489j.mo9831c(e2);
                        return true;
                    }
                    try {
                        if (!(this.f2489j.mo9834e() == null || e2.mo9647q())) {
                            LatLng c = e2.mo9633c();
                            if (c != null) {
                                this.f2481b.mo9783a(C0955ck.m3884a(c));
                                CameraChangeFinishObserver.m4312a().mo10303b();
                            }
                        }
                    } catch (RemoteException e3) {
                        C0955ck.m3888a(e3, "AMapDelegateImpGLSurfaceView", str);
                    }
                }
                mo10019a(e2);
                this.f2489j.mo9831c(e2);
                return true;
            }
            if (this.f2458ad != null) {
                C1044r c1044r = new C1044r();
                mo9949a((int) motionEvent.getX(), (int) motionEvent.getY(), c1044r);
                this.f2458ad.onMapClick(new LatLng(c1044r.f3049b, c1044r.f3048a));
            }
            return true;
        } catch (Exception e4) {
            C0955ck.m3888a(e4, "AMapDelegateImpGLSurfaceView", str);
            return true;
        }
    }

    /* renamed from: a */
    private void m3456a(float f, PointF pointF, float f2, float f3) {
        String str = "doScale";
        try {
            if (!this.f2487h.mo9727f()) {
                return;
            }
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", str);
        }
        this.f2452aG = 2;
        int c = this.f2448a.f2384c.mo9903c() / 2;
        int d = this.f2448a.f2384c.mo9904d() / 2;
        float a = mo10016a((float) ((Math.log((double) f) / Math.log(2.0d)) + ((double) this.f2448a.f2384c.mo9905e())));
        if (a != this.f2448a.f2384c.mo9905e()) {
            this.f2482c[0] = this.f2482c[1];
            this.f2482c[1] = a;
            if (this.f2482c[0] != this.f2482c[1]) {
                GeoPoint a2 = this.f2448a.f2383b.mo9909a(c, d);
                this.f2448a.f2384c.mo9893a(a);
                this.f2448a.f2384c.mo9897a(a2);
                m3465aa();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public PointF mo10021b(PointF pointF) {
        PointF pointF2 = new PointF();
        int width = getWidth();
        int height = getHeight();
        float f = pointF.x - ((float) (width >> 1));
        float f2 = pointF.y - ((float) (height >> 1));
        double atan2 = Math.atan2((double) f2, (double) f);
        double sqrt = Math.sqrt(Math.pow((double) f2, 2.0d) + Math.pow((double) f, 2.0d));
        atan2 -= (((double) mo10013L()) * 3.141592653589793d) / 180.0d;
        pointF2.x = (float) ((Math.cos(atan2) * sqrt) + ((double) (width >> 1)));
        pointF2.y = (float) (((double) (height >> 1)) + (sqrt * Math.sin(atan2)));
        return pointF2;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: c */
    public PointF mo10026c(PointF pointF) {
        PointF pointF2 = new PointF();
        int width = getWidth();
        int height = getHeight();
        float f = pointF.x - ((float) (width >> 1));
        float f2 = pointF.y - ((float) (height >> 1));
        double atan2 = Math.atan2((double) f2, (double) f);
        double sqrt = Math.sqrt(Math.pow((double) f2, 2.0d) + Math.pow((double) f, 2.0d));
        atan2 += (((double) mo10013L()) * 3.141592653589793d) / 180.0d;
        pointF2.x = (float) ((Math.cos(atan2) * sqrt) + ((double) (width >> 1)));
        pointF2.y = (float) (((double) (height >> 1)) + (sqrt * Math.sin(atan2)));
        return pointF2;
    }

    /* renamed from: L */
    public int mo10013L() {
        return 0;
    }

    /* renamed from: a */
    private void m3463a(boolean z, CameraPosition cameraPosition) {
        String str = "cameraChangeFinish";
        if (this.f2431H != null && this.f2432I.mo10294a() && isEnabled()) {
            if (cameraPosition == null) {
                try {
                    cameraPosition = mo9987g();
                } catch (RemoteException e) {
                    C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", str);
                }
            }
            try {
                this.f2431H.onCameraChangeFinish(cameraPosition);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void mo9956a(OnCameraChangeListener onCameraChangeListener) throws RemoteException {
        this.f2431H = onCameraChangeListener;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: M */
    public void mo10014M() {
        this.f2490k.sendEmptyMessage(10);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: N */
    public void mo10015N() {
        this.f2490k.sendEmptyMessage(15);
    }

    /* renamed from: aa */
    private void m3465aa() {
        int i = 100;
        if (this.f2425B != null) {
            if (this.f2491l == -1.0f) {
                int width = getWidth();
                int height = getHeight();
                int i2 = this.f2492m.getResources().getDisplayMetrics().densityDpi;
                if (i2 > 120) {
                    if (i2 > 160) {
                        i = i2 <= 240 ? Math.min(width, height) >= 1000 ? 60 : 70 : i2 <= 320 ? 50 : i2 <= 480 ? 50 : 40;
                    } else if (Math.max(width, height) <= 480) {
                        i = 120;
                    }
                }
                this.f2491l = ((float) i) / 100.0f;
            }
            LatLng ab = m3466ab();
            if (ab != null) {
                float f = mo9985f();
                i = (int) (((double) this.f2497r[(int) f]) / (((double) this.f2491l) * ((double) ((float) ((((Math.cos((ab.latitude * 3.141592653589793d) / 180.0d) * 2.0d) * 3.141592653589793d) * 6378137.0d) / (256.0d * Math.pow(2.0d, (double) f)))))));
                String a = C0955ck.m3885a(this.f2497r[(int) f]);
                this.f2425B.mo10081a(i);
                this.f2425B.mo10082a(a);
                this.f2425B.invalidate();
            }
        }
    }

    /* renamed from: a */
    public void mo9949a(int i, int i2, C1044r c1044r) {
        GeoPoint a = this.f2434K.mo9845a(new PointF((float) i, (float) i2), this.f2434K.f2316l, this.f2434K.f2318n, this.f2434K.f2315k, this.f2434K.f2319o);
        if (c1044r != null) {
            double a2 = C1043q.m4373a((long) a.mo10334b());
            double a3 = C1043q.m4373a((long) a.mo10332a());
            c1044r.f3049b = a2;
            c1044r.f3048a = a3;
        }
    }

    /* renamed from: a */
    private void m3458a(int i, int i2, IPoint iPoint) {
        int f = (int) mo9985f();
        GeoPoint a = this.f2434K.mo9845a(new PointF((float) i, (float) i2), this.f2434K.f2316l, this.f2434K.f2318n, this.f2434K.f2315k, this.f2434K.f2319o);
        if (iPoint != null) {
            iPoint.f2229a = (int) a.mo10338e();
            iPoint.f2230b = (int) a.mo10340f();
        }
    }

    /* renamed from: a */
    public void mo9947a(double d, double d2, C1044r c1044r) {
        int f = (int) mo9985f();
        PointF b = this.f2434K.mo9852b(new GeoPoint((int) C1043q.m4374a(d), (int) C1043q.m4374a(d2)), this.f2434K.f2316l, this.f2434K.f2318n, this.f2434K.f2315k);
        if (c1044r != null) {
            c1044r.f3048a = (double) b.x;
            c1044r.f3049b = (double) b.y;
        }
    }

    /* renamed from: a */
    public void mo10017a(double d, double d2, IPoint iPoint) {
        GeoPoint b = this.f2434K.mo9854b(new GeoPoint((int) (d * 1000000.0d), (int) (1000000.0d * d2)));
        iPoint.f2229a = b.mo10332a();
        iPoint.f2230b = b.mo10334b();
    }

    /* renamed from: b */
    public void mo10023b(int i, int i2, C1044r c1044r) {
        if (c1044r != null) {
            c1044r.f3048a = C1043q.m4373a((long) i);
            c1044r.f3049b = C1043q.m4373a((long) i2);
        }
    }

    /* renamed from: b */
    public void mo9971b(double d, double d2, IPoint iPoint) {
        int f = (int) mo9985f();
        PointF b = this.f2434K.mo9852b(new GeoPoint((int) C1043q.m4374a(d), (int) C1043q.m4374a(d2)), this.f2434K.f2316l, this.f2434K.f2318n, this.f2434K.f2315k);
        if (iPoint != null) {
            iPoint.f2229a = (int) b.x;
            iPoint.f2230b = (int) b.y;
        }
    }

    /* renamed from: ab */
    private LatLng m3466ab() {
        GeoPoint C = mo10006C();
        if (C == null) {
            return null;
        }
        return new LatLng(C1043q.m4373a((long) C.mo10334b()), C1043q.m4373a((long) C.mo10332a()));
    }

    /* renamed from: ac */
    private IPoint m3467ac() {
        GeoPoint C = mo10006C();
        IPoint iPoint = new IPoint();
        iPoint.f2229a = (int) C.mo10338e();
        iPoint.f2230b = (int) C.mo10340f();
        return iPoint;
    }

    /* renamed from: O */
    public void mo9935O() {
        if (this.f2433J != null) {
            this.f2433J.onCancel();
            this.f2433J = null;
        }
    }

    /* renamed from: P */
    public void mo9933P() {
    }

    /* renamed from: Q */
    public void mo9934Q() {
        this.f2490k.sendEmptyMessage(17);
    }

    /* renamed from: R */
    public void mo9936R() {
        postInvalidate();
        this.f2486g.postInvalidate();
    }

    /* renamed from: S */
    public List<Marker> mo9937S() {
        boolean z = getWidth() > 0 && getHeight() > 0;
        C0953ch.m3872a(z, (Object) "地图未初始化完成！");
        return this.f2489j.mo9838g();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo10020a(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate, boolean z, long j) {
        String str = "newLatLngBoundsWithSize";
        if (this.f2481b != null) {
            try {
                LatLngBounds latLngBounds = cameraUpdateFactoryDelegate.f2999g;
                float f = (float) ((latLngBounds.northeast.latitude * 1000000.0d) - (latLngBounds.southwest.latitude * 1000000.0d));
                float f2 = (float) ((latLngBounds.northeast.longitude * 1000000.0d) - (latLngBounds.southwest.longitude * 1000000.0d));
                GeoPoint geoPoint = new GeoPoint((int) (((latLngBounds.northeast.latitude * 1000000.0d) + (latLngBounds.southwest.latitude * 1000000.0d)) / 2.0d), (int) (((latLngBounds.northeast.longitude * 1000000.0d) + (latLngBounds.southwest.longitude * 1000000.0d)) / 2.0d));
                if (z) {
                    this.f2481b.mo9785a(geoPoint, (int) j);
                } else {
                    this.f2481b.mo9783a(geoPoint);
                }
                this.f2481b.mo9781a(f, f2, cameraUpdateFactoryDelegate.f3001i, cameraUpdateFactoryDelegate.f3002j, cameraUpdateFactoryDelegate.f3000h);
                CameraChangeFinishObserver.m4312a().mo10303b();
            } catch (Exception e) {
                C0955ck.m3888a(e, "AMapDelegateImpGLSurfaceView", str);
            }
        }
    }

    /* renamed from: c */
    public void mo9979c(String str) throws RemoteException {
        if (this.f2448a != null && this.f2448a.f2386e != null && !mo10008E()) {
            this.f2448a.f2386e.mo9876a(str);
        }
    }

    /* renamed from: T */
    public void mo9938T() {
        mo9955a(null);
    }

    /* renamed from: a */
    public void mo9955a(OnCacheRemoveListener onCacheRemoveListener) {
        if (this.f2464aj != null) {
            try {
                C0920b c0920b = new C0920b(this.f2492m, onCacheRemoveListener);
                this.f2464aj.removeCallbacks(c0920b);
                this.f2464aj.post(c0920b);
            } catch (Throwable th) {
                C0990dd.m4098b(th, "AMapDelegateImpGLSurfaceView", "removecache");
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public Text mo9945a(TextOptions textOptions) throws RemoteException {
        ITextDelegate c0934bm = new C0934bm(this, textOptions, this.f2489j);
        this.f2489j.mo9824a(c0934bm);
        invalidate();
        return new Text(c0934bm);
    }
}
