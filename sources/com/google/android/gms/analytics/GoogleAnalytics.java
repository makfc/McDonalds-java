package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import com.google.android.gms.analytics.internal.zzae;
import com.google.android.gms.analytics.internal.zzap;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzy;
import com.google.android.gms.common.internal.zzaa;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class GoogleAnalytics extends zza {
    private static List<Runnable> zzTX = new ArrayList();
    private Set<zza> zzTY = new HashSet();
    private boolean zzUa;
    private volatile boolean zzUb;
    private boolean zzUc;
    private boolean zzru;

    interface zza {
        void zzo(Activity activity);

        void zzp(Activity activity);
    }

    @TargetApi(14)
    class zzb implements ActivityLifecycleCallbacks {
        final /* synthetic */ GoogleAnalytics zzUd;

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
            this.zzUd.zzm(activity);
        }

        public void onActivityStopped(Activity activity) {
            this.zzUd.zzn(activity);
        }
    }

    public GoogleAnalytics(zzf zzf) {
        super(zzf);
    }

    @RequiresPermission
    public static GoogleAnalytics getInstance(Context context) {
        return zzf.zzX(context).zzme();
    }

    public static void zzkt() {
        synchronized (GoogleAnalytics.class) {
            if (zzTX != null) {
                for (Runnable run : zzTX) {
                    run.run();
                }
                zzTX = null;
            }
        }
    }

    private com.google.android.gms.analytics.internal.zzb zzkw() {
        return zzkj().zzkw();
    }

    private zzap zzkx() {
        return zzkj().zzkx();
    }

    public void dispatchLocalHits() {
        zzkw().zzlJ();
    }

    public boolean getAppOptOut() {
        return this.zzUb;
    }

    @Deprecated
    public Logger getLogger() {
        return zzae.getLogger();
    }

    public void initialize() {
        zzks();
        this.zzru = true;
    }

    public boolean isDryRunEnabled() {
        return this.zzUa;
    }

    public boolean isInitialized() {
        return this.zzru;
    }

    public Tracker newTracker(String str) {
        Tracker tracker;
        synchronized (this) {
            tracker = new Tracker(zzkj(), str, null);
            tracker.initialize();
        }
        return tracker;
    }

    public void setDryRun(boolean z) {
        this.zzUa = z;
    }

    @Deprecated
    public void setLogger(Logger logger) {
        zzae.setLogger(logger);
        if (!this.zzUc) {
            String str = (String) zzy.zzXF.get();
            Log.i((String) zzy.zzXF.get(), new StringBuilder(String.valueOf(str).length() + 112).append("GoogleAnalytics.setLogger() is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.").append(str).append(" DEBUG").toString());
            this.zzUc = true;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zzks() {
        zzap zzkx = zzkx();
        if (zzkx.zznL()) {
            getLogger().setLogLevel(zzkx.getLogLevel());
        }
        if (zzkx.zznP()) {
            setDryRun(zzkx.zznQ());
        }
        if (zzkx.zznL()) {
            Logger logger = zzae.getLogger();
            if (logger != null) {
                logger.setLogLevel(zzkx.getLogLevel());
            }
        }
    }

    public String zzku() {
        zzaa.zzdd("getClientId can not be called from the main thread");
        return zzkj().zzmh().zzmP();
    }

    /* Access modifiers changed, original: 0000 */
    public void zzkv() {
        zzkw().zzlK();
    }

    /* Access modifiers changed, original: 0000 */
    public void zzm(Activity activity) {
        for (zza zzo : this.zzTY) {
            zzo.zzo(activity);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zzn(Activity activity) {
        for (zza zzp : this.zzTY) {
            zzp.zzp(activity);
        }
    }
}
