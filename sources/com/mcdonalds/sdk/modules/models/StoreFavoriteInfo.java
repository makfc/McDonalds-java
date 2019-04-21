package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class StoreFavoriteInfo implements Parcelable {
    public static final Creator<StoreFavoriteInfo> CREATOR = new C40871();
    private int mFavoriteId;
    private String mFavoriteNickName;
    private int mStoreId;

    /* renamed from: com.mcdonalds.sdk.modules.models.StoreFavoriteInfo$1 */
    static class C40871 implements Creator<StoreFavoriteInfo> {
        C40871() {
        }

        public StoreFavoriteInfo createFromParcel(Parcel source) {
            return new StoreFavoriteInfo(source);
        }

        public StoreFavoriteInfo[] newArray(int size) {
            return new StoreFavoriteInfo[size];
        }
    }

    public int getStoreId() {
        return this.mStoreId;
    }

    public void setStoreId(int storeId) {
        this.mStoreId = storeId;
    }

    public int getFavoriteId() {
        return this.mFavoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.mFavoriteId = favoriteId;
    }

    public String getFavoriteNickName() {
        return this.mFavoriteNickName;
    }

    public void setFavoriteNickName(String favoriteNickName) {
        this.mFavoriteNickName = favoriteNickName;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mStoreId);
        dest.writeInt(this.mFavoriteId);
        dest.writeString(this.mFavoriteNickName);
    }

    protected StoreFavoriteInfo(Parcel in) {
        this.mStoreId = in.readInt();
        this.mFavoriteId = in.readInt();
        this.mFavoriteNickName = in.readString();
    }
}
