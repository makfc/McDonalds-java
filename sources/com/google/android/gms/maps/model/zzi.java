package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzi implements Creator<PolylineOptions> {
    static void zza(PolylineOptions polylineOptions, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, polylineOptions.getVersionCode());
        zzb.zzc(parcel, 2, polylineOptions.getPoints(), false);
        zzb.zza(parcel, 3, polylineOptions.getWidth());
        zzb.zzc(parcel, 4, polylineOptions.getColor());
        zzb.zza(parcel, 5, polylineOptions.getZIndex());
        zzb.zza(parcel, 6, polylineOptions.isVisible());
        zzb.zza(parcel, 7, polylineOptions.isGeodesic());
        zzb.zza(parcel, 8, polylineOptions.isClickable());
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfJ */
    public PolylineOptions createFromParcel(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int zzaq = zza.zzaq(parcel);
        List list = null;
        boolean z2 = false;
        boolean z3 = false;
        int i = 0;
        float f2 = 0.0f;
        int i2 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    list = zza.zzc(parcel, zzap, LatLng.CREATOR);
                    break;
                case 3:
                    f2 = zza.zzl(parcel, zzap);
                    break;
                case 4:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 5:
                    f = zza.zzl(parcel, zzap);
                    break;
                case 6:
                    z3 = zza.zzc(parcel, zzap);
                    break;
                case 7:
                    z2 = zza.zzc(parcel, zzap);
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
            return new PolylineOptions(i2, list, f2, i, f, z3, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zziS */
    public PolylineOptions[] newArray(int i) {
        return new PolylineOptions[i];
    }
}
