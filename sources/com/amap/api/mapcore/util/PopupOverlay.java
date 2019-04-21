package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IMarkerDelegate;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.util.ap */
class PopupOverlay implements IMarkerDelegate {
    /* renamed from: a */
    private boolean f1140a = false;
    /* renamed from: b */
    private int f1141b = 0;
    /* renamed from: c */
    private int f1142c = 0;
    /* renamed from: d */
    private FloatBuffer f1143d = null;
    /* renamed from: e */
    private String f1144e;
    /* renamed from: f */
    private FPoint f1145f;
    /* renamed from: g */
    private BitmapDescriptor f1146g;
    /* renamed from: h */
    private boolean f1147h = true;
    /* renamed from: i */
    private FloatBuffer f1148i;
    /* renamed from: j */
    private Object f1149j;
    /* renamed from: k */
    private int f1150k;
    /* renamed from: l */
    private IAMapDelegate f1151l = null;
    /* renamed from: m */
    private MapProjection f1152m = null;
    /* renamed from: n */
    private float f1153n = 0.5f;
    /* renamed from: o */
    private float f1154o = 1.0f;
    /* renamed from: p */
    private boolean f1155p;
    /* renamed from: q */
    private boolean f1156q = false;
    /* renamed from: r */
    private boolean f1157r = true;
    /* renamed from: s */
    private int f1158s = 20;

    public boolean isDestory() {
        return this.f1140a;
    }

