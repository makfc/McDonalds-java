package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.p000v4.view.ViewCompat;
import android.view.View;

/* compiled from: ScaleView */
/* renamed from: com.amap.api.mapcore2d.bi */
class C0933bi extends View {
    /* renamed from: a */
    private String f2567a = "";
    /* renamed from: b */
    private int f2568b = 0;
    /* renamed from: c */
    private AMapDelegateImpGLSurfaceView f2569c;
    /* renamed from: d */
    private Paint f2570d;
    /* renamed from: e */
    private Paint f2571e;
    /* renamed from: f */
    private Rect f2572f;

    /* renamed from: a */
    public void mo10080a() {
        this.f2570d = null;
        this.f2571e = null;
        this.f2572f = null;
        this.f2567a = null;
    }

    public C0933bi(Context context, AMapDelegateImpGLSurfaceView aMapDelegateImpGLSurfaceView) {
        super(context);
        this.f2569c = aMapDelegateImpGLSurfaceView;
        this.f2570d = new Paint();
        this.f2572f = new Rect();
        this.f2570d.setAntiAlias(true);
        this.f2570d.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f2570d.setStrokeWidth(2.0f * C1042p.f3031a);
        this.f2570d.setStyle(Style.STROKE);
        this.f2571e = new Paint();
        this.f2571e.setAntiAlias(true);
        this.f2571e.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f2571e.setTextSize(20.0f * C1042p.f3031a);
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        String str = "onDraw";
        try {
            if (!this.f2569c.mo9997q().mo9716a()) {
                return;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (!this.f2567a.equals("") && this.f2568b != 0) {
            int i;
            int i2 = this.f2568b;
            try {
                if (i2 > this.f2569c.getWidth() / 5) {
                    i2 = this.f2569c.getWidth() / 5;
                }
                i = i2;
            } catch (Exception e2) {
                C0955ck.m3888a(e2, "ScaleView", str);
                i = i2;
            }
            Point A = this.f2569c.mo10004A();
            this.f2571e.getTextBounds(this.f2567a, 0, this.f2567a.length(), this.f2572f);
            if (A.x + i > this.f2569c.getWidth() - 10) {
                i2 = (this.f2569c.getWidth() - 10) - ((this.f2572f.width() + i) / 2);
            } else {
                i2 = A.x + ((i - this.f2572f.width()) / 2);
            }
            int height = (A.y - this.f2572f.height()) + 5;
            canvas.drawText(this.f2567a, (float) i2, (float) height, this.f2571e);
            int width = i2 - ((i - this.f2572f.width()) / 2);
            int height2 = height + (this.f2572f.height() - 5);
            canvas.drawLine((float) width, (float) (height2 - 2), (float) width, (float) (height2 + 2), this.f2570d);
            canvas.drawLine((float) width, (float) height2, (float) (width + i), (float) height2, this.f2570d);
            canvas.drawLine((float) (width + i), (float) (height2 - 2), (float) (width + i), (float) (height2 + 2), this.f2570d);
        }
    }

    /* renamed from: a */
    public void mo10082a(String str) {
        this.f2567a = str;
    }

    /* renamed from: a */
    public void mo10081a(int i) {
        this.f2568b = i;
    }
}
