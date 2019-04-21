package com.amap.api.maps.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.amap.api.maps.overlay.b */
class RouteOverlay {
    /* renamed from: a */
    private Context f3328a;
    protected List<Polyline> allPolyLines = new ArrayList();
    /* renamed from: b */
    private Bitmap f3329b;
    /* renamed from: c */
    private Bitmap f3330c;
    /* renamed from: d */
    private Bitmap f3331d;
    /* renamed from: e */
    private Bitmap f3332e;
    protected Marker endMarker;
    protected LatLng endPoint;
    /* renamed from: f */
    private Bitmap f3333f;
    protected AMap mAMap;
    protected boolean nodeIconVisible = true;
    protected Marker startMarker;
    protected LatLng startPoint;
    protected List<Marker> stationMarkers = new ArrayList();

    public RouteOverlay(Context context) {
        this.f3328a = context;
    }

    public void removeFromMap() {
        if (this.startMarker != null) {
            this.startMarker.remove();
        }
        if (this.endMarker != null) {
            this.endMarker.remove();
        }
        for (Marker remove : this.stationMarkers) {
            remove.remove();
        }
        for (Polyline remove2 : this.allPolyLines) {
            remove2.remove();
        }
        m4518a();
    }

    /* renamed from: a */
    private void m4518a() {
        if (this.f3329b != null) {
            this.f3329b.recycle();
            this.f3329b = null;
        }
        if (this.f3330c != null) {
            this.f3330c.recycle();
            this.f3330c = null;
        }
        if (this.f3331d != null) {
            this.f3331d.recycle();
            this.f3331d = null;
        }
        if (this.f3332e != null) {
            this.f3332e.recycle();
            this.f3332e = null;
        }
        if (this.f3333f != null) {
            this.f3333f.recycle();
            this.f3333f = null;
        }
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getStartBitmapDescriptor() {
        return m4517a(this.f3329b, "amap_start.png");
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getEndBitmapDescriptor() {
        return m4517a(this.f3330c, "amap_end.png");
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getBusBitmapDescriptor() {
        return m4517a(this.f3331d, "amap_bus.png");
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getWalkBitmapDescriptor() {
        return m4517a(this.f3332e, "amap_man.png");
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getDriveBitmapDescriptor() {
        return m4517a(this.f3333f, "amap_car.png");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x003c A:{SYNTHETIC, Splitter:B:23:0x003c} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0039 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x0001} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:12:0x0027, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:13:0x0028, code skipped:
            r2 = r1;
            r1 = r0;
            r0 = r4;
     */
    /* JADX WARNING: Missing block: B:21:0x0039, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:24:?, code skipped:
            r1.close();
     */
    /* JADX WARNING: Missing block: B:26:0x0040, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:27:0x0041, code skipped:
            r1.printStackTrace();
     */
    /* renamed from: a */
    private com.amap.api.maps.model.BitmapDescriptor m4517a(android.graphics.Bitmap r4, java.lang.String r5) {
        /*
        r3 = this;
        r1 = 0;
        r0 = r3.f3328a;	 Catch:{ IOException -> 0x0027, all -> 0x0039 }
        r0 = com.amap.api.mapcore.util.ResourcesUtil.m2327a(r0);	 Catch:{ IOException -> 0x0027, all -> 0x0039 }
        r1 = r0.open(r5);	 Catch:{ IOException -> 0x0027, all -> 0x0039 }
        r4 = com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation.decodeStream(r1);	 Catch:{ IOException -> 0x0048, all -> 0x0039 }
        r0 = com.amap.api.mapcore.util.ConfigableConst.f2121a;	 Catch:{ IOException -> 0x0048, all -> 0x0039 }
        r0 = com.amap.api.mapcore.util.Util.m2345a(r4, r0);	 Catch:{ IOException -> 0x0048, all -> 0x0039 }
        if (r1 == 0) goto L_0x001a;
    L_0x0017:
        r1.close();	 Catch:{ IOException -> 0x0022 }
    L_0x001a:
        r1 = com.amap.api.maps.model.BitmapDescriptorFactory.fromBitmap(r0);
        r0.recycle();
        return r1;
    L_0x0022:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001a;
    L_0x0027:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        r0 = r4;
    L_0x002b:
        r1.printStackTrace();	 Catch:{ all -> 0x0045 }
        if (r2 == 0) goto L_0x001a;
    L_0x0030:
        r2.close();	 Catch:{ IOException -> 0x0034 }
        goto L_0x001a;
    L_0x0034:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001a;
    L_0x0039:
        r0 = move-exception;
    L_0x003a:
        if (r1 == 0) goto L_0x003f;
    L_0x003c:
        r1.close();	 Catch:{ IOException -> 0x0040 }
    L_0x003f:
        throw r0;
    L_0x0040:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x003f;
    L_0x0045:
        r0 = move-exception;
        r1 = r2;
        goto L_0x003a;
    L_0x0048:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x002b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.maps.overlay.RouteOverlay.m4517a(android.graphics.Bitmap, java.lang.String):com.amap.api.maps.model.BitmapDescriptor");
    }

    /* Access modifiers changed, original: protected */
    public void addStartAndEndMarker() {
        this.startMarker = this.mAMap.addMarker(new MarkerOptions().position(this.startPoint).icon(getStartBitmapDescriptor()).title("起点"));
        this.endMarker = this.mAMap.addMarker(new MarkerOptions().position(this.endPoint).icon(getEndBitmapDescriptor()).title("终点"));
    }

    public void zoomToSpan() {
        if (this.startPoint != null && this.mAMap != null) {
            try {
                this.mAMap.animateCamera(CameraUpdateFactory.newLatLngBounds(getLatLngBounds(), 50));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public LatLngBounds getLatLngBounds() {
        Builder builder = LatLngBounds.builder();
        builder.include(new LatLng(this.startPoint.latitude, this.startPoint.longitude));
        builder.include(new LatLng(this.endPoint.latitude, this.endPoint.longitude));
        return builder.build();
    }

    public void setNodeIconVisibility(boolean z) {
        try {
            this.nodeIconVisible = z;
            if (this.stationMarkers != null && this.stationMarkers.size() > 0) {
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 < this.stationMarkers.size()) {
                        ((Marker) this.stationMarkers.get(i2)).setVisible(z);
                        i = i2 + 1;
                    } else {
                        return;
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* Access modifiers changed, original: protected */
    public void addStationMarker(MarkerOptions markerOptions) {
        if (markerOptions != null) {
            Marker addMarker = this.mAMap.addMarker(markerOptions);
            if (addMarker != null) {
                this.stationMarkers.add(addMarker);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void addPolyLine(PolylineOptions polylineOptions) {
        if (polylineOptions != null) {
            Polyline addPolyline = this.mAMap.addPolyline(polylineOptions);
            if (addPolyline != null) {
                this.allPolyLines.add(addPolyline);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public float getRouteWidth() {
        return 18.0f;
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
