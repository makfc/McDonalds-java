package com.newrelic.agent.android.analytics;

import com.newrelic.agent.android.FeatureFlag;
import com.newrelic.agent.android.harvest.HttpTransaction;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;

public class NetworkEventController {
    static final AgentLog log = AgentLogManager.getAgentLog();

    public static void createHttpErrorEvent(HttpTransaction txn) {
        if (!FeatureFlag.featureEnabled(FeatureFlag.NetworkErrorRequests)) {
            return;
        }
        if (AnalyticsControllerImpl.getInstance().addEvent(NetworkRequestErrorEvent.createHttpErrorEvent(txn))) {
            log.verbose(AnalyticsEventCategory.RequestError.toString() + " added to event store for request: " + txn.getUrl());
        } else {
            log.error("Failed to add MobileRequestError");
        }
    }

    public static void createNetworkFailureEvent(HttpTransaction txn) {
        if (!FeatureFlag.featureEnabled(FeatureFlag.NetworkErrorRequests)) {
            return;
        }
        if (AnalyticsControllerImpl.getInstance().addEvent(NetworkRequestErrorEvent.createNetworkFailureEvent(txn))) {
            log.verbose(AnalyticsEventCategory.RequestError.toString() + " added to event store for request: " + txn.getUrl());
        } else {
            log.error("Failed to add MobileRequestError");
        }
    }

    public static void createNetworkRequestEvent(HttpTransaction txn) {
        if (!FeatureFlag.featureEnabled(FeatureFlag.NetworkRequests)) {
            return;
        }
        if (AnalyticsControllerImpl.getInstance().addEvent(NetworkRequestEvent.createNetworkEvent(txn))) {
            log.verbose(AnalyticsEventCategory.NetworkRequest.toString() + " added to event store for request: " + txn.getUrl());
        } else {
            log.error("Failed to add MobileRequest");
        }
    }

    protected NetworkEventController() {
    }
}
