package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.com.google.gson.JsonArray;

public class ConnectInformation extends HarvestableArray {
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private ApplicationInformation applicationInformation;
    private DeviceInformation deviceInformation;

    public ConnectInformation(ApplicationInformation applicationInformation, DeviceInformation deviceInformation) {
        if (applicationInformation == null) {
            log.error("null applicationInformation passed into ConnectInformation constructor");
        }
        if (deviceInformation == null) {
            log.error("null deviceInformation passed into ConnectInformation constructor");
        }
        this.applicationInformation = applicationInformation;
        this.deviceInformation = deviceInformation;
    }

    public JsonArray asJsonArray() {
        JsonArray array = new JsonArray();
        notNull(this.applicationInformation);
        array.add(this.applicationInformation.asJsonArray());
        notNull(this.deviceInformation);
        array.add(this.deviceInformation.asJsonArray());
        return array;
    }

    public ApplicationInformation getApplicationInformation() {
        return this.applicationInformation;
    }

    public DeviceInformation getDeviceInformation() {
        return this.deviceInformation;
    }

    public void setApplicationInformation(ApplicationInformation applicationInformation) {
        this.applicationInformation = applicationInformation;
    }

    public void setDeviceInformation(DeviceInformation deviceInformation) {
        this.deviceInformation = deviceInformation;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConnectInformation that = (ConnectInformation) o;
        if (this.applicationInformation == null ? that.applicationInformation != null : !this.applicationInformation.equals(that.applicationInformation)) {
            return false;
        }
        if (this.deviceInformation != null) {
            if (this.deviceInformation.equals(that.deviceInformation)) {
                return true;
            }
        } else if (that.deviceInformation == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result;
        int i = 0;
        if (this.applicationInformation != null) {
            result = this.applicationInformation.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.deviceInformation != null) {
            i = this.deviceInformation.hashCode();
        }
        return i2 + i;
    }
}
