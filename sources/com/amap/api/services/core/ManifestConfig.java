package com.amap.api.services.core;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* renamed from: com.amap.api.services.core.l */
public class ManifestConfig {
    /* renamed from: a */
    public static C1081ac f3792a;
    /* renamed from: b */
    private static ManifestConfig f3793b;
    /* renamed from: c */
    private static Context f3794c;
    /* renamed from: d */
    private C1130a f3795d;
    /* renamed from: e */
    private HandlerThread f3796e = new C1131m(this, "manifestThread");

    /* compiled from: ManifestConfig */
    /* renamed from: com.amap.api.services.core.l$a */
    class C1130a extends Handler {
        /* renamed from: a */
        String f3790a = "handleMessage";

        public C1130a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message != null) {
                switch (message.what) {
                    case 3:
                        try {
                            ManifestResult manifestResult = (ManifestResult) message.obj;
                            if (manifestResult == null) {
                                manifestResult = new ManifestResult(false, false);
                            }
                            C1099ax.m4796a(ManifestConfig.f3794c, C1127c.m4968a(manifestResult.mo12110a()));
                            ManifestConfig.f3792a = C1127c.m4968a(manifestResult.mo12110a());
                            return;
                        } catch (Throwable th) {
                            C1128d.m4975a(th, "ManifestConfig", this.f3790a);
                            return;
                        }
                    default:
                        return;
                }
            }
        }
    }

    private ManifestConfig(Context context) {
        f3794c = context;
        f3792a = C1127c.m4968a(false);
        try {
            this.f3796e.start();
            this.f3795d = new C1130a(Looper.getMainLooper());
        } catch (Throwable th) {
            C1128d.m4975a(th, "ManifestConfig", "ManifestConfig");
        }
    }

    /* renamed from: a */
    public static ManifestConfig m5058a(Context context) {
        if (f3793b == null) {
            f3793b = new ManifestConfig(context);
        }
        return f3793b;
    }
}
