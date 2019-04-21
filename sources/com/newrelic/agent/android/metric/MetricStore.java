package com.newrelic.agent.android.metric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class MetricStore {
    private final Map<String, Map<String, Metric>> metricStore = new ConcurrentHashMap();

    public void add(Metric metric) {
        String scope = metric.getStringScope();
        String name = metric.getName();
        if (!this.metricStore.containsKey(scope)) {
            this.metricStore.put(scope, new HashMap());
        }
        if (((Map) this.metricStore.get(scope)).containsKey(name)) {
            ((Metric) ((Map) this.metricStore.get(scope)).get(name)).aggregate(metric);
        } else {
            ((Map) this.metricStore.get(scope)).put(name, metric);
        }
    }

    public Metric get(String name) {
        return get(name, "");
    }

    public Metric get(String name, String scope) {
        try {
            Map map = this.metricStore;
            if (scope == null) {
                scope = "";
            }
            return (Metric) ((Map) map.get(scope)).get(name);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public List<Metric> getAll() {
        List<Metric> metrics = new ArrayList();
        for (Entry<String, Map<String, Metric>> entry : this.metricStore.entrySet()) {
            for (Entry<String, Metric> metricEntry : ((Map) entry.getValue()).entrySet()) {
                metrics.add(metricEntry.getValue());
            }
        }
        return metrics;
    }

    public List<Metric> getAllByScope(String scope) {
        List<Metric> metrics = new ArrayList();
        try {
            for (Entry<String, Metric> metricEntry : ((Map) this.metricStore.get(scope)).entrySet()) {
                metrics.add(metricEntry.getValue());
            }
        } catch (NullPointerException e) {
        }
        return metrics;
    }

    public List<Metric> getAllUnscoped() {
        return getAllByScope("");
    }

    public void remove(Metric metric) {
        String scope = metric.getStringScope();
        String name = metric.getName();
        if (this.metricStore.containsKey(scope) && ((Map) this.metricStore.get(scope)).containsKey(name)) {
            ((Map) this.metricStore.get(scope)).remove(name);
        }
    }

    public void removeAll(List<Metric> metrics) {
        synchronized (this.metricStore) {
            for (Metric metric : metrics) {
                remove(metric);
            }
        }
    }

    public List<Metric> removeAllWithScope(String scope) {
        List<Metric> metrics = getAllByScope(scope);
        if (!metrics.isEmpty()) {
            removeAll(metrics);
        }
        return metrics;
    }

    public void clear() {
        this.metricStore.clear();
    }

    public boolean isEmpty() {
        return this.metricStore.isEmpty();
    }
}
