package org.acra.jraf.android.util.activitylifecyclecallbackscompat;

import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;

public class MainLifecycleDispatcher implements ActivityLifecycleCallbacksCompat {
    private static final MainLifecycleDispatcher INSTANCE = new MainLifecycleDispatcher();
    private ArrayList<ActivityLifecycleCallbacksCompat> mActivityLifecycleCallbacks = new ArrayList();

    private MainLifecycleDispatcher() {
    }

    private Object[] collectActivityLifecycleCallbacks() {
        Object[] objArr = null;
        synchronized (this.mActivityLifecycleCallbacks) {
            if (this.mActivityLifecycleCallbacks.size() > 0) {
                objArr = this.mActivityLifecycleCallbacks.toArray();
            }
        }
        return objArr;
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Object[] collectActivityLifecycleCallbacks = collectActivityLifecycleCallbacks();
        if (collectActivityLifecycleCallbacks != null) {
            for (Object obj : collectActivityLifecycleCallbacks) {
                ((ActivityLifecycleCallbacksCompat) obj).onActivityCreated(activity, savedInstanceState);
            }
        }
    }

    public void onActivityStarted(Activity activity) {
        Object[] collectActivityLifecycleCallbacks = collectActivityLifecycleCallbacks();
        if (collectActivityLifecycleCallbacks != null) {
            for (Object obj : collectActivityLifecycleCallbacks) {
                ((ActivityLifecycleCallbacksCompat) obj).onActivityStarted(activity);
            }
        }
    }

    public void onActivityResumed(Activity activity) {
        Object[] collectActivityLifecycleCallbacks = collectActivityLifecycleCallbacks();
        if (collectActivityLifecycleCallbacks != null) {
            for (Object obj : collectActivityLifecycleCallbacks) {
                ((ActivityLifecycleCallbacksCompat) obj).onActivityResumed(activity);
            }
        }
    }

    public void onActivityPaused(Activity activity) {
        Object[] collectActivityLifecycleCallbacks = collectActivityLifecycleCallbacks();
        if (collectActivityLifecycleCallbacks != null) {
            for (Object obj : collectActivityLifecycleCallbacks) {
                ((ActivityLifecycleCallbacksCompat) obj).onActivityPaused(activity);
            }
        }
    }

    public void onActivityStopped(Activity activity) {
        Object[] collectActivityLifecycleCallbacks = collectActivityLifecycleCallbacks();
        if (collectActivityLifecycleCallbacks != null) {
            for (Object obj : collectActivityLifecycleCallbacks) {
                ((ActivityLifecycleCallbacksCompat) obj).onActivityStopped(activity);
            }
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Object[] collectActivityLifecycleCallbacks = collectActivityLifecycleCallbacks();
        if (collectActivityLifecycleCallbacks != null) {
            for (Object obj : collectActivityLifecycleCallbacks) {
                ((ActivityLifecycleCallbacksCompat) obj).onActivitySaveInstanceState(activity, outState);
            }
        }
    }

    public void onActivityDestroyed(Activity activity) {
        Object[] collectActivityLifecycleCallbacks = collectActivityLifecycleCallbacks();
        if (collectActivityLifecycleCallbacks != null) {
            for (Object obj : collectActivityLifecycleCallbacks) {
                ((ActivityLifecycleCallbacksCompat) obj).onActivityDestroyed(activity);
            }
        }
    }
}
