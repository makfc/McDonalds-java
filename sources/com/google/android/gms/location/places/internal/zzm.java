package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzm implements Creator<PlaceLikelihoodEntity> {
    static void zza(PlaceLikelihoodEntity placeLikelihoodEntity, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, placeLikelihoodEntity.zzaYc, i, false);
        zzb.zza(parcel, 2, placeLikelihoodEntity.zzaYd);
        zzb.zzc(parcel, 1000, placeLikelihoodEntity.mVersionCode);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfr */
    public PlaceLikelihoodEntity createFromParcel(Parcel parcel) {
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        PlaceEntity placeEntity = null;
        float f = 0.0f;
        while (parcel.dataPosition() < zzaq) {
            int i2;
            float f2;
            PlaceEntity placeEntity2;
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i2 = i;
                    PlaceEntity placeEntity3 = (PlaceEntity) zza.zza(parcel, zzap, PlaceEntity.CREATOR);
                    f2 = f;
                    placeEntity2 = placeEntity3;
                    break;
                case 2:
                    f2 = zza.zzl(parcel, zzap);
                    placeEntity2 = placeEntity;
                    i2 = i;
                    break;
                case 1000:
                    float f3 = f;
                    placeEntity2 = placeEntity;
                    i2 = zza.zzg(parcel, zzap);
                    f2 = f3;
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    f2 = f;
                    placeEntity2 = placeEntity;
                    i2 = i;
                    break;
            }
            i = i2;
            placeEntity = placeEntity2;
            f = f2;
        }
        if (parcel.dataPosition() == zzaq) {
            return new PlaceLikelihoodEntity(i, placeEntity, f);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zziz */
    public PlaceLikelihoodEntity[] newArray(int i) {
        return new PlaceLikelihoodEntity[i];
    }
}
