package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class zzd implements FusedLocationProviderApi {

    private static abstract class zza extends com.google.android.gms.location.LocationServices.zza<Status> {
        public zza(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* renamed from: zzb */
        public Status zzc(Status status) {
            return status;
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd$10 */
    class C229210 extends zza {
        final /* synthetic */ PendingIntent zzaVG;

        /* Access modifiers changed, original: protected */
        public void zza(zzl zzl) throws RemoteException {
            zzl.zza(this.zzaVG, new zzb(this));
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd$2 */
    class C22942 extends zza {
        final /* synthetic */ LocationCallback zzaVO;

        /* Access modifiers changed, original: protected */
        public void zza(zzl zzl) throws RemoteException {
            zzl.zza(this.zzaVO, new zzb(this));
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd$3 */
    class C22953 extends zza {
        final /* synthetic */ boolean zzaVP;

        /* Access modifiers changed, original: protected */
        public void zza(zzl zzl) throws RemoteException {
            zzl.zzat(this.zzaVP);
            zzb(Status.zzalw);
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd$4 */
    class C22964 extends zza {
        final /* synthetic */ Location zzaVQ;

        /* Access modifiers changed, original: protected */
        public void zza(zzl zzl) throws RemoteException {
            zzl.zzc(this.zzaVQ);
            zzb(Status.zzalw);
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd$5 */
    class C22975 extends zza {
        /* Access modifiers changed, original: protected */
        public void zza(zzl zzl) throws RemoteException {
            zzl.zza(new zzb(this));
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd$6 */
    class C22986 extends zza {
        final /* synthetic */ LocationRequest zzaVL;
        final /* synthetic */ LocationListener zzaVM;
        final /* synthetic */ Looper zzaVR;

        /* Access modifiers changed, original: protected */
        public void zza(zzl zzl) throws RemoteException {
            zzl.zza(this.zzaVL, this.zzaVM, this.zzaVR, new zzb(this));
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd$7 */
    class C22997 extends zza {
        final /* synthetic */ LocationRequest zzaVL;
        final /* synthetic */ LocationCallback zzaVO;
        final /* synthetic */ Looper zzaVR;

        /* Access modifiers changed, original: protected */
        public void zza(zzl zzl) throws RemoteException {
            zzl.zza(LocationRequestInternal.zzb(this.zzaVL), this.zzaVO, this.zzaVR, new zzb(this));
        }
    }

    /* renamed from: com.google.android.gms.location.internal.zzd$8 */
    class C23008 extends zza {
        final /* synthetic */ PendingIntent zzaVG;
        final /* synthetic */ LocationRequest zzaVL;

        /* Access modifiers changed, original: protected */
        public void zza(zzl zzl) throws RemoteException {
            zzl.zza(this.zzaVL, this.zzaVG, new zzb(this));
        }
    }

    private static class zzb extends com.google.android.gms.location.internal.zzg.zza {
        private final com.google.android.gms.internal.zznt.zzb<Status> zzasz;

        public zzb(com.google.android.gms.internal.zznt.zzb<Status> zzb) {
            this.zzasz = zzb;
        }

        public void zza(FusedLocationProviderResult fusedLocationProviderResult) {
            this.zzasz.setResult(fusedLocationProviderResult.getStatus());
        }
    }

    public Location getLastLocation(GoogleApiClient googleApiClient) {
        try {
            return LocationServices.zzi(googleApiClient).getLastLocation();
        } catch (Exception e) {
            return null;
        }
    }

    public PendingResult<Status> removeLocationUpdates(GoogleApiClient googleApiClient, final LocationListener locationListener) {
        return googleApiClient.zzd(new zza(googleApiClient) {
            /* Access modifiers changed, original: protected */
            public void zza(zzl zzl) throws RemoteException {
                zzl.zza(locationListener, new zzb(this));
            }
        });
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient googleApiClient, final LocationRequest locationRequest, final LocationListener locationListener) {
        return googleApiClient.zzd(new zza(googleApiClient) {
            /* Access modifiers changed, original: protected */
            public void zza(zzl zzl) throws RemoteException {
                zzl.zza(locationRequest, locationListener, null, new zzb(this));
            }
        });
    }
}
