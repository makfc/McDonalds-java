package com.google.android.gms.location;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class ActivityRecognitionResultCreator implements Creator<ActivityRecognitionResult> {
    static void zza(ActivityRecognitionResult activityRecognitionResult, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, activityRecognitionResult.zzaUJ, false);
        zzb.zza(parcel, 2, activityRecognitionResult.zzaUK);
        zzb.zza(parcel, 3, activityRecognitionResult.zzaUL);
        zzb.zzc(parcel, 4, activityRecognitionResult.zzaUM);
        zzb.zza(parcel, 5, activityRecognitionResult.extras, false);
        zzb.zzc(parcel, 1000, activityRecognitionResult.getVersionCode());
        zzb.zzJ(parcel, zzar);
    }

    public ActivityRecognitionResult createFromParcel(Parcel parcel) {
        long j = 0;
        Bundle bundle = null;
        int i = 0;
        int zzaq = zza.zzaq(parcel);
        long j2 = 0;
        List list = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    list = zza.zzc(parcel, zzap, DetectedActivity.CREATOR);
                    break;
                case 2:
                    j2 = zza.zzi(parcel, zzap);
                    break;
                case 3:
                    j = zza.zzi(parcel, zzap);
                    break;
                case 4:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 5:
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
        if (parcel.dataPosition() == zzaq) {
            return new ActivityRecognitionResult(i2, list, j2, j, i, bundle);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    public ActivityRecognitionResult[] newArray(int i) {
        return new ActivityRecognitionResult[i];
    }
}
