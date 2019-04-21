package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.location.internal.ParcelableGeofence;
import java.util.ArrayList;
import java.util.List;

public class GeofencingRequest extends AbstractSafeParcelable {
    public static final Creator<GeofencingRequest> CREATOR = new zzb();
    private final int mVersionCode;
    private final List<ParcelableGeofence> zzaVd;
    private final int zzaVe;

    public static final class Builder {
        private final List<ParcelableGeofence> zzaVd = new ArrayList();
        private int zzaVe = 5;
    }

    GeofencingRequest(int i, List<ParcelableGeofence> list, int i2) {
        this.mVersionCode = i;
        this.zzaVd = list;
        this.zzaVe = i2;
    }

    public int getInitialTrigger() {
        return this.zzaVe;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public List<ParcelableGeofence> zzCq() {
        return this.zzaVd;
    }
}
