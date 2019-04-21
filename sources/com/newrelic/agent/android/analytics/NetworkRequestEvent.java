package com.newrelic.agent.android.analytics;

import com.newrelic.agent.android.harvest.HttpTransaction;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class NetworkRequestEvent extends AnalyticsEvent {
    static final AgentLog log = AgentLogManager.getAgentLog();

    public NetworkRequestEvent(Set<AnalyticAttribute> attributeSet) {
        super(null, AnalyticsEventCategory.NetworkRequest, AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE_REQUEST, attributeSet);
    }

    public static NetworkRequestEvent createNetworkEvent(HttpTransaction txn) {
        Set<AnalyticAttribute> attributes = createDefaultAttributeSet(txn);
        attributes.add(new AnalyticAttribute(AnalyticAttribute.RESPONSE_TIME_ATTRIBUTE, Double.valueOf(txn.getTotalTime()).floatValue()));
        attributes.add(new AnalyticAttribute(AnalyticAttribute.STATUS_CODE_ATTRIBUTE, (float) txn.getStatusCode()));
        attributes.add(new AnalyticAttribute(AnalyticAttribute.BYTES_SENT_ATTRIBUTE, Double.valueOf((double) txn.getBytesSent()).floatValue()));
        attributes.add(new AnalyticAttribute(AnalyticAttribute.BYTES_RECEIVED_ATTRIBUTE, Double.valueOf((double) txn.getBytesReceived()).floatValue()));
        return new NetworkRequestEvent(attributes);
    }

    static Set<AnalyticAttribute> createDefaultAttributeSet(HttpTransaction txn) {
        Set<AnalyticAttribute> attributes = new HashSet();
        try {
            URL url = new URL(txn.getUrl());
            attributes.add(new AnalyticAttribute(AnalyticAttribute.REQUEST_DOMAIN_ATTRIBUTE, url.getHost()));
            attributes.add(new AnalyticAttribute(AnalyticAttribute.REQUEST_PATH_ATTRIBUTE, url.getPath()));
        } catch (MalformedURLException e) {
            log.error(txn.getUrl() + " is not a valid URL. Unable to set host or path attributes.");
        }
        attributes.add(new AnalyticAttribute(AnalyticAttribute.REQUEST_URL_ATTRIBUTE, txn.getUrl()));
        attributes.add(new AnalyticAttribute(AnalyticAttribute.CONNECTION_TYPE_ATTRIBUTE, txn.getWanType()));
        attributes.add(new AnalyticAttribute(AnalyticAttribute.REQUEST_METHOD_ATTRIBUTE, txn.getHttpMethod()));
        double totalTime = txn.getTotalTime();
        if (totalTime != 0.0d) {
            attributes.add(new AnalyticAttribute(AnalyticAttribute.RESPONSE_TIME_ATTRIBUTE, Double.valueOf(totalTime).floatValue()));
        }
        double bytesSent = (double) txn.getBytesSent();
        if (bytesSent != 0.0d) {
            attributes.add(new AnalyticAttribute(AnalyticAttribute.BYTES_SENT_ATTRIBUTE, Double.valueOf(bytesSent).floatValue()));
        }
        double bytesReceived = (double) txn.getBytesReceived();
        if (bytesReceived != 0.0d) {
            attributes.add(new AnalyticAttribute(AnalyticAttribute.BYTES_RECEIVED_ATTRIBUTE, Double.valueOf(bytesReceived).floatValue()));
        }
        return attributes;
    }
}
