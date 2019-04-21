package com.newrelic.agent.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.newrelic.agent.android.agentdata.HexAttributes;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.harvest.ApplicationInformation;
import com.newrelic.agent.android.harvest.ConnectInformation;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.harvest.HarvestAdapter;
import com.newrelic.agent.android.harvest.HarvestConfiguration;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

public class SavedState extends HarvestAdapter {
    private static final AgentLog log = AgentLogManager.getAgentLog();
    private final String NEW_RELIC_AGENT_DISABLED_VERSION_KEY = "NewRelicAgentDisabledVersion";
    private final String PREFERENCE_FILE_PREFIX = "com.newrelic.android.agent.v1_";
    private final String PREF_ACTIVITY_TRACE_MIN_UTILIZATION = "activityTraceMinUtilization";
    private final String PREF_AGENT_NAME = "agentName";
    private final String PREF_AGENT_VERSION = "agentVersion";
    private final String PREF_APP_BUILD = "appBuild";
    private final String PREF_APP_NAME = AnalyticAttribute.APP_NAME_ATTRIBUTE;
    private final String PREF_APP_TOKEN = "appToken";
    private final String PREF_APP_VERSION = HexAttributes.HEX_ATTR_APP_VERSION;
    private final String PREF_COLLECT_NETWORK_ERRORS = "collectNetworkErrors";
    private final String PREF_CROSS_PROCESS_ID = "crossProcessId";
    private final String PREF_DATA_TOKEN = "dataToken";
    private final String PREF_DEVICE_ARCHITECTURE = "deviceArchitecture";
    private final String PREF_DEVICE_ID = "deviceId";
    private final String PREF_DEVICE_MANUFACTURER = AnalyticAttribute.DEVICE_MANUFACTURER_ATTRIBUTE;
    private final String PREF_DEVICE_MODEL = AnalyticAttribute.DEVICE_MODEL_ATTRIBUTE;
    private final String PREF_DEVICE_RUN_TIME = "deviceRunTime";
    private final String PREF_DEVICE_SIZE = "deviceSize";
    private final String PREF_ERROR_LIMIT = "errorLimit";
    private final String PREF_HARVEST_INTERVAL = "harvestIntervalInSeconds";
    private final String PREF_MAX_TRANSACTION_AGE = "maxTransactionAgeInSeconds";
    private final String PREF_MAX_TRANSACTION_COUNT = "maxTransactionCount";
    private final String PREF_OS_BUILD = AnalyticAttribute.OS_BUILD_ATTRIBUTE;
    private final String PREF_OS_NAME = AnalyticAttribute.OS_NAME_ATTRIBUTE;
    private final String PREF_OS_VERSION = AnalyticAttribute.OS_VERSION_ATTRIBUTE;
    private final String PREF_PACKAGE_ID = "packageId";
    private final String PREF_PLATFORM = AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE;
    private final String PREF_PLATFORM_VERSION = AnalyticAttribute.APPLICATION_PLATFORM_VERSION_ATTRIBUTE;
    private final String PREF_RESPONSE_BODY_LIMIT = "responseBodyLimit";
    private final String PREF_SERVER_TIMESTAMP = "serverTimestamp";
    private final String PREF_STACK_TRACE_LIMIT = "stackTraceLimit";
    private final String PREF_VERSION_CODE = "versionCode";
    private Float activityTraceMinUtilization;
    private final HarvestConfiguration configuration = new HarvestConfiguration();
    private final ConnectInformation connectInformation = new ConnectInformation(new ApplicationInformation(), new DeviceInformation());
    private final Editor editor;
    private final Lock lock = new ReentrantLock();
    private final SharedPreferences prefs;

    @SuppressLint({"CommitPrefEdits"})
    public SavedState(Context context) {
        this.prefs = context.getSharedPreferences(getPreferenceFileName(context.getPackageName()), 0);
        this.editor = this.prefs.edit();
        loadHarvestConfiguration();
        loadConnectInformation();
    }

    public void saveHarvestConfiguration(HarvestConfiguration newConfiguration) {
        if (!this.configuration.equals(newConfiguration)) {
            if (!newConfiguration.getDataToken().isValid()) {
                newConfiguration.setData_token(this.configuration.getData_token());
            }
            log.info("Saving configuration: " + newConfiguration);
            String newDataToken = newConfiguration.getDataToken().toJsonString();
            log.debug("!! saving data token: " + newDataToken);
            save("dataToken", newDataToken);
            save("crossProcessId", newConfiguration.getCross_process_id());
            save("serverTimestamp", newConfiguration.getServer_timestamp());
            save("harvestIntervalInSeconds", (long) newConfiguration.getData_report_period());
            save("maxTransactionAgeInSeconds", (long) newConfiguration.getReport_max_transaction_age());
            save("maxTransactionCount", (long) newConfiguration.getReport_max_transaction_count());
            save("stackTraceLimit", newConfiguration.getStack_trace_limit());
            save("responseBodyLimit", newConfiguration.getResponse_body_limit());
            save("collectNetworkErrors", newConfiguration.isCollect_network_errors());
            save("errorLimit", newConfiguration.getError_limit());
            saveActivityTraceMinUtilization((float) newConfiguration.getActivity_trace_min_utilization());
            loadHarvestConfiguration();
        }
    }

