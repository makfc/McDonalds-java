package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class Tile implements Parcelable {
    public static final C1066j CREATOR = new C1066j();
    /* renamed from: a */
    private final int f3475a;
    /* renamed from: b */
    private final int f3476b;
    /* renamed from: c */
    private final int f3477c;
    public final byte[] data;

    Tile(int i, int i2, int i3, byte[] bArr) {
        this.f3475a = i;
        this.f3476b = i2;
        this.f3477c = i3;
        this.data = bArr;
    }

    public Tile(int i, int i2, byte[] bArr) {
        this(1, i, i2, bArr);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3475a);
        parcel.writeInt(this.f3476b);
        parcel.writeInt(this.f3477c);
        parcel.writeByteArray(this.data);
    }
}
