package com.kochava.base;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.Contract;
import org.json.JSONObject;

@AnyThread
/* renamed from: com.kochava.base.h */
final class C2906h {
    @NonNull
    /* renamed from: a */
    final Context f6608a;
    @NonNull
    /* renamed from: b */
    final List<String> f6609b = Collections.synchronizedList(new ArrayList());
    @NonNull
    /* renamed from: c */
    final JSONObject f6610c;
    @NonNull
    /* renamed from: d */
    final C2901d f6611d;
    @Nullable
    /* renamed from: e */
    final AttributionListener f6612e;
    @Nullable
    /* renamed from: f */
    final AttributionUpdateListener f6613f;
    @Nullable
    /* renamed from: g */
    final ConsentStatusChangeListener f6614g;
    @NonNull
    /* renamed from: h */
    final Runnable f6615h;
    @NonNull
    /* renamed from: i */
    final C2893b f6616i;
    @NonNull
    /* renamed from: j */
    final Handler f6617j;
    @NonNull
    /* renamed from: k */
    final Handler f6618k;
    @NonNull
    /* renamed from: l */
    final HandlerThread f6619l = new HandlerThread("EventThread");
    @NonNull
    /* renamed from: m */
    final HandlerThread f6620m = new HandlerThread("ControllerThread");
    /* renamed from: n */
    final String f6621n = UUID.randomUUID().toString().substring(0, 5);
    /* renamed from: o */
    final boolean f6622o;
    /* renamed from: p */
    final long f6623p = C2901d.m7626a();
    /* renamed from: q */
    final boolean f6624q;
    /* renamed from: r */
    volatile int f6625r = 0;
    /* renamed from: s */
    volatile long f6626s = C2901d.m7626a();
    @Nullable
    /* renamed from: t */
    private Object f6627t = null;

    /* renamed from: com.kochava.base.h$1 */
    class C29051 implements UncaughtExceptionHandler {
        C29051() {
        }

        public final void uncaughtException(@NonNull Thread thread, @NonNull Throwable th) {
            Tracker.m7517a(1, "STT", "uncaughtExcep", "Critical Error: Shutting Down", th);
            Tracker.unConfigure();
        }
    }

    C2906h(@NonNull Context context, @NonNull Runnable runnable, @NonNull C2893b c2893b, @NonNull JSONObject jSONObject, @Nullable AttributionListener attributionListener, @Nullable AttributionUpdateListener attributionUpdateListener, @Nullable ConsentStatusChangeListener consentStatusChangeListener, boolean z) {
        boolean z2 = false;
        this.f6608a = context;
        this.f6615h = runnable;
        this.f6616i = c2893b;
        this.f6610c = jSONObject;
        this.f6612e = attributionListener;
        this.f6613f = attributionUpdateListener;
        this.f6614g = consentStatusChangeListener;
        this.f6622o = z;
        this.f6611d = new C2901d(context, z);
        C29051 c29051 = new C29051();
        this.f6619l.setUncaughtExceptionHandler(c29051);
        this.f6620m.setUncaughtExceptionHandler(c29051);
        this.f6619l.start();
        this.f6620m.start();
        this.f6617j = new Handler(this.f6619l.getLooper());
        this.f6618k = new Handler(this.f6620m.getLooper());
        if (this.f6611d.mo26572c("kochava_device_id") == null && this.f6611d.mo26572c("kvinit_wait") == null) {
            z2 = true;
        }
        this.f6624q = z2;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo26598a(@NonNull Runnable runnable) {
        this.f6617j.post(runnable);
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo26599a(@NonNull Runnable runnable, long j) {
        this.f6618k.postDelayed(runnable, j);
    }

    /* Access modifiers changed, original: final */
    /* renamed from: a */
    public final void mo26600a(@NonNull Runnable runnable, boolean z) {
        if (z) {
            this.f6618k.postAtFrontOfQueue(runnable);
        } else {
            this.f6618k.post(runnable);
        }
    }

    /* Access modifiers changed, original: final */
    @Contract(pure = true)
    /* renamed from: a */
    public final boolean mo26601a() {
        return this.f6627t != null;
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final void mo26602b() {
        if (this.f6627t != null) {
            this.f6618k.removeCallbacksAndMessages(this.f6627t);
            this.f6627t = null;
        }
    }

    /* Access modifiers changed, original: final */
    /* renamed from: b */
    public final void mo26603b(@NonNull Runnable runnable, long j) {
        this.f6627t = new Object();
        this.f6618k.postAtTime(runnable, this.f6627t, SystemClock.uptimeMillis() + j);
    }
}
