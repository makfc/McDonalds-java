package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PoiItem implements Parcelable {
    public static final Creator<PoiItem> CREATOR = new C1132p();
    /* renamed from: a */
    private String f3584a;
    /* renamed from: b */
    private String f3585b;
    /* renamed from: c */
    private String f3586c;
    /* renamed from: d */
    private String f3587d;
    /* renamed from: e */
    private String f3588e = "";
    /* renamed from: f */
    private int f3589f = -1;
    /* renamed from: g */
    private LatLonPoint f3590g;
    /* renamed from: h */
    private LatLonPoint f3591h;
    /* renamed from: i */
    private String f3592i;
    /* renamed from: j */
    private String f3593j;
    /* renamed from: k */
    private String f3594k;
    /* renamed from: l */
    private boolean f3595l;
    /* renamed from: m */
    private boolean f3596m;
    protected final LatLonPoint mPoint;
    protected final String mSnippet;
    protected final String mTitle;
    /* renamed from: n */
    private String f3597n;
    /* renamed from: o */
    private String f3598o;
    /* renamed from: p */
    private String f3599p;
    /* renamed from: q */
    private String f3600q;
    /* renamed from: r */
    private boolean f3601r;
    /* renamed from: s */
    private String f3602s;

    public PoiItem(String str, LatLonPoint latLonPoint, String str2, String str3) {
        this.f3584a = str;
        this.mPoint = latLonPoint;
        this.mTitle = str2;
        this.mSnippet = str3;
    }

    public String getAdName() {
        return this.f3600q;
    }

    public void setAdName(String str) {
        this.f3600q = str;
    }

    public String getCityName() {
        return this.f3599p;
    }

    public void setCityName(String str) {
        this.f3599p = str;
    }

    public String getProvinceName() {
        return this.f3598o;
    }

    public void setProvinceName(String str) {
        this.f3598o = str;
    }

    public String getTypeDes() {
        return this.f3588e;
    }

    public void setTypeDes(String str) {
        this.f3588e = str;
    }

    public String getTel() {
        return this.f3585b;
    }

    public void setTel(String str) {
        this.f3585b = str;
    }

    public String getAdCode() {
        return this.f3586c;
    }

    public void setAdCode(String str) {
        this.f3586c = str;
    }

    public String getPoiId() {
        return this.f3584a;
    }

    public int getDistance() {
        return this.f3589f;
    }

    public void setDistance(int i) {
        this.f3589f = i;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSnippet() {
        return this.mSnippet;
    }

    public LatLonPoint getLatLonPoint() {
        return this.mPoint;
    }

    public String getCityCode() {
        return this.f3587d;
    }

    public void setCityCode(String str) {
        this.f3587d = str;
    }

    public LatLonPoint getEnter() {
        return this.f3590g;
    }

    public void setEnter(LatLonPoint latLonPoint) {
        this.f3590g = latLonPoint;
    }

    public LatLonPoint getExit() {
        return this.f3591h;
    }

    public void setExit(LatLonPoint latLonPoint) {
        this.f3591h = latLonPoint;
    }

    public String getWebsite() {
        return this.f3592i;
    }

    public void setWebsite(String str) {
        this.f3592i = str;
    }

    public String getPostcode() {
        return this.f3593j;
    }

    public void setPostcode(String str) {
        this.f3593j = str;
    }

    public String getEmail() {
        return this.f3594k;
    }

    public void setEmail(String str) {
        this.f3594k = str;
    }

    public boolean isGroupbuyInfo() {
        return this.f3595l;
    }

    public void setGroupbuyInfo(boolean z) {
        this.f3595l = z;
    }

    public boolean isDiscountInfo() {
        return this.f3596m;
    }

    public void setDiscountInfo(boolean z) {
        this.f3596m = z;
    }

    public String getDirection() {
        return this.f3597n;
    }

    public void setDirection(String str) {
        this.f3597n = str;
    }

    public void setIndoorMap(boolean z) {
        this.f3601r = z;
    }

    public boolean isIndoorMap() {
        return this.f3601r;
    }

    public void setProvinceCode(String str) {
        this.f3602s = str;
    }

    public String getProvinceCode() {
        return this.f3602s;
    }

    protected PoiItem(Parcel parcel) {
        this.f3584a = parcel.readString();
        this.f3586c = parcel.readString();
        this.f3585b = parcel.readString();
        this.f3588e = parcel.readString();
        this.f3589f = parcel.readInt();
        this.mPoint = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.mTitle = parcel.readString();
        this.mSnippet = parcel.readString();
        this.f3587d = parcel.readString();
        this.f3590g = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.f3591h = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.f3592i = parcel.readString();
        this.f3593j = parcel.readString();
        this.f3594k = parcel.readString();
        boolean[] zArr = new boolean[3];
        parcel.readBooleanArray(zArr);
        this.f3595l = zArr[0];
        this.f3596m = zArr[1];
        this.f3601r = zArr[2];
        this.f3597n = parcel.readString();
        this.f3598o = parcel.readString();
        this.f3599p = parcel.readString();
        this.f3600q = parcel.readString();
        this.f3602s = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3584a);
        parcel.writeString(this.f3586c);
        parcel.writeString(this.f3585b);
        parcel.writeString(this.f3588e);
        parcel.writeInt(this.f3589f);
        parcel.writeValue(this.mPoint);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSnippet);
        parcel.writeString(this.f3587d);
        parcel.writeValue(this.f3590g);
        parcel.writeValue(this.f3591h);
        parcel.writeString(this.f3592i);
        parcel.writeString(this.f3593j);
        parcel.writeString(this.f3594k);
        parcel.writeBooleanArray(new boolean[]{this.f3595l, this.f3596m, this.f3601r});
        parcel.writeString(this.f3597n);
        parcel.writeString(this.f3598o);
        parcel.writeString(this.f3599p);
        parcel.writeString(this.f3600q);
        parcel.writeString(this.f3602s);
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
        PoiItem poiItem = (PoiItem) obj;
        if (this.f3584a == null) {
            if (poiItem.f3584a != null) {
                return false;
            }
            return true;
        } else if (this.f3584a.equals(poiItem.f3584a)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (this.f3584a == null ? 0 : this.f3584a.hashCode()) + 31;
    }

    public String toString() {
        return this.mTitle;
    }
}
