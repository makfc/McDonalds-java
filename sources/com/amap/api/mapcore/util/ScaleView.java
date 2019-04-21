package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.p000v4.view.ViewCompat;
import android.view.View;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.VTMCDataCache;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.mcdonalds.sdk.connectors.autonavi.AutoNaviConnector;
import com.newrelic.agent.android.tracing.ActivityTrace;

/* renamed from: com.amap.api.mapcore.util.ar */
class ScaleView extends View {
    /* renamed from: a */
    private String f1160a = "";
    /* renamed from: b */
    private int f1161b = 0;
    /* renamed from: c */
    private IAMapDelegate f1162c;
    /* renamed from: d */
    private Paint f1163d;
    /* renamed from: e */
    private Paint f1164e;
    /* renamed from: f */
    private Rect f1165f;
    /* renamed from: g */
    private final int[] f1166g = new int[]{10000000, 5000000, 2000000, 1000000, 500000, 200000, 100000, AutoNaviConnector.DEFAULT_SEARCH_RADIUS, 30000, 20000, 10000, 5000, ActivityTrace.MAX_TRACES, 1000, VTMCDataCache.MAXSIZE, 200, 100, 50, 25, 10, 5};

    /* renamed from: a */
    public void mo8672a() {
        this.f1163d = null;
        this.f1164e = null;
        this.f1165f = null;
        this.f1160a = null;
    }

    public ScaleView(Context context) {
        super(context);
    }

    public ScaleView(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.f1162c = iAMapDelegate;
        this.f1163d = new Paint();
        this.f1165f = new Rect();
        this.f1163d.setAntiAlias(true);
        this.f1163d.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f1163d.setStrokeWidth(2.0f * ConfigableConst.f2121a);
        this.f1163d.setStyle(Style.STROKE);
        this.f1164e = new Paint();
        this.f1164e.setAntiAlias(true);
        this.f1164e.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f1164e.setTextSize(20.0f * ConfigableConst.f2121a);
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        if (this.f1160a != null && !this.f1160a.equals("") && this.f1161b != 0) {
            Point waterMarkerPositon = this.f1162c.getWaterMarkerPositon();
            if (waterMarkerPositon != null) {
                this.f1164e.getTextBounds(this.f1160a, 0, this.f1160a.length(), this.f1165f);
                int i = waterMarkerPositon.x;
                int height = (waterMarkerPositon.y - this.f1165f.height()) + 5;
                canvas.drawText(this.f1160a, (float) i, (float) height, this.f1164e);
                int height2 = height + (this.f1165f.height() - 5);
                canvas.drawLine((float) i, (float) (height2 - 2), (float) i, (float) (height2 + 2), this.f1163d);
                canvas.drawLine((float) i, (float) height2, (float) (this.f1161b + i), (float) height2, this.f1163d);
                canvas.drawLine((float) (this.f1161b + i), (float) (height2 - 2), (float) (this.f1161b + i), (float) (height2 + 2), this.f1163d);
            }
        }
    }

    /* renamed from: a */
    public void mo8674a(String str) {
        this.f1160a = str;
    }

    /* renamed from: a */
    public void mo8673a(int i) {
        this.f1161b = i;
    }

    /* renamed from: a */
    public void mo8675a(boolean z) {
        if (z) {
            setVisibility(0);
            mo8676b();
            return;
        }
        mo8674a("");
        mo8673a(0);
        setVisibility(8);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo8676b() {
        if (this.f1162c != null) {
            try {
                CameraPosition cameraPosition = this.f1162c.getCameraPosition();
                if (cameraPosition != null) {
                    LatLng latLng = cameraPosition.target;
                    float zoomLevel = this.f1162c.getZoomLevel();
                    double cos = (double) ((float) ((((Math.cos((latLng.latitude * 3.141592653589793d) / 180.0d) * 2.0d) * 3.141592653589793d) * 6378137.0d) / (256.0d * Math.pow(2.0d, (double) zoomLevel))));
                    int mapZoomScale = (int) (((double) this.f1166g[(int) zoomLevel]) / (((double) this.f1162c.getMapZoomScale()) * cos));
                    String b = Util.m2368b(this.f1166g[(int) zoomLevel]);
                    mo8673a(mapZoomScale);
                    mo8674a(b);
                    invalidate();
                }
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "AMapDelegateImpGLSurfaceView", "changeScaleState");
                th.printStackTrace();
            }
        }
    }
}
