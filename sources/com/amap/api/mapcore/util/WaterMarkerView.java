package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.support.p000v4.view.ViewCompat;
import android.view.View;
import com.amap.api.mapcore.util.ConfigableConst.C0866a;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.InputStream;

/* renamed from: com.amap.api.mapcore.util.az */
class WaterMarkerView extends View {
    /* renamed from: a */
    int f1285a = 10;
    /* renamed from: b */
    private Bitmap f1286b;
    /* renamed from: c */
    private Bitmap f1287c;
    /* renamed from: d */
    private Bitmap f1288d;
    /* renamed from: e */
    private Bitmap f1289e;
    /* renamed from: f */
    private Paint f1290f = new Paint();
    /* renamed from: g */
    private boolean f1291g = false;
    /* renamed from: h */
    private int f1292h = 0;
    /* renamed from: i */
    private AMapDelegateImp f1293i;
    /* renamed from: j */
    private int f1294j = 0;

    /* renamed from: a */
    public void mo8778a() {
        try {
            if (this.f1286b != null) {
                this.f1286b.recycle();
            }
            if (this.f1287c != null) {
                this.f1287c.recycle();
            }
            this.f1286b = null;
            this.f1287c = null;
            if (this.f1288d != null) {
                this.f1288d.recycle();
                this.f1288d = null;
            }
            if (this.f1289e != null) {
                this.f1289e.recycle();
                this.f1289e = null;
            }
            this.f1290f = null;
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "WaterMarkerView", "destory");
            th.printStackTrace();
        }
    }

    public WaterMarkerView(Context context) {
        super(context);
    }

    public WaterMarkerView(Context context, AMapDelegateImp aMapDelegateImp) {
        super(context);
        this.f1293i = aMapDelegateImp;
        try {
            InputStream open;
            if (ConfigableConst.f2127g == C0866a.ALIBABA) {
                open = ResourcesUtil.m2327a(context).open("apl.data");
            } else {
                open = ResourcesUtil.m2327a(context).open("ap.data");
            }
            this.f1288d = BitmapFactoryInstrumentation.decodeStream(open);
            this.f1286b = Util.m2345a(this.f1288d, ConfigableConst.f2121a);
            open.close();
            if (ConfigableConst.f2127g == C0866a.ALIBABA) {
                open = ResourcesUtil.m2327a(context).open("apl1.data");
            } else {
                open = ResourcesUtil.m2327a(context).open("ap1.data");
            }
            this.f1289e = BitmapFactoryInstrumentation.decodeStream(open);
            this.f1287c = Util.m2345a(this.f1289e, ConfigableConst.f2121a);
            open.close();
            this.f1292h = this.f1287c.getHeight();
            this.f1290f.setAntiAlias(true);
            this.f1290f.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.f1290f.setStyle(Style.STROKE);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "WaterMarkerView", "create");
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    public Bitmap mo8781b() {
        if (this.f1291g) {
            return this.f1287c;
        }
        return this.f1286b;
    }

    /* renamed from: a */
    public void mo8780a(boolean z) {
        try {
            this.f1291g = z;
            if (z) {
                this.f1290f.setColor(-1);
            } else {
                this.f1290f.setColor(ViewCompat.MEASURED_STATE_MASK);
            }
            invalidate();
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "WaterMarkerView", "changeBitmap");
            th.printStackTrace();
        }
    }

    /* renamed from: c */
    public Point mo8782c() {
        return new Point(this.f1285a, (getHeight() - this.f1292h) - 10);
    }

    /* renamed from: a */
    public void mo8779a(int i) {
        this.f1294j = i;
    }

    public void onDraw(Canvas canvas) {
        try {
            if (this.f1287c != null) {
                int width = this.f1287c.getWidth();
                if (this.f1294j == 1) {
                    this.f1285a = (this.f1293i.mo9173c() - width) / 2;
                } else if (this.f1294j == 2) {
                    this.f1285a = (this.f1293i.mo9173c() - width) - 10;
                } else {
                    this.f1285a = 10;
                }
                if (ConfigableConst.f2127g == C0866a.ALIBABA) {
                    canvas.drawBitmap(mo8781b(), (float) (this.f1285a + 15), (float) ((getHeight() - this.f1292h) - 8), this.f1290f);
                } else {
                    canvas.drawBitmap(mo8781b(), (float) this.f1285a, (float) ((getHeight() - this.f1292h) - 8), this.f1290f);
                }
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "WaterMarkerView", "onDraw");
            th.printStackTrace();
        }
    }
}
