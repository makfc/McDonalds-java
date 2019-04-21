package com.newrelic.agent.android.analytics;

import java.util.Set;

public class InteractionEvent extends AnalyticsEvent {
    public InteractionEvent(String name) {
        super(name, AnalyticsEventCategory.Interaction);
    }

    public InteractionEvent(String name, Set<AnalyticAttribute> attributeSet) {
        super(name, AnalyticsEventCategory.Interaction, AnalyticAttribute.EVENT_TYPE_ATTRIBUTE_MOBILE, attributeSet);
    }
}
