package com.amap.api.maps2d.model;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.ViewCompat;

public class TextOptions implements Parcelable {
    public static final TextOptionsCreator CREATOR = new TextOptionsCreator();
    /* renamed from: a */
    private String f3463a;
    /* renamed from: b */
    private Typeface f3464b = Typeface.DEFAULT;
    /* renamed from: c */
    private boolean f3465c = true;
    /* renamed from: d */
    private float f3466d;
    /* renamed from: e */
    private LatLng f3467e;
    /* renamed from: f */
    private float f3468f = 0.0f;
    /* renamed from: g */
    private int f3469g = 0;
    /* renamed from: h */
    private Object f3470h;
    /* renamed from: i */
    private int f3471i = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: j */
    private int f3472j = 20;
    /* renamed from: k */
    private int f3473k = 3;
    /* renamed from: l */
    private int f3474l = 6;

    public TextOptions text(String str) {
        this.f3463a = str;
        return this;
    }

    public TextOptions typeface(Typeface typeface) {
        this.f3464b = typeface;
        return this;
    }

    public TextOptions visible(boolean z) {
        this.f3465c = z;
        return this;
    }

    public TextOptions zIndex(float f) {
        this.f3466d = f;
        return this;
    }

    public TextOptions position(LatLng latLng) {
        this.f3467e = latLng;
        return this;
    }

    public TextOptions rotate(float f) {
        this.f3468f = f;
        return this;
    }

    public TextOptions align(int i, int i2) {
        this.f3473k = i;
        this.f3474l = i2;
        return this;
    }

    public TextOptions backgroundColor(int i) {
        this.f3469g = i;
        return this;
    }

    public TextOptions setObject(Object obj) {
        this.f3470h = obj;
        return this;
    }

    public TextOptions fontColor(int i) {
        this.f3471i = i;
        return this;
    }

    public TextOptions fontSize(int i) {
        this.f3472j = i;
        return this;
    }

    public int getAlignX() {
        return this.f3473k;
    }

    public int getAlignY() {
        return this.f3474l;
    }

    public int getBackgroundColor() {
        return this.f3469g;
    }

    public Object getObject() {
        return this.f3470h;
    }

    public int getFontColor() {
        return this.f3471i;
    }

    public int getFontSize() {
        return this.f3472j;
    }

    public LatLng getPosition() {
        return this.f3467e;
    }

    public float getRotate() {
        return this.f3468f;
    }

    public String getText() {
        return this.f3463a;
    }

    public Typeface getTypeface() {
        return this.f3464b;
    }

    public float getZIndex() {
        return this.f3466d;
    }

    public boolean isVisible() {
        return this.f3465c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        if (this.f3467e != null) {
            bundle.putDouble("lat", this.f3467e.latitude);
            bundle.putDouble("lng", this.f3467e.longitude);
        }
        parcel.writeBundle(bundle);
        parcel.writeString(this.f3463a);
        parcel.writeInt(this.f3464b.getStyle());
        parcel.writeFloat(this.f3468f);
        parcel.writeInt(this.f3473k);
        parcel.writeInt(this.f3474l);
        parcel.writeInt(this.f3469g);
        parcel.writeInt(this.f3471i);
        parcel.writeInt(this.f3472j);
        parcel.writeFloat(this.f3466d);
        parcel.writeByte((byte) (this.f3465c ? 1 : 0));
        if (this.f3470h instanceof Parcelable) {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("obj", (Parcelable) this.f3470h);
            parcel.writeBundle(bundle2);
        }
    }
}
