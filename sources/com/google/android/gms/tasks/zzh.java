package com.google.android.gms.tasks;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzoq;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.Executor;

final class zzh<TResult> extends Task<TResult> {
    private boolean zzbwA;
    private TResult zzbwB;
    private Exception zzbwC;
    private final zzg<TResult> zzbwz = new zzg();
    private final Object zzpp = new Object();

    private static class zza extends zzoq {
        private final List<WeakReference<zzf<?>>> mListeners;

        @MainThread
        public void onStop() {
            synchronized (this.mListeners) {
                for (WeakReference weakReference : this.mListeners) {
                    zzf zzf = (zzf) weakReference.get();
                    if (zzf != null) {
                        zzf.cancel();
                    }
                }
                this.mListeners.clear();
            }
        }
    }

    zzh() {
    }

    private void zzMN() {
        zzaa.zza(this.zzbwA, "Task is not yet complete");
    }

    private void zzMO() {
        zzaa.zza(!this.zzbwA, "Task is already complete");
    }

    private void zzMP() {
        synchronized (this.zzpp) {
            if (this.zzbwA) {
                this.zzbwz.zza((Task) this);
                return;
            }
        }
    }

    @NonNull
    public Task<TResult> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        this.zzbwz.zza(new zzd(executor, onFailureListener));
        zzMP();
        return this;
    }

    @NonNull
    public Task<TResult> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.zzbwz.zza(new zze(executor, onSuccessListener));
        zzMP();
        return this;
    }

    @Nullable
    public Exception getException() {
        Exception exception;
        synchronized (this.zzpp) {
            exception = this.zzbwC;
        }
        return exception;
    }

    public TResult getResult() {
        Object obj;
        synchronized (this.zzpp) {
            zzMN();
            if (this.zzbwC != null) {
                throw new RuntimeExecutionException(this.zzbwC);
            }
            obj = this.zzbwB;
        }
        return obj;
    }

    public boolean isSuccessful() {
        boolean z;
        synchronized (this.zzpp) {
            z = this.zzbwA && this.zzbwC == null;
        }
        return z;
    }

    public void setException(@NonNull Exception exception) {
        zzaa.zzb((Object) exception, (Object) "Exception must not be null");
        synchronized (this.zzpp) {
            zzMO();
            this.zzbwA = true;
            this.zzbwC = exception;
        }
        this.zzbwz.zza((Task) this);
    }

    public void setResult(TResult tResult) {
        synchronized (this.zzpp) {
            zzMO();
            this.zzbwA = true;
            this.zzbwB = tResult;
        }
        this.zzbwz.zza((Task) this);
    }
}
