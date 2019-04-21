package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IGroundOverlayDelegate;
import com.autonavi.amap.mapcore.interfaces.IOverlayDelegate;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.util.x */
public class GroundOverlayDelegateImp implements IGroundOverlayDelegate {
    /* renamed from: a */
    private IAMapDelegate f2177a;
    /* renamed from: b */
    private BitmapDescriptor f2178b;
    /* renamed from: c */
    private LatLng f2179c;
    /* renamed from: d */
    private float f2180d;
    /* renamed from: e */
    private float f2181e;
    /* renamed from: f */
    private LatLngBounds f2182f;
    /* renamed from: g */
    private float f2183g;
    /* renamed from: h */
    private float f2184h;
    /* renamed from: i */
    private boolean f2185i = true;
    /* renamed from: j */
    private float f2186j = 0.0f;
    /* renamed from: k */
    private float f2187k = 0.5f;
    /* renamed from: l */
    private float f2188l = 0.5f;
    /* renamed from: m */
    private String f2189m;
    /* renamed from: n */
    private FloatBuffer f2190n = null;
    /* renamed from: o */
    private FloatBuffer f2191o;
    /* renamed from: p */
    private int f2192p;
    /* renamed from: q */
    private boolean f2193q = false;
    /* renamed from: r */
    private boolean f2194r = false;

    public GroundOverlayDelegateImp(IAMapDelegate iAMapDelegate) {
        this.f2177a = iAMapDelegate;
        try {
            this.f2189m = getId();
        } catch (RemoteException e) {
            SDKLogHandler.m2563a(e, "GroundOverlayDelegateImp", "create");
            e.printStackTrace();
        }
    }

    public void remove() throws RemoteException {
        this.f2177a.deleteTexsureId(this.f2192p);
        this.f2177a.removeGLOverlay(getId());
        this.f2177a.setRunLowFrame(false);
    }

    public String getId() throws RemoteException {
        if (this.f2189m == null) {
            this.f2189m = GLOverlayLayer.m2918a("GroundOverlay");
        }
        return this.f2189m;
    }

    public void setZIndex(float f) throws RemoteException {
        this.f2184h = f;
        this.f2177a.changeGLOverlayIndex();
        this.f2177a.setRunLowFrame(false);
    }

    public float getZIndex() throws RemoteException {
        return this.f2184h;
    }

    public void setVisible(boolean z) throws RemoteException {
        this.f2185i = z;
        this.f2177a.setRunLowFrame(false);
    }

    public boolean isVisible() throws RemoteException {
        return this.f2185i;
    }

