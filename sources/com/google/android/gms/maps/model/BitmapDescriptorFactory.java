package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.maps.model.internal.zza;

public final class BitmapDescriptorFactory {
    private static zza zzbam;

    private BitmapDescriptorFactory() {
    }

    public static BitmapDescriptor fromResource(int i) {
        try {
            return new BitmapDescriptor(zzDS().zzja(i));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    private static zza zzDS() {
        return (zza) zzaa.zzb(zzbam, (Object) "IBitmapDescriptorFactory is not initialized");
    }

    public static void zza(zza zza) {
        if (zzbam == null) {
            zzbam = (zza) zzaa.zzz(zza);
        }
    }
}
