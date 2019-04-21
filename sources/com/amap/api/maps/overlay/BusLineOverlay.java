package com.amap.api.maps.overlay;

import android.content.Context;
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
import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BusLineOverlay {
    /* renamed from: a */
    private BusLineItem f3319a;
    /* renamed from: b */
    private AMap f3320b;
    /* renamed from: c */
    private ArrayList<Marker> f3321c = new ArrayList();
    /* renamed from: d */
    private Polyline f3322d;
    /* renamed from: e */
    private List<BusStationItem> f3323e;
    /* renamed from: f */
    private BitmapDescriptor f3324f;
    /* renamed from: g */
    private BitmapDescriptor f3325g;
    /* renamed from: h */
    private BitmapDescriptor f3326h;
    /* renamed from: i */
    private Context f3327i;

    public BusLineOverlay(Context context, AMap aMap, BusLineItem busLineItem) {
        this.f3327i = context;
        this.f3319a = busLineItem;
        this.f3320b = aMap;
        this.f3323e = this.f3319a.getBusStations();
    }

    public void addToMap() {
        int i = 1;
        try {
            this.f3322d = this.f3320b.addPolyline(new PolylineOptions().addAll(AMapServicesUtil.m4557a(this.f3319a.getDirectionsCoordinates())).color(getBusColor()).width(getBuslineWidth()));
            if (this.f3323e.size() >= 1) {
                while (i < this.f3323e.size() - 1) {
                    this.f3321c.add(this.f3320b.addMarker(m4515a(i)));
                    i++;
                }
                this.f3321c.add(this.f3320b.addMarker(m4515a(0)));
                this.f3321c.add(this.f3320b.addMarker(m4515a(this.f3323e.size() - 1)));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void removeFromMap() {
        if (this.f3322d != null) {
            this.f3322d.remove();
        }
        try {
            Iterator it = this.f3321c.iterator();
            while (it.hasNext()) {
                ((Marker) it.next()).remove();
            }
            m4516a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m4516a() {
        if (this.f3324f != null) {
            this.f3324f.recycle();
            this.f3324f = null;
        }
        if (this.f3325g != null) {
            this.f3325g.recycle();
            this.f3325g = null;
        }
        if (this.f3326h != null) {
            this.f3326h.recycle();
            this.f3326h = null;
        }
    }

    public void zoomToSpan() {
        if (this.f3320b != null) {
            try {
                List directionsCoordinates = this.f3319a.getDirectionsCoordinates();
                if (directionsCoordinates != null && directionsCoordinates.size() > 0) {
                    this.f3320b.moveCamera(CameraUpdateFactory.newLatLngBounds(m4514a(directionsCoordinates), 5));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private LatLngBounds m4514a(List<LatLonPoint> list) {
        Builder builder = LatLngBounds.builder();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                return builder.build();
            }
            builder.include(new LatLng(((LatLonPoint) list.get(i2)).getLatitude(), ((LatLonPoint) list.get(i2)).getLongitude()));
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    private MarkerOptions m4515a(int i) {
        MarkerOptions snippet = new MarkerOptions().position(new LatLng(((BusStationItem) this.f3323e.get(i)).getLatLonPoint().getLatitude(), ((BusStationItem) this.f3323e.get(i)).getLatLonPoint().getLongitude())).title(getTitle(i)).snippet(getSnippet(i));
        if (i == 0) {
            snippet.icon(getStartBitmapDescriptor());
        } else if (i == this.f3323e.size() - 1) {
            snippet.icon(getEndBitmapDescriptor());
        } else {
            snippet.anchor(0.5f, 0.5f);
            snippet.icon(getBusBitmapDescriptor());
        }
        return snippet;
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getStartBitmapDescriptor() {
        this.f3324f = m4513a("amap_start.png");
        return this.f3324f;
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getEndBitmapDescriptor() {
        this.f3325g = m4513a("amap_end.png");
        return this.f3325g;
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getBusBitmapDescriptor() {
        this.f3326h = m4513a("amap_bus.png");
        return this.f3326h;
    }

    /* Access modifiers changed, original: protected */
    public String getTitle(int i) {
        return ((BusStationItem) this.f3323e.get(i)).getBusStationName();
    }

    /* Access modifiers changed, original: protected */
    public String getSnippet(int i) {
        return "";
    }

    public int getBusStationIndex(Marker marker) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.f3321c.size()) {
                return -1;
            }
            if (((Marker) this.f3321c.get(i2)).equals(marker)) {
                return i2;
            }
            i = i2 + 1;
        }
    }

    public BusStationItem getBusStationItem(int i) {
        if (i < 0 || i >= this.f3323e.size()) {
            return null;
        }
        return (BusStationItem) this.f3323e.get(i);
    }

    /* Access modifiers changed, original: protected */
    public int getBusColor() {
        return Color.parseColor("#537edc");
    }

    /* Access modifiers changed, original: protected */
    public float getBuslineWidth() {
        return 18.0f;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:16:0x002c=Splitter:B:16:0x002c, B:25:0x003f=Splitter:B:25:0x003f} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0051 A:{SYNTHETIC, Splitter:B:35:0x0051} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0044 A:{SYNTHETIC, Splitter:B:28:0x0044} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0044 A:{SYNTHETIC, Splitter:B:28:0x0044} */
    /* renamed from: a */
    private com.amap.api.maps.model.BitmapDescriptor m4513a(java.lang.String r5) {
        /*
        r4 = this;
        r1 = 0;
        r0 = r4.f3327i;	 Catch:{ IOException -> 0x0027, Throwable -> 0x003a, all -> 0x004d }
        r0 = com.amap.api.mapcore.util.ResourcesUtil.m2327a(r0);	 Catch:{ IOException -> 0x0027, Throwable -> 0x003a, all -> 0x004d }
        r2 = r0.open(r5);	 Catch:{ IOException -> 0x0027, Throwable -> 0x003a, all -> 0x004d }
        r1 = com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation.decodeStream(r2);	 Catch:{ IOException -> 0x0066, Throwable -> 0x005c }
        r0 = com.amap.api.mapcore.util.ConfigableConst.f2121a;	 Catch:{ IOException -> 0x006b, Throwable -> 0x0061 }
        r0 = com.amap.api.mapcore.util.Util.m2345a(r1, r0);	 Catch:{ IOException -> 0x006b, Throwable -> 0x0061 }
        if (r2 == 0) goto L_0x001a;
    L_0x0017:
        r2.close();	 Catch:{ IOException -> 0x0022 }
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
        r3 = r1;
        r1 = r0;
        r0 = r3;
    L_0x002c:
        r1.printStackTrace();	 Catch:{ all -> 0x005a }
        if (r2 == 0) goto L_0x001a;
    L_0x0031:
        r2.close();	 Catch:{ IOException -> 0x0035 }
        goto L_0x001a;
    L_0x0035:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001a;
    L_0x003a:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        r1 = r0;
        r0 = r3;
    L_0x003f:
        r1.printStackTrace();	 Catch:{ all -> 0x005a }
        if (r2 == 0) goto L_0x001a;
    L_0x0044:
        r2.close();	 Catch:{ IOException -> 0x0048 }
        goto L_0x001a;
    L_0x0048:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001a;
    L_0x004d:
        r0 = move-exception;
        r2 = r1;
    L_0x004f:
        if (r2 == 0) goto L_0x0054;
    L_0x0051:
        r2.close();	 Catch:{ IOException -> 0x0055 }
    L_0x0054:
        throw r0;
    L_0x0055:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0054;
    L_0x005a:
        r0 = move-exception;
        goto L_0x004f;
    L_0x005c:
        r0 = move-exception;
        r3 = r0;
        r0 = r1;
        r1 = r3;
        goto L_0x003f;
    L_0x0061:
        r0 = move-exception;
        r3 = r0;
        r0 = r1;
        r1 = r3;
        goto L_0x003f;
    L_0x0066:
        r0 = move-exception;
        r3 = r0;
        r0 = r1;
        r1 = r3;
        goto L_0x002c;
    L_0x006b:
        r0 = move-exception;
        r3 = r0;
        r0 = r1;
        r1 = r3;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.maps.overlay.BusLineOverlay.m4513a(java.lang.String):com.amap.api.maps.model.BitmapDescriptor");
    }
}
