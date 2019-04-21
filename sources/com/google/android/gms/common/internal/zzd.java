package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzd<T extends IInterface> {
    public static final String[] zzaqJ = new String[]{"service_esmobile", "service_googleme"};
    private final Context mContext;
    final Handler mHandler;
    private final Looper zzakW;
    private final com.google.android.gms.common.zzc zzamE;
    private T zzaqA;
    private final ArrayList<zze<?>> zzaqB;
    private zzh zzaqC;
    private int zzaqD;
    private final zzb zzaqE;
    private final zzc zzaqF;
    private final int zzaqG;
    private final String zzaqH;
    protected AtomicInteger zzaqI;
    private int zzaqr;
    private long zzaqs;
    private long zzaqt;
    private int zzaqu;
    private long zzaqv;
    private final zzm zzaqw;
    private final Object zzaqx;
    private zzu zzaqy;
    private zzf zzaqz;
    private final Object zzpp;

    protected abstract class zze<TListener> {
        private TListener mListener;
        private boolean zzaqM = false;

        public zze(TListener tListener) {
            this.mListener = tListener;
        }

        public void unregister() {
            zzts();
            synchronized (zzd.this.zzaqB) {
                zzd.this.zzaqB.remove(this);
            }
        }

        public abstract void zztq();

        public void zztr() {
            Object obj;
            synchronized (this) {
                obj = this.mListener;
                if (this.zzaqM) {
                    String valueOf = String.valueOf(this);
                    Log.w("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Callback proxy ").append(valueOf).append(" being reused. This is not safe.").toString());
                }
            }
            if (obj != null) {
                try {
                    zzw(obj);
                } catch (RuntimeException e) {
                    zztq();
                    throw e;
                }
            }
            zztq();
            synchronized (this) {
                this.zzaqM = true;
            }
            unregister();
        }

        public void zzts() {
            synchronized (this) {
                this.mListener = null;
            }
        }

        public abstract void zzw(TListener tListener);
    }

    private abstract class zza extends zze<Boolean> {
        public final int statusCode;
        public final Bundle zzaqK;

        @BinderThread
        protected zza(int i, Bundle bundle) {
            super(Boolean.valueOf(true));
            this.statusCode = i;
            this.zzaqK = bundle;
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: zzc */
        public void zzw(Boolean bool) {
            PendingIntent pendingIntent = null;
            if (bool == null) {
                zzd.this.zzb(1, null);
                return;
            }
            switch (this.statusCode) {
                case 0:
                    if (!zztp()) {
                        zzd.this.zzb(1, null);
                        zzl(new ConnectionResult(8, null));
                        return;
                    }
                    return;
                case 10:
                    zzd.this.zzb(1, null);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                default:
                    zzd.this.zzb(1, null);
                    if (this.zzaqK != null) {
                        pendingIntent = (PendingIntent) this.zzaqK.getParcelable("pendingIntent");
                    }
                    zzl(new ConnectionResult(this.statusCode, pendingIntent));
                    return;
            }
        }

        public abstract void zzl(ConnectionResult connectionResult);

        public abstract boolean zztp();

        /* Access modifiers changed, original: protected */
        public void zztq() {
        }
    }

    public interface zzb {
        void onConnected(@Nullable Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface zzc {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    final class zzd extends Handler {
        public zzd(Looper looper) {
            super(looper);
        }

        private void zza(Message message) {
            zze zze = (zze) message.obj;
            zze.zztq();
            zze.unregister();
        }

        private boolean zzb(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 5;
        }

        public void handleMessage(Message message) {
            PendingIntent pendingIntent = null;
            if (zzd.this.zzaqI.get() != message.arg1) {
                if (zzb(message)) {
                    zza(message);
                }
            } else if ((message.what == 1 || message.what == 5) && !zzd.this.isConnecting()) {
                zza(message);
            } else if (message.what == 3) {
                if (message.obj instanceof PendingIntent) {
                    pendingIntent = (PendingIntent) message.obj;
                }
                ConnectionResult connectionResult = new ConnectionResult(message.arg2, pendingIntent);
                zzd.this.zzaqz.zzh(connectionResult);
                zzd.this.onConnectionFailed(connectionResult);
            } else if (message.what == 4) {
                zzd.this.zzb(4, null);
                if (zzd.this.zzaqE != null) {
                    zzd.this.zzaqE.onConnectionSuspended(message.arg2);
                }
                zzd.this.onConnectionSuspended(message.arg2);
                zzd.this.zza(4, 1, null);
            } else if (message.what == 2 && !zzd.this.isConnected()) {
                zza(message);
            } else if (zzb(message)) {
                ((zze) message.obj).zztr();
            } else {
                Log.wtf("GmsClient", "Don't know how to handle message: " + message.what, new Exception());
            }
        }
    }

    public interface zzf {
        void zzh(@NonNull ConnectionResult connectionResult);
    }

    public static final class zzg extends com.google.android.gms.common.internal.zzt.zza {
        private zzd zzaqN;
        private final int zzaqO;

        public zzg(@NonNull zzd zzd, int i) {
            this.zzaqN = zzd;
            this.zzaqO = i;
        }

        private void zztt() {
            this.zzaqN = null;
        }

        @BinderThread
        public void zza(int i, @NonNull IBinder iBinder, @Nullable Bundle bundle) {
            zzaa.zzb(this.zzaqN, (Object) "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzaqN.zza(i, iBinder, bundle, this.zzaqO);
            zztt();
        }

        @BinderThread
        public void zzb(int i, @Nullable Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }
    }

    public final class zzh implements ServiceConnection {
        private final int zzaqO;

        public zzh(int i) {
            this.zzaqO = i;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            zzaa.zzb((Object) iBinder, (Object) "Expecting a valid IBinder");
            synchronized (zzd.this.zzaqx) {
                zzd.this.zzaqy = com.google.android.gms.common.internal.zzu.zza.zzaW(iBinder);
            }
            zzd.this.zza(0, null, this.zzaqO);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (zzd.this.zzaqx) {
                zzd.this.zzaqy = null;
            }
            zzd.this.mHandler.sendMessage(zzd.this.mHandler.obtainMessage(4, this.zzaqO, 1));
        }
    }

    protected class zzi implements zzf {
        public void zzh(@NonNull ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                zzd.this.zza(null, zzd.this.zzto());
            } else if (zzd.this.zzaqF != null) {
                zzd.this.zzaqF.onConnectionFailed(connectionResult);
            }
        }
    }

    protected final class zzj extends zza {
        public final IBinder zzaqP;

        @BinderThread
        public zzj(int i, IBinder iBinder, Bundle bundle) {
            super(i, bundle);
            this.zzaqP = iBinder;
        }

        /* Access modifiers changed, original: protected */
        public void zzl(ConnectionResult connectionResult) {
            if (zzd.this.zzaqF != null) {
                zzd.this.zzaqF.onConnectionFailed(connectionResult);
            }
            zzd.this.onConnectionFailed(connectionResult);
        }

        /* Access modifiers changed, original: protected */
        public boolean zztp() {
            try {
                String interfaceDescriptor = this.zzaqP.getInterfaceDescriptor();
                if (zzd.this.zzhU().equals(interfaceDescriptor)) {
                    IInterface zzab = zzd.this.zzab(this.zzaqP);
                    if (zzab == null || !zzd.this.zza(2, 3, zzab)) {
                        return false;
                    }
                    Bundle zzqr = zzd.this.zzqr();
                    if (zzd.this.zzaqE != null) {
                        zzd.this.zzaqE.onConnected(zzqr);
                    }
                    return true;
                }
                String valueOf = String.valueOf(zzd.this.zzhU());
                Log.e("GmsClient", new StringBuilder((String.valueOf(valueOf).length() + 34) + String.valueOf(interfaceDescriptor).length()).append("service descriptor mismatch: ").append(valueOf).append(" vs. ").append(interfaceDescriptor).toString());
                return false;
            } catch (RemoteException e) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    protected final class zzk extends zza {
        @BinderThread
        public zzk(int i, Bundle bundle) {
            super(i, bundle);
        }

        /* Access modifiers changed, original: protected */
        public void zzl(ConnectionResult connectionResult) {
            zzd.this.zzaqz.zzh(connectionResult);
            zzd.this.onConnectionFailed(connectionResult);
        }

        /* Access modifiers changed, original: protected */
        public boolean zztp() {
            zzd.this.zzaqz.zzh(ConnectionResult.zzakj);
            return true;
        }
    }

    protected zzd(Context context, Looper looper, int i, zzb zzb, zzc zzc, String str) {
        this(context, looper, zzm.zzav(context), com.google.android.gms.common.zzc.zzqV(), i, (zzb) zzaa.zzz(zzb), (zzc) zzaa.zzz(zzc), str);
    }

    protected zzd(Context context, Looper looper, zzm zzm, com.google.android.gms.common.zzc zzc, int i, zzb zzb, zzc zzc2, String str) {
        this.zzpp = new Object();
        this.zzaqx = new Object();
        this.zzaqB = new ArrayList();
        this.zzaqD = 1;
        this.zzaqI = new AtomicInteger(0);
        this.mContext = (Context) zzaa.zzb((Object) context, (Object) "Context must not be null");
        this.zzakW = (Looper) zzaa.zzb((Object) looper, (Object) "Looper must not be null");
        this.zzaqw = (zzm) zzaa.zzb((Object) zzm, (Object) "Supervisor must not be null");
        this.zzamE = (com.google.android.gms.common.zzc) zzaa.zzb((Object) zzc, (Object) "API availability must not be null");
        this.mHandler = new zzd(looper);
        this.zzaqG = i;
        this.zzaqE = zzb;
        this.zzaqF = zzc2;
        this.zzaqH = str;
    }

    private boolean zza(int i, int i2, T t) {
        boolean z;
        synchronized (this.zzpp) {
            if (this.zzaqD != i) {
                z = false;
            } else {
                zzb(i2, t);
                z = true;
            }
        }
        return z;
    }

    private void zzb(int i, T t) {
        boolean z = true;
        if ((i == 3) != (t != null)) {
            z = false;
        }
        zzaa.zzaj(z);
        synchronized (this.zzpp) {
            this.zzaqD = i;
            this.zzaqA = t;
            zzc(i, t);
            switch (i) {
                case 1:
                    zzti();
                    break;
                case 2:
                    zzth();
                    break;
                case 3:
                    zza((IInterface) t);
                    break;
            }
        }
    }

    private void zzth() {
        String valueOf;
        String valueOf2;
        if (this.zzaqC != null) {
            valueOf = String.valueOf(zzhT());
            valueOf2 = String.valueOf(zztf());
            Log.e("GmsClient", new StringBuilder((String.valueOf(valueOf).length() + 70) + String.valueOf(valueOf2).length()).append("Calling connect() while still connected, missing disconnect() for ").append(valueOf).append(" on ").append(valueOf2).toString());
            this.zzaqw.zzb(zzhT(), zztf(), this.zzaqC, zztg());
            this.zzaqI.incrementAndGet();
        }
        this.zzaqC = new zzh(this.zzaqI.get());
        if (!this.zzaqw.zza(zzhT(), zztf(), this.zzaqC, zztg())) {
            valueOf = String.valueOf(zzhT());
            valueOf2 = String.valueOf(zztf());
            Log.e("GmsClient", new StringBuilder((String.valueOf(valueOf).length() + 34) + String.valueOf(valueOf2).length()).append("unable to connect to service: ").append(valueOf).append(" on ").append(valueOf2).toString());
            zza(16, null, this.zzaqI.get());
        }
    }

    private void zzti() {
        if (this.zzaqC != null) {
            this.zzaqw.zzb(zzhT(), zztf(), this.zzaqC, zztg());
            this.zzaqC = null;
        }
    }

    public void disconnect() {
        this.zzaqI.incrementAndGet();
        synchronized (this.zzaqB) {
            int size = this.zzaqB.size();
            for (int i = 0; i < size; i++) {
                ((zze) this.zzaqB.get(i)).zzts();
            }
            this.zzaqB.clear();
        }
        synchronized (this.zzaqx) {
            this.zzaqy = null;
        }
        zzb(1, null);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i;
        IInterface iInterface;
        PrintWriter append;
        long j;
        String valueOf;
        synchronized (this.zzpp) {
            i = this.zzaqD;
            iInterface = this.zzaqA;
        }
        printWriter.append(str).append("mConnectState=");
        switch (i) {
            case 1:
                printWriter.print("DISCONNECTED");
                break;
            case 2:
                printWriter.print("CONNECTING");
                break;
            case 3:
                printWriter.print("CONNECTED");
                break;
            case 4:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print("UNKNOWN");
                break;
        }
        printWriter.append(" mService=");
        if (iInterface == null) {
            printWriter.println(SafeJsonPrimitive.NULL_STRING);
        } else {
            printWriter.append(zzhU()).append("@").println(Integer.toHexString(System.identityHashCode(iInterface.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzaqt > 0) {
            append = printWriter.append(str).append("lastConnectedTime=");
            j = this.zzaqt;
            valueOf = String.valueOf(simpleDateFormat.format(new Date(this.zzaqt)));
            append.println(new StringBuilder(String.valueOf(valueOf).length() + 21).append(j).append(" ").append(valueOf).toString());
        }
        if (this.zzaqs > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            switch (this.zzaqr) {
                case 1:
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                case 2:
                    printWriter.append("CAUSE_NETWORK_LOST");
                    break;
                default:
                    printWriter.append(String.valueOf(this.zzaqr));
                    break;
            }
            append = printWriter.append(" lastSuspendedTime=");
            j = this.zzaqs;
            valueOf = String.valueOf(simpleDateFormat.format(new Date(this.zzaqs)));
            append.println(new StringBuilder(String.valueOf(valueOf).length() + 21).append(j).append(" ").append(valueOf).toString());
        }
        if (this.zzaqv > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzaqu));
            append = printWriter.append(" lastFailedTime=");
            j = this.zzaqv;
            String valueOf2 = String.valueOf(simpleDateFormat.format(new Date(this.zzaqv)));
            append.println(new StringBuilder(String.valueOf(valueOf2).length() + 21).append(j).append(" ").append(valueOf2).toString());
        }
    }

    public Account getAccount() {
        return null;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.zzpp) {
            z = this.zzaqD == 3;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.zzpp) {
            z = this.zzaqD == 2;
        }
        return z;
    }

    /* Access modifiers changed, original: protected */
    @CallSuper
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzaqu = connectionResult.getErrorCode();
        this.zzaqv = System.currentTimeMillis();
    }

    /* Access modifiers changed, original: protected */
    @CallSuper
    public void onConnectionSuspended(int i) {
        this.zzaqr = i;
        this.zzaqs = System.currentTimeMillis();
    }

    /* Access modifiers changed, original: protected */
    public void zza(int i, @Nullable Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(5, i2, -1, new zzk(i, bundle)));
    }

    /* Access modifiers changed, original: protected */
    @BinderThread
    public void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, i2, -1, new zzj(i, iBinder, bundle)));
    }

    /* Access modifiers changed, original: protected */
    @CallSuper
    public void zza(@NonNull T t) {
        this.zzaqt = System.currentTimeMillis();
    }

    public void zza(@NonNull zzf zzf) {
        this.zzaqz = (zzf) zzaa.zzb((Object) zzf, (Object) "Connection progress callbacks cannot be null.");
        zzb(2, null);
    }

    @WorkerThread
    public void zza(zzq zzq, Set<Scope> set) {
        try {
            GetServiceRequest zzl = new GetServiceRequest(this.zzaqG).zzdf(this.mContext.getPackageName()).zzl(zzoO());
            if (set != null) {
                zzl.zzd(set);
            }
            if (zzpd()) {
                zzl.zzc(zztk()).zzb(zzq);
            } else if (zztn()) {
                zzl.zzc(getAccount());
            }
            synchronized (this.zzaqx) {
                if (this.zzaqy != null) {
                    this.zzaqy.zza(new zzg(this, this.zzaqI.get()), zzl);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "service died");
            zzbZ(1);
        } catch (RemoteException e2) {
            Log.w("GmsClient", "Remote exception occurred", e2);
        }
    }

    @Nullable
    public abstract T zzab(IBinder iBinder);

    public void zzbZ(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, this.zzaqI.get(), i));
    }

    /* Access modifiers changed, original: 0000 */
    public void zzc(int i, T t) {
    }

    @NonNull
    public abstract String zzhT();

    @NonNull
    public abstract String zzhU();

    /* Access modifiers changed, original: protected */
    public Bundle zzoO() {
        return new Bundle();
    }

    public boolean zzpd() {
        return false;
    }

    public boolean zzps() {
        return false;
    }

    public Intent zzpt() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    public Bundle zzqr() {
        return null;
    }

    public boolean zzrg() {
        return true;
    }

    @Nullable
    public IBinder zzrh() {
        IBinder iBinder;
        synchronized (this.zzaqx) {
            if (this.zzaqy == null) {
                iBinder = null;
            } else {
                iBinder = this.zzaqy.asBinder();
            }
        }
        return iBinder;
    }

    /* Access modifiers changed, original: protected */
    public String zztf() {
        return "com.google.android.gms";
    }

    /* Access modifiers changed, original: protected|final */
    @Nullable
    public final String zztg() {
        return this.zzaqH == null ? this.mContext.getClass().getName() : this.zzaqH;
    }

    public void zztj() {
        int isGooglePlayServicesAvailable = this.zzamE.isGooglePlayServicesAvailable(this.mContext);
        if (isGooglePlayServicesAvailable != 0) {
            zzb(1, null);
            this.zzaqz = new zzi();
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzaqI.get(), isGooglePlayServicesAvailable));
            return;
        }
        zza(new zzi());
    }

    public final Account zztk() {
        return getAccount() != null ? getAccount() : new Account("<<default account>>", "com.google");
    }

    /* Access modifiers changed, original: protected|final */
    public final void zztl() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final T zztm() throws DeadObjectException {
        IInterface iInterface;
        synchronized (this.zzpp) {
            if (this.zzaqD == 4) {
                throw new DeadObjectException();
            }
            zztl();
            zzaa.zza(this.zzaqA != null, "Client is connected but service is null");
            iInterface = this.zzaqA;
        }
        return iInterface;
    }

    public boolean zztn() {
        return false;
    }

    /* Access modifiers changed, original: protected */
    public Set<Scope> zzto() {
        return Collections.EMPTY_SET;
    }
}
