package com.mcdonalds.app.storelocator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.amap.api.maps.AMap;
import com.ensighten.Ensighten;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.mcdonalds.app.storelocator.maps.McdMap.InfoWindowAdapter;
import com.mcdonalds.gma.hongkong.C2658R;

public class MapWindowAdapter implements InfoWindowAdapter {
    private LayoutInflater mInflater;

    /* renamed from: com.mcdonalds.app.storelocator.MapWindowAdapter$1 */
    class C37071 implements GoogleMap.InfoWindowAdapter {
        C37071() {
        }

        public View getInfoWindow(Marker marker) {
            Ensighten.evaluateEvent(this, "getInfoWindow", new Object[]{marker});
            return MapWindowAdapter.this.getInfoWindow(null);
        }

        public View getInfoContents(Marker marker) {
            Ensighten.evaluateEvent(this, "getInfoContents", new Object[]{marker});
            return null;
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.MapWindowAdapter$2 */
    class C37082 implements AMap.InfoWindowAdapter {
        C37082() {
        }

        public View getInfoWindow(com.amap.api.maps.model.Marker marker) {
            Ensighten.evaluateEvent(this, "getInfoWindow", new Object[]{marker});
            return MapWindowAdapter.this.getInfoWindow(null);
        }

        public View getInfoContents(com.amap.api.maps.model.Marker marker) {
            Ensighten.evaluateEvent(this, "getInfoContents", new Object[]{marker});
            return null;
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.MapWindowAdapter$3 */
    class C37093 implements com.amap.api.maps2d.AMap.InfoWindowAdapter {
        C37093() {
        }

        public View getInfoWindow(com.amap.api.maps2d.model.Marker marker) {
            Ensighten.evaluateEvent(this, "getInfoWindow", new Object[]{marker});
            return MapWindowAdapter.this.getInfoWindow(null);
        }

        public View getInfoContents(com.amap.api.maps2d.model.Marker marker) {
            Ensighten.evaluateEvent(this, "getInfoContents", new Object[]{marker});
            return null;
        }
    }

    public MapWindowAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public View getInfoWindow(com.mcdonalds.app.storelocator.maps.model.Marker marker) {
        Ensighten.evaluateEvent(this, "getInfoWindow", new Object[]{marker});
        return this.mInflater.inflate(C2658R.layout.no_info_window, null, false);
    }

    public GoogleMap.InfoWindowAdapter toGMap() {
        Ensighten.evaluateEvent(this, "toGMap", null);
        return new C37071();
    }

    public AMap.InfoWindowAdapter toAMap() {
        Ensighten.evaluateEvent(this, "toAMap", null);
        return new C37082();
    }

    public com.amap.api.maps2d.AMap.InfoWindowAdapter toAMap2D() {
        Ensighten.evaluateEvent(this, "toAMap2D", null);
        return new C37093();
    }
}
