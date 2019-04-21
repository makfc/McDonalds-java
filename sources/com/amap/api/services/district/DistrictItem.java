package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public final class DistrictItem implements Parcelable {
    public static final Creator<DistrictItem> CREATOR = new C1141a();
    /* renamed from: a */
    private String f3822a;
    /* renamed from: b */
    private String f3823b;
    /* renamed from: c */
    private String f3824c;
    /* renamed from: d */
    private LatLonPoint f3825d;
    /* renamed from: e */
    private String f3826e;
    /* renamed from: f */
    private List<DistrictItem> f3827f = new ArrayList();

    public DistrictItem(String str, String str2, String str3, LatLonPoint latLonPoint, String str4) {
        this.f3824c = str;
        this.f3822a = str2;
        this.f3823b = str3;
        this.f3825d = latLonPoint;
        this.f3826e = str4;
    }

    public String getCitycode() {
        return this.f3822a;
    }

    public void setCitycode(String str) {
        this.f3822a = str;
    }

    public String getAdcode() {
        return this.f3823b;
    }

    public void setAdcode(String str) {
        this.f3823b = str;
    }

    public String getName() {
        return this.f3824c;
    }

    public void setName(String str) {
        this.f3824c = str;
    }

    public LatLonPoint getCenter() {
        return this.f3825d;
    }

    public void setCenter(LatLonPoint latLonPoint) {
        this.f3825d = latLonPoint;
    }

    public String getLevel() {
        return this.f3826e;
    }

    public void setLevel(String str) {
        this.f3826e = str;
    }

    public List<DistrictItem> getSubDistrict() {
        return this.f3827f;
    }

    public void setSubDistrict(ArrayList<DistrictItem> arrayList) {
        this.f3827f = arrayList;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3822a);
        parcel.writeString(this.f3823b);
        parcel.writeString(this.f3824c);
        parcel.writeParcelable(this.f3825d, i);
        parcel.writeString(this.f3826e);
        parcel.writeTypedList(this.f3827f);
    }

    public DistrictItem(Parcel parcel) {
        this.f3822a = parcel.readString();
        this.f3823b = parcel.readString();
        this.f3824c = parcel.readString();
        this.f3825d = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.f3826e = parcel.readString();
        this.f3827f = parcel.createTypedArrayList(CREATOR);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3826e == null ? 0 : this.f3826e.hashCode()) + (((this.f3827f == null ? 0 : this.f3827f.hashCode()) + (((this.f3822a == null ? 0 : this.f3822a.hashCode()) + (((this.f3825d == null ? 0 : this.f3825d.hashCode()) + (((this.f3823b == null ? 0 : this.f3823b.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f3824c != null) {
            i = this.f3824c.hashCode();
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
        DistrictItem districtItem = (DistrictItem) obj;
        if (this.f3823b == null) {
            if (districtItem.f3823b != null) {
                return false;
            }
        } else if (!this.f3823b.equals(districtItem.f3823b)) {
            return false;
        }
        if (this.f3825d == null) {
            if (districtItem.f3825d != null) {
                return false;
            }
        } else if (!this.f3825d.equals(districtItem.f3825d)) {
            return false;
        }
        if (this.f3822a == null) {
            if (districtItem.f3822a != null) {
                return false;
            }
        } else if (!this.f3822a.equals(districtItem.f3822a)) {
            return false;
        }
        if (this.f3827f == null) {
            if (districtItem.f3827f != null) {
                return false;
            }
        } else if (!this.f3827f.equals(districtItem.f3827f)) {
            return false;
        }
        if (this.f3826e == null) {
            if (districtItem.f3826e != null) {
                return false;
            }
        } else if (!this.f3826e.equals(districtItem.f3826e)) {
            return false;
        }
        if (this.f3824c == null) {
            if (districtItem.f3824c != null) {
                return false;
            }
            return true;
        } else if (this.f3824c.equals(districtItem.f3824c)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "DistrictItem [mCitycode=" + this.f3822a + ", mAdcode=" + this.f3823b + ", mName=" + this.f3824c + ", mCenter=" + this.f3825d + ", mLevel=" + this.f3826e + ", mDistricts=" + this.f3827f + "]";
    }
}
