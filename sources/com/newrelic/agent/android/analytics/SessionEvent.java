package com.newrelic.agent.android.analytics;

import java.util.Set;

public class SessionEvent extends AnalyticsEvent {
    public SessionEvent() {
        super(null, AnalyticsEventCategory.Session);
    }

    public SessionEvent(Set<AnalyticAttribute> attributeSet) {
        super(null, AnalyticsEventCategory.Session, AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE, attributeSet);
    }
}
