package com.newrelic.agent.android.tracing;

public interface TraceLifecycleAware {
    void onEnterMethod();

    void onExitMethod();

    void onTraceComplete(ActivityTrace activityTrace);

    void onTraceRename(ActivityTrace activityTrace);

    void onTraceStart(ActivityTrace activityTrace);
}
