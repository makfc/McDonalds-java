package com.baidu.android.pushservice.p026j;

import android.text.TextUtils;
import com.baidu.android.pushservice.p036h.C1425a;

/* renamed from: com.baidu.android.pushservice.j.c */
public abstract class C1281c implements Runnable {
    /* renamed from: a */
    private String f4589a;
    /* renamed from: b */
    private short f4590b = (short) 99;

    public C1281c(String str, short s) {
        this.f4589a = str;
        this.f4590b = s;
    }

    /* renamed from: a */
    public abstract void mo13487a();

    /* renamed from: a */
    public void mo13488a(short s) {
        this.f4590b = s;
    }

    /* renamed from: c */
    public void mo13489c(String str) {
        this.f4589a = str;
    }

    /* renamed from: d */
    public short mo13490d() {
        return this.f4590b;
    }

    public final void run() {
        if (!TextUtils.isEmpty(this.f4589a)) {
            Thread.currentThread().setName(this.f4589a);
        }
        C1425a.m6442c("PushRunnable", "running: " + this.f4589a);
        mo13487a();
    }
}
