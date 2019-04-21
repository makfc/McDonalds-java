package com.amap.api.maps2d;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.amap.api.maps2d.model.CameraPosition;

/* renamed from: com.amap.api.maps2d.a */
class AMapOptionsCreator implements Creator<AMapOptions> {
    AMapOptionsCreator() {
    }

    /* renamed from: a */
    public AMapOptions createFromParcel(Parcel parcel) {
        AMapOptions aMapOptions = new AMapOptions();
        CameraPosition cameraPosition = (CameraPosition) parcel.readParcelable(CameraPosition.class.getClassLoader());
        aMapOptions.mapType(parcel.readInt());
        aMapOptions.camera(cameraPosition);
        boolean[] createBooleanArray = parcel.createBooleanArray();
        if (createBooleanArray != null && createBooleanArray.length >= 6) {
            aMapOptions.scrollGesturesEnabled(createBooleanArray[0]);
            aMapOptions.zoomGesturesEnabled(createBooleanArray[1]);
            aMapOptions.zoomControlsEnabled(createBooleanArray[2]);
            aMapOptions.zOrderOnTop(createBooleanArray[3]);
            aMapOptions.compassEnabled(createBooleanArray[4]);
            aMapOptions.scaleControlsEnabled(createBooleanArray[5]);
        }
        return aMapOptions;
    }

    /* renamed from: a */
    public AMapOptions[] newArray(int i) {
        return new AMapOptions[i];
    }
}
