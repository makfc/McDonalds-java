package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzaa;
import java.lang.ref.WeakReference;

public class zzpe<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    private final Object zzalY;
    private final WeakReference<GoogleApiClient> zzama;
    private ResultTransform<? super R, ? extends Result> zzaoA;
    private zzpe<? extends Result> zzaoB;
    private volatile ResultCallbacks<? super R> zzaoC;
    private PendingResult<R> zzaoD;
    private Status zzaoE;
    private final zza zzaoF;
    private boolean zzaoG;

    private final class zza extends Handler {
        final /* synthetic */ zzpe zzaoI;

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    PendingResult pendingResult = (PendingResult) message.obj;
                    synchronized (this.zzaoI.zzalY) {
                        if (pendingResult == null) {
                            this.zzaoI.zzaoB.zzA(new Status(13, "Transform returned null"));
                        } else if (pendingResult instanceof zzoz) {
                            this.zzaoI.zzaoB.zzA(((zzoz) pendingResult).getStatus());
                        } else {
                            this.zzaoI.zzaoB.zza(pendingResult);
                        }
                    }
                    return;
                case 1:
                    RuntimeException runtimeException = (RuntimeException) message.obj;
                    String str = "TransformedResultImpl";
                    String str2 = "Runtime exception on the transformation worker thread: ";
                    String valueOf = String.valueOf(runtimeException.getMessage());
                    Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    throw runtimeException;
                default:
                    Log.e("TransformedResultImpl", "TransformationResultHandler received unknown message type: " + message.what);
                    return;
            }
        }
    }

    private void zzA(Status status) {
        synchronized (this.zzalY) {
            this.zzaoE = status;
            zzB(this.zzaoE);
        }
    }

    private void zzB(Status status) {
        synchronized (this.zzalY) {
            if (this.zzaoA != null) {
                Object onFailure = this.zzaoA.onFailure(status);
                zzaa.zzb(onFailure, (Object) "onFailure must not return null");
                this.zzaoB.zzA(onFailure);
            } else if (zzsK()) {
                this.zzaoC.onFailure(status);
            }
        }
    }

    private void zzd(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result);
                Log.w("TransformedResultImpl", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    private void zzsI() {
        if (this.zzaoA != null || this.zzaoC != null) {
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zzama.get();
            if (!(this.zzaoG || this.zzaoA == null || googleApiClient == null)) {
                googleApiClient.zza(this);
                this.zzaoG = true;
            }
            if (this.zzaoE != null) {
                zzB(this.zzaoE);
            } else if (this.zzaoD != null) {
                this.zzaoD.setResultCallback(this);
            }
        }
    }

    private boolean zzsK() {
        return (this.zzaoC == null || ((GoogleApiClient) this.zzama.get()) == null) ? false : true;
    }

    public void onResult(final R r) {
        synchronized (this.zzalY) {
            if (!r.getStatus().isSuccess()) {
                zzA(r.getStatus());
                zzd((Result) r);
            } else if (this.zzaoA != null) {
                zzoy.zzsp().submit(new Runnable() {
                    @WorkerThread
                    public void run() {
                        GoogleApiClient googleApiClient;
                        try {
                            zznv.zzalX.set(Boolean.valueOf(true));
                            zzpe.this.zzaoF.sendMessage(zzpe.this.zzaoF.obtainMessage(0, zzpe.this.zzaoA.onSuccess(r)));
                            zznv.zzalX.set(Boolean.valueOf(false));
                            zzpe.this.zzd(r);
                            googleApiClient = (GoogleApiClient) zzpe.this.zzama.get();
                            if (googleApiClient != null) {
                                googleApiClient.zzb(zzpe.this);
                            }
                        } catch (RuntimeException e) {
                            zzpe.this.zzaoF.sendMessage(zzpe.this.zzaoF.obtainMessage(1, e));
                            zznv.zzalX.set(Boolean.valueOf(false));
                            zzpe.this.zzd(r);
                            googleApiClient = (GoogleApiClient) zzpe.this.zzama.get();
                            if (googleApiClient != null) {
                                googleApiClient.zzb(zzpe.this);
                            }
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            zznv.zzalX.set(Boolean.valueOf(false));
                            zzpe.this.zzd(r);
                            googleApiClient = (GoogleApiClient) zzpe.this.zzama.get();
                            if (googleApiClient != null) {
                                googleApiClient.zzb(zzpe.this);
                            }
                        }
                    }
                });
            } else if (zzsK()) {
                this.zzaoC.onSuccess(r);
            }
        }
    }

    public void zza(PendingResult<?> pendingResult) {
        synchronized (this.zzalY) {
            this.zzaoD = pendingResult;
            zzsI();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zzsJ() {
        this.zzaoC = null;
    }
}
