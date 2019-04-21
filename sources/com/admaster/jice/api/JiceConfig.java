package com.admaster.jice.api;

public class JiceConfig {
    private boolean debugMode;
    private boolean isEventWifiOnly;
    private boolean isPushWifiOnly;

    public JiceConfig() {
        this.isEventWifiOnly = false;
        this.isPushWifiOnly = false;
        this.debugMode = false;
        this.isEventWifiOnly = false;
        this.isPushWifiOnly = false;
        this.debugMode = false;
    }

    public JiceConfig(boolean z, boolean z2, boolean z3) {
        this.isEventWifiOnly = false;
        this.isPushWifiOnly = false;
        this.debugMode = false;
        this.isEventWifiOnly = z;
        this.isPushWifiOnly = z2;
        this.debugMode = z3;
    }

    public boolean isEventWifiOnly() {
        return this.isEventWifiOnly;
    }

    public void setEventWifiOnly(boolean z) {
        this.isEventWifiOnly = z;
    }

    public boolean isPushWifiOnly() {
        return this.isPushWifiOnly;
    }

    public void setPushWifiOnly(boolean z) {
        this.isPushWifiOnly = z;
    }

    public boolean isDebugMode() {
        return this.debugMode;
    }

    public void setDebugMode(boolean z) {
        this.debugMode = z;
    }
}
