package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.DriveRouteQuery;
import java.util.ArrayList;
import java.util.List;

public class DriveRouteResult extends RouteResult implements Parcelable {
    public static final Creator<DriveRouteResult> CREATOR = new C1178g();
    /* renamed from: a */
    private float f4070a;
    /* renamed from: b */
    private List<DrivePath> f4071b = new ArrayList();
    /* renamed from: c */
    private DriveRouteQuery f4072c;

    public float getTaxiCost() {
        return this.f4070a;
    }

    public void setTaxiCost(float f) {
        this.f4070a = f;
    }

    public List<DrivePath> getPaths() {
        return this.f4071b;
    }

    public void setPaths(List<DrivePath> list) {
        this.f4071b = list;
    }

    public DriveRouteQuery getDriveQuery() {
        return this.f4072c;
    }

    public void setDriveQuery(DriveRouteQuery driveRouteQuery) {
        this.f4072c = driveRouteQuery;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.f4070a);
        parcel.writeTypedList(this.f4071b);
        parcel.writeParcelable(this.f4072c, i);
    }

    public DriveRouteResult(Parcel parcel) {
        super(parcel);
        this.f4070a = parcel.readFloat();
        this.f4071b = parcel.createTypedArrayList(DrivePath.CREATOR);
        this.f4072c = (DriveRouteQuery) parcel.readParcelable(DriveRouteQuery.class.getClassLoader());
    }
}