    public void loadHarvestConfiguration() {
        if (has("dataToken")) {
            this.configuration.setData_token(getDataToken());
        }
        if (has("crossProcessId")) {
            this.configuration.setCross_process_id(getCrossProcessId());
        }
        if (has("serverTimestamp")) {
            this.configuration.setServer_timestamp(getServerTimestamp());
        }
        if (has("harvestIntervalInSeconds")) {
            this.configuration.setData_report_period((int) getHarvestIntervalInSeconds());
        }
        if (has("maxTransactionAgeInSeconds")) {
            this.configuration.setReport_max_transaction_age((int) getMaxTransactionAgeInSeconds());
        }
        if (has("maxTransactionCount")) {
            this.configuration.setReport_max_transaction_count((int) getMaxTransactionCount());
        }
        if (has("stackTraceLimit")) {
            this.configuration.setStack_trace_limit(getStackTraceLimit());
        }
        if (has("responseBodyLimit")) {
            this.configuration.setResponse_body_limit(getResponseBodyLimit());
        }
        if (has("collectNetworkErrors")) {
            this.configuration.setCollect_network_errors(isCollectingNetworkErrors());
        }
        if (has("errorLimit")) {
            this.configuration.setError_limit(getErrorLimit());
        }
        if (has("activityTraceMinUtilization")) {
            this.configuration.setActivity_trace_min_utilization((double) getActivityTraceMinUtilization());
        }
        log.info("Loaded configuration: " + this.configuration);
    }

    public void saveConnectInformation(ConnectInformation newConnectInformation) {
        if (!this.connectInformation.equals(newConnectInformation)) {
            saveApplicationInformation(newConnectInformation.getApplicationInformation());
            saveDeviceInformation(newConnectInformation.getDeviceInformation());
            loadConnectInformation();
        }
    }

    public void saveDeviceId(String deviceId) {
        save("deviceId", deviceId);
        this.connectInformation.getDeviceInformation().setDeviceId(deviceId);
    }

    public String getAppToken() {
        return getString("appToken");
    }

    public void saveAppToken(String appToken) {
        save("appToken", appToken);
    }

    private void saveApplicationInformation(ApplicationInformation applicationInformation) {
        save(AnalyticAttribute.APP_NAME_ATTRIBUTE, applicationInformation.getAppName());
        save(HexAttributes.HEX_ATTR_APP_VERSION, applicationInformation.getAppVersion());
        save("appBuild", applicationInformation.getAppBuild());
        save("packageId", applicationInformation.getPackageId());
        save("versionCode", applicationInformation.getVersionCode());
    }

    private void saveDeviceInformation(DeviceInformation deviceInformation) {
        save("agentName", deviceInformation.getAgentName());
        save("agentVersion", deviceInformation.getAgentVersion());
        save("deviceArchitecture", deviceInformation.getArchitecture());
        save("deviceId", deviceInformation.getDeviceId());
        save(AnalyticAttribute.DEVICE_MODEL_ATTRIBUTE, deviceInformation.getModel());
        save(AnalyticAttribute.DEVICE_MANUFACTURER_ATTRIBUTE, deviceInformation.getManufacturer());
        save("deviceRunTime", deviceInformation.getRunTime());
        save("deviceSize", deviceInformation.getSize());
        save(AnalyticAttribute.OS_NAME_ATTRIBUTE, deviceInformation.getOsName());
        save(AnalyticAttribute.OS_BUILD_ATTRIBUTE, deviceInformation.getOsBuild());
        save(AnalyticAttribute.OS_VERSION_ATTRIBUTE, deviceInformation.getOsVersion());
        save(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE, deviceInformation.getApplicationPlatform().toString());
        save(AnalyticAttribute.APPLICATION_PLATFORM_VERSION_ATTRIBUTE, deviceInformation.getApplicationPlatformVersion());
    }

