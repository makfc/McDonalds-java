package com.baidu.android.pushservice.p027b;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.baidu.android.pushservice.p027b.C1284b.C1285a;

/* renamed from: com.baidu.android.pushservice.b.a */
public interface C1295a extends IInterface {

    /* renamed from: com.baidu.android.pushservice.b.a$a */
    public static abstract class C1296a extends Binder implements C1295a {

        /* renamed from: com.baidu.android.pushservice.b.a$a$a */
        private static class C1297a implements C1295a {
            /* renamed from: a */
            private IBinder f4617a;

            C1297a(IBinder iBinder) {
                this.f4617a = iBinder;
            }

            /* renamed from: a */
            public int mo13514a(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.f4617a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: a */
            public String mo13515a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    this.f4617a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: a */
            public String mo13516a(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    this.f4617a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: a */
            public String mo13517a(String str, int i, boolean z, int i2, int i3) throws RemoteException {
                int i4 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (z) {
                        i4 = 1;
                    }
                    obtain.writeInt(i4);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.f4617a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: a */
            public void mo13518a(String str, String str2, C1284b c1284b) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(c1284b != null ? c1284b.asBinder() : null);
                    this.f4617a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: a */
            public void mo13519a(String str, String str2, boolean z, C1284b c1284b) throws RemoteException {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(c1284b != null ? c1284b.asBinder() : null);
                    this.f4617a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: a */
            public void mo13520a(String str, String str2, boolean z, String str3, C1284b c1284b) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    obtain.writeString(str3);
                    obtain.writeStrongBinder(c1284b != null ? c1284b.asBinder() : null);
                    this.f4617a.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: a */
            public boolean mo13521a(String str, String str2) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.f4617a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: a */
            public boolean mo13522a(String str, String str2, int i) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    this.f4617a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: a */
            public boolean mo13523a(String str, String str2, String str3, String str4) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    this.f4617a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: a */
            public boolean mo13524a(String str, boolean z) throws RemoteException {
                boolean z2 = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    this.f4617a.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.f4617a;
            }

            /* renamed from: b */
            public int mo13525b(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    this.f4617a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: b */
            public int mo13526b(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.f4617a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: b */
            public String mo13527b() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    this.f4617a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: b */
            public void mo13528b(String str, String str2, C1284b c1284b) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(c1284b != null ? c1284b.asBinder() : null);
                    this.f4617a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: b */
            public boolean mo13529b(String str, String str2) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.f4617a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: c */
            public int mo13530c() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    this.f4617a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: c */
            public int mo13531c(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    this.f4617a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: c */
            public int mo13532c(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.f4617a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: d */
            public int mo13533d(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    this.f4617a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: e */
            public boolean mo13534e(String str) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    this.f4617a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: f */
            public String mo13535f(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.baidu.android.pushservice.aidl.IPushService");
                    obtain.writeString(str);
                    this.f4617a.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public C1296a() {
            attachInterface(this, "com.baidu.android.pushservice.aidl.IPushService");
        }

        /* renamed from: a */
        public static C1295a m5802a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.baidu.android.pushservice.aidl.IPushService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C1295a)) ? new C1297a(iBinder) : (C1295a) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int i3 = 0;
            String a;
            boolean a2;
            switch (i) {
                case 1:
                    boolean z;
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    String readString = parcel.readString();
                    String readString2 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    mo13519a(readString, readString2, z, C1285a.m5765a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    mo13518a(parcel.readString(), parcel.readString(), C1285a.m5765a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    mo13528b(parcel.readString(), parcel.readString(), C1285a.m5765a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    a = mo13515a();
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case 5:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    a = mo13527b();
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case 6:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    a = mo13516a(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case 7:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    a = mo13517a(parcel.readString(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case 8:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    i3 = mo13514a(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 9:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    i3 = mo13525b(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 10:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    i3 = mo13526b(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 11:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    i3 = mo13531c(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 12:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    i3 = mo13532c(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 13:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    a2 = mo13521a(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (a2) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 14:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    a2 = mo13523a(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (a2) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 15:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    a2 = mo13522a(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (a2) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 16:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    a2 = mo13529b(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (a2) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 17:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    i3 = mo13533d(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 18:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    a2 = mo13534e(parcel.readString());
                    parcel2.writeNoException();
                    if (a2) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 19:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    a2 = mo13524a(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (a2) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 20:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    i3 = mo13530c();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 21:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    mo13520a(parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readString(), C1285a.m5765a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface("com.baidu.android.pushservice.aidl.IPushService");
                    a = mo13535f(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.baidu.android.pushservice.aidl.IPushService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    /* renamed from: a */
    int mo13514a(String str, int i) throws RemoteException;

    /* renamed from: a */
    String mo13515a() throws RemoteException;

    /* renamed from: a */
    String mo13516a(String str) throws RemoteException;

    /* renamed from: a */
    String mo13517a(String str, int i, boolean z, int i2, int i3) throws RemoteException;

    /* renamed from: a */
    void mo13518a(String str, String str2, C1284b c1284b) throws RemoteException;

    /* renamed from: a */
    void mo13519a(String str, String str2, boolean z, C1284b c1284b) throws RemoteException;

    /* renamed from: a */
    void mo13520a(String str, String str2, boolean z, String str3, C1284b c1284b) throws RemoteException;

    /* renamed from: a */
    boolean mo13521a(String str, String str2) throws RemoteException;

    /* renamed from: a */
    boolean mo13522a(String str, String str2, int i) throws RemoteException;

    /* renamed from: a */
    boolean mo13523a(String str, String str2, String str3, String str4) throws RemoteException;

    /* renamed from: a */
    boolean mo13524a(String str, boolean z) throws RemoteException;

    /* renamed from: b */
    int mo13525b(String str) throws RemoteException;

    /* renamed from: b */
    int mo13526b(String str, int i) throws RemoteException;

    /* renamed from: b */
    String mo13527b() throws RemoteException;

    /* renamed from: b */
    void mo13528b(String str, String str2, C1284b c1284b) throws RemoteException;

    /* renamed from: b */
    boolean mo13529b(String str, String str2) throws RemoteException;

    /* renamed from: c */
    int mo13530c() throws RemoteException;

    /* renamed from: c */
    int mo13531c(String str) throws RemoteException;

    /* renamed from: c */
    int mo13532c(String str, int i) throws RemoteException;

    /* renamed from: d */
    int mo13533d(String str) throws RemoteException;

    /* renamed from: e */
    boolean mo13534e(String str) throws RemoteException;

    /* renamed from: f */
    String mo13535f(String str) throws RemoteException;
}
