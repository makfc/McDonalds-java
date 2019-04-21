package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public final class Dining implements Parcelable {
    public static final Creator<Dining> CREATOR = new C1159b();
    /* renamed from: a */
    private boolean f3918a;
    /* renamed from: b */
    private String f3919b;
    /* renamed from: c */
    private String f3920c;
    /* renamed from: d */
    private String f3921d;
    /* renamed from: e */
    private String f3922e;
    /* renamed from: f */
    private String f3923f;
    /* renamed from: g */
    private String f3924g;
    /* renamed from: h */
    private String f3925h;
    /* renamed from: i */
    private String f3926i;
    /* renamed from: j */
    private String f3927j;
    /* renamed from: k */
    private String f3928k;
    /* renamed from: l */
    private String f3929l;
    /* renamed from: m */
    private String f3930m;
    /* renamed from: n */
    private String f3931n;
    /* renamed from: o */
    private String f3932o;
    /* renamed from: p */
    private String f3933p;
    /* renamed from: q */
    private String f3934q;
    /* renamed from: r */
    private String f3935r;
    /* renamed from: s */
    private String f3936s;
    /* renamed from: t */
    private List<Photo> f3937t = new ArrayList();

    public boolean isMealOrdering() {
        return this.f3918a;
    }

    public void setMealOrdering(boolean z) {
        this.f3918a = z;
    }

    public String getCuisines() {
        return this.f3919b;
    }

    public void setCuisines(String str) {
        this.f3919b = str;
    }

    public String getTag() {
        return this.f3920c;
    }

    public void setTag(String str) {
        this.f3920c = str;
    }

    public String getIntro() {
        return this.f3921d;
    }

    public void setIntro(String str) {
        this.f3921d = str;
    }

    public String getRating() {
        return this.f3922e;
    }

    public void setRating(String str) {
        this.f3922e = str;
    }

    public String getCpRating() {
        return this.f3923f;
    }

    public void setCpRating(String str) {
        this.f3923f = str;
    }

    public String getDeepsrc() {
        return this.f3924g;
    }

    public void setDeepsrc(String str) {
        this.f3924g = str;
    }

    public String getTasteRating() {
        return this.f3925h;
    }

    public void setTasteRating(String str) {
        this.f3925h = str;
    }

    public String getEnvironmentRating() {
        return this.f3926i;
    }

    public void setEnvironmentRating(String str) {
        this.f3926i = str;
    }

    public String getServiceRating() {
        return this.f3927j;
    }

    public void setServiceRating(String str) {
        this.f3927j = str;
    }

    public String getCost() {
        return this.f3928k;
    }

    public void setCost(String str) {
        this.f3928k = str;
    }

    public String getRecommend() {
        return this.f3929l;
    }

    public void setRecommend(String str) {
        this.f3929l = str;
    }

    public String getAtmosphere() {
        return this.f3930m;
    }

    public void setAtmosphere(String str) {
        this.f3930m = str;
    }

    public String getOrderingWapUrl() {
        return this.f3931n;
    }

    public void setOrderingWapUrl(String str) {
        this.f3931n = str;
    }

    public String getOrderingWebUrl() {
        return this.f3932o;
    }

    public void setOrderingWebUrl(String str) {
        this.f3932o = str;
    }

    public String getOrderinAppUrl() {
        return this.f3933p;
    }

    public void setOrderinAppUrl(String str) {
        this.f3933p = str;
    }

    public String getOpentimeGDF() {
        return this.f3934q;
    }

    public void setOpentimeGDF(String str) {
        this.f3934q = str;
    }

    public String getOpentime() {
        return this.f3935r;
    }

    public void setOpentime(String str) {
        this.f3935r = str;
    }

    public String getAddition() {
        return this.f3936s;
    }

    public void setAddition(String str) {
        this.f3936s = str;
    }

    public List<Photo> getPhotos() {
        return this.f3937t;
    }

    public void setPhotos(List<Photo> list) {
        this.f3937t = list;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBooleanArray(new boolean[]{this.f3918a});
        parcel.writeString(this.f3919b);
        parcel.writeString(this.f3920c);
        parcel.writeString(this.f3921d);
        parcel.writeString(this.f3922e);
        parcel.writeString(this.f3923f);
        parcel.writeString(this.f3924g);
        parcel.writeString(this.f3925h);
        parcel.writeString(this.f3926i);
        parcel.writeString(this.f3927j);
        parcel.writeString(this.f3928k);
        parcel.writeString(this.f3929l);
        parcel.writeString(this.f3930m);
        parcel.writeString(this.f3931n);
        parcel.writeString(this.f3932o);
        parcel.writeString(this.f3933p);
        parcel.writeString(this.f3934q);
        parcel.writeString(this.f3935r);
        parcel.writeString(this.f3936s);
        parcel.writeTypedList(this.f3937t);
    }

    public Dining(Parcel parcel) {
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.f3918a = zArr[0];
        this.f3919b = parcel.readString();
        this.f3920c = parcel.readString();
        this.f3921d = parcel.readString();
        this.f3922e = parcel.readString();
        this.f3923f = parcel.readString();
        this.f3924g = parcel.readString();
        this.f3925h = parcel.readString();
        this.f3926i = parcel.readString();
        this.f3927j = parcel.readString();
        this.f3928k = parcel.readString();
        this.f3929l = parcel.readString();
        this.f3930m = parcel.readString();
        this.f3931n = parcel.readString();
        this.f3932o = parcel.readString();
        this.f3933p = parcel.readString();
        this.f3934q = parcel.readString();
        this.f3935r = parcel.readString();
        this.f3936s = parcel.readString();
        this.f3937t = parcel.createTypedArrayList(Photo.CREATOR);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3920c == null ? 0 : this.f3920c.hashCode()) + (((this.f3927j == null ? 0 : this.f3927j.hashCode()) + (((this.f3929l == null ? 0 : this.f3929l.hashCode()) + (((this.f3922e == null ? 0 : this.f3922e.hashCode()) + (((this.f3937t == null ? 0 : this.f3937t.hashCode()) + (((this.f3932o == null ? 0 : this.f3932o.hashCode()) + (((this.f3931n == null ? 0 : this.f3931n.hashCode()) + (((this.f3933p == null ? 0 : this.f3933p.hashCode()) + (((this.f3934q == null ? 0 : this.f3934q.hashCode()) + (((this.f3935r == null ? 0 : this.f3935r.hashCode()) + (((this.f3918a ? 1231 : 1237) + (((this.f3921d == null ? 0 : this.f3921d.hashCode()) + (((this.f3926i == null ? 0 : this.f3926i.hashCode()) + (((this.f3924g == null ? 0 : this.f3924g.hashCode()) + (((this.f3919b == null ? 0 : this.f3919b.hashCode()) + (((this.f3923f == null ? 0 : this.f3923f.hashCode()) + (((this.f3928k == null ? 0 : this.f3928k.hashCode()) + (((this.f3930m == null ? 0 : this.f3930m.hashCode()) + (((this.f3936s == null ? 0 : this.f3936s.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f3925h != null) {
            i = this.f3925h.hashCode();
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
        Dining dining = (Dining) obj;
        if (this.f3936s == null) {
            if (dining.f3936s != null) {
                return false;
            }
        } else if (!this.f3936s.equals(dining.f3936s)) {
            return false;
        }
        if (this.f3930m == null) {
            if (dining.f3930m != null) {
                return false;
            }
        } else if (!this.f3930m.equals(dining.f3930m)) {
            return false;
        }
        if (this.f3928k == null) {
            if (dining.f3928k != null) {
                return false;
            }
        } else if (!this.f3928k.equals(dining.f3928k)) {
            return false;
        }
        if (this.f3923f == null) {
            if (dining.f3923f != null) {
                return false;
            }
        } else if (!this.f3923f.equals(dining.f3923f)) {
            return false;
        }
        if (this.f3919b == null) {
            if (dining.f3919b != null) {
                return false;
            }
        } else if (!this.f3919b.equals(dining.f3919b)) {
            return false;
        }
        if (this.f3924g == null) {
            if (dining.f3924g != null) {
                return false;
            }
        } else if (!this.f3924g.equals(dining.f3924g)) {
            return false;
        }
        if (this.f3926i == null) {
            if (dining.f3926i != null) {
                return false;
            }
        } else if (!this.f3926i.equals(dining.f3926i)) {
            return false;
        }
        if (this.f3921d == null) {
            if (dining.f3921d != null) {
                return false;
            }
        } else if (!this.f3921d.equals(dining.f3921d)) {
            return false;
        }
        if (this.f3918a != dining.f3918a) {
            return false;
        }
        if (this.f3935r == null) {
            if (dining.f3935r != null) {
                return false;
            }
        } else if (!this.f3935r.equals(dining.f3935r)) {
            return false;
        }
        if (this.f3934q == null) {
            if (dining.f3934q != null) {
                return false;
            }
        } else if (!this.f3934q.equals(dining.f3934q)) {
            return false;
        }
        if (this.f3933p == null) {
            if (dining.f3933p != null) {
                return false;
            }
        } else if (!this.f3933p.equals(dining.f3933p)) {
            return false;
        }
        if (this.f3931n == null) {
            if (dining.f3931n != null) {
                return false;
            }
        } else if (!this.f3931n.equals(dining.f3931n)) {
            return false;
        }
        if (this.f3932o == null) {
            if (dining.f3932o != null) {
                return false;
            }
        } else if (!this.f3932o.equals(dining.f3932o)) {
            return false;
        }
        if (this.f3937t == null) {
            if (dining.f3937t != null) {
                return false;
            }
        } else if (!this.f3937t.equals(dining.f3937t)) {
            return false;
        }
        if (this.f3922e == null) {
            if (dining.f3922e != null) {
                return false;
            }
        } else if (!this.f3922e.equals(dining.f3922e)) {
            return false;
        }
        if (this.f3929l == null) {
            if (dining.f3929l != null) {
                return false;
            }
        } else if (!this.f3929l.equals(dining.f3929l)) {
            return false;
        }
        if (this.f3927j == null) {
            if (dining.f3927j != null) {
                return false;
            }
        } else if (!this.f3927j.equals(dining.f3927j)) {
            return false;
        }
        if (this.f3920c == null) {
            if (dining.f3920c != null) {
                return false;
            }
        } else if (!this.f3920c.equals(dining.f3920c)) {
            return false;
        }
        if (this.f3925h == null) {
            if (dining.f3925h != null) {
                return false;
            }
            return true;
        } else if (this.f3925h.equals(dining.f3925h)) {
            return true;
        } else {
            return false;
        }
    }
}
