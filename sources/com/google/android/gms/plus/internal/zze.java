package com.google.android.gms.plus.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.plus.People.LoadPeopleResult;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.internal.model.people.PersonEntity;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class zze extends zzk<zzd> {
    private Person zzblw;
    private final PlusSession zzblx;

    static final class zza implements LoadPeopleResult {
        private final Status zzaaO;
        private final String zzbly;
        private final PersonBuffer zzblz;

        public zza(Status status, DataHolder dataHolder, String str) {
            this.zzaaO = status;
            this.zzbly = str;
            this.zzblz = dataHolder != null ? new PersonBuffer(dataHolder) : null;
        }

        public Status getStatus() {
            return this.zzaaO;
        }

        public void release() {
            if (this.zzblz != null) {
                this.zzblz.release();
            }
        }
    }

    static final class zzb extends zza {
        private final com.google.android.gms.internal.zznt.zzb<LoadPeopleResult> zzaWd;

        public zzb(com.google.android.gms.internal.zznt.zzb<LoadPeopleResult> zzb) {
            this.zzaWd = zzb;
        }

        public void zza(DataHolder dataHolder, String str) {
            Status status = new Status(dataHolder.getStatusCode(), null, dataHolder.zzsO() != null ? (PendingIntent) dataHolder.zzsO().getParcelable("pendingIntent") : null);
            if (!(status.isSuccess() || dataHolder == null)) {
                if (!dataHolder.isClosed()) {
                    dataHolder.close();
                }
                dataHolder = null;
            }
            this.zzaWd.setResult(new zza(status, dataHolder, str));
        }
    }

    static final class zzc extends zza {
        private final com.google.android.gms.internal.zznt.zzb<Status> zzaWd;

        public zzc(com.google.android.gms.internal.zznt.zzb<Status> zzb) {
            this.zzaWd = zzb;
        }

        public void zzk(int i, Bundle bundle) {
            this.zzaWd.setResult(new Status(i, null, bundle != null ? (PendingIntent) bundle.getParcelable("pendingIntent") : null));
        }
    }

    public zze(Context context, Looper looper, zzg zzg, PlusSession plusSession, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 2, zzg, connectionCallbacks, onConnectionFailedListener);
        this.zzblx = plusSession;
    }

    public static boolean zzd(Set<Scope> set) {
        return (set == null || set.isEmpty()) ? false : (set.size() == 1 && set.contains(new Scope("plus_one_placeholder_scope"))) ? false : true;
    }

    public String getAccountName() {
        zztl();
        try {
            return ((zzd) zztm()).getAccountName();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public void zzIk() {
        zztl();
        try {
            this.zzblw = null;
            ((zzd) zztm()).zzIk();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public Person zzIm() {
        zztl();
        return this.zzblw;
    }

    public zzr zza(com.google.android.gms.internal.zznt.zzb<LoadPeopleResult> zzb, int i, String str) {
        zztl();
        zzb zzb2 = new zzb(zzb);
        try {
            return ((zzd) zztm()).zza(zzb2, 1, i, -1, str);
        } catch (RemoteException e) {
            zzb2.zza(DataHolder.zzbQ(8), null);
            return null;
        }
    }

    /* Access modifiers changed, original: protected */
    public void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        if (i == 0 && bundle != null && bundle.containsKey("loaded_person")) {
            this.zzblw = PersonEntity.zzx(bundle.getByteArray("loaded_person"));
        }
        super.zza(i, iBinder, bundle, i2);
    }

    public void zza(com.google.android.gms.internal.zznt.zzb<LoadPeopleResult> zzb, Collection<String> collection) {
        zztl();
        zzb zzb2 = new zzb(zzb);
        try {
            ((zzd) zztm()).zza(zzb2, new ArrayList(collection));
        } catch (RemoteException e) {
            zzb2.zza(DataHolder.zzbQ(8), null);
        }
    }

    public void zzd(com.google.android.gms.internal.zznt.zzb<LoadPeopleResult> zzb, String[] strArr) {
        zza(zzb, Arrays.asList(strArr));
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: zzdV */
    public zzd zzab(IBinder iBinder) {
        return com.google.android.gms.plus.internal.zzd.zza.zzdU(iBinder);
    }

    /* Access modifiers changed, original: protected */
    public String zzhT() {
        return "com.google.android.gms.plus.service.START";
    }

    /* Access modifiers changed, original: protected */
    public String zzhU() {
        return "com.google.android.gms.plus.internal.IPlusService";
    }

    /* Access modifiers changed, original: protected */
    public Bundle zzoO() {
        Bundle zzIw = this.zzblx.zzIw();
        zzIw.putStringArray("request_visible_actions", this.zzblx.zzIq());
        zzIw.putString("auth_package", this.zzblx.zzIs());
        return zzIw;
    }

    public boolean zzpd() {
        return zzd(zztH().zzb(Plus.API));
    }

    public void zzt(com.google.android.gms.internal.zznt.zzb<LoadPeopleResult> zzb) {
        zztl();
        zzb zzb2 = new zzb(zzb);
        try {
            ((zzd) zztm()).zza(zzb2, 2, 1, -1, null);
        } catch (RemoteException e) {
            zzb2.zza(DataHolder.zzbQ(8), null);
        }
    }

    public zzr zzu(com.google.android.gms.internal.zznt.zzb<LoadPeopleResult> zzb, String str) {
        return zza(zzb, 0, str);
    }

    public void zzu(com.google.android.gms.internal.zznt.zzb<Status> zzb) {
        zztl();
        zzIk();
        zzc zzc = new zzc(zzb);
        try {
            ((zzd) zztm()).zzb(zzc);
        } catch (RemoteException e) {
            zzc.zzk(8, null);
        }
    }
}
