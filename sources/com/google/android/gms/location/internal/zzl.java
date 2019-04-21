package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationStatusCodes;
import java.util.List;

public class zzl extends zzb {
    private final zzk zzaWc;

    private static final class zza extends com.google.android.gms.location.internal.zzh.zza {
        private com.google.android.gms.internal.zznt.zzb<Status> zzaWd;

        public zza(com.google.android.gms.internal.zznt.zzb<Status> zzb) {
            this.zzaWd = zzb;
        }

        public void zza(int i, PendingIntent pendingIntent) {
            Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByPendingIntentResult");
        }

        public void zza(int i, String[] strArr) {
            if (this.zzaWd == null) {
                Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
                return;
            }
            this.zzaWd.setResult(LocationStatusCodes.zzic(LocationStatusCodes.zzib(i)));
            this.zzaWd = null;
        }

        public void zzb(int i, String[] strArr) {
            Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByRequestIdsResult");
        }
    }

    private static final class zzb extends com.google.android.gms.location.internal.zzh.zza {
        private com.google.android.gms.internal.zznt.zzb<Status> zzaWd;

        public zzb(com.google.android.gms.internal.zznt.zzb<Status> zzb) {
            this.zzaWd = zzb;
        }

        private void zzif(int i) {
            if (this.zzaWd == null) {
                Log.wtf("LocationClientImpl", "onRemoveGeofencesResult called multiple times");
                return;
            }
            this.zzaWd.setResult(LocationStatusCodes.zzic(LocationStatusCodes.zzib(i)));
            this.zzaWd = null;
        }

        public void zza(int i, PendingIntent pendingIntent) {
            zzif(i);
        }

        public void zza(int i, String[] strArr) {
            Log.wtf("LocationClientImpl", "Unexpected call to onAddGeofencesResult");
        }

        public void zzb(int i, String[] strArr) {
            zzif(i);
        }
    }

    private static final class zzc extends com.google.android.gms.location.internal.zzj.zza {
        private com.google.android.gms.internal.zznt.zzb<LocationSettingsResult> zzaWd;

        public zzc(com.google.android.gms.internal.zznt.zzb<LocationSettingsResult> zzb) {
            zzaa.zzb(zzb != null, (Object) "listener can't be null.");
            this.zzaWd = zzb;
        }

        public void zza(LocationSettingsResult locationSettingsResult) throws RemoteException {
            this.zzaWd.setResult(locationSettingsResult);
            this.zzaWd = null;
        }
    }

    public zzl(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str) {
        this(context, looper, connectionCallbacks, onConnectionFailedListener, str, zzg.zzau(context));
    }

    public zzl(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str, zzg zzg) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, str, zzg);
        this.zzaWc = new zzk(context, this.zzaVJ);
    }

    public void disconnect() {
        synchronized (this.zzaWc) {
            if (isConnected()) {
                try {
                    this.zzaWc.removeAllListeners();
                    this.zzaWc.zzCx();
                } catch (Exception e) {
                    Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", e);
                }
            }
            super.disconnect();
        }
    }

    public Location getLastLocation() {
        return this.zzaWc.getLastLocation();
    }

    public void zza(long j, PendingIntent pendingIntent) throws RemoteException {
        zztl();
        zzaa.zzz(pendingIntent);
        zzaa.zzb(j >= 0, (Object) "detectionIntervalMillis must be >= 0");
        ((zzi) zztm()).zza(j, true, pendingIntent);
    }

    public void zza(PendingIntent pendingIntent) throws RemoteException {
        zztl();
        zzaa.zzz(pendingIntent);
        ((zzi) zztm()).zza(pendingIntent);
    }

    public void zza(PendingIntent pendingIntent, com.google.android.gms.internal.zznt.zzb<Status> zzb) throws RemoteException {
        zztl();
        zzaa.zzb((Object) pendingIntent, (Object) "PendingIntent must be specified.");
        zzaa.zzb((Object) zzb, (Object) "ResultHolder not provided.");
        ((zzi) zztm()).zza(pendingIntent, new zzb(zzb), getContext().getPackageName());
    }

    public void zza(PendingIntent pendingIntent, zzg zzg) throws RemoteException {
        this.zzaWc.zza(pendingIntent, zzg);
    }

    public void zza(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, com.google.android.gms.internal.zznt.zzb<Status> zzb) throws RemoteException {
        zztl();
        zzaa.zzb((Object) geofencingRequest, (Object) "geofencingRequest can't be null.");
        zzaa.zzb((Object) pendingIntent, (Object) "PendingIntent must be specified.");
        zzaa.zzb((Object) zzb, (Object) "ResultHolder not provided.");
        ((zzi) zztm()).zza(geofencingRequest, pendingIntent, new zza(zzb));
    }

    public void zza(LocationCallback locationCallback, zzg zzg) throws RemoteException {
        this.zzaWc.zza(locationCallback, zzg);
    }

    public void zza(LocationListener locationListener, zzg zzg) throws RemoteException {
        this.zzaWc.zza(locationListener, zzg);
    }

    public void zza(LocationRequest locationRequest, PendingIntent pendingIntent, zzg zzg) throws RemoteException {
        this.zzaWc.zza(locationRequest, pendingIntent, zzg);
    }

    public void zza(LocationRequest locationRequest, LocationListener locationListener, Looper looper, zzg zzg) throws RemoteException {
        synchronized (this.zzaWc) {
            this.zzaWc.zza(locationRequest, locationListener, looper, zzg);
        }
    }

    public void zza(LocationSettingsRequest locationSettingsRequest, com.google.android.gms.internal.zznt.zzb<LocationSettingsResult> zzb, String str) throws RemoteException {
        boolean z = true;
        zztl();
        zzaa.zzb(locationSettingsRequest != null, (Object) "locationSettingsRequest can't be null nor empty.");
        if (zzb == null) {
            z = false;
        }
        zzaa.zzb(z, (Object) "listener can't be null.");
        ((zzi) zztm()).zza(locationSettingsRequest, new zzc(zzb), str);
    }

    public void zza(LocationRequestInternal locationRequestInternal, LocationCallback locationCallback, Looper looper, zzg zzg) throws RemoteException {
        synchronized (this.zzaWc) {
            this.zzaWc.zza(locationRequestInternal, locationCallback, looper, zzg);
        }
    }

    public void zza(zzg zzg) throws RemoteException {
        this.zzaWc.zza(zzg);
    }

    public void zza(List<String> list, com.google.android.gms.internal.zznt.zzb<Status> zzb) throws RemoteException {
        zztl();
        boolean z = list != null && list.size() > 0;
        zzaa.zzb(z, (Object) "geofenceRequestIds can't be null nor empty.");
        zzaa.zzb((Object) zzb, (Object) "ResultHolder not provided.");
        ((zzi) zztm()).zza((String[]) list.toArray(new String[0]), new zzb(zzb), getContext().getPackageName());
    }

    public void zzat(boolean z) throws RemoteException {
        this.zzaWc.zzat(z);
    }

    public void zzc(Location location) throws RemoteException {
        this.zzaWc.zzc(location);
    }
}
