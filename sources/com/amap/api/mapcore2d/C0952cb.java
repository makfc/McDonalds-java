package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

/* compiled from: ZoomControllerView */
/* renamed from: com.amap.api.mapcore2d.cb */
class C0952cb extends LinearLayout {
    /* renamed from: a */
    private Bitmap f2673a;
    /* renamed from: b */
    private Bitmap f2674b;
    /* renamed from: c */
    private Bitmap f2675c;
    /* renamed from: d */
    private Bitmap f2676d;
    /* renamed from: e */
    private Bitmap f2677e;
    /* renamed from: f */
    private Bitmap f2678f;
    /* renamed from: g */
    private ImageView f2679g;
    /* renamed from: h */
    private ImageView f2680h;
    /* renamed from: i */
    private MapController f2681i;
    /* renamed from: j */
    private IAMapDelegate f2682j;
    /* renamed from: k */
    private int f2683k = 0;

    /* compiled from: ZoomControllerView */
    /* renamed from: com.amap.api.mapcore2d.cb$1 */
    class C09481 implements OnClickListener {
        C09481() {
        }

        public void onClick(View view) {
            C0952cb.this.f2680h.setImageBitmap(C0952cb.this.f2675c);
            if (C0952cb.this.f2682j.mo9985f() > ((float) (((int) C0952cb.this.f2682j.mo9989h()) - 2))) {
                C0952cb.this.f2679g.setImageBitmap(C0952cb.this.f2674b);
            } else {
                C0952cb.this.f2679g.setImageBitmap(C0952cb.this.f2673a);
            }
            C0952cb.this.mo10149a(C0952cb.this.f2682j.mo9985f() + 1.0f);
            C0952cb.this.f2681i.mo9794c();
        }
    }

    /* compiled from: ZoomControllerView */
    /* renamed from: com.amap.api.mapcore2d.cb$2 */
    class C09492 implements OnClickListener {
        C09492() {
        }

        public void onClick(View view) {
            C0952cb.this.f2679g.setImageBitmap(C0952cb.this.f2673a);
            C0952cb.this.mo10149a(C0952cb.this.f2682j.mo9985f() - 1.0f);
            if (C0952cb.this.f2682j.mo9985f() < ((float) (((int) C0952cb.this.f2682j.mo9990i()) + 2))) {
                C0952cb.this.f2680h.setImageBitmap(C0952cb.this.f2676d);
            } else {
                C0952cb.this.f2680h.setImageBitmap(C0952cb.this.f2675c);
            }
            C0952cb.this.f2681i.mo9796d();
        }
    }

