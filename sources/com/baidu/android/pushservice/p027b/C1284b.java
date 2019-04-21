package com.baidu.android.pushservice.p027b;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.baidu.android.pushservice.b.b */
public interface C1284b extends IInterface {

    /* renamed from: com.baidu.android.pushservice.b.b$a */
    public static abstract class C1285a extends Binder implements C1284b {

        /* renamed from: com.baidu.android.pushservice.b.b$a$a */
        private static class C1286a implements C1284b {
            /* renamed from: a */
            private IBinder f4593a;

            C1286a(IBinder iBinder) {
                this.f4593a = iBinder;
            }

            /* renamed from: a */
            public void mo13494a(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushServiceListener");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.f4593a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f4593a;
            }

            /* renamed from: b */
            public void mo13495b(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushServiceListener");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.f4593a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: c */
            public void mo13496c(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushServiceListener");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.f4593a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public C1285a() {
            attachInterface(this, "com.baidu.android.pushservice.aidl.IPushServiceListener");
        }

        /* renamed from: a */
        public static C1284b m5765a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.baidu.android.pushservice.aidl.IPushServiceListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C1284b)) ? new C1286a(iBinder) : (C1284b) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushServiceListener");
                    mo13495b(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushServiceListener");
                    mo13494a(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushServiceListener");
                    mo13496c(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.baidu.android.pushservice.aidl.IPushServiceListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    /* renamed from: a */
    void mo13494a(int i, String str) throws RemoteException;

    /* renamed from: b */
    void mo13495b(int i, String str) throws RemoteException;

    /* renamed from: c */
    void mo13496c(int i, String str) throws RemoteException;
}
