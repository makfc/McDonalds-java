package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public final class Hotel implements Parcelable {
    public static final Creator<Hotel> CREATOR = new C1162e();
    /* renamed from: a */
    private String f3961a;
    /* renamed from: b */
    private String f3962b;
    /* renamed from: c */
    private String f3963c;
    /* renamed from: d */
    private String f3964d;
    /* renamed from: e */
    private String f3965e;
    /* renamed from: f */
    private String f3966f;
    /* renamed from: g */
    private String f3967g;
    /* renamed from: h */
    private String f3968h;
    /* renamed from: i */
    private String f3969i;
    /* renamed from: j */
    private String f3970j;
    /* renamed from: k */
    private String f3971k;
    /* renamed from: l */
    private List<Photo> f3972l = new ArrayList();

    public String getRating() {
        return this.f3961a;
    }

    public void setRating(String str) {
        this.f3961a = str;
    }

    public String getStar() {
        return this.f3962b;
    }

    public void setStar(String str) {
        this.f3962b = str;
    }

    public String getIntro() {
        return this.f3963c;
    }

    public void setIntro(String str) {
        this.f3963c = str;
    }

    public String getLowestPrice() {
        return this.f3964d;
    }

    public void setLowestPrice(String str) {
        this.f3964d = str;
    }

    public String getFaciRating() {
        return this.f3965e;
    }

    public void setFaciRating(String str) {
        this.f3965e = str;
    }

    public String getHealthRating() {
        return this.f3966f;
    }

    public void setHealthRating(String str) {
        this.f3966f = str;
    }

    public String getEnvironmentRating() {
        return this.f3967g;
    }

    public void setEnvironmentRating(String str) {
        this.f3967g = str;
    }

    public String getServiceRating() {
        return this.f3968h;
    }

    public void setServiceRating(String str) {
        this.f3968h = str;
    }

    public String getTraffic() {
        return this.f3969i;
    }

    public void setTraffic(String str) {
        this.f3969i = str;
    }

    public String getAddition() {
        return this.f3970j;
    }

    public void setAddition(String str) {
        this.f3970j = str;
    }

    public String getDeepsrc() {
        return this.f3971k;
    }

    public void setDeepsrc(String str) {
        this.f3971k = str;
    }

    public List<Photo> getPhotos() {
        return this.f3972l;
    }

    public void setPhotos(List<Photo> list) {
        this.f3972l = list;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3961a);
        parcel.writeString(this.f3962b);
        parcel.writeString(this.f3963c);
        parcel.writeString(this.f3964d);
        parcel.writeString(this.f3965e);
        parcel.writeString(this.f3966f);
        parcel.writeString(this.f3967g);
        parcel.writeString(this.f3968h);
        parcel.writeString(this.f3969i);
        parcel.writeString(this.f3970j);
        parcel.writeString(this.f3971k);
        parcel.writeTypedList(this.f3972l);
    }

    public Hotel(Parcel parcel) {
        this.f3961a = parcel.readString();
        this.f3962b = parcel.readString();
        this.f3963c = parcel.readString();
        this.f3964d = parcel.readString();
        this.f3965e = parcel.readString();
        this.f3966f = parcel.readString();
        this.f3967g = parcel.readString();
        this.f3968h = parcel.readString();
        this.f3969i = parcel.readString();
        this.f3970j = parcel.readString();
        this.f3971k = parcel.readString();
        this.f3972l = parcel.createTypedArrayList(Photo.CREATOR);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3962b == null ? 0 : this.f3962b.hashCode()) + (((this.f3968h == null ? 0 : this.f3968h.hashCode()) + (((this.f3961a == null ? 0 : this.f3961a.hashCode()) + (((this.f3972l == null ? 0 : this.f3972l.hashCode()) + (((this.f3964d == null ? 0 : this.f3964d.hashCode()) + (((this.f3963c == null ? 0 : this.f3963c.hashCode()) + (((this.f3966f == null ? 0 : this.f3966f.hashCode()) + (((this.f3965e == null ? 0 : this.f3965e.hashCode()) + (((this.f3967g == null ? 0 : this.f3967g.hashCode()) + (((this.f3971k == null ? 0 : this.f3971k.hashCode()) + (((this.f3970j == null ? 0 : this.f3970j.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f3969i != null) {
            i = this.f3969i.hashCode();
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
        Hotel hotel = (Hotel) obj;
        if (this.f3970j == null) {
            if (hotel.f3970j != null) {
                return false;
            }
        } else if (!this.f3970j.equals(hotel.f3970j)) {
            return false;
        }
        if (this.f3971k == null) {
            if (hotel.f3971k != null) {
                return false;
            }
        } else if (!this.f3971k.equals(hotel.f3971k)) {
            return false;
        }
        if (this.f3967g == null) {
            if (hotel.f3967g != null) {
                return false;
            }
        } else if (!this.f3967g.equals(hotel.f3967g)) {
            return false;
        }
        if (this.f3965e == null) {
            if (hotel.f3965e != null) {
                return false;
            }
        } else if (!this.f3965e.equals(hotel.f3965e)) {
            return false;
        }
        if (this.f3966f == null) {
            if (hotel.f3966f != null) {
                return false;
            }
        } else if (!this.f3966f.equals(hotel.f3966f)) {
            return false;
        }
        if (this.f3963c == null) {
            if (hotel.f3963c != null) {
                return false;
            }
        } else if (!this.f3963c.equals(hotel.f3963c)) {
            return false;
        }
        if (this.f3964d == null) {
            if (hotel.f3964d != null) {
                return false;
            }
        } else if (!this.f3964d.equals(hotel.f3964d)) {
            return false;
        }
        if (this.f3972l == null) {
            if (hotel.f3972l != null) {
                return false;
            }
        } else if (!this.f3972l.equals(hotel.f3972l)) {
            return false;
        }
        if (this.f3961a == null) {
            if (hotel.f3961a != null) {
                return false;
            }
        } else if (!this.f3961a.equals(hotel.f3961a)) {
            return false;
        }
        if (this.f3968h == null) {
            if (hotel.f3968h != null) {
                return false;
            }
        } else if (!this.f3968h.equals(hotel.f3968h)) {
            return false;
        }
        if (this.f3962b == null) {
            if (hotel.f3962b != null) {
                return false;
            }
        } else if (!this.f3962b.equals(hotel.f3962b)) {
            return false;
        }
        if (this.f3969i == null) {
            if (hotel.f3969i != null) {
                return false;
            }
            return true;
        } else if (this.f3969i.equals(hotel.f3969i)) {
            return true;
        } else {
            return false;
        }
    }
}
