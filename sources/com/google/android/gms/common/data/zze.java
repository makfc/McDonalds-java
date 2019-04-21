package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze implements Creator<DataHolder> {
    static void zza(DataHolder dataHolder, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, dataHolder.zzsU(), false);
        zzb.zza(parcel, 2, dataHolder.zzsV(), i, false);
        zzb.zzc(parcel, 3, dataHolder.getStatusCode());
        zzb.zza(parcel, 4, dataHolder.zzsO(), false);
        zzb.zzc(parcel, 1000, dataHolder.getVersionCode());
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzag */
    public DataHolder createFromParcel(Parcel parcel) {
        int i = 0;
        Bundle bundle = null;
        int zzaq = zza.zzaq(parcel);
        CursorWindow[] cursorWindowArr = null;
        String[] strArr = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    strArr = zza.zzC(parcel, zzap);
                    break;
                case 2:
                    cursorWindowArr = (CursorWindow[]) zza.zzb(parcel, zzap, CursorWindow.CREATOR);
                    break;
                case 3:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 4:
                    bundle = zza.zzs(parcel, zzap);
                    break;
                case 1000:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() != zzaq) {
            throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
        }
        DataHolder dataHolder = new DataHolder(i2, strArr, cursorWindowArr, i, bundle);
        dataHolder.zzsT();
        return dataHolder;
    }

    /* renamed from: zzbS */
    public DataHolder[] newArray(int i) {
        return new DataHolder[i];
    }
}
