package com.google.android.gms.location.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zznt.zzb;
import com.google.android.gms.location.LocationServices.zza;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.SettingsApi;

public class zzq implements SettingsApi {

    /* renamed from: com.google.android.gms.location.internal.zzq$1 */
    class C23061 extends zza<LocationSettingsResult> {
        final /* synthetic */ LocationSettingsRequest zzaWn;
        final /* synthetic */ String zzaWo;

        /* Access modifiers changed, original: protected */
        public void zza(zzl zzl) throws RemoteException {
            zzl.zza(this.zzaWn, (zzb) this, this.zzaWo);
        }

        /* renamed from: zzbe */
        public LocationSettingsResult zzc(Status status) {
            return new LocationSettingsResult(status);
        }
    }
}
