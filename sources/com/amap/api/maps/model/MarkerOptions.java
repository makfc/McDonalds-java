package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class MarkerOptions implements Parcelable {
    public static final MarkerOptionsCreator CREATOR = new MarkerOptionsCreator();
    /* renamed from: a */
    String f3190a;
    /* renamed from: b */
    private LatLng f3191b;
    /* renamed from: c */
    private String f3192c;
    /* renamed from: d */
    private String f3193d;
    /* renamed from: e */
    private float f3194e = 0.5f;
    /* renamed from: f */
    private float f3195f = 1.0f;
    /* renamed from: g */
    private float f3196g = 0.0f;
    /* renamed from: h */
    private boolean f3197h = false;
    /* renamed from: i */
    private boolean f3198i = true;
    /* renamed from: j */
    private boolean f3199j = false;
    /* renamed from: k */
    private int f3200k = 0;
    /* renamed from: l */
    private int f3201l = 0;
    /* renamed from: m */
    private ArrayList<BitmapDescriptor> f3202m = new ArrayList();
    /* renamed from: n */
    private int f3203n = 20;
    /* renamed from: o */
    private boolean f3204o = false;
    /* renamed from: p */
    private boolean f3205p = false;

    public MarkerOptions icons(ArrayList<BitmapDescriptor> arrayList) {
        this.f3202m = arrayList;
        return this;
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        return this.f3202m;
    }

    public MarkerOptions period(int i) {
        if (i <= 1) {
            this.f3203n = 1;
        } else {
            this.f3203n = i;
        }
        return this;
    }

    public int getPeriod() {
        return this.f3203n;
    }

    public boolean isPerspective() {
        return this.f3199j;
    }

    public MarkerOptions perspective(boolean z) {
        this.f3199j = z;
        return this;
    }

    public MarkerOptions position(LatLng latLng) {
        this.f3191b = latLng;
        return this;
    }

    public MarkerOptions setFlat(boolean z) {
        this.f3205p = z;
        return this;
    }

    /* renamed from: a */
    private void m4485a() {
        if (this.f3202m == null) {
            this.f3202m = new ArrayList();
        }
    }

    public MarkerOptions icon(BitmapDescriptor bitmapDescriptor) {
        m4485a();
        this.f3202m.clear();
        this.f3202m.add(bitmapDescriptor);
        return this;
    }

    public MarkerOptions anchor(float f, float f2) {
        this.f3194e = f;
        this.f3195f = f2;
        return this;
    }

    public MarkerOptions setInfoWindowOffset(int i, int i2) {
        this.f3200k = i;
        this.f3201l = i2;
        return this;
    }

    public MarkerOptions title(String str) {
        this.f3192c = str;
        return this;
    }

    public MarkerOptions snippet(String str) {
        this.f3193d = str;
        return this;
    }

    public MarkerOptions draggable(boolean z) {
        this.f3197h = z;
        return this;
    }

    public MarkerOptions visible(boolean z) {
        this.f3198i = z;
        return this;
    }

    public MarkerOptions setGps(boolean z) {
        this.f3204o = z;
        return this;
    }

    public LatLng getPosition() {
        return this.f3191b;
    }

    public String getTitle() {
        return this.f3192c;
    }

    public String getSnippet() {
        return this.f3193d;
    }

    public BitmapDescriptor getIcon() {
        if (this.f3202m == null || this.f3202m.size() == 0) {
            return null;
        }
        return (BitmapDescriptor) this.f3202m.get(0);
    }

    public float getAnchorU() {
        return this.f3194e;
    }

    public int getInfoWindowOffsetX() {
        return this.f3200k;
    }

    public int getInfoWindowOffsetY() {
        return this.f3201l;
    }

    public float getAnchorV() {
        return this.f3195f;
    }

    public boolean isDraggable() {
        return this.f3197h;
    }

    public boolean isVisible() {
        return this.f3198i;
    }

    public boolean isGps() {
        return this.f3204o;
    }

    public boolean isFlat() {
        return this.f3205p;
    }

    public MarkerOptions zIndex(float f) {
        this.f3196g = f;
        return this;
    }

    public float getZIndex() {
        return this.f3196g;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3191b, i);
        if (!(this.f3202m == null || this.f3202m.size() == 0)) {
            parcel.writeParcelable((Parcelable) this.f3202m.get(0), i);
        }
        parcel.writeString(this.f3192c);
        parcel.writeString(this.f3193d);
        parcel.writeFloat(this.f3194e);
        parcel.writeFloat(this.f3195f);
        parcel.writeInt(this.f3200k);
        parcel.writeInt(this.f3201l);
        parcel.writeBooleanArray(new boolean[]{this.f3198i, this.f3197h, this.f3204o, this.f3205p});
        parcel.writeString(this.f3190a);
        parcel.writeInt(this.f3203n);
        parcel.writeList(this.f3202m);
        parcel.writeFloat(this.f3196g);
    }
}
