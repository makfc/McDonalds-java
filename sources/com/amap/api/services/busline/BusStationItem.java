package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class BusStationItem implements Parcelable {
    public static final Creator<BusStationItem> CREATOR = new C1073c();
    /* renamed from: a */
    private String f3555a;
    /* renamed from: b */
    private String f3556b;
    /* renamed from: c */
    private LatLonPoint f3557c;
    /* renamed from: d */
    private String f3558d;
    /* renamed from: e */
    private String f3559e;
    /* renamed from: f */
    private List<BusLineItem> f3560f;

    /* synthetic */ BusStationItem(Parcel parcel, C1073c c1073c) {
        this(parcel);
    }

    public BusStationItem() {
        this.f3560f = new ArrayList();
    }

    public String getBusStationId() {
        return this.f3555a;
    }

    public void setBusStationId(String str) {
        this.f3555a = str;
    }

    public String getBusStationName() {
        return this.f3556b;
    }

    public void setBusStationName(String str) {
        this.f3556b = str;
    }

    public LatLonPoint getLatLonPoint() {
        return this.f3557c;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.f3557c = latLonPoint;
    }

    public String getCityCode() {
        return this.f3558d;
    }

    public void setCityCode(String str) {
        this.f3558d = str;
    }

    public String getAdCode() {
        return this.f3559e;
    }

    public void setAdCode(String str) {
        this.f3559e = str;
    }

    public List<BusLineItem> getBusLineItems() {
        return this.f3560f;
    }

    public void setBusLineItems(List<BusLineItem> list) {
        this.f3560f = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BusStationItem busStationItem = (BusStationItem) obj;
        if (this.f3555a == null) {
            if (busStationItem.f3555a != null) {
                return false;
            }
            return true;
        } else if (this.f3555a.equals(busStationItem.f3555a)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (this.f3555a == null ? 0 : this.f3555a.hashCode()) + 31;
    }

    public String toString() {
        return "BusStationName: " + this.f3556b + " LatLonPoint: " + this.f3557c.toString() + " BusLines: " + m4645a(this.f3560f) + " CityCode: " + this.f3558d + " AdCode: " + this.f3559e;
    }

    /* renamed from: a */
    private String m4645a(List<BusLineItem> list) {
        StringBuffer stringBuffer = new StringBuffer();
        if (list != null) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= list.size()) {
                    break;
                }
                stringBuffer.append(((BusLineItem) list.get(i2)).getBusLineName());
                if (i2 < list.size() - 1) {
                    stringBuffer.append("|");
                }
                i = i2 + 1;
            }
        }
        return stringBuffer.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3556b);
        parcel.writeString(this.f3555a);
        parcel.writeValue(this.f3557c);
        parcel.writeString(this.f3558d);
        parcel.writeString(this.f3559e);
        parcel.writeList(this.f3560f);
    }

    private BusStationItem(Parcel parcel) {
        this.f3560f = new ArrayList();
        this.f3556b = parcel.readString();
        this.f3555a = parcel.readString();
        this.f3557c = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.f3558d = parcel.readString();
        this.f3559e = parcel.readString();
        this.f3560f = parcel.readArrayList(BusLineItem.class.getClassLoader());
    }
}
