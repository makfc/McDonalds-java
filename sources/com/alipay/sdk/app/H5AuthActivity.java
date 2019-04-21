package com.alipay.sdk.app;

public class H5AuthActivity extends H5PayActivity {
    /* renamed from: a */
    public void mo7969a() {
        Object obj = AuthTask.f453a;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception e) {
            }
        }
    }
}
