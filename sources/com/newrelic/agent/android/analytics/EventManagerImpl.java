package com.newrelic.agent.android.analytics;

import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class EventManagerImpl implements EventManager {
    public static int DEFAULT_MAX_EVENT_BUFFER_SIZE = 1000;
    public static int DEFAULT_MAX_EVENT_BUFFER_TIME = 600;
    private static final String EVENT_TYPE_ALLOWABLE_CHARS = "^[\\p{L}\\p{Nd} _:.]+$";
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private List<AnalyticsEvent> events;
    private AtomicInteger eventsEjected;
    private AtomicInteger eventsRecorded;
    private long firstEventTimestamp;
    private AtomicBoolean initialized;
    private int maxBufferTimeInSec;
    private int maxEventPoolSize;

    static {
        RESERVED_EVENT_TYPES.add(AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE);
        RESERVED_EVENT_TYPES.add(AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE_REQUEST);
        RESERVED_EVENT_TYPES.add(AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE_REQUEST_ERROR);
        RESERVED_EVENT_TYPES.add(AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE_BREADCRUMB);
        RESERVED_EVENT_TYPES.add(AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE_CRASH);
    }

    public EventManagerImpl() {
        this(DEFAULT_MAX_EVENT_BUFFER_SIZE, DEFAULT_MAX_EVENT_BUFFER_TIME);
    }

    public EventManagerImpl(int maxEventPoolSize, int maxBufferTimeInSec) {
        this.initialized = new AtomicBoolean(false);
        this.eventsRecorded = new AtomicInteger(0);
        this.eventsEjected = new AtomicInteger(0);
        this.events = Collections.synchronizedList(new ArrayList(maxEventPoolSize));
        this.maxBufferTimeInSec = maxBufferTimeInSec;
        this.maxEventPoolSize = maxEventPoolSize;
        this.firstEventTimestamp = 0;
        this.eventsRecorded.set(0);
        this.eventsEjected.set(0);
    }

    public void initialize() {
        if (this.initialized.compareAndSet(false, true)) {
            this.firstEventTimestamp = 0;
            this.eventsRecorded.set(0);
            this.eventsEjected.set(0);
            empty();
            return;
        }
        log.verbose("EventManagerImpl has already been initialized.  Bypassing...");
    }

    public void shutdown() {
        this.initialized.set(false);
    }

    public int size() {
        return this.events.size();
    }

    public void empty() {
        this.events.clear();
        this.firstEventTimestamp = 0;
    }

    public boolean isTransmitRequired() {
        return (!this.initialized.get() && this.events.size() > 0) || isMaxEventBufferTimeExceeded();
    }

    public boolean addEvent(AnalyticsEvent event) {
        int eventsRecorded = this.eventsRecorded.incrementAndGet();
        if (this.events.size() == 0) {
            log.verbose("EventManagerImpl.addEvent - Queue is currently empty, setting first event timestamp to " + System.currentTimeMillis());
            this.firstEventTimestamp = System.currentTimeMillis();
        }
        if (this.events.size() >= this.maxEventPoolSize) {
            this.eventsEjected.incrementAndGet();
            int index = (int) (Math.random() * ((double) eventsRecorded));
            if (index >= this.maxEventPoolSize) {
                return true;
            }
            this.events.remove(index);
        }
        return this.events.add(event);
    }

    public int getEventsRecorded() {
        return this.eventsRecorded.get();
    }

    public int getEventsEjected() {
        return this.eventsEjected.get();
    }

    public boolean isMaxEventBufferTimeExceeded() {
        if (this.firstEventTimestamp <= 0 || System.currentTimeMillis() - this.firstEventTimestamp <= ((long) (this.maxBufferTimeInSec * 1000))) {
            return false;
        }
        return true;
    }

    public boolean isMaxEventPoolSizeExceeded() {
        return this.events.size() > this.maxEventPoolSize;
    }

    public int getMaxEventPoolSize() {
        return this.maxEventPoolSize;
    }

    public void setMaxEventPoolSize(int maxSize) {
        this.maxEventPoolSize = maxSize;
    }

    public void setMaxEventBufferTime(int maxBufferTimeInSec) {
        this.maxBufferTimeInSec = maxBufferTimeInSec;
    }

    public int getMaxEventBufferTime() {
        return this.maxBufferTimeInSec;
    }

    public Collection<AnalyticsEvent> getQueuedEvents() {
        return Collections.unmodifiableCollection(this.events);
    }

    public boolean isEventTypeValid(String eventType) {
        boolean valid = eventType != null;
        if (valid) {
            valid = eventType.matches(EVENT_TYPE_ALLOWABLE_CHARS);
        }
        if (!valid) {
            log.error("Event type name [" + eventType + "] is invalid and will be ignored. Custom event types may only include alphanumeric, ' ', '.', ':' or '_' characters.");
        }
        return valid;
    }

    public boolean isEventTypeReserved(String eventType) {
        boolean reserved = RESERVED_EVENT_TYPES.contains(eventType);
        if (reserved) {
            log.error("Event type name [" + eventType + "] is reserved and will be ignored.");
        }
        return reserved;
    }
}
