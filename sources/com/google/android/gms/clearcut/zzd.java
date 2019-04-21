package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.playlog.internal.PlayLoggerContext;

public class zzd implements Creator<LogEventParcelable> {
    static void zza(LogEventParcelable logEventParcelable, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, logEventParcelable.versionCode);
        zzb.zza(parcel, 2, logEventParcelable.zzajL, i, false);
        zzb.zza(parcel, 3, logEventParcelable.zzajM, false);
        zzb.zza(parcel, 4, logEventParcelable.zzajN, false);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzab */
    public LogEventParcelable createFromParcel(Parcel parcel) {
        int[] iArr = null;
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        byte[] bArr = null;
        PlayLoggerContext playLoggerContext = null;
        while (parcel.dataPosition() < zzaq) {
            byte[] bArr2;
            PlayLoggerContext playLoggerContext2;
            int zzg;
            int[] iArr2;
            int zzap = zza.zzap(parcel);
            int[] iArr3;
            switch (zza.zzcj(zzap)) {
                case 1:
                    iArr3 = iArr;
                    bArr2 = bArr;
                    playLoggerContext2 = playLoggerContext;
                    zzg = zza.zzg(parcel, zzap);
                    iArr2 = iArr3;
                    break;
                case 2:
                    zzg = i;
                    byte[] bArr3 = bArr;
                    playLoggerContext2 = (PlayLoggerContext) zza.zza(parcel, zzap, PlayLoggerContext.CREATOR);
                    iArr2 = iArr;
                    bArr2 = bArr3;
                    break;
                case 3:
                    playLoggerContext2 = playLoggerContext;
                    zzg = i;
                    iArr3 = iArr;
                    bArr2 = zza.zzt(parcel, zzap);
                    iArr2 = iArr3;
                    break;
                case 4:
                    iArr2 = zza.zzw(parcel, zzap);
                    bArr2 = bArr;
                    playLoggerContext2 = playLoggerContext;
                    zzg = i;
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    iArr2 = iArr;
                    bArr2 = bArr;
                    playLoggerContext2 = playLoggerContext;
                    zzg = i;
                    break;
            }
            i = zzg;
            playLoggerContext = playLoggerContext2;
            bArr = bArr2;
            iArr = iArr2;
        }
        if (parcel.dataPosition() == zzaq) {
            return new LogEventParcelable(i, playLoggerContext, bArr, iArr);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzbz */
    public LogEventParcelable[] newArray(int i) {
        return new LogEventParcelable[i];
    }
}
