package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.analytics.AnalyticsEvent;
import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class HarvestData extends HarvestableArray {
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private ActivityTraces activityTraces = new ActivityTraces();
    private AgentHealth agentHealth = new AgentHealth();
    private boolean analyticsEnabled = false;
    private Collection<AnalyticsEvent> analyticsEvents = new HashSet();
    private DataToken dataToken = new DataToken();
    private DeviceInformation deviceInformation = Agent.getDeviceInformation();
    private double harvestTimeDelta;
    private HttpErrors httpErrors = new HttpErrors();
    private HttpTransactions httpTransactions = new HttpTransactions();
    private MachineMeasurements machineMeasurements = new MachineMeasurements();
    private Set<AnalyticAttribute> sessionAttributes = new HashSet();

    public JsonArray asJsonArray() {
        JsonArray array = new JsonArray();
        array.add(this.dataToken.asJson());
        array.add(this.deviceInformation.asJson());
        array.add(new JsonPrimitive(Double.valueOf(this.harvestTimeDelta)));
        array.add(this.httpTransactions.asJson());
        array.add(this.machineMeasurements.asJson());
        array.add(this.httpErrors.asJson());
        JsonElement activityTracesElement = this.activityTraces.asJson();
        String activityTraceJson = activityTracesElement.toString();
        if (activityTraceJson.length() < Harvest.getHarvestConfiguration().getActivity_trace_max_size()) {
            array.add(activityTracesElement);
        } else {
            StatsEngine.get().sample("Supportability/AgentHealth/BigActivityTracesDropped", (float) activityTraceJson.length());
        }
        array.add(this.agentHealth.asJson());
        if (this.analyticsEnabled) {
            JsonObject sessionAttrObj = new JsonObject();
            for (AnalyticAttribute attribute : this.sessionAttributes) {
                switch (attribute.getAttributeDataType()) {
                    case STRING:
                        sessionAttrObj.addProperty(attribute.getName(), attribute.getStringValue());
                        break;
                    case FLOAT:
                        sessionAttrObj.addProperty(attribute.getName(), Float.valueOf(attribute.getFloatValue()));
                        break;
                    case BOOLEAN:
                        sessionAttrObj.addProperty(attribute.getName(), Boolean.valueOf(attribute.getBooleanValue()));
                        break;
                    default:
                        break;
                }
            }
            array.add(sessionAttrObj);
            JsonArray events = new JsonArray();
            for (AnalyticsEvent event : this.analyticsEvents) {
                events.add(event.asJsonObject());
            }
            array.add(events);
        }
        return array;
    }

    public boolean isValid() {
        return this.dataToken.isValid();
    }

    public void reset() {
        this.httpErrors.clear();
        this.httpTransactions.clear();
        this.activityTraces.clear();
        this.machineMeasurements.clear();
        this.agentHealth.clear();
        this.sessionAttributes.clear();
        this.analyticsEvents.clear();
    }

    public void setDataToken(DataToken dataToken) {
        if (dataToken != null) {
            this.dataToken = dataToken;
        }
    }

    public void setDeviceInformation(DeviceInformation deviceInformation) {
        this.deviceInformation = deviceInformation;
    }

    public void setHarvestTimeDelta(double harvestTimeDelta) {
        this.harvestTimeDelta = harvestTimeDelta;
    }

    public void setHttpTransactions(HttpTransactions httpTransactions) {
        this.httpTransactions = httpTransactions;
    }

    public void setMachineMeasurements(MachineMeasurements machineMeasurements) {
        this.machineMeasurements = machineMeasurements;
    }

    public void setActivityTraces(ActivityTraces activityTraces) {
        this.activityTraces = activityTraces;
    }

    public void setHttpErrors(HttpErrors httpErrors) {
        this.httpErrors = httpErrors;
    }

    public Set<AnalyticAttribute> getSessionAttributes() {
        return this.sessionAttributes;
    }

    public void setSessionAttributes(Set<AnalyticAttribute> sessionAttributes) {
        log.debug("HarvestData.setSessionAttributes invoked with attribute set " + sessionAttributes);
        this.sessionAttributes = new HashSet(sessionAttributes);
    }

    public Collection<AnalyticsEvent> getAnalyticsEvents() {
        return this.analyticsEvents;
    }

    public void setAnalyticsEvents(Collection<AnalyticsEvent> analyticsEvents) {
        this.analyticsEvents = new HashSet(analyticsEvents);
    }

    public DeviceInformation getDeviceInformation() {
        return this.deviceInformation;
    }

    public HttpErrors getHttpErrors() {
        return this.httpErrors;
    }

    public HttpTransactions getHttpTransactions() {
        return this.httpTransactions;
    }

    public MachineMeasurements getMetrics() {
        return this.machineMeasurements;
    }

    public ActivityTraces getActivityTraces() {
        return this.activityTraces;
    }

    public AgentHealth getAgentHealth() {
        return this.agentHealth;
    }

    public DataToken getDataToken() {
        return this.dataToken;
    }

    public boolean isAnalyticsEnabled() {
        return this.analyticsEnabled;
    }

    public void setAnalyticsEnabled(boolean analyticsEnabled) {
        this.analyticsEnabled = analyticsEnabled;
    }

    public String toString() {
        return "HarvestData{\n\tdataToken=" + this.dataToken + ", \n\tdeviceInformation=" + this.deviceInformation + ", \n\tharvestTimeDelta=" + this.harvestTimeDelta + ", \n\thttpTransactions=" + this.httpTransactions + ", \n\tmachineMeasurements=" + this.machineMeasurements + ", \n\thttpErrors=" + this.httpErrors + ", \n\tactivityTraces=" + this.activityTraces + ", \n\tsessionAttributes=" + this.sessionAttributes + ", \n\tanalyticAttributes=" + this.analyticsEvents + '}';
    }
}
