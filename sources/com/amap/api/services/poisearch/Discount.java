package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.C1128d;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Discount implements Parcelable {
    public static final Creator<Discount> CREATOR = new C1160c();
    /* renamed from: a */
    private String f3938a;
    /* renamed from: b */
    private String f3939b;
    /* renamed from: c */
    private Date f3940c;
    /* renamed from: d */
    private Date f3941d;
    /* renamed from: e */
    private int f3942e;
    /* renamed from: f */
    private List<Photo> f3943f = new ArrayList();
    /* renamed from: g */
    private String f3944g;
    /* renamed from: h */
    private String f3945h;

    public String getTitle() {
        return this.f3938a;
    }

    public void setTitle(String str) {
        this.f3938a = str;
    }

    public String getDetail() {
        return this.f3939b;
    }

    public void setDetail(String str) {
        this.f3939b = str;
    }

    public Date getStartTime() {
        if (this.f3940c == null) {
            return null;
        }
        return (Date) this.f3940c.clone();
    }

    public void setStartTime(Date date) {
        if (date == null) {
            this.f3940c = null;
        } else {
            this.f3940c = (Date) date.clone();
        }
    }

    public Date getEndTime() {
        if (this.f3941d == null) {
            return null;
        }
        return (Date) this.f3941d.clone();
    }

    public void setEndTime(Date date) {
        if (date == null) {
            this.f3941d = null;
        } else {
            this.f3941d = (Date) date.clone();
        }
    }

    public int getSoldCount() {
        return this.f3942e;
    }

    public void setSoldCount(int i) {
        this.f3942e = i;
    }

    public List<Photo> getPhotos() {
        return this.f3943f;
    }

    public void addPhotos(Photo photo) {
        this.f3943f.add(photo);
    }

    public void initPhotos(List<Photo> list) {
        if (list != null && list.size() != 0) {
            this.f3943f.clear();
            for (Photo add : list) {
                this.f3943f.add(add);
            }
        }
    }

    public String getUrl() {
        return this.f3944g;
    }

    public void setUrl(String str) {
        this.f3944g = str;
    }

    public String getProvider() {
        return this.f3945h;
    }

    public void setProvider(String str) {
        this.f3945h = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3938a);
        parcel.writeString(this.f3939b);
        parcel.writeString(C1128d.m4973a(this.f3940c));
        parcel.writeString(C1128d.m4973a(this.f3941d));
        parcel.writeInt(this.f3942e);
        parcel.writeTypedList(this.f3943f);
        parcel.writeString(this.f3944g);
        parcel.writeString(this.f3945h);
    }

    public Discount(Parcel parcel) {
        this.f3938a = parcel.readString();
        this.f3939b = parcel.readString();
        this.f3940c = C1128d.m4980e(parcel.readString());
        this.f3941d = C1128d.m4980e(parcel.readString());
        this.f3942e = parcel.readInt();
        this.f3943f = parcel.createTypedArrayList(Photo.CREATOR);
        this.f3944g = parcel.readString();
        this.f3945h = parcel.readString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3938a == null ? 0 : this.f3938a.hashCode()) + (((this.f3940c == null ? 0 : this.f3940c.hashCode()) + (((((this.f3945h == null ? 0 : this.f3945h.hashCode()) + (((this.f3943f == null ? 0 : this.f3943f.hashCode()) + (((this.f3941d == null ? 0 : this.f3941d.hashCode()) + (((this.f3939b == null ? 0 : this.f3939b.hashCode()) + 31) * 31)) * 31)) * 31)) * 31) + this.f3942e) * 31)) * 31)) * 31;
        if (this.f3944g != null) {
            i = this.f3944g.hashCode();
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
        Discount discount = (Discount) obj;
        if (this.f3939b == null) {
            if (discount.f3939b != null) {
                return false;
            }
        } else if (!this.f3939b.equals(discount.f3939b)) {
            return false;
        }
        if (this.f3941d == null) {
            if (discount.f3941d != null) {
                return false;
            }
        } else if (!this.f3941d.equals(discount.f3941d)) {
            return false;
        }
        if (this.f3943f == null) {
            if (discount.f3943f != null) {
                return false;
            }
        } else if (!this.f3943f.equals(discount.f3943f)) {
            return false;
        }
        if (this.f3945h == null) {
            if (discount.f3945h != null) {
                return false;
            }
        } else if (!this.f3945h.equals(discount.f3945h)) {
            return false;
        }
        if (this.f3942e != discount.f3942e) {
            return false;
        }
        if (this.f3940c == null) {
            if (discount.f3940c != null) {
                return false;
            }
        } else if (!this.f3940c.equals(discount.f3940c)) {
            return false;
        }
        if (this.f3938a == null) {
            if (discount.f3938a != null) {
                return false;
            }
        } else if (!this.f3938a.equals(discount.f3938a)) {
            return false;
        }
        if (this.f3944g == null) {
            if (discount.f3944g != null) {
                return false;
            }
            return true;
        } else if (this.f3944g.equals(discount.f3944g)) {
            return true;
        } else {
            return false;
        }
    }
}
