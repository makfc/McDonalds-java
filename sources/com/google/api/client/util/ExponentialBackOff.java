package com.google.api.client.util;

import com.autonavi.amap.mapcore.VTMCDataCache;
import java.io.IOException;

public class ExponentialBackOff implements BackOff {
    private int currentIntervalMillis;
    private final int initialIntervalMillis;
    private final int maxElapsedTimeMillis;
    private final int maxIntervalMillis;
    private final double multiplier;
    private final NanoClock nanoClock;
    private final double randomizationFactor;
    long startTimeNanos;

    public static class Builder {
        int initialIntervalMillis = VTMCDataCache.MAXSIZE;
        int maxElapsedTimeMillis = 900000;
        int maxIntervalMillis = 60000;
        double multiplier = 1.5d;
        NanoClock nanoClock = NanoClock.SYSTEM;
        double randomizationFactor = 0.5d;

        public ExponentialBackOff build() {
            return new ExponentialBackOff(this);
        }
    }

    public ExponentialBackOff() {
        this(new Builder());
    }

    protected ExponentialBackOff(Builder builder) {
        boolean z;
        boolean z2 = true;
        this.initialIntervalMillis = builder.initialIntervalMillis;
        this.randomizationFactor = builder.randomizationFactor;
        this.multiplier = builder.multiplier;
        this.maxIntervalMillis = builder.maxIntervalMillis;
        this.maxElapsedTimeMillis = builder.maxElapsedTimeMillis;
        this.nanoClock = builder.nanoClock;
        Preconditions.checkArgument(this.initialIntervalMillis > 0);
        if (0.0d > this.randomizationFactor || this.randomizationFactor >= 1.0d) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z);
        if (this.multiplier >= 1.0d) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (this.maxIntervalMillis >= this.initialIntervalMillis) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (this.maxElapsedTimeMillis <= 0) {
            z2 = false;
        }
        Preconditions.checkArgument(z2);
        reset();
    }

    public final void reset() {
        this.currentIntervalMillis = this.initialIntervalMillis;
        this.startTimeNanos = this.nanoClock.nanoTime();
    }

    public long nextBackOffMillis() throws IOException {
        if (getElapsedTimeMillis() > ((long) this.maxElapsedTimeMillis)) {
            return -1;
        }
        int randomizedInterval = getRandomValueFromInterval(this.randomizationFactor, Math.random(), this.currentIntervalMillis);
        incrementCurrentInterval();
        return (long) randomizedInterval;
    }

    static int getRandomValueFromInterval(double randomizationFactor, double random, int currentIntervalMillis) {
        double delta = randomizationFactor * ((double) currentIntervalMillis);
        double minInterval = ((double) currentIntervalMillis) - delta;
        return (int) (((((((double) currentIntervalMillis) + delta) - minInterval) + 1.0d) * random) + minInterval);
    }

    public final long getElapsedTimeMillis() {
        return (this.nanoClock.nanoTime() - this.startTimeNanos) / 1000000;
    }

    private void incrementCurrentInterval() {
        if (((double) this.currentIntervalMillis) >= ((double) this.maxIntervalMillis) / this.multiplier) {
            this.currentIntervalMillis = this.maxIntervalMillis;
        } else {
            this.currentIntervalMillis = (int) (((double) this.currentIntervalMillis) * this.multiplier);
        }
    }
}
