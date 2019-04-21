package com.amap.api.mapcore2d;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.support.p000v4.view.ViewCompat;
import android.view.View;
import com.amap.api.mapcore2d.C1042p.C1041a;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.InputStream;

/* compiled from: WaterMarkerView */
/* renamed from: com.amap.api.mapcore2d.ca */
class C0947ca extends View {
    /* renamed from: a */
    private Bitmap f2661a;
    /* renamed from: b */
    private Bitmap f2662b;
    /* renamed from: c */
    private Paint f2663c = new Paint();
    /* renamed from: d */
    private boolean f2664d = false;
    /* renamed from: e */
    private int f2665e = 0;
    /* renamed from: f */
    private AMapDelegateImpGLSurfaceView f2666f;
    /* renamed from: g */
    private int f2667g = 0;
    /* renamed from: h */
    private int f2668h = 10;

    /* renamed from: a */
    public void mo10138a() {
        String str = "destory";
        try {
            if (this.f2661a != null) {
                this.f2661a.recycle();
            }
            if (this.f2662b != null) {
                this.f2662b.recycle();
            }
            this.f2661a = null;
            this.f2662b = null;
            this.f2663c = null;
        } catch (Exception e) {
            C0955ck.m3888a(e, "WaterMarkerView", str);
        }
    }

    public C0947ca(Context context, AMapDelegateImpGLSurfaceView aMapDelegateImpGLSurfaceView) {
        super(context);
        String str = "WaterMarkerView";
        this.f2666f = aMapDelegateImpGLSurfaceView;
        AssetManager assets = context.getResources().getAssets();
        try {
            InputStream open;
            if (C1042p.f3035e == C1041a.ALIBABA) {
                open = assets.open("apl2d.data");
            } else {
                open = assets.open("ap2d.data");
            }
            this.f2661a = BitmapFactoryInstrumentation.decodeStream(open);
            this.f2661a = C0955ck.m3881a(this.f2661a, C1042p.f3031a);
            open.close();
            if (C1042p.f3035e == C1041a.ALIBABA) {
                open = assets.open("apl12d.data");
            } else {
                open = assets.open("ap12d.data");
            }
            this.f2662b = BitmapFactoryInstrumentation.decodeStream(open);
            this.f2662b = C0955ck.m3881a(this.f2662b, C1042p.f3031a);
            open.close();
            this.f2665e = this.f2662b.getHeight();
        } catch (Throwable th) {
            C0955ck.m3888a(th, "WaterMarkerView", str);
        }
        this.f2663c.setAntiAlias(true);
        this.f2663c.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f2663c.setStyle(Style.STROKE);
    }

    /* renamed from: b */
    public Bitmap mo10141b() {
        if (this.f2664d) {
            return this.f2662b;
        }
        return this.f2661a;
    }

    /* renamed from: a */
    public void mo10140a(boolean z) {
        this.f2664d = z;
        invalidate();
    }

    /* renamed from: c */
    public Point mo10142c() {
        return new Point(this.f2668h, (getHeight() - this.f2665e) - 10);
    }

    /* renamed from: a */
    public void mo10139a(int i) {
        this.f2667g = i;
    }

    public void onDraw(Canvas canvas) {
        if (this.f2662b != null) {
            int width = this.f2662b.getWidth() + 3;
            if (this.f2667g == 1) {
                this.f2668h = (this.f2666f.getWidth() - width) / 2;
            } else if (this.f2667g == 2) {
                this.f2668h = (this.f2666f.getWidth() - width) - 10;
            } else {
                this.f2668h = 10;
            }
            if (C1042p.f3035e == C1041a.ALIBABA) {
                canvas.drawBitmap(mo10141b(), (float) (this.f2668h + 15), (float) ((getHeight() - this.f2665e) - 8), this.f2663c);
            } else {
                canvas.drawBitmap(mo10141b(), (float) this.f2668h, (float) ((getHeight() - this.f2665e) - 8), this.f2663c);
            }
        }
    }
}
