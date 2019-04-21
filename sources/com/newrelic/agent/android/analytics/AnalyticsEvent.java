package com.newrelic.agent.android.analytics;

import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class AnalyticsEvent extends HarvestableObject {
    private Set<AnalyticAttribute> attributeSet;
    private AnalyticsEventCategory category;
    private String eventType;
    private final AgentLog log;
    private String name;
    private long timestamp;

    protected AnalyticsEvent(String name) {
        this(name, AnalyticsEventCategory.Custom, null, null);
    }

    protected AnalyticsEvent(String name, AnalyticsEventCategory category) {
        this(name, category, null, null);
    }

    protected AnalyticsEvent(String name, AnalyticsEventCategory category, String eventType, Set<AnalyticAttribute> initialAttributeSet) {
        this(name, category, eventType, System.currentTimeMillis(), initialAttributeSet);
    }

    private AnalyticsEvent(String name, AnalyticsEventCategory category, String eventType, long timeStamp, Set<AnalyticAttribute> initialAttributeSet) {
        this.log = AgentLogManager.getAgentLog();
        this.attributeSet = Collections.synchronizedSet(new HashSet());
        this.name = name;
        if (category == null) {
            this.category = AnalyticsEventCategory.Custom;
        } else {
            this.category = category;
        }
        if (eventType == null) {
            this.eventType = AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE;
        } else {
            this.eventType = eventType;
        }
        this.timestamp = timeStamp;
        if (initialAttributeSet != null) {
            for (AnalyticAttribute attribute : initialAttributeSet) {
                this.attributeSet.add(new AnalyticAttribute(attribute));
            }
        }
        if (name != null) {
            this.attributeSet.add(new AnalyticAttribute("name", this.name));
        }
        this.attributeSet.add(new AnalyticAttribute(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, String.valueOf(this.timestamp)));
        this.attributeSet.add(new AnalyticAttribute("category", this.category.name()));
        this.attributeSet.add(new AnalyticAttribute(AnalyticAttribute.EVENT_TYPE_ATTRIBUTE, this.eventType));
    }

    public void addAttributes(Set<AnalyticAttribute> attributeSet) {
        if (attributeSet != null) {
            for (AnalyticAttribute attribute : attributeSet) {
                if (!this.attributeSet.add(attribute)) {
                    this.log.error("Failed to add attribute " + attribute.getName() + " to event " + getName() + ": the event already contains that attribute.");
                }
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public AnalyticsEventCategory getCategory() {
        return this.category;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getEventType() {
        return this.eventType;
    }

    public JsonObject asJsonObject() {
        JsonObject data = new JsonObject();
        synchronized (this) {
            for (AnalyticAttribute attribute : this.attributeSet) {
                data.add(attribute.getName(), attribute.asJsonElement());
            }
        }
        return data;
    }

    public Collection<AnalyticAttribute> getAttributeSet() {
        return Collections.unmodifiableCollection(this.attributeSet);
    }

    public static AnalyticsEvent newFromJson(JsonObject analyticsEventJson) {
        String name = null;
        String eventType = null;
        AnalyticsEventCategory category = null;
        long timestamp = 0;
        Set<AnalyticAttribute> attributeSet = new HashSet();
        for (Entry<String, JsonElement> elem : analyticsEventJson.entrySet()) {
            String key = (String) elem.getKey();
            if (key.equalsIgnoreCase("name")) {
                name = ((JsonElement) elem.getValue()).getAsString();
            } else if (key.equalsIgnoreCase("category")) {
                category = AnalyticsEventCategory.fromString(((JsonElement) elem.getValue()).getAsString());
            } else if (key.equalsIgnoreCase(AnalyticAttribute.EVENT_TYPE_ATTRIBUTE)) {
                eventType = ((JsonElement) elem.getValue()).getAsString();
            } else if (key.equalsIgnoreCase(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE)) {
                timestamp = ((JsonElement) elem.getValue()).getAsLong();
            } else {
                JsonPrimitive value = ((JsonElement) elem.getValue()).getAsJsonPrimitive();
                if (value.isString()) {
                    attributeSet.add(new AnalyticAttribute(key, value.getAsString(), false));
                } else if (value.isBoolean()) {
                    attributeSet.add(new AnalyticAttribute(key, value.getAsBoolean(), false));
                } else if (value.isNumber()) {
                    attributeSet.add(new AnalyticAttribute(key, value.getAsFloat(), false));
                }
            }
        }
        return new AnalyticsEvent(name, category, eventType, timestamp, attributeSet);
    }

    public static Collection<AnalyticsEvent> newFromJson(JsonArray analyticsEventsJson) {
        ArrayList<AnalyticsEvent> events = new ArrayList();
        Iterator<JsonElement> entry = analyticsEventsJson.iterator();
        while (entry.hasNext()) {
            events.add(newFromJson(((JsonElement) entry.next()).getAsJsonObject()));
        }
        return events;
    }
}
