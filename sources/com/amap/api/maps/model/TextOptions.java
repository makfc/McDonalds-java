package com.amap.api.maps.model;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.ViewCompat;

public final class TextOptions implements Parcelable {
    public static final TextOptionsCreator CREATOR = new TextOptionsCreator();
    /* renamed from: a */
    String f3261a;
    /* renamed from: b */
    private LatLng f3262b;
    /* renamed from: c */
    private String f3263c;
    /* renamed from: d */
    private Typeface f3264d = Typeface.DEFAULT;
    /* renamed from: e */
    private float f3265e;
    /* renamed from: f */
    private int f3266f = 4;
    /* renamed from: g */
    private int f3267g = 32;
    /* renamed from: h */
    private int f3268h = -1;
    /* renamed from: i */
    private int f3269i = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: j */
    private Object f3270j;
    /* renamed from: k */
    private int f3271k = 20;
    /* renamed from: l */
    private float f3272l = 0.0f;
    /* renamed from: m */
    private boolean f3273m = true;

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3261a);
        Bundle bundle = new Bundle();
        if (this.f3262b != null) {
            bundle.putDouble("lat", this.f3262b.latitude);
            bundle.putDouble("lng", this.f3262b.longitude);
        }
        parcel.writeBundle(bundle);
        parcel.writeString(this.f3263c);
        parcel.writeInt(this.f3264d.getStyle());
        parcel.writeFloat(this.f3265e);
        parcel.writeInt(this.f3266f);
        parcel.writeInt(this.f3267g);
        parcel.writeInt(this.f3268h);
        parcel.writeInt(this.f3269i);
        parcel.writeInt(this.f3271k);
        parcel.writeFloat(this.f3272l);
        parcel.writeByte((byte) (this.f3273m ? 1 : 0));
        if (this.f3270j instanceof Parcelable) {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("obj", (Parcelable) this.f3270j);
            parcel.writeBundle(bundle2);
        }
    }

    public int describeContents() {
        return 0;
    }

    public TextOptions position(LatLng latLng) {
        this.f3262b = latLng;
        return this;
    }

    public TextOptions text(String str) {
        this.f3263c = str;
        return this;
    }

    public TextOptions typeface(Typeface typeface) {
        if (typeface != null) {
            this.f3264d = typeface;
        }
        return this;
    }

    public TextOptions visible(boolean z) {
        this.f3273m = z;
        return this;
    }

    public TextOptions zIndex(float f) {
        this.f3272l = f;
        return this;
    }

    public TextOptions rotate(float f) {
        this.f3265e = f;
        return this;
    }

    public TextOptions align(int i, int i2) {
        this.f3266f = i;
        this.f3267g = i2;
        return this;
    }

    public TextOptions backgroundColor(int i) {
        this.f3268h = i;
        return this;
    }

    public TextOptions setObject(Object obj) {
        this.f3270j = obj;
        return this;
    }

    public TextOptions fontColor(int i) {
        this.f3269i = i;
        return this;
    }

    public TextOptions fontSize(int i) {
        this.f3271k = i;
        return this;
    }

    public LatLng getPosition() {
        return this.f3262b;
    }

    public String getText() {
        return this.f3263c;
    }

    public Typeface getTypeface() {
        return this.f3264d;
    }

    public float getRotate() {
        return this.f3265e;
    }

    public int getAlignX() {
        return this.f3266f;
    }

    public int getAlignY() {
        return this.f3267g;
    }

    public int getBackgroundColor() {
        return this.f3268h;
    }

    public int getFontColor() {
        return this.f3269i;
    }

    public Object getObject() {
        return this.f3270j;
    }

    public int getFontSize() {
        return this.f3271k;
    }

    public float getZIndex() {
        return this.f3272l;
    }

    public boolean isVisible() {
        return this.f3273m;
    }
}
