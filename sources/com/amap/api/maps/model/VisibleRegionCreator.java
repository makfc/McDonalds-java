package com.amap.api.maps.model;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class VisibleRegionCreator implements Creator<VisibleRegion> {
    public static final int CONTENT_DESCRIPTION = 0;

    public VisibleRegion createFromParcel(Parcel parcel) {
        LatLng latLng;
        LatLng latLng2;
        LatLng latLng3;
        LatLng latLng4;
        BadParcelableException badParcelableException;
        BadParcelableException e;
        LatLngBounds latLngBounds = null;
        int readInt = parcel.readInt();
        try {
            LatLng latLng5 = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
            try {
                latLng = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
                try {
                    latLng2 = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
                } catch (BadParcelableException e2) {
                    latLng3 = null;
                    latLng4 = null;
                    badParcelableException = e2;
                    latLng2 = latLng;
                    latLng = latLng5;
                    e = badParcelableException;
                    e.printStackTrace();
                    return new VisibleRegion(readInt, latLng, latLng2, latLng4, latLng3, latLngBounds);
                }
                try {
                    latLng4 = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
                    try {
                        latLngBounds = (LatLngBounds) parcel.readParcelable(LatLngBounds.class.getClassLoader());
                        latLng3 = latLng4;
                        latLng4 = latLng2;
                        latLng2 = latLng;
                        latLng = latLng5;
                    } catch (BadParcelableException e3) {
                        badParcelableException = e3;
                        latLng3 = latLng4;
                        latLng4 = latLng2;
                        latLng2 = latLng;
                        latLng = latLng5;
                        e = badParcelableException;
                        e.printStackTrace();
                        return new VisibleRegion(readInt, latLng, latLng2, latLng4, latLng3, latLngBounds);
                    }
                } catch (BadParcelableException e4) {
                    latLng3 = null;
                    badParcelableException = e4;
                    latLng4 = latLng2;
                    latLng2 = latLng;
                    latLng = latLng5;
                    e = badParcelableException;
                    e.printStackTrace();
                    return new VisibleRegion(readInt, latLng, latLng2, latLng4, latLng3, latLngBounds);
                }
            } catch (BadParcelableException e5) {
                latLng3 = null;
                latLng4 = null;
                latLng2 = null;
                badParcelableException = e5;
                latLng = latLng5;
                e = badParcelableException;
                e.printStackTrace();
                return new VisibleRegion(readInt, latLng, latLng2, latLng4, latLng3, latLngBounds);
            }
        } catch (BadParcelableException e6) {
            e = e6;
            latLng3 = null;
            latLng4 = null;
            latLng2 = null;
            latLng = null;
            e.printStackTrace();
            return new VisibleRegion(readInt, latLng, latLng2, latLng4, latLng3, latLngBounds);
        }
        return new VisibleRegion(readInt, latLng, latLng2, latLng4, latLng3, latLngBounds);
    }

    public VisibleRegion[] newArray(int i) {
        return new VisibleRegion[i];
    }

    /* renamed from: a */
    static void m4487a(VisibleRegion visibleRegion, Parcel parcel, int i) {
        parcel.writeInt(visibleRegion.mo11065a());
        parcel.writeParcelable(visibleRegion.nearLeft, i);
        parcel.writeParcelable(visibleRegion.nearRight, i);
        parcel.writeParcelable(visibleRegion.farLeft, i);
        parcel.writeParcelable(visibleRegion.farRight, i);
        parcel.writeParcelable(visibleRegion.latLngBounds, i);
    }
}
