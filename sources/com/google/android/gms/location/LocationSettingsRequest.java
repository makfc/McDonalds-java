package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class LocationSettingsRequest extends AbstractSafeParcelable {
    public static final Creator<LocationSettingsRequest> CREATOR = new zzg();
    private final int mVersionCode;
    private final List<LocationRequest> zzaHZ;
    private final boolean zzaVv;
    private final boolean zzaVw;

    public static final class Builder {
        private boolean zzaVv = false;
        private boolean zzaVw = false;
        private final ArrayList<LocationRequest> zzaVx = new ArrayList();
    }

    LocationSettingsRequest(int i, List<LocationRequest> list, boolean z, boolean z2) {
        this.mVersionCode = i;
        this.zzaHZ = list;
        this.zzaVv = z;
        this.zzaVw = z2;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }

    public boolean zzCs() {
        return this.zzaVv;
    }

    public boolean zzCt() {
        return this.zzaVw;
    }

    public List<LocationRequest> zzyj() {
        return Collections.unmodifiableList(this.zzaHZ);
    }
}
