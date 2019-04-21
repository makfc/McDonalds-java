package com.mcdonalds.app.analytics.datalayer.analytics;

import java.util.Map;

public interface DataLayerAnalytics {
    void log(String str, Map<String, Object> map);
}
