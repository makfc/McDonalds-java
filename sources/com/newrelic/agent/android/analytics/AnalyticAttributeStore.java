package com.newrelic.agent.android.analytics;

import com.newrelic.agent.android.payload.PayloadStore;
import java.util.List;

public interface AnalyticAttributeStore extends PayloadStore<AnalyticAttribute> {
    void clear();

    int count();

    void delete(AnalyticAttribute analyticAttribute);

    List<AnalyticAttribute> fetchAll();

    boolean store(AnalyticAttribute analyticAttribute);
}
