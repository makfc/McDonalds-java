package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class RouteResult implements Parcelable {
    public static final Creator<RouteResult> CREATOR = new C1183l();
    /* renamed from: a */
    private LatLonPoint f4053a;
    /* renamed from: b */
    private LatLonPoint f4054b;

    public LatLonPoint getStartPos() {
        return this.f4053a;
    }

    public void setStartPos(LatLonPoint latLonPoint) {
        this.f4053a = latLonPoint;
    }

    public LatLonPoint getTargetPos() {
        return this.f4054b;
    }

    public void setTargetPos(LatLonPoint latLonPoint) {
        this.f4054b = latLonPoint;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f4053a, i);
        parcel.writeParcelable(this.f4054b, i);
    }

    public RouteResult(Parcel parcel) {
        this.f4053a = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.f4054b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
    }
}
