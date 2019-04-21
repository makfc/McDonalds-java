package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.location.zzd;
import com.google.android.gms.location.zze;
import com.google.android.gms.location.zze.zza;

public class LocationRequestUpdateData extends AbstractSafeParcelable {
    public static final zzn CREATOR = new zzn();
    PendingIntent mPendingIntent;
    private final int mVersionCode;
    int zzaWh;
    LocationRequestInternal zzaWi;
    zze zzaWj;
    zzd zzaWk;
    zzg zzaWl;

    LocationRequestUpdateData(int i, int i2, LocationRequestInternal locationRequestInternal, IBinder iBinder, PendingIntent pendingIntent, IBinder iBinder2, IBinder iBinder3) {
        zzg zzg = null;
        this.mVersionCode = i;
        this.zzaWh = i2;
        this.zzaWi = locationRequestInternal;
        this.zzaWj = iBinder == null ? null : zza.zzcn(iBinder);
        this.mPendingIntent = pendingIntent;
        this.zzaWk = iBinder2 == null ? null : zzd.zza.zzcm(iBinder2);
        if (iBinder3 != null) {
            zzg = zzg.zza.zzcp(iBinder3);
        }
        this.zzaWl = zzg;
    }

    public static LocationRequestUpdateData zza(LocationRequestInternal locationRequestInternal, PendingIntent pendingIntent, @Nullable zzg zzg) {
        return new LocationRequestUpdateData(1, 1, locationRequestInternal, null, pendingIntent, null, zzg != null ? zzg.asBinder() : null);
    }

    public static LocationRequestUpdateData zza(LocationRequestInternal locationRequestInternal, zzd zzd, @Nullable zzg zzg) {
        return new LocationRequestUpdateData(1, 1, locationRequestInternal, null, null, zzd.asBinder(), zzg != null ? zzg.asBinder() : null);
    }

    public static LocationRequestUpdateData zza(LocationRequestInternal locationRequestInternal, zze zze, @Nullable zzg zzg) {
        return new LocationRequestUpdateData(1, 1, locationRequestInternal, zze.asBinder(), null, null, zzg != null ? zzg.asBinder() : null);
    }

    public static LocationRequestUpdateData zza(zzd zzd, @Nullable zzg zzg) {
        return new LocationRequestUpdateData(1, 2, null, null, null, zzd.asBinder(), zzg != null ? zzg.asBinder() : null);
    }

    public static LocationRequestUpdateData zza(zze zze, @Nullable zzg zzg) {
        return new LocationRequestUpdateData(1, 2, null, zze.asBinder(), null, null, zzg != null ? zzg.asBinder() : null);
    }

    public static LocationRequestUpdateData zzb(PendingIntent pendingIntent, @Nullable zzg zzg) {
        return new LocationRequestUpdateData(1, 2, null, null, pendingIntent, null, zzg != null ? zzg.asBinder() : null);
    }

    /* Access modifiers changed, original: 0000 */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzn.zza(this, parcel, i);
    }

    /* Access modifiers changed, original: 0000 */
    public IBinder zzCA() {
        return this.zzaWl == null ? null : this.zzaWl.asBinder();
    }

    /* Access modifiers changed, original: 0000 */
    public IBinder zzCy() {
        return this.zzaWj == null ? null : this.zzaWj.asBinder();
    }

    /* Access modifiers changed, original: 0000 */
    public IBinder zzCz() {
        return this.zzaWk == null ? null : this.zzaWk.asBinder();
    }
}
