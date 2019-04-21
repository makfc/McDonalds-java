package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzb implements Creator<CircleOptions> {
    static void zza(CircleOptions circleOptions, Parcel parcel, int i) {
        int zzar = com.google.android.gms.common.internal.safeparcel.zzb.zzar(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, circleOptions.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, circleOptions.getCenter(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, circleOptions.getRadius());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, circleOptions.getStrokeWidth());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 5, circleOptions.getStrokeColor());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 6, circleOptions.getFillColor());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, circleOptions.getZIndex());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 8, circleOptions.isVisible());
        com.google.android.gms.common.internal.safeparcel.zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfC */
    public CircleOptions createFromParcel(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int zzaq = zza.zzaq(parcel);
        LatLng latLng = null;
        double d = 0.0d;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        int i3 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i3 = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    latLng = (LatLng) zza.zza(parcel, zzap, LatLng.CREATOR);
                    break;
                case 3:
                    d = zza.zzn(parcel, zzap);
                    break;
                case 4:
                    f2 = zza.zzl(parcel, zzap);
                    break;
                case 5:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                case 6:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 7:
                    f = zza.zzl(parcel, zzap);
                    break;
                case 8:
                    z = zza.zzc(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new CircleOptions(i3, latLng, d, f2, i2, i, f, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zziL */
    public CircleOptions[] newArray(int i) {
        return new CircleOptions[i];
    }
}
