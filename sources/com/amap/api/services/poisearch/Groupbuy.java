package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.C1128d;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Groupbuy implements Parcelable {
    public static final Creator<Groupbuy> CREATOR = new C1161d();
    /* renamed from: a */
    private String f3946a;
    /* renamed from: b */
    private String f3947b;
    /* renamed from: c */
    private String f3948c;
    /* renamed from: d */
    private Date f3949d;
    /* renamed from: e */
    private Date f3950e;
    /* renamed from: f */
    private int f3951f;
    /* renamed from: g */
    private int f3952g;
    /* renamed from: h */
    private float f3953h;
    /* renamed from: i */
    private float f3954i;
    /* renamed from: j */
    private float f3955j;
    /* renamed from: k */
    private String f3956k;
    /* renamed from: l */
    private String f3957l;
    /* renamed from: m */
    private List<Photo> f3958m = new ArrayList();
    /* renamed from: n */
    private String f3959n;
    /* renamed from: o */
    private String f3960o;

    public String getTypeCode() {
        return this.f3946a;
    }

    public void setTypeCode(String str) {
        this.f3946a = str;
    }

    public String getTypeDes() {
        return this.f3947b;
    }

    public void setTypeDes(String str) {
        this.f3947b = str;
    }

    public String getDetail() {
        return this.f3948c;
    }

    public void setDetail(String str) {
        this.f3948c = str;
    }

    public Date getStartTime() {
        if (this.f3949d == null) {
            return null;
        }
        return (Date) this.f3949d.clone();
    }

    public void setStartTime(Date date) {
        if (date == null) {
            this.f3949d = null;
        } else {
            this.f3949d = (Date) date.clone();
        }
    }

    public Date getEndTime() {
        if (this.f3950e == null) {
            return null;
        }
        return (Date) this.f3950e.clone();
    }

    public void setEndTime(Date date) {
        if (date == null) {
            this.f3950e = null;
        } else {
            this.f3950e = (Date) date.clone();
        }
    }

    public int getCount() {
        return this.f3951f;
    }

    public void setCount(int i) {
        this.f3951f = i;
    }

    public int getSoldCount() {
        return this.f3952g;
    }

    public void setSoldCount(int i) {
        this.f3952g = i;
    }

    public float getOriginalPrice() {
        return this.f3953h;
    }

    public void setOriginalPrice(float f) {
        this.f3953h = f;
    }

    public float getGroupbuyPrice() {
        return this.f3954i;
    }

    public void setGroupbuyPrice(float f) {
        this.f3954i = f;
    }

    public float getDiscount() {
        return this.f3955j;
    }

    public void setDiscount(float f) {
        this.f3955j = f;
    }

    public String getTicketAddress() {
        return this.f3956k;
    }

    public void setTicketAddress(String str) {
        this.f3956k = str;
    }

    public String getTicketTel() {
        return this.f3957l;
    }

    public void setTicketTel(String str) {
        this.f3957l = str;
    }

    public List<Photo> getPhotos() {
        return this.f3958m;
    }

    public void addPhotos(Photo photo) {
        this.f3958m.add(photo);
    }

    public void initPhotos(List<Photo> list) {
        if (list != null && list.size() != 0) {
            this.f3958m.clear();
            for (Photo add : list) {
                this.f3958m.add(add);
            }
        }
    }

    public String getUrl() {
        return this.f3959n;
    }

    public void setUrl(String str) {
        this.f3959n = str;
    }

    public String getProvider() {
        return this.f3960o;
    }

    public void setProvider(String str) {
        this.f3960o = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3946a);
        parcel.writeString(this.f3947b);
        parcel.writeString(this.f3948c);
        parcel.writeString(C1128d.m4973a(this.f3949d));
        parcel.writeString(C1128d.m4973a(this.f3950e));
        parcel.writeInt(this.f3951f);
        parcel.writeInt(this.f3952g);
        parcel.writeFloat(this.f3953h);
        parcel.writeFloat(this.f3954i);
        parcel.writeFloat(this.f3955j);
        parcel.writeString(this.f3956k);
        parcel.writeString(this.f3957l);
        parcel.writeTypedList(this.f3958m);
        parcel.writeString(this.f3959n);
        parcel.writeString(this.f3960o);
    }

    public Groupbuy(Parcel parcel) {
        this.f3946a = parcel.readString();
        this.f3947b = parcel.readString();
        this.f3948c = parcel.readString();
        this.f3949d = C1128d.m4980e(parcel.readString());
        this.f3950e = C1128d.m4980e(parcel.readString());
        this.f3951f = parcel.readInt();
        this.f3952g = parcel.readInt();
        this.f3953h = parcel.readFloat();
        this.f3954i = parcel.readFloat();
        this.f3955j = parcel.readFloat();
        this.f3956k = parcel.readString();
        this.f3957l = parcel.readString();
        this.f3958m = parcel.createTypedArrayList(Photo.CREATOR);
        this.f3959n = parcel.readString();
        this.f3960o = parcel.readString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3947b == null ? 0 : this.f3947b.hashCode()) + (((this.f3946a == null ? 0 : this.f3946a.hashCode()) + (((this.f3957l == null ? 0 : this.f3957l.hashCode()) + (((this.f3956k == null ? 0 : this.f3956k.hashCode()) + (((this.f3949d == null ? 0 : this.f3949d.hashCode()) + (((((this.f3960o == null ? 0 : this.f3960o.hashCode()) + (((this.f3958m == null ? 0 : this.f3958m.hashCode()) + (((((((this.f3950e == null ? 0 : this.f3950e.hashCode()) + (((((this.f3948c == null ? 0 : this.f3948c.hashCode()) + ((this.f3951f + 31) * 31)) * 31) + Float.floatToIntBits(this.f3955j)) * 31)) * 31) + Float.floatToIntBits(this.f3954i)) * 31) + Float.floatToIntBits(this.f3953h)) * 31)) * 31)) * 31) + this.f3952g) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f3959n != null) {
            i = this.f3959n.hashCode();
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
        Groupbuy groupbuy = (Groupbuy) obj;
        if (this.f3951f != groupbuy.f3951f) {
            return false;
        }
        if (this.f3948c == null) {
            if (groupbuy.f3948c != null) {
                return false;
            }
        } else if (!this.f3948c.equals(groupbuy.f3948c)) {
            return false;
        }
        if (Float.floatToIntBits(this.f3955j) != Float.floatToIntBits(groupbuy.f3955j)) {
            return false;
        }
        if (this.f3950e == null) {
            if (groupbuy.f3950e != null) {
                return false;
            }
        } else if (!this.f3950e.equals(groupbuy.f3950e)) {
            return false;
        }
        if (Float.floatToIntBits(this.f3954i) != Float.floatToIntBits(groupbuy.f3954i)) {
            return false;
        }
        if (Float.floatToIntBits(this.f3953h) != Float.floatToIntBits(groupbuy.f3953h)) {
            return false;
        }
        if (this.f3958m == null) {
            if (groupbuy.f3958m != null) {
                return false;
            }
        } else if (!this.f3958m.equals(groupbuy.f3958m)) {
            return false;
        }
        if (this.f3960o == null) {
            if (groupbuy.f3960o != null) {
                return false;
            }
        } else if (!this.f3960o.equals(groupbuy.f3960o)) {
            return false;
        }
        if (this.f3952g != groupbuy.f3952g) {
            return false;
        }
        if (this.f3949d == null) {
            if (groupbuy.f3949d != null) {
                return false;
            }
        } else if (!this.f3949d.equals(groupbuy.f3949d)) {
            return false;
        }
        if (this.f3956k == null) {
            if (groupbuy.f3956k != null) {
                return false;
            }
        } else if (!this.f3956k.equals(groupbuy.f3956k)) {
            return false;
        }
        if (this.f3957l == null) {
            if (groupbuy.f3957l != null) {
                return false;
            }
        } else if (!this.f3957l.equals(groupbuy.f3957l)) {
            return false;
        }
        if (this.f3946a == null) {
            if (groupbuy.f3946a != null) {
                return false;
            }
        } else if (!this.f3946a.equals(groupbuy.f3946a)) {
            return false;
        }
        if (this.f3947b == null) {
            if (groupbuy.f3947b != null) {
                return false;
            }
        } else if (!this.f3947b.equals(groupbuy.f3947b)) {
            return false;
        }
        if (this.f3959n == null) {
            if (groupbuy.f3959n != null) {
                return false;
            }
            return true;
        } else if (this.f3959n.equals(groupbuy.f3959n)) {
            return true;
        } else {
            return false;
        }
    }
}
