package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.opengl.GLES10;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.TextOptions;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IMarkerDelegate;
import com.autonavi.amap.mapcore.interfaces.ITextDelegate;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.util.at */
class TextDelegateImp implements ITextDelegate {
    /* renamed from: a */
    private static int f1175a = 0;
    /* renamed from: A */
    private Paint f1176A = new Paint();
    /* renamed from: B */
    private Handler f1177B = new Handler();
    /* renamed from: C */
    private Runnable f1178C = new C0737au(this);
    /* renamed from: D */
    private boolean f1179D = false;
    /* renamed from: E */
    private boolean f1180E = false;
    /* renamed from: b */
    private float f1181b = 0.0f;
    /* renamed from: c */
    private float f1182c = 0.0f;
    /* renamed from: d */
    private int f1183d = 4;
    /* renamed from: e */
    private int f1184e = 32;
    /* renamed from: f */
    private FPoint f1185f = new FPoint();
    /* renamed from: g */
    private int f1186g;
    /* renamed from: h */
    private Bitmap f1187h;
    /* renamed from: i */
    private int f1188i;
    /* renamed from: j */
    private int f1189j;
    /* renamed from: k */
    private FloatBuffer f1190k = null;
    /* renamed from: l */
    private String f1191l;
    /* renamed from: m */
    private LatLng f1192m;
    /* renamed from: n */
    private float f1193n = 0.5f;
    /* renamed from: o */
    private float f1194o = 1.0f;
    /* renamed from: p */
    private boolean f1195p = true;
    /* renamed from: q */
    private MapOverlayImageView f1196q;
    /* renamed from: r */
    private FloatBuffer f1197r;
    /* renamed from: s */
    private Object f1198s;
    /* renamed from: t */
    private String f1199t;
    /* renamed from: u */
    private int f1200u;
    /* renamed from: v */
    private int f1201v;
    /* renamed from: w */
    private int f1202w;
    /* renamed from: x */
    private Typeface f1203x;
    /* renamed from: y */
    private float f1204y;
    /* renamed from: z */
    private Rect f1205z = new Rect();

    /* renamed from: a */
    private static String m1640a(String str) {
        f1175a++;
        return str + f1175a;
    }

    public void setRotateAngle(float f) {
        this.f1182c = f;
        this.f1181b = (((-f) % 360.0f) + 360.0f) % 360.0f;
        m1649d();
    }

    public boolean isDestory() {
        return this.f1179D;
    }

