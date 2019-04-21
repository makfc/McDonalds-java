package com.amap.api.maps2d.overlay;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.LatLngBounds.Builder;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.Polyline;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BusLineOverlay {
    /* renamed from: a */
    private BusLineItem f3491a;
    /* renamed from: b */
    private AMap f3492b;
    /* renamed from: c */
    private ArrayList<Marker> f3493c = new ArrayList();
    /* renamed from: d */
    private Polyline f3494d;
    /* renamed from: e */
    private List<BusStationItem> f3495e;
    /* renamed from: f */
    private BitmapDescriptor f3496f;
    /* renamed from: g */
    private BitmapDescriptor f3497g;
    /* renamed from: h */
    private BitmapDescriptor f3498h;
    /* renamed from: i */
    private AssetManager f3499i;
    /* renamed from: j */
    private Context f3500j;

    public BusLineOverlay(Context context, AMap aMap, BusLineItem busLineItem) {
        this.f3500j = context;
        this.f3491a = busLineItem;
        this.f3492b = aMap;
        this.f3495e = this.f3491a.getBusStations();
        this.f3499i = this.f3500j.getResources().getAssets();
    }

    public void addToMap() {
        int i = 1;
        this.f3494d = this.f3492b.addPolyline(new PolylineOptions().addAll(C1068a.m4637a(this.f3491a.getDirectionsCoordinates())).color(getBusColor()).width(getBuslineWidth()));
        if (this.f3495e.size() >= 1) {
            while (i < this.f3495e.size() - 1) {
                this.f3493c.add(this.f3492b.addMarker(m4612a(i)));
                i++;
            }
            this.f3493c.add(this.f3492b.addMarker(m4612a(0)));
            this.f3493c.add(this.f3492b.addMarker(m4612a(this.f3495e.size() - 1)));
        }
    }

    public void removeFromMap() {
        if (this.f3494d != null) {
            this.f3494d.remove();
        }
        Iterator it = this.f3493c.iterator();
        while (it.hasNext()) {
            ((Marker) it.next()).remove();
        }
        m4613a();
    }

    /* renamed from: a */
    private void m4613a() {
        if (this.f3496f != null) {
            this.f3496f.recycle();
            this.f3496f = null;
        }
        if (this.f3497g != null) {
            this.f3497g.recycle();
            this.f3497g = null;
        }
        if (this.f3498h != null) {
            this.f3498h.recycle();
            this.f3498h = null;
        }
    }

    public void zoomToSpan() {
        if (this.f3492b != null) {
            List directionsCoordinates = this.f3491a.getDirectionsCoordinates();
            if (directionsCoordinates != null && directionsCoordinates.size() > 0) {
                this.f3492b.moveCamera(CameraUpdateFactory.newLatLngBounds(m4611a(directionsCoordinates), 5));
            }
        }
    }

    /* renamed from: a */
    private LatLngBounds m4611a(List<LatLonPoint> list) {
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
    private MarkerOptions m4612a(int i) {
        MarkerOptions snippet = new MarkerOptions().position(new LatLng(((BusStationItem) this.f3495e.get(i)).getLatLonPoint().getLatitude(), ((BusStationItem) this.f3495e.get(i)).getLatLonPoint().getLongitude())).title(getTitle(i)).snippet(getSnippet(i));
        if (i == 0) {
            snippet.icon(getStartBitmapDescriptor());
        } else if (i == this.f3495e.size() - 1) {
            snippet.icon(getEndBitmapDescriptor());
        } else {
            snippet.anchor(0.5f, 0.5f);
            snippet.icon(getBusBitmapDescriptor());
        }
        return snippet;
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getStartBitmapDescriptor() {
        this.f3496f = m4610a("amap_start.png");
        return this.f3496f;
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getEndBitmapDescriptor() {
        this.f3497g = m4610a("amap_end.png");
        return this.f3497g;
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getBusBitmapDescriptor() {
        this.f3498h = m4610a("amap_bus.png");
        return this.f3498h;
    }

    /* Access modifiers changed, original: protected */
    public String getTitle(int i) {
        return ((BusStationItem) this.f3495e.get(i)).getBusStationName();
    }

    /* Access modifiers changed, original: protected */
    public String getSnippet(int i) {
        return "";
    }

    public int getBusStationIndex(Marker marker) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.f3493c.size()) {
                return -1;
            }
            if (((Marker) this.f3493c.get(i2)).equals(marker)) {
                return i2;
            }
            i = i2 + 1;
        }
    }

    public BusStationItem getBusStationItem(int i) {
        if (i < 0 || i >= this.f3495e.size()) {
            return null;
        }
        return (BusStationItem) this.f3495e.get(i);
    }

    /* Access modifiers changed, original: protected */
    public int getBusColor() {
        return Color.parseColor("#537edc");
    }

    /* Access modifiers changed, original: protected */
    public float getBuslineWidth() {
        return 18.0f;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x003f A:{SYNTHETIC, Splitter:B:26:0x003f} */
    /* renamed from: a */
    private com.amap.api.maps2d.model.BitmapDescriptor m4610a(java.lang.String r7) {
        /*
        r6 = this;
        r1 = 0;
        r3 = "getBitDes";
        r0 = r6.f3499i;	 Catch:{ IOException -> 0x0024, all -> 0x003b }
        r2 = r0.open(r7);	 Catch:{ IOException -> 0x0024, all -> 0x003b }
        r1 = com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation.decodeStream(r2);	 Catch:{ IOException -> 0x004c }
        r0 = com.amap.api.mapcore2d.C1042p.f3031a;	 Catch:{ IOException -> 0x0051 }
        r0 = com.amap.api.mapcore2d.C0955ck.m3881a(r1, r0);	 Catch:{ IOException -> 0x0051 }
        if (r2 == 0) goto L_0x0018;
    L_0x0015:
        r2.close();	 Catch:{ IOException -> 0x001d }
    L_0x0018:
        r0 = com.amap.api.maps2d.model.BitmapDescriptorFactory.fromBitmap(r0);
        return r0;
    L_0x001d:
        r1 = move-exception;
        r2 = "BusLineOverlay";
        com.amap.api.mapcore2d.C0955ck.m3888a(r1, r2, r3);
        goto L_0x0018;
    L_0x0024:
        r0 = move-exception;
        r2 = r1;
        r5 = r1;
        r1 = r0;
        r0 = r5;
    L_0x0029:
        r4 = "BusLineOverlay";
        com.amap.api.mapcore2d.C0955ck.m3888a(r1, r4, r3);	 Catch:{ all -> 0x004a }
        if (r2 == 0) goto L_0x0018;
    L_0x0030:
        r2.close();	 Catch:{ IOException -> 0x0034 }
        goto L_0x0018;
    L_0x0034:
        r1 = move-exception;
        r2 = "BusLineOverlay";
        com.amap.api.mapcore2d.C0955ck.m3888a(r1, r2, r3);
        goto L_0x0018;
    L_0x003b:
        r0 = move-exception;
        r2 = r1;
    L_0x003d:
        if (r2 == 0) goto L_0x0042;
    L_0x003f:
        r2.close();	 Catch:{ IOException -> 0x0043 }
    L_0x0042:
        throw r0;
    L_0x0043:
        r1 = move-exception;
        r2 = "BusLineOverlay";
        com.amap.api.mapcore2d.C0955ck.m3888a(r1, r2, r3);
        goto L_0x0042;
    L_0x004a:
        r0 = move-exception;
        goto L_0x003d;
    L_0x004c:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x0029;
    L_0x0051:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x0029;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.maps2d.overlay.BusLineOverlay.m4610a(java.lang.String):com.amap.api.maps2d.model.BitmapDescriptor");
    }
}
