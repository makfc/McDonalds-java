package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.road.Crossroad;
import com.amap.api.services.road.Road;
import java.util.ArrayList;
import java.util.List;

public final class RegeocodeAddress implements Parcelable {
    public static final Creator<RegeocodeAddress> CREATOR = new C1150d();
    /* renamed from: a */
    private String f3870a;
    /* renamed from: b */
    private String f3871b;
    /* renamed from: c */
    private String f3872c;
    /* renamed from: d */
    private String f3873d;
    /* renamed from: e */
    private String f3874e;
    /* renamed from: f */
    private String f3875f;
    /* renamed from: g */
    private String f3876g;
    /* renamed from: h */
    private StreetNumber f3877h;
    /* renamed from: i */
    private String f3878i;
    /* renamed from: j */
    private String f3879j;
    /* renamed from: k */
    private List<RegeocodeRoad> f3880k;
    /* renamed from: l */
    private List<Crossroad> f3881l;
    /* renamed from: m */
    private List<PoiItem> f3882m;
    /* renamed from: n */
    private List<BusinessArea> f3883n;

    /* synthetic */ RegeocodeAddress(Parcel parcel, C1150d c1150d) {
        this(parcel);
    }

    public RegeocodeAddress() {
        this.f3880k = new ArrayList();
        this.f3881l = new ArrayList();
        this.f3882m = new ArrayList();
        this.f3883n = new ArrayList();
    }

    public String getFormatAddress() {
        return this.f3870a;
    }

    public void setFormatAddress(String str) {
        this.f3870a = str;
    }

    public String getProvince() {
        return this.f3871b;
    }

    public void setProvince(String str) {
        this.f3871b = str;
    }

    public String getCity() {
        return this.f3872c;
    }

    public void setCity(String str) {
        this.f3872c = str;
    }

    public String getCityCode() {
        return this.f3878i;
    }

    public void setCityCode(String str) {
        this.f3878i = str;
    }

    public String getAdCode() {
        return this.f3879j;
    }

    public void setAdCode(String str) {
        this.f3879j = str;
    }

    public String getDistrict() {
        return this.f3873d;
    }

    public void setDistrict(String str) {
        this.f3873d = str;
    }

    public String getTownship() {
        return this.f3874e;
    }

    public void setTownship(String str) {
        this.f3874e = str;
    }

    public String getNeighborhood() {
        return this.f3875f;
    }

    public void setNeighborhood(String str) {
        this.f3875f = str;
    }

    public String getBuilding() {
        return this.f3876g;
    }

    public void setBuilding(String str) {
        this.f3876g = str;
    }

    public StreetNumber getStreetNumber() {
        return this.f3877h;
    }

    public void setStreetNumber(StreetNumber streetNumber) {
        this.f3877h = streetNumber;
    }

    public List<RegeocodeRoad> getRoads() {
        return this.f3880k;
    }

    public void setRoads(List<RegeocodeRoad> list) {
        this.f3880k = list;
    }

    public List<PoiItem> getPois() {
        return this.f3882m;
    }

    public void setPois(List<PoiItem> list) {
        this.f3882m = list;
    }

    public List<Crossroad> getCrossroads() {
        return this.f3881l;
    }

    public void setCrossroads(List<Crossroad> list) {
        this.f3881l = list;
    }

    public List<BusinessArea> getBusinessAreas() {
        return this.f3883n;
    }

    public void setBusinessAreas(List<BusinessArea> list) {
        this.f3883n = list;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3870a);
        parcel.writeString(this.f3871b);
        parcel.writeString(this.f3872c);
        parcel.writeString(this.f3873d);
        parcel.writeString(this.f3874e);
        parcel.writeString(this.f3875f);
        parcel.writeString(this.f3876g);
        parcel.writeValue(this.f3877h);
        parcel.writeList(this.f3880k);
        parcel.writeList(this.f3881l);
        parcel.writeList(this.f3882m);
        parcel.writeString(this.f3878i);
        parcel.writeString(this.f3879j);
        parcel.writeList(this.f3883n);
    }

    private RegeocodeAddress(Parcel parcel) {
        this.f3880k = new ArrayList();
        this.f3881l = new ArrayList();
        this.f3882m = new ArrayList();
        this.f3883n = new ArrayList();
        this.f3870a = parcel.readString();
        this.f3871b = parcel.readString();
        this.f3872c = parcel.readString();
        this.f3873d = parcel.readString();
        this.f3874e = parcel.readString();
        this.f3875f = parcel.readString();
        this.f3876g = parcel.readString();
        this.f3877h = (StreetNumber) parcel.readValue(StreetNumber.class.getClassLoader());
        this.f3880k = parcel.readArrayList(Road.class.getClassLoader());
        this.f3881l = parcel.readArrayList(Crossroad.class.getClassLoader());
        this.f3882m = parcel.readArrayList(PoiItem.class.getClassLoader());
        this.f3878i = parcel.readString();
        this.f3879j = parcel.readString();
        this.f3883n = parcel.readArrayList(BusinessArea.class.getClassLoader());
    }
}
