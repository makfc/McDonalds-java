package com.google.android.gms.playlog.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc implements Creator<LogEvent> {
    static void zza(LogEvent logEvent, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, logEvent.versionCode);
        zzb.zza(parcel, 2, logEvent.zzbkO);
        zzb.zza(parcel, 3, logEvent.tag, false);
        zzb.zza(parcel, 4, logEvent.zzbkQ, false);
        zzb.zza(parcel, 5, logEvent.zzbkR, false);
        zzb.zza(parcel, 6, logEvent.zzbkP);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzgT */
    public LogEvent createFromParcel(Parcel parcel) {
        long j = 0;
        Bundle bundle = null;
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        byte[] bArr = null;
        String str = null;
        long j2 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    j2 = zza.zzi(parcel, zzap);
                    break;
                case 3:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 4:
                    bArr = zza.zzt(parcel, zzap);
                    break;
                case 5:
                    bundle = zza.zzs(parcel, zzap);
                    break;
                case 6:
                    j = zza.zzi(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new LogEvent(i, j2, j, str, bArr, bundle);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzkp */
    public LogEvent[] newArray(int i) {
        return new LogEvent[i];
    }
}
