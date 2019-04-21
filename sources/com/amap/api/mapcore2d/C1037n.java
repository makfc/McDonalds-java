package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.os.RemoteException;
import android.support.p000v4.view.ViewCompat;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.model.LatLng;

/* compiled from: CircleDelegateImp */
/* renamed from: com.amap.api.mapcore2d.n */
class C1037n implements ICircleDelegate {
    /* renamed from: a */
    private LatLng f3010a = null;
    /* renamed from: b */
    private double f3011b = 0.0d;
    /* renamed from: c */
    private float f3012c = 10.0f;
    /* renamed from: d */
    private int f3013d = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: e */
    private int f3014e = 0;
    /* renamed from: f */
    private float f3015f = 0.0f;
    /* renamed from: g */
    private boolean f3016g = true;
    /* renamed from: h */
    private String f3017h;
    /* renamed from: i */
    private AMapDelegateImpGLSurfaceView f3018i;

    public C1037n(AMapDelegateImpGLSurfaceView aMapDelegateImpGLSurfaceView) {
        String str = "CircleDelegateIme";
        this.f3018i = aMapDelegateImpGLSurfaceView;
        try {
            this.f3017h = mo9654c();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "CircleDelegateImp", str);
        }
    }

    /* renamed from: a */
    public boolean mo9651a() {
        return true;
    }

    /* renamed from: b */
    public void mo9653b() throws RemoteException {
        this.f3018i.mo9969a(mo9654c());
        this.f3018i.postInvalidate();
    }

    /* renamed from: c */
    public String mo9654c() throws RemoteException {
        if (this.f3017h == null) {
            this.f3017h = C1047t.m4384a("Circle");
        }
        return this.f3017h;
    }

    /* renamed from: a */
    public void mo9648a(float f) throws RemoteException {
        this.f3015f = f;
        this.f3018i.invalidate();
    }

    /* renamed from: d */
    public float mo9655d() throws RemoteException {
        return this.f3015f;
    }

    /* renamed from: a */
    public void mo9650a(boolean z) throws RemoteException {
        this.f3016g = z;
        this.f3018i.postInvalidate();
    }

    /* renamed from: e */
    public boolean mo9656e() throws RemoteException {
        return this.f3016g;
    }

    /* renamed from: a */
    public boolean mo9652a(IOverlayDelegate iOverlayDelegate) throws RemoteException {
        if (equals(iOverlayDelegate) || iOverlayDelegate.mo9654c().equals(mo9654c())) {
            return true;
        }
        return false;
    }

    /* renamed from: f */
    public int mo9657f() throws RemoteException {
        return 0;
    }

    /* renamed from: a */
    public void mo9649a(Canvas canvas) throws RemoteException {
        if (mo10314g() != null && this.f3011b > 0.0d && mo9656e()) {
            float a = this.f3018i.mo10022b().f2383b.mo9910a((float) mo10315h());
            GeoPoint geoPoint = new GeoPoint((int) (this.f3010a.latitude * 1000000.0d), (int) (this.f3010a.longitude * 1000000.0d));
            Point point = new Point();
            this.f3018i.mo9999s().mo9908a(geoPoint, point);
            Paint paint = new Paint();
            paint.setColor(mo10318k());
            paint.setAntiAlias(true);
            paint.setStyle(Style.FILL);
            canvas.drawCircle((float) point.x, (float) point.y, a, paint);
            paint.setColor(mo10317j());
            paint.setStyle(Style.STROKE);
            paint.setStrokeWidth(mo10316i());
            canvas.drawCircle((float) point.x, (float) point.y, a, paint);
        }
    }

    /* renamed from: a */
    public void mo10310a(LatLng latLng) throws RemoteException {
        this.f3010a = latLng;
    }

    /* renamed from: g */
    public LatLng mo10314g() throws RemoteException {
        return this.f3010a;
    }

    /* renamed from: a */
    public void mo10308a(double d) throws RemoteException {
        this.f3011b = d;
    }

    /* renamed from: h */
    public double mo10315h() throws RemoteException {
        return this.f3011b;
    }

    /* renamed from: b */
    public void mo10311b(float f) throws RemoteException {
        this.f3012c = f;
    }

    /* renamed from: i */
    public float mo10316i() throws RemoteException {
        return this.f3012c;
    }

    /* renamed from: a */
    public void mo10309a(int i) throws RemoteException {
        this.f3013d = i;
    }

    /* renamed from: j */
    public int mo10317j() throws RemoteException {
        return this.f3013d;
    }

    /* renamed from: b */
    public void mo10312b(int i) throws RemoteException {
        this.f3014e = i;
    }

    /* renamed from: k */
    public int mo10318k() throws RemoteException {
        return this.f3014e;
    }

    /* renamed from: l */
    public void mo9658l() {
        this.f3010a = null;
    }

    /* renamed from: b */
    public boolean mo10313b(LatLng latLng) throws RemoteException {
        if (this.f3011b >= ((double) AMapUtils.calculateLineDistance(this.f3010a, latLng))) {
            return true;
        }
        return false;
    }
}
