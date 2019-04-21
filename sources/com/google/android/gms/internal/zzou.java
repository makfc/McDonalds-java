package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Message;
import com.google.android.gms.common.internal.zzaa;

public final class zzou<L> {
    private volatile L mListener;

    public interface zzb<L> {
        void zzrV();

        void zzt(L l);
    }

    private final class zza extends Handler {
        final /* synthetic */ zzou zzaow;

        public void handleMessage(Message message) {
            boolean z = true;
            if (message.what != 1) {
                z = false;
            }
            zzaa.zzaj(z);
            this.zzaow.zzb((zzb) message.obj);
        }
    }

    public void clear() {
        this.mListener = null;
    }

    /* Access modifiers changed, original: 0000 */
    public void zzb(zzb<? super L> zzb) {
        Object obj = this.mListener;
        if (obj == null) {
            zzb.zzrV();
            return;
        }
        try {
            zzb.zzt(obj);
        } catch (RuntimeException e) {
            zzb.zzrV();
            throw e;
        }
    }
}
