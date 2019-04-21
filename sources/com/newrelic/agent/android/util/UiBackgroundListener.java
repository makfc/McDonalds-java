package com.newrelic.agent.android.util;

import android.annotation.TargetApi;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@TargetApi(14)
public class UiBackgroundListener implements ComponentCallbacks2 {
    protected final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("UiBackgroundListener"));

    /* renamed from: com.newrelic.agent.android.util.UiBackgroundListener$1 */
    class C41801 implements Runnable {
        C41801() {
        }

        public void run() {
            ApplicationStateMonitor.getInstance().uiHidden();
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }

    public void onLowMemory() {
    }

    public void onTrimMemory(int level) {
        switch (level) {
            case 20:
                this.executor.submit(new C41801());
                return;
            default:
                return;
        }
    }
}
