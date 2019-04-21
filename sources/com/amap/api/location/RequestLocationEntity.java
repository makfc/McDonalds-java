package com.amap.api.location;

/* renamed from: com.amap.api.location.i */
public class RequestLocationEntity {
    /* renamed from: a */
    long f952a;
    /* renamed from: b */
    public AMapLocationListener f953b;
    /* renamed from: c */
    Boolean f954c;

    public RequestLocationEntity(long j, float f, AMapLocationListener aMapLocationListener, String str, boolean z) {
        this.f952a = j;
        this.f953b = aMapLocationListener;
        this.f954c = Boolean.valueOf(z);
    }

    public int hashCode() {
        return (this.f953b == null ? 0 : this.f953b.hashCode()) + 31;
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
        RequestLocationEntity requestLocationEntity = (RequestLocationEntity) obj;
        if (this.f953b == null) {
            if (requestLocationEntity.f953b != null) {
                return false;
            }
            return true;
        } else if (this.f953b.equals(requestLocationEntity.f953b)) {
            return true;
        } else {
            return false;
        }
    }
}
