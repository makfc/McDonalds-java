package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc implements Creator<ClientIdentity> {
    static void zza(ClientIdentity clientIdentity, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, clientIdentity.uid);
        zzb.zza(parcel, 2, clientIdentity.packageName, false);
        zzb.zzc(parcel, 1000, clientIdentity.getVersionCode());
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfa */
    public ClientIdentity createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaq = zza.zzaq(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 1000:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new ClientIdentity(i2, i, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzid */
    public ClientIdentity[] newArray(int i) {
        return new ClientIdentity[i];
    }
}
