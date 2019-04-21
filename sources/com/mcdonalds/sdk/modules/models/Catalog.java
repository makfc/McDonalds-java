package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Parcelable {
    public static final Creator<Catalog> CREATOR = new C40381();
    private MarketCatalog mMarketCatalog;
    private List<StoreCatalog> mStoreCatalog;

    /* renamed from: com.mcdonalds.sdk.modules.models.Catalog$1 */
    static class C40381 implements Creator<Catalog> {
        C40381() {
        }

        public Catalog createFromParcel(Parcel source) {
            return new Catalog(source);
        }

        public Catalog[] newArray(int size) {
            return new Catalog[size];
        }
    }

    public MarketCatalog getMarketCatalog() {
        return this.mMarketCatalog;
    }

    public void setMarketCatalog(MarketCatalog marketCatalog) {
        this.mMarketCatalog = marketCatalog;
    }

    public List<StoreCatalog> getStoreCatalog() {
        return this.mStoreCatalog;
    }

    public void setStoreCatalog(List<StoreCatalog> storeCatalog) {
        this.mStoreCatalog = storeCatalog;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mMarketCatalog, flags);
        dest.writeList(this.mStoreCatalog);
    }

    protected Catalog(Parcel in) {
        this.mMarketCatalog = (MarketCatalog) in.readParcelable(MarketCatalog.class.getClassLoader());
        this.mStoreCatalog = new ArrayList();
        in.readList(this.mStoreCatalog, StoreCatalog.class.getClassLoader());
    }
}
