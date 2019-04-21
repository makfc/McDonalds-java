package com.newrelic.agent.android.harvest;

public class EnvironmentInformation {
    private long[] diskAvailable;
    private long memoryUsage;
    private String networkStatus;
    private String networkWanType;
    private int orientation;

    public EnvironmentInformation(long memoryUsage, int orientation, String networkStatus, String networkWanType, long[] diskAvailable) {
        this.memoryUsage = memoryUsage;
        this.orientation = orientation;
        this.networkStatus = networkStatus;
        this.networkWanType = networkWanType;
        this.diskAvailable = diskAvailable;
    }

    public void setMemoryUsage(long memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void setNetworkStatus(String networkStatus) {
        this.networkStatus = networkStatus;
    }

    public void setNetworkWanType(String networkWanType) {
        this.networkWanType = networkWanType;
    }

    public void setDiskAvailable(long[] diskAvailable) {
        this.diskAvailable = diskAvailable;
    }

    public long getMemoryUsage() {
        return this.memoryUsage;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public String getNetworkStatus() {
        return this.networkStatus;
    }

    public String getNetworkWanType() {
        return this.networkWanType;
    }

    public long[] getDiskAvailable() {
        return this.diskAvailable;
    }
}
