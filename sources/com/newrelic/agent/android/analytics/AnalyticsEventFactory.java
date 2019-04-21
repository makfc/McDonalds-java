package com.newrelic.agent.android.analytics;

import java.util.Set;

class AnalyticsEventFactory {
    static AnalyticsEvent createEvent(String name, AnalyticsEventCategory eventCategory, String eventType, Set<AnalyticAttribute> eventAttributes) {
        switch (eventCategory) {
            case Session:
                return new SessionEvent(eventAttributes);
            case RequestError:
                return new NetworkRequestErrorEvent(eventAttributes);
            case Interaction:
                return new InteractionEvent(name, eventAttributes);
            case Crash:
                return new CrashEvent(name, eventAttributes);
            case Custom:
                return new CustomEvent(name, eventType, eventAttributes);
            case Breadcrumb:
                return new BreadcrumbEvent(name, eventAttributes);
            case NetworkRequest:
                return new NetworkRequestEvent(eventAttributes);
            default:
                return null;
        }
    }

    private AnalyticsEventFactory() {
    }
}
