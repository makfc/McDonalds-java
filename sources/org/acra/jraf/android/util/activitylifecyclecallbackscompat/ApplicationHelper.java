package org.acra.jraf.android.util.activitylifecyclecallbackscompat;

import android.app.Application;

public final class ApplicationHelper {
    public static void registerActivityLifecycleCallbacks(Application application, ActivityLifecycleCallbacksCompat callback) {
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksWrapper(callback));
    }
}
