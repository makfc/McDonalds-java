package com.google.android.gms.location.places;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.newrelic.agent.android.agentdata.HexAttributes;
import java.util.concurrent.TimeUnit;

public final class PlaceRequest extends AbstractSafeParcelable {
    public static final Creator<PlaceRequest> CREATOR = new zzk();
    static final long zzaWW = TimeUnit.SECONDS.toMillis(20);
    static final long zzaWX = TimeUnit.MINUTES.toMillis(5);
    static final long zzaWY = TimeUnit.MINUTES.toMillis(40);
    static final long zzaWZ = TimeUnit.HOURS.toMillis(1);
    static final long zzaXa = zzaWX;
    private final int mPriority;
    final int mVersionCode;
    private final long zzaUT;
    private final long zzaVo;
    private final PlaceFilter zzaXb;

    public PlaceRequest(int i, PlaceFilter placeFilter, long j, int i2, long j2) {
        this.mVersionCode = i;
        this.zzaXb = placeFilter;
        this.zzaVo = j;
        this.mPriority = i2;
        this.zzaUT = j2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceRequest)) {
            return false;
        }
        PlaceRequest placeRequest = (PlaceRequest) obj;
        return zzz.equal(this.zzaXb, placeRequest.zzaXb) && this.zzaVo == placeRequest.zzaVo && this.mPriority == placeRequest.mPriority && this.zzaUT == placeRequest.zzaUT;
    }

    public long getExpirationTime() {
        return this.zzaUT;
    }

    public long getInterval() {
        return this.zzaVo;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public int hashCode() {
        return zzz.hashCode(this.zzaXb, Long.valueOf(this.zzaVo), Integer.valueOf(this.mPriority), Long.valueOf(this.zzaUT));
    }

    @SuppressLint({"DefaultLocale"})
    public String toString() {
        return zzz.zzy(this).zzg(Parameters.FILTER, this.zzaXb).zzg("interval", Long.valueOf(this.zzaVo)).zzg(HexAttributes.HEX_ATTR_THREAD_PRI, Integer.valueOf(this.mPriority)).zzg("expireAt", Long.valueOf(this.zzaUT)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzk.zza(this, parcel, i);
    }

    public PlaceFilter zzCH() {
        return this.zzaXb;
    }
}
