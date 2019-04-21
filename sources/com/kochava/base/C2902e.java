package com.kochava.base;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.autonavi.amap.mapcore.interfaces.CameraAnimator;
import com.kochava.base.C2900c.C2897a;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.kochava.base.e */
final class C2902e implements Runnable {
    /* renamed from: a */
    private final int f6595a;
    @NonNull
    /* renamed from: b */
    private final C2906h f6596b;
    @NonNull
    /* renamed from: c */
    private final Handler f6597c = new Handler(Looper.getMainLooper());
    /* renamed from: d */
    private final long f6598d = C2901d.m7626a();
    @Nullable
    /* renamed from: e */
    private DeepLinkListener f6599e;
    @Nullable
    /* renamed from: f */
    private Map<String, String> f6600f = null;

    C2902e(@Nullable Uri uri, int i, @NonNull C2906h c2906h, @NonNull DeepLinkListener deepLinkListener) {
        this.f6595a = C2901d.m7624a(i, (int) CameraAnimator.DEFAULT_DURATION, 10000);
        this.f6599e = deepLinkListener;
        this.f6596b = c2906h;
        if (uri != null) {
            this.f6600f = C2901d.m7633a(uri.getQuery());
        }
        boolean a = C2901d.m7644a(c2906h.f6611d.mo26572c("deeplink_ran"), false);
        c2906h.f6611d.mo26566a("deeplink_ran", Boolean.valueOf(true), true);
        if (this.f6600f == null && c2906h.f6624q && !a) {
            run();
        } else {
            m7681b();
        }
    }

    /* renamed from: b */
    private void m7681b() {
        synchronized (this) {
            try {
                if (this.f6599e != null) {
                    this.f6599e.onDeepLink(this.f6600f != null ? this.f6600f : new HashMap());
                }
                mo26583a();
            } catch (Throwable th) {
                Tracker.m7517a(2, "DLT", "sendDeepLink", "Exception in Host App", th);
            }
        }
    }

    @Nullable
    /* renamed from: c */
    private Map<String, String> m7682c() {
        Map<String, String> a;
        synchronized (this) {
            InstallReferrer a2 = C2897a.m7551a(C2901d.m7649b(this.f6596b.f6611d.mo26572c("install_referrer"), true), false, C2901d.m7628a(this.f6596b.f6611d.mo26572c("referrer")));
            if (a2.isValid()) {
                a = C2901d.m7633a(a2.referrer);
            } else {
                a = null;
            }
        }
        return a;
    }

    /* renamed from: a */
    public final void mo26583a() {
        synchronized (this) {
            this.f6597c.removeCallbacks(this);
            this.f6599e = null;
            this.f6600f = null;
        }
    }

    public final void run() {
        synchronized (this) {
            this.f6600f = m7682c();
            if (this.f6600f != null || C2901d.m7626a() - this.f6598d >= ((long) this.f6595a)) {
                m7681b();
                return;
            }
            this.f6597c.postDelayed(this, 250);
        }
    }
}