    public void loadConnectInformation() {
        ApplicationInformation applicationInformation = new ApplicationInformation();
        if (has(AnalyticAttribute.APP_NAME_ATTRIBUTE)) {
            applicationInformation.setAppName(getAppName());
        }
        if (has(HexAttributes.HEX_ATTR_APP_VERSION)) {
            applicationInformation.setAppVersion(getAppVersion());
        }
        if (has("appBuild")) {
            applicationInformation.setAppBuild(getAppBuild());
        }
        if (has("packageId")) {
            applicationInformation.setPackageId(getPackageId());
        }
        if (has("versionCode")) {
            applicationInformation.setVersionCode(getVersionCode());
        }
        DeviceInformation deviceInformation = new DeviceInformation();
        if (has("agentName")) {
            deviceInformation.setAgentName(getAgentName());
        }
        if (has("agentVersion")) {
            deviceInformation.setAgentVersion(getAgentVersion());
        }
        if (has("deviceArchitecture")) {
            deviceInformation.setArchitecture(getDeviceArchitecture());
        }
        if (has("deviceId")) {
            deviceInformation.setDeviceId(getDeviceId());
        }
        if (has(AnalyticAttribute.DEVICE_MODEL_ATTRIBUTE)) {
            deviceInformation.setModel(getDeviceModel());
        }
        if (has(AnalyticAttribute.DEVICE_MANUFACTURER_ATTRIBUTE)) {
            deviceInformation.setManufacturer(getDeviceManufacturer());
        }
        if (has("deviceRunTime")) {
            deviceInformation.setRunTime(getDeviceRunTime());
        }
        if (has("deviceSize")) {
            deviceInformation.setSize(getDeviceSize());
        }
        if (has(AnalyticAttribute.OS_NAME_ATTRIBUTE)) {
            deviceInformation.setOsName(getOsName());
        }
        if (has(AnalyticAttribute.OS_BUILD_ATTRIBUTE)) {
            deviceInformation.setOsBuild(getOsBuild());
        }
        if (has(AnalyticAttribute.OS_VERSION_ATTRIBUTE)) {
            deviceInformation.setOsVersion(getOsVersion());
        }
        if (has(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE)) {
            deviceInformation.setApplicationPlatform(getPlatform());
        }
        if (has(AnalyticAttribute.APPLICATION_PLATFORM_VERSION_ATTRIBUTE)) {
            deviceInformation.setApplicationPlatformVersion(getPlatformVersion());
        }
        this.connectInformation.setApplicationInformation(applicationInformation);
        this.connectInformation.setDeviceInformation(deviceInformation);
    }

    public HarvestConfiguration getHarvestConfiguration() {
        return this.configuration;
    }

    public ConnectInformation getConnectInformation() {
        return this.connectInformation;
    }

    private boolean has(String key) {
        return this.prefs.contains(key);
    }

    public void onHarvestConnected() {
        saveHarvestConfiguration(Harvest.getHarvestConfiguration());
    }

    public void onHarvestComplete() {
        saveHarvestConfiguration(Harvest.getHarvestConfiguration());
    }

    public void onHarvestDisconnected() {
        log.info("Clearing harvest configuration.");
        clear();
    }

    public void onHarvestDisabled() {
        String agentVersion = Agent.getDeviceInformation().getAgentVersion();
        log.info("Disabling agent version " + agentVersion);
        saveDisabledVersion(agentVersion);
    }

    public void save(String key, String value) {
        this.lock.lock();
        try {
            this.editor.putString(key, value);
            this.editor.apply();
        } finally {
            this.lock.unlock();
        }
    }

    public void save(String key, boolean value) {
        this.lock.lock();
        try {
            this.editor.putBoolean(key, value);
            this.editor.apply();
        } finally {
            this.lock.unlock();
        }
    }

    public void save(String key, int value) {
        this.lock.lock();
        try {
            this.editor.putInt(key, value);
            this.editor.apply();
        } finally {
            this.lock.unlock();
        }
    }

    public void save(String key, long value) {
        this.lock.lock();
        try {
            this.editor.putLong(key, value);
            this.editor.apply();
        } finally {
            this.lock.unlock();
        }
    }

    public void save(String key, float value) {
        this.lock.lock();
        try {
            this.editor.putFloat(key, value);
            this.editor.apply();
        } finally {
            this.lock.unlock();
        }
    }

    public String getString(String key) {
        if (this.prefs.contains(key)) {
            return this.prefs.getString(key, null);
        }
        return null;
    }

    public boolean getBoolean(String key) {
        return this.prefs.getBoolean(key, false);
    }

    public long getLong(String key) {
        return this.prefs.getLong(key, 0);
    }

    public int getInt(String key) {
        return this.prefs.getInt(key, 0);
    }

    public Float getFloat(String key) {
        if (this.prefs.contains(key)) {
            return Float.valueOf(((float) ((int) (this.prefs.getFloat(key, 0.0f) * 100.0f))) / 100.0f);
        }
        return null;
    }

    public String getDisabledVersion() {
        return getString("NewRelicAgentDisabledVersion");
    }

