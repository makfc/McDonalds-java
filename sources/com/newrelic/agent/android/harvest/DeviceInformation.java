package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.ApplicationPlatform;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;
import java.util.HashMap;
import java.util.Map;

public class DeviceInformation extends HarvestableArray {
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private String agentName;
    private String agentVersion;
    private ApplicationPlatform applicationPlatform;
    private String applicationPlatformVersion;
    private String architecture;
    private String countryCode;
    private String deviceId;
    private String manufacturer;
    private Map<String, String> misc = new HashMap();
    private String model;
    private String osBuild;
    private String osName;
    private String osVersion;
    private String regionCode;
    private String runTime;
    private String size;

    public JsonArray asJsonArray() {
        JsonArray array = new JsonArray();
        notEmpty(this.osName);
        array.add(new JsonPrimitive(this.osName));
        notEmpty(this.osVersion);
        array.add(new JsonPrimitive(this.osVersion));
        notEmpty(this.manufacturer);
        notEmpty(this.model);
        array.add(new JsonPrimitive(this.manufacturer + " " + this.model));
        notEmpty(this.agentName);
        array.add(new JsonPrimitive(this.agentName));
        notEmpty(this.agentVersion);
        array.add(new JsonPrimitive(this.agentVersion));
        notEmpty(this.deviceId);
        array.add(new JsonPrimitive(this.deviceId));
        array.add(new JsonPrimitive(optional(this.countryCode)));
        array.add(new JsonPrimitive(optional(this.regionCode)));
        array.add(new JsonPrimitive(this.manufacturer));
        Map<String, String> miscMap = new HashMap();
        if (!(this.misc == null || this.misc.isEmpty())) {
            miscMap.putAll(this.misc);
        }
        if (this.applicationPlatform != null) {
            miscMap.put(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE, this.applicationPlatform.toString());
            if (this.applicationPlatformVersion != null) {
                miscMap.put(AnalyticAttribute.APPLICATION_PLATFORM_VERSION_ATTRIBUTE, this.applicationPlatformVersion);
            }
        }
        array.add(new Gson().toJsonTree(miscMap, GSON_STRING_MAP_TYPE));
        return array;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public void setOsBuild(String osBuild) {
        this.osBuild = osBuild;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public void setAgentVersion(String agentVersion) {
        this.agentVersion = agentVersion;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public void setSize(String size) {
        this.size = size;
        addMisc("size", size);
    }

    public void setApplicationPlatform(ApplicationPlatform applicationPlatform) {
        this.applicationPlatform = applicationPlatform;
    }

    public void setApplicationPlatformVersion(String applicationPlatformVersion) {
        this.applicationPlatformVersion = applicationPlatformVersion;
    }

    public void setMisc(Map<String, String> misc) {
        this.misc = new HashMap(misc);
    }

    public void addMisc(String key, String value) {
        this.misc.put(key, value);
    }

    public String getOsName() {
        return this.osName;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public String getOsBuild() {
        return this.osBuild;
    }

    public String getModel() {
        return this.model;
    }

    public String getAgentName() {
        return this.agentName;
    }

    public String getAgentVersion() {
        return this.agentVersion;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getArchitecture() {
        return this.architecture;
    }

    public String getRunTime() {
        return this.runTime;
    }

    public String getSize() {
        return this.size;
    }

    public ApplicationPlatform getApplicationPlatform() {
        return this.applicationPlatform;
    }

    public String getApplicationPlatformVersion() {
        return this.applicationPlatformVersion;
    }

    public String toJsonString() {
        return "DeviceInformation{manufacturer='" + this.manufacturer + '\'' + ", osName='" + this.osName + '\'' + ", osVersion='" + this.osVersion + '\'' + ", model='" + this.model + '\'' + ", agentName='" + this.agentName + '\'' + ", agentVersion='" + this.agentVersion + '\'' + ", deviceId='" + this.deviceId + '\'' + ", countryCode='" + this.countryCode + '\'' + ", regionCode='" + this.regionCode + '\'' + '}';
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceInformation that = (DeviceInformation) o;
        if (this.agentName == null ? that.agentName != null : !this.agentName.equals(that.agentName)) {
            return false;
        }
        if (this.agentVersion == null ? that.agentVersion != null : !this.agentVersion.equals(that.agentVersion)) {
            return false;
        }
        if (this.architecture == null ? that.architecture != null : !this.architecture.equals(that.architecture)) {
            return false;
        }
        if (this.deviceId == null ? that.deviceId != null : !this.deviceId.equals(that.deviceId)) {
            return false;
        }
        if (this.manufacturer == null ? that.manufacturer != null : !this.manufacturer.equals(that.manufacturer)) {
            return false;
        }
        if (this.model == null ? that.model != null : !this.model.equals(that.model)) {
            return false;
        }
        if (this.osBuild == null ? that.osBuild != null : !this.osBuild.equals(that.osBuild)) {
            return false;
        }
        if (this.osName == null ? that.osName != null : !this.osName.equals(that.osName)) {
            return false;
        }
        if (this.osVersion == null ? that.osVersion != null : !this.osVersion.equals(that.osVersion)) {
            return false;
        }
        if (this.runTime == null ? that.runTime != null : !this.runTime.equals(that.runTime)) {
            return false;
        }
        if (this.size != null) {
            if (this.size.equals(that.size)) {
                return true;
            }
        } else if (that.size == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result;
        int hashCode;
        int i = 0;
        if (this.osName != null) {
            result = this.osName.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.osVersion != null) {
            hashCode = this.osVersion.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.osBuild != null) {
            hashCode = this.osBuild.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.model != null) {
            hashCode = this.model.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.agentName != null) {
            hashCode = this.agentName.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.agentVersion != null) {
            hashCode = this.agentVersion.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.deviceId != null) {
            hashCode = this.deviceId.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.manufacturer != null) {
            hashCode = this.manufacturer.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.architecture != null) {
            hashCode = this.architecture.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.runTime != null) {
            hashCode = this.runTime.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (i2 + hashCode) * 31;
        if (this.size != null) {
            i = this.size.hashCode();
        }
        return hashCode + i;
    }
}
