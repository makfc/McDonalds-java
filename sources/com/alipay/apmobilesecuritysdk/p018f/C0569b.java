package com.alipay.apmobilesecuritysdk.p018f;

import java.util.LinkedList;

/* renamed from: com.alipay.apmobilesecuritysdk.f.b */
public final class C0569b {
    /* renamed from: a */
    private static C0569b f436a = new C0569b();
    /* renamed from: b */
    private Thread f437b = null;
    /* renamed from: c */
    private LinkedList<Runnable> f438c = new LinkedList();

    /* renamed from: a */
    public static C0569b m734a() {
        return f436a;
    }

    /* renamed from: a */
    public final synchronized void mo7954a(Runnable runnable) {
        this.f438c.add(runnable);
        if (this.f437b == null) {
            this.f437b = new Thread(new C0570c(this));
            this.f437b.start();
        }
    }
}
