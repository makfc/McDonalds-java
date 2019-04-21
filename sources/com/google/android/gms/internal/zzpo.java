package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class zzpo<R extends Result> extends com.google.android.gms.internal.zznt.zza<R, zzpp> {

    static abstract class zza extends zzpo<Status> {
        public zza(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* renamed from: zzb */
        public Status zzc(Status status) {
            return status;
        }
    }

    public zzpo(GoogleApiClient googleApiClient) {
        super(zzpl.API, googleApiClient);
    }
}
