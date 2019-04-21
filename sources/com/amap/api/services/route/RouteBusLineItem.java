package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class RouteBusLineItem extends BusLineItem implements Parcelable {
    public static final Creator<RouteBusLineItem> CREATOR = new C1181j();
    /* renamed from: a */
    private BusStationItem f4085a;
    /* renamed from: b */
    private BusStationItem f4086b;
    /* renamed from: c */
    private List<LatLonPoint> f4087c = new ArrayList();
    /* renamed from: d */
    private int f4088d;
    /* renamed from: e */
    private List<BusStationItem> f4089e = new ArrayList();
    /* renamed from: f */
    private float f4090f;

    public BusStationItem getDepartureBusStation() {
        return this.f4085a;
    }

    public void setDepartureBusStation(BusStationItem busStationItem) {
        this.f4085a = busStationItem;
    }

    public BusStationItem getArrivalBusStation() {
        return this.f4086b;
    }

    public void setArrivalBusStation(BusStationItem busStationItem) {
        this.f4086b = busStationItem;
    }

    public List<LatLonPoint> getPolyline() {
        return this.f4087c;
    }

    public void setPolyline(List<LatLonPoint> list) {
        this.f4087c = list;
    }

    public int getPassStationNum() {
        return this.f4088d;
    }

    public void setPassStationNum(int i) {
        this.f4088d = i;
    }

    public List<BusStationItem> getPassStations() {
        return this.f4089e;
    }

    public void setPassStations(List<BusStationItem> list) {
        this.f4089e = list;
    }

    public float getDuration() {
        return this.f4090f;
    }

    public void setDuration(float f) {
        this.f4090f = f;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f4085a, i);
        parcel.writeParcelable(this.f4086b, i);
        parcel.writeTypedList(this.f4087c);
        parcel.writeInt(this.f4088d);
        parcel.writeTypedList(this.f4089e);
        parcel.writeFloat(this.f4090f);
    }

    public RouteBusLineItem(Parcel parcel) {
        super(parcel);
        this.f4085a = (BusStationItem) parcel.readParcelable(BusStationItem.class.getClassLoader());
        this.f4086b = (BusStationItem) parcel.readParcelable(BusStationItem.class.getClassLoader());
        this.f4087c = parcel.createTypedArrayList(LatLonPoint.CREATOR);
        this.f4088d = parcel.readInt();
        this.f4089e = parcel.createTypedArrayList(BusStationItem.CREATOR);
        this.f4090f = parcel.readFloat();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f4086b == null ? 0 : this.f4086b.hashCode()) + (super.hashCode() * 31)) * 31;
        if (this.f4085a != null) {
            i = this.f4085a.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RouteBusLineItem routeBusLineItem = (RouteBusLineItem) obj;
        if (this.f4086b == null) {
            if (routeBusLineItem.f4086b != null) {
                return false;
            }
        } else if (!this.f4086b.equals(routeBusLineItem.f4086b)) {
            return false;
        }
        if (this.f4085a == null) {
            if (routeBusLineItem.f4085a != null) {
                return false;
            }
            return true;
        } else if (this.f4085a.equals(routeBusLineItem.f4085a)) {
            return true;
        } else {
            return false;
        }
    }
}
