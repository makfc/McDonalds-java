package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public final class Tasks {

    /* renamed from: com.google.android.gms.tasks.Tasks$1 */
    class C27481 implements Runnable {
        final /* synthetic */ Callable zzQq;
        final /* synthetic */ zzh zzbwD;

        public void run() {
            try {
                this.zzbwD.setResult(this.zzQq.call());
            } catch (Exception e) {
                this.zzbwD.setException(e);
            }
        }
    }

    interface zzb extends OnFailureListener, OnSuccessListener<Object> {
    }

    private static final class zza implements zzb {
        private final CountDownLatch zzqF = new CountDownLatch(1);

        private zza() {
        }

        public void onFailure(@NonNull Exception exception) {
            this.zzqF.countDown();
        }

        public void onSuccess(Object obj) {
            this.zzqF.countDown();
        }
    }

    private static final class zzc implements zzb {
        private Exception zzbwC;
        private final int zzbwE;
        private int zzbwF;
        private int zzbwG;
        private final zzh<Void> zzbwx;
        private final Object zzpp;

        private void zzMQ() {
            if (this.zzbwF + this.zzbwG != this.zzbwE) {
                return;
            }
            if (this.zzbwC == null) {
                this.zzbwx.setResult(null);
                return;
            }
            zzh zzh = this.zzbwx;
            int i = this.zzbwG;
            zzh.setException(new ExecutionException(i + " out of " + this.zzbwE + " underlying tasks failed", this.zzbwC));
        }

        public void onFailure(@NonNull Exception exception) {
            synchronized (this.zzpp) {
                this.zzbwG++;
                this.zzbwC = exception;
                zzMQ();
            }
        }

        public void onSuccess(Object obj) {
            synchronized (this.zzpp) {
                this.zzbwF++;
                zzMQ();
            }
        }
    }

    private Tasks() {
    }
}
