package com.mcdonalds.app.analytics.datalayer;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.ensighten.Ensighten;

public class DataLayerLifecycleManager implements ActivityLifecycleCallbacks {
    private int numStarted;

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Ensighten.evaluateEvent(this, "onActivityCreated", new Object[]{activity, savedInstanceState});
    }

    public void onActivityStarted(Activity activity) {
        Ensighten.evaluateEvent(this, "onActivityStarted", new Object[]{activity});
        if (this.numStarted == 0) {
            DataLayerManager.getInstance().logLifecycleEvent("resume");
        }
        this.numStarted++;
    }

    public void onActivityResumed(Activity activity) {
        Ensighten.evaluateEvent(this, "onActivityResumed", new Object[]{activity});
    }

    public void onActivityPaused(Activity activity) {
        Ensighten.evaluateEvent(this, "onActivityPaused", new Object[]{activity});
    }

    public void onActivityStopped(Activity activity) {
        Ensighten.evaluateEvent(this, "onActivityStopped", new Object[]{activity});
        this.numStarted--;
        if (this.numStarted == 0) {
            DataLayerManager.getInstance().logLifecycleEvent("background");
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Ensighten.evaluateEvent(this, "onActivitySaveInstanceState", new Object[]{activity, outState});
    }

    public void onActivityDestroyed(Activity activity) {
        Ensighten.evaluateEvent(this, "onActivityDestroyed", new Object[]{activity});
    }
}
