package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import com.newrelic.agent.android.agentdata.HexAttributes;

public final class NearbyAlertRequest extends AbstractSafeParcelable {
    public static final zze CREATOR = new zze();
    private int mPriority = 110;
    private final int mVersionCode;
    private final int zzaUS;
    private final int zzaWF;
    @Deprecated
    private final PlaceFilter zzaWG;
    private final NearbyAlertFilter zzaWH;
    private final boolean zzaWI;
    private final int zzaWJ;

    NearbyAlertRequest(int i, int i2, int i3, PlaceFilter placeFilter, NearbyAlertFilter nearbyAlertFilter, boolean z, int i4, int i5) {
        this.mVersionCode = i;
        this.zzaUS = i2;
        this.zzaWF = i3;
        if (nearbyAlertFilter != null) {
            this.zzaWH = nearbyAlertFilter;
        } else if (placeFilter == null) {
            this.zzaWH = null;
        } else if (placeFilter.getPlaceIds() != null && !placeFilter.getPlaceIds().isEmpty()) {
            this.zzaWH = NearbyAlertFilter.zzh(placeFilter.getPlaceIds());
        } else if (placeFilter.zzCL() == null || placeFilter.zzCL().isEmpty()) {
            this.zzaWH = null;
        } else {
            this.zzaWH = NearbyAlertFilter.zzi(placeFilter.zzCL());
        }
        this.zzaWG = null;
        this.zzaWI = z;
        this.zzaWJ = i4;
        this.mPriority = i5;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NearbyAlertRequest)) {
            return false;
        }
        NearbyAlertRequest nearbyAlertRequest = (NearbyAlertRequest) obj;
        return this.zzaUS == nearbyAlertRequest.zzaUS && this.zzaWF == nearbyAlertRequest.zzaWF && zzz.equal(this.zzaWH, nearbyAlertRequest.zzaWH) && this.mPriority == nearbyAlertRequest.mPriority;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzz.hashCode(Integer.valueOf(this.zzaUS), Integer.valueOf(this.zzaWF), this.zzaWH, Integer.valueOf(this.mPriority));
    }

    public String toString() {
        return zzz.zzy(this).zzg("transitionTypes", Integer.valueOf(this.zzaUS)).zzg("loiteringTimeMillis", Integer.valueOf(this.zzaWF)).zzg("nearbyAlertFilter", this.zzaWH).zzg(HexAttributes.HEX_ATTR_THREAD_PRI, Integer.valueOf(this.mPriority)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }

    public int zzCC() {
        return this.zzaUS;
    }

    public int zzCG() {
        return this.zzaWF;
    }

    @Deprecated
    public PlaceFilter zzCH() {
        return null;
    }

    public NearbyAlertFilter zzCI() {
        return this.zzaWH;
    }

    public boolean zzCJ() {
        return this.zzaWI;
    }

    public int zzCK() {
        return this.zzaWJ;
    }
}
