package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.zzd;
import com.google.android.gms.location.zze;
import java.util.HashMap;
import java.util.Map;

public class zzk {
    private final Context mContext;
    private Map<LocationListener, zzc> zzaDR = new HashMap();
    private final zzp<zzi> zzaVJ;
    private ContentProviderClient zzaVW = null;
    private boolean zzaVX = false;
    private Map<LocationCallback, zza> zzaVY = new HashMap();

    private static class zza extends com.google.android.gms.location.zzd.zza {
        private Handler zzaVZ;

        zza(final LocationCallback locationCallback, Looper looper) {
            if (looper == null) {
                looper = Looper.myLooper();
                zzaa.zza(looper != null, "Can't create handler inside thread that has not called Looper.prepare()");
            }
            this.zzaVZ = new Handler(looper) {
                public void handleMessage(Message message) {
                    switch (message.what) {
                        case 0:
                            locationCallback.onLocationResult((LocationResult) message.obj);
                            return;
                        case 1:
                            locationCallback.onLocationAvailability((LocationAvailability) message.obj);
                            return;
                        default:
                            return;
                    }
                }
            };
        }

        private void zzb(int i, Object obj) {
            if (this.zzaVZ == null) {
                Log.e("LocationClientHelper", "Received a data in client after calling removeLocationUpdates.");
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.obj = obj;
            this.zzaVZ.sendMessage(obtain);
        }

        public void onLocationAvailability(LocationAvailability locationAvailability) {
            zzb(1, locationAvailability);
        }

        public void onLocationResult(LocationResult locationResult) {
            zzb(0, locationResult);
        }

        public void release() {
            this.zzaVZ = null;
        }
    }

    private static class zzb extends Handler {
        private final LocationListener zzaWb;

        public zzb(LocationListener locationListener) {
            this.zzaWb = locationListener;
        }

        public zzb(LocationListener locationListener, Looper looper) {
            super(looper);
            this.zzaWb = locationListener;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.zzaWb.onLocationChanged(new Location((Location) message.obj));
                    return;
                default:
                    Log.e("LocationClientHelper", "unknown message in LocationHandler.handleMessage");
                    return;
            }
        }
    }

    private static class zzc extends com.google.android.gms.location.zze.zza {
        private Handler zzaVZ;

        zzc(LocationListener locationListener, Looper looper) {
            if (looper == null) {
                zzaa.zza(Looper.myLooper() != null, "Can't create handler inside thread that has not called Looper.prepare()");
            }
            this.zzaVZ = looper == null ? new zzb(locationListener) : new zzb(locationListener, looper);
        }

        public void onLocationChanged(Location location) {
            if (this.zzaVZ == null) {
                Log.e("LocationClientHelper", "Received a location in client after calling removeLocationUpdates.");
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = location;
            this.zzaVZ.sendMessage(obtain);
        }

        public void release() {
            this.zzaVZ = null;
        }
    }

    public zzk(Context context, zzp<zzi> zzp) {
        this.mContext = context;
        this.zzaVJ = zzp;
    }

    private zza zza(LocationCallback locationCallback, Looper looper) {
        Object obj;
        synchronized (this.zzaVY) {
            obj = (zza) this.zzaVY.get(locationCallback);
            if (obj == null) {
                obj = new zza(locationCallback, looper);
            }
            this.zzaVY.put(locationCallback, obj);
        }
        return obj;
    }

    private zzc zza(LocationListener locationListener, Looper looper) {
        Object obj;
        synchronized (this.zzaDR) {
            obj = (zzc) this.zzaDR.get(locationListener);
            if (obj == null) {
                obj = new zzc(locationListener, looper);
            }
            this.zzaDR.put(locationListener, obj);
        }
        return obj;
    }

    public Location getLastLocation() {
        this.zzaVJ.zztl();
        try {
            return ((zzi) this.zzaVJ.zztm()).zzeJ(this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public void removeAllListeners() {
        try {
            synchronized (this.zzaDR) {
                for (zze zze : this.zzaDR.values()) {
                    if (zze != null) {
                        ((zzi) this.zzaVJ.zztm()).zza(LocationRequestUpdateData.zza(zze, null));
                    }
                }
                this.zzaDR.clear();
            }
            synchronized (this.zzaVY) {
                for (zzd zzd : this.zzaVY.values()) {
                    if (zzd != null) {
                        ((zzi) this.zzaVJ.zztm()).zza(LocationRequestUpdateData.zza(zzd, null));
                    }
                }
                this.zzaVY.clear();
            }
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public void zzCx() {
        if (this.zzaVX) {
            try {
                zzat(false);
            } catch (RemoteException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public void zza(PendingIntent pendingIntent, zzg zzg) throws RemoteException {
        this.zzaVJ.zztl();
        ((zzi) this.zzaVJ.zztm()).zza(LocationRequestUpdateData.zzb(pendingIntent, zzg));
    }

    public void zza(LocationCallback locationCallback, zzg zzg) throws RemoteException {
        this.zzaVJ.zztl();
        zzaa.zzb((Object) locationCallback, (Object) "Invalid null callback");
        synchronized (this.zzaVY) {
            zzd zzd = (zza) this.zzaVY.remove(locationCallback);
            if (zzd != null) {
                zzd.release();
                ((zzi) this.zzaVJ.zztm()).zza(LocationRequestUpdateData.zza(zzd, zzg));
            }
        }
    }

    public void zza(LocationListener locationListener, zzg zzg) throws RemoteException {
        this.zzaVJ.zztl();
        zzaa.zzb((Object) locationListener, (Object) "Invalid null listener");
        synchronized (this.zzaDR) {
            zze zze = (zzc) this.zzaDR.remove(locationListener);
            if (zze != null) {
                zze.release();
                ((zzi) this.zzaVJ.zztm()).zza(LocationRequestUpdateData.zza(zze, zzg));
            }
        }
    }

    public void zza(LocationRequest locationRequest, PendingIntent pendingIntent, zzg zzg) throws RemoteException {
        this.zzaVJ.zztl();
        ((zzi) this.zzaVJ.zztm()).zza(LocationRequestUpdateData.zza(LocationRequestInternal.zzb(locationRequest), pendingIntent, zzg));
    }

    public void zza(LocationRequest locationRequest, LocationListener locationListener, Looper looper, zzg zzg) throws RemoteException {
        this.zzaVJ.zztl();
        ((zzi) this.zzaVJ.zztm()).zza(LocationRequestUpdateData.zza(LocationRequestInternal.zzb(locationRequest), zza(locationListener, looper), zzg));
    }

    public void zza(LocationRequestInternal locationRequestInternal, LocationCallback locationCallback, Looper looper, zzg zzg) throws RemoteException {
        this.zzaVJ.zztl();
        ((zzi) this.zzaVJ.zztm()).zza(LocationRequestUpdateData.zza(locationRequestInternal, zza(locationCallback, looper), zzg));
    }

    public void zza(zzg zzg) throws RemoteException {
        this.zzaVJ.zztl();
        ((zzi) this.zzaVJ.zztm()).zza(zzg);
    }

    public void zzat(boolean z) throws RemoteException {
        this.zzaVJ.zztl();
        ((zzi) this.zzaVJ.zztm()).zzat(z);
        this.zzaVX = z;
    }

    public void zzc(Location location) throws RemoteException {
        this.zzaVJ.zztl();
        ((zzi) this.zzaVJ.zztm()).zzc(location);
    }
}
