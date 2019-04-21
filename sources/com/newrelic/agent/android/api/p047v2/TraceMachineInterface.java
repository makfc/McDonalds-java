package com.newrelic.agent.android.api.p047v2;

/* renamed from: com.newrelic.agent.android.api.v2.TraceMachineInterface */
public interface TraceMachineInterface {
    long getCurrentThreadId();

    String getCurrentThreadName();

    boolean isUIThread();
}
