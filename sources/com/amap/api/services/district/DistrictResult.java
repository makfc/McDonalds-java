package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.AMapException;
import java.util.ArrayList;

public final class DistrictResult implements Parcelable {
    public Creator<DistrictResult> CREATOR = new C1142b(this);
    /* renamed from: a */
    private DistrictSearchQuery f3828a;
    /* renamed from: b */
    private ArrayList<DistrictItem> f3829b = new ArrayList();
    /* renamed from: c */
    private int f3830c;
    /* renamed from: d */
    private AMapException f3831d;

    public DistrictResult(DistrictSearchQuery districtSearchQuery, ArrayList<DistrictItem> arrayList) {
        this.f3828a = districtSearchQuery;
        this.f3829b = arrayList;
    }

    public ArrayList<DistrictItem> getDistrict() {
        return this.f3829b;
    }

    public void setDistrict(ArrayList<DistrictItem> arrayList) {
        this.f3829b = arrayList;
    }

    public DistrictSearchQuery getQuery() {
        return this.f3828a;
    }

    public void setQuery(DistrictSearchQuery districtSearchQuery) {
        this.f3828a = districtSearchQuery;
    }

    public int getPageCount() {
        return this.f3830c;
    }

    public void setPageCount(int i) {
        this.f3830c = i;
    }

    public AMapException getAMapException() {
        return this.f3831d;
    }

    public void setAMapException(AMapException aMapException) {
        this.f3831d = aMapException;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3828a, i);
        parcel.writeTypedList(this.f3829b);
    }

    protected DistrictResult(Parcel parcel) {
        this.f3828a = (DistrictSearchQuery) parcel.readParcelable(DistrictSearchQuery.class.getClassLoader());
        this.f3829b = parcel.createTypedArrayList(DistrictItem.CREATOR);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3828a == null ? 0 : this.f3828a.hashCode()) + 31) * 31;
        if (this.f3829b != null) {
            i = this.f3829b.hashCode();
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
        DistrictResult districtResult = (DistrictResult) obj;
        if (this.f3828a == null) {
            if (districtResult.f3828a != null) {
                return false;
            }
        } else if (!this.f3828a.equals(districtResult.f3828a)) {
            return false;
        }
        if (this.f3829b == null) {
            if (districtResult.f3829b != null) {
                return false;
            }
            return true;
        } else if (this.f3829b.equals(districtResult.f3829b)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "DistrictResult [mDisQuery=" + this.f3828a + ", mDistricts=" + this.f3829b + "]";
    }
}
