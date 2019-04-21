package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import java.util.ArrayList;
import java.util.List;

public class PoiItemDetail extends PoiItem implements Parcelable {
    public static final Creator<PoiItemDetail> CREATOR = new C1164h();
    /* renamed from: a */
    private List<Groupbuy> f3976a;
    /* renamed from: b */
    private List<Discount> f3977b;
    /* renamed from: c */
    private Dining f3978c;
    /* renamed from: d */
    private Hotel f3979d;
    /* renamed from: e */
    private Cinema f3980e;
    /* renamed from: f */
    private Scenic f3981f;
    /* renamed from: g */
    private DeepType f3982g;

    public enum DeepType {
        UNKNOWN,
        DINING,
        HOTEL,
        CINEMA,
        SCENIC
    }

    /* synthetic */ PoiItemDetail(Parcel parcel, C1164h c1164h) {
        this(parcel);
    }

    public PoiItemDetail(String str, LatLonPoint latLonPoint, String str2, String str3) {
        super(str, latLonPoint, str2, str3);
        this.f3976a = new ArrayList();
        this.f3977b = new ArrayList();
    }

    public List<Groupbuy> getGroupbuys() {
        return this.f3976a;
    }

    public void initGroupbuys(List<Groupbuy> list) {
        if (list != null && list.size() != 0) {
            for (Groupbuy add : list) {
                this.f3976a.add(add);
            }
        }
    }

    public void addGroupbuy(Groupbuy groupbuy) {
        this.f3976a.add(groupbuy);
    }

    public List<Discount> getDiscounts() {
        return this.f3977b;
    }

    public void initDiscounts(List<Discount> list) {
        if (list != null && list.size() != 0) {
            this.f3977b.clear();
            for (Discount add : list) {
                this.f3977b.add(add);
            }
        }
    }

    public void addDiscount(Discount discount) {
        this.f3977b.add(discount);
    }

    public DeepType getDeepType() {
        return this.f3982g;
    }

    public void setDeepType(DeepType deepType) {
        this.f3982g = deepType;
    }

    public Dining getDining() {
        return this.f3978c;
    }

    public void setDining(Dining dining) {
        this.f3978c = dining;
    }

    public Hotel getHotel() {
        return this.f3979d;
    }

    public void setHotel(Hotel hotel) {
        this.f3979d = hotel;
    }

    public Cinema getCinema() {
        return this.f3980e;
    }

    public void setCinema(Cinema cinema) {
        this.f3980e = cinema;
    }

    public Scenic getScenic() {
        return this.f3981f;
    }

    public void setScenic(Scenic scenic) {
        this.f3981f = scenic;
    }

    private PoiItemDetail(Parcel parcel) {
        super(parcel);
        this.f3976a = new ArrayList();
        this.f3977b = new ArrayList();
        this.f3976a = parcel.readArrayList(Groupbuy.class.getClassLoader());
        this.f3977b = parcel.readArrayList(Discount.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeList(this.f3976a);
        parcel.writeList(this.f3977b);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3979d == null ? 0 : this.f3979d.hashCode()) + (((this.f3976a == null ? 0 : this.f3976a.hashCode()) + (((this.f3977b == null ? 0 : this.f3977b.hashCode()) + (((this.f3978c == null ? 0 : this.f3978c.hashCode()) + (((this.f3982g == null ? 0 : this.f3982g.hashCode()) + (((this.f3980e == null ? 0 : this.f3980e.hashCode()) + (super.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f3981f != null) {
            i = this.f3981f.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PoiItemDetail poiItemDetail = (PoiItemDetail) obj;
        if (this.f3980e == null) {
            if (poiItemDetail.f3980e != null) {
                return false;
            }
        } else if (!this.f3980e.equals(poiItemDetail.f3980e)) {
            return false;
        }
        if (this.f3982g != poiItemDetail.f3982g) {
            return false;
        }
        if (this.f3978c == null) {
            if (poiItemDetail.f3978c != null) {
                return false;
            }
        } else if (!this.f3978c.equals(poiItemDetail.f3978c)) {
            return false;
        }
        if (this.f3977b == null) {
            if (poiItemDetail.f3977b != null) {
                return false;
            }
        } else if (!this.f3977b.equals(poiItemDetail.f3977b)) {
            return false;
        }
        if (this.f3976a == null) {
            if (poiItemDetail.f3976a != null) {
                return false;
            }
        } else if (!this.f3976a.equals(poiItemDetail.f3976a)) {
            return false;
        }
        if (this.f3979d == null) {
            if (poiItemDetail.f3979d != null) {
                return false;
            }
        } else if (!this.f3979d.equals(poiItemDetail.f3979d)) {
            return false;
        }
        if (this.f3981f == null) {
            if (poiItemDetail.f3981f != null) {
                return false;
            }
            return true;
        } else if (this.f3981f.equals(poiItemDetail.f3981f)) {
            return true;
        } else {
            return false;
        }
    }
}
