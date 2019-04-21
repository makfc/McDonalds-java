package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class FavoriteItem extends CustomerOrder {
    public static final Creator<FavoriteItem> CREATOR = new C40491();
    private Integer mFavoriteId;
    private FavoriteProductType mType;

    /* renamed from: com.mcdonalds.sdk.modules.models.FavoriteItem$1 */
    static class C40491 implements Creator<FavoriteItem> {
        C40491() {
        }

        public FavoriteItem createFromParcel(Parcel source) {
            return new FavoriteItem(source);
        }

        public FavoriteItem[] newArray(int size) {
            return new FavoriteItem[size];
        }
    }

    public enum FavoriteProductType {
        FAVORITE_PRODUCT_TYPE_ITEM(1),
        FAVORITE_PRODUCT_TYPE_ORDER(2);
        
        private int mTypeValue;

        private FavoriteProductType(int type) {
            this.mTypeValue = type;
        }

        public static FavoriteProductType getType(int index) {
            switch (index) {
                case 1:
                    return FAVORITE_PRODUCT_TYPE_ITEM;
                case 2:
                    return FAVORITE_PRODUCT_TYPE_ORDER;
                default:
                    return null;
            }
        }

        public int getTypeValue() {
            return this.mTypeValue;
        }
    }

    public Integer getFavoriteId() {
        return this.mFavoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.mFavoriteId = favoriteId;
    }

    public FavoriteProductType getType() {
        return this.mType;
    }

    public void setType(FavoriteProductType type) {
        this.mType = type;
    }

    public void setType(int type) {
        this.mType = FavoriteProductType.getType(type);
    }

    public String toString() {
        return "FavoriteOrderProduct{mFavoriteId=" + this.mFavoriteId + ", mType=" + this.mType + ", mName=\"" + getName() + "\", mOrderProducts=" + getProducts() + "}";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeValue(this.mFavoriteId);
        dest.writeInt(this.mType == null ? -1 : this.mType.ordinal());
    }

    protected FavoriteItem(Parcel in) {
        super(in);
        this.mFavoriteId = (Integer) in.readValue(Integer.class.getClassLoader());
        int tmpMType = in.readInt();
        this.mType = tmpMType == -1 ? null : FavoriteProductType.values()[tmpMType];
    }
}
