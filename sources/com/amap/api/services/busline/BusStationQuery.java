package com.amap.api.services.busline;

import com.amap.api.services.core.C1128d;

public class BusStationQuery {
    /* renamed from: a */
    private String f3561a;
    /* renamed from: b */
    private String f3562b;
    /* renamed from: c */
    private int f3563c = 10;
    /* renamed from: d */
    private int f3564d = 0;

    public BusStationQuery(String str, String str2) {
        this.f3561a = str;
        this.f3562b = str2;
        if (!m4646a()) {
            throw new IllegalArgumentException("Empty query");
        }
    }

    /* renamed from: a */
    private boolean m4646a() {
        return !C1128d.m4976a(this.f3561a);
    }

    public String getQueryString() {
        return this.f3561a;
    }

    public String getCity() {
        return this.f3562b;
    }

    public int getPageSize() {
        return this.f3563c;
    }

    public int getPageNumber() {
        return this.f3564d;
    }

    public void setQueryString(String str) {
        this.f3561a = str;
    }

    public void setCity(String str) {
        this.f3562b = str;
    }

    public void setPageSize(int i) {
        int i2 = 20;
        if (i <= 20) {
            i2 = i;
        }
        if (i2 <= 0) {
            i2 = 10;
        }
        this.f3563c = i2;
    }

    public void setPageNumber(int i) {
        this.f3564d = i;
    }

    /* Access modifiers changed, original: protected */
    public BusStationQuery clone() {
        BusStationQuery busStationQuery = new BusStationQuery(this.f3561a, this.f3562b);
        busStationQuery.setPageNumber(this.f3564d);
        busStationQuery.setPageSize(this.f3563c);
        return busStationQuery;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((this.f3562b == null ? 0 : this.f3562b.hashCode()) + 31) * 31) + this.f3564d) * 31) + this.f3563c) * 31;
        if (this.f3561a != null) {
            i = this.f3561a.hashCode();
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
        BusStationQuery busStationQuery = (BusStationQuery) obj;
        if (this.f3562b == null) {
            if (busStationQuery.f3562b != null) {
                return false;
            }
        } else if (!this.f3562b.equals(busStationQuery.f3562b)) {
            return false;
        }
        if (this.f3564d != busStationQuery.f3564d) {
            return false;
        }
        if (this.f3563c != busStationQuery.f3563c) {
            return false;
        }
        if (this.f3561a == null) {
            if (busStationQuery.f3561a != null) {
                return false;
            }
            return true;
        } else if (this.f3561a.equals(busStationQuery.f3561a)) {
            return true;
        } else {
            return false;
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean weakEquals(BusStationQuery busStationQuery) {
        if (this == busStationQuery) {
            return true;
        }
        if (busStationQuery == null) {
            return false;
        }
        if (this.f3562b == null) {
            if (busStationQuery.f3562b != null) {
                return false;
            }
        } else if (!this.f3562b.equals(busStationQuery.f3562b)) {
            return false;
        }
        if (this.f3563c != busStationQuery.f3563c) {
            return false;
        }
        if (this.f3561a == null) {
            if (busStationQuery.f3561a != null) {
                return false;
            }
            return true;
        } else if (this.f3561a.equals(busStationQuery.f3561a)) {
            return true;
        } else {
            return false;
        }
    }
}
