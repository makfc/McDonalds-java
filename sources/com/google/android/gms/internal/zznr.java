package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzaa;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zznr extends zznu {
    private final SparseArray<zza> zzalH = new SparseArray();

    private class zza implements OnConnectionFailedListener {
        public final int zzalI;
        public final GoogleApiClient zzalJ;
        public final OnConnectionFailedListener zzalK;

        public zza(int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
            this.zzalI = i;
            this.zzalJ = googleApiClient;
            this.zzalK = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.append(str).append("GoogleApiClient #").print(this.zzalI);
            printWriter.println(":");
            this.zzalJ.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            String valueOf = String.valueOf(connectionResult);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 27).append("beginFailureResolution for ").append(valueOf).toString());
            zznr.this.zzb(connectionResult, this.zzalI);
        }

        public void zzrB() {
            this.zzalJ.unregisterConnectionFailedListener(this);
            this.zzalJ.disconnect();
        }
    }

    private zznr(zzor zzor) {
        super(zzor);
        this.zzaop.zza("AutoManageHelper", (zzoq) this);
    }

    public static zznr zza(zzop zzop) {
        zzor zzc = zzoq.zzc(zzop);
        zznr zznr = (zznr) zzc.zza("AutoManageHelper", zznr.class);
        return zznr != null ? zznr : new zznr(zzc);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.zzalH.size()) {
                ((zza) this.zzalH.valueAt(i2)).dump(str, fileDescriptor, printWriter, strArr);
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public void onStart() {
        super.onStart();
        boolean z = this.mStarted;
        String valueOf = String.valueOf(this.zzalH);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 14).append("onStart ").append(z).append(" ").append(valueOf).toString());
        if (!this.zzalQ) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.zzalH.size()) {
                    ((zza) this.zzalH.valueAt(i2)).zzalJ.connect();
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    public void onStop() {
        super.onStop();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.zzalH.size()) {
                ((zza) this.zzalH.valueAt(i2)).zzalJ.disconnect();
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    public void zza(int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
        zzaa.zzb((Object) googleApiClient, (Object) "GoogleApiClient instance cannot be null");
        zzaa.zza(this.zzalH.indexOfKey(i) < 0, "Already managing a GoogleApiClient with id " + i);
        Log.d("AutoManageHelper", "starting AutoManage for client " + i + " " + this.mStarted + " " + this.zzalQ);
        this.zzalH.put(i, new zza(i, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && !this.zzalQ) {
            String valueOf = String.valueOf(googleApiClient);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 11).append("connecting ").append(valueOf).toString());
            googleApiClient.connect();
        }
    }

    /* Access modifiers changed, original: protected */
    public void zza(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zza zza = (zza) this.zzalH.get(i);
        if (zza != null) {
            zzbG(i);
            OnConnectionFailedListener onConnectionFailedListener = zza.zzalK;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    public void zzbG(int i) {
        zza zza = (zza) this.zzalH.get(i);
        this.zzalH.remove(i);
        if (zza != null) {
            zza.zzrB();
        }
    }

    /* Access modifiers changed, original: protected */
    public void zzrA() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.zzalH.size()) {
                ((zza) this.zzalH.valueAt(i2)).zzalJ.connect();
                i = i2 + 1;
            } else {
                return;
            }
        }
    }
}
