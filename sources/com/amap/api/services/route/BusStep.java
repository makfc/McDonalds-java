package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class BusStep implements Parcelable {
    public static final Creator<BusStep> CREATOR = new C1174c();
    /* renamed from: a */
    private RouteBusWalkItem f4058a;
    /* renamed from: b */
    private List<RouteBusLineItem> f4059b = new ArrayList();
    /* renamed from: c */
    private Doorway f4060c;
    /* renamed from: d */
    private Doorway f4061d;

    public RouteBusWalkItem getWalk() {
        return this.f4058a;
    }

    public void setWalk(RouteBusWalkItem routeBusWalkItem) {
        this.f4058a = routeBusWalkItem;
    }

    public RouteBusLineItem getBusLine() {
        if (this.f4059b == null || this.f4059b.size() == 0) {
            return null;
        }
        return (RouteBusLineItem) this.f4059b.get(0);
    }

    public void setBusLine(RouteBusLineItem routeBusLineItem) {
        if (this.f4059b != null) {
            if (this.f4059b.size() == 0) {
                this.f4059b.add(routeBusLineItem);
            }
            this.f4059b.set(0, routeBusLineItem);
        }
    }

    public void setBusLines(List<RouteBusLineItem> list) {
        this.f4059b = list;
    }

    public Doorway getEntrance() {
        return this.f4060c;
    }

    public void setEntrance(Doorway doorway) {
        this.f4060c = doorway;
    }

    public Doorway getExit() {
        return this.f4061d;
    }

    public void setExit(Doorway doorway) {
        this.f4061d = doorway;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f4058a, i);
        parcel.writeTypedList(this.f4059b);
        parcel.writeParcelable(this.f4060c, i);
        parcel.writeParcelable(this.f4061d, i);
    }

    public BusStep(Parcel parcel) {
        this.f4058a = (RouteBusWalkItem) parcel.readParcelable(RouteBusWalkItem.class.getClassLoader());
        this.f4059b = parcel.createTypedArrayList(RouteBusLineItem.CREATOR);
        this.f4060c = (Doorway) parcel.readParcelable(Doorway.class.getClassLoader());
        this.f4061d = (Doorway) parcel.readParcelable(Doorway.class.getClassLoader());
    }
}
