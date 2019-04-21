package com.amap.api.services.busline;

import com.amap.api.services.core.C1128d;

public class BusLineQuery {
    /* renamed from: a */
    private String f3537a;
    /* renamed from: b */
    private String f3538b;
    /* renamed from: c */
    private int f3539c = 10;
    /* renamed from: d */
    private int f3540d = 0;
    /* renamed from: e */
    private SearchType f3541e;

    public enum SearchType {
        BY_LINE_ID,
        BY_LINE_NAME
    }

    public BusLineQuery(String str, SearchType searchType, String str2) {
        this.f3537a = str;
        this.f3541e = searchType;
        this.f3538b = str2;
        if (!m4638a()) {
            throw new IllegalArgumentException("Empty query");
        }
    }

    /* renamed from: a */
    private boolean m4638a() {
        return !C1128d.m4976a(this.f3537a);
    }

    public SearchType getCategory() {
        return this.f3541e;
    }

    public String getQueryString() {
        return this.f3537a;
    }

    public void setQueryString(String str) {
        this.f3537a = str;
    }

    public String getCity() {
        return this.f3538b;
    }

    public void setCity(String str) {
        this.f3538b = str;
    }

    public int getPageSize() {
        return this.f3539c;
    }

    public void setPageSize(int i) {
        this.f3539c = i;
    }

    public int getPageNumber() {
        return this.f3540d;
    }

    public void setPageNumber(int i) {
        this.f3540d = i;
    }

    public void setCategory(SearchType searchType) {
        this.f3541e = searchType;
    }

    /* Access modifiers changed, original: protected */
    public BusLineQuery clone() {
        BusLineQuery busLineQuery = new BusLineQuery(this.f3537a, this.f3541e, this.f3538b);
        busLineQuery.setPageNumber(this.f3540d);
        busLineQuery.setPageSize(this.f3539c);
        return busLineQuery;
    }

    /* Access modifiers changed, original: protected */
    public boolean weakEquals(BusLineQuery busLineQuery) {
        if (busLineQuery.getQueryString().equals(this.f3537a) && busLineQuery.getCity().equals(this.f3538b) && busLineQuery.getPageSize() == this.f3539c && busLineQuery.getCategory().compareTo(this.f3541e) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((this.f3538b == null ? 0 : this.f3538b.hashCode()) + (((this.f3541e == null ? 0 : this.f3541e.hashCode()) + 31) * 31)) * 31) + this.f3540d) * 31) + this.f3539c) * 31;
        if (this.f3537a != null) {
            i = this.f3537a.hashCode();
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
        BusLineQuery busLineQuery = (BusLineQuery) obj;
        if (this.f3541e != busLineQuery.f3541e) {
            return false;
        }
        if (this.f3538b == null) {
            if (busLineQuery.f3538b != null) {
                return false;
            }
        } else if (!this.f3538b.equals(busLineQuery.f3538b)) {
            return false;
        }
        if (this.f3540d != busLineQuery.f3540d) {
            return false;
        }
        if (this.f3539c != busLineQuery.f3539c) {
            return false;
        }
        if (this.f3537a == null) {
            if (busLineQuery.f3537a != null) {
                return false;
            }
            return true;
        } else if (this.f3537a.equals(busLineQuery.f3537a)) {
            return true;
        } else {
            return false;
        }
    }
}
