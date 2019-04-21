package com.google.android.gms.maps.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.maps.internal.zzc.zza;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class zzad {
    private static Context zzbak;
    private static zzc zzbal;

    private static Context getRemoteContext(Context context) {
        if (zzbak == null) {
            if (zzDQ()) {
                zzbak = context.getApplicationContext();
            } else {
                zzbak = GooglePlayServicesUtil.getRemoteContext(context);
            }
        }
        return zzbak;
    }

    public static boolean zzDQ() {
        return false;
    }

    private static Class<?> zzDR() {
        try {
            return Class.forName("com.google.android.gms.maps.internal.CreatorImpl");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T zza(ClassLoader classLoader, String str) {
        try {
            return zzf(((ClassLoader) zzaa.zzz(classLoader)).loadClass(str));
        } catch (ClassNotFoundException e) {
            String str2 = "Unable to find dynamic class ";
            String valueOf = String.valueOf(str);
            throw new IllegalStateException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
    }

    public static zzc zzaZ(Context context) throws GooglePlayServicesNotAvailableException {
        zzaa.zzz(context);
        if (zzbal != null) {
            return zzbal;
        }
        zzba(context);
        zzbal = zzbb(context);
        try {
            zzbal.zzd(zze.zzD(getRemoteContext(context).getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            return zzbal;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    private static void zzba(Context context) throws GooglePlayServicesNotAvailableException {
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        switch (isGooglePlayServicesAvailable) {
            case 0:
                return;
            default:
                throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
        }
    }

    private static zzc zzbb(Context context) {
        if (zzDQ()) {
            Log.i(zzad.class.getSimpleName(), "Making Creator statically");
            return (zzc) zzf(zzDR());
        }
        Log.i(zzad.class.getSimpleName(), "Making Creator dynamically");
        return zza.zzcC((IBinder) zza(getRemoteContext(context).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl"));
    }

    private static <T> T zzf(Class<?> cls) {
        String str;
        String valueOf;
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            str = "Unable to instantiate the dynamic class ";
            valueOf = String.valueOf(cls.getName());
            throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        } catch (IllegalAccessException e2) {
            str = "Unable to call the default constructor of ";
            valueOf = String.valueOf(cls.getName());
            throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }
}
