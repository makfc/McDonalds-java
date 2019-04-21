package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zza implements Creator<AliasedPlace> {
    static void zza(AliasedPlace aliasedPlace, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, aliasedPlace.getPlaceId(), false);
        zzb.zzb(parcel, 2, aliasedPlace.zzDk(), false);
        zzb.zzc(parcel, 1000, aliasedPlace.mVersionCode);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfv */
    public AliasedPlace createFromParcel(Parcel parcel) {
        List list = null;
        int zzaq = com.google.android.gms.common.internal.safeparcel.zza.zzaq(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzaq) {
            int zzap = com.google.android.gms.common.internal.safeparcel.zza.zzap(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzcj(zzap)) {
                case 1:
                    str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzap);
                    break;
                case 2:
                    list = com.google.android.gms.common.internal.safeparcel.zza.zzE(parcel, zzap);
                    break;
                case 1000:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzap);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new AliasedPlace(i, str, list);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zziD */
    public AliasedPlace[] newArray(int i) {
        return new AliasedPlace[i];
    }
}
