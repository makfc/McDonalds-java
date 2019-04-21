package org.acra.jraf.android.util.activitylifecyclecallbackscompat;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

class ActivityLifecycleCallbacksWrapper implements ActivityLifecycleCallbacks {
    private final ActivityLifecycleCallbacksCompat mCallback;

    public ActivityLifecycleCallbacksWrapper(ActivityLifecycleCallbacksCompat callback) {
        this.mCallback = callback;
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        this.mCallback.onActivityCreated(activity, savedInstanceState);
    }

    public void onActivityStarted(Activity activity) {
        this.mCallback.onActivityStarted(activity);
    }

    public void onActivityResumed(Activity activity) {
        this.mCallback.onActivityResumed(activity);
    }

    public void onActivityPaused(Activity activity) {
        this.mCallback.onActivityPaused(activity);
    }

    public void onActivityStopped(Activity activity) {
        this.mCallback.onActivityStopped(activity);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        this.mCallback.onActivitySaveInstanceState(activity, outState);
    }

    public void onActivityDestroyed(Activity activity) {
        this.mCallback.onActivityDestroyed(activity);
    }

    public boolean equals(Object object) {
        if (!(object instanceof ActivityLifecycleCallbacksWrapper)) {
            return false;
        }
        ActivityLifecycleCallbacksWrapper activityLifecycleCallbacksWrapper = (ActivityLifecycleCallbacksWrapper) object;
        if (this.mCallback != null) {
            return this.mCallback.equals(activityLifecycleCallbacksWrapper.mCallback);
        }
        if (activityLifecycleCallbacksWrapper.mCallback == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.mCallback != null ? this.mCallback.hashCode() : 0;
    }
}
