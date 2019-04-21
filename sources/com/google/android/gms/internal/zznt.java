package com.google.android.gms.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzaa;
import java.util.concurrent.atomic.AtomicReference;

public class zznt {

    public interface zzb<R> {
        void setResult(R r);
    }

    public static abstract class zza<R extends Result, A extends com.google.android.gms.common.api.Api.zzb> extends zznv<R> implements zzb<R> {
        private final Api<?> zzakT;
        private final zzc<A> zzalO;
        private AtomicReference<zzb> zzalP = new AtomicReference();

        @Deprecated
        protected zza(zzc<A> zzc, GoogleApiClient googleApiClient) {
            super((GoogleApiClient) zzaa.zzb((Object) googleApiClient, (Object) "GoogleApiClient must not be null"));
            this.zzalO = (zzc) zzaa.zzz(zzc);
            this.zzakT = null;
        }

        protected zza(Api<?> api, GoogleApiClient googleApiClient) {
            super((GoogleApiClient) zzaa.zzb((Object) googleApiClient, (Object) "GoogleApiClient must not be null"));
            this.zzalO = api.zzre();
            this.zzakT = api;
        }

        private void zza(RemoteException remoteException) {
            zzx(new Status(8, remoteException.getLocalizedMessage(), null));
        }

        public /* synthetic */ void setResult(Object obj) {
            super.zzb((Result) obj);
        }

        public abstract void zza(A a) throws RemoteException;

        /* Access modifiers changed, original: protected */
        public void zza(R r) {
        }

        public void zza(zzb zzb) {
            this.zzalP.set(zzb);
        }

        public final void zzb(A a) throws DeadObjectException {
            try {
                zza((com.google.android.gms.common.api.Api.zzb) a);
            } catch (DeadObjectException e) {
                zza(e);
                throw e;
            } catch (RemoteException e2) {
                zza(e2);
            }
        }

        public void zzrD() {
            setResultCallback(null);
        }

        /* Access modifiers changed, original: protected */
        public void zzrE() {
            zzb zzb = (zzb) this.zzalP.getAndSet(null);
            if (zzb != null) {
                zzb.zzh(this);
            }
        }

        public final zzc<A> zzre() {
            return this.zzalO;
        }

        public final Api<?> zzrl() {
            return this.zzakT;
        }

        public final void zzx(Status status) {
            zzaa.zzb(!status.isSuccess(), (Object) "Failed result must not be success");
            Result zzc = zzc(status);
            zzb(zzc);
            zza(zzc);
        }
    }
}
