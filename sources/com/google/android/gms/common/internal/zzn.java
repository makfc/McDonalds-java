package com.google.android.gms.common.internal;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

final class zzn extends zzm implements Callback {
    private final Handler mHandler;
    private final HashMap<zza, zzb> zzarR = new HashMap();
    private final com.google.android.gms.common.stats.zzb zzarS;
    private final long zzarT;
    private final Context zztm;

    private static final class zza {
        private final String zzVt;
        private final String zzarU;
        private final ComponentName zzarV;

        public zza(ComponentName componentName) {
            this.zzVt = null;
            this.zzarU = null;
            this.zzarV = (ComponentName) zzaa.zzz(componentName);
        }

        public zza(String str, String str2) {
            this.zzVt = zzaa.zzdl(str);
            this.zzarU = zzaa.zzdl(str2);
            this.zzarV = null;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return zzz.equal(this.zzVt, zza.zzVt) && zzz.equal(this.zzarV, zza.zzarV);
        }

        public int hashCode() {
            return zzz.hashCode(this.zzVt, this.zzarV);
        }

        public String toString() {
            return this.zzVt == null ? this.zzarV.flattenToString() : this.zzVt;
        }

        public Intent zztK() {
            return this.zzVt != null ? new Intent(this.zzVt).setPackage(this.zzarU) : new Intent().setComponent(this.zzarV);
        }
    }

    private final class zzb {
        private int mState = 2;
        private IBinder zzaqQ;
        private ComponentName zzarV;
        private final zza zzarW = new zza();
        private final Set<ServiceConnection> zzarX = new HashSet();
        private boolean zzarY;
        private final zza zzarZ;

        public class zza implements ServiceConnection {
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (zzn.this.zzarR) {
                    zzb.this.zzaqQ = iBinder;
                    zzb.this.zzarV = componentName;
                    for (ServiceConnection onServiceConnected : zzb.this.zzarX) {
                        onServiceConnected.onServiceConnected(componentName, iBinder);
                    }
                    zzb.this.mState = 1;
                }
            }

            public void onServiceDisconnected(ComponentName componentName) {
                synchronized (zzn.this.zzarR) {
                    zzb.this.zzaqQ = null;
                    zzb.this.zzarV = componentName;
                    for (ServiceConnection onServiceDisconnected : zzb.this.zzarX) {
                        onServiceDisconnected.onServiceDisconnected(componentName);
                    }
                    zzb.this.mState = 2;
                }
            }
        }

        public zzb(zza zza) {
            this.zzarZ = zza;
        }

        public IBinder getBinder() {
            return this.zzaqQ;
        }

        public ComponentName getComponentName() {
            return this.zzarV;
        }

        public int getState() {
            return this.mState;
        }

        public boolean isBound() {
            return this.zzarY;
        }

        public void zza(ServiceConnection serviceConnection, String str) {
            zzn.this.zzarS.zza(zzn.this.zztm, serviceConnection, str, this.zzarZ.zztK());
            this.zzarX.add(serviceConnection);
        }

        public boolean zza(ServiceConnection serviceConnection) {
            return this.zzarX.contains(serviceConnection);
        }

        public void zzb(ServiceConnection serviceConnection, String str) {
            zzn.this.zzarS.zzb(zzn.this.zztm, serviceConnection);
            this.zzarX.remove(serviceConnection);
        }

        @TargetApi(14)
        public void zzdg(String str) {
            this.mState = 3;
            this.zzarY = zzn.this.zzarS.zza(zzn.this.zztm, str, this.zzarZ.zztK(), this.zzarW, 129);
            if (!this.zzarY) {
                this.mState = 2;
                try {
                    zzn.this.zzarS.zza(zzn.this.zztm, this.zzarW);
                } catch (IllegalArgumentException e) {
                }
            }
        }

        public void zzdh(String str) {
            zzn.this.zzarS.zza(zzn.this.zztm, this.zzarW);
            this.zzarY = false;
            this.mState = 2;
        }

        public boolean zztL() {
            return this.zzarX.isEmpty();
        }
    }

    zzn(Context context) {
        this.zztm = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), this);
        this.zzarS = com.google.android.gms.common.stats.zzb.zzuH();
        this.zzarT = 5000;
    }

    private boolean zza(zza zza, ServiceConnection serviceConnection, String str) {
        boolean isBound;
        zzaa.zzb((Object) serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.zzarR) {
            zzb zzb = (zzb) this.zzarR.get(zza);
            if (zzb != null) {
                this.mHandler.removeMessages(0, zzb);
                if (!zzb.zza(serviceConnection)) {
                    zzb.zza(serviceConnection, str);
                    switch (zzb.getState()) {
                        case 1:
                            serviceConnection.onServiceConnected(zzb.getComponentName(), zzb.getBinder());
                            break;
                        case 2:
                            zzb.zzdg(str);
                            break;
                        default:
                            break;
                    }
                }
                String valueOf = String.valueOf(zza);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 81).append("Trying to bind a GmsServiceConnection that was already connected before.  config=").append(valueOf).toString());
            }
            zzb = new zzb(zza);
            zzb.zza(serviceConnection, str);
            zzb.zzdg(str);
            this.zzarR.put(zza, zzb);
            isBound = zzb.isBound();
        }
        return isBound;
    }

    private void zzb(zza zza, ServiceConnection serviceConnection, String str) {
        zzaa.zzb((Object) serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.zzarR) {
            zzb zzb = (zzb) this.zzarR.get(zza);
            String valueOf;
            if (zzb == null) {
                valueOf = String.valueOf(zza);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 50).append("Nonexistent connection status for service config: ").append(valueOf).toString());
            } else if (zzb.zza(serviceConnection)) {
                zzb.zzb(serviceConnection, str);
                if (zzb.zztL()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, zzb), this.zzarT);
                }
            } else {
                valueOf = String.valueOf(zza);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 76).append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=").append(valueOf).toString());
            }
        }
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                zzb zzb = (zzb) message.obj;
                synchronized (this.zzarR) {
                    if (zzb.zztL()) {
                        if (zzb.isBound()) {
                            zzb.zzdh("GmsClientSupervisor");
                        }
                        this.zzarR.remove(zzb.zzarZ);
                    }
                }
                return true;
            default:
                return false;
        }
    }

    public boolean zza(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        return zza(new zza(componentName), serviceConnection, str);
    }

    public boolean zza(String str, String str2, ServiceConnection serviceConnection, String str3) {
        return zza(new zza(str, str2), serviceConnection, str3);
    }

    public void zzb(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        zzb(new zza(componentName), serviceConnection, str);
    }

    public void zzb(String str, String str2, ServiceConnection serviceConnection, String str3) {
        zzb(new zza(str, str2), serviceConnection, str3);
    }
}
