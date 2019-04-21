package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzo implements Creator<ParcelableGeofence> {
    static void zza(ParcelableGeofence parcelableGeofence, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, parcelableGeofence.getRequestId(), false);
        zzb.zza(parcel, 2, parcelableGeofence.getExpirationTime());
        zzb.zza(parcel, 3, parcelableGeofence.zzCB());
        zzb.zza(parcel, 4, parcelableGeofence.getLatitude());
        zzb.zza(parcel, 5, parcelableGeofence.getLongitude());
        zzb.zza(parcel, 6, parcelableGeofence.getRadius());
        zzb.zzc(parcel, 7, parcelableGeofence.zzCC());
        zzb.zzc(parcel, 1000, parcelableGeofence.getVersionCode());
        zzb.zzc(parcel, 8, parcelableGeofence.zzCD());
        zzb.zzc(parcel, 9, parcelableGeofence.zzCE());
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfe */
    public ParcelableGeofence createFromParcel(Parcel parcel) {
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        String str = null;
        int i2 = 0;
        short s = (short) 0;
        double d = 0.0d;
        double d2 = 0.0d;
        float f = 0.0f;
        long j = 0;
        int i3 = 0;
        int i4 = -1;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 2:
                    j = zza.zzi(parcel, zzap);
                    break;
                case 3:
                    s = zza.zzf(parcel, zzap);
                    break;
                case 4:
                    d = zza.zzn(parcel, zzap);
                    break;
                case 5:
                    d2 = zza.zzn(parcel, zzap);
                    break;
                case 6:
                    f = zza.zzl(parcel, zzap);
                    break;
                case 7:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                case 8:
                    i3 = zza.zzg(parcel, zzap);
                    break;
                case 9:
                    i4 = zza.zzg(parcel, zzap);
                    break;
                case 1000:
                    i = zza.zzg(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new ParcelableGeofence(i, str, i2, s, d, d2, f, j, i3, i4);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzik */
    public ParcelableGeofence[] newArray(int i) {
        return new ParcelableGeofence[i];
    }
}
