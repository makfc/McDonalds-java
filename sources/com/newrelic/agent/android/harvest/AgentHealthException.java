package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonObject;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

public class AgentHealthException extends HarvestableArray {
    private final AtomicLong count;
    private String exceptionClass;
    private Map<String, String> extras;
    private String message;
    private StackTraceElement[] stackTrace;
    private String threadName;

    public AgentHealthException(Exception e) {
        this(e, Thread.currentThread().getName());
    }

    public AgentHealthException(Exception e, String threadName) {
        this(e.getClass().getName(), e.getMessage(), threadName, e.getStackTrace());
    }

    public AgentHealthException(String exceptionClass, String message, String threadName, StackTraceElement[] stackTrace) {
        this(exceptionClass, message, threadName, stackTrace, null);
    }

    public AgentHealthException(String exceptionClass, String message, String threadName, StackTraceElement[] stackTrace, Map<String, String> extras) {
        this.count = new AtomicLong(1);
        this.exceptionClass = exceptionClass;
        this.message = message;
        this.threadName = threadName;
        this.stackTrace = stackTrace;
        this.extras = extras;
    }

    public void increment() {
        this.count.getAndIncrement();
    }

    public void increment(long i) {
        this.count.getAndAdd(i);
    }

    public String getExceptionClass() {
        return this.exceptionClass;
    }

    public String getMessage() {
        return this.message;
    }

    public String getThreadName() {
        return this.threadName;
    }

    public StackTraceElement[] getStackTrace() {
        return this.stackTrace;
    }

    public long getCount() {
        return this.count.get();
    }

    public Map<String, String> getExtras() {
        return this.extras;
    }

    public String getSourceClass() {
        return this.stackTrace[0].getClassName();
    }

    public String getSourceMethod() {
        return this.stackTrace[0].getMethodName();
    }

    public JsonArray asJsonArray() {
        JsonArray data = new JsonArray();
        data.add(SafeJsonPrimitive.factory(this.exceptionClass));
        data.add(SafeJsonPrimitive.factory(this.message == null ? "" : this.message));
        data.add(SafeJsonPrimitive.factory(this.threadName));
        data.add(stackTraceToJson());
        data.add(SafeJsonPrimitive.factory(Long.valueOf(this.count.get())));
        data.add(extrasToJson());
        return data;
    }

    private JsonArray stackTraceToJson() {
        JsonArray stack = new JsonArray();
        for (StackTraceElement element : this.stackTrace) {
            stack.add(SafeJsonPrimitive.factory(element.toString()));
        }
        return stack;
    }

    private JsonObject extrasToJson() {
        JsonObject data = new JsonObject();
        if (this.extras != null) {
            for (Entry<String, String> entry : this.extras.entrySet()) {
                data.add((String) entry.getKey(), SafeJsonPrimitive.factory((String) entry.getValue()));
            }
        }
        return data;
    }
}
