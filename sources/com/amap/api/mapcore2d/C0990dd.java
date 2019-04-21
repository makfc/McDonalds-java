package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Looper;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: SDKLogHandler */
/* renamed from: com.amap.api.mapcore2d.dd */
public class C0990dd extends C0982da implements UncaughtExceptionHandler {
    /* renamed from: e */
    private static ExecutorService f2820e;
    /* renamed from: d */
    private Context f2821d;

    /* compiled from: SDKLogHandler */
    /* renamed from: com.amap.api.mapcore2d.dd$a */
    private static class C0989a implements C0988ee {
        /* renamed from: a */
        private Context f2819a;

        C0989a(Context context) {
            this.f2819a = context;
        }

        /* renamed from: a */
        public void mo10186a() {
            try {
                C0985db.m4085b(this.f2819a);
            } catch (Throwable th) {
                C0982da.m4076a(th, "LogNetListener", "onNetCompleted");
            }
        }
    }

    /* renamed from: a */
    public static synchronized C0990dd m4096a(Context context, C0977cv c0977cv) throws C0956cl {
        C0990dd c0990dd;
        synchronized (C0990dd.class) {
            if (c0977cv == null) {
                throw new C0956cl("sdk info is null");
            } else if (c0977cv.mo10172a() == null || "".equals(c0977cv.mo10172a())) {
                throw new C0956cl("sdk name is invalid");
            } else {
                try {
                    if (C0982da.f2799a == null) {
                        C0982da.f2799a = new C0990dd(context, c0977cv);
                    } else {
                        C0982da.f2799a.f2801c = false;
                    }
                    C0982da.f2799a.mo10181a(context, c0977cv, C0982da.f2799a.f2801c);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                c0990dd = (C0990dd) C0982da.f2799a;
            }
        }
        return c0990dd;
    }

    /* renamed from: a */
    public static synchronized C0990dd m4095a() {
        C0990dd c0990dd;
        synchronized (C0990dd.class) {
            c0990dd = (C0990dd) C0982da.f2799a;
        }
        return c0990dd;
    }

    /* renamed from: b */
    public static void m4098b(Throwable th, String str, String str2) {
        if (C0982da.f2799a != null) {
            C0982da.f2799a.mo10182a(th, 1, str, str2);
        }
    }

    /* renamed from: b */
    public static synchronized void m4097b() {
        synchronized (C0990dd.class) {
            try {
                if (f2820e != null) {
                    f2820e.shutdown();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                if (!(C0982da.f2799a == null || Thread.getDefaultUncaughtExceptionHandler() != C0982da.f2799a || C0982da.f2799a.f2800b == null)) {
                    Thread.setDefaultUncaughtExceptionHandler(C0982da.f2799a.f2800b);
                }
                C0982da.f2799a = null;
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
        return;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (th != null) {
            mo10182a(th, 0, null, null);
            if (this.f2800b != null) {
                try {
                    Thread.setDefaultUncaughtExceptionHandler(this.f2800b);
                } catch (Throwable th2) {
                }
                this.f2800b.uncaughtException(thread, th);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo10182a(Throwable th, int i, String str, String str2) {
        C0985db.m4083a(this.f2821d, th, i, str, str2);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo10181a(final Context context, final C0977cv c0977cv, final boolean z) {
        try {
            ExecutorService c = C0990dd.m4099c();
            if (c != null && !c.isShutdown()) {
                c.submit(new Runnable() {
                    public void run() {
                        try {
                            synchronized (Looper.getMainLooper()) {
                                new C1008dt(context, true).mo10233a(c0977cv);
                            }
                            if (z) {
                                synchronized (Looper.getMainLooper()) {
                                    C1009du c1009du = new C1009du(context);
                                    C1010dv c1010dv = new C1010dv();
                                    c1010dv.mo10240c(true);
                                    c1010dv.mo10236a(true);
                                    c1010dv.mo10238b(true);
                                    c1009du.mo10235a(c1010dv);
                                }
                                C0985db.m4082a(C0990dd.this.f2821d);
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

    private C0990dd(Context context, C0977cv c0977cv) {
        this.f2821d = context;
        C1024ed.m4268a(new C0989a(context));
        m4100d();
    }

    /* renamed from: d */
    private void m4100d() {
        try {
            this.f2800b = Thread.getDefaultUncaughtExceptionHandler();
            if (this.f2800b == null) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.f2801c = true;
            } else if (this.f2800b.toString().indexOf("com.amap.api") != -1) {
                this.f2801c = false;
            } else {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.f2801c = true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: c */
    public void mo10187c(Throwable th, String str, String str2) {
        if (th != null) {
            try {
                mo10182a(th, 1, str, str2);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    /* renamed from: c */
    static synchronized ExecutorService m4099c() {
        ExecutorService executorService;
        synchronized (C0990dd.class) {
            try {
                if (f2820e == null || f2820e.isShutdown()) {
                    f2820e = Executors.newSingleThreadExecutor();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            executorService = f2820e;
        }
        return executorService;
    }
}
