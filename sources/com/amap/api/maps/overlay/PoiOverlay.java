package com.amap.api.maps.overlay;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.PoiItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PoiOverlay {
    /* renamed from: a */
    private List<PoiItem> f3342a;
    /* renamed from: b */
    private AMap f3343b;
    /* renamed from: c */
    private ArrayList<Marker> f3344c = new ArrayList();

    public PoiOverlay(AMap aMap, List<PoiItem> list) {
        this.f3343b = aMap;
        this.f3342a = list;
    }

    public void addToMap() {
        int i = 0;
        while (i < this.f3342a.size()) {
            try {
                Marker addMarker = this.f3343b.addMarker(m4548a(i));
                addMarker.setObject(Integer.valueOf(i));
                this.f3344c.add(addMarker);
                i++;
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
    }

    public void removeFromMap() {
        Iterator it = this.f3344c.iterator();
        while (it.hasNext()) {
            ((Marker) it.next()).remove();
        }
    }

    public void zoomToSpan() {
        try {
            if (this.f3342a != null && this.f3342a.size() > 0 && this.f3343b != null) {
                if (this.f3342a.size() == 1) {
                    this.f3343b.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(((PoiItem) this.f3342a.get(0)).getLatLonPoint().getLatitude(), ((PoiItem) this.f3342a.get(0)).getLatLonPoint().getLongitude()), 18.0f));
                    return;
                }
                this.f3343b.moveCamera(CameraUpdateFactory.newLatLngBounds(m4547a(), 5));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private LatLngBounds m4547a() {
        Builder builder = LatLngBounds.builder();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.f3342a.size()) {
                return builder.build();
            }
            builder.include(new LatLng(((PoiItem) this.f3342a.get(i2)).getLatLonPoint().getLatitude(), ((PoiItem) this.f3342a.get(i2)).getLatLonPoint().getLongitude()));
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    private MarkerOptions m4548a(int i) {
        return new MarkerOptions().position(new LatLng(((PoiItem) this.f3342a.get(i)).getLatLonPoint().getLatitude(), ((PoiItem) this.f3342a.get(i)).getLatLonPoint().getLongitude())).title(getTitle(i)).snippet(getSnippet(i)).icon(getBitmapDescriptor(i));
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getBitmapDescriptor(int i) {
        return null;
    }

    /* Access modifiers changed, original: protected */
    public String getTitle(int i) {
        return ((PoiItem) this.f3342a.get(i)).getTitle();
    }

    /* Access modifiers changed, original: protected */
    public String getSnippet(int i) {
        return ((PoiItem) this.f3342a.get(i)).getSnippet();
    }

    public int getPoiIndex(Marker marker) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.f3344c.size()) {
                return -1;
            }
            if (((Marker) this.f3344c.get(i2)).equals(marker)) {
                return i2;
            }
            i = i2 + 1;
        }
    }

    public PoiItem getPoiItem(int i) {
        if (i < 0 || i >= this.f3342a.size()) {
            return null;
        }
        return (PoiItem) this.f3342a.get(i);
    }
}
