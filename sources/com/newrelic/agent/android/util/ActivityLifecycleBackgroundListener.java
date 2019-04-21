package com.newrelic.agent.android.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.util.concurrent.atomic.AtomicBoolean;

@TargetApi(14)
public class ActivityLifecycleBackgroundListener extends UiBackgroundListener implements ActivityLifecycleCallbacks {
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private AtomicBoolean isInBackground = new AtomicBoolean(false);

    /* renamed from: com.newrelic.agent.android.util.ActivityLifecycleBackgroundListener$1 */
    class C41771 implements Runnable {
        C41771() {
        }

        public void run() {
            ApplicationStateMonitor.getInstance().activityStarted();
        }
    }

    /* renamed from: com.newrelic.agent.android.util.ActivityLifecycleBackgroundListener$2 */
    class C41782 implements Runnable {
        C41782() {
        }

        public void run() {
            ActivityLifecycleBackgroundListener.log.debug("ActivityLifecycleBackgroundListener.onActivityStarted - notifying ApplicationStateMonitor");
            ApplicationStateMonitor.getInstance().activityStarted();
        }
    }

    /* renamed from: com.newrelic.agent.android.util.ActivityLifecycleBackgroundListener$3 */
    class C41793 implements Runnable {
        C41793() {
        }

        public void run() {
            ActivityLifecycleBackgroundListener.log.debug("ActivityLifecycleBackgroundListener.onActivityPaused - notifying ApplicationStateMonitor");
            ApplicationStateMonitor.getInstance().uiHidden();
        }
    }

    public void onActivityResumed(Activity activity) {
        log.info("ActivityLifecycleBackgroundListener.onActivityResumed");
        if (this.isInBackground.getAndSet(false)) {
            this.executor.submit(new C41771());
        }
    }

    public void onTrimMemory(int level) {
        log.info("ActivityLifecycleBackgroundListener.onTrimMemory level: " + level);
        if (20 == level) {
            this.isInBackground.set(true);
        }
        super.onTrimMemory(level);
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        log.info("ActivityLifecycleBackgroundListener.onActivityCreated");
        this.isInBackground.set(false);
    }

    public void onActivityDestroyed(Activity activity) {
        log.info("ActivityLifecycleBackgroundListener.onActivityDestroyed");
        this.isInBackground.set(false);
    }

    public void onActivityStarted(Activity activity) {
        if (this.isInBackground.compareAndSet(true, false)) {
            this.executor.submit(new C41782());
        }
    }

    public void onActivityPaused(Activity activity) {
        if (this.isInBackground.compareAndSet(false, true)) {
            this.executor.submit(new C41793());
        }
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }
}
