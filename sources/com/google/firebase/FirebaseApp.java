package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzaiy;
import com.google.android.gms.internal.zzaiz;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class FirebaseApp {
    static final Map<String, FirebaseApp> zzaTZ = new ArrayMap();
    private static final List<String> zzbEC = Arrays.asList(new String[]{"com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId"});
    private static final List<String> zzbED = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
    private static final List<String> zzbEE = Arrays.asList(new String[]{"com.google.android.gms.measurement.AppMeasurement"});
    private static final Set<String> zzbEF = Collections.emptySet();
    private static final Object zzrs = new Object();
    private final String mName;
    private final FirebaseOptions zzbEG;
    private final AtomicBoolean zzbEH = new AtomicBoolean(true);
    private final AtomicBoolean zzbEI = new AtomicBoolean();
    private final List<zza> zzbEJ = new CopyOnWriteArrayList();
    private final List<zzb> zzbEK = new CopyOnWriteArrayList();
    private final List<Object> zzbEL = new CopyOnWriteArrayList();
    private final Context zztm;

    public interface zza {
    }

    public interface zzb {
        void zzaI(boolean z);
    }

    protected FirebaseApp(Context context, String str, FirebaseOptions firebaseOptions) {
        this.zztm = (Context) zzaa.zzz(context);
        this.mName = zzaa.zzdl(str);
        this.zzbEG = (FirebaseOptions) zzaa.zzz(firebaseOptions);
    }

    @Nullable
    public static FirebaseApp getInstance() {
        return getInstance("[DEFAULT]");
    }

    public static FirebaseApp getInstance(@NonNull String str) {
        FirebaseApp firebaseApp;
        synchronized (zzrs) {
            firebaseApp = (FirebaseApp) zzaTZ.get(zzhF(str));
            if (firebaseApp != null) {
            } else {
                String str2;
                List zzNW = zzNW();
                if (zzNW.isEmpty()) {
                    str2 = "";
                } else {
                    String str3 = "Available app names: ";
                    str2 = String.valueOf(zzx.zzdk(", ").zza(zzNW));
                    str2 = str2.length() != 0 ? str3.concat(str2) : new String(str3);
                }
                throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", new Object[]{str, str2}));
            }
        }
        return firebaseApp;
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions) {
        return initializeApp(context, firebaseOptions, "[DEFAULT]");
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions, String str) {
        FirebaseApp firebaseApp;
        zzaiz zzbA = zzaiz.zzbA(context);
        zzbv(context);
        String zzhF = zzhF(str);
        Object applicationContext = context.getApplicationContext();
        synchronized (zzrs) {
            zzaa.zza(!zzaTZ.containsKey(zzhF), new StringBuilder(String.valueOf(zzhF).length() + 33).append("FirebaseApp name ").append(zzhF).append(" already exists!").toString());
            zzaa.zzb(applicationContext, (Object) "Application context cannot be null.");
            firebaseApp = new FirebaseApp(applicationContext, zzhF, firebaseOptions);
            zzaTZ.put(zzhF, firebaseApp);
        }
        zzbA.zzf(firebaseApp);
        zza(FirebaseApp.class, firebaseApp, zzbEC);
        if (firebaseApp.zzNU()) {
            zza(FirebaseApp.class, firebaseApp, zzbED);
            zza(Context.class, firebaseApp.getApplicationContext(), zzbEE);
        }
        return firebaseApp;
    }

    private void zzNT() {
        zzaa.zza(!this.zzbEI.get(), "FirebaseApp was deleted");
    }

    private static List<String> zzNW() {
        com.google.android.gms.common.util.zza zza = new com.google.android.gms.common.util.zza();
        synchronized (zzrs) {
            for (FirebaseApp name : zzaTZ.values()) {
                zza.add(name.getName());
            }
            zzaiz zzUw = zzaiz.zzUw();
            if (zzUw != null) {
                zza.addAll(zzUw.zzUx());
            }
        }
        ArrayList arrayList = new ArrayList(zza);
        Collections.sort(arrayList);
        return arrayList;
    }

    private static <T> void zza(Class<T> cls, T t, Iterable<String> iterable) {
        for (String str : iterable) {
            String str2;
            try {
                Class cls2 = Class.forName(str2);
                Method method = cls2.getMethod("getInstance", new Class[]{cls});
                if ((method.getModifiers() & 9) == 9) {
                    method.invoke(null, new Object[]{t});
                }
                String valueOf = String.valueOf(cls2);
                Log.d("FirebaseApp", new StringBuilder(String.valueOf(valueOf).length() + 13).append("Initialized ").append(valueOf).append(".").toString());
            } catch (ClassNotFoundException e) {
                if (zzbEF.contains(str2)) {
                    throw new IllegalStateException(String.valueOf(str2).concat(" is missing, but is required. Check if it has been removed by Proguard."));
                }
                Log.d("FirebaseApp", String.valueOf(str2).concat(" is not linked. Skipping initialization."));
            } catch (NoSuchMethodException e2) {
                throw new IllegalStateException(String.valueOf(str2).concat("#getInstance has been removed by Proguard. Add keep rule to prevent it."));
            } catch (InvocationTargetException e3) {
                Log.wtf("FirebaseApp", "Firebase API initialization failure.", e3);
            } catch (IllegalAccessException e4) {
                String str3 = "FirebaseApp";
                String str4 = "Failed to initialize ";
                str2 = String.valueOf(str2);
                Log.wtf(str3, str2.length() != 0 ? str4.concat(str2) : new String(str4), e4);
            }
        }
    }

    public static void zzaI(boolean z) {
        synchronized (zzrs) {
            Iterator it = new ArrayList(zzaTZ.values()).iterator();
            while (it.hasNext()) {
                FirebaseApp firebaseApp = (FirebaseApp) it.next();
                if (firebaseApp.zzbEH.get()) {
                    firebaseApp.zzaJ(z);
                }
            }
        }
    }

    private void zzaJ(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for (zzb zzaI : this.zzbEK) {
            zzaI.zzaI(z);
        }
    }

    public static FirebaseApp zzbu(Context context) {
        FirebaseOptions fromResource = FirebaseOptions.fromResource(context);
        return fromResource == null ? null : initializeApp(context, fromResource);
    }

    @TargetApi(14)
    private static void zzbv(Context context) {
        if (zzs.zzva() && (context.getApplicationContext() instanceof Application)) {
            zzaiy.zza((Application) context.getApplicationContext());
        }
    }

    private static String zzhF(@NonNull String str) {
        return str.trim();
    }

    public boolean equals(Object obj) {
        return !(obj instanceof FirebaseApp) ? false : this.mName.equals(((FirebaseApp) obj).getName());
    }

    @NonNull
    public Context getApplicationContext() {
        zzNT();
        return this.zztm;
    }

    @NonNull
    public String getName() {
        zzNT();
        return this.mName;
    }

    @NonNull
    public FirebaseOptions getOptions() {
        zzNT();
        return this.zzbEG;
    }

    public int hashCode() {
        return this.mName.hashCode();
    }

    public String toString() {
        return zzz.zzy(this).zzg("name", this.mName).zzg("options", this.zzbEG).toString();
    }

    public boolean zzNU() {
        return "[DEFAULT]".equals(getName());
    }
}