    public void realDestroy() {
        if (this.f1140a) {
            try {
                remove();
                if (this.f1146g != null) {
                    Bitmap bitmap = this.f1146g.getBitmap();
                    if (bitmap != null) {
                        bitmap.recycle();
                        this.f1146g = null;
                    }
                }
                if (this.f1148i != null) {
                    this.f1148i.clear();
                    this.f1148i = null;
                }
                if (this.f1143d != null) {
                    this.f1143d.clear();
                    this.f1143d = null;
                }
                this.f1145f = null;
                this.f1149j = null;
                this.f1150k = 0;
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "PopupOverlay", "realDestroy");
                th.printStackTrace();
                Log.d("destroy erro", "MarkerDelegateImp destroy");
            }
        }
    }

    /* renamed from: a */
    private void m1622a(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            this.f1150k = 0;
            this.f1146g = bitmapDescriptor;
        }
    }

    public PopupOverlay(MarkerOptions markerOptions, IAMapDelegate iAMapDelegate) {
        this.f1151l = iAMapDelegate;
        this.f1152m = iAMapDelegate.getMapProjection();
        m1622a(markerOptions.getIcon());
        this.f1141b = markerOptions.getInfoWindowOffsetX();
        this.f1142c = markerOptions.getInfoWindowOffsetY();
        this.f1147h = markerOptions.isVisible();
        this.f1144e = getId();
        calFPoint();
    }

    /* renamed from: b */
    public IPoint mo8664b() {
        if (this.f1145f == null) {
            return null;
        }
        IPoint iPoint = new IPoint();
        this.f1151l.getMapProjection().map2Win(this.f1145f.f4560x, this.f1145f.f4561y, iPoint);
        return iPoint;
    }

    public int getWidth() {
        try {
            return getBitmapDescriptor().getWidth();
        } catch (Throwable th) {
            return 0;
        }
    }

    public int getHeight() {
        try {
            return getBitmapDescriptor().getHeight();
        } catch (Throwable th) {
            return 0;
        }
    }

    public FPoint anchorUVoff() {
        FPoint fPoint = new FPoint();
        if (this.f1146g != null) {
            fPoint.f4560x = ((float) getWidth()) * this.f1153n;
            fPoint.f4561y = ((float) getHeight()) * this.f1154o;
        }
        return fPoint;
    }

    public boolean isContains() {
        return false;
    }

    public IPoint getAnchor() {
        IPoint b = mo8664b();
        if (b == null) {
            return null;
        }
        return b;
    }

    public Rect getRect() {
        return null;
    }

    public boolean remove() {
        m1625c();
        if (this.f1150k != 0) {
            this.f1151l.deleteTexsureId(this.f1150k);
        }
        return true;
    }

    /* renamed from: c */
    private void m1625c() {
        if (this.f1151l != null) {
            this.f1151l.setRunLowFrame(false);
        }
    }

    public LatLng getPosition() {
        return null;
    }

    public String getId() {
        if (this.f1144e == null) {
            this.f1144e = "PopupOverlay";
        }
        return this.f1144e;
    }

    /* renamed from: a */
    public void mo8662a(FPoint fPoint) {
        if (fPoint == null || !fPoint.equals(this.f1145f)) {
            this.f1145f = fPoint;
        }
    }

    public void setPosition(LatLng latLng) {
    }

    public void setTitle(String str) {
    }

    public String getTitle() {
        return null;
    }

    public void setSnippet(String str) {
    }

    public String getSnippet() {
        return null;
    }

    public void setDraggable(boolean z) {
    }

    public void setIcons(ArrayList<BitmapDescriptor> arrayList) {
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        return null;
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            this.f1146g = bitmapDescriptor;
            this.f1156q = false;
            if (this.f1148i != null) {
                this.f1148i.clear();
                this.f1148i = null;
            }
            m1625c();
        }
    }

    public BitmapDescriptor getBitmapDescriptor() {
        return this.f1146g;
    }

    public boolean isDraggable() {
        return false;
    }

    public void showInfoWindow() {
    }

    public void hideInfoWindow() {
    }

    public boolean isInfoWindowShown() {
        return false;
    }

    public void setVisible(boolean z) {
        if (!this.f1147h && z) {
            this.f1155p = true;
        }
        this.f1147h = z;
    }

    public boolean isVisible() {
        return this.f1147h;
    }

    public void setAnchor(float f, float f2) {
        if (this.f1153n != f || this.f1154o != f2) {
            this.f1153n = f;
            this.f1154o = f2;
        }
    }

    public float getAnchorU() {
        return this.f1153n;
    }

    public float getAnchorV() {
        return this.f1154o;
    }

    public boolean equalsRemote(IMarkerDelegate iMarkerDelegate) throws RemoteException {
        if (equals(iMarkerDelegate) || iMarkerDelegate.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() {
        return super.hashCode();
    }

    public boolean calFPoint() {
        if (this.f1145f == null) {
            return false;
        }
        IPoint iPoint = new IPoint();
        this.f1151l.getMapProjection().map2Win(this.f1145f.f4560x, this.f1145f.f4561y, iPoint);
        int width = getWidth();
        int height = getHeight();
        int i = (int) (((float) (iPoint.f4562x + this.f1141b)) - (((float) width) * this.f1153n));
        int i2 = (int) (((float) (iPoint.f4563y + this.f1142c)) + (((float) height) * (1.0f - this.f1154o)));
        if (i - width > this.f1151l.getMapWidth() || i < (-width) * 2 || i2 < (-height) * 2 || i2 - height > this.f1151l.getMapHeight() || this.f1146g == null) {
            return false;
        }
        width = this.f1146g.getWidth();
        float width2 = ((float) width) / ((float) this.f1146g.getBitmap().getWidth());
        float height2 = ((float) this.f1146g.getHeight()) / ((float) this.f1146g.getBitmap().getHeight());
        if (this.f1148i == null) {
            this.f1148i = Util.m2355a(new float[]{0.0f, height2, width2, height2, width2, 0.0f, 0.0f, 0.0f});
        }
        float[] fArr = new float[]{(float) i, (float) (this.f1151l.getMapHeight() - i2), 0.0f, (float) (i + width), (float) (this.f1151l.getMapHeight() - i2), 0.0f, (float) (width + i), (float) ((this.f1151l.getMapHeight() - i2) + height), 0.0f, (float) i, (float) ((this.f1151l.getMapHeight() - i2) + height), 0.0f};
        if (this.f1143d == null) {
            this.f1143d = Util.m2355a(fArr);
        } else {
            this.f1143d = Util.m2356a(fArr, this.f1143d);
        }
        return true;
    }

    /* renamed from: a */
    private void m1623a(GL10 gl10, int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (floatBuffer != null && floatBuffer2 != null) {
            gl10.glEnable(3042);
            gl10.glBlendFunc(1, 771);
            gl10.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
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

    /* renamed from: a */
    public void mo8663a(GL10 gl10) {
        if (this.f1147h && this.f1145f != null && getBitmapDescriptor() != null) {
            if (!this.f1156q) {
                try {
                    if (this.f1150k != 0) {
                        gl10.glDeleteTextures(1, new int[]{this.f1150k}, 0);
                        this.f1151l.deleteTexsureId(this.f1150k);
                    }
                    this.f1150k = m1624b(gl10);
                    if (this.f1146g != null) {
                        Bitmap bitmap = this.f1146g.getBitmap();
                        if (!(bitmap == null || bitmap.isRecycled())) {
                            Util.m2367b(gl10, this.f1150k, bitmap, false);
                        }
                        this.f1156q = true;
                    }
                } catch (Throwable th) {
                    SDKLogHandler.m2563a(th, "PopupOverlay", "drawMarker");
                    th.printStackTrace();
                    return;
                }
            }
            if (calFPoint()) {
                gl10.glLoadIdentity();
                gl10.glViewport(0, 0, this.f1151l.getMapWidth(), this.f1151l.getMapHeight());
                gl10.glMatrixMode(5889);
                gl10.glLoadIdentity();
                gl10.glOrthof(0.0f, (float) this.f1151l.getMapWidth(), 0.0f, (float) this.f1151l.getMapHeight(), 1.0f, -1.0f);
                m1623a(gl10, this.f1150k, this.f1143d, this.f1148i);
                if (this.f1155p) {
                    mo8661a();
                    this.f1155p = false;
                }
            }
        }
    }

    /* renamed from: a */
    public void mo8661a() {
    }

    /* renamed from: b */
    private int m1624b(GL10 gl10) {
        int texsureId = this.f1151l.getTexsureId();
        if (texsureId != 0) {
            return texsureId;
        }
        int[] iArr = new int[]{0};
        gl10.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    public boolean isAllowLow() {
        return this.f1157r;
    }

    public void setPeriod(int i) {
        if (i <= 1) {
            this.f1158s = 1;
        } else {
            this.f1158s = i;
        }
    }

    public void setObject(Object obj) {
        this.f1149j = obj;
    }

    public Object getObject() {
        return this.f1149j;
    }

    public void setPerspective(boolean z) {
    }

    public boolean isPerspective() {
        return false;
    }

    public int getTextureId() {
        return this.f1150k;
    }

    public int getPeriod() {
        return this.f1158s;
    }

    public LatLng getRealPosition() {
        return null;
    }

    public void set2Top() {
    }

    public void setFlat(boolean z) throws RemoteException {
        m1625c();
    }

    public boolean isFlat() {
        return false;
    }

    public void setRotateAngle(float f) throws RemoteException {
    }

    public void destroy() {
    }

    public void drawMarker(GL10 gl10, IAMapDelegate iAMapDelegate) {
    }

    public float getRotateAngle() {
        return 0.0f;
    }

    public void setInfoWindowOffset(int i, int i2) throws RemoteException {
        this.f1141b = i;
        this.f1142c = i2;
    }

    public int getInfoWindowOffsetX() {
        return this.f1141b;
    }

    public int getInfoWindowOffsetY() {
        return this.f1142c;
    }

    public void setPositionByPixels(int i, int i2) {
    }

    public int getRealInfoWindowOffsetX() {
        return 0;
    }

    public int getRealInfoWindowOffsetY() {
        return 0;
    }

    public FPoint getMapPosition() {
        return this.f1145f;
    }

    public boolean isViewMode() {
        return false;
    }

    public void setZIndex(float f) {
    }

    public float getZIndex() {
        return 0.0f;
    }

    public boolean checkInBounds() {
        return false;
    }

    public void setInfoWindowShown(boolean z) {
    }

    public void setGeoPoint(IPoint iPoint) {
    }

    public IPoint getGeoPoint() {
        return null;
    }

    public void reLoadTexture() {
        this.f1156q = false;
        this.f1150k = 0;
    }
}
