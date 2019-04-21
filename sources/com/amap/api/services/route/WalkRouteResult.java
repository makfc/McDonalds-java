package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.WalkRouteQuery;
import java.util.ArrayList;
import java.util.List;

public class WalkRouteResult extends RouteResult implements Parcelable {
    public static final Creator<WalkRouteResult> CREATOR = new C1192u();
    /* renamed from: a */
    private List<WalkPath> f4122a = new ArrayList();
    /* renamed from: b */
    private WalkRouteQuery f4123b;

    public List<WalkPath> getPaths() {
        return this.f4122a;
    }

    public void setPaths(List<WalkPath> list) {
        this.f4122a = list;
    }

    public WalkRouteQuery getWalkQuery() {
        return this.f4123b;
    }

    public void setWalkQuery(WalkRouteQuery walkRouteQuery) {
        this.f4123b = walkRouteQuery;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.f4122a);
        parcel.writeParcelable(this.f4123b, i);
    }

    public WalkRouteResult(Parcel parcel) {
        super(parcel);
        this.f4122a = parcel.createTypedArrayList(WalkPath.CREATOR);
        this.f4123b = (WalkRouteQuery) parcel.readParcelable(WalkRouteQuery.class.getClassLoader());
    }
}
