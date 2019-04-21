package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class DetectedActivityCreator implements Creator<DetectedActivity> {
    static void zza(DetectedActivity detectedActivity, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, detectedActivity.zzaUQ);
        zzb.zzc(parcel, 2, detectedActivity.zzaUR);
        zzb.zzc(parcel, 1000, detectedActivity.getVersionCode());
        zzb.zzJ(parcel, zzar);
    }

    public DetectedActivity createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaq = zza.zzaq(parcel);
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 1000:
                    i3 = zza.zzg(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new DetectedActivity(i3, i2, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    public DetectedActivity[] newArray(int i) {
        return new DetectedActivity[i];
    }
}
