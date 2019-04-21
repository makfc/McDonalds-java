package com.google.android.gms.location.places.personalized;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface zzc extends IInterface {

    public static abstract class zza extends Binder implements zzc {

        private static class zza implements zzc {
            private IBinder zzpb;

            zza(IBinder iBinder) {
                this.zzpb = iBinder;
            }

            public IBinder asBinder() {
                return this.zzpb;
            }

            public void zza(AliasedPlacesResult aliasedPlacesResult) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.places.personalized.IAliasedPlacesCallbacks");
                    if (aliasedPlacesResult != null) {
                        obtain.writeInt(1);
                        aliasedPlacesResult.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzpb.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void zzb(AliasedPlacesResult aliasedPlacesResult) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.places.personalized.IAliasedPlacesCallbacks");
                    if (aliasedPlacesResult != null) {
                        obtain.writeInt(1);
                        aliasedPlacesResult.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzpb.transact(3, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void zzc(AliasedPlacesResult aliasedPlacesResult) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.places.personalized.IAliasedPlacesCallbacks");
                    if (aliasedPlacesResult != null) {
                        obtain.writeInt(1);
                        aliasedPlacesResult.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzpb.transact(4, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static zzc zzcz(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.places.personalized.IAliasedPlacesCallbacks");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzc)) ? new zza(iBinder) : (zzc) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            AliasedPlacesResult aliasedPlacesResult = null;
            switch (i) {
                case 2:
                    parcel.enforceInterface("com.google.android.gms.location.places.personalized.IAliasedPlacesCallbacks");
                    if (parcel.readInt() != 0) {
                        aliasedPlacesResult = (AliasedPlacesResult) AliasedPlacesResult.CREATOR.createFromParcel(parcel);
                    }
                    zza(aliasedPlacesResult);
                    return true;
                case 3:
                    parcel.enforceInterface("com.google.android.gms.location.places.personalized.IAliasedPlacesCallbacks");
                    if (parcel.readInt() != 0) {
                        aliasedPlacesResult = (AliasedPlacesResult) AliasedPlacesResult.CREATOR.createFromParcel(parcel);
                    }
                    zzb(aliasedPlacesResult);
                    return true;
                case 4:
                    parcel.enforceInterface("com.google.android.gms.location.places.personalized.IAliasedPlacesCallbacks");
                    if (parcel.readInt() != 0) {
                        aliasedPlacesResult = (AliasedPlacesResult) AliasedPlacesResult.CREATOR.createFromParcel(parcel);
                    }
                    zzc(aliasedPlacesResult);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.location.places.personalized.IAliasedPlacesCallbacks");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void zza(AliasedPlacesResult aliasedPlacesResult) throws RemoteException;

    void zzb(AliasedPlacesResult aliasedPlacesResult) throws RemoteException;

    void zzc(AliasedPlacesResult aliasedPlacesResult) throws RemoteException;
}
