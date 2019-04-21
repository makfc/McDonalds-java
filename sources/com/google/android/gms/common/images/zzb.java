package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzb implements Creator<WebImage> {
    static void zza(WebImage webImage, Parcel parcel, int i) {
        int zzar = com.google.android.gms.common.internal.safeparcel.zzb.zzar(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, webImage.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, webImage.getUrl(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 3, webImage.getWidth());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 4, webImage.getHeight());
        com.google.android.gms.common.internal.safeparcel.zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzah */
    public WebImage createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaq = zza.zzaq(parcel);
        Uri uri = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzaq) {
            Uri uri2;
            int zzg;
            int zzap = zza.zzap(parcel);
            int i4;
            switch (zza.zzcj(zzap)) {
                case 1:
                    i4 = i;
                    i = i2;
                    uri2 = uri;
                    zzg = zza.zzg(parcel, zzap);
                    zzap = i4;
                    break;
                case 2:
                    zzg = i3;
                    i4 = i2;
                    uri2 = (Uri) zza.zza(parcel, zzap, Uri.CREATOR);
                    zzap = i;
                    i = i4;
                    break;
                case 3:
                    uri2 = uri;
                    zzg = i3;
                    i4 = i;
                    i = zza.zzg(parcel, zzap);
                    zzap = i4;
                    break;
                case 4:
                    zzap = zza.zzg(parcel, zzap);
                    i = i2;
                    uri2 = uri;
                    zzg = i3;
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    zzap = i;
                    i = i2;
                    uri2 = uri;
                    zzg = i3;
                    break;
            }
            i3 = zzg;
            uri = uri2;
            i2 = i;
            i = zzap;
        }
        if (parcel.dataPosition() == zzaq) {
            return new WebImage(i3, uri, i2, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzbW */
    public WebImage[] newArray(int i) {
        return new WebImage[i];
    }
}
