package com.google.android.gms.gcm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PendingCallback implements Parcelable {
    public static final Creator<PendingCallback> CREATOR = new C21431();
    final IBinder zzaqQ;

    /* renamed from: com.google.android.gms.gcm.PendingCallback$1 */
    class C21431 implements Creator<PendingCallback> {
        C21431() {
        }

        /* renamed from: zzeN */
        public PendingCallback createFromParcel(Parcel parcel) {
            return new PendingCallback(parcel);
        }

        /* renamed from: zzhI */
        public PendingCallback[] newArray(int i) {
            return new PendingCallback[i];
        }
    }

    public PendingCallback(Parcel parcel) {
        this.zzaqQ = parcel.readStrongBinder();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.zzaqQ);
    }
}