    /* compiled from: ZoomControllerView */
    /* renamed from: com.amap.api.mapcore2d.cb$3 */
    class C09503 implements OnTouchListener {
        C09503() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            String str = "ontouch";
            if (C0952cb.this.f2682j.mo9985f() < C0952cb.this.f2682j.mo9989h()) {
                if (motionEvent.getAction() == 0) {
                    C0952cb.this.f2679g.setImageBitmap(C0952cb.this.f2677e);
                } else if (motionEvent.getAction() == 1) {
                    C0952cb.this.f2679g.setImageBitmap(C0952cb.this.f2673a);
                    try {
                        C0952cb.this.f2682j.mo9974b(CameraUpdateFactoryDelegate.m4327b());
                    } catch (RemoteException e) {
                        C0955ck.m3888a(e, "ZoomControllerView", str);
                    }
                }
            }
            return false;
        }
    }

    /* compiled from: ZoomControllerView */
    /* renamed from: com.amap.api.mapcore2d.cb$4 */
    class C09514 implements OnTouchListener {
        C09514() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            String str = "onTouch";
            if (C0952cb.this.f2682j.mo9985f() > C0952cb.this.f2682j.mo9990i()) {
                if (motionEvent.getAction() == 0) {
                    C0952cb.this.f2680h.setImageBitmap(C0952cb.this.f2678f);
                } else if (motionEvent.getAction() == 1) {
                    C0952cb.this.f2680h.setImageBitmap(C0952cb.this.f2675c);
                    try {
                        C0952cb.this.f2682j.mo9974b(CameraUpdateFactoryDelegate.m4330c());
                    } catch (RemoteException e) {
                        C0955ck.m3888a(e, "ZoomControllerView", str);
                    }
                }
            }
            return false;
        }
    }

    /* renamed from: a */
    public void mo10148a() {
        String str = "destory";
        try {
            this.f2673a.recycle();
            this.f2674b.recycle();
            this.f2675c.recycle();
            this.f2676d.recycle();
            this.f2677e.recycle();
            this.f2678f.recycle();
            this.f2673a = null;
            this.f2674b = null;
            this.f2675c = null;
            this.f2676d = null;
            this.f2677e = null;
            this.f2678f = null;
        } catch (Exception e) {
            C0955ck.m3888a(e, "ZoomControllerView", str);
        }
    }

    public C0952cb(Context context, MapController mapController, IAMapDelegate iAMapDelegate) {
        super(context);
        String str = "ZoomControllerView";
        setWillNotDraw(false);
        this.f2681i = mapController;
        this.f2682j = iAMapDelegate;
        try {
            this.f2673a = C0955ck.m3882a("zoomin_selected2d.png");
            this.f2673a = C0955ck.m3881a(this.f2673a, C1042p.f3031a);
            this.f2674b = C0955ck.m3882a("zoomin_unselected2d.png");
            this.f2674b = C0955ck.m3881a(this.f2674b, C1042p.f3031a);
            this.f2675c = C0955ck.m3882a("zoomout_selected2d.png");
            this.f2675c = C0955ck.m3881a(this.f2675c, C1042p.f3031a);
            this.f2676d = C0955ck.m3882a("zoomout_unselected2d.png");
            this.f2676d = C0955ck.m3881a(this.f2676d, C1042p.f3031a);
            this.f2677e = C0955ck.m3882a("zoomin_pressed2d.png");
            this.f2678f = C0955ck.m3882a("zoomout_pressed2d.png");
            this.f2677e = C0955ck.m3881a(this.f2677e, C1042p.f3031a);
            this.f2678f = C0955ck.m3881a(this.f2678f, C1042p.f3031a);
        } catch (Throwable th) {
            C0955ck.m3888a(th, "ZoomControllerView", str);
        }
        this.f2679g = new ImageView(context);
        this.f2679g.setImageBitmap(this.f2673a);
        this.f2679g.setOnClickListener(new C09481());
        this.f2680h = new ImageView(context);
        this.f2680h.setImageBitmap(this.f2675c);
        this.f2680h.setOnClickListener(new C09492());
        this.f2679g.setOnTouchListener(new C09503());
        this.f2680h.setOnTouchListener(new C09514());
        this.f2679g.setPadding(0, 0, 20, -2);
        this.f2680h.setPadding(0, 0, 20, 20);
        setOrientation(1);
        addView(this.f2679g);
        addView(this.f2680h);
    }

    /* renamed from: a */
    public void mo10150a(int i) {
        this.f2683k = i;
        removeView(this.f2679g);
        removeView(this.f2680h);
        addView(this.f2679g);
        addView(this.f2680h);
    }

    /* renamed from: b */
    public int mo10151b() {
        return this.f2683k;
    }

    /* renamed from: a */
    public void mo10149a(float f) {
        if (f < this.f2682j.mo9989h() && f > this.f2682j.mo9990i()) {
            this.f2679g.setImageBitmap(this.f2673a);
            this.f2680h.setImageBitmap(this.f2675c);
        } else if (f <= this.f2682j.mo9990i()) {
            this.f2680h.setImageBitmap(this.f2676d);
            this.f2679g.setImageBitmap(this.f2673a);
        } else if (f >= this.f2682j.mo9989h()) {
            this.f2679g.setImageBitmap(this.f2674b);
            this.f2680h.setImageBitmap(this.f2675c);
        }
    }
}
