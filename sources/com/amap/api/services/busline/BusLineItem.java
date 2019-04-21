package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.C1128d;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusLineItem implements Parcelable {
    public static final Creator<BusLineItem> CREATOR = new C1071a();
    /* renamed from: a */
    private float f3521a;
    /* renamed from: b */
    private String f3522b;
    /* renamed from: c */
    private String f3523c;
    /* renamed from: d */
    private String f3524d;
    /* renamed from: e */
    private List<LatLonPoint> f3525e = new ArrayList();
    /* renamed from: f */
    private List<LatLonPoint> f3526f = new ArrayList();
    /* renamed from: g */
    private String f3527g;
    /* renamed from: h */
    private String f3528h;
    /* renamed from: i */
    private String f3529i;
    /* renamed from: j */
    private Date f3530j;
    /* renamed from: k */
    private Date f3531k;
    /* renamed from: l */
    private String f3532l;
    /* renamed from: m */
    private float f3533m;
    /* renamed from: n */
    private float f3534n;
    /* renamed from: o */
    private List<BusStationItem> f3535o = new ArrayList();

    public float getDistance() {
        return this.f3521a;
    }

    public void setDistance(float f) {
        this.f3521a = f;
    }

    public String getBusLineName() {
        return this.f3522b;
    }

    public void setBusLineName(String str) {
        this.f3522b = str;
    }

    public String getBusLineType() {
        return this.f3523c;
    }

    public void setBusLineType(String str) {
        this.f3523c = str;
    }

    public String getCityCode() {
        return this.f3524d;
    }

    public void setCityCode(String str) {
        this.f3524d = str;
    }

    public List<LatLonPoint> getDirectionsCoordinates() {
        return this.f3525e;
    }

    public void setDirectionsCoordinates(List<LatLonPoint> list) {
        this.f3525e = list;
    }

    public List<LatLonPoint> getBounds() {
        return this.f3526f;
    }

    public void setBounds(List<LatLonPoint> list) {
        this.f3526f = list;
    }

    public String getBusLineId() {
        return this.f3527g;
    }

    public void setBusLineId(String str) {
        this.f3527g = str;
    }

    public String getOriginatingStation() {
        return this.f3528h;
    }

    public void setOriginatingStation(String str) {
        this.f3528h = str;
    }

    public String getTerminalStation() {
        return this.f3529i;
    }

    public void setTerminalStation(String str) {
        this.f3529i = str;
    }

    public Date getFirstBusTime() {
        if (this.f3530j == null) {
            return null;
        }
        return (Date) this.f3530j.clone();
    }

    public void setFirstBusTime(Date date) {
        if (date == null) {
            this.f3530j = null;
        } else {
            this.f3530j = (Date) date.clone();
        }
    }

    public Date getLastBusTime() {
        if (this.f3531k == null) {
            return null;
        }
        return (Date) this.f3531k.clone();
    }

    public void setLastBusTime(Date date) {
        if (date == null) {
            this.f3531k = null;
        } else {
            this.f3531k = (Date) date.clone();
        }
    }

    public String getBusCompany() {
        return this.f3532l;
    }

    public void setBusCompany(String str) {
        this.f3532l = str;
    }

    public float getBasicPrice() {
        return this.f3533m;
    }

    public void setBasicPrice(float f) {
        this.f3533m = f;
    }

    public float getTotalPrice() {
        return this.f3534n;
    }

    public void setTotalPrice(float f) {
        this.f3534n = f;
    }

    public List<BusStationItem> getBusStations() {
        return this.f3535o;
    }

    public void setBusStations(List<BusStationItem> list) {
        this.f3535o = list;
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
        BusLineItem busLineItem = (BusLineItem) obj;
        if (this.f3527g == null) {
            if (busLineItem.f3527g != null) {
                return false;
            }
            return true;
        } else if (this.f3527g.equals(busLineItem.f3527g)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (this.f3527g == null ? 0 : this.f3527g.hashCode()) + 31;
    }

    public String toString() {
        return this.f3522b + " " + C1128d.m4973a(this.f3530j) + "-" + C1128d.m4973a(this.f3531k);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f3521a);
        parcel.writeString(this.f3522b);
        parcel.writeString(this.f3523c);
        parcel.writeString(this.f3524d);
        parcel.writeList(this.f3525e);
        parcel.writeList(this.f3526f);
        parcel.writeString(this.f3527g);
        parcel.writeString(this.f3528h);
        parcel.writeString(this.f3529i);
        parcel.writeString(C1128d.m4973a(this.f3530j));
        parcel.writeString(C1128d.m4973a(this.f3531k));
        parcel.writeString(this.f3532l);
        parcel.writeFloat(this.f3533m);
        parcel.writeFloat(this.f3534n);
        parcel.writeList(this.f3535o);
    }

    public BusLineItem(Parcel parcel) {
        this.f3521a = parcel.readFloat();
        this.f3522b = parcel.readString();
        this.f3523c = parcel.readString();
        this.f3524d = parcel.readString();
        this.f3525e = parcel.readArrayList(LatLonPoint.class.getClassLoader());
        this.f3526f = parcel.readArrayList(LatLonPoint.class.getClassLoader());
        this.f3527g = parcel.readString();
        this.f3528h = parcel.readString();
        this.f3529i = parcel.readString();
        this.f3530j = C1128d.m4980e(parcel.readString());
        this.f3531k = C1128d.m4980e(parcel.readString());
        this.f3532l = parcel.readString();
        this.f3533m = parcel.readFloat();
        this.f3534n = parcel.readFloat();
        this.f3535o = parcel.readArrayList(BusStationItem.class.getClassLoader());
    }
}
