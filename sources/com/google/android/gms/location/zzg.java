package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzg implements Creator<LocationSettingsRequest> {
    static void zza(LocationSettingsRequest locationSettingsRequest, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, locationSettingsRequest.zzyj(), false);
        zzb.zza(parcel, 2, locationSettingsRequest.zzCs());
        zzb.zza(parcel, 3, locationSettingsRequest.zzCt());
        zzb.zzc(parcel, 1000, locationSettingsRequest.getVersionCode());
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzeX */
    public LocationSettingsRequest createFromParcel(Parcel parcel) {
        boolean z = false;
        int zzaq = zza.zzaq(parcel);
        List list = null;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    list = zza.zzc(parcel, zzap, LocationRequest.CREATOR);
                    break;
                case 2:
                    z2 = zza.zzc(parcel, zzap);
                    break;
                case 3:
                    z = zza.zzc(parcel, zzap);
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
            return new LocationSettingsRequest(i, list, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzhY */
    public LocationSettingsRequest[] newArray(int i) {
        return new LocationSettingsRequest[i];
    }
}
