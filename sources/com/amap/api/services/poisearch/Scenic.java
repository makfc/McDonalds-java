package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public final class Scenic implements Parcelable {
    public static final Creator<Scenic> CREATOR = new C1166l();
    /* renamed from: a */
    private String f4014a;
    /* renamed from: b */
    private String f4015b;
    /* renamed from: c */
    private String f4016c;
    /* renamed from: d */
    private String f4017d;
    /* renamed from: e */
    private String f4018e;
    /* renamed from: f */
    private String f4019f;
    /* renamed from: g */
    private String f4020g;
    /* renamed from: h */
    private String f4021h;
    /* renamed from: i */
    private String f4022i;
    /* renamed from: j */
    private String f4023j;
    /* renamed from: k */
    private String f4024k;
    /* renamed from: l */
    private String f4025l;
    /* renamed from: m */
    private List<Photo> f4026m = new ArrayList();

    public String getIntro() {
        return this.f4014a;
    }

    public void setIntro(String str) {
        this.f4014a = str;
    }

    public String getRating() {
        return this.f4015b;
    }

    public void setRating(String str) {
        this.f4015b = str;
    }

    public String getDeepsrc() {
        return this.f4016c;
    }

    public void setDeepsrc(String str) {
        this.f4016c = str;
    }

    public String getLevel() {
        return this.f4017d;
    }

    public void setLevel(String str) {
        this.f4017d = str;
    }

    public String getPrice() {
        return this.f4018e;
    }

    public void setPrice(String str) {
        this.f4018e = str;
    }

    public String getSeason() {
        return this.f4019f;
    }

    public void setSeason(String str) {
        this.f4019f = str;
    }

    public String getRecommend() {
        return this.f4020g;
    }

    public void setRecommend(String str) {
        this.f4020g = str;
    }

    public String getTheme() {
        return this.f4021h;
    }

    public void setTheme(String str) {
        this.f4021h = str;
    }

    public String getOrderWapUrl() {
        return this.f4022i;
    }

    public void setOrderWapUrl(String str) {
        this.f4022i = str;
    }

    public String getOrderWebUrl() {
        return this.f4023j;
    }

    public void setOrderWebUrl(String str) {
        this.f4023j = str;
    }

    public String getOpentimeGDF() {
        return this.f4024k;
    }

    public void setOpentimeGDF(String str) {
        this.f4024k = str;
    }

    public String getOpentime() {
        return this.f4025l;
    }

    public void setOpentime(String str) {
        this.f4025l = str;
    }

    public List<Photo> getPhotos() {
        return this.f4026m;
    }

    public void setPhotos(List<Photo> list) {
        this.f4026m = list;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4014a);
        parcel.writeString(this.f4015b);
        parcel.writeString(this.f4016c);
        parcel.writeString(this.f4017d);
        parcel.writeString(this.f4018e);
        parcel.writeString(this.f4019f);
        parcel.writeString(this.f4020g);
        parcel.writeString(this.f4021h);
        parcel.writeString(this.f4022i);
        parcel.writeString(this.f4023j);
        parcel.writeString(this.f4024k);
        parcel.writeString(this.f4025l);
        parcel.writeTypedList(this.f4026m);
    }

    public Scenic(Parcel parcel) {
        this.f4014a = parcel.readString();
        this.f4015b = parcel.readString();
        this.f4016c = parcel.readString();
        this.f4017d = parcel.readString();
        this.f4018e = parcel.readString();
        this.f4019f = parcel.readString();
        this.f4020g = parcel.readString();
        this.f4021h = parcel.readString();
        this.f4022i = parcel.readString();
        this.f4023j = parcel.readString();
        this.f4024k = parcel.readString();
        this.f4025l = parcel.readString();
        this.f4026m = parcel.createTypedArrayList(Photo.CREATOR);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f4019f == null ? 0 : this.f4019f.hashCode()) + (((this.f4020g == null ? 0 : this.f4020g.hashCode()) + (((this.f4015b == null ? 0 : this.f4015b.hashCode()) + (((this.f4018e == null ? 0 : this.f4018e.hashCode()) + (((this.f4026m == null ? 0 : this.f4026m.hashCode()) + (((this.f4023j == null ? 0 : this.f4023j.hashCode()) + (((this.f4022i == null ? 0 : this.f4022i.hashCode()) + (((this.f4024k == null ? 0 : this.f4024k.hashCode()) + (((this.f4025l == null ? 0 : this.f4025l.hashCode()) + (((this.f4017d == null ? 0 : this.f4017d.hashCode()) + (((this.f4014a == null ? 0 : this.f4014a.hashCode()) + (((this.f4016c == null ? 0 : this.f4016c.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f4021h != null) {
            i = this.f4021h.hashCode();
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
        Scenic scenic = (Scenic) obj;
        if (this.f4016c == null) {
            if (scenic.f4016c != null) {
                return false;
            }
        } else if (!this.f4016c.equals(scenic.f4016c)) {
            return false;
        }
        if (this.f4014a == null) {
            if (scenic.f4014a != null) {
                return false;
            }
        } else if (!this.f4014a.equals(scenic.f4014a)) {
            return false;
        }
        if (this.f4017d == null) {
            if (scenic.f4017d != null) {
                return false;
            }
        } else if (!this.f4017d.equals(scenic.f4017d)) {
            return false;
        }
        if (this.f4025l == null) {
            if (scenic.f4025l != null) {
                return false;
            }
        } else if (!this.f4025l.equals(scenic.f4025l)) {
            return false;
        }
        if (this.f4024k == null) {
            if (scenic.f4024k != null) {
                return false;
            }
        } else if (!this.f4024k.equals(scenic.f4024k)) {
            return false;
        }
        if (this.f4022i == null) {
            if (scenic.f4022i != null) {
                return false;
            }
        } else if (!this.f4022i.equals(scenic.f4022i)) {
            return false;
        }
        if (this.f4023j == null) {
            if (scenic.f4023j != null) {
                return false;
            }
        } else if (!this.f4023j.equals(scenic.f4023j)) {
            return false;
        }
        if (this.f4026m == null) {
            if (scenic.f4026m != null) {
                return false;
            }
        } else if (!this.f4026m.equals(scenic.f4026m)) {
            return false;
        }
        if (this.f4018e == null) {
            if (scenic.f4018e != null) {
                return false;
            }
        } else if (!this.f4018e.equals(scenic.f4018e)) {
            return false;
        }
        if (this.f4015b == null) {
            if (scenic.f4015b != null) {
                return false;
            }
        } else if (!this.f4015b.equals(scenic.f4015b)) {
            return false;
        }
        if (this.f4020g == null) {
            if (scenic.f4020g != null) {
                return false;
            }
        } else if (!this.f4020g.equals(scenic.f4020g)) {
            return false;
        }
        if (this.f4019f == null) {
            if (scenic.f4019f != null) {
                return false;
            }
        } else if (!this.f4019f.equals(scenic.f4019f)) {
            return false;
        }
        if (this.f4021h == null) {
            if (scenic.f4021h != null) {
                return false;
            }
            return true;
        } else if (this.f4021h.equals(scenic.f4021h)) {
            return true;
        } else {
            return false;
        }
    }
}
