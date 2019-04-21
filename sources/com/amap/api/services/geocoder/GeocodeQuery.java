package com.amap.api.services.geocoder;

public class GeocodeQuery {
    /* renamed from: a */
    private String f3859a;
    /* renamed from: b */
    private String f3860b;

    public GeocodeQuery(String str, String str2) {
        this.f3859a = str;
        this.f3860b = str2;
    }

    public String getLocationName() {
        return this.f3859a;
    }

    public void setLocationName(String str) {
        this.f3859a = str;
    }

    public String getCity() {
        return this.f3860b;
    }

    public void setCity(String str) {
        this.f3860b = str;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3860b == null ? 0 : this.f3860b.hashCode()) + 31) * 31;
        if (this.f3859a != null) {
            i = this.f3859a.hashCode();
        }
        return hashCode + i;
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
        GeocodeQuery geocodeQuery = (GeocodeQuery) obj;
        if (this.f3860b == null) {
            if (geocodeQuery.f3860b != null) {
                return false;
            }
        } else if (!this.f3860b.equals(geocodeQuery.f3860b)) {
            return false;
        }
        if (this.f3859a == null) {
            if (geocodeQuery.f3859a != null) {
                return false;
            }
            return true;
        } else if (this.f3859a.equals(geocodeQuery.f3859a)) {
            return true;
        } else {
            return false;
        }
    }
}
