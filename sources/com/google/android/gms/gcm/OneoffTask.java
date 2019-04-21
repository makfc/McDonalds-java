package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class OneoffTask extends Task {
    public static final Creator<OneoffTask> CREATOR = new C21421();
    private final long zzaTg;
    private final long zzaTh;

    /* renamed from: com.google.android.gms.gcm.OneoffTask$1 */
    class C21421 implements Creator<OneoffTask> {
        C21421() {
        }

        /* renamed from: zzeM */
        public OneoffTask createFromParcel(Parcel parcel) {
            return new OneoffTask(parcel, null);
        }

        /* renamed from: zzhH */
        public OneoffTask[] newArray(int i) {
            return new OneoffTask[i];
        }
    }

    public static class Builder extends com.google.android.gms.gcm.Task.Builder {
        private long zzaTi;
        private long zzaTj;

        public Builder() {
            this.zzaTi = -1;
            this.zzaTj = -1;
            this.isPersisted = false;
        }
    }

    @Deprecated
    private OneoffTask(Parcel parcel) {
        super(parcel);
        this.zzaTg = parcel.readLong();
        this.zzaTh = parcel.readLong();
    }

    /* synthetic */ OneoffTask(Parcel parcel, C21421 c21421) {
        this(parcel);
    }

    public long getWindowEnd() {
        return this.zzaTh;
    }

    public long getWindowStart() {
        return this.zzaTg;
    }

    public String toString() {
        String valueOf = String.valueOf(super.toString());
        long windowStart = getWindowStart();
        return new StringBuilder(String.valueOf(valueOf).length() + 64).append(valueOf).append(" windowStart=").append(windowStart).append(" windowEnd=").append(getWindowEnd()).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.zzaTg);
        parcel.writeLong(this.zzaTh);
    }
}
