package com.amap.api.services.core;

import android.content.Context;
import android.os.Looper;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: SDKLogHandler */
/* renamed from: com.amap.api.services.core.ax */
public class C1099ax implements UncaughtExceptionHandler {
    /* renamed from: a */
    private static C1099ax f3692a;
    /* renamed from: e */
    private static ExecutorService f3693e;
    /* renamed from: b */
    private UncaughtExceptionHandler f3694b;
    /* renamed from: c */
    private Context f3695c;
    /* renamed from: d */
    private boolean f3696d = true;

    /* compiled from: SDKLogHandler */
    /* renamed from: com.amap.api.services.core.ax$a */
    private static class C1098a implements C1097bq {
        /* renamed from: a */
        private Context f3691a;

        C1098a(Context context) {
            this.f3691a = context;
        }

        /* renamed from: a */
        public void mo12038a() {
            try {
                C1107be.m4878b(this.f3691a);
            } catch (Throwable th) {
                C1099ax.m4800a(th, "LogNetListener", "onNetCompleted");
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    static synchronized ExecutorService m4797a() {
        ExecutorService executorService;
        synchronized (C1099ax.class) {
            try {
                if (f3693e == null || f3693e.isShutdown()) {
                    f3693e = Executors.newSingleThreadExecutor();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            executorService = f3693e;
        }
        return executorService;
    }

    /* renamed from: a */
    public static synchronized C1099ax m4796a(Context context, C1081ac c1081ac) throws C1133u {
        C1099ax c1099ax;
        synchronized (C1099ax.class) {
            if (c1081ac == null) {
                throw new C1133u("sdk info is null");
            } else if (c1081ac.mo11988a() == null || "".equals(c1081ac.mo11988a())) {
                throw new C1133u("sdk name is invalid");
            } else {
                try {
                    if (f3692a == null) {
                        f3692a = new C1099ax(context, c1081ac);
                    } else {
                        f3692a.f3696d = false;
                    }
                    f3692a.m4798a(context, c1081ac, f3692a.f3696d);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                c1099ax = f3692a;
            }
        }
        return c1099ax;
    }

    /* renamed from: b */
    public static synchronized C1099ax m4801b() {
        C1099ax c1099ax;
        synchronized (C1099ax.class) {
            c1099ax = f3692a;
        }
        return c1099ax;
    }

    /* renamed from: a */
    public static void m4800a(Throwable th, String str, String str2) {
        if (f3692a != null) {
            f3692a.m4799a(th, 1, str, str2);
        }
    }

    private C1099ax(Context context, C1081ac c1081ac) {
        this.f3695c = context;
        C1120bp.m4945a(new C1098a(context));
        m4802c();
    }

    /* renamed from: c */
    private void m4802c() {
        try {
            this.f3694b = Thread.getDefaultUncaughtExceptionHandler();
            if (this.f3694b == null) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.f3696d = true;
            } else if (this.f3694b.toString().indexOf("com.amap.api") != -1) {
                this.f3696d = false;
            } else {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.f3696d = true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (th != null) {
            m4799a(th, 0, null, null);
            if (this.f3694b != null) {
                this.f3694b.uncaughtException(thread, th);
            }
        }
    }

    /* renamed from: b */
    public void mo12039b(Throwable th, String str, String str2) {
        if (th != null) {
            try {
                m4799a(th, 1, str, str2);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private void m4799a(Throwable th, int i, String str, String str2) {
        C1107be.m4877a(this.f3695c, th, i, str, str2);
    }

    /* renamed from: a */
    private void m4798a(final Context context, final C1081ac c1081ac, final boolean z) {
        try {
            ExecutorService a = C1099ax.m4797a();
            if (a != null && !a.isShutdown()) {
                a.submit(new Runnable() {
                    public void run() {
                        try {
                            synchronized (Looper.getMainLooper()) {
                                new C1087am(context).mo12020a(c1081ac);
                            }
                            if (z) {
                                synchronized (Looper.getMainLooper()) {
                                    C1088ap c1088ap = new C1088ap(context);
                                    C1089ar c1089ar = new C1089ar();
                                    c1089ar.mo12031c(true);
                                    c1089ar.mo12027a(true);
                                    c1089ar.mo12029b(true);
                                    c1088ap.mo12024a(c1089ar);
                                }
                                C1107be.m4876a(C1099ax.this.f3695c);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
