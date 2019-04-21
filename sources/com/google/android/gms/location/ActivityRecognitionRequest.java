package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.WorkSource;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class ActivityRecognitionRequest extends AbstractSafeParcelable {
    public static final Creator<ActivityRecognitionRequest> CREATOR = new zza();
    @Nullable
    private String mTag;
    private final int mVersionCode;
    private long zzaUE;
    private boolean zzaUF;
    @Nullable
    private WorkSource zzaUG;
    @Nullable
    private int[] zzaUH;
    @Nullable
    private boolean zzaUI;
    @Nullable
    private String zzaaR;

    ActivityRecognitionRequest(int i, long j, boolean z, @Nullable WorkSource workSource, @Nullable String str, @Nullable int[] iArr, boolean z2, @Nullable String str2) {
        this.mVersionCode = i;
        this.zzaUE = j;
        this.zzaUF = z;
        this.zzaUG = workSource;
        this.mTag = str;
        this.zzaUH = iArr;
        this.zzaUI = z2;
        this.zzaaR = str2;
    }

    @Nullable
    public String getAccountName() {
        return this.zzaaR;
    }

    public long getIntervalMillis() {
        return this.zzaUE;
    }

    @Nullable
    public String getTag() {
        return this.mTag;
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public boolean zzCm() {
        return this.zzaUF;
    }

    @Nullable
    public WorkSource zzCn() {
        return this.zzaUG;
    }

    @Nullable
    public int[] zzCo() {
        return this.zzaUH;
    }

    public boolean zzCp() {
        return this.zzaUI;
    }
}
