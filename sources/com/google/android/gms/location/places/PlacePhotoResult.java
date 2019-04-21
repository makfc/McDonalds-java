package com.google.android.gms.location.places;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;

public class PlacePhotoResult extends AbstractSafeParcelable implements Result {
    public static final Creator<PlacePhotoResult> CREATOR = new zzi();
    private final Bitmap mBitmap;
    final int mVersionCode;
    final BitmapTeleporter zzaWU;
    private final Status zzaaO;

    PlacePhotoResult(int i, Status status, BitmapTeleporter bitmapTeleporter) {
        this.mVersionCode = i;
        this.zzaaO = status;
        this.zzaWU = bitmapTeleporter;
        if (this.zzaWU != null) {
            this.mBitmap = bitmapTeleporter.zzsP();
        } else {
            this.mBitmap = null;
        }
    }

    public PlacePhotoResult(Status status, @Nullable BitmapTeleporter bitmapTeleporter) {
        this(0, status, bitmapTeleporter);
    }

    public Status getStatus() {
        return this.zzaaO;
    }

    public String toString() {
        return zzz.zzy(this).zzg("status", this.zzaaO).zzg("bitmap", this.mBitmap).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }
}
