package com.newrelic.agent.android.api.common;

import java.util.concurrent.TimeUnit;

public final class ConnectionState {
    public static final ConnectionState NULL = new ConnectionState();
    private final boolean collectingNetworkErrors;
    private final String crossProcessId;
    private final Object dataToken;
    private final int errorLimit;
    private final long harvestInterval;
    private final TimeUnit harvestIntervalTimeUnit;
    private final long maxTransactionAge;
    private final TimeUnit maxTransactionAgeTimeUnit;
    private final long maxTransactionCount;
    private final int responseBodyLimit;
    private final long serverTimestamp;
    private final int stackTraceLimit;

    private ConnectionState() {
        this.dataToken = null;
        this.crossProcessId = null;
        this.serverTimestamp = 0;
        this.harvestInterval = 60;
        this.harvestIntervalTimeUnit = TimeUnit.SECONDS;
        this.maxTransactionAge = 600;
        this.maxTransactionAgeTimeUnit = TimeUnit.SECONDS;
        this.maxTransactionCount = 1000;
        this.stackTraceLimit = 50;
        this.responseBodyLimit = 1024;
        this.collectingNetworkErrors = true;
        this.errorLimit = 10;
    }

    public ConnectionState(Object dataToken, String crossProcessId, long serverTimestamp, long harvestInterval, TimeUnit harvestIntervalTimeUnit, long maxTransactionAge, TimeUnit maxTransactionAgeTimeUnit, long maxTransactionCount, int stackTraceLimit, int responseBodyLimit, boolean collectingNetworkerrors, int errorLimit) {
        this.dataToken = dataToken;
        this.crossProcessId = crossProcessId;
        this.serverTimestamp = serverTimestamp;
        this.harvestInterval = harvestInterval;
        this.harvestIntervalTimeUnit = harvestIntervalTimeUnit;
        this.maxTransactionAge = maxTransactionAge;
        this.maxTransactionAgeTimeUnit = maxTransactionAgeTimeUnit;
        this.maxTransactionCount = maxTransactionCount;
        this.stackTraceLimit = stackTraceLimit;
        this.responseBodyLimit = responseBodyLimit;
        this.collectingNetworkErrors = collectingNetworkerrors;
        this.errorLimit = errorLimit;
    }

    public Object getDataToken() {
        return this.dataToken;
    }

    public String getCrossProcessId() {
        return this.crossProcessId;
    }

    public long getServerTimestamp() {
        return this.serverTimestamp;
    }

    public long getHarvestIntervalInSeconds() {
        return TimeUnit.SECONDS.convert(this.harvestInterval, this.harvestIntervalTimeUnit);
    }

    public long getHarvestIntervalInMilliseconds() {
        return TimeUnit.MILLISECONDS.convert(this.harvestInterval, this.harvestIntervalTimeUnit);
    }

    public long getMaxTransactionAgeInSeconds() {
        return TimeUnit.SECONDS.convert(this.maxTransactionAge, this.maxTransactionAgeTimeUnit);
    }

    public long getMaxTransactionAgeInMilliseconds() {
        return TimeUnit.MILLISECONDS.convert(this.maxTransactionAge, this.maxTransactionAgeTimeUnit);
    }

    public long getMaxTransactionCount() {
        return this.maxTransactionCount;
    }

    public int getStackTraceLimit() {
        return this.stackTraceLimit;
    }

    public int getResponseBodyLimit() {
        return this.responseBodyLimit;
    }

    public boolean isCollectingNetworkErrors() {
        return this.collectingNetworkErrors;
    }

    public int getErrorLimit() {
        return this.errorLimit;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.dataToken);
        return sb.toString();
    }
}
