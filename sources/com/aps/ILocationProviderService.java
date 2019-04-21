package com.aps;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.aps.a */
public interface ILocationProviderService extends IInterface {

    /* compiled from: ILocationProviderService */
    /* renamed from: com.aps.a$a */
    public static abstract class C1211a extends Binder implements ILocationProviderService {

        /* compiled from: ILocationProviderService */
        /* renamed from: com.aps.a$a$a */
        private static class C1212a implements ILocationProviderService {
            /* renamed from: a */
            private IBinder f4133a;

            C1212a(IBinder iBinder) {
                this.f4133a = iBinder;
            }

            public IBinder asBinder() {
                return this.f4133a;
            }

            /* renamed from: a */
            public int mo13074a(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.amap.api.service.locationprovider.ILocationProviderService");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f4133a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public C1211a() {
            attachInterface(this, "com.amap.api.service.locationprovider.ILocationProviderService");
        }

        /* renamed from: a */
        public static ILocationProviderService m5267a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.amap.api.service.locationprovider.ILocationProviderService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ILocationProviderService)) {
                return new C1212a(iBinder);
            }
            return (ILocationProviderService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    Bundle bundle;
                    parcel.enforceInterface("com.amap.api.service.locationprovider.ILocationProviderService");
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    int a = mo13074a(bundle);
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    if (bundle != null) {
                        parcel2.writeInt(1);
                        bundle.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 1598968902:
                    parcel2.writeString("com.amap.api.service.locationprovider.ILocationProviderService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    /* renamed from: a */
    int mo13074a(Bundle bundle) throws RemoteException;
}
