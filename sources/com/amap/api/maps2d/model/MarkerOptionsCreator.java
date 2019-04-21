package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.maps2d.model.g */
class MarkerOptionsCreator implements Creator<MarkerOptions> {
    MarkerOptionsCreator() {
    }

    /* renamed from: a */
    public MarkerOptions createFromParcel(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position((LatLng) parcel.readParcelable(LatLng.class.getClassLoader()));
        markerOptions.icon((BitmapDescriptor) parcel.readParcelable(BitmapDescriptor.class.getClassLoader()));
        markerOptions.title(parcel.readString());
        markerOptions.snippet(parcel.readString());
        markerOptions.anchor(parcel.readFloat(), parcel.readFloat());
        markerOptions.visible(parcel.readByte() == (byte) 1);
        if (parcel.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        markerOptions.draggable(z);
        if (parcel.readByte() != (byte) 1) {
            z2 = false;
        }
        markerOptions.setGps(z2);
        markerOptions.f3413a = parcel.readString();
        markerOptions.zIndex(parcel.readFloat());
        markerOptions.icons(parcel.createTypedArrayList(BitmapDescriptor.f3376a));
        return markerOptions;
    }

    /* renamed from: a */
    public MarkerOptions[] newArray(int i) {
        return new MarkerOptions[i];
    }
}
