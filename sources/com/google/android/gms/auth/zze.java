package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zze implements Creator<TokenData> {
    static void zza(TokenData tokenData, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, tokenData.mVersionCode);
        zzb.zza(parcel, 2, tokenData.getToken(), false);
        zzb.zza(parcel, 3, tokenData.zzoQ(), false);
        zzb.zza(parcel, 4, tokenData.zzoR());
        zzb.zza(parcel, 5, tokenData.zzoS());
        zzb.zzb(parcel, 6, tokenData.zzoT(), false);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzE */
    public TokenData createFromParcel(Parcel parcel) {
        List list = null;
        boolean z = false;
        int zzaq = zza.zzaq(parcel);
        boolean z2 = false;
        Long l = null;
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 3:
                    l = zza.zzj(parcel, zzap);
                    break;
                case 4:
                    z2 = zza.zzc(parcel, zzap);
                    break;
                case 5:
                    z = zza.zzc(parcel, zzap);
                    break;
                case 6:
                    list = zza.zzE(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new TokenData(i, str, l, z2, z, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzaL */
    public TokenData[] newArray(int i) {
        return new TokenData[i];
    }
}
