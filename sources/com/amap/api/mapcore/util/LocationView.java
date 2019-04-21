package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;

/* renamed from: com.amap.api.mapcore.util.aa */
class LocationView extends LinearLayout {
    /* renamed from: a */
    Bitmap f965a;
    /* renamed from: b */
    Bitmap f966b;
    /* renamed from: c */
    Bitmap f967c;
    /* renamed from: d */
    Bitmap f968d;
    /* renamed from: e */
    Bitmap f969e;
    /* renamed from: f */
    Bitmap f970f;
    /* renamed from: g */
    ImageView f971g;
    /* renamed from: h */
    IAMapDelegate f972h;
    /* renamed from: i */
    boolean f973i = false;

    /* compiled from: LocationView */
    /* renamed from: com.amap.api.mapcore.util.aa$1 */
    class C07321 implements OnTouchListener {
        C07321() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (LocationView.this.f973i) {
                if (motionEvent.getAction() == 0) {
                    LocationView.this.f971g.setImageBitmap(LocationView.this.f966b);
                } else if (motionEvent.getAction() == 1) {
                    try {
                        LocationView.this.f971g.setImageBitmap(LocationView.this.f965a);
                        LocationView.this.f972h.setMyLocationEnabled(true);
                        Location myLocation = LocationView.this.f972h.getMyLocation();
                        if (myLocation != null) {
                            LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                            LocationView.this.f972h.showMyLocationOverlay(myLocation);
                            LocationView.this.f972h.moveCamera(CameraUpdateFactoryDelegate.newLatLngZoom(latLng, LocationView.this.f972h.getZoomLevel()));
                        }
                    } catch (Throwable th) {
                        SDKLogHandler.m2563a(th, "LocationView", "onTouch");
                        th.printStackTrace();
                    }
                }
            }
            return false;
        }
    }

    /* renamed from: a */
    public void mo8466a() {
        try {
            removeAllViews();
            if (this.f965a != null) {
                this.f965a.recycle();
            }
            if (this.f966b != null) {
                this.f966b.recycle();
            }
            if (this.f966b != null) {
                this.f967c.recycle();
            }
            this.f965a = null;
            this.f966b = null;
            this.f967c = null;
            if (this.f968d != null) {
                this.f968d.recycle();
                this.f968d = null;
            }
            if (this.f969e != null) {
                this.f969e.recycle();
                this.f969e = null;
            }
            if (this.f970f != null) {
                this.f970f.recycle();
                this.f970f = null;
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "LocationView", "destroy");
            th.printStackTrace();
        }
    }

    public LocationView(Context context) {
        super(context);
    }

    public LocationView(Context context, MapMessageQueue mapMessageQueue, IAMapDelegate iAMapDelegate) {
        super(context);
        this.f972h = iAMapDelegate;
        try {
            this.f968d = Util.m2344a(context, "location_selected.png");
            this.f965a = Util.m2345a(this.f968d, ConfigableConst.f2121a);
            this.f969e = Util.m2344a(context, "location_pressed.png");
            this.f966b = Util.m2345a(this.f969e, ConfigableConst.f2121a);
            this.f970f = Util.m2344a(context, "location_unselected.png");
            this.f967c = Util.m2345a(this.f970f, ConfigableConst.f2121a);
            this.f971g = new ImageView(context);
            this.f971g.setImageBitmap(this.f965a);
            this.f971g.setClickable(true);
            this.f971g.setPadding(0, 20, 20, 0);
            this.f971g.setOnTouchListener(new C07321());
            addView(this.f971g);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "LocationView", "create");
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo8467a(boolean z) {
        this.f973i = z;
        if (z) {
            try {
                this.f971g.setImageBitmap(this.f965a);
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "LocationView", "showSelect");
                th.printStackTrace();
                return;
            }
        }
        this.f971g.setImageBitmap(this.f967c);
        this.f971g.invalidate();
    }
}
