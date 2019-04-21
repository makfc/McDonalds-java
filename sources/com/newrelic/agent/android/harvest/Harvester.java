package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.FeatureFlag;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.activity.config.ActivityTraceConfiguration;
import com.newrelic.agent.android.activity.config.ActivityTraceConfigurationDeserializer;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.analytics.AnalyticsControllerImpl;
import com.newrelic.agent.android.analytics.EventManager;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.com.google.gson.GsonBuilder;
import com.newrelic.com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Harvester {
    private AgentConfiguration agentConfiguration;
    private HarvestConfiguration configuration = HarvestConfiguration.getDefaultHarvestConfiguration();
    private HarvestConnection harvestConnection;
    private HarvestData harvestData;
    private final Collection<HarvestLifecycleAware> harvestListeners = new ArrayList();
    private final AgentLog log = AgentLogManager.getAgentLog();
    private State state = State.UNINITIALIZED;
    protected boolean stateChanged;

    protected enum State {
        UNINITIALIZED,
        DISCONNECTED,
        CONNECTED,
        DISABLED
    }

    public void start() {
        fireOnHarvestStart();
    }

    public void stop() {
        fireOnHarvestStop();
    }

    /* Access modifiers changed, original: protected */
    public void uninitialized() {
        if (this.agentConfiguration == null) {
            this.log.error("Agent configuration unavailable.");
            return;
        }
        if (Agent.getImpl().updateSavedConnectInformation()) {
            configureHarvester(HarvestConfiguration.getDefaultHarvestConfiguration());
            this.harvestData.getDataToken().clear();
        }
        Harvest.setHarvestConnectInformation(new ConnectInformation(Agent.getApplicationInformation(), Agent.getDeviceInformation()));
        this.harvestConnection.setApplicationToken(this.agentConfiguration.getApplicationToken());
        this.harvestConnection.setCollectorHost(this.agentConfiguration.getCollectorHost());
        this.harvestConnection.useSsl(this.agentConfiguration.useSsl());
        transition(State.DISCONNECTED);
        execute();
    }

    /* Access modifiers changed, original: protected */
    public void disconnected() {
        if (this.configuration == null) {
            configureHarvester(HarvestConfiguration.getDefaultHarvestConfiguration());
        }
        if (this.harvestData.isValid()) {
            this.log.verbose("Skipping connect call, saved state is available: " + this.harvestData.getDataToken());
            StatsEngine.get().sample("Session/Start", 1.0f);
            fireOnHarvestConnected();
            transition(State.CONNECTED);
            execute();
            return;
        }
        this.log.info("Connecting, saved state is not available: " + this.harvestData.getDataToken());
        HarvestResponse response = this.harvestConnection.sendConnect();
        if (response == null) {
            this.log.error("Unable to connect to the Collector.");
        } else if (response.isOK()) {
            HarvestConfiguration configuration = parseHarvesterConfiguration(response);
            if (configuration == null) {
                this.log.error("Unable to configure Harvester using Collector configuration.");
                return;
            }
            configureHarvester(configuration);
            StatsEngine.get().sampleTimeMs("Supportability/AgentHealth/Collector/Harvest", response.getResponseTime());
            fireOnHarvestConnected();
            transition(State.CONNECTED);
        } else {
            this.log.debug("Harvest connect response: " + response.getResponseCode());
            switch (response.getResponseCode()) {
                case UNAUTHORIZED:
                case INVALID_AGENT_ID:
                    this.harvestData.getDataToken().clear();
                    fireOnHarvestDisconnected();
                    return;
                case FORBIDDEN:
                    if (!response.isDisableCommand()) {
                        this.log.error("Unexpected Collector response: FORBIDDEN");
                        break;
                    }
                    this.log.error("Collector has commanded Agent to disable.");
                    fireOnHarvestDisabled();
                    transition(State.DISABLED);
                    return;
                case UNSUPPORTED_MEDIA_TYPE:
                case ENTITY_TOO_LARGE:
                    this.log.error("Invalid ConnectionInformation was sent to the Collector.");
                    break;
                default:
                    this.log.error("An unknown error occurred when connecting to the Collector.");
                    break;
            }
            fireOnHarvestError();
        }
    }

    /* Access modifiers changed, original: protected */
    public void connected() {
        this.log.info("Harvester: connected");
        this.log.info("Harvester: Sending " + this.harvestData.getHttpTransactions().count() + " HTTP transactions.");
        this.log.info("Harvester: Sending " + this.harvestData.getHttpErrors().count() + " HTTP errors.");
        this.log.info("Harvester: Sending " + this.harvestData.getActivityTraces().count() + " activity traces.");
        this.harvestData.setAnalyticsEnabled(this.agentConfiguration.getEnableAnalyticsEvents());
        if (this.agentConfiguration.getEnableAnalyticsEvents() && FeatureFlag.featureEnabled(FeatureFlag.AnalyticsEvents)) {
            EventManager eventManager = AnalyticsControllerImpl.getInstance().getEventManager();
            if (eventManager.isTransmitRequired()) {
                Set<AnalyticAttribute> sessionAttributes = new HashSet();
                sessionAttributes.addAll(AnalyticsControllerImpl.getInstance().getSystemAttributes());
                sessionAttributes.addAll(AnalyticsControllerImpl.getInstance().getUserAttributes());
                this.harvestData.setSessionAttributes(sessionAttributes);
                this.log.info("Harvester: Sending " + this.harvestData.getSessionAttributes().size() + " session attributes.");
                this.harvestData.setAnalyticsEvents(eventManager.getQueuedEvents());
                eventManager.empty();
            }
            this.log.info("Harvester: Sending " + this.harvestData.getAnalyticsEvents().size() + " analytics events.");
        }
        HarvestResponse response = this.harvestConnection.sendData(this.harvestData);
        if (response == null || response.isUnknown()) {
            fireOnHarvestSendFailed();
            return;
        }
        this.harvestData.reset();
        StatsEngine.get().sampleTimeMs("Supportability/AgentHealth/Collector/Harvest", response.getResponseTime());
        this.log.debug("Harvest data response: " + response.getResponseCode());
        this.log.debug("Harvest data response status code: " + response.getStatusCode());
        this.log.debug("Harvest data response BODY: " + response.getResponseBody());
        if (response.isError()) {
            fireOnHarvestError();
            switch (response.getResponseCode()) {
                case UNAUTHORIZED:
                case INVALID_AGENT_ID:
                    this.harvestData.getDataToken().clear();
                    transition(State.DISCONNECTED);
                    return;
                case FORBIDDEN:
                    if (response.isDisableCommand()) {
                        this.log.error("Collector has commanded Agent to disable.");
                        transition(State.DISABLED);
                        return;
                    }
                    this.log.error("Unexpected Collector response: FORBIDDEN");
                    transition(State.DISCONNECTED);
                    return;
                case UNSUPPORTED_MEDIA_TYPE:
                case ENTITY_TOO_LARGE:
                    this.log.error("Invalid ConnectionInformation was sent to the Collector.");
                    return;
                default:
                    this.log.error("An unknown error occurred when connecting to the Collector.");
                    return;
            }
        }
        HarvestConfiguration configuration = parseHarvesterConfiguration(response);
        if (configuration == null) {
            this.log.error("Unable to configure Harvester using Collector configuration.");
            return;
        }
        configureHarvester(configuration);
        fireOnHarvestComplete();
    }

    /* Access modifiers changed, original: protected */
    public void disabled() {
        Harvest.stop();
        fireOnHarvestDisabled();
    }

    /* Access modifiers changed, original: protected */
    public void execute() {
        this.log.debug("Harvester state: " + this.state);
        this.stateChanged = false;
        try {
            expireHarvestData();
            switch (this.state) {
                case UNINITIALIZED:
                    uninitialized();
                    return;
                case DISCONNECTED:
                    fireOnHarvestBefore();
                    disconnected();
                    return;
                case CONNECTED:
                    fireOnHarvestBefore();
                    fireOnHarvest();
                    fireOnHarvestFinalize();
                    TaskQueue.synchronousDequeue();
                    connected();
                    return;
                case DISABLED:
                    disabled();
                    return;
                default:
                    throw new IllegalStateException();
            }
        } catch (Exception e) {
            this.log.error("Exception encountered while attempting to harvest", e);
            AgentHealth.noticeException(e);
        }
        this.log.error("Exception encountered while attempting to harvest", e);
        AgentHealth.noticeException(e);
    }

    /* Access modifiers changed, original: protected */
    public void transition(State newState) {
        if (this.stateChanged) {
            this.log.debug("Ignoring multiple transition: " + newState);
        } else if (this.state != newState) {
            switch (this.state) {
                case UNINITIALIZED:
                    if (!stateIn(newState, State.DISCONNECTED, newState, State.CONNECTED, State.DISABLED)) {
                        throw new IllegalStateException();
                    }
                    break;
                case DISCONNECTED:
                    if (!stateIn(newState, State.UNINITIALIZED, State.CONNECTED, State.DISABLED)) {
                        throw new IllegalStateException();
                    }
                    break;
                case CONNECTED:
                    if (!stateIn(newState, State.DISCONNECTED, State.DISABLED)) {
                        throw new IllegalStateException();
                    }
                    break;
                default:
                    throw new IllegalStateException();
            }
            changeState(newState);
        }
    }

    private HarvestConfiguration parseHarvesterConfiguration(HarvestResponse response) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ActivityTraceConfiguration.class, new ActivityTraceConfigurationDeserializer());
        HarvestConfiguration config = null;
        try {
            return (HarvestConfiguration) gsonBuilder.create().fromJson(response.getResponseBody(), HarvestConfiguration.class);
        } catch (JsonSyntaxException e) {
            this.log.error("Unable to parse collector configuration: " + e.getMessage());
            AgentHealth.noticeException(e);
            return config;
        }
    }

    private void configureHarvester(HarvestConfiguration harvestConfiguration) {
        this.configuration.reconfigure(harvestConfiguration);
        this.harvestData.setDataToken(this.configuration.getDataToken());
        Harvest.setHarvestConfiguration(this.configuration);
    }

    private void changeState(State newState) {
        this.log.debug("Harvester changing state: " + this.state + " -> " + newState);
        if (this.state == State.CONNECTED) {
            if (newState == State.DISCONNECTED) {
                fireOnHarvestDisconnected();
            } else if (newState == State.DISABLED) {
                fireOnHarvestDisabled();
            }
        }
        this.state = newState;
        this.stateChanged = true;
    }

    private boolean stateIn(State testState, State... legalStates) {
        for (State state : legalStates) {
            if (testState == state) {
                return true;
            }
        }
        return false;
    }

    public State getCurrentState() {
        return this.state;
    }

    public boolean isDisabled() {
        return State.DISABLED == this.state;
    }

    public void addHarvestListener(HarvestLifecycleAware harvestAware) {
        if (harvestAware == null) {
            this.log.error("Can't add null harvest listener");
            new Exception().printStackTrace();
            return;
        }
        synchronized (this.harvestListeners) {
            if (this.harvestListeners.contains(harvestAware)) {
                return;
            }
            this.harvestListeners.add(harvestAware);
        }
    }

    public void removeHarvestListener(HarvestLifecycleAware harvestAware) {
        synchronized (this.harvestListeners) {
            if (this.harvestListeners.contains(harvestAware)) {
                this.harvestListeners.remove(harvestAware);
                return;
            }
        }
    }

    public void expireHarvestData() {
        expireHttpErrors();
        expireHttpTransactions();
        expireActivityTraces();
    }

    public void expireHttpErrors() {
        HttpErrors errors = this.harvestData.getHttpErrors();
        synchronized (errors) {
            Collection<HttpError> oldErrors = new ArrayList();
            long now = System.currentTimeMillis();
            long maxAge = this.configuration.getReportMaxTransactionAgeMilliseconds();
            for (HttpError error : errors.getHttpErrors()) {
                if (error.getTimestamp().longValue() < now - maxAge) {
                    this.log.debug("HttpError too old, purging: " + error);
                    oldErrors.add(error);
                }
            }
            for (HttpError error2 : oldErrors) {
                errors.removeHttpError(error2);
            }
        }
    }

    public void expireHttpTransactions() {
        HttpTransactions transactions = this.harvestData.getHttpTransactions();
        synchronized (transactions) {
            Collection<HttpTransaction> oldTransactions = new ArrayList();
            long now = System.currentTimeMillis();
            long maxAge = this.configuration.getReportMaxTransactionAgeMilliseconds();
            for (HttpTransaction txn : transactions.getHttpTransactions()) {
                if (txn.getTimestamp().longValue() < now - maxAge) {
                    this.log.debug("HttpTransaction too old, purging: " + txn);
                    oldTransactions.add(txn);
                }
            }
            for (HttpTransaction txn2 : oldTransactions) {
                transactions.remove(txn2);
            }
        }
    }

    public void expireActivityTraces() {
        ActivityTraces traces = this.harvestData.getActivityTraces();
        synchronized (traces) {
            Collection<ActivityTrace> expiredTraces = new ArrayList();
            long maxAttempts = (long) this.configuration.getActivity_trace_max_report_attempts();
            for (ActivityTrace trace : traces.getActivityTraces()) {
                if (trace.getReportAttemptCount() >= maxAttempts) {
                    this.log.debug("ActivityTrace has had " + trace.getReportAttemptCount() + " report attempts, purging: " + trace);
                    expiredTraces.add(trace);
                }
            }
            for (ActivityTrace trace2 : expiredTraces) {
                traces.remove(trace2);
            }
        }
    }

    public void setAgentConfiguration(AgentConfiguration agentConfiguration) {
        this.agentConfiguration = agentConfiguration;
    }

    public void setHarvestConnection(HarvestConnection connection) {
        this.harvestConnection = connection;
    }

    public HarvestConnection getHarvestConnection() {
        return this.harvestConnection;
    }

    public void setHarvestData(HarvestData harvestData) {
        this.harvestData = harvestData;
    }

    public HarvestData getHarvestData() {
        return this.harvestData;
    }

    private void fireOnHarvestBefore() {
        try {
            for (HarvestLifecycleAware harvestAware : getHarvestListeners()) {
                harvestAware.onHarvestBefore();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestBefore", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestStart() {
        try {
            for (HarvestLifecycleAware harvestAware : getHarvestListeners()) {
                harvestAware.onHarvestStart();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestStart", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestStop() {
        try {
            for (HarvestLifecycleAware harvestAware : getHarvestListeners()) {
                harvestAware.onHarvestStop();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestStop", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvest() {
        try {
            for (HarvestLifecycleAware harvestAware : getHarvestListeners()) {
                harvestAware.onHarvest();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvest", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestFinalize() {
        try {
            for (HarvestLifecycleAware harvestAware : getHarvestListeners()) {
                harvestAware.onHarvestFinalize();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestFinalize", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestDisabled() {
        try {
            for (HarvestLifecycleAware harvestAware : getHarvestListeners()) {
                harvestAware.onHarvestDisabled();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestDisabled", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestDisconnected() {
        try {
            for (HarvestLifecycleAware harvestAware : getHarvestListeners()) {
                harvestAware.onHarvestDisconnected();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestDisconnected", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestError() {
        try {
            for (HarvestLifecycleAware harvestAware : getHarvestListeners()) {
                harvestAware.onHarvestError();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestError", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestSendFailed() {
        try {
            for (HarvestLifecycleAware harvestAware : getHarvestListeners()) {
                harvestAware.onHarvestSendFailed();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestSendFailed", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestComplete() {
        try {
            for (HarvestLifecycleAware harvestAware : getHarvestListeners()) {
                harvestAware.onHarvestComplete();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestComplete", e);
            AgentHealth.noticeException(e);
        }
    }

    private void fireOnHarvestConnected() {
        try {
            for (HarvestLifecycleAware harvestAware : getHarvestListeners()) {
                harvestAware.onHarvestConnected();
            }
        } catch (Exception e) {
            this.log.error("Error in fireOnHarvestConnected", e);
            AgentHealth.noticeException(e);
        }
    }

    public void setConfiguration(HarvestConfiguration configuration) {
        this.configuration = configuration;
    }

    private Collection<HarvestLifecycleAware> getHarvestListeners() {
        return new ArrayList(this.harvestListeners);
    }
}
