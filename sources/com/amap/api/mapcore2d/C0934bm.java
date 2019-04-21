package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.graphics.Paint.FontMetrics;
import android.graphics.Point;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.TextOptions;

/* compiled from: TextDelegateImp */
/* renamed from: com.amap.api.mapcore2d.bm */
public class C0934bm implements ITextDelegate {
    /* renamed from: a */
    private AMapDelegateImpGLSurfaceView f2578a;
    /* renamed from: b */
    private C0897as f2579b;
    /* renamed from: c */
    private String f2580c;
    /* renamed from: d */
    private int f2581d;
    /* renamed from: e */
    private int f2582e;
    /* renamed from: f */
    private LatLng f2583f;
    /* renamed from: g */
    private float f2584g;
    /* renamed from: h */
    private int f2585h;
    /* renamed from: i */
    private Typeface f2586i;
    /* renamed from: j */
    private boolean f2587j;
    /* renamed from: k */
    private float f2588k;
    /* renamed from: l */
    private int f2589l;
    /* renamed from: m */
    private int f2590m;
    /* renamed from: n */
    private Object f2591n;
    /* renamed from: o */
    private int f2592o;

    public C0934bm(IAMapDelegate iAMapDelegate, TextOptions textOptions, C0897as c0897as) {
        this.f2579b = c0897as;
        this.f2580c = textOptions.getText();
        this.f2581d = textOptions.getFontSize();
        this.f2582e = textOptions.getFontColor();
        this.f2583f = textOptions.getPosition();
        this.f2584g = textOptions.getRotate();
        this.f2585h = textOptions.getBackgroundColor();
        this.f2586i = textOptions.getTypeface();
        this.f2587j = textOptions.isVisible();
        this.f2588k = textOptions.getZIndex();
        this.f2589l = textOptions.getAlignX();
        this.f2590m = textOptions.getAlignY();
        this.f2591n = textOptions.getObject();
        this.f2578a = (AMapDelegateImpGLSurfaceView) iAMapDelegate;
    }

    /* renamed from: i */
    public void mo9699i() {
        if (this.f2579b != null) {
            this.f2579b.mo9827b((ITextDelegate) this);
        }
    }

    /* renamed from: b */
    public void mo9610b(float f) {
        this.f2588k = f;
        this.f2579b.mo9832d();
    }

    /* renamed from: r */
    public float mo9613r() {
        return this.f2588k;
    }

    /* renamed from: a */
    public void mo9689a(boolean z) {
        this.f2587j = z;
        this.f2578a.postInvalidate();
    }

    /* renamed from: s */
    public boolean mo9614s() {
        return this.f2587j;
    }

    /* renamed from: a */
    public void mo9686a(Canvas canvas) {
        if (!TextUtils.isEmpty(this.f2580c) && this.f2583f != null) {
            int i;
            int i2;
            TextPaint textPaint = new TextPaint();
            if (this.f2586i == null) {
                this.f2586i = Typeface.DEFAULT;
            }
            textPaint.setTypeface(this.f2586i);
            textPaint.setAntiAlias(true);
            textPaint.setTextSize((float) this.f2581d);
            float measureText = textPaint.measureText(this.f2580c);
            float f = (float) this.f2581d;
            textPaint.setColor(this.f2585h);
            GeoPoint geoPoint = new GeoPoint((int) (this.f2583f.latitude * 1000000.0d), (int) (this.f2583f.longitude * 1000000.0d));
            Point point = new Point();
            this.f2578a.mo9999s().mo9908a(geoPoint, point);
            canvas.save();
            canvas.rotate(-(this.f2584g % 360.0f), (float) point.x, (float) point.y);
            FontMetrics fontMetrics = textPaint.getFontMetrics();
            if (this.f2589l < 1 || this.f2589l > 3) {
                this.f2589l = 3;
            }
            if (this.f2590m < 4 || this.f2590m > 6) {
                this.f2590m = 6;
            }
            switch (this.f2589l) {
                case 1:
                    i = point.x;
                    break;
                case 2:
                    i = (int) (((float) point.x) - measureText);
                    break;
                case 3:
                    i = (int) (((float) point.x) - (measureText / 2.0f));
                    break;
                default:
                    i = 0;
                    break;
            }
            switch (this.f2590m) {
                case 4:
                    i2 = point.y;
                    break;
                case 5:
                    i2 = (int) (((float) point.y) - f);
                    break;
                case 6:
                    i2 = (int) (((float) point.y) - (f / 2.0f));
                    break;
                default:
                    i2 = 0;
                    break;
            }
            float f2 = 2.0f + f;
            Canvas canvas2 = canvas;
            canvas2.drawRect((float) (i - 1), (float) (i2 - 1), ((float) i) + (measureText + 2.0f), ((float) i2) + f2, textPaint);
            textPaint.setColor(this.f2582e);
            canvas.drawText(this.f2580c, (float) i, (((float) i2) + f2) - fontMetrics.bottom, textPaint);
            canvas.restore();
        }
    }

    /* renamed from: a */
    public void mo9688a(String str) {
        this.f2580c = str;
        this.f2578a.postInvalidate();
    }

    /* renamed from: a */
    public String mo9682a() {
        return this.f2580c;
    }

    /* renamed from: a */
    public void mo9684a(int i) {
        this.f2581d = i;
        this.f2578a.postInvalidate();
    }

    /* renamed from: b */
    public int mo9690b() {
        return this.f2581d;
    }

    /* renamed from: c */
    public void mo9692c(int i) {
        this.f2582e = i;
        this.f2578a.postInvalidate();
    }

    /* renamed from: c */
    public int mo9691c() {
        return this.f2582e;
    }

    /* renamed from: b */
    public void mo9612b(LatLng latLng) {
        this.f2583f = latLng;
        this.f2578a.postInvalidate();
    }

    /* renamed from: t */
    public LatLng mo9615t() {
        return this.f2583f;
    }

    /* renamed from: a */
    public void mo9683a(float f) {
        this.f2584g = f;
        this.f2578a.postInvalidate();
    }

    /* renamed from: d */
    public float mo9693d() {
        return this.f2584g;
    }

    /* renamed from: d */
    public void mo9694d(int i) {
        this.f2585h = i;
        this.f2578a.postInvalidate();
    }

    /* renamed from: e */
    public int mo9695e() {
        return this.f2585h;
    }

    /* renamed from: a */
    public void mo9687a(Typeface typeface) {
        this.f2586i = typeface;
        this.f2578a.postInvalidate();
    }

    /* renamed from: f */
    public Typeface mo9696f() {
        return this.f2586i;
    }

    /* renamed from: g */
    public int mo9697g() {
        return this.f2589l;
    }

    /* renamed from: h */
    public int mo9698h() {
        return this.f2590m;
    }

    /* renamed from: a */
    public void mo9685a(int i, int i2) {
        this.f2589l = i;
        this.f2590m = i2;
        this.f2578a.postInvalidate();
    }

    /* renamed from: a */
    public void mo9609a(Object obj) {
        this.f2591n = obj;
    }

    /* renamed from: u */
    public Object mo9616u() {
        return this.f2591n;
    }

    /* renamed from: b */
    public void mo9611b(int i) {
        this.f2592o = i;
    }

    /* renamed from: v */
    public int mo9617v() {
        return this.f2592o;
    }
}
