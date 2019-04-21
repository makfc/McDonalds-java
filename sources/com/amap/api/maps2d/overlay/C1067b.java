package com.amap.api.maps2d.overlay;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import com.amap.api.mapcore2d.C0955ck;
import com.amap.api.mapcore2d.C1042p;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.LatLngBounds.Builder;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.Polyline;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* compiled from: RouteOverlay */
/* renamed from: com.amap.api.maps2d.overlay.b */
class C1067b {
    /* renamed from: a */
    private Marker f3501a;
    protected List<Polyline> allPolyLines = new ArrayList();
    /* renamed from: b */
    private Marker f3502b;
    /* renamed from: c */
    private Context f3503c;
    /* renamed from: d */
    private Bitmap f3504d;
    /* renamed from: e */
    private Bitmap f3505e;
    protected LatLng endPoint;
    /* renamed from: f */
    private Bitmap f3506f;
    /* renamed from: g */
    private Bitmap f3507g;
    /* renamed from: h */
    private Bitmap f3508h;
    /* renamed from: i */
    private AssetManager f3509i;
    protected AMap mAMap;
    protected boolean mNodeIconVisible = true;
    protected LatLng startPoint;
    protected List<Marker> stationMarkers = new ArrayList();

    public C1067b(Context context) {
        this.f3503c = context;
        this.f3509i = this.f3503c.getResources().getAssets();
    }

    public void removeFromMap() {
        if (this.f3501a != null) {
            this.f3501a.remove();
        }
        if (this.f3502b != null) {
            this.f3502b.remove();
        }
        for (Marker remove : this.stationMarkers) {
            remove.remove();
        }
        for (Polyline remove2 : this.allPolyLines) {
            remove2.remove();
        }
        m4614a();
    }

    public void setNodeIconVisibility(boolean z) {
        this.mNodeIconVisible = z;
        for (Marker visible : this.stationMarkers) {
            visible.setVisible(z);
        }
        this.mAMap.postInvalidate();
    }

    /* renamed from: a */
    private void m4614a() {
        if (this.f3504d != null) {
            this.f3504d.recycle();
            this.f3504d = null;
        }
        if (this.f3505e != null) {
            this.f3505e.recycle();
            this.f3505e = null;
        }
        if (this.f3506f != null) {
            this.f3506f.recycle();
            this.f3506f = null;
        }
        if (this.f3507g != null) {
            this.f3507g.recycle();
            this.f3507g = null;
        }
        if (this.f3508h != null) {
            this.f3508h.recycle();
            this.f3508h = null;
        }
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getStartBitmapDescriptor() {
        return getBitDes(this.f3504d, "amap_start.png");
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getEndBitmapDescriptor() {
        return getBitDes(this.f3505e, "amap_end.png");
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getBusBitmapDescriptor() {
        return getBitDes(this.f3506f, "amap_bus.png");
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getWalkBitmapDescriptor() {
        return getBitDes(this.f3507g, "amap_man.png");
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getDriveBitmapDescriptor() {
        return getBitDes(this.f3508h, "amap_car.png");
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getBitDes(Bitmap bitmap, String str) {
        Bitmap a;
        Throwable e;
        String str2 = "getBitDes";
        try {
            InputStream open = this.f3509i.open(str);
            bitmap = BitmapFactoryInstrumentation.decodeStream(open);
            a = C1068a.m4635a(bitmap, C1042p.f3031a);
            try {
                open.close();
            } catch (IOException e2) {
                e = e2;
                C0955ck.m3888a(e, "RouteOverlay", str2);
                return BitmapDescriptorFactory.fromBitmap(a);
            } catch (Exception e3) {
                e = e3;
                C0955ck.m3888a(e, "RouteOverlay", str2);
                return BitmapDescriptorFactory.fromBitmap(a);
            }
        } catch (IOException e4) {
            e = e4;
            a = bitmap;
        } catch (Exception e42) {
            e = e42;
            a = bitmap;
            C0955ck.m3888a(e, "RouteOverlay", str2);
            return BitmapDescriptorFactory.fromBitmap(a);
        }
        return BitmapDescriptorFactory.fromBitmap(a);
    }

    /* Access modifiers changed, original: protected */
    public void addStartAndEndMarker() {
        this.f3501a = this.mAMap.addMarker(new MarkerOptions().position(this.startPoint).icon(getStartBitmapDescriptor()).title("起点"));
        this.f3502b = this.mAMap.addMarker(new MarkerOptions().position(this.endPoint).icon(getEndBitmapDescriptor()).title("终点"));
    }

    public void zoomToSpan() {
        if (this.startPoint != null && this.endPoint != null && this.mAMap != null) {
            this.mAMap.moveCamera(CameraUpdateFactory.newLatLngBounds(getLatLngBounds(), 50));
        }
    }

    /* Access modifiers changed, original: protected */
    public LatLngBounds getLatLngBounds() {
        Builder builder = LatLngBounds.builder();
        builder.include(new LatLng(this.startPoint.latitude, this.startPoint.longitude));
        builder.include(new LatLng(this.endPoint.latitude, this.endPoint.longitude));
        return builder.build();
    }

    /* Access modifiers changed, original: protected */
    public int getWalkColor() {
        return Color.parseColor("#6db74d");
    }

    /* Access modifiers changed, original: protected */
    public int getBusColor() {
        return Color.parseColor("#537edc");
    }

    /* Access modifiers changed, original: protected */
    public int getDriveColor() {
        return Color.parseColor("#537edc");
    }
}
