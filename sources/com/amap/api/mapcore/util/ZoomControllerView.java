package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.mapcore.util.MapOverlayViewGroup.C0736a;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;

/* renamed from: com.amap.api.mapcore.util.ba */
class ZoomControllerView extends LinearLayout {
    /* renamed from: a */
    private Bitmap f1298a;
    /* renamed from: b */
    private Bitmap f1299b;
    /* renamed from: c */
    private Bitmap f1300c;
    /* renamed from: d */
    private Bitmap f1301d;
    /* renamed from: e */
    private Bitmap f1302e;
    /* renamed from: f */
    private Bitmap f1303f;
    /* renamed from: g */
    private Bitmap f1304g;
    /* renamed from: h */
    private Bitmap f1305h;
    /* renamed from: i */
    private Bitmap f1306i;
    /* renamed from: j */
    private Bitmap f1307j;
    /* renamed from: k */
    private Bitmap f1308k;
    /* renamed from: l */
    private Bitmap f1309l;
    /* renamed from: m */
    private ImageView f1310m;
    /* renamed from: n */
    private ImageView f1311n;
    /* renamed from: o */
    private IAMapDelegate f1312o;

    /* compiled from: ZoomControllerView */
    /* renamed from: com.amap.api.mapcore.util.ba$1 */
    class C07521 implements OnTouchListener {
        C07521() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (ZoomControllerView.this.f1312o.getZoomLevel() < ZoomControllerView.this.f1312o.getMaxZoomLevel() && ZoomControllerView.this.f1312o.isMaploaded()) {
                if (motionEvent.getAction() == 0) {
                    ZoomControllerView.this.f1310m.setImageBitmap(ZoomControllerView.this.f1302e);
                } else if (motionEvent.getAction() == 1) {
                    ZoomControllerView.this.f1310m.setImageBitmap(ZoomControllerView.this.f1298a);
                    try {
                        ZoomControllerView.this.f1312o.animateCamera(CameraUpdateFactoryDelegate.zoomIn());
                    } catch (RemoteException e) {
                        SDKLogHandler.m2563a(e, "ZoomControllerView", "zoomin ontouch");
                        e.printStackTrace();
                    }
                }
            }
            return false;
        }
    }

    /* compiled from: ZoomControllerView */
    /* renamed from: com.amap.api.mapcore.util.ba$2 */
    class C07532 implements OnTouchListener {
        C07532() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (ZoomControllerView.this.f1312o.getZoomLevel() > ZoomControllerView.this.f1312o.getMinZoomLevel() && ZoomControllerView.this.f1312o.isMaploaded()) {
                if (motionEvent.getAction() == 0) {
                    ZoomControllerView.this.f1311n.setImageBitmap(ZoomControllerView.this.f1303f);
                } else if (motionEvent.getAction() == 1) {
                    ZoomControllerView.this.f1311n.setImageBitmap(ZoomControllerView.this.f1300c);
                    try {
                        ZoomControllerView.this.f1312o.animateCamera(CameraUpdateFactoryDelegate.zoomOut());
                    } catch (RemoteException e) {
                        SDKLogHandler.m2563a(e, "ZoomControllerView", "zoomout ontouch");
                        e.printStackTrace();
                    }
                }
            }
            return false;
        }
    }

    /* renamed from: a */
    public void mo8786a() {
        try {
            removeAllViews();
            this.f1298a.recycle();
            this.f1299b.recycle();
            this.f1300c.recycle();
            this.f1301d.recycle();
            this.f1302e.recycle();
            this.f1303f.recycle();
            this.f1298a = null;
            this.f1299b = null;
            this.f1300c = null;
            this.f1301d = null;
            this.f1302e = null;
            this.f1303f = null;
            if (this.f1304g != null) {
                this.f1304g.recycle();
                this.f1304g = null;
            }
            if (this.f1305h != null) {
                this.f1305h.recycle();
                this.f1305h = null;
            }
            if (this.f1306i != null) {
                this.f1306i.recycle();
                this.f1306i = null;
            }
            if (this.f1307j != null) {
                this.f1307j.recycle();
                this.f1304g = null;
            }
            if (this.f1308k != null) {
                this.f1308k.recycle();
                this.f1308k = null;
            }
            if (this.f1309l != null) {
                this.f1309l.recycle();
                this.f1309l = null;
            }
            this.f1310m = null;
            this.f1311n = null;
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "ZoomControllerView", "destory");
            th.printStackTrace();
        }
    }

    public ZoomControllerView(Context context) {
        super(context);
    }

    public ZoomControllerView(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.f1312o = iAMapDelegate;
        try {
            this.f1304g = Util.m2344a(context, "zoomin_selected.png");
            this.f1298a = Util.m2345a(this.f1304g, ConfigableConst.f2121a);
            this.f1305h = Util.m2344a(context, "zoomin_unselected.png");
            this.f1299b = Util.m2345a(this.f1305h, ConfigableConst.f2121a);
            this.f1306i = Util.m2344a(context, "zoomout_selected.png");
            this.f1300c = Util.m2345a(this.f1306i, ConfigableConst.f2121a);
            this.f1307j = Util.m2344a(context, "zoomout_unselected.png");
            this.f1301d = Util.m2345a(this.f1307j, ConfigableConst.f2121a);
            this.f1308k = Util.m2344a(context, "zoomin_pressed.png");
            this.f1302e = Util.m2345a(this.f1308k, ConfigableConst.f2121a);
            this.f1309l = Util.m2344a(context, "zoomout_pressed.png");
            this.f1303f = Util.m2345a(this.f1309l, ConfigableConst.f2121a);
            this.f1310m = new ImageView(context);
            this.f1310m.setImageBitmap(this.f1298a);
            this.f1310m.setClickable(true);
            this.f1311n = new ImageView(context);
            this.f1311n.setImageBitmap(this.f1300c);
            this.f1311n.setClickable(true);
            this.f1310m.setOnTouchListener(new C07521());
            this.f1311n.setOnTouchListener(new C07532());
            this.f1310m.setPadding(0, 0, 20, -2);
            this.f1311n.setPadding(0, 0, 20, 20);
            setOrientation(1);
            addView(this.f1310m);
            addView(this.f1311n);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "ZoomControllerView", "create");
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo8787a(float f) {
        try {
            if (f < this.f1312o.getMaxZoomLevel() && f > this.f1312o.getMinZoomLevel()) {
                this.f1310m.setImageBitmap(this.f1298a);
                this.f1311n.setImageBitmap(this.f1300c);
            } else if (f == this.f1312o.getMinZoomLevel()) {
                this.f1311n.setImageBitmap(this.f1301d);
                this.f1310m.setImageBitmap(this.f1298a);
            } else if (f == this.f1312o.getMaxZoomLevel()) {
                this.f1310m.setImageBitmap(this.f1299b);
                this.f1311n.setImageBitmap(this.f1300c);
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "ZoomControllerView", "setZoomBitmap");
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo8788a(int i) {
        try {
            C0736a c0736a = (C0736a) getLayoutParams();
            if (i == 1) {
                c0736a.f1002d = 16;
            } else if (i == 2) {
                c0736a.f1002d = 80;
            }
            setLayoutParams(c0736a);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "ZoomControllerView", "setZoomPosition");
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo8789a(boolean z) {
        if (z) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }
}
