package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.C1128d;

public class DistrictSearchQuery implements Parcelable, Cloneable {
    public static final Creator<DistrictSearchQuery> CREATOR = new C1144d();
    public static final String KEYWORDS_BUSINESS = "biz_area";
    public static final String KEYWORDS_CITY = "city";
    public static final String KEYWORDS_COUNTRY = "country";
    public static final String KEYWORDS_DISTRICT = "district";
    public static final String KEYWORDS_PROVINCE = "province";
    /* renamed from: a */
    private int f3840a;
    /* renamed from: b */
    private int f3841b;
    /* renamed from: c */
    private String f3842c;
    /* renamed from: d */
    private String f3843d;
    /* renamed from: e */
    private boolean f3844e;

    public DistrictSearchQuery() {
        this.f3840a = 0;
        this.f3841b = 20;
        this.f3844e = true;
    }

    public DistrictSearchQuery(String str, String str2, int i) {
        this.f3840a = 0;
        this.f3841b = 20;
        this.f3844e = true;
        this.f3842c = str;
        this.f3843d = str2;
        this.f3840a = i;
    }

    public DistrictSearchQuery(String str, String str2, int i, boolean z, int i2) {
        this(str, str2, i);
        this.f3844e = z;
        this.f3841b = i2;
    }

    public int getPageNum() {
        return this.f3840a;
    }

    public void setPageNum(int i) {
        this.f3840a = i;
    }

    public int getPageSize() {
        return this.f3841b;
    }

    public void setPageSize(int i) {
        this.f3841b = i;
    }

    public String getKeywords() {
        return this.f3842c;
    }

    public void setKeywords(String str) {
        this.f3842c = str;
    }

    public String getKeywordsLevel() {
        return this.f3843d;
    }

    public void setKeywordsLevel(String str) {
        this.f3843d = str;
    }

    public boolean isShowChild() {
        return this.f3844e;
    }

    public void setShowChild(boolean z) {
        this.f3844e = z;
    }

    public boolean checkLevels() {
        if (this.f3843d == null) {
            return false;
        }
        if (this.f3843d.trim().equals(KEYWORDS_COUNTRY) || this.f3843d.trim().equals(KEYWORDS_PROVINCE) || this.f3843d.trim().equals(KEYWORDS_CITY) || this.f3843d.trim().equals(KEYWORDS_DISTRICT) || this.f3843d.trim().equals(KEYWORDS_BUSINESS)) {
            return true;
        }
        return false;
    }

    public boolean checkKeyWords() {
        if (this.f3842c == null || this.f3842c.trim().equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3842c == null ? 0 : this.f3842c.hashCode()) + 31) * 31;
        if (this.f3843d != null) {
            i = this.f3843d.hashCode();
        }
        return (this.f3844e ? 1231 : 1237) + ((((((hashCode + i) * 31) + this.f3840a) * 31) + this.f3841b) * 31);
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
        DistrictSearchQuery districtSearchQuery = (DistrictSearchQuery) obj;
        if (this.f3842c == null) {
            if (districtSearchQuery.f3842c != null) {
                return false;
            }
        } else if (!this.f3842c.equals(districtSearchQuery.f3842c)) {
            return false;
        }
        if (this.f3843d == null) {
            if (districtSearchQuery.f3843d != null) {
                return false;
            }
        } else if (!this.f3843d.equals(districtSearchQuery.f3843d)) {
            return false;
        }
        if (this.f3840a != districtSearchQuery.f3840a) {
            return false;
        }
        if (this.f3841b != districtSearchQuery.f3841b) {
            return false;
        }
        if (this.f3844e != districtSearchQuery.f3844e) {
            return false;
        }
        return true;
    }

    /* Access modifiers changed, original: protected */
    public boolean weakEquals(DistrictSearchQuery districtSearchQuery) {
        if (this == districtSearchQuery) {
            return true;
        }
        if (districtSearchQuery == null) {
            return false;
        }
        if (this.f3842c == null) {
            if (districtSearchQuery.f3842c != null) {
                return false;
            }
        } else if (!this.f3842c.equals(districtSearchQuery.f3842c)) {
            return false;
        }
        if (this.f3843d == null) {
            if (districtSearchQuery.f3843d != null) {
                return false;
            }
        } else if (!this.f3843d.equals(districtSearchQuery.f3843d)) {
            return false;
        }
        if (this.f3841b != districtSearchQuery.f3841b) {
            return false;
        }
        if (this.f3844e != districtSearchQuery.f3844e) {
            return false;
        }
        return true;
    }

    public DistrictSearchQuery clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            C1128d.m4975a(e, "DistrictSearchQuery", "clone");
        }
        return new DistrictSearchQuery(this.f3842c, this.f3843d, this.f3840a, this.f3844e, this.f3841b);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3842c);
        parcel.writeString(this.f3843d);
        parcel.writeInt(this.f3840a);
        parcel.writeInt(this.f3841b);
        parcel.writeByte((byte) (this.f3844e ? 1 : 0));
    }
}
