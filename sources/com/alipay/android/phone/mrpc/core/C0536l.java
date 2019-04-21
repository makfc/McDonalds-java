package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/* renamed from: com.alipay.android.phone.mrpc.core.l */
public final class C0536l implements C0519ab {
    /* renamed from: b */
    private static C0536l f353b = null;
    /* renamed from: i */
    private static final ThreadFactory f354i = new C0538n();
    /* renamed from: a */
    Context f355a;
    /* renamed from: c */
    private ThreadPoolExecutor f356c = new ThreadPoolExecutor(10, 11, 3, TimeUnit.SECONDS, new ArrayBlockingQueue(20), f354i, new CallerRunsPolicy());
    /* renamed from: d */
    private C0524b f357d = C0524b.m557a("android");
    /* renamed from: e */
    private long f358e;
    /* renamed from: f */
    private long f359f;
    /* renamed from: g */
    private long f360g;
    /* renamed from: h */
    private int f361h;

    private C0536l(Context context) {
        this.f355a = context;
        try {
            this.f356c.allowCoreThreadTimeOut(true);
        } catch (Exception e) {
        }
        CookieSyncManager.createInstance(this.f355a);
        CookieManager.getInstance().setAcceptCookie(true);
    }

    /* renamed from: a */
    public static final C0536l m584a(Context context) {
        return f353b != null ? f353b : C0536l.m585b(context);
    }

    /* renamed from: b */
    private static final synchronized C0536l m585b(Context context) {
        C0536l c0536l;
        synchronized (C0536l.class) {
            if (f353b != null) {
                c0536l = f353b;
            } else {
                c0536l = new C0536l(context);
                f353b = c0536l;
            }
        }
        return c0536l;
    }

    /* renamed from: a */
    public final C0524b mo7897a() {
        return this.f357d;
    }

    /* renamed from: a */
    public final Future<C0541u> mo7871a(C0539t c0539t) {
        long j = 0;
        if (C0545s.m627a(this.f355a)) {
            String str = "HttpManager" + hashCode() + ": Active Task = %d, Completed Task = %d, All Task = %d,Avarage Speed = %d KB/S, Connetct Time = %d ms, All data size = %d bytes, All enqueueConnect time = %d ms, All socket time = %d ms, All request times = %d times";
            Object[] objArr = new Object[9];
            objArr[0] = Integer.valueOf(this.f356c.getActiveCount());
            objArr[1] = Long.valueOf(this.f356c.getCompletedTaskCount());
            objArr[2] = Long.valueOf(this.f356c.getTaskCount());
            objArr[3] = Long.valueOf(this.f360g == 0 ? 0 : ((this.f358e * 1000) / this.f360g) >> 10);
            if (this.f361h != 0) {
                j = this.f359f / ((long) this.f361h);
            }
            objArr[4] = Long.valueOf(j);
            objArr[5] = Long.valueOf(this.f358e);
            objArr[6] = Long.valueOf(this.f359f);
            objArr[7] = Long.valueOf(this.f360g);
            objArr[8] = Integer.valueOf(this.f361h);
            String.format(str, objArr);
        }
        C0543q c0543q = new C0543q(this, (C0540o) c0539t);
        C0537m c0537m = new C0537m(this, c0543q, c0543q);
        this.f356c.execute(c0537m);
        return c0537m;
    }

    /* renamed from: a */
    public final void mo7898a(long j) {
        this.f358e += j;
    }

    /* renamed from: b */
    public final void mo7899b(long j) {
        this.f359f += j;
        this.f361h++;
    }

    /* renamed from: c */
    public final void mo7900c(long j) {
        this.f360g += j;
    }
}
