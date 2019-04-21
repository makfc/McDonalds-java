package com.google.android.gms.location;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import java.util.List;

public class ActivityRecognitionResult extends AbstractSafeParcelable {
    public static final ActivityRecognitionResultCreator CREATOR = new ActivityRecognitionResultCreator();
    Bundle extras;
    private final int mVersionCode;
    List<DetectedActivity> zzaUJ;
    long zzaUK;
    long zzaUL;
    int zzaUM;

    public ActivityRecognitionResult(int i, List<DetectedActivity> list, long j, long j2, int i2, Bundle bundle) {
        this.mVersionCode = i;
        this.zzaUJ = list;
        this.zzaUK = j;
        this.zzaUL = j2;
        this.zzaUM = i2;
        this.extras = bundle;
    }

    private static boolean zzc(Bundle bundle, Bundle bundle2) {
        if (bundle == null && bundle2 == null) {
            return true;
        }
        if ((bundle == null && bundle2 != null) || (bundle != null && bundle2 == null)) {
            return false;
        }
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        for (String str : bundle.keySet()) {
            if (!bundle2.containsKey(str)) {
                return false;
            }
            if (bundle.get(str) == null) {
                if (bundle2.get(str) != null) {
                    return false;
                }
            } else if (bundle.get(str) instanceof Bundle) {
                if (!zzc(bundle.getBundle(str), bundle2.getBundle(str))) {
                    return false;
                }
            } else if (!bundle.get(str).equals(bundle2.get(str))) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ActivityRecognitionResult activityRecognitionResult = (ActivityRecognitionResult) obj;
        return this.zzaUK == activityRecognitionResult.zzaUK && this.zzaUL == activityRecognitionResult.zzaUL && this.zzaUM == activityRecognitionResult.zzaUM && zzz.equal(this.zzaUJ, activityRecognitionResult.zzaUJ) && zzc(this.extras, activityRecognitionResult.extras);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzz.hashCode(Long.valueOf(this.zzaUK), Long.valueOf(this.zzaUL), Integer.valueOf(this.zzaUM), this.zzaUJ, this.extras);
    }

    public String toString() {
        String valueOf = String.valueOf(this.zzaUJ);
        long j = this.zzaUK;
        return new StringBuilder(String.valueOf(valueOf).length() + 124).append("ActivityRecognitionResult [probableActivities=").append(valueOf).append(", timeMillis=").append(j).append(", elapsedRealtimeMillis=").append(this.zzaUL).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        ActivityRecognitionResultCreator.zza(this, parcel, i);
    }
}
