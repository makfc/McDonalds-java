package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public final class Cinema implements Parcelable {
    public static final Creator<Cinema> CREATOR = new C1158a();
    /* renamed from: a */
    private boolean f3910a;
    /* renamed from: b */
    private String f3911b;
    /* renamed from: c */
    private String f3912c;
    /* renamed from: d */
    private String f3913d;
    /* renamed from: e */
    private String f3914e;
    /* renamed from: f */
    private String f3915f;
    /* renamed from: g */
    private String f3916g;
    /* renamed from: h */
    private List<Photo> f3917h = new ArrayList();

    public boolean isSeatOrdering() {
        return this.f3910a;
    }

    public void setSeatOrdering(boolean z) {
        this.f3910a = z;
    }

    public String getIntro() {
        return this.f3911b;
    }

    public void setIntro(String str) {
        this.f3911b = str;
    }

    public String getRating() {
        return this.f3912c;
    }

    public void setRating(String str) {
        this.f3912c = str;
    }

    public String getDeepsrc() {
        return this.f3913d;
    }

    public void setDeepsrc(String str) {
        this.f3913d = str;
    }

    public String getParking() {
        return this.f3914e;
    }

    public void setParking(String str) {
        this.f3914e = str;
    }

    public String getOpentimeGDF() {
        return this.f3915f;
    }

    public void setOpentimeGDF(String str) {
        this.f3915f = str;
    }

    public String getOpentime() {
        return this.f3916g;
    }

    public void setOpentime(String str) {
        this.f3916g = str;
    }

    public List<Photo> getPhotos() {
        return this.f3917h;
    }

    public void setPhotos(List<Photo> list) {
        this.f3917h = list;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBooleanArray(new boolean[]{this.f3910a});
        parcel.writeString(this.f3911b);
        parcel.writeString(this.f3912c);
        parcel.writeString(this.f3913d);
        parcel.writeString(this.f3914e);
        parcel.writeString(this.f3915f);
        parcel.writeString(this.f3916g);
        parcel.writeTypedList(this.f3917h);
    }

    public Cinema(Parcel parcel) {
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.f3910a = zArr[0];
        this.f3911b = parcel.readString();
        this.f3912c = parcel.readString();
        this.f3913d = parcel.readString();
        this.f3914e = parcel.readString();
        this.f3915f = parcel.readString();
        this.f3916g = parcel.readString();
        this.f3917h = parcel.createTypedArrayList(Photo.CREATOR);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3917h == null ? 0 : this.f3917h.hashCode()) + (((this.f3914e == null ? 0 : this.f3914e.hashCode()) + (((this.f3915f == null ? 0 : this.f3915f.hashCode()) + (((this.f3916g == null ? 0 : this.f3916g.hashCode()) + (((this.f3911b == null ? 0 : this.f3911b.hashCode()) + (((this.f3913d == null ? 0 : this.f3913d.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f3912c != null) {
            i = this.f3912c.hashCode();
        }
        return (this.f3910a ? 1231 : 1237) + ((hashCode + i) * 31);
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
        Cinema cinema = (Cinema) obj;
        if (this.f3913d == null) {
            if (cinema.f3913d != null) {
                return false;
            }
        } else if (!this.f3913d.equals(cinema.f3913d)) {
            return false;
        }
        if (this.f3911b == null) {
            if (cinema.f3911b != null) {
                return false;
            }
        } else if (!this.f3911b.equals(cinema.f3911b)) {
            return false;
        }
        if (this.f3916g == null) {
            if (cinema.f3916g != null) {
                return false;
            }
        } else if (!this.f3916g.equals(cinema.f3916g)) {
            return false;
        }
        if (this.f3915f == null) {
            if (cinema.f3915f != null) {
                return false;
            }
        } else if (!this.f3915f.equals(cinema.f3915f)) {
            return false;
        }
        if (this.f3914e == null) {
            if (cinema.f3914e != null) {
                return false;
            }
        } else if (!this.f3914e.equals(cinema.f3914e)) {
            return false;
        }
        if (this.f3917h == null) {
            if (cinema.f3917h != null) {
                return false;
            }
        } else if (!this.f3917h.equals(cinema.f3917h)) {
            return false;
        }
        if (this.f3912c == null) {
            if (cinema.f3912c != null) {
                return false;
            }
        } else if (!this.f3912c.equals(cinema.f3912c)) {
            return false;
        }
        if (this.f3910a != cinema.f3910a) {
            return false;
        }
        return true;
    }
}
