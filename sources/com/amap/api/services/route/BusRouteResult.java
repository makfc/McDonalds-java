package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.BusRouteQuery;
import java.util.ArrayList;
import java.util.List;

public class BusRouteResult extends RouteResult implements Parcelable {
    public static final Creator<BusRouteResult> CREATOR = new C1173b();
    /* renamed from: a */
    private float f4055a;
    /* renamed from: b */
    private List<BusPath> f4056b = new ArrayList();
    /* renamed from: c */
    private BusRouteQuery f4057c;

    public float getTaxiCost() {
        return this.f4055a;
    }

    public void setTaxiCost(float f) {
        this.f4055a = f;
    }

    public List<BusPath> getPaths() {
        return this.f4056b;
    }

    public void setPaths(List<BusPath> list) {
        this.f4056b = list;
    }

    public BusRouteQuery getBusQuery() {
        return this.f4057c;
    }

    public void setBusQuery(BusRouteQuery busRouteQuery) {
        this.f4057c = busRouteQuery;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.f4055a);
        parcel.writeTypedList(this.f4056b);
        parcel.writeParcelable(this.f4057c, i);
    }

    public BusRouteResult(Parcel parcel) {
        super(parcel);
        this.f4055a = parcel.readFloat();
        this.f4056b = parcel.createTypedArrayList(BusPath.CREATOR);
        this.f4057c = (BusRouteQuery) parcel.readParcelable(BusRouteQuery.class.getClassLoader());
    }
}
