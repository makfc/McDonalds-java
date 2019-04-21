package com.newrelic.agent.android.activity.config;

public class ActivityTraceConfiguration {
    private int maxTotalTraceCount;

    public static ActivityTraceConfiguration defaultActivityTraceConfiguration() {
        ActivityTraceConfiguration configuration = new ActivityTraceConfiguration();
        configuration.setMaxTotalTraceCount(1);
        return configuration;
    }

    public int getMaxTotalTraceCount() {
        return this.maxTotalTraceCount;
    }

    public void setMaxTotalTraceCount(int maxTotalTraceCount) {
        this.maxTotalTraceCount = maxTotalTraceCount;
    }

    public String toString() {
        return "ActivityTraceConfiguration{maxTotalTraceCount=" + this.maxTotalTraceCount + '}';
    }
}
