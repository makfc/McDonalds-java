package com.alipay.sdk.util;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.alipay.android.app.IRemoteServiceCallback.Stub;

/* renamed from: com.alipay.sdk.util.g */
class C0650g extends Stub {
    /* renamed from: a */
    final /* synthetic */ C0648e f660a;

    C0650g(C0648e c0648e) {
        this.f660a = c0648e;
    }

    public boolean isHideLoadingScreen() throws RemoteException {
        return false;
    }

    public void payEnd(boolean z, String str) throws RemoteException {
    }

    public void startActivity(String str, String str2, int i, Bundle bundle) throws RemoteException {
        Intent intent = new Intent("android.intent.action.MAIN", null);
        if (bundle == null) {
            bundle = new Bundle();
        }
        try {
            bundle.putInt("CallingPid", i);
            intent.putExtras(bundle);
        } catch (Exception e) {
        }
        intent.setClassName(str, str2);
        if (this.f660a.f652b != null) {
            this.f660a.f652b.startActivity(intent);
        }
        this.f660a.f656f.mo7993b();
    }
}
