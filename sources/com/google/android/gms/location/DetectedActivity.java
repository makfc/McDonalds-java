package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import java.util.Comparator;

public class DetectedActivity extends AbstractSafeParcelable {
    public static final DetectedActivityCreator CREATOR = new DetectedActivityCreator();
    public static final Comparator<DetectedActivity> zzaUN = new C22841();
    public static final int[] zzaUO = new int[]{9, 10};
    public static final int[] zzaUP = new int[]{0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14};
    private final int mVersionCode;
    int zzaUQ;
    int zzaUR;

    /* renamed from: com.google.android.gms.location.DetectedActivity$1 */
    class C22841 implements Comparator<DetectedActivity> {
        C22841() {
        }

        /* renamed from: zza */
        public int compare(DetectedActivity detectedActivity, DetectedActivity detectedActivity2) {
            int compareTo = Integer.valueOf(detectedActivity2.getConfidence()).compareTo(Integer.valueOf(detectedActivity.getConfidence()));
            return compareTo == 0 ? Integer.valueOf(detectedActivity.getType()).compareTo(Integer.valueOf(detectedActivity2.getType())) : compareTo;
        }
    }

    public DetectedActivity(int i, int i2, int i3) {
        this.mVersionCode = i;
        this.zzaUQ = i2;
        this.zzaUR = i3;
    }

    private int zzhQ(int i) {
        return i > 15 ? 4 : i;
    }

    public static String zzhR(int i) {
        switch (i) {
            case 0:
                return "IN_VEHICLE";
            case 1:
                return "ON_BICYCLE";
            case 2:
                return "ON_FOOT";
            case 3:
                return "STILL";
            case 4:
                return "UNKNOWN";
            case 5:
                return "TILTING";
            case 7:
                return "WALKING";
            case 8:
                return "RUNNING";
            default:
                return Integer.toString(i);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DetectedActivity detectedActivity = (DetectedActivity) obj;
        return this.zzaUQ == detectedActivity.zzaUQ && this.zzaUR == detectedActivity.zzaUR;
    }

    public int getConfidence() {
        return this.zzaUR;
    }

    public int getType() {
        return zzhQ(this.zzaUQ);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzz.hashCode(Integer.valueOf(this.zzaUQ), Integer.valueOf(this.zzaUR));
    }

    public String toString() {
        String valueOf = String.valueOf(zzhR(getType()));
        return new StringBuilder(String.valueOf(valueOf).length() + 48).append("DetectedActivity [type=").append(valueOf).append(", confidence=").append(this.zzaUR).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        DetectedActivityCreator.zza(this, parcel, i);
    }
}
