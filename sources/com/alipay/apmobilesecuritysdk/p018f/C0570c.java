package com.alipay.apmobilesecuritysdk.p018f;

import android.os.Process;

/* renamed from: com.alipay.apmobilesecuritysdk.f.c */
final class C0570c implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C0569b f439a;

    C0570c(C0569b c0569b) {
        this.f439a = c0569b;
    }

    public final void run() {
        try {
            Process.setThreadPriority(0);
            while (!this.f439a.f438c.isEmpty()) {
                Runnable runnable = (Runnable) this.f439a.f438c.get(0);
                this.f439a.f438c.remove(0);
                if (runnable != null) {
                    runnable.run();
                }
            }
        } catch (Exception e) {
        } finally {
            this.f439a.f437b = null;
        }
    }
}
