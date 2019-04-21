package com.newrelic.agent.android.activity;

public class NamedActivity extends BaseMeasuredActivity {
    public NamedActivity(String activityName) {
        setName(activityName);
        setAutoInstrumented(false);
    }

    public void rename(String activityName) {
        setName(activityName);
    }
}
