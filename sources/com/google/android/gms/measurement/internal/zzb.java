package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzb implements Creator<AppMetadata> {
    static void zza(AppMetadata appMetadata, Parcel parcel, int i) {
        int zzar = com.google.android.gms.common.internal.safeparcel.zzb.zzar(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, appMetadata.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, appMetadata.packageName, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, appMetadata.zzbbK, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, appMetadata.zzaUf, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, appMetadata.zzbbL, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, appMetadata.zzbbM);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, appMetadata.zzbbN);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 8, appMetadata.zzbbO, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 9, appMetadata.zzbbP);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 10, appMetadata.zzbbQ);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 11, appMetadata.zzbbR);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 12, appMetadata.zzbbS, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfR */
    public AppMetadata createFromParcel(Parcel parcel) {
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        long j = 0;
        long j2 = 0;
        String str5 = null;
        boolean z = false;
        boolean z2 = false;
        long j3 = 0;
        String str6 = null;
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
                    str2 = zza.zzq(parcel, zzap);
                    break;
                case 4:
                    str3 = zza.zzq(parcel, zzap);
                    break;
                case 5:
                    str4 = zza.zzq(parcel, zzap);
                    break;
                case 6:
                    j = zza.zzi(parcel, zzap);
                    break;
                case 7:
                    j2 = zza.zzi(parcel, zzap);
                    break;
                case 8:
                    str5 = zza.zzq(parcel, zzap);
                    break;
                case 9:
                    z = zza.zzc(parcel, zzap);
                    break;
                case 10:
                    z2 = zza.zzc(parcel, zzap);
                    break;
                case 11:
                    j3 = zza.zzi(parcel, zzap);
                    break;
                case 12:
                    str6 = zza.zzq(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new AppMetadata(i, str, str2, str3, str4, j, j2, str5, z, z2, j3, str6);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzjb */
    public AppMetadata[] newArray(int i) {
        return new AppMetadata[i];
    }
}
