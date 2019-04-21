package com.google.android.gms.iid;

import android.annotation.TargetApi;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public class MessengerCompat implements Parcelable {
    public static final Creator<MessengerCompat> CREATOR = new C21481();
    Messenger zzaUn;
    zzb zzaUo;

    /* renamed from: com.google.android.gms.iid.MessengerCompat$1 */
    class C21481 implements Creator<MessengerCompat> {
        C21481() {
        }

        /* renamed from: zzeS */
        public MessengerCompat createFromParcel(Parcel parcel) {
            IBinder readStrongBinder = parcel.readStrongBinder();
            return readStrongBinder != null ? new MessengerCompat(readStrongBinder) : null;
        }

        /* renamed from: zzhO */
        public MessengerCompat[] newArray(int i) {
            return new MessengerCompat[i];
        }
    }

    private final class zza extends com.google.android.gms.iid.zzb.zza {
        Handler handler;

        zza(Handler handler) {
            this.handler = handler;
        }

        public void send(Message message) throws RemoteException {
            message.arg2 = Binder.getCallingUid();
            this.handler.dispatchMessage(message);
        }
    }

    public MessengerCompat(Handler handler) {
        if (VERSION.SDK_INT >= 21) {
            this.zzaUn = new Messenger(handler);
        } else {
            this.zzaUo = new zza(handler);
        }
    }

    public MessengerCompat(IBinder iBinder) {
        if (VERSION.SDK_INT >= 21) {
            this.zzaUn = new Messenger(iBinder);
        } else {
            this.zzaUo = com.google.android.gms.iid.zzb.zza.zzcl(iBinder);
        }
    }

    public static int zzc(Message message) {
        return VERSION.SDK_INT >= 21 ? zzd(message) : message.arg2;
    }

    @TargetApi(21)
    private static int zzd(Message message) {
        return message.sendingUid;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj == null) {
            return z;
        }
        try {
            return getBinder().equals(((MessengerCompat) obj).getBinder());
        } catch (ClassCastException e) {
            return z;
        }
    }

    public IBinder getBinder() {
        return this.zzaUn != null ? this.zzaUn.getBinder() : this.zzaUo.asBinder();
    }

    public int hashCode() {
        return getBinder().hashCode();
    }

    public void send(Message message) throws RemoteException {
        if (this.zzaUn != null) {
            this.zzaUn.send(message);
        } else {
            this.zzaUo.send(message);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.zzaUn != null) {
            parcel.writeStrongBinder(this.zzaUn.getBinder());
        } else {
            parcel.writeStrongBinder(this.zzaUo.asBinder());
        }
    }
}
