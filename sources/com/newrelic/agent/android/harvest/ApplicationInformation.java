package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import com.newrelic.com.google.gson.JsonPrimitive;

public class ApplicationInformation extends HarvestableArray {
    private String appBuild;
    private String appName;
    private String appVersion;
    private String packageId;
    private int versionCode;

    public ApplicationInformation() {
        this.versionCode = -1;
    }

    public ApplicationInformation(String appName, String appVersion, String packageId, String appBuild) {
        this();
        this.appName = appName;
        this.appVersion = appVersion;
        this.packageId = packageId;
        this.appBuild = appBuild;
    }

    public JsonArray asJsonArray() {
        JsonArray array = new JsonArray();
        notEmpty(this.appName);
        array.add(new JsonPrimitive(this.appName));
        notEmpty(this.appVersion);
        array.add(new JsonPrimitive(this.appVersion));
        notEmpty(this.packageId);
        array.add(new JsonPrimitive(this.packageId));
        return array;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppBuild(String appBuild) {
        this.appBuild = appBuild;
    }

    public String getAppBuild() {
        return this.appBuild;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageId() {
        return this.packageId;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApplicationInformation that = (ApplicationInformation) o;
        if (this.appName == null ? that.appName != null : !this.appName.equals(that.appName)) {
            return false;
        }
        if (this.appVersion == null ? that.appVersion != null : !this.appVersion.equals(that.appVersion)) {
            return false;
        }
        if (this.appBuild == null ? that.appBuild != null : !this.appBuild.equals(that.appBuild)) {
            return false;
        }
        if (this.packageId == null ? that.packageId != null : !this.packageId.equals(that.packageId)) {
            return false;
        }
        if (this.versionCode != that.versionCode) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result;
        int hashCode;
        int i = 0;
        if (this.appName != null) {
            result = this.appName.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.appVersion != null) {
            hashCode = this.appVersion.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.appBuild != null) {
            hashCode = this.appBuild.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (i2 + hashCode) * 31;
        if (this.packageId != null) {
            i = this.packageId.hashCode();
        }
        return hashCode + i;
    }

    public boolean isAppUpgrade(ApplicationInformation that) {
        return that.versionCode == -1 ? this.versionCode >= 0 && that.appVersion != null : this.versionCode > that.versionCode;
    }
}
