package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;

/* compiled from: GroundOverlayDelegateImp */
/* renamed from: com.amap.api.mapcore2d.v */
class C1048v implements IGroundOverlayDelegate {
    /* renamed from: a */
    private final double f3065a = 0.01745329251994329d;
    /* renamed from: b */
    private final double f3066b = 6371000.79d;
    /* renamed from: c */
    private AMapDelegateImpGLSurfaceView f3067c;
    /* renamed from: d */
    private BitmapDescriptor f3068d;
    /* renamed from: e */
    private LatLng f3069e;
    /* renamed from: f */
    private float f3070f;
    /* renamed from: g */
    private float f3071g;
    /* renamed from: h */
    private LatLngBounds f3072h;
    /* renamed from: i */
    private float f3073i;
    /* renamed from: j */
    private float f3074j;
    /* renamed from: k */
    private boolean f3075k = true;
    /* renamed from: l */
    private float f3076l = 0.0f;
    /* renamed from: m */
    private float f3077m = 0.5f;
    /* renamed from: n */
    private float f3078n = 0.5f;
    /* renamed from: o */
    private String f3079o;
    /* renamed from: p */
    private Bitmap f3080p;

    C1048v(AMapDelegateImpGLSurfaceView aMapDelegateImpGLSurfaceView) {
        String str = "GroundOverlayDelegateImp";
        this.f3067c = aMapDelegateImpGLSurfaceView;
        try {
            this.f3079o = mo9654c();
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "GroundOverlayDelegateImp", str);
        }
    }

    /* renamed from: b */
    public void mo9653b() throws RemoteException {
        this.f3067c.mo9969a(mo9654c());
    }

    /* renamed from: c */
    public String mo9654c() throws RemoteException {
        if (this.f3079o == null) {
            this.f3079o = C1047t.m4384a("GroundOverlay");
        }
        return this.f3079o;
    }

    /* renamed from: a */
    public void mo9648a(float f) throws RemoteException {
        this.f3074j = f;
        this.f3067c.invalidate();
    }

    /* renamed from: d */
    public float mo9655d() throws RemoteException {
        return this.f3074j;
    }

    /* renamed from: a */
    public void mo9650a(boolean z) throws RemoteException {
        this.f3075k = z;
        this.f3067c.postInvalidate();
    }

    /* renamed from: e */
    public boolean mo9656e() throws RemoteException {
        return this.f3075k;
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
        return super.hashCode();
    }

    /* renamed from: g */
    public void mo10357g() throws RemoteException {
        if (this.f3069e == null) {
            m4418p();
        } else if (this.f3072h == null) {
            m4417o();
        }
    }

    /* renamed from: o */
    private void m4417o() {
        double cos = ((double) this.f3070f) / ((6371000.79d * Math.cos(this.f3069e.latitude * 0.01745329251994329d)) * 0.01745329251994329d);
        double d = ((double) this.f3071g) / 111194.94043265979d;
        this.f3072h = new LatLngBounds(new LatLng(this.f3069e.latitude - (((double) (1.0f - this.f3078n)) * d), this.f3069e.longitude - (((double) this.f3077m) * cos)), new LatLng((d * ((double) this.f3078n)) + this.f3069e.latitude, (cos * ((double) (1.0f - this.f3077m))) + this.f3069e.longitude));
    }

    /* renamed from: p */
    private void m4418p() {
        LatLng latLng = this.f3072h.southwest;
        LatLng latLng2 = this.f3072h.northeast;
        this.f3069e = new LatLng(latLng.latitude + (((double) (1.0f - this.f3078n)) * (latLng2.latitude - latLng.latitude)), latLng.longitude + (((double) this.f3077m) * (latLng2.longitude - latLng.longitude)));
        this.f3070f = (float) (((6371000.79d * Math.cos(this.f3069e.latitude * 0.01745329251994329d)) * (latLng2.longitude - latLng.longitude)) * 0.01745329251994329d);
        this.f3071g = (float) (((latLng2.latitude - latLng.latitude) * 6371000.79d) * 0.01745329251994329d);
    }

    /* renamed from: l */
    public void mo9658l() {
        String str = "destroy";
        try {
            mo9653b();
            if (this.f3068d != null) {
                Bitmap bitmap = this.f3068d.getBitmap();
                if (bitmap != null) {
                    bitmap.recycle();
                    this.f3068d = null;
                }
            }
            this.f3069e = null;
            this.f3072h = null;
        } catch (Exception e) {
            C0955ck.m3888a(e, "GroundOverlayDelegateImp", str);
            Log.d("destroy erro", "GroundOverlayDelegateImp destroy");
        }
    }

    /* renamed from: a */
    public boolean mo9651a() {
        if (this.f3072h == null) {
            return false;
        }
        LatLngBounds x = this.f3067c.mo10055x();
        if (x == null) {
            return true;
        }
        if (x.contains(this.f3072h) || this.f3072h.intersects(x)) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public void mo10345a(LatLng latLng) throws RemoteException {
        if (this.f3069e == null || this.f3069e.equals(latLng)) {
            this.f3069e = latLng;
            return;
        }
        this.f3069e = latLng;
        m4417o();
    }

    /* renamed from: h */
    public LatLng mo10350h() throws RemoteException {
        return this.f3069e;
    }

    /* renamed from: b */
    public void mo10347b(float f) throws RemoteException {
        C0953ch.m3874b(f >= 0.0f, "Width must be non-negative");
        if (this.f3070f != f) {
            this.f3070f = f;
            this.f3071g = f;
            return;
        }
        this.f3070f = f;
        this.f3071g = f;
    }

    /* renamed from: a */
    public void mo10343a(float f, float f2) throws RemoteException {
        boolean z = true;
        C0953ch.m3874b(f >= 0.0f, "Width must be non-negative");
        if (f2 < 0.0f) {
            z = false;
        }
        C0953ch.m3874b(z, "Height must be non-negative");
        if (this.f3070f == f || this.f3071g == f2) {
            this.f3070f = f;
            this.f3071g = f2;
            return;
        }
        this.f3070f = f;
        this.f3071g = f2;
    }

    /* renamed from: i */
    public float mo10351i() throws RemoteException {
        return this.f3070f;
    }

    /* renamed from: j */
    public float mo10352j() throws RemoteException {
        return this.f3071g;
    }

    /* renamed from: a */
    public void mo10346a(LatLngBounds latLngBounds) throws RemoteException {
        if (this.f3072h == null || this.f3072h.equals(latLngBounds)) {
            this.f3072h = latLngBounds;
            return;
        }
        this.f3072h = latLngBounds;
        m4418p();
    }

    /* renamed from: k */
    public LatLngBounds mo10353k() throws RemoteException {
        return this.f3072h;
    }

    /* renamed from: c */
    public void mo10348c(float f) throws RemoteException {
        float f2 = (((-f) % 360.0f) + 360.0f) % 360.0f;
        if (Double.doubleToLongBits((double) this.f3073i) != Double.doubleToLongBits((double) f2)) {
            this.f3073i = f2;
        } else {
            this.f3073i = f2;
        }
    }

    /* renamed from: m */
    public float mo10354m() throws RemoteException {
        return this.f3073i;
    }

    /* renamed from: d */
    public void mo10349d(float f) throws RemoteException {
        boolean z = f >= 0.0f && f <= 1.0f;
        C0953ch.m3874b(z, "Transparency must be in the range [0..1]");
        this.f3076l = f;
    }

    /* renamed from: n */
    public float mo10355n() throws RemoteException {
        return this.f3076l;
    }

    /* renamed from: a */
    public void mo10344a(BitmapDescriptor bitmapDescriptor) throws RemoteException {
        this.f3068d = bitmapDescriptor;
    }

    /* renamed from: b */
    public void mo10356b(float f, float f2) throws RemoteException {
        this.f3077m = f;
        this.f3078n = f2;
    }

    /* renamed from: a */
    public void mo9649a(Canvas canvas) throws RemoteException {
        if (!this.f3075k) {
            return;
        }
        if ((this.f3069e != null || this.f3072h != null) && this.f3068d != null) {
            mo10357g();
            if (this.f3070f != 0.0f || this.f3071g != 0.0f) {
                this.f3080p = this.f3068d.getBitmap();
                if (this.f3080p != null && !this.f3080p.isRecycled()) {
                    LatLng latLng = this.f3072h.southwest;
                    LatLng latLng2 = this.f3072h.northeast;
                    LatLng latLng3 = this.f3069e;
                    GeoPoint b = m4416b(latLng);
                    GeoPoint b2 = m4416b(latLng2);
                    GeoPoint b3 = m4416b(latLng3);
                    Point point = new Point();
                    Point point2 = new Point();
                    Point point3 = new Point();
                    this.f3067c.mo9999s().mo9908a(b, point);
                    this.f3067c.mo9999s().mo9908a(b2, point2);
                    this.f3067c.mo9999s().mo9908a(b3, point3);
                    Paint paint = new Paint();
                    RectF rectF = new RectF((float) point.x, (float) point2.y, (float) point2.x, (float) point.y);
                    paint.setAlpha((int) (255.0f - (this.f3076l * 255.0f)));
                    paint.setFilterBitmap(true);
                    canvas.save();
                    canvas.rotate(this.f3073i, (float) point3.x, (float) point3.y);
                    canvas.drawBitmap(this.f3080p, null, rectF, paint);
                    canvas.restore();
                }
            }
        }
    }

    /* renamed from: b */
    private GeoPoint m4416b(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return new GeoPoint((int) (latLng.latitude * 1000000.0d), (int) (latLng.longitude * 1000000.0d));
    }
}
