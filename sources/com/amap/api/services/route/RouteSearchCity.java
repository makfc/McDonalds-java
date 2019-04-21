package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class RouteSearchCity extends SearchCity implements Parcelable {
    public static final Creator<RouteSearchCity> CREATOR = new C1189r();
    /* renamed from: a */
    List<District> f4121a = new ArrayList();

    public List<District> getDistricts() {
        return this.f4121a;
    }

    public void setDistricts(List<District> list) {
        this.f4121a = list;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.f4121a);
    }

    public RouteSearchCity(Parcel parcel) {
        super(parcel);
        this.f4121a = parcel.createTypedArrayList(District.CREATOR);
    }
}
