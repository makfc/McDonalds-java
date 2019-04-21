package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.maps.model.d */
class TileCreator implements Creator<Tile> {
    TileCreator() {
    }

    /* renamed from: a */
    public Tile createFromParcel(Parcel parcel) {
        return new Tile(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
    }

    /* renamed from: a */
    public Tile[] newArray(int i) {
        return new Tile[i];
    }
}
