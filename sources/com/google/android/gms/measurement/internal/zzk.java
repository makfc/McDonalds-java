package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzk implements Creator<EventParcel> {
    static void zza(EventParcel eventParcel, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, eventParcel.versionCode);
        zzb.zza(parcel, 2, eventParcel.name, false);
        zzb.zza(parcel, 3, eventParcel.zzbcq, i, false);
        zzb.zza(parcel, 4, eventParcel.zzbcr, false);
        zzb.zza(parcel, 5, eventParcel.zzbcs);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfT */
    public EventParcel createFromParcel(Parcel parcel) {
        String str = null;
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        long j = 0;
        EventParams eventParams = null;
        String str2 = null;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    str2 = zza.zzq(parcel, zzap);
                    break;
                case 3:
                    eventParams = (EventParams) zza.zza(parcel, zzap, EventParams.CREATOR);
                    break;
                case 4:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 5:
                    j = zza.zzi(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new EventParcel(i, str2, eventParams, str, j);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzjd */
    public EventParcel[] newArray(int i) {
        return new EventParcel[i];
    }
}
