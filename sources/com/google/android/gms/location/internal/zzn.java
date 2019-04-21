package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzn implements Creator<LocationRequestUpdateData> {
    static void zza(LocationRequestUpdateData locationRequestUpdateData, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, locationRequestUpdateData.zzaWh);
        zzb.zza(parcel, 2, locationRequestUpdateData.zzaWi, i, false);
        zzb.zza(parcel, 3, locationRequestUpdateData.zzCy(), false);
        zzb.zza(parcel, 4, locationRequestUpdateData.mPendingIntent, i, false);
        zzb.zza(parcel, 5, locationRequestUpdateData.zzCz(), false);
        zzb.zza(parcel, 6, locationRequestUpdateData.zzCA(), false);
        zzb.zzc(parcel, 1000, locationRequestUpdateData.getVersionCode());
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzfd */
    public LocationRequestUpdateData createFromParcel(Parcel parcel) {
        IBinder iBinder = null;
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        int i2 = 1;
        IBinder iBinder2 = null;
        PendingIntent pendingIntent = null;
        IBinder iBinder3 = null;
        LocationRequestInternal locationRequestInternal = null;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    locationRequestInternal = (LocationRequestInternal) zza.zza(parcel, zzap, LocationRequestInternal.CREATOR);
                    break;
                case 3:
                    iBinder3 = zza.zzr(parcel, zzap);
                    break;
                case 4:
                    pendingIntent = (PendingIntent) zza.zza(parcel, zzap, PendingIntent.CREATOR);
                    break;
                case 5:
                    iBinder2 = zza.zzr(parcel, zzap);
                    break;
                case 6:
                    iBinder = zza.zzr(parcel, zzap);
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
            return new LocationRequestUpdateData(i, i2, locationRequestInternal, iBinder3, pendingIntent, iBinder2, iBinder);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzih */
    public LocationRequestUpdateData[] newArray(int i) {
        return new LocationRequestUpdateData[i];
    }
}
