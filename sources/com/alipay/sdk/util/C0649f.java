package com.alipay.sdk.util;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.alipay.android.app.IAlixPay.Stub;

/* renamed from: com.alipay.sdk.util.f */
class C0649f implements ServiceConnection {
    /* renamed from: a */
    final /* synthetic */ C0648e f659a;

    C0649f(C0648e c0648e) {
        this.f659a = c0648e;
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.f659a.f653c = null;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.f659a.f654d) {
            this.f659a.f653c = Stub.asInterface(iBinder);
            this.f659a.f654d.notify();
        }
    }
}
