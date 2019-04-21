package com.amap.api.maps2d.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.maps2d.model.c */
class CircleOptionsCreator implements Creator<CircleOptions> {
    CircleOptionsCreator() {
    }

    /* renamed from: a */
    public CircleOptions createFromParcel(Parcel parcel) {
        boolean z = true;
        CircleOptions circleOptions = new CircleOptions();
        Bundle readBundle = parcel.readBundle();
        circleOptions.center(new LatLng(readBundle.getDouble("lat"), readBundle.getDouble("lng")));
        circleOptions.radius(parcel.readDouble());
        circleOptions.strokeWidth(parcel.readFloat());
        circleOptions.strokeColor(parcel.readInt());
        circleOptions.fillColor(parcel.readInt());
        circleOptions.zIndex((float) parcel.readInt());
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        circleOptions.visible(z);
        circleOptions.f3385a = parcel.readString();
        return circleOptions;
    }

    /* renamed from: a */
    public CircleOptions[] newArray(int i) {
        return new CircleOptions[i];
    }
}
