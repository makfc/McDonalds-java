package com.amap.api.maps2d.overlay;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.LatLngBounds.Builder;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.services.core.PoiItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PoiOverlay {
    /* renamed from: a */
    private List<PoiItem> f3516a;
    /* renamed from: b */
    private AMap f3517b;
    /* renamed from: c */
    private ArrayList<Marker> f3518c = new ArrayList();

    public PoiOverlay(AMap aMap, List<PoiItem> list) {
        this.f3517b = aMap;
        this.f3516a = list;
    }

    public void addToMap() {
        for (int i = 0; i < this.f3516a.size(); i++) {
            Marker addMarker = this.f3517b.addMarker(m4634a(i));
            addMarker.setObject(Integer.valueOf(i));
            this.f3518c.add(addMarker);
        }
    }

    public void removeFromMap() {
        Iterator it = this.f3518c.iterator();
        while (it.hasNext()) {
            ((Marker) it.next()).remove();
        }
    }

    public void zoomToSpan() {
        if (this.f3516a != null && this.f3516a.size() > 0 && this.f3517b != null) {
            this.f3517b.moveCamera(CameraUpdateFactory.newLatLngBounds(m4633a(), 5));
        }
    }

    /* renamed from: a */
    private LatLngBounds m4633a() {
        Builder builder = LatLngBounds.builder();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.f3516a.size()) {
                return builder.build();
            }
            builder.include(new LatLng(((PoiItem) this.f3516a.get(i2)).getLatLonPoint().getLatitude(), ((PoiItem) this.f3516a.get(i2)).getLatLonPoint().getLongitude()));
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    private MarkerOptions m4634a(int i) {
        return new MarkerOptions().position(new LatLng(((PoiItem) this.f3516a.get(i)).getLatLonPoint().getLatitude(), ((PoiItem) this.f3516a.get(i)).getLatLonPoint().getLongitude())).title(getTitle(i)).snippet(getSnippet(i)).icon(getBitmapDescriptor(i));
    }

    /* Access modifiers changed, original: protected */
    public BitmapDescriptor getBitmapDescriptor(int i) {
        return null;
    }

    /* Access modifiers changed, original: protected */
    public String getTitle(int i) {
        return ((PoiItem) this.f3516a.get(i)).getTitle();
    }

    /* Access modifiers changed, original: protected */
    public String getSnippet(int i) {
        return ((PoiItem) this.f3516a.get(i)).getSnippet();
    }

    public int getPoiIndex(Marker marker) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.f3518c.size()) {
                return -1;
            }
            if (((Marker) this.f3518c.get(i2)).equals(marker)) {
                return i2;
            }
            i = i2 + 1;
        }
    }

    public PoiItem getPoiItem(int i) {
        if (i < 0 || i >= this.f3516a.size()) {
            return null;
        }
        return (PoiItem) this.f3516a.get(i);
    }
}
