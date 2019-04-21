package com.newrelic.agent.android.analytics;

import com.newrelic.agent.android.harvest.HttpTransaction;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.util.Set;

public class NetworkRequestErrorEvent extends AnalyticsEvent {
    static final AgentLog log = AgentLogManager.getAgentLog();

    public NetworkRequestErrorEvent(Set<AnalyticAttribute> attributeSet) {
        super(null, AnalyticsEventCategory.RequestError, AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE_REQUEST_ERROR, attributeSet);
    }

    public static NetworkRequestErrorEvent createHttpErrorEvent(HttpTransaction txn) {
        Set<AnalyticAttribute> attributes = NetworkRequestEvent.createDefaultAttributeSet(txn);
        attributes.add(new AnalyticAttribute(AnalyticAttribute.STATUS_CODE_ATTRIBUTE, (float) txn.getStatusCode()));
        return new NetworkRequestErrorEvent(attributes);
    }

    public static NetworkRequestErrorEvent createNetworkFailureEvent(HttpTransaction txn) {
        Set<AnalyticAttribute> attributes = NetworkRequestEvent.createDefaultAttributeSet(txn);
        attributes.add(new AnalyticAttribute(AnalyticAttribute.NETWORK_ERROR_CODE_ATTRIBUTE, (float) txn.getErrorCode()));
        return new NetworkRequestErrorEvent(attributes);
    }
}
