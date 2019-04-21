package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import com.newrelic.agent.android.analytics.AnalyticAttribute;

public final class Status extends AbstractSafeParcelable implements Result {
    public static final Creator<Status> CREATOR = new zzf();
    public static final Status zzalA = new Status(16);
    public static final Status zzalB = new Status(17);
    public static final Status zzalw = new Status(0);
    public static final Status zzalx = new Status(14);
    public static final Status zzaly = new Status(8);
    public static final Status zzalz = new Status(15);
    private final PendingIntent mPendingIntent;
    private final int mVersionCode;
    private final int zzahG;
    private final String zzakk;

    public Status(int i) {
        this(i, null);
    }

    Status(int i, int i2, String str, PendingIntent pendingIntent) {
        this.mVersionCode = i;
        this.zzahG = i2;
        this.zzakk = str;
        this.mPendingIntent = pendingIntent;
    }

    public Status(int i, String str) {
        this(1, i, str, null);
    }

    public Status(int i, String str, PendingIntent pendingIntent) {
        this(1, i, str, pendingIntent);
    }

    private String zzry() {
        return this.zzakk != null ? this.zzakk : CommonStatusCodes.getStatusCodeString(this.zzahG);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.mVersionCode == status.mVersionCode && this.zzahG == status.zzahG && zzz.equal(this.zzakk, status.zzakk) && zzz.equal(this.mPendingIntent, status.mPendingIntent);
    }

    public Status getStatus() {
        return this;
    }

    public int getStatusCode() {
        return this.zzahG;
    }

    @Nullable
    public String getStatusMessage() {
        return this.zzakk;
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzz.hashCode(Integer.valueOf(this.mVersionCode), Integer.valueOf(this.zzahG), this.zzakk, this.mPendingIntent);
    }

    public boolean isCanceled() {
        return this.zzahG == 16;
    }

    public boolean isSuccess() {
        return this.zzahG <= 0;
    }

    public String toString() {
        return zzz.zzy(this).zzg(AnalyticAttribute.STATUS_CODE_ATTRIBUTE, zzry()).zzg("resolution", this.mPendingIntent).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }

    /* Access modifiers changed, original: 0000 */
    public PendingIntent zzrx() {
        return this.mPendingIntent;
    }
}
