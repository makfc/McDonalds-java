package com.amap.api.services.geocoder;

import com.amap.api.services.core.LatLonPoint;

public class RegeocodeQuery {
    /* renamed from: a */
    private LatLonPoint f3884a;
    /* renamed from: b */
    private float f3885b;
    /* renamed from: c */
    private String f3886c = "autonavi";

    public RegeocodeQuery(LatLonPoint latLonPoint, float f, String str) {
        this.f3884a = latLonPoint;
        this.f3885b = f;
        setLatLonType(str);
    }

    public LatLonPoint getPoint() {
        return this.f3884a;
    }

    public void setPoint(LatLonPoint latLonPoint) {
        this.f3884a = latLonPoint;
    }

    public float getRadius() {
        return this.f3885b;
    }

    public void setRadius(float f) {
        this.f3885b = f;
    }

    public String getLatLonType() {
        return this.f3886c;
    }

    public void setLatLonType(String str) {
        if (str == null) {
            return;
        }
        if (str.equals("autonavi") || str.equals("gps")) {
            this.f3886c = str;
        }
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3886c == null ? 0 : this.f3886c.hashCode()) + 31) * 31;
        if (this.f3884a != null) {
            i = this.f3884a.hashCode();
        }
        return ((hashCode + i) * 31) + Float.floatToIntBits(this.f3885b);
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
        RegeocodeQuery regeocodeQuery = (RegeocodeQuery) obj;
        if (this.f3886c == null) {
            if (regeocodeQuery.f3886c != null) {
                return false;
            }
        } else if (!this.f3886c.equals(regeocodeQuery.f3886c)) {
            return false;
        }
        if (this.f3884a == null) {
            if (regeocodeQuery.f3884a != null) {
                return false;
            }
        } else if (!this.f3884a.equals(regeocodeQuery.f3884a)) {
            return false;
        }
        if (Float.floatToIntBits(this.f3885b) != Float.floatToIntBits(regeocodeQuery.f3885b)) {
            return false;
        }
        return true;
    }
}
