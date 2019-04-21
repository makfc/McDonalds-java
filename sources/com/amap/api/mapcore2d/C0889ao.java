package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.maps2d.model.LatLng;

/* compiled from: LocationView */
/* renamed from: com.amap.api.mapcore2d.ao */
class C0889ao extends LinearLayout {
    /* renamed from: a */
    private Bitmap f2257a;
    /* renamed from: b */
    private Bitmap f2258b;
    /* renamed from: c */
    private Bitmap f2259c;
    /* renamed from: d */
    private ImageView f2260d;
    /* renamed from: e */
    private IAMapDelegate f2261e;
    /* renamed from: f */
    private boolean f2262f = false;

    /* compiled from: LocationView */
    /* renamed from: com.amap.api.mapcore2d.ao$1 */
    class C08871 implements OnClickListener {
        C08871() {
        }

        public void onClick(View view) {
        }
    }

    /* compiled from: LocationView */
    /* renamed from: com.amap.api.mapcore2d.ao$2 */
    class C08882 implements OnTouchListener {
        C08882() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            String str = "onTouch";
            if (C0889ao.this.f2262f) {
                if (motionEvent.getAction() == 0) {
                    C0889ao.this.f2260d.setImageBitmap(C0889ao.this.f2258b);
                } else if (motionEvent.getAction() == 1) {
                    try {
                        C0889ao.this.f2260d.setImageBitmap(C0889ao.this.f2257a);
                        C0889ao.this.f2261e.mo9980c(true);
                        Location p = C0889ao.this.f2261e.mo9996p();
                        if (p != null) {
                            LatLng latLng = new LatLng(p.getLatitude(), p.getLongitude());
                            C0889ao.this.f2261e.mo9950a(p);
                            C0889ao.this.f2261e.mo9951a(CameraUpdateFactoryDelegate.m4323a(latLng, C0889ao.this.f2261e.mo9985f()));
                        }
                    } catch (Exception e) {
                        C0955ck.m3888a(e, "LocationView", str);
                    }
                }
            }
            return false;
        }
    }

    /* renamed from: a */
    public void mo9766a() {
        String str = "destory";
        try {
            this.f2257a.recycle();
            this.f2258b.recycle();
            this.f2259c.recycle();
            this.f2257a = null;
            this.f2258b = null;
            this.f2259c = null;
        } catch (Exception e) {
            C0955ck.m3888a(e, "LocationView", str);
        }
    }

    public C0889ao(Context context, C0894ar c0894ar, IAMapDelegate iAMapDelegate) {
        super(context);
        String str = "LocationView";
        this.f2261e = iAMapDelegate;
        try {
            this.f2257a = C0955ck.m3882a("location_selected2d.png");
            this.f2258b = C0955ck.m3882a("location_pressed2d.png");
            this.f2257a = C0955ck.m3881a(this.f2257a, C1042p.f3031a);
            this.f2258b = C0955ck.m3881a(this.f2258b, C1042p.f3031a);
            this.f2259c = C0955ck.m3882a("location_unselected2d.png");
            this.f2259c = C0955ck.m3881a(this.f2259c, C1042p.f3031a);
        } catch (Throwable th) {
            C0955ck.m3888a(th, "LocationView", str);
        }
        this.f2260d = new ImageView(context);
        this.f2260d.setImageBitmap(this.f2257a);
        this.f2260d.setPadding(0, 20, 20, 0);
        this.f2260d.setOnClickListener(new C08871());
        this.f2260d.setOnTouchListener(new C08882());
        addView(this.f2260d);
    }

    /* renamed from: a */
    public void mo9767a(boolean z) {
        this.f2262f = z;
        if (z) {
            this.f2260d.setImageBitmap(this.f2257a);
        } else {
            this.f2260d.setImageBitmap(this.f2259c);
        }
        this.f2260d.invalidate();
    }
}
