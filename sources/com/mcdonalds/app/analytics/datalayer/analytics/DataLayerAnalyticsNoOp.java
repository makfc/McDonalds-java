package com.mcdonalds.app.analytics.datalayer.analytics;

import com.ensighten.Ensighten;
import java.util.Map;

public class DataLayerAnalyticsNoOp implements DataLayerAnalytics {
    public void log(String eventName, Map<String, Object> eventValue) {
        Ensighten.evaluateEvent(this, "log", new Object[]{eventName, eventValue});
    }
}
