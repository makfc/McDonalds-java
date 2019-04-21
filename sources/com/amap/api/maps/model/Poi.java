package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Poi implements Parcelable {
    public static final PoiCreator CREATOR = new PoiCreator();
    /* renamed from: a */
    private final String f3226a;
    /* renamed from: b */
    private final LatLng f3227b;
    /* renamed from: c */
    private final String f3228c;

    public Poi(String str, LatLng latLng, String str2) {
        this.f3226a = str;
        this.f3227b = latLng;
        this.f3228c = str2;
    }

    public String getName() {
        return this.f3226a;
    }

    public LatLng getCoordinate() {
        return this.f3227b;
    }

    public String getPoiId() {
        return this.f3228c;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Poi)) {
            return false;
        }
        Poi poi = (Poi) obj;
        if (poi.getName().equals(this.f3226a) && poi.getCoordinate().equals(this.f3227b) && poi.getPoiId().equals(this.f3228c)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "poiid " + this.f3228c + " name:" + this.f3226a + "  coordinate:" + this.f3227b.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3226a);
        parcel.writeParcelable(this.f3227b, i);
        parcel.writeString(this.f3228c);
    }
}
