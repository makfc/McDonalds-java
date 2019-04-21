package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzh implements Creator<PlusSession> {
    static void zza(PlusSession plusSession, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, plusSession.getAccountName(), false);
        zzb.zza(parcel, 2, plusSession.zzIp(), false);
        zzb.zza(parcel, 3, plusSession.zzIq(), false);
        zzb.zza(parcel, 4, plusSession.zzIr(), false);
        zzb.zza(parcel, 5, plusSession.zzIs(), false);
        zzb.zza(parcel, 6, plusSession.zzIt(), false);
        zzb.zza(parcel, 7, plusSession.zzqg(), false);
        zzb.zzc(parcel, 1000, plusSession.getVersionCode());
        zzb.zza(parcel, 8, plusSession.zzIu(), false);
        zzb.zza(parcel, 9, plusSession.zzIv(), i, false);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzgW */
    public PlusSession createFromParcel(Parcel parcel) {
        PlusCommonExtras plusCommonExtras = null;
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String[] strArr = null;
        String[] strArr2 = null;
        String[] strArr3 = null;
        String str5 = null;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    str5 = zza.zzq(parcel, zzap);
                    break;
                case 2:
                    strArr3 = zza.zzC(parcel, zzap);
                    break;
                case 3:
                    strArr2 = zza.zzC(parcel, zzap);
                    break;
                case 4:
                    strArr = zza.zzC(parcel, zzap);
                    break;
                case 5:
                    str4 = zza.zzq(parcel, zzap);
                    break;
                case 6:
                    str3 = zza.zzq(parcel, zzap);
                    break;
                case 7:
                    str2 = zza.zzq(parcel, zzap);
                    break;
                case 8:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 9:
                    plusCommonExtras = (PlusCommonExtras) zza.zza(parcel, zzap, PlusCommonExtras.CREATOR);
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
            return new PlusSession(i, str5, strArr3, strArr2, strArr, str4, str3, str2, str, plusCommonExtras);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzkt */
    public PlusSession[] newArray(int i) {
        return new PlusSession[i];
    }
}
