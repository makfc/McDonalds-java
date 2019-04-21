package com.amap.api.maps.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class BitmapDescriptorCreator implements Creator<BitmapDescriptor> {
    public BitmapDescriptor createFromParcel(Parcel parcel) {
        BitmapDescriptor bitmapDescriptor = new BitmapDescriptor(null);
        bitmapDescriptor.f3133c = (Bitmap) parcel.readParcelable(BitmapDescriptor.class.getClassLoader());
        bitmapDescriptor.f3131a = parcel.readInt();
        bitmapDescriptor.f3132b = parcel.readInt();
        return bitmapDescriptor;
    }

    public BitmapDescriptor[] newArray(int i) {
        return new BitmapDescriptor[i];
    }
}
