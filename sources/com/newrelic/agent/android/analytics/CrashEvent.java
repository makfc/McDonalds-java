package com.newrelic.agent.android.analytics;

import java.util.Set;

public class CrashEvent extends AnalyticsEvent {
    public CrashEvent(String name) {
        super(name, AnalyticsEventCategory.Crash);
    }

    public CrashEvent(String name, Set<AnalyticAttribute> attributeSet) {
        super(name, AnalyticsEventCategory.Crash, AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE, attributeSet);
    }
}
