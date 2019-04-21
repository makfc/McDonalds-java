package com.amap.api.maps2d.model;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.amap.api.mapcore2d.C0955ck;

/* renamed from: com.amap.api.maps2d.model.e */
class LatLngBoundsCreator implements Creator<LatLngBounds> {
    LatLngBoundsCreator() {
    }

    /* renamed from: a */
    public LatLngBounds createFromParcel(Parcel parcel) {
        LatLng latLng;
        LatLng latLng2;
        Throwable e;
        String str = "createFromParcel";
        int readInt = parcel.readInt();
        try {
            latLng = (LatLng) parcel.readParcelable(LatLngBounds.class.getClassLoader());
            try {
                latLng2 = (LatLng) parcel.readParcelable(LatLngBounds.class.getClassLoader());
            } catch (BadParcelableException e2) {
                e = e2;
                C0955ck.m3888a(e, "LatLngBoundsCreator", str);
                latLng2 = null;
                return new LatLngBounds(readInt, latLng, latLng2);
            }
        } catch (BadParcelableException e3) {
            e = e3;
            latLng = null;
            C0955ck.m3888a(e, "LatLngBoundsCreator", str);
            latLng2 = null;
            return new LatLngBounds(readInt, latLng, latLng2);
        }
        return new LatLngBounds(readInt, latLng, latLng2);
    }

    /* renamed from: a */
    public LatLngBounds[] newArray(int i) {
        return new LatLngBounds[i];
    }

    /* renamed from: a */
    static void m4592a(LatLngBounds latLngBounds, Parcel parcel, int i) {
        parcel.writeInt(latLngBounds.mo11432a());
        parcel.writeParcelable(latLngBounds.southwest, i);
        parcel.writeParcelable(latLngBounds.northeast, i);
    }
}
