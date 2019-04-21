package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;

public final class WebImage extends AbstractSafeParcelable {
    public static final Creator<WebImage> CREATOR = new zzb();
    private final int mVersionCode;
    private final Uri zzapT;
    private final int zzpi;
    private final int zzpj;

    WebImage(int i, Uri uri, int i2, int i3) {
        this.mVersionCode = i;
        this.zzapT = uri;
        this.zzpi = i2;
        this.zzpj = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof WebImage)) {
            return false;
        }
        WebImage webImage = (WebImage) obj;
        return zzz.equal(this.zzapT, webImage.zzapT) && this.zzpi == webImage.zzpi && this.zzpj == webImage.zzpj;
    }

    public int getHeight() {
        return this.zzpj;
    }

    public Uri getUrl() {
        return this.zzapT;
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int getWidth() {
        return this.zzpi;
    }

    public int hashCode() {
        return zzz.hashCode(this.zzapT, Integer.valueOf(this.zzpi), Integer.valueOf(this.zzpj));
    }

    public String toString() {
        return String.format("Image %dx%d %s", new Object[]{Integer.valueOf(this.zzpi), Integer.valueOf(this.zzpj), this.zzapT.toString()});
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }
}
