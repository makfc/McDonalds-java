package com.google.android.gms.analytics;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.gms.analytics.internal.zzao;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzln;
import com.google.android.gms.internal.zzls;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzi {
    private static volatile zzi zzUz;
    private final Context mContext;
    private final List<zzj> zzUA = new CopyOnWriteArrayList();
    private final zzd zzUB = new zzd();
    private final zza zzUC = new zza();
    private volatile zzln zzUD;
    private UncaughtExceptionHandler zzUE;

    private class zza extends ThreadPoolExecutor {
        public zza() {
            super(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
            setThreadFactory(new zzb());
        }

        /* Access modifiers changed, original: protected */
        public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
            return new FutureTask<T>(runnable, t) {
                /* Access modifiers changed, original: protected */
                public void setException(Throwable th) {
                    UncaughtExceptionHandler zzb = zzi.this.zzUE;
                    if (zzb != null) {
                        zzb.uncaughtException(Thread.currentThread(), th);
                    } else if (Log.isLoggable("GAv4", 6)) {
                        String valueOf = String.valueOf(th);
                        Log.e("GAv4", new StringBuilder(String.valueOf(valueOf).length() + 37).append("MeasurementExecutor: job failed with ").append(valueOf).toString());
                    }
                    super.setException(th);
                }
            };
        }
    }

    private static class zzb implements ThreadFactory {
        private static final AtomicInteger zzUI = new AtomicInteger();

        private zzb() {
        }

        /* synthetic */ zzb(C20931 c20931) {
            this();
        }

        public Thread newThread(Runnable runnable) {
            return new zzc(runnable, "measurement-" + zzUI.incrementAndGet());
        }
    }

    private static class zzc extends Thread {
        zzc(Runnable runnable, String str) {
            super(runnable, str);
        }

        public void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }

    zzi(Context context) {
        Context applicationContext = context.getApplicationContext();
        zzaa.zzz(applicationContext);
        this.mContext = applicationContext;
    }

    public static zzi zzW(Context context) {
        zzaa.zzz(context);
        if (zzUz == null) {
            synchronized (zzi.class) {
                if (zzUz == null) {
                    zzUz = new zzi(context);
                }
            }
        }
        return zzUz;
    }

    private void zzb(zze zze) {
        zzaa.zzdd("deliver should be called from worker thread");
        zzaa.zzb(zze.zzkD(), (Object) "Measurement must be submitted");
        List<zzk> zzkA = zze.zzkA();
        if (!zzkA.isEmpty()) {
            HashSet hashSet = new HashSet();
            for (zzk zzk : zzkA) {
                Uri zzkn = zzk.zzkn();
                if (!hashSet.contains(zzkn)) {
                    hashSet.add(zzkn);
                    zzk.zzb(zze);
                }
            }
        }
    }

    public static void zzkN() {
        if (!(Thread.currentThread() instanceof zzc)) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public void zza(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzUE = uncaughtExceptionHandler;
    }

    public <V> Future<V> zzc(Callable<V> callable) {
        zzaa.zzz(callable);
        if (!(Thread.currentThread() instanceof zzc)) {
            return this.zzUC.submit(callable);
        }
        FutureTask futureTask = new FutureTask(callable);
        futureTask.run();
        return futureTask;
    }

    /* Access modifiers changed, original: 0000 */
    public void zze(zze zze) {
        if (zze.zzkH()) {
            throw new IllegalStateException("Measurement prototype can't be submitted");
        } else if (zze.zzkD()) {
            throw new IllegalStateException("Measurement can only be submitted once");
        } else {
            final zze zzky = zze.zzky();
            zzky.zzkE();
            this.zzUC.execute(new Runnable() {
                public void run() {
                    zzky.zzkF().zza(zzky);
                    for (zzj zza : zzi.this.zzUA) {
                        zza.zza(zzky);
                    }
                    zzi.this.zzb(zzky);
                }
            });
        }
    }

    public void zzf(Runnable runnable) {
        zzaa.zzz(runnable);
        this.zzUC.submit(runnable);
    }

    public zzln zzkL() {
        if (this.zzUD == null) {
            synchronized (this) {
                if (this.zzUD == null) {
                    zzln zzln = new zzln();
                    PackageManager packageManager = this.mContext.getPackageManager();
                    String packageName = this.mContext.getPackageName();
                    zzln.setAppId(packageName);
                    zzln.setAppInstallerId(packageManager.getInstallerPackageName(packageName));
                    String str = null;
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(this.mContext.getPackageName(), 0);
                        if (packageInfo != null) {
                            CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                            if (!TextUtils.isEmpty(applicationLabel)) {
                                packageName = applicationLabel.toString();
                            }
                            str = packageInfo.versionName;
                        }
                    } catch (NameNotFoundException e) {
                        String str2 = "GAv4";
                        String str3 = "Error retrieving package info: appName set to ";
                        String valueOf = String.valueOf(packageName);
                        Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                    }
                    zzln.setAppName(packageName);
                    zzln.setAppVersion(str);
                    this.zzUD = zzln;
                }
            }
        }
        return this.zzUD;
    }

    public zzls zzkM() {
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        zzls zzls = new zzls();
        zzls.setLanguage(zzao.zza(Locale.getDefault()));
        zzls.zzao(displayMetrics.widthPixels);
        zzls.zzap(displayMetrics.heightPixels);
        return zzls;
    }
}
