package com.amap.api.maps2d.model;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.amap.api.mapcore2d.C0955ck;

/* renamed from: com.amap.api.maps2d.model.l */
class VisibleRegionCreator implements Creator<VisibleRegion> {
    VisibleRegionCreator() {
    }

    /* renamed from: a */
    public VisibleRegion createFromParcel(Parcel parcel) {
        LatLng latLng;
        LatLng latLng2;
        LatLng latLng3;
        LatLng latLng4;
        Throwable th;
        Throwable e;
        LatLngBounds latLngBounds = null;
        String str = "createFromParcel";
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
                    th = e2;
                    latLng2 = latLng;
                    latLng = latLng5;
                    e = th;
                    C0955ck.m3888a(e, "VisibleRegionCreator", str);
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
                        th = e3;
                        latLng3 = latLng4;
                        latLng4 = latLng2;
                        latLng2 = latLng;
                        latLng = latLng5;
                        e = th;
                        C0955ck.m3888a(e, "VisibleRegionCreator", str);
                        return new VisibleRegion(readInt, latLng, latLng2, latLng4, latLng3, latLngBounds);
                    }
                } catch (BadParcelableException e4) {
                    latLng3 = null;
                    th = e4;
                    latLng4 = latLng2;
                    latLng2 = latLng;
                    latLng = latLng5;
                    e = th;
                    C0955ck.m3888a(e, "VisibleRegionCreator", str);
                    return new VisibleRegion(readInt, latLng, latLng2, latLng4, latLng3, latLngBounds);
                }
            } catch (BadParcelableException e5) {
                latLng3 = null;
                latLng4 = null;
                latLng2 = null;
                th = e5;
                latLng = latLng5;
                e = th;
                C0955ck.m3888a(e, "VisibleRegionCreator", str);
                return new VisibleRegion(readInt, latLng, latLng2, latLng4, latLng3, latLngBounds);
            }
        } catch (BadParcelableException e6) {
            e = e6;
            latLng3 = null;
            latLng4 = null;
            latLng2 = null;
            latLng = null;
            C0955ck.m3888a(e, "VisibleRegionCreator", str);
            return new VisibleRegion(readInt, latLng, latLng2, latLng4, latLng3, latLngBounds);
        }
        return new VisibleRegion(readInt, latLng, latLng2, latLng4, latLng3, latLngBounds);
    }

    /* renamed from: a */
    public VisibleRegion[] newArray(int i) {
        return new VisibleRegion[i];
    }

    /* renamed from: a */
    static void m4607a(VisibleRegion visibleRegion, Parcel parcel, int i) {
        parcel.writeInt(visibleRegion.mo11693a());
        parcel.writeParcelable(visibleRegion.nearLeft, i);
        parcel.writeParcelable(visibleRegion.nearRight, i);
        parcel.writeParcelable(visibleRegion.farLeft, i);
        parcel.writeParcelable(visibleRegion.farRight, i);
        parcel.writeParcelable(visibleRegion.latLngBounds, i);
    }
}
