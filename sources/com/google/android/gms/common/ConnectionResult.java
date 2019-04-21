package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import com.newrelic.agent.android.agentdata.HexAttributes;
import com.newrelic.agent.android.analytics.AnalyticAttribute;

public final class ConnectionResult extends AbstractSafeParcelable {
    public static final Creator<ConnectionResult> CREATOR = new zzb();
    public static final ConnectionResult zzakj = new ConnectionResult(0);
    private final PendingIntent mPendingIntent;
    final int mVersionCode;
    private final int zzahG;
    private final String zzakk;

    public ConnectionResult(int i) {
        this(i, null, null);
    }

    ConnectionResult(int i, int i2, PendingIntent pendingIntent, String str) {
        this.mVersionCode = i;
        this.zzahG = i2;
        this.mPendingIntent = pendingIntent;
        this.zzakk = str;
    }

    public ConnectionResult(int i, PendingIntent pendingIntent) {
        this(i, pendingIntent, null);
    }

    public ConnectionResult(int i, PendingIntent pendingIntent, String str) {
        this(1, i, pendingIntent, str);
    }

    static String getStatusString(int i) {
        switch (i) {
            case -1:
                return "UNKNOWN";
            case 0:
                return "SUCCESS";
            case 1:
                return "SERVICE_MISSING";
            case 2:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case 3:
                return "SERVICE_DISABLED";
            case 4:
                return "SIGN_IN_REQUIRED";
            case 5:
                return "INVALID_ACCOUNT";
            case 6:
                return "RESOLUTION_REQUIRED";
            case 7:
                return "NETWORK_ERROR";
            case 8:
                return "INTERNAL_ERROR";
            case 9:
                return "SERVICE_INVALID";
            case 10:
                return "DEVELOPER_ERROR";
            case 11:
                return "LICENSE_CHECK_FAILED";
            case 13:
                return "CANCELED";
            case 14:
                return "TIMEOUT";
            case 15:
                return "INTERRUPTED";
            case 16:
                return "API_UNAVAILABLE";
            case 17:
                return "SIGN_IN_FAILED";
            case 18:
                return "SERVICE_UPDATING";
            case 19:
                return "SERVICE_MISSING_PERMISSION";
            case 20:
                return "RESTRICTED_PROFILE";
            case 21:
                return "API_VERSION_UPDATE_REQUIRED";
            case 42:
                return "UPDATE_ANDROID_WEAR";
            case 99:
                return "UNFINISHED";
            case 1500:
                return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
            default:
                return "UNKNOWN_ERROR_CODE(" + i + ")";
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConnectionResult)) {
            return false;
        }
        ConnectionResult connectionResult = (ConnectionResult) obj;
        return this.zzahG == connectionResult.zzahG && zzz.equal(this.mPendingIntent, connectionResult.mPendingIntent) && zzz.equal(this.zzakk, connectionResult.zzakk);
    }

    public int getErrorCode() {
        return this.zzahG;
    }

    @Nullable
    public String getErrorMessage() {
        return this.zzakk;
    }

    @Nullable
    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }

    public boolean hasResolution() {
        return (this.zzahG == 0 || this.mPendingIntent == null) ? false : true;
    }

    public int hashCode() {
        return zzz.hashCode(Integer.valueOf(this.zzahG), this.mPendingIntent, this.zzakk);
    }

    public boolean isSuccess() {
        return this.zzahG == 0;
    }

    public String toString() {
        return zzz.zzy(this).zzg(AnalyticAttribute.STATUS_CODE_ATTRIBUTE, getStatusString(this.zzahG)).zzg("resolution", this.mPendingIntent).zzg(HexAttributes.HEX_ATTR_MESSAGE, this.zzakk).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }
}