    public void saveDisabledVersion(String version) {
        save("NewRelicAgentDisabledVersion", version);
    }

    public int[] getDataToken() {
        int[] dataToken = new int[2];
        String dataTokenString = getString("dataToken");
        if (dataTokenString == null) {
            return null;
        }
        try {
            JSONTokener tokener = new JSONTokener(dataTokenString);
            if (tokener == null) {
                return null;
            }
            JSONArray array = (JSONArray) tokener.nextValue();
            if (array == null) {
                return null;
            }
            dataToken[0] = array.getInt(0);
            dataToken[1] = array.getInt(1);
            return dataToken;
        } catch (JSONException e) {
            e.printStackTrace();
            return dataToken;
        }
    }

    public String getCrossProcessId() {
        return getString("crossProcessId");
    }

    public boolean isCollectingNetworkErrors() {
        return getBoolean("collectNetworkErrors");
    }

    public long getServerTimestamp() {
        return getLong("serverTimestamp");
    }

    public long getHarvestInterval() {
        return getLong("harvestIntervalInSeconds");
    }

    public long getMaxTransactionAge() {
        return getLong("maxTransactionAgeInSeconds");
    }

    public long getMaxTransactionCount() {
        return getLong("maxTransactionCount");
    }

    public int getStackTraceLimit() {
        return getInt("stackTraceLimit");
    }

    public int getResponseBodyLimit() {
        return getInt("responseBodyLimit");
    }

    public int getErrorLimit() {
        return getInt("errorLimit");
    }

    public void saveActivityTraceMinUtilization(float activityTraceMinUtilization) {
        this.activityTraceMinUtilization = Float.valueOf(activityTraceMinUtilization);
        save("activityTraceMinUtilization", activityTraceMinUtilization);
    }

    public float getActivityTraceMinUtilization() {
        if (this.activityTraceMinUtilization == null) {
            this.activityTraceMinUtilization = getFloat("activityTraceMinUtilization");
        }
        return this.activityTraceMinUtilization.floatValue();
    }

    public long getHarvestIntervalInSeconds() {
        return getHarvestInterval();
    }

    public long getMaxTransactionAgeInSeconds() {
        return getMaxTransactionAge();
    }

    public String getAppName() {
        return getString(AnalyticAttribute.APP_NAME_ATTRIBUTE);
    }

    public String getAppVersion() {
        return getString(HexAttributes.HEX_ATTR_APP_VERSION);
    }

    public int getVersionCode() {
        return getInt("versionCode");
    }

    public String getAppBuild() {
        return getString("appBuild");
    }

    public String getPackageId() {
        return getString("packageId");
    }

    public String getAgentName() {
        return getString("agentName");
    }

    public String getAgentVersion() {
        return getString("agentVersion");
    }

    public String getDeviceArchitecture() {
        return getString("deviceArchitecture");
    }

    public String getDeviceId() {
        return getString("deviceId");
    }

    public String getDeviceModel() {
        return getString(AnalyticAttribute.DEVICE_MODEL_ATTRIBUTE);
    }

    public String getDeviceManufacturer() {
        return getString(AnalyticAttribute.DEVICE_MANUFACTURER_ATTRIBUTE);
    }

    public String getDeviceRunTime() {
        return getString("deviceRunTime");
    }

    public String getDeviceSize() {
        return getString("deviceSize");
    }

    public String getOsName() {
        return getString(AnalyticAttribute.OS_NAME_ATTRIBUTE);
    }

    public String getOsBuild() {
        return getString(AnalyticAttribute.OS_BUILD_ATTRIBUTE);
    }

    public String getOsVersion() {
        return getString(AnalyticAttribute.OS_VERSION_ATTRIBUTE);
    }

    public String getApplicationPlatform() {
        return getString(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE);
    }

    public String getApplicationPlatformVersion() {
        return getString(AnalyticAttribute.APPLICATION_PLATFORM_VERSION_ATTRIBUTE);
    }

    public ApplicationPlatform getPlatform() {
        ApplicationPlatform applicationPlatform = ApplicationPlatform.Native;
        try {
            return ApplicationPlatform.valueOf(getString(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE));
        } catch (IllegalArgumentException e) {
            return applicationPlatform;
        }
    }

    public String getPlatformVersion() {
        return getString(AnalyticAttribute.APPLICATION_PLATFORM_VERSION_ATTRIBUTE);
    }

    private String getPreferenceFileName(String packageName) {
        return "com.newrelic.android.agent.v1_" + packageName;
    }

    public void clear() {
        this.lock.lock();
        try {
            this.editor.clear();
            this.editor.apply();
            this.configuration.setDefaultValues();
        } finally {
            this.lock.unlock();
        }
    }
}
