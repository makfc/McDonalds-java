package com.google.android.gms.common.api;

import com.google.android.gms.internal.zznv;

public final class PendingResults {

    private static final class zza<R extends Result> extends zznv<R> {
        private final R zzalr;

        /* Access modifiers changed, original: protected */
        public R zzc(Status status) {
            if (status.getStatusCode() == this.zzalr.getStatus().getStatusCode()) {
                return this.zzalr;
            }
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    private static final class zzb<R extends Result> extends zznv<R> {
        private final R zzals;

        /* Access modifiers changed, original: protected */
        public R zzc(Status status) {
            return this.zzals;
        }
    }

    private static final class zzc<R extends Result> extends zznv<R> {
        /* Access modifiers changed, original: protected */
        public R zzc(Status status) {
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    private PendingResults() {
    }
}
