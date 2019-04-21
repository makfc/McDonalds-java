package com.newrelic.agent.android.background;

public interface ApplicationStateListener {
    void applicationBackgrounded(ApplicationStateEvent applicationStateEvent);

    void applicationForegrounded(ApplicationStateEvent applicationStateEvent);
}
