package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Looper;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/* renamed from: com.amap.api.mapcore.util.ee */
public class SDKLogHandler extends BasicLogHandler implements UncaughtExceptionHandler {
    /* renamed from: e */
    private static ExecutorService f1865e;
    /* renamed from: d */
    private Context f1866d;

    /* compiled from: SDKLogHandler */
    /* renamed from: com.amap.api.mapcore.util.ee$a */
    private static class C0830a implements NetCompleteListener {
        /* renamed from: a */
        private Context f1864a;

        C0830a(Context context) {
            this.f1864a = context;
        }

        /* renamed from: a */
        public void mo9307a() {
            try {
                Log.m2551b(this.f1864a);
            } catch (Throwable th) {
                BasicLogHandler.m2542a(th, "LogNetListener", "onNetCompleted");
            }
        }
    }

    /* renamed from: a */
    public static synchronized SDKLogHandler m2562a(Context context, SDKInfo sDKInfo) throws AMapCoreException {
        SDKLogHandler sDKLogHandler;
        synchronized (SDKLogHandler.class) {
            if (sDKInfo == null) {
                throw new AMapCoreException("sdk info is null");
            } else if (sDKInfo.mo9292a() == null || "".equals(sDKInfo.mo9292a())) {
                throw new AMapCoreException("sdk name is invalid");
            } else {
                try {
                    if (BasicLogHandler.f1844a == null) {
                        BasicLogHandler.f1844a = new SDKLogHandler(context, sDKInfo);
                    } else {
                        BasicLogHandler.f1844a.f1846c = false;
                    }
                    BasicLogHandler.f1844a.mo9302a(context, sDKInfo, BasicLogHandler.f1844a.f1846c);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                sDKLogHandler = (SDKLogHandler) BasicLogHandler.f1844a;
            }
        }
        return sDKLogHandler;
    }

    /* renamed from: a */
    public static synchronized SDKLogHandler m2561a() {
        SDKLogHandler sDKLogHandler;
        synchronized (SDKLogHandler.class) {
            sDKLogHandler = (SDKLogHandler) BasicLogHandler.f1844a;
        }
        return sDKLogHandler;
    }

    /* renamed from: a */
    public static void m2563a(Throwable th, String str, String str2) {
        if (BasicLogHandler.f1844a != null) {
            BasicLogHandler.f1844a.mo9303a(th, 1, str, str2);
        }
    }

    /* renamed from: b */
    public static synchronized void m2564b() {
        synchronized (SDKLogHandler.class) {
            try {
                if (f1865e != null) {
                    f1865e.shutdown();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                if (!(BasicLogHandler.f1844a == null || Thread.getDefaultUncaughtExceptionHandler() != BasicLogHandler.f1844a || BasicLogHandler.f1844a.f1845b == null)) {
                    Thread.setDefaultUncaughtExceptionHandler(BasicLogHandler.f1844a.f1845b);
                }
                BasicLogHandler.f1844a = null;
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
        return;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (th != null) {
            mo9303a(th, 0, null, null);
            if (this.f1845b != null) {
                try {
                    Thread.setDefaultUncaughtExceptionHandler(this.f1845b);
                } catch (Throwable th2) {
                }
                this.f1845b.uncaughtException(thread, th);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo9303a(Throwable th, int i, String str, String str2) {
        Log.m2549a(this.f1866d, th, i, str, str2);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo9302a(final Context context, final SDKInfo sDKInfo, final boolean z) {
        try {
            ExecutorService c = SDKLogHandler.m2565c();
            if (c != null && !c.isShutdown()) {
                c.submit(new Runnable() {
                    public void run() {
                        try {
                            synchronized (Looper.getMainLooper()) {
                                new SDKDBOperation(context, true).mo9352a(sDKInfo);
                            }
                            if (z) {
                                synchronized (Looper.getMainLooper()) {
                                    UpdateLogDBOperation updateLogDBOperation = new UpdateLogDBOperation(context);
                                    UpdateLogInfo updateLogInfo = new UpdateLogInfo();
                                    updateLogInfo.mo9359c(true);
                                    updateLogInfo.mo9355a(true);
                                    updateLogInfo.mo9357b(true);
                                    updateLogDBOperation.mo9354a(updateLogInfo);
                                }
                                Log.m2548a(SDKLogHandler.this.f1866d);
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

    private SDKLogHandler(Context context, SDKInfo sDKInfo) {
        this.f1866d = context;
        HttpUrlUtil.m2809a(new C0830a(context));
        m2566d();
    }

    /* renamed from: d */
    private void m2566d() {
        try {
            this.f1845b = Thread.getDefaultUncaughtExceptionHandler();
            if (this.f1845b == null) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.f1846c = true;
            } else if (this.f1845b.toString().indexOf("com.amap.api") != -1) {
                this.f1846c = false;
            } else {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.f1846c = true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    public void mo9308b(Throwable th, String str, String str2) {
        if (th != null) {
            try {
                mo9303a(th, 1, str, str2);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    /* renamed from: c */
    static synchronized ExecutorService m2565c() {
        ExecutorService executorService;
        synchronized (SDKLogHandler.class) {
            try {
                if (f1865e == null || f1865e.isShutdown()) {
                    f1865e = Executors.newSingleThreadExecutor();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            executorService = f1865e;
        }
        return executorService;
    }
}
