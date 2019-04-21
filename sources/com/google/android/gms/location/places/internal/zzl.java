package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

public class zzl implements Creator<PlaceEntity> {
    static void zza(PlaceEntity placeEntity, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, placeEntity.getId(), false);
        zzb.zza(parcel, 2, placeEntity.zzDc(), false);
        zzb.zza(parcel, 3, placeEntity.zzDe(), i, false);
        zzb.zza(parcel, 4, placeEntity.getLatLng(), i, false);
        zzb.zza(parcel, 5, placeEntity.zzCX());
        zzb.zza(parcel, 6, placeEntity.getViewport(), i, false);
        zzb.zza(parcel, 7, placeEntity.zzDd(), false);
        zzb.zzc(parcel, 1000, placeEntity.mVersionCode);
        zzb.zza(parcel, 8, placeEntity.getWebsiteUri(), i, false);
        zzb.zza(parcel, 9, placeEntity.zzDa());
        zzb.zza(parcel, 10, placeEntity.getRating());
        zzb.zzc(parcel, 11, placeEntity.getPriceLevel());
        zzb.zza(parcel, 12, placeEntity.zzDb());
        zzb.zza(parcel, 13, placeEntity.zzCW(), false);
        zzb.zza(parcel, 14, (String) placeEntity.getAddress(), false);
        zzb.zza(parcel, 15, (String) placeEntity.getPhoneNumber(), false);
        zzb.zza(parcel, 16, placeEntity.zzCY(), false);
        zzb.zzb(parcel, 17, placeEntity.zzCZ(), false);
        zzb.zza(parcel, 19, (String) placeEntity.getName(), false);
        zzb.zza(parcel, 20, placeEntity.getPlaceTypes(), false);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfq */
    public PlaceEntity createFromParcel(Parcel parcel) {
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        String str = null;
        List list = null;
        List list2 = null;
        Bundle bundle = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        List list3 = null;
        LatLng latLng = null;
        float f = 0.0f;
        LatLngBounds latLngBounds = null;
        String str6 = null;
        Uri uri = null;
        boolean z = false;
        float f2 = 0.0f;
        int i2 = 0;
        long j = 0;
        PlaceLocalization placeLocalization = null;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 2:
                    bundle = zza.zzs(parcel, zzap);
                    break;
                case 3:
                    placeLocalization = (PlaceLocalization) zza.zza(parcel, zzap, (Creator) PlaceLocalization.CREATOR);
                    break;
                case 4:
                    latLng = (LatLng) zza.zza(parcel, zzap, LatLng.CREATOR);
                    break;
                case 5:
                    f = zza.zzl(parcel, zzap);
                    break;
                case 6:
                    latLngBounds = (LatLngBounds) zza.zza(parcel, zzap, (Creator) LatLngBounds.CREATOR);
                    break;
                case 7:
                    str6 = zza.zzq(parcel, zzap);
                    break;
                case 8:
                    uri = (Uri) zza.zza(parcel, zzap, Uri.CREATOR);
                    break;
                case 9:
                    z = zza.zzc(parcel, zzap);
                    break;
                case 10:
                    f2 = zza.zzl(parcel, zzap);
                    break;
                case 11:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                case 12:
                    j = zza.zzi(parcel, zzap);
                    break;
                case 13:
                    list2 = zza.zzD(parcel, zzap);
                    break;
                case 14:
                    str3 = zza.zzq(parcel, zzap);
                    break;
                case 15:
                    str4 = zza.zzq(parcel, zzap);
                    break;
                case 16:
                    str5 = zza.zzq(parcel, zzap);
                    break;
                case 17:
                    list3 = zza.zzE(parcel, zzap);
                    break;
                case 19:
                    str2 = zza.zzq(parcel, zzap);
                    break;
                case 20:
                    list = zza.zzD(parcel, zzap);
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
            return new PlaceEntity(i, str, list, list2, bundle, str2, str3, str4, str5, list3, latLng, f, latLngBounds, str6, uri, z, f2, i2, j, placeLocalization);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zziy */
    public PlaceEntity[] newArray(int i) {
        return new PlaceEntity[i];
    }
}
