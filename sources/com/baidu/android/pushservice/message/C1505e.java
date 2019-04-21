package com.baidu.android.pushservice.message;

import android.content.Context;
import com.baidu.android.pushservice.p036h.C1425a;
import java.io.IOException;
import java.util.LinkedList;

/* renamed from: com.baidu.android.pushservice.message.e */
public abstract class C1505e {
    /* renamed from: a */
    protected Context f5256a;
    /* renamed from: b */
    private LinkedList<C1506f> f5257b = new LinkedList();

    public C1505e(Context context) {
        this.f5256a = context;
    }

    /* renamed from: a */
    public abstract C1506f mo13976a(byte[] bArr, int i) throws IOException;

    /* renamed from: a */
    public LinkedList<C1506f> mo13977a() {
        return this.f5257b;
    }

    /* renamed from: a */
    public abstract void mo13978a(int i);

    /* renamed from: a */
    public void mo13979a(C1506f c1506f) {
        synchronized (this.f5257b) {
            try {
                this.f5257b.add(c1506f);
                this.f5257b.notify();
            } catch (Exception e) {
                C1425a.m6440a("IMessageHandler", e);
            }
        }
    }

    /* renamed from: b */
    public abstract void mo13980b();

    /* renamed from: b */
    public abstract void mo13981b(C1506f c1506f) throws Exception;

    /* renamed from: c */
    public abstract void mo13982c();
}
