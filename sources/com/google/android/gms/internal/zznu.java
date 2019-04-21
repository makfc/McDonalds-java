package com.google.android.gms.internal;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

public abstract class zznu extends zzoq implements OnCancelListener {
    protected boolean mStarted;
    protected boolean zzalQ;
    private ConnectionResult zzalR;
    private int zzalS;
    private final Handler zzalT;
    protected final GoogleApiAvailability zzaln;

    private class zza implements Runnable {
        private zza() {
        }

        @MainThread
        public void run() {
            if (!zznu.this.mStarted) {
                return;
            }
            if (zznu.this.zzalR.hasResolution()) {
                zznu.this.zzaop.startActivityForResult(GoogleApiActivity.zzb(zznu.this.getActivity(), zznu.this.zzalR.getResolution(), zznu.this.zzalS, false), 1);
            } else if (zznu.this.zzaln.isUserResolvableError(zznu.this.zzalR.getErrorCode())) {
                zznu.this.zzaln.zza(zznu.this.getActivity(), zznu.this.zzaop, zznu.this.zzalR.getErrorCode(), 2, zznu.this);
            } else if (zznu.this.zzalR.getErrorCode() == 18) {
                final Dialog zza = zznu.this.zzaln.zza(zznu.this.getActivity(), zznu.this);
                zznu.this.zzaln.zza(zznu.this.getActivity().getApplicationContext(), new com.google.android.gms.internal.zzol.zza() {
                    public void zzrG() {
                        zznu.this.zzrF();
                        if (zza.isShowing()) {
                            zza.dismiss();
                        }
                    }
                });
            } else {
                zznu.this.zza(zznu.this.zzalR, zznu.this.zzalS);
            }
        }
    }

    protected zznu(zzor zzor) {
        this(zzor, GoogleApiAvailability.getInstance());
    }

    zznu(zzor zzor, GoogleApiAvailability googleApiAvailability) {
        super(zzor);
        this.zzalS = -1;
        this.zzalT = new Handler(Looper.getMainLooper());
        this.zzaln = googleApiAvailability;
    }

    public void onActivityResult(int r6, int r7, android.content.Intent r8) {
        /*
        r5 = this;
        r4 = 18;
        r2 = 13;
        r0 = 1;
        r1 = 0;
        switch(r6) {
            case 1: goto L_0x0027;
            case 2: goto L_0x0010;
            default: goto L_0x0009;
        };
    L_0x0009:
        r0 = r1;
    L_0x000a:
        if (r0 == 0) goto L_0x003d;
    L_0x000c:
        r5.zzrF();
    L_0x000f:
        return;
    L_0x0010:
        r2 = r5.zzaln;
        r3 = r5.getActivity();
        r2 = r2.isGooglePlayServicesAvailable(r3);
        if (r2 != 0) goto L_0x0047;
    L_0x001c:
        r1 = r5.zzalR;
        r1 = r1.getErrorCode();
        if (r1 != r4) goto L_0x000a;
    L_0x0024:
        if (r2 != r4) goto L_0x000a;
    L_0x0026:
        goto L_0x000f;
    L_0x0027:
        r3 = -1;
        if (r7 == r3) goto L_0x000a;
    L_0x002a:
        if (r7 != 0) goto L_0x0009;
    L_0x002c:
        if (r8 == 0) goto L_0x0045;
    L_0x002e:
        r0 = "<<ResolutionFailureErrorDetail>>";
        r0 = r8.getIntExtra(r0, r2);
    L_0x0034:
        r2 = new com.google.android.gms.common.ConnectionResult;
        r3 = 0;
        r2.<init>(r0, r3);
        r5.zzalR = r2;
        goto L_0x0009;
    L_0x003d:
        r0 = r5.zzalR;
        r1 = r5.zzalS;
        r5.zza(r0, r1);
        goto L_0x000f;
    L_0x0045:
        r0 = r2;
        goto L_0x0034;
    L_0x0047:
        r0 = r1;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zznu.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onCancel(DialogInterface dialogInterface) {
        zza(new ConnectionResult(13, null), this.zzalS);
        zzrF();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zzalQ = bundle.getBoolean("resolving_error", false);
            if (this.zzalQ) {
                this.zzalS = bundle.getInt("failed_client_id", -1);
                this.zzalR = new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution"));
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("resolving_error", this.zzalQ);
        if (this.zzalQ) {
            bundle.putInt("failed_client_id", this.zzalS);
            bundle.putInt("failed_status", this.zzalR.getErrorCode());
            bundle.putParcelable("failed_resolution", this.zzalR.getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }

    public abstract void zza(ConnectionResult connectionResult, int i);

    public void zzb(ConnectionResult connectionResult, int i) {
        if (!this.zzalQ) {
            this.zzalQ = true;
            this.zzalS = i;
            this.zzalR = connectionResult;
            this.zzalT.post(new zza());
        }
    }

    public abstract void zzrA();

    /* Access modifiers changed, original: protected */
    public void zzrF() {
        this.zzalS = -1;
        this.zzalQ = false;
        this.zzalR = null;
        zzrA();
    }
}
