package com.newrelic.agent.android.stats;

import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.metric.Metric;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class StatsEngine extends HarvestAdapter {
    public static final StatsEngine INSTANCE = new StatsEngine();
    public boolean enabled = true;
    private ConcurrentHashMap<String, Metric> statsMap = new ConcurrentHashMap();

    private StatsEngine() {
    }

    public static StatsEngine get() {
        return INSTANCE;
    }

    public void inc(String name) {
        Metric m = lazyGet(name);
        synchronized (m) {
            m.increment();
        }
    }

    public void inc(String name, long count) {
        Metric m = lazyGet(name);
        synchronized (m) {
            m.increment(count);
        }
    }

    public void sample(String name, float value) {
        Metric m = lazyGet(name);
        synchronized (m) {
            m.sample((double) value);
        }
    }

    public void sampleTimeMs(String name, long time) {
        sample(name, ((float) time) / 1000.0f);
    }

    public static void populateMetrics() {
        for (Entry<String, Metric> entry : INSTANCE.getStatsMap().entrySet()) {
            TaskQueue.queue((Metric) entry.getValue());
        }
    }

    public void onHarvest() {
        populateMetrics();
        reset();
    }

    public static void reset() {
        INSTANCE.getStatsMap().clear();
    }

    public static synchronized void disable() {
        synchronized (StatsEngine.class) {
            INSTANCE.enabled = false;
        }
    }

    public static synchronized void enable() {
        synchronized (StatsEngine.class) {
            INSTANCE.enabled = true;
        }
    }

    public ConcurrentHashMap<String, Metric> getStatsMap() {
        return this.statsMap;
    }

    private Metric lazyGet(String name) {
        Metric m = (Metric) this.statsMap.get(name);
        if (m == null) {
            m = new Metric(name);
            if (this.enabled) {
                this.statsMap.put(name, m);
            }
        }
        return m;
    }
}
