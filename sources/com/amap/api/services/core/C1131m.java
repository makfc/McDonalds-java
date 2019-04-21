package com.amap.api.services.core;

import android.os.HandlerThread;
import android.os.Message;

/* compiled from: ManifestConfig */
/* renamed from: com.amap.api.services.core.m */
class C1131m extends HandlerThread {
    /* renamed from: a */
    final /* synthetic */ ManifestConfig f3797a;

    C1131m(ManifestConfig manifestConfig, String str) {
        this.f3797a = manifestConfig;
        super(str);
    }

    public void run() {
        String str = "run";
        Thread.currentThread().setName("ManifestConfigThread");
        Message message = new Message();
        try {
            message.obj = new ManifestManager(ManifestConfig.f3794c).mo12109a();
            message.what = 3;
            if (this.f3797a.f3795d != null) {
                this.f3797a.f3795d.sendMessage(message);
            }
        } catch (Throwable th) {
            message.what = 3;
            if (this.f3797a.f3795d != null) {
                this.f3797a.f3795d.sendMessage(message);
            }
        }
        try {
            C1131m.sleep(10000);
        } catch (InterruptedException e) {
            C1128d.m4975a(e, "ManifestConfig", "mVerfy");
        }
    }
}
