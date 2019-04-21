package com.newrelic.agent.android.crash;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.AgentImpl;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.analytics.AnalyticsEvent;
import com.newrelic.agent.android.harvest.ActivityHistory;
import com.newrelic.agent.android.harvest.DataToken;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.crash.ApplicationInfo;
import com.newrelic.agent.android.harvest.crash.DeviceInfo;
import com.newrelic.agent.android.harvest.crash.ExceptionInfo;
import com.newrelic.agent.android.harvest.crash.ThreadInfo;
import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonParser;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Crash extends HarvestableObject {
    public static final int MAX_UPLOAD_COUNT = 3;
    public static final int PROTOCOL_VERSION = 1;
    private ActivityHistory activityHistory;
    private boolean analyticsEnabled;
    private final String appToken;
    private ApplicationInfo applicationInfo;
    private final String buildId;
    private DeviceInfo deviceInfo;
    private Collection<AnalyticsEvent> events;
    private ExceptionInfo exceptionInfo;
    private Set<AnalyticAttribute> sessionAttributes;
    private List<ThreadInfo> threads;
    private final long timestamp;
    private int uploadCount;
    private final UUID uuid;

    public static String getBuildId() {
        return "6243fbe4-fc89-4c32-b5a7-cff154491f35";
    }

    public Crash(UUID uuid, String buildId, long timestamp) {
        AgentImpl agentImpl = Agent.getImpl();
        this.uuid = uuid;
        this.buildId = buildId;
        this.timestamp = timestamp;
        this.appToken = CrashReporter.getInstance().getAgentConfiguration().getApplicationToken();
        this.deviceInfo = new DeviceInfo(agentImpl.getDeviceInformation(), agentImpl.getEnvironmentInformation());
        this.applicationInfo = new ApplicationInfo(agentImpl.getApplicationInformation());
        this.exceptionInfo = new ExceptionInfo();
        this.threads = new ArrayList();
        this.activityHistory = new ActivityHistory(new ArrayList());
        this.sessionAttributes = new HashSet();
        this.events = new HashSet();
        this.analyticsEnabled = true;
        this.uploadCount = 0;
    }

    public Crash(Throwable throwable) {
        this(throwable, new HashSet(), new HashSet(), false);
    }

    public Crash(Throwable throwable, Set<AnalyticAttribute> sessionAttributes, Collection<AnalyticsEvent> events, boolean analyticsEnabled) {
        AgentImpl agentImpl = Agent.getImpl();
        Throwable cause = getRootCause(throwable);
        this.uuid = UUID.randomUUID();
        this.buildId = getBuildId();
        this.timestamp = System.currentTimeMillis();
        this.appToken = CrashReporter.getInstance().getAgentConfiguration().getApplicationToken();
        this.deviceInfo = new DeviceInfo(agentImpl.getDeviceInformation(), agentImpl.getEnvironmentInformation());
        this.applicationInfo = new ApplicationInfo(agentImpl.getApplicationInformation());
        this.exceptionInfo = new ExceptionInfo(cause);
        this.threads = ThreadInfo.extractThreads(cause);
        this.activityHistory = TraceMachine.getActivityHistory();
        this.sessionAttributes = sessionAttributes;
        this.events = events;
        this.analyticsEnabled = analyticsEnabled;
        this.uploadCount = 0;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public ExceptionInfo getExceptionInfo() {
        return this.exceptionInfo;
    }

    public void setSessionAttributes(Set<AnalyticAttribute> sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

    public Set<AnalyticAttribute> getSessionAttributes() {
        return this.sessionAttributes;
    }

    public void setAnalyticsEvents(Collection<AnalyticsEvent> events) {
        this.events = events;
    }

    public JsonObject asJsonObject() {
        JsonObject data = new JsonObject();
        data.add("protocolVersion", new JsonPrimitive(Integer.valueOf(1)));
        data.add(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE, new JsonPrimitive("Android"));
        data.add(AnalyticAttribute.UUID_ATTRIBUTE, SafeJsonPrimitive.factory(this.uuid.toString()));
        data.add("buildId", SafeJsonPrimitive.factory(this.buildId));
        data.add(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, SafeJsonPrimitive.factory(Long.valueOf(this.timestamp)));
        data.add("appToken", SafeJsonPrimitive.factory(this.appToken));
        data.add("deviceInfo", this.deviceInfo.asJsonObject());
        data.add("appInfo", this.applicationInfo.asJsonObject());
        data.add("exception", this.exceptionInfo.asJsonObject());
        data.add("threads", getThreadsAsJson());
        data.add("activityHistory", this.activityHistory.asJsonArrayWithoutDuration());
        JsonObject attributeObject = new JsonObject();
        if (this.sessionAttributes != null) {
            for (AnalyticAttribute attribute : this.sessionAttributes) {
                attributeObject.add(attribute.getName(), attribute.asJsonElement());
            }
        }
        data.add("sessionAttributes", attributeObject);
        JsonArray eventArray = new JsonArray();
        if (this.events != null) {
            for (AnalyticsEvent event : this.events) {
                eventArray.add(event.asJsonObject());
            }
        }
        data.add("analyticsEvents", eventArray);
        DataToken dataToken = Harvest.getHarvestConfiguration().getDataToken();
        if (dataToken != null) {
            data.add("dataToken", dataToken.asJsonArray());
        }
        return data;
    }

    public static Crash crashFromJsonString(String json) {
        JsonObject crashObject = new JsonParser().parse(json).getAsJsonObject();
        Crash crash = new Crash(UUID.fromString(crashObject.get(AnalyticAttribute.UUID_ATTRIBUTE).getAsString()), crashObject.get("buildId").getAsString(), crashObject.get(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE).getAsLong());
        crash.deviceInfo = DeviceInfo.newFromJson(crashObject.get("deviceInfo").getAsJsonObject());
        crash.applicationInfo = ApplicationInfo.newFromJson(crashObject.get("appInfo").getAsJsonObject());
        crash.exceptionInfo = ExceptionInfo.newFromJson(crashObject.get("exception").getAsJsonObject());
        crash.threads = ThreadInfo.newListFromJson(crashObject.get("threads").getAsJsonArray());
        crash.activityHistory = ActivityHistory.newFromJson(crashObject.get("activityHistory").getAsJsonArray());
        boolean z = crashObject.has("sessionAttributes") || crashObject.has("analyticsEvents");
        crash.analyticsEnabled = z;
        if (crashObject.has("sessionAttributes")) {
            crash.setSessionAttributes(AnalyticAttribute.newFromJson(crashObject.get("sessionAttributes").getAsJsonObject()));
        }
        if (crashObject.has("analyticsEvents")) {
            crash.setAnalyticsEvents(AnalyticsEvent.newFromJson(crashObject.get("analyticsEvents").getAsJsonArray()));
        }
        if (crashObject.has("uploadCount")) {
            crash.uploadCount = crashObject.get("uploadCount").getAsInt();
        }
        return crash;
    }

    protected static Throwable getRootCause(Throwable throwable) {
        if (throwable != null) {
            try {
                Throwable cause = throwable.getCause();
                if (cause == null) {
                    return throwable;
                }
                return getRootCause(cause);
            } catch (Exception e) {
                if (throwable != null) {
                    return throwable;
                }
            }
        }
        return new Throwable("Unknown cause");
    }

    /* Access modifiers changed, original: protected */
    public JsonArray getThreadsAsJson() {
        JsonArray data = new JsonArray();
        if (this.threads != null) {
            for (ThreadInfo thread : this.threads) {
                data.add(thread.asJsonObject());
            }
        }
        return data;
    }

    public void incrementUploadCount() {
        this.uploadCount++;
    }

    public int getUploadCount() {
        return this.uploadCount;
    }

    public boolean isStale() {
        return this.uploadCount >= 3;
    }
}
