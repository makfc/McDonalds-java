package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class MarkerOptions implements Parcelable {
    public static final MarkerOptionsCreator CREATOR = new MarkerOptionsCreator();
    /* renamed from: a */
    String f3413a;
    /* renamed from: b */
    private LatLng f3414b;
    /* renamed from: c */
    private String f3415c;
    /* renamed from: d */
    private String f3416d;
    /* renamed from: e */
    private float f3417e = 0.5f;
    /* renamed from: f */
    private float f3418f = 1.0f;
    /* renamed from: g */
    private boolean f3419g = false;
    /* renamed from: h */
    private boolean f3420h = true;
    /* renamed from: i */
    private boolean f3421i = false;
    /* renamed from: j */
    private float f3422j;
    /* renamed from: k */
    private ArrayList<BitmapDescriptor> f3423k = new ArrayList();
    /* renamed from: l */
    private int f3424l = 20;

    public MarkerOptions icons(ArrayList<BitmapDescriptor> arrayList) {
        this.f3423k = arrayList;
        return this;
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        return this.f3423k;
    }

    public MarkerOptions period(int i) {
        if (i <= 1) {
            this.f3424l = 1;
        } else {
            this.f3424l = i;
        }
        return this;
    }

    public int getPeriod() {
        return this.f3424l;
    }

    public MarkerOptions position(LatLng latLng) {
        this.f3414b = latLng;
        return this;
    }

    public MarkerOptions icon(BitmapDescriptor bitmapDescriptor) {
        m4580a();
        this.f3423k.clear();
        this.f3423k.add(bitmapDescriptor);
        return this;
    }

    public MarkerOptions anchor(float f, float f2) {
        this.f3417e = f;
        this.f3418f = f2;
        return this;
    }

    public MarkerOptions title(String str) {
        this.f3415c = str;
        return this;
    }

    public MarkerOptions snippet(String str) {
        this.f3416d = str;
        return this;
    }

    public MarkerOptions draggable(boolean z) {
        this.f3419g = z;
        return this;
    }

    public MarkerOptions visible(boolean z) {
        this.f3420h = z;
        return this;
    }

    public MarkerOptions setGps(boolean z) {
        this.f3421i = z;
        return this;
    }

    public MarkerOptions zIndex(float f) {
        this.f3422j = f;
        return this;
    }

    public float getZIndex() {
        return this.f3422j;
    }

    public LatLng getPosition() {
        return this.f3414b;
    }

    public String getTitle() {
        return this.f3415c;
    }

    public String getSnippet() {
        return this.f3416d;
    }

    public BitmapDescriptor getIcon() {
        if (this.f3423k == null || this.f3423k.size() == 0) {
            return null;
        }
        return (BitmapDescriptor) this.f3423k.get(0);
    }

    public float getAnchorU() {
        return this.f3417e;
    }

    public float getAnchorV() {
        return this.f3418f;
    }

    public boolean isDraggable() {
        return this.f3419g;
    }

    public boolean isVisible() {
        return this.f3420h;
    }

    public boolean isGps() {
        return this.f3421i;
    }

    /* renamed from: a */
    private void m4580a() {
        if (this.f3423k == null) {
            this.f3423k = new ArrayList();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeParcelable(this.f3414b, i);
        if (!(this.f3423k == null || this.f3423k.size() == 0)) {
            parcel.writeParcelable((Parcelable) this.f3423k.get(0), i);
        }
        parcel.writeString(this.f3415c);
        parcel.writeString(this.f3416d);
        parcel.writeFloat(this.f3417e);
        parcel.writeFloat(this.f3418f);
        parcel.writeByte((byte) (this.f3420h ? 1 : 0));
        if (this.f3419g) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (!this.f3421i) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        parcel.writeString(this.f3413a);
        parcel.writeFloat(this.f3422j);
        parcel.writeList(this.f3423k);
    }
}
