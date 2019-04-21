package com.kochava.base;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.AnyThread;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.newrelic.agent.android.payload.PayloadController;
import java.lang.ref.WeakReference;
import org.jetbrains.annotations.Contract;

/* renamed from: com.kochava.base.g */
final class C2904g implements ActivityLifecycleCallbacks, ComponentCallbacks2 {
    @NonNull
    /* renamed from: a */
    private final Handler f6602a;
    @NonNull
    /* renamed from: b */
    private final C2894f f6603b;
    @Nullable
    /* renamed from: c */
    private WeakReference<Activity> f6604c = null;
    /* renamed from: d */
    private boolean f6605d = false;
    @NonNull
    /* renamed from: e */
    private final Runnable f6606e = new C29031();

    /* renamed from: com.kochava.base.g$1 */
    class C29031 implements Runnable {
        C29031() {
        }

        @WorkerThread
        public final void run() {
            Tracker.m7517a(4, "SMO", "goInactive", new Object[0]);
            C2904g.this.f6605d = false;
            C2904g.this.f6603b.mo26535c(false);
        }
    }

    @AnyThread
    C2904g(@NonNull Context context, @NonNull Handler handler, @NonNull C2894f c2894f) {
        this.f6603b = c2894f;
        this.f6602a = handler;
        Tracker.m7517a(5, "SMO", "SessionMonito", new Object[0]);
        if (context instanceof Application) {
            ((Application) context).registerActivityLifecycleCallbacks(this);
            context.registerComponentCallbacks(this);
        } else {
            Tracker.m7517a(2, "SMO", "SessionMonito", "Invalid Application Context");
        }
        if (C2901d.m7641a(context)) {
            this.f6605d = true;
            c2894f.mo26535c(true);
        }
    }

    /* renamed from: b */
    private void m7686b() {
        this.f6602a.removeCallbacks(this.f6606e);
        if (!this.f6605d) {
            Tracker.m7517a(4, "SMO", "goActive", "goActive");
            this.f6605d = true;
            this.f6603b.mo26535c(true);
        }
    }

    /* Access modifiers changed, original: final */
    @Contract(pure = true)
    /* renamed from: a */
    public final boolean mo26586a() {
        return this.f6605d;
    }

    @MainThread
    public final void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        Tracker.m7517a(5, "SMO", "onActivityCre", new Object[0]);
    }

    @MainThread
    public final void onActivityDestroyed(@NonNull Activity activity) {
        Tracker.m7517a(5, "SMO", "onActivityDes", new Object[0]);
    }

    @MainThread
    public final void onActivityPaused(@NonNull Activity activity) {
        Tracker.m7517a(5, "SMO", "onActivityPau", new Object[0]);
        if (this.f6604c == null) {
            this.f6604c = new WeakReference(activity);
        }
    }

    @MainThread
    public final void onActivityResumed(@NonNull Activity activity) {
        Tracker.m7517a(5, "SMO", "onActivityRes", new Object[0]);
        if (this.f6604c == null) {
            this.f6604c = new WeakReference(activity);
        }
        m7686b();
    }

    @MainThread
    public final void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        Tracker.m7517a(5, "SMO", "onActivitySav", new Object[0]);
    }

    @MainThread
    public final void onActivityStarted(@NonNull Activity activity) {
        String str = "onActivitySta";
        Tracker.m7517a(5, "SMO", "onActivitySta", Boolean.toString(this.f6605d));
        this.f6604c = new WeakReference(activity);
        m7686b();
    }

    @MainThread
    public final void onActivityStopped(@NonNull Activity activity) {
        String str = "onActivitySto";
        Tracker.m7517a(5, "SMO", "onActivitySto", Boolean.toString(this.f6605d));
        if (this.f6605d && this.f6604c != null) {
            Activity activity2 = (Activity) this.f6604c.get();
            if (activity2 != null && activity2.equals(activity)) {
                Tracker.m7517a(5, "SMO", "onActivitySto", "?GoInactive?");
                this.f6602a.removeCallbacks(this.f6606e);
                this.f6602a.postDelayed(this.f6606e, PayloadController.PAYLOAD_COLLECTOR_TIMEOUT);
            }
        }
        this.f6604c = null;
    }

    public final void onConfigurationChanged(@NonNull Configuration configuration) {
        Tracker.m7517a(5, "SMO", "onConfigurati", new Object[0]);
    }

    public final void onLowMemory() {
        Tracker.m7517a(5, "SMO", "onLowMemory", new Object[0]);
    }

    public final void onTrimMemory(int i) {
        Tracker.m7517a(5, "SMO", "onTrimMemory", Boolean.toString(this.f6605d));
        if (this.f6605d && i == 20) {
            Tracker.m7517a(5, "SMO", "onTrimMemory", "GoInactive");
            this.f6602a.removeCallbacks(this.f6606e);
            this.f6605d = false;
            this.f6603b.mo26535c(false);
        }
    }
}
