package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
public final class BinderWrapper implements Parcelable {
    public static final Creator<BinderWrapper> CREATOR = new C21071();
    private IBinder zzaqQ;

    /* renamed from: com.google.android.gms.common.internal.BinderWrapper$1 */
    class C21071 implements Creator<BinderWrapper> {
        C21071() {
        }

        /* renamed from: zzaj */
        public BinderWrapper createFromParcel(Parcel parcel) {
            return new BinderWrapper(parcel, null);
        }

        /* renamed from: zzca */
        public BinderWrapper[] newArray(int i) {
            return new BinderWrapper[i];
        }
    }

    public BinderWrapper() {
        this.zzaqQ = null;
    }

    private BinderWrapper(Parcel parcel) {
        this.zzaqQ = null;
        this.zzaqQ = parcel.readStrongBinder();
    }

    /* synthetic */ BinderWrapper(Parcel parcel, C21071 c21071) {
        this(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.zzaqQ);
    }
}
