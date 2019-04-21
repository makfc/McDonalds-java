package com.newrelic.agent.android.analytics;

public enum AnalyticsEventCategory {
    Session,
    Interaction,
    Crash,
    Custom,
    NetworkRequest,
    RequestError,
    Breadcrumb;

    public static AnalyticsEventCategory fromString(String categoryString) {
        AnalyticsEventCategory category = Custom;
        if (categoryString == null) {
            return category;
        }
        if (categoryString.equalsIgnoreCase("session")) {
            return Session;
        }
        if (categoryString.equalsIgnoreCase("interaction")) {
            return Interaction;
        }
        if (categoryString.equalsIgnoreCase("crash")) {
            return Crash;
        }
        if (categoryString.equalsIgnoreCase("requesterror")) {
            return RequestError;
        }
        if (categoryString.equalsIgnoreCase("breadcrumb")) {
            return Breadcrumb;
        }
        if (categoryString.equalsIgnoreCase("networkrequest")) {
            return NetworkRequest;
        }
        return category;
    }
}
