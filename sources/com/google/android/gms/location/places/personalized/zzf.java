package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzf implements Creator<PlaceUserData> {
    static void zza(PlaceUserData placeUserData, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, placeUserData.zzDn(), false);
        zzb.zza(parcel, 2, placeUserData.getPlaceId(), false);
        zzb.zzc(parcel, 6, placeUserData.zzDk(), false);
        zzb.zzc(parcel, 1000, placeUserData.mVersionCode);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfy */
    public PlaceUserData createFromParcel(Parcel parcel) {
        List list = null;
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    str2 = zza.zzq(parcel, zzap);
                    break;
                case 2:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 6:
                    list = zza.zzc(parcel, zzap, PlaceAlias.CREATOR);
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
            return new PlaceUserData(i, str2, str, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zziG */
    public PlaceUserData[] newArray(int i) {
        return new PlaceUserData[i];
    }
}
