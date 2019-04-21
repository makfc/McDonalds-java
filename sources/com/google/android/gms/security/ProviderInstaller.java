package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.zze;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.lang.reflect.Method;

public class ProviderInstaller {
    private static final zzc zzbnb = zzc.zzqV();
    private static Method zzbnc = null;
    private static final Object zzrs = new Object();

    /* renamed from: com.google.android.gms.security.ProviderInstaller$1 */
    class C27111 extends AsyncTask<Void, Void, Integer> implements TraceFieldInterface {
        public Trace _nr_trace;
        final /* synthetic */ ProviderInstallListener zzbnd;
        final /* synthetic */ Context zzqB;

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        /* Access modifiers changed, original: protected|synthetic */
        public /* synthetic */ Object doInBackground(Object[] objArr) {
            try {
                TraceMachine.enterMethod(this._nr_trace, "ProviderInstaller$1#doInBackground", null);
            } catch (NoSuchFieldError e) {
                while (true) {
                    TraceMachine.enterMethod(null, "ProviderInstaller$1#doInBackground", null);
                }
            }
            Integer zzc = zzc((Void[]) objArr);
            TraceMachine.exitMethod();
            TraceMachine.unloadTraceContext(this);
            return zzc;
        }

        /* Access modifiers changed, original: protected|synthetic */
        public /* synthetic */ void onPostExecute(Object obj) {
            try {
                TraceMachine.enterMethod(this._nr_trace, "ProviderInstaller$1#onPostExecute", null);
            } catch (NoSuchFieldError e) {
                while (true) {
                    TraceMachine.enterMethod(null, "ProviderInstaller$1#onPostExecute", null);
                }
            }
            zzg((Integer) obj);
            TraceMachine.exitMethod();
        }

        /* Access modifiers changed, original: protected|varargs */
        public Integer zzc(Void... voidArr) {
            try {
                ProviderInstaller.installIfNeeded(this.zzqB);
                return Integer.valueOf(0);
            } catch (GooglePlayServicesRepairableException e) {
                return Integer.valueOf(e.getConnectionStatusCode());
            } catch (GooglePlayServicesNotAvailableException e2) {
                return Integer.valueOf(e2.errorCode);
            }
        }

        /* Access modifiers changed, original: protected */
        public void zzg(Integer num) {
            if (num.intValue() == 0) {
                this.zzbnd.onProviderInstalled();
                return;
            }
            this.zzbnd.onProviderInstallFailed(num.intValue(), ProviderInstaller.zzbnb.zza(this.zzqB, num.intValue(), "pi"));
        }
    }

    public interface ProviderInstallListener {
        void onProviderInstallFailed(int i, Intent intent);

        void onProviderInstalled();
    }

    public static void installIfNeeded(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        zzaa.zzb((Object) context, (Object) "Context must not be null");
        zzbnb.zzaf(context);
        Context remoteContext = zze.getRemoteContext(context);
        if (remoteContext == null) {
            Log.e("ProviderInstaller", "Failed to get remote context");
            throw new GooglePlayServicesNotAvailableException(8);
        }
        synchronized (zzrs) {
            try {
                if (zzbnc == null) {
                    zzbf(remoteContext);
                }
                zzbnc.invoke(null, new Object[]{remoteContext});
            } catch (Exception e) {
                String str = "ProviderInstaller";
                String str2 = "Failed to install provider: ";
                String valueOf = String.valueOf(e.getMessage());
                Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                throw new GooglePlayServicesNotAvailableException(8);
            }
        }
    }

    private static void zzbf(Context context) throws ClassNotFoundException, NoSuchMethodException {
        zzbnc = context.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", new Class[]{Context.class});
    }
}
