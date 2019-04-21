package com.newrelic.agent.android.analytics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface EventManager {
    public static final List<String> RESERVED_EVENT_TYPES = new ArrayList();

    boolean addEvent(AnalyticsEvent analyticsEvent);

    void empty();

    int getEventsEjected();

    int getEventsRecorded();

    int getMaxEventBufferTime();

    int getMaxEventPoolSize();

    Collection<AnalyticsEvent> getQueuedEvents();

    void initialize();

    boolean isMaxEventBufferTimeExceeded();

    boolean isMaxEventPoolSizeExceeded();

    boolean isTransmitRequired();

    void setMaxEventBufferTime(int i);

    void setMaxEventPoolSize(int i);

    void shutdown();

    int size();
}
