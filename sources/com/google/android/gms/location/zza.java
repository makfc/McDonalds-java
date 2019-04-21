package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.WorkSource;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza implements Creator<ActivityRecognitionRequest> {
    static void zza(ActivityRecognitionRequest activityRecognitionRequest, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zza(parcel, 1, activityRecognitionRequest.getIntervalMillis());
        zzb.zza(parcel, 2, activityRecognitionRequest.zzCm());
        zzb.zza(parcel, 3, activityRecognitionRequest.zzCn(), i, false);
        zzb.zza(parcel, 4, activityRecognitionRequest.getTag(), false);
        zzb.zza(parcel, 5, activityRecognitionRequest.zzCo(), false);
        zzb.zza(parcel, 6, activityRecognitionRequest.zzCp());
        zzb.zza(parcel, 7, activityRecognitionRequest.getAccountName(), false);
        zzb.zzc(parcel, 1000, activityRecognitionRequest.getVersionCode());
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzeT */
    public ActivityRecognitionRequest createFromParcel(Parcel parcel) {
        boolean z = false;
        String str = null;
        int zzaq = com.google.android.gms.common.internal.safeparcel.zza.zzaq(parcel);
        long j = 0;
        int[] iArr = null;
        String str2 = null;
        WorkSource workSource = null;
        boolean z2 = false;
        int i = 0;
        while (parcel.dataPosition() < zzaq) {
            int zzap = com.google.android.gms.common.internal.safeparcel.zza.zzap(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzcj(zzap)) {
                case 1:
                    j = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzap);
                    break;
                case 2:
                    z2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzap);
                    break;
                case 3:
                    workSource = (WorkSource) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzap, WorkSource.CREATOR);
                    break;
                case 4:
                    str2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzap);
                    break;
                case 5:
                    iArr = com.google.android.gms.common.internal.safeparcel.zza.zzw(parcel, zzap);
                    break;
                case 6:
                    z = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzap);
                    break;
                case 7:
                    str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzap);
                    break;
                case 1000:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzap);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new ActivityRecognitionRequest(i, j, z2, workSource, str2, iArr, z, str);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzhP */
    public ActivityRecognitionRequest[] newArray(int i) {
        return new ActivityRecognitionRequest[i];
    }
}
