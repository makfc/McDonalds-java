package com.mcdonalds.app.storelocator.maps.model;

import com.ensighten.Ensighten;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class MarkerOptions {
    private float mAlpha;
    private int mIcon;
    private LatLng mPosition;
    private String mSnippet;

    public MarkerOptions position(LatLng latLng) {
        Ensighten.evaluateEvent(this, "position", new Object[]{latLng});
        this.mPosition = latLng;
        return this;
    }

    public MarkerOptions icon(int resId) {
        Ensighten.evaluateEvent(this, "icon", new Object[]{new Integer(resId)});
        this.mIcon = resId;
        return this;
    }

    public MarkerOptions alpha(float value) {
        Ensighten.evaluateEvent(this, "alpha", new Object[]{new Float(value)});
        this.mAlpha = value;
        return this;
    }

    public MarkerOptions snippet(String value) {
        Ensighten.evaluateEvent(this, "snippet", new Object[]{value});
        this.mSnippet = value;
        return this;
    }

    public com.google.android.gms.maps.model.MarkerOptions toGMap() {
        Ensighten.evaluateEvent(this, "toGMap", null);
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(this.mIcon);
        com.google.android.gms.maps.model.MarkerOptions options = new com.google.android.gms.maps.model.MarkerOptions();
        options.position(this.mPosition.toGmaps());
        options.icon(descriptor);
        options.snippet(this.mSnippet);
        return options;
    }

    public com.amap.api.maps.model.MarkerOptions toAMap() {
        Ensighten.evaluateEvent(this, "toAMap", null);
        com.amap.api.maps.model.BitmapDescriptor descriptor = com.amap.api.maps.model.BitmapDescriptorFactory.fromResource(this.mIcon);
        com.amap.api.maps.model.MarkerOptions options = new com.amap.api.maps.model.MarkerOptions();
        options.icon(descriptor);
        options.position(this.mPosition.toAMap());
        options.snippet(this.mSnippet);
        options.zIndex(-1.0f);
        return options;
    }

    public com.amap.api.maps2d.model.MarkerOptions toAMap2D() {
        Ensighten.evaluateEvent(this, "toAMap2D", null);
        com.amap.api.maps2d.model.BitmapDescriptor descriptor = com.amap.api.maps2d.model.BitmapDescriptorFactory.fromResource(this.mIcon);
        com.amap.api.maps2d.model.MarkerOptions options = new com.amap.api.maps2d.model.MarkerOptions();
        options.icon(descriptor);
        options.position(this.mPosition.toAMap2D());
        options.snippet(this.mSnippet);
        options.zIndex(-1.0f);
        return options;
    }
}
