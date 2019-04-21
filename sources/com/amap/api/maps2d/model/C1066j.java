package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* compiled from: TileCreator */
/* renamed from: com.amap.api.maps2d.model.j */
class C1066j implements Creator<Tile> {
    C1066j() {
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
