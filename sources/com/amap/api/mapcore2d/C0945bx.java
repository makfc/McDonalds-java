package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;

/* compiled from: UiSettingsDelegateImp */
/* renamed from: com.amap.api.mapcore2d.bx */
class C0945bx implements IUiSettingsDelegate {
    /* renamed from: a */
    private IAMapDelegate f2649a;
    /* renamed from: b */
    private boolean f2650b = true;
    /* renamed from: c */
    private boolean f2651c = false;
    /* renamed from: d */
    private boolean f2652d = true;
    /* renamed from: e */
    private boolean f2653e = true;
    /* renamed from: f */
    private boolean f2654f = true;
    /* renamed from: g */
    private boolean f2655g = false;
    /* renamed from: h */
    private int f2656h = 0;
    /* renamed from: i */
    private int f2657i = 0;
    /* renamed from: j */
    private final Handler f2658j = new C09441();

    /* compiled from: UiSettingsDelegateImp */
    /* renamed from: com.amap.api.mapcore2d.bx$1 */
    class C09441 extends Handler {
        C09441() {
        }

        public void handleMessage(Message message) {
            if (message != null && C0945bx.this.f2649a != null) {
                try {
                    switch (message.what) {
                        case 0:
                            C0945bx.this.f2649a.mo9982d(C0945bx.this.f2653e);
                            return;
                        case 1:
                            C0945bx.this.f2649a.mo9988g(C0945bx.this.f2655g);
                            return;
                        case 2:
                            C0945bx.this.f2649a.mo9986f(C0945bx.this.f2654f);
                            return;
                        case 3:
                            C0945bx.this.f2649a.mo9984e(C0945bx.this.f2651c);
                            return;
                        default:
                            return;
                    }
                } catch (Throwable th) {
                    C0955ck.m3888a(th, "UiSettingsDelegateImp", "handle_handleMessage");
                }
                C0955ck.m3888a(th, "UiSettingsDelegateImp", "handle_handleMessage");
            }
        }
    }

    C0945bx(IAMapDelegate iAMapDelegate) {
        this.f2649a = iAMapDelegate;
    }

    /* renamed from: a */
    public void mo9715a(boolean z) throws RemoteException {
        this.f2655g = z;
        this.f2658j.obtainMessage(1).sendToTarget();
    }

    /* renamed from: b */
    public void mo9718b(boolean z) throws RemoteException {
        this.f2653e = z;
        this.f2658j.obtainMessage(0).sendToTarget();
    }

    /* renamed from: c */
    public void mo9720c(boolean z) throws RemoteException {
        this.f2654f = z;
        this.f2658j.obtainMessage(2).sendToTarget();
    }

    /* renamed from: d */
    public void mo9722d(boolean z) throws RemoteException {
        this.f2651c = z;
        this.f2658j.obtainMessage(3).sendToTarget();
    }

    /* renamed from: e */
    public void mo9724e(boolean z) throws RemoteException {
        this.f2650b = z;
    }

    /* renamed from: f */
    public void mo9726f(boolean z) throws RemoteException {
        this.f2652d = z;
    }

    /* renamed from: g */
    public void mo9729g(boolean z) throws RemoteException {
        mo9726f(z);
        mo9724e(z);
    }

    /* renamed from: a */
    public void mo9714a(int i) throws RemoteException {
        this.f2656h = i;
        this.f2649a.mo9973b(i);
    }

    /* renamed from: b */
    public void mo9717b(int i) throws RemoteException {
        this.f2657i = i;
        this.f2649a.mo9978c(i);
    }

    /* renamed from: a */
    public boolean mo9716a() throws RemoteException {
        return this.f2655g;
    }

    /* renamed from: b */
    public boolean mo9719b() throws RemoteException {
        return this.f2653e;
    }

    /* renamed from: c */
    public boolean mo9721c() throws RemoteException {
        return this.f2654f;
    }

    /* renamed from: d */
    public boolean mo9723d() throws RemoteException {
        return this.f2651c;
    }

    /* renamed from: e */
    public boolean mo9725e() throws RemoteException {
        return this.f2650b;
    }

    /* renamed from: f */
    public boolean mo9727f() throws RemoteException {
        return this.f2652d;
    }

    /* renamed from: g */
    public int mo9728g() throws RemoteException {
        return this.f2656h;
    }

    /* renamed from: h */
    public int mo9730h() throws RemoteException {
        return this.f2657i;
    }
}
