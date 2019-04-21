package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class PeriodicTask extends Task {
    public static final Creator<PeriodicTask> CREATOR = new C21441();
    protected long mFlexInSeconds;
    protected long mIntervalInSeconds;

    /* renamed from: com.google.android.gms.gcm.PeriodicTask$1 */
    class C21441 implements Creator<PeriodicTask> {
        C21441() {
        }

        /* renamed from: zzeO */
        public PeriodicTask createFromParcel(Parcel parcel) {
            return new PeriodicTask(parcel, null);
        }

        /* renamed from: zzhJ */
        public PeriodicTask[] newArray(int i) {
            return new PeriodicTask[i];
        }
    }

    public static class Builder extends com.google.android.gms.gcm.Task.Builder {
        private long zzaTk;
        private long zzaTl;

        public Builder() {
            this.zzaTk = -1;
            this.zzaTl = -1;
            this.isPersisted = true;
        }
    }

    @Deprecated
    private PeriodicTask(Parcel parcel) {
        super(parcel);
        this.mIntervalInSeconds = -1;
        this.mFlexInSeconds = -1;
        this.mIntervalInSeconds = parcel.readLong();
        this.mFlexInSeconds = Math.min(parcel.readLong(), this.mIntervalInSeconds);
    }

    /* synthetic */ PeriodicTask(Parcel parcel, C21441 c21441) {
        this(parcel);
    }

    public long getFlex() {
        return this.mFlexInSeconds;
    }

    public long getPeriod() {
        return this.mIntervalInSeconds;
    }

    public String toString() {
        String valueOf = String.valueOf(super.toString());
        long period = getPeriod();
        return new StringBuilder(String.valueOf(valueOf).length() + 54).append(valueOf).append(" period=").append(period).append(" flex=").append(getFlex()).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.mIntervalInSeconds);
        parcel.writeLong(this.mFlexInSeconds);
    }
}
