package com.google.android.gms.playlog.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze implements Creator<PlayLoggerContext> {
    static void zza(PlayLoggerContext playLoggerContext, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, playLoggerContext.versionCode);
        zzb.zza(parcel, 2, playLoggerContext.packageName, false);
        zzb.zzc(parcel, 3, playLoggerContext.zzbkZ);
        zzb.zzc(parcel, 4, playLoggerContext.zzbla);
        zzb.zza(parcel, 5, playLoggerContext.zzblb, false);
        zzb.zza(parcel, 6, playLoggerContext.zzblc, false);
        zzb.zza(parcel, 7, playLoggerContext.zzbld);
        zzb.zza(parcel, 8, playLoggerContext.zzble, false);
        zzb.zza(parcel, 9, playLoggerContext.zzblf);
        zzb.zzc(parcel, 10, playLoggerContext.zzblg);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzgU */
    public PlayLoggerContext createFromParcel(Parcel parcel) {
        String str = null;
        int i = 0;
        int zzaq = zza.zzaq(parcel);
        boolean z = true;
        boolean z2 = false;
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        int i3 = 0;
        String str4 = null;
        int i4 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i4 = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    str4 = zza.zzq(parcel, zzap);
                    break;
                case 3:
                    i3 = zza.zzg(parcel, zzap);
                    break;
                case 4:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                case 5:
                    str3 = zza.zzq(parcel, zzap);
                    break;
                case 6:
                    str2 = zza.zzq(parcel, zzap);
                    break;
                case 7:
                    z = zza.zzc(parcel, zzap);
                    break;
                case 8:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 9:
                    z2 = zza.zzc(parcel, zzap);
                    break;
                case 10:
                    i = zza.zzg(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new PlayLoggerContext(i4, str4, i3, i2, str3, str2, z, str, z2, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzkq */
    public PlayLoggerContext[] newArray(int i) {
        return new PlayLoggerContext[i];
    }
}
