package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.amap.api.maps.model.CameraPosition;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;

/* renamed from: com.amap.api.mapcore.util.q */
class CompassView extends LinearLayout {
    /* renamed from: a */
    Bitmap f2111a;
    /* renamed from: b */
    Bitmap f2112b;
    /* renamed from: c */
    Bitmap f2113c;
    /* renamed from: d */
    ImageView f2114d;
    /* renamed from: e */
    IAMapDelegate f2115e;

    /* compiled from: CompassView */
    /* renamed from: com.amap.api.mapcore.util.q$1 */
    class C08651 implements OnTouchListener {
        C08651() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            try {
                if (CompassView.this.f2115e.isMaploaded()) {
                    if (motionEvent.getAction() == 0) {
                        CompassView.this.f2114d.setImageBitmap(CompassView.this.f2112b);
                    } else if (motionEvent.getAction() == 1) {
                        CompassView.this.f2114d.setImageBitmap(CompassView.this.f2111a);
                        CameraPosition cameraPosition = CompassView.this.f2115e.getCameraPosition();
                        CompassView.this.f2115e.animateCamera(CameraUpdateFactoryDelegate.newCameraPosition(new CameraPosition(cameraPosition.target, cameraPosition.zoom, 0.0f, 0.0f)));
                    }
                }
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "CompassView", "onTouch");
                th.printStackTrace();
            }
            return false;
        }
    }

    /* renamed from: a */
    public void mo9538a() {
        try {
            removeAllViews();
            if (this.f2111a != null) {
                this.f2111a.recycle();
            }
            if (this.f2112b != null) {
                this.f2112b.recycle();
            }
            if (this.f2113c != null) {
                this.f2113c.recycle();
            }
            this.f2113c = null;
            this.f2111a = null;
            this.f2112b = null;
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "CompassView", "destroy");
            th.printStackTrace();
        }
    }

    public CompassView(Context context) {
        super(context);
    }

    public CompassView(Context context, MapMessageQueue mapMessageQueue, IAMapDelegate iAMapDelegate) {
        super(context);
        this.f2115e = iAMapDelegate;
        try {
            this.f2113c = Util.m2344a(context, "maps_dav_compass_needle_large.png");
            this.f2112b = Util.m2345a(this.f2113c, ConfigableConst.f2121a * 0.8f);
            this.f2113c = Util.m2345a(this.f2113c, ConfigableConst.f2121a * 0.7f);
            this.f2111a = Bitmap.createBitmap(this.f2112b.getWidth(), this.f2112b.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(this.f2111a);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            canvas.drawBitmap(this.f2113c, ((float) (this.f2112b.getWidth() - this.f2113c.getWidth())) / 2.0f, ((float) (this.f2112b.getHeight() - this.f2113c.getHeight())) / 2.0f, paint);
            this.f2114d = new ImageView(context);
            this.f2114d.setScaleType(ScaleType.MATRIX);
            this.f2114d.setImageBitmap(this.f2111a);
            this.f2114d.setClickable(true);
            mo9540b();
            this.f2114d.setOnTouchListener(new C08651());
            addView(this.f2114d);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "CompassView", "create");
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    public void mo9540b() {
        try {
            MapProjection mapProjection = this.f2115e.getMapProjection();
            float mapAngle = mapProjection.getMapAngle();
            float cameraHeaderAngle = mapProjection.getCameraHeaderAngle();
            Matrix matrix = new Matrix();
            matrix.postRotate(-mapAngle, ((float) this.f2114d.getDrawable().getBounds().width()) / 2.0f, ((float) this.f2114d.getDrawable().getBounds().height()) / 2.0f);
            matrix.postScale(1.0f, (float) Math.cos((((double) cameraHeaderAngle) * 3.141592653589793d) / 180.0d), ((float) this.f2114d.getDrawable().getBounds().width()) / 2.0f, ((float) this.f2114d.getDrawable().getBounds().height()) / 2.0f);
            this.f2114d.setImageMatrix(matrix);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "CompassView", "invalidateAngle");
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo9539a(boolean z) {
        if (z) {
            setVisibility(0);
            mo9540b();
            return;
        }
        setVisibility(8);
    }
}