    public boolean equalsRemote(IOverlayDelegate iOverlayDelegate) throws RemoteException {
        if (equals(iOverlayDelegate) || iOverlayDelegate.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() throws RemoteException {
        return super.hashCode();
    }

    public void calMapFPoint() throws RemoteException {
        this.f2194r = false;
        if (this.f2179c == null) {
            m2936b();
        } else if (this.f2182f == null) {
            m2933a();
        } else {
            m2937c();
        }
    }

    /* renamed from: a */
    private void m2933a() {
        if (this.f2179c != null) {
            double cos = ((double) this.f2180d) / ((6371000.79d * Math.cos(this.f2179c.latitude * 0.01745329251994329d)) * 0.01745329251994329d);
            double d = ((double) this.f2181e) / 111194.94043265979d;
            this.f2182f = new LatLngBounds(new LatLng(this.f2179c.latitude - (((double) (1.0f - this.f2188l)) * d), this.f2179c.longitude - (((double) this.f2187k) * cos)), new LatLng((d * ((double) this.f2188l)) + this.f2179c.latitude, (cos * ((double) (1.0f - this.f2187k))) + this.f2179c.longitude));
            m2937c();
        }
    }

    /* renamed from: b */
    private void m2936b() {
        if (this.f2182f != null) {
            LatLng latLng = this.f2182f.southwest;
            LatLng latLng2 = this.f2182f.northeast;
            this.f2179c = new LatLng(latLng.latitude + (((double) (1.0f - this.f2188l)) * (latLng2.latitude - latLng.latitude)), latLng.longitude + (((double) this.f2187k) * (latLng2.longitude - latLng.longitude)));
            this.f2180d = (float) (((6371000.79d * Math.cos(this.f2179c.latitude * 0.01745329251994329d)) * (latLng2.longitude - latLng.longitude)) * 0.01745329251994329d);
            this.f2181e = (float) (((latLng2.latitude - latLng.latitude) * 6371000.79d) * 0.01745329251994329d);
            m2937c();
        }
    }

    /* renamed from: c */
    private void m2937c() {
        if (this.f2182f != null) {
            float[] fArr = new float[12];
            FPoint fPoint = new FPoint();
            FPoint fPoint2 = new FPoint();
            FPoint fPoint3 = new FPoint();
            FPoint fPoint4 = new FPoint();
            this.f2177a.getLatLng2Map(this.f2182f.southwest.latitude, this.f2182f.southwest.longitude, fPoint);
            this.f2177a.getLatLng2Map(this.f2182f.southwest.latitude, this.f2182f.northeast.longitude, fPoint2);
            this.f2177a.getLatLng2Map(this.f2182f.northeast.latitude, this.f2182f.northeast.longitude, fPoint3);
            this.f2177a.getLatLng2Map(this.f2182f.northeast.latitude, this.f2182f.southwest.longitude, fPoint4);
            if (this.f2183g != 0.0f) {
                double d = (double) (fPoint2.f4560x - fPoint.f4560x);
                double d2 = (double) (fPoint2.f4561y - fPoint3.f4561y);
                DPoint dPoint = new DPoint();
                dPoint.f4558x = ((double) fPoint.f4560x) + (((double) this.f2187k) * d);
                dPoint.f4559y = ((double) fPoint.f4561y) - (((double) (1.0f - this.f2188l)) * d2);
                m2934a(dPoint, 0.0d, 0.0d, d, d2, fPoint);
                m2934a(dPoint, d, 0.0d, d, d2, fPoint2);
                m2934a(dPoint, d, d2, d, d2, fPoint3);
                m2934a(dPoint, 0.0d, d2, d, d2, fPoint4);
            }
            fArr[0] = fPoint.f4560x;
            fArr[1] = fPoint.f4561y;
            fArr[2] = 0.0f;
            fArr[3] = fPoint2.f4560x;
            fArr[4] = fPoint2.f4561y;
            fArr[5] = 0.0f;
            fArr[6] = fPoint3.f4560x;
            fArr[7] = fPoint3.f4561y;
            fArr[8] = 0.0f;
            fArr[9] = fPoint4.f4560x;
            fArr[10] = fPoint4.f4561y;
            fArr[11] = 0.0f;
            if (this.f2190n == null) {
                this.f2190n = Util.m2355a(fArr);
            } else {
                this.f2190n = Util.m2356a(fArr, this.f2190n);
            }
        }
    }

    /* renamed from: a */
    private void m2934a(DPoint dPoint, double d, double d2, double d3, double d4, FPoint fPoint) {
        double d5 = d - (((double) this.f2187k) * d3);
        double d6 = (((double) (1.0f - this.f2188l)) * d4) - d2;
        double d7 = ((double) (-this.f2183g)) * 0.01745329251994329d;
        fPoint.f4560x = (float) (dPoint.f4558x + ((Math.cos(d7) * d5) + (Math.sin(d7) * d6)));
        fPoint.f4561y = (float) (((d6 * Math.cos(d7)) - (d5 * Math.sin(d7))) + dPoint.f4559y);
    }

    /* renamed from: d */
    private void m2938d() {
        if (this.f2178b != null) {
            int width = this.f2178b.getWidth();
            float width2 = ((float) width) / ((float) this.f2178b.getBitmap().getWidth());
            float height = ((float) this.f2178b.getHeight()) / ((float) this.f2178b.getBitmap().getHeight());
            this.f2191o = Util.m2355a(new float[]{0.0f, height, width2, height, width2, 0.0f, 0.0f, 0.0f});
        }
    }

    public void draw(GL10 gl10) throws RemoteException {
        if (!this.f2185i) {
            return;
        }
        if ((this.f2179c != null || this.f2182f != null) && this.f2178b != null) {
            if (!this.f2193q) {
                Bitmap bitmap = this.f2178b.getBitmap();
                if (!(bitmap == null || bitmap.isRecycled())) {
                    if (this.f2192p == 0) {
                        this.f2192p = this.f2177a.getTexsureId();
                        if (this.f2192p == 0) {
                            int[] iArr = new int[]{0};
                            gl10.glGenTextures(1, iArr, 0);
                            this.f2192p = iArr[0];
                        }
                    } else {
                        gl10.glDeleteTextures(1, new int[]{this.f2192p}, 0);
                    }
                    Util.m2367b(gl10, this.f2192p, bitmap, true);
                }
                this.f2193q = true;
            }
            if (this.f2180d != 0.0f || this.f2181e != 0.0f) {
                m2935a(gl10, this.f2192p, this.f2190n, this.f2191o);
                this.f2194r = true;
            }
        }
    }

    /* renamed from: a */
    private void m2935a(GL10 gl10, int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (floatBuffer != null && floatBuffer2 != null) {
            gl10.glEnable(3042);
            gl10.glTexEnvf(8960, 8704, 8448.0f);
            gl10.glBlendFunc(1, 771);
            gl10.glColor4f(1.0f, 1.0f, 1.0f, 1.0f - this.f2186j);
            gl10.glEnable(3553);
            gl10.glEnableClientState(32884);
            gl10.glEnableClientState(32888);
            gl10.glBindTexture(3553, i);
            gl10.glVertexPointer(3, 5126, 0, floatBuffer);
            gl10.glTexCoordPointer(2, 5126, 0, floatBuffer2);
            gl10.glDrawArrays(6, 0, 4);
            gl10.glDisableClientState(32884);
            gl10.glDisableClientState(32888);
            gl10.glDisable(3553);
            gl10.glDisable(3042);
        }
    }

    public void destroy() {
        try {
            remove();
            if (this.f2178b != null) {
                Bitmap bitmap = this.f2178b.getBitmap();
                if (bitmap != null) {
                    bitmap.recycle();
                    this.f2178b = null;
                }
            }
            if (this.f2191o != null) {
                this.f2191o.clear();
                this.f2191o = null;
            }
            if (this.f2190n != null) {
                this.f2190n.clear();
                this.f2190n = null;
            }
            this.f2179c = null;
            this.f2182f = null;
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "GroundOverlayDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "GroundOverlayDelegateImp destroy");
        }
    }

    public boolean checkInBounds() {
        Rect rect = this.f2177a.getRect();
        if (rect == null) {
            return true;
        }
        IPoint iPoint = new IPoint();
        if (this.f2179c != null) {
            this.f2177a.getLatLng2Pixel(this.f2179c.latitude, this.f2179c.longitude, iPoint);
        }
        return rect.contains(iPoint.f4562x, iPoint.f4563y);
    }

    public void setPosition(LatLng latLng) throws RemoteException {
        this.f2179c = latLng;
        m2933a();
        this.f2177a.setRunLowFrame(false);
    }

    public LatLng getPosition() throws RemoteException {
        return this.f2179c;
    }

    public void setDimensions(float f) throws RemoteException {
        AMapThrowException.m2228b(f >= 0.0f, "Width must be non-negative");
        if (!this.f2193q || this.f2180d == f) {
            this.f2180d = f;
            this.f2181e = f;
        } else {
            this.f2180d = f;
            this.f2181e = f;
            m2933a();
        }
        this.f2177a.setRunLowFrame(false);
    }

    public void setDimensions(float f, float f2) throws RemoteException {
        boolean z = true;
        AMapThrowException.m2228b(f >= 0.0f, "Width must be non-negative");
        if (f2 < 0.0f) {
            z = false;
        }
        AMapThrowException.m2228b(z, "Height must be non-negative");
        if (!this.f2193q || this.f2180d == f || this.f2181e == f2) {
            this.f2180d = f;
            this.f2181e = f2;
        } else {
            this.f2180d = f;
            this.f2181e = f2;
            m2933a();
        }
        this.f2177a.setRunLowFrame(false);
    }

    public float getWidth() throws RemoteException {
        return this.f2180d;
    }

    public float getHeight() throws RemoteException {
        return this.f2181e;
    }

    public void setPositionFromBounds(LatLngBounds latLngBounds) throws RemoteException {
        this.f2182f = latLngBounds;
        m2936b();
        this.f2177a.setRunLowFrame(false);
    }

    public LatLngBounds getBounds() throws RemoteException {
        return this.f2182f;
    }

    public void setBearing(float f) throws RemoteException {
        float f2 = ((f % 360.0f) + 360.0f) % 360.0f;
        if (!this.f2193q || ((double) Math.abs(this.f2183g - f2)) <= 1.0E-7d) {
            this.f2183g = f2;
        } else {
            this.f2183g = f2;
            m2937c();
        }
        this.f2177a.setRunLowFrame(false);
    }

    public float getBearing() throws RemoteException {
        return this.f2183g;
    }

    public void setTransparency(float f) throws RemoteException {
        boolean z = f >= 0.0f && f <= 1.0f;
        AMapThrowException.m2228b(z, "Transparency must be in the range [0..1]");
        this.f2186j = f;
        this.f2177a.setRunLowFrame(false);
    }

    public float getTransparency() throws RemoteException {
        return this.f2186j;
    }

    public void setImage(BitmapDescriptor bitmapDescriptor) throws RemoteException {
        this.f2178b = bitmapDescriptor;
        m2938d();
        if (this.f2193q) {
            this.f2193q = false;
        }
        this.f2177a.setRunLowFrame(false);
    }

    public void setAnchor(float f, float f2) throws RemoteException {
        this.f2187k = f;
        this.f2188l = f2;
        this.f2177a.setRunLowFrame(false);
    }

    public void reLoadTexture() {
        this.f2193q = false;
        this.f2192p = 0;
    }

    public boolean isDrawFinish() {
        return this.f2194r;
    }
}
