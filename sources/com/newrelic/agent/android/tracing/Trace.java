package com.newrelic.agent.android.tracing;

import com.newrelic.agent.android.instrumentation.MetricCategory;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.util.Util;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Trace {
    private static final String CATEGORY_PARAMETER = "category";
    private static final AgentLog log = AgentLogManager.getAgentLog();
    public long childExclusiveTime;
    private volatile Set<UUID> children;
    public String displayName;
    public long entryTimestamp;
    public long exclusiveTime;
    public long exitTimestamp;
    private boolean isComplete;
    public String metricBackgroundName;
    public String metricName;
    public final UUID myUUID;
    private volatile Map<String, Object> params;
    public final UUID parentUUID;
    private List<String> rawAnnotationParams;
    public String scope;
    public long threadId;
    public String threadName;
    public TraceMachine traceMachine;
    private TraceType type;

    public Trace() {
        this.myUUID = new UUID(Util.getRandom().nextLong(), Util.getRandom().nextLong());
        this.entryTimestamp = 0;
        this.exitTimestamp = 0;
        this.exclusiveTime = 0;
        this.childExclusiveTime = 0;
        this.threadId = 0;
        this.threadName = "main";
        this.type = TraceType.TRACE;
        this.isComplete = false;
        this.parentUUID = null;
    }

    public Trace(String displayName, UUID parentUUID, TraceMachine traceMachine) {
        this.myUUID = new UUID(Util.getRandom().nextLong(), Util.getRandom().nextLong());
        this.entryTimestamp = 0;
        this.exitTimestamp = 0;
        this.exclusiveTime = 0;
        this.childExclusiveTime = 0;
        this.threadId = 0;
        this.threadName = "main";
        this.type = TraceType.TRACE;
        this.isComplete = false;
        this.displayName = displayName;
        this.parentUUID = parentUUID;
        this.traceMachine = traceMachine;
    }

    public void addChild(Trace trace) {
        if (this.children == null) {
            synchronized (this) {
                if (this.children == null) {
                    this.children = Collections.synchronizedSet(new HashSet());
                }
            }
        }
        this.children.add(trace.myUUID);
    }

    public Set<UUID> getChildren() {
        if (this.children == null) {
            synchronized (this) {
                if (this.children == null) {
                    this.children = Collections.synchronizedSet(new HashSet());
                }
            }
        }
        return this.children;
    }

    public Map<String, Object> getParams() {
        if (this.params == null) {
            synchronized (this) {
                if (this.params == null) {
                    this.params = new ConcurrentHashMap();
                }
            }
        }
        return this.params;
    }

    public void setAnnotationParams(List<String> rawAnnotationParams) {
        this.rawAnnotationParams = rawAnnotationParams;
    }

    public Map<String, Object> getAnnotationParams() {
        HashMap<String, Object> annotationParams = new HashMap();
        if (this.rawAnnotationParams != null && this.rawAnnotationParams.size() > 0) {
            Iterator<String> i = this.rawAnnotationParams.iterator();
            while (i.hasNext()) {
                String paramName = (String) i.next();
                Object param = createParameter(paramName, (String) i.next(), (String) i.next());
                if (param != null) {
                    annotationParams.put(paramName, param);
                }
            }
        }
        return annotationParams;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public void complete() throws TracingInactiveException {
        if (this.isComplete) {
            log.warning("Attempted to double complete trace " + this.myUUID.toString());
            return;
        }
        if (this.exitTimestamp == 0) {
            this.exitTimestamp = System.currentTimeMillis();
        }
        this.exclusiveTime = getDurationAsMilliseconds() - this.childExclusiveTime;
        this.isComplete = true;
        try {
            this.traceMachine.storeCompletedTrace(this);
        } catch (NullPointerException e) {
            throw new TracingInactiveException();
        }
    }

    public void prepareForSerialization() {
        getParams().put("type", this.type.toString());
    }

    public TraceType getType() {
        return this.type;
    }

    public void setType(TraceType type) {
        this.type = type;
    }

    public long getDurationAsMilliseconds() {
        return this.exitTimestamp - this.entryTimestamp;
    }

    public float getDurationAsSeconds() {
        return ((float) (this.exitTimestamp - this.entryTimestamp)) / 1000.0f;
    }

    public MetricCategory getCategory() {
        if (!getAnnotationParams().containsKey("category")) {
            return null;
        }
        Object category = getAnnotationParams().get("category");
        if (category instanceof MetricCategory) {
            return (MetricCategory) category;
        }
        log.error("Category annotation parameter is not of type MetricCategory");
        return null;
    }

    private static Object createParameter(String parameterName, String parameterClass, String parameterValue) {
        try {
            Class clazz = Class.forName(parameterClass);
            if (MetricCategory.class == clazz) {
                return MetricCategory.valueOf(parameterValue);
            }
            if (String.class != clazz) {
                return null;
            }
            return parameterValue;
        } catch (ClassNotFoundException e) {
            log.error("Unable to resolve parameter class in enterMethod: " + e.getMessage(), e);
            return null;
        }
    }
}