    public synchronized void realDestroy() {
        if (this.f1179D) {
            try {
                remove();
                if (this.f1187h != null) {
                    this.f1187h.recycle();
                    this.f1187h = null;
                }
                if (this.f1197r != null) {
                    this.f1197r.clear();
                    this.f1197r = null;
                }
                if (this.f1190k != null) {
                    this.f1190k.clear();
                    this.f1190k = null;
                }
                this.f1192m = null;
                this.f1198s = null;
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "TextDelegateImp", "realdestroy");
                th.printStackTrace();
                Log.d("destroy erro", "TextDelegateImp destroy");
            }
        }
        return;
    }

    public void destroy() {
        try {
            this.f1179D = true;
            if (!(this.f1196q == null || this.f1196q.f986a == null)) {
                this.f1196q.f986a.callRunDestroy();
            }
            this.f1186g = 0;
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "TextDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "TextDelegateImp destroy");
        }
    }

    public TextDelegateImp(TextOptions textOptions, MapOverlayImageView mapOverlayImageView) throws RemoteException {
        this.f1196q = mapOverlayImageView;
        if (textOptions.getPosition() != null) {
            this.f1192m = textOptions.getPosition();
        }
        setAlign(textOptions.getAlignX(), textOptions.getAlignY());
        this.f1195p = textOptions.isVisible();
        this.f1199t = textOptions.getText();
        this.f1200u = textOptions.getBackgroundColor();
        this.f1201v = textOptions.getFontColor();
        this.f1202w = textOptions.getFontSize();
        this.f1198s = textOptions.getObject();
        this.f1204y = textOptions.getZIndex();
        this.f1203x = textOptions.getTypeface();
        this.f1191l = getId();
        setRotateAngle(textOptions.getRotate());
        m1641a();
        calFPoint();
    }

    /* renamed from: a */
    private void m1641a() {
        if (this.f1199t != null && this.f1199t.trim().length() > 0) {
            try {
                this.f1176A.setTypeface(this.f1203x);
                this.f1176A.setSubpixelText(true);
                this.f1176A.setAntiAlias(true);
                this.f1176A.setStrokeWidth(5.0f);
                this.f1176A.setStrokeCap(Cap.ROUND);
                this.f1176A.setTextSize((float) this.f1202w);
                this.f1176A.setTextAlign(Align.CENTER);
                this.f1176A.setColor(this.f1201v);
                FontMetrics fontMetrics = this.f1176A.getFontMetrics();
                int i = (int) (fontMetrics.descent - fontMetrics.ascent);
                int i2 = (int) (((((float) i) - fontMetrics.bottom) - fontMetrics.top) / 2.0f);
                this.f1176A.getTextBounds(this.f1199t, 0, this.f1199t.length(), this.f1205z);
                Bitmap createBitmap = Bitmap.createBitmap(this.f1205z.width() + 6, i, Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawColor(this.f1200u);
                canvas.drawText(this.f1199t, (float) (this.f1205z.centerX() + 3), (float) i2, this.f1176A);
                this.f1187h = createBitmap;
                this.f1188i = this.f1187h.getWidth();
                this.f1189j = this.f1187h.getHeight();
                this.f1197r = Util.m2355a(new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f});
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "TextDelegateImp", "initBitmap");
            }
        }
    }

    /* renamed from: b */
    private int m1646b() {
        return this.f1188i;
    }

    /* renamed from: c */
    private int m1648c() {
        return this.f1189j;
    }

    public FPoint anchorUVoff() {
        return null;
    }

    public boolean isContains() {
        return this.f1196q.mo8501a((IMarkerDelegate) this);
    }

    public IPoint getAnchor() {
        return null;
    }

    public synchronized boolean remove() {
        m1649d();
        this.f1195p = false;
        return this.f1196q.mo8507c(this);
    }

    /* renamed from: d */
    private void m1649d() {
        if (this.f1196q.f986a != null) {
            this.f1196q.f986a.setRunLowFrame(false);
        }
    }

    public LatLng getPosition() {
        return this.f1192m;
    }

    public String getId() {
        if (this.f1191l == null) {
            this.f1191l = TextDelegateImp.m1640a("Text");
        }
        return this.f1191l;
    }

    public void setPosition(LatLng latLng) {
        this.f1192m = latLng;
        calFPoint();
        m1649d();
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

    public synchronized void setIcons(ArrayList<BitmapDescriptor> arrayList) {
    }

    public synchronized ArrayList<BitmapDescriptor> getIcons() {
        return null;
    }

    public synchronized void setIcon(BitmapDescriptor bitmapDescriptor) {
    }

    public synchronized BitmapDescriptor getBitmapDescriptor() {
        return null;
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
        if (this.f1195p != z) {
            this.f1195p = z;
            m1649d();
        }
    }

    public boolean isVisible() {
        return this.f1195p;
    }

    public void setZIndex(float f) {
        this.f1204y = f;
        this.f1196q.mo8516i();
    }

    public float getZIndex() {
        return this.f1204y;
    }

    public void setAnchor(float f, float f2) {
    }

    public float getAnchorU() {
        return this.f1193n;
    }

    public float getAnchorV() {
        return this.f1194o;
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
        if (this.f1192m == null) {
            return false;
        }
        this.f1196q.f986a.getLatLng2Map(this.f1192m.latitude, this.f1192m.longitude, this.f1185f);
        return true;
    }

    /* renamed from: a */
    private void m1644a(IAMapDelegate iAMapDelegate) throws RemoteException {
        float[] a = Util.m2365a(iAMapDelegate, 0, this.f1185f, this.f1181b, m1646b(), m1648c(), this.f1193n, this.f1194o);
        if (this.f1190k == null) {
            this.f1190k = Util.m2355a(a);
        } else {
            this.f1190k = Util.m2356a(a, this.f1190k);
        }
        if (this.f1186g != 0) {
            m1642a(this.f1186g, this.f1190k, this.f1197r);
        }
    }

    /* renamed from: a */
    private void m1642a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (i != 0 && floatBuffer != null && floatBuffer2 != null) {
            GLES10.glEnable(3042);
            GLES10.glBlendFunc(1, 771);
            GLES10.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GLES10.glEnable(3553);
            GLES10.glEnableClientState(32884);
            GLES10.glEnableClientState(32888);
            GLES10.glBindTexture(3553, i);
            GLES10.glVertexPointer(3, 5126, 0, floatBuffer);
            GLES10.glTexCoordPointer(2, 5126, 0, floatBuffer2);
            GLES10.glDrawArrays(6, 0, 4);
            GLES10.glDisableClientState(32884);
            GLES10.glDisableClientState(32888);
            GLES10.glDisable(3553);
            GLES10.glDisable(3042);
        }
    }

    public void drawMarker(GL10 gl10, IAMapDelegate iAMapDelegate) {
        if (this.f1195p && !this.f1179D && this.f1192m != null && this.f1187h != null) {
            if (!this.f1180E) {
                try {
                    if (!(this.f1187h == null || this.f1187h.isRecycled())) {
                        if (this.f1186g == 0) {
                            this.f1186g = m1639a(gl10);
                        }
                        Util.m2367b(gl10, this.f1186g, this.f1187h, false);
                        this.f1180E = true;
                        this.f1187h.recycle();
                    }
                } catch (Throwable th) {
                    SDKLogHandler.m2563a(th, "TextDelegateImp", "loadtexture");
                    th.printStackTrace();
                    return;
                }
            }
            try {
                m1644a(iAMapDelegate);
            } catch (Throwable th2) {
                SDKLogHandler.m2563a(th2, "TextDelegateImp", "drawMarker");
            }
        }
    }

    /* renamed from: a */
    private int m1639a(GL10 gl10) {
        int texsureId = this.f1196q.f986a.getTexsureId();
        if (texsureId != 0) {
            return texsureId;
        }
        int[] iArr = new int[]{0};
        gl10.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    public boolean isAllowLow() {
        return true;
    }

    public void setPeriod(int i) {
    }

    public void setObject(Object obj) {
        this.f1198s = obj;
    }

    public Object getObject() {
        return this.f1198s;
    }

    public void setPerspective(boolean z) {
    }

    public boolean isPerspective() {
        return false;
    }

    public int getTextureId() {
        try {
            return this.f1186g;
        } catch (Throwable th) {
            return 0;
        }
    }

    public int getPeriod() {
        return 0;
    }

    public LatLng getRealPosition() {
        return this.f1192m;
    }

    public void set2Top() {
        this.f1196q.mo8508d(this);
    }

    public void setFlat(boolean z) throws RemoteException {
    }

    public boolean isFlat() {
        return false;
    }

    public float getRotateAngle() {
        return this.f1182c;
    }

    public void setInfoWindowOffset(int i, int i2) throws RemoteException {
    }

    public int getInfoWindowOffsetX() {
        return 0;
    }

    public int getInfoWindowOffsetY() {
        return 0;
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
        return this.f1185f;
    }

    public boolean isViewMode() {
        return false;
    }

    public Rect getRect() {
        return null;
    }

    public void setText(String str) throws RemoteException {
        this.f1199t = str;
        m1650e();
    }

    public String getText() throws RemoteException {
        return this.f1199t;
    }

    public void setBackgroundColor(int i) throws RemoteException {
        this.f1200u = i;
        m1650e();
    }

    public int getBackgroundColor() throws RemoteException {
        return this.f1200u;
    }

    public void setFontColor(int i) throws RemoteException {
        this.f1201v = i;
        m1650e();
    }

    public int getFontColor() throws RemoteException {
        return this.f1201v;
    }

    public void setFontSize(int i) throws RemoteException {
        this.f1202w = i;
        m1650e();
    }

    public int getFontSize() throws RemoteException {
        return this.f1202w;
    }

    public void setTypeface(Typeface typeface) throws RemoteException {
        this.f1203x = typeface;
        m1650e();
    }

    public Typeface getTypeface() throws RemoteException {
        return this.f1203x;
    }

    public void setAlign(int i, int i2) throws RemoteException {
        this.f1183d = i;
        switch (i) {
            case 1:
                this.f1193n = 0.0f;
                break;
            case 2:
                this.f1193n = 1.0f;
                break;
            case 4:
                this.f1193n = 0.5f;
                break;
            default:
                this.f1193n = 0.5f;
                break;
        }
        this.f1184e = i2;
        switch (i2) {
            case 8:
                this.f1194o = 0.0f;
                break;
            case 16:
                this.f1194o = 1.0f;
                break;
            case 32:
                this.f1194o = 0.5f;
                break;
            default:
                this.f1194o = 0.5f;
                break;
        }
        m1649d();
    }

    public int getAlignX() throws RemoteException {
        return this.f1183d;
    }

    public int getAlignY() {
        return this.f1184e;
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }

    /* renamed from: e */
    private void m1650e() {
        this.f1177B.removeCallbacks(this.f1178C);
        this.f1177B.post(this.f1178C);
    }

    public boolean checkInBounds() {
        Rect rect = this.f1196q.f986a.getRect();
        if (rect == null) {
            return true;
        }
        IPoint iPoint = new IPoint();
        if (this.f1192m != null) {
            this.f1196q.f986a.getLatLng2Pixel(this.f1192m.latitude, this.f1192m.longitude, iPoint);
        }
        return rect.contains(iPoint.f4562x, iPoint.f4563y);
    }

    public void setInfoWindowShown(boolean z) {
    }

    public void setGeoPoint(IPoint iPoint) {
    }

    public IPoint getGeoPoint() {
        return null;
    }

    public void reLoadTexture() {
        this.f1180E = false;
        this.f1186g = 0;
        m1641a();
    }
}
