package com.newrelic.agent.android.harvest.crash;

import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.harvest.EnvironmentInformation;
import com.newrelic.agent.android.harvest.type.HarvestableObject;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonElement;
import com.newrelic.com.google.gson.JsonObject;
import java.util.Iterator;

public class DeviceInfo extends HarvestableObject {
    private String OSBuild;
    private String OSVersion;
    private String architecture;
    private String deviceName;
    private String deviceUuid;
    private long[] diskAvailable;
    private long memoryUsage;
    private String modelNumber;
    private String networkStatus;
    private int orientation;
    private String runTime;
    private String screenResolution;

    public DeviceInfo(DeviceInformation devInfo, EnvironmentInformation envInfo) {
        this.memoryUsage = envInfo.getMemoryUsage();
        this.orientation = envInfo.getOrientation();
        this.networkStatus = envInfo.getNetworkStatus();
        this.diskAvailable = envInfo.getDiskAvailable();
        this.OSVersion = devInfo.getOsVersion();
        this.deviceName = devInfo.getManufacturer();
        this.OSBuild = devInfo.getOsBuild();
        this.architecture = devInfo.getArchitecture();
        this.modelNumber = devInfo.getModel();
        this.screenResolution = devInfo.getSize();
        this.deviceUuid = devInfo.getDeviceId();
        this.runTime = devInfo.getRunTime();
    }

    public JsonObject asJsonObject() {
        JsonObject data = new JsonObject();
        data.add("memoryUsage", SafeJsonPrimitive.factory(Long.valueOf(this.memoryUsage)));
        data.add("orientation", SafeJsonPrimitive.factory(Integer.valueOf(this.orientation)));
        data.add("networkStatus", SafeJsonPrimitive.factory(this.networkStatus));
        data.add("diskAvailable", getDiskAvailableAsJson());
        data.add(AnalyticAttribute.OS_VERSION_ATTRIBUTE, SafeJsonPrimitive.factory(this.OSVersion));
        data.add("deviceName", SafeJsonPrimitive.factory(this.deviceName));
        data.add(AnalyticAttribute.OS_BUILD_ATTRIBUTE, SafeJsonPrimitive.factory(this.OSBuild));
        data.add(AnalyticAttribute.ARCHITECTURE_ATTRIBUTE, SafeJsonPrimitive.factory(this.architecture));
        data.add(AnalyticAttribute.RUNTIME_ATTRIBUTE, SafeJsonPrimitive.factory(this.runTime));
        data.add("modelNumber", SafeJsonPrimitive.factory(this.modelNumber));
        data.add("screenResolution", SafeJsonPrimitive.factory(this.screenResolution));
        data.add("deviceUuid", SafeJsonPrimitive.factory(this.deviceUuid));
        return data;
    }

    public static DeviceInfo newFromJson(JsonObject jsonObject) {
        DeviceInfo info = new DeviceInfo();
        info.memoryUsage = jsonObject.get("memoryUsage").getAsLong();
        info.orientation = jsonObject.get("orientation").getAsInt();
        info.networkStatus = jsonObject.get("networkStatus").getAsString();
        info.diskAvailable = longArrayFromJsonArray(jsonObject.get("diskAvailable").getAsJsonArray());
        info.OSVersion = jsonObject.get(AnalyticAttribute.OS_VERSION_ATTRIBUTE).getAsString();
        info.deviceName = jsonObject.get("deviceName").getAsString();
        info.OSBuild = jsonObject.get(AnalyticAttribute.OS_BUILD_ATTRIBUTE).getAsString();
        info.architecture = jsonObject.get(AnalyticAttribute.ARCHITECTURE_ATTRIBUTE).getAsString();
        info.runTime = jsonObject.get(AnalyticAttribute.RUNTIME_ATTRIBUTE).getAsString();
        info.modelNumber = jsonObject.get("modelNumber").getAsString();
        info.screenResolution = jsonObject.get("screenResolution").getAsString();
        info.deviceUuid = jsonObject.get("deviceUuid").getAsString();
        return info;
    }

    private static long[] longArrayFromJsonArray(JsonArray jsonArray) {
        long[] array = new long[jsonArray.size()];
        int i = 0;
        Iterator it = jsonArray.iterator();
        while (it.hasNext()) {
            int i2 = i + 1;
            array[i] = ((JsonElement) it.next()).getAsLong();
            i = i2;
        }
        return array;
    }

    private JsonArray getDiskAvailableAsJson() {
        JsonArray data = new JsonArray();
        for (long value : this.diskAvailable) {
            data.add(SafeJsonPrimitive.factory(Long.valueOf(value)));
        }
        return data;
    }
}
