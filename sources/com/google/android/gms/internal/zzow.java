package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

public class zzow extends zznu {
    private TaskCompletionSource<Void> zzalF;

    public void onStop() {
        super.onStop();
        this.zzalF.setException(new CancellationException());
    }

    /* Access modifiers changed, original: protected */
    public void zza(ConnectionResult connectionResult, int i) {
        this.zzalF.setException(new Exception());
    }

    public void zzk(ConnectionResult connectionResult) {
        zzb(connectionResult, 0);
    }

    /* Access modifiers changed, original: protected */
    public void zzrA() {
        int isGooglePlayServicesAvailable = this.zzaln.isGooglePlayServicesAvailable(this.zzaop.zzsF());
        if (isGooglePlayServicesAvailable == 0) {
            this.zzalF.setResult(null);
        } else {
            zzk(new ConnectionResult(isGooglePlayServicesAvailable, null));
        }
    }
}
