package com.amap.api.location;

import android.location.Location;
import com.amap.api.location.core.AMapLocException;

public class AMapLocation extends Location {
    /* renamed from: a */
    private String f800a;
    /* renamed from: b */
    private String f801b;
    /* renamed from: c */
    private String f802c;
    /* renamed from: d */
    private String f803d;
    /* renamed from: e */
    private String f804e;
    /* renamed from: f */
    private String f805f;
    /* renamed from: g */
    private String f806g;
    /* renamed from: h */
    private String f807h;
    /* renamed from: i */
    private String f808i;
    /* renamed from: j */
    private String f809j;
    /* renamed from: k */
    private String f810k;
    /* renamed from: l */
    private String f811l;
    /* renamed from: m */
    private AMapLocException f812m = new AMapLocException();

    public String getPoiName() {
        return this.f811l;
    }

    public void setPoiName(String str) {
        this.f811l = str;
    }

    public String getCountry() {
        return this.f809j;
    }

    public void setCountry(String str) {
        this.f809j = str;
    }

    public String getRoad() {
        return this.f810k;
    }

    public void setRoad(String str) {
        this.f810k = str;
    }

    public AMapLocException getAMapException() {
        return this.f812m;
    }

    public void setAMapException(AMapLocException aMapLocException) {
        this.f812m = aMapLocException;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo8269a(String str) {
        this.f807h = str;
    }

    public void setAddress(String str) {
        this.f808i = str;
    }

    public String getPoiId() {
        return this.f805f;
    }

    public void setPoiId(String str) {
        this.f805f = str;
    }

    public String getFloor() {
        return this.f806g;
    }

    public void setFloor(String str) {
        this.f806g = str;
    }

    public AMapLocation(String str) {
        super(str);
    }

    public AMapLocation(Location location) {
        super(location);
    }

    public String getProvince() {
        return this.f800a;
    }

    public void setProvince(String str) {
        this.f800a = str;
    }

    public String getCity() {
        return this.f801b;
    }

    public void setCity(String str) {
        this.f801b = str;
    }

    public String getDistrict() {
        return this.f802c;
    }

    public void setDistrict(String str) {
        this.f802c = str;
    }

    public String getCityCode() {
        return this.f803d;
    }

    public void setCityCode(String str) {
        this.f803d = str;
    }

    public String getAdCode() {
        return this.f804e;
    }

    public void setAdCode(String str) {
        this.f804e = str;
    }

    public String getAddress() {
        return this.f808i;
    }

    @Deprecated
    public String getStreet() {
        return this.f807h;
    }
}
