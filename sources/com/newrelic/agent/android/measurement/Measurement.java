package com.newrelic.agent.android.measurement;

public interface Measurement {
    double asDouble();

    void finish();

    long getEndTime();

    double getEndTimeInSeconds();

    long getExclusiveTime();

    double getExclusiveTimeInSeconds();

    String getName();

    String getScope();

    long getStartTime();

    double getStartTimeInSeconds();

    ThreadInfo getThreadInfo();

    MeasurementType getType();

    boolean isFinished();

    boolean isInstantaneous();
}
