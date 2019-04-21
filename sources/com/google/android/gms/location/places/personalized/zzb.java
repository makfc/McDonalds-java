package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.ArrayList;
import java.util.List;

public class zzb implements Creator<AliasedPlacesResult> {
    static void zza(AliasedPlacesResult aliasedPlacesResult, Parcel parcel, int i) {
        int zzar = com.google.android.gms.common.internal.safeparcel.zzb.zzar(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, aliasedPlacesResult.getStatus(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, aliasedPlacesResult.zzDl(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, aliasedPlacesResult.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfw */
    public AliasedPlacesResult createFromParcel(Parcel parcel) {
        List list = null;
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < zzaq) {
            int i2;
            Status status2;
            ArrayList zzc;
            int zzap = zza.zzap(parcel);
            List zzc2;
            switch (zza.zzcj(zzap)) {
                case 1:
                    i2 = i;
                    Status status3 = (Status) zza.zza(parcel, zzap, Status.CREATOR);
                    zzc2 = list;
                    status2 = status3;
                    break;
                case 2:
                    zzc2 = zza.zzc(parcel, zzap, AliasedPlace.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    List list2 = list;
                    status2 = status;
                    i2 = zza.zzg(parcel, zzap);
                    zzc2 = list2;
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    zzc2 = list;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            Object list3 = zzc2;
        }
        if (parcel.dataPosition() == zzaq) {
            return new AliasedPlacesResult(i, status, list3);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zziE */
    public AliasedPlacesResult[] newArray(int i) {
        return new AliasedPlacesResult[i];
    }
}
