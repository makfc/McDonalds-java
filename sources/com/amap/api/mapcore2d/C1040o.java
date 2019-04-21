package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.amap.api.maps2d.model.CameraPosition;

/* compiled from: CompassView */
/* renamed from: com.amap.api.mapcore2d.o */
class C1040o extends LinearLayout {
    /* renamed from: a */
    private Bitmap f3021a;
    /* renamed from: b */
    private Bitmap f3022b;
    /* renamed from: c */
    private ImageView f3023c;
    /* renamed from: d */
    private C0894ar f3024d;
    /* renamed from: e */
    private IAMapDelegate f3025e;

    /* compiled from: CompassView */
    /* renamed from: com.amap.api.mapcore2d.o$1 */
    class C10381 implements OnClickListener {
        C10381() {
        }

        public void onClick(View view) {
        }
    }

    /* compiled from: CompassView */
    /* renamed from: com.amap.api.mapcore2d.o$2 */
    class C10392 implements OnTouchListener {
        C10392() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                C1040o.this.f3023c.setImageBitmap(C1040o.this.f3022b);
            } else if (motionEvent.getAction() == 1) {
                try {
                    C1040o.this.f3023c.setImageBitmap(C1040o.this.f3021a);
                    CameraPosition g = C1040o.this.f3025e.mo9987g();
                    C1040o.this.f3025e.mo9974b(CameraUpdateFactoryDelegate.m4321a(new CameraPosition(g.target, g.zoom, 0.0f, 0.0f)));
                } catch (Exception e) {
                    C0955ck.m3888a(e, "CompassView", "onTouch");
                }
            }
            return false;
        }
    }

    /* renamed from: a */
    public void mo10321a() {
        String str = "destory";
        try {
            this.f3021a.recycle();
            this.f3022b.recycle();
            this.f3021a = null;
            this.f3022b = null;
        } catch (Exception e) {
            C0955ck.m3888a(e, "CompassView", str);
        }
    }

    public C1040o(Context context, C0894ar c0894ar, IAMapDelegate iAMapDelegate) {
        super(context);
        String str = "CompassView";
        this.f3024d = c0894ar;
        this.f3025e = iAMapDelegate;
        try {
            Bitmap a = C0955ck.m3882a("maps_dav_compass_needle_large2d.png");
            this.f3022b = C0955ck.m3881a(a, C1042p.f3031a * 0.8f);
            a = C0955ck.m3881a(a, C1042p.f3031a * 0.7f);
            this.f3021a = Bitmap.createBitmap(this.f3022b.getWidth(), this.f3022b.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(this.f3021a);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            canvas.drawBitmap(a, (float) ((this.f3022b.getWidth() - a.getWidth()) / 2), (float) ((this.f3022b.getHeight() - a.getHeight()) / 2), paint);
        } catch (Throwable th) {
            C0955ck.m3888a(th, "CompassView", str);
        }
        this.f3023c = new ImageView(context);
        this.f3023c.setScaleType(ScaleType.MATRIX);
        this.f3023c.setImageBitmap(this.f3021a);
        this.f3023c.setOnClickListener(new C10381());
        this.f3023c.setOnTouchListener(new C10392());
        addView(this.f3023c);
    }
}
