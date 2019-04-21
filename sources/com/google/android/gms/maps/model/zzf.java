package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzf implements Creator<MarkerOptions> {
    static void zza(MarkerOptions markerOptions, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, markerOptions.getVersionCode());
        zzb.zza(parcel, 2, markerOptions.getPosition(), i, false);
        zzb.zza(parcel, 3, markerOptions.getTitle(), false);
        zzb.zza(parcel, 4, markerOptions.getSnippet(), false);
        zzb.zza(parcel, 5, markerOptions.zzDU(), false);
        zzb.zza(parcel, 6, markerOptions.getAnchorU());
        zzb.zza(parcel, 7, markerOptions.getAnchorV());
        zzb.zza(parcel, 8, markerOptions.isDraggable());
        zzb.zza(parcel, 9, markerOptions.isVisible());
        zzb.zza(parcel, 10, markerOptions.isFlat());
        zzb.zza(parcel, 11, markerOptions.getRotation());
        zzb.zza(parcel, 12, markerOptions.getInfoWindowAnchorU());
        zzb.zza(parcel, 13, markerOptions.getInfoWindowAnchorV());
        zzb.zza(parcel, 14, markerOptions.getAlpha());
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfG */
    public MarkerOptions createFromParcel(Parcel parcel) {
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        LatLng latLng = null;
        String str = null;
        String str2 = null;
        IBinder iBinder = null;
        float f = 0.0f;
        float f2 = 0.0f;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        float f3 = 0.0f;
        float f4 = 0.5f;
        float f5 = 0.0f;
        float f6 = 1.0f;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    latLng = (LatLng) zza.zza(parcel, zzap, LatLng.CREATOR);
                    break;
                case 3:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 4:
                    str2 = zza.zzq(parcel, zzap);
                    break;
                case 5:
                    iBinder = zza.zzr(parcel, zzap);
                    break;
                case 6:
                    f = zza.zzl(parcel, zzap);
                    break;
                case 7:
                    f2 = zza.zzl(parcel, zzap);
                    break;
                case 8:
                    z = zza.zzc(parcel, zzap);
                    break;
                case 9:
                    z2 = zza.zzc(parcel, zzap);
                    break;
                case 10:
                    z3 = zza.zzc(parcel, zzap);
                    break;
                case 11:
                    f3 = zza.zzl(parcel, zzap);
                    break;
                case 12:
                    f4 = zza.zzl(parcel, zzap);
                    break;
                case 13:
                    f5 = zza.zzl(parcel, zzap);
                    break;
                case 14:
                    f6 = zza.zzl(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new MarkerOptions(i, latLng, str, str2, iBinder, f, f2, z, z2, z3, f3, f4, f5, f6);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zziP */
    public MarkerOptions[] newArray(int i) {
        return new MarkerOptions[i];
    }
}
